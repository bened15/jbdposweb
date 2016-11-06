package com.jbd.beans;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;

import org.joda.time.DateTime;
import org.joda.time.Hours;
import org.joda.time.Minutes;
import org.primefaces.event.DragDropEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.jbd.hibernate.interfaces.ICtgMenuSubTypeManagement;
import com.jbd.hibernate.interfaces.ICtgMenuTypeManagement;
import com.jbd.hibernate.interfaces.ICtgTipManagement;
import com.jbd.hibernate.interfaces.IRestAreaManagement;
import com.jbd.hibernate.interfaces.IRestBillDetailManagement;
import com.jbd.hibernate.interfaces.IRestBillManagement;
import com.jbd.hibernate.interfaces.IRestMenuItemManagement;
import com.jbd.hibernate.interfaces.IRestOrderManagement;
import com.jbd.hibernate.interfaces.IRestShiftManagement;
import com.jbd.hibernate.interfaces.IRestTableAccountManagement;
import com.jbd.hibernate.interfaces.IRestTableManagement;
import com.jbd.model.CtgMenuSubType;
import com.jbd.model.CtgMenuType;
import com.jbd.model.CtgTip;
import com.jbd.model.RestArea;
import com.jbd.model.RestBill;
import com.jbd.model.RestBillDetail;
import com.jbd.model.RestMenuItem;
import com.jbd.model.RestOrder;
import com.jbd.model.RestTable;
import com.jbd.model.RestTableAccount;

@ManagedBean(name = "splitOrderController")
@SessionScoped
public class SplitOrderController {
	@Autowired
	private IRestAreaManagement manageAreas;
	@Autowired
	private IRestTableManagement manageTables;
	@Autowired
	private IRestTableAccountManagement manageTablesAccount;
	@Autowired
	private ICtgMenuTypeManagement manageMenuType;

	@Autowired
	private ICtgMenuSubTypeManagement manageMenuSubType;

	@Autowired
	private IRestOrderManagement manageRestOrder;

	@Autowired
	private IRestShiftManagement manageRestShift;
	@Autowired
	private IRestMenuItemManagement manageMenuItem;

	@Autowired
	private IRestBillManagement manageRestBill;
	@Autowired
	private IRestBillDetailManagement manageRestBillDetail;

	@Autowired
	private ICtgTipManagement manageCtgTip;

	@Autowired
	private RestArea selectedArea;

	@Autowired
	private CtgTip ctgTipForAccount;

	@Autowired
	private RestBill restBillSelected;
	private String timeOpen;
	@Autowired
	private RestOrder orderSelected;

	private String orderComment, numPersons, numBillsDigit, newNameAccount;

	private boolean editandoOrden = false, mostrarOptions = true;

	@Autowired
	private RestTableAccount rta;

	private double subTotal = 0.0;

	private List<RestArea> areas = new ArrayList<RestArea>();
	private List<RestTable> tables = new ArrayList<RestTable>();
	private List<String> howManyBillsList = new ArrayList<String>();
	private List<RestBill> bills = new ArrayList<RestBill>();

	private List<CtgMenuType> menuType = new ArrayList<CtgMenuType>();
	private List<CtgMenuSubType> menuSubType = new ArrayList<CtgMenuSubType>();
	private List<RestMenuItem> menuItem = new ArrayList<RestMenuItem>();
	private List<RestBillDetail> billsDetail = new ArrayList<RestBillDetail>();
	private List<RestBillDetail> billsDetailNew = new ArrayList<RestBillDetail>();
	private List<RestOrder> menuOrderChanged = new ArrayList<RestOrder>();

	public boolean mostrarAreas = true, mostrarTables = false, mostrarMenuType = false, mostrarMenuItems = false;

	public SplitOrderController() {
	}

	public String setHowManyBillsAccountDivision(String billNum) {
		try {
			if (billNum.length() <= 0) {
				billNum = numBillsDigit;
				System.out.println("puse en el textbos");
			}

			int i = 0;
			bills.clear();
			FacesContext faceContext = FacesContext.getCurrentInstance();
			HomeController hController = (HomeController) faceContext.getApplication()
					.evaluateExpressionGet(faceContext, "#{homeController}", HomeController.class);
			
			rta = manageTablesAccount.findRestTableAccountOpen(hController.getTableSelected());
			hController.setOptionSelected("ADMINISTRAR CUENTAS");
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

			Date c = format.parse(format.format(new Date()));
			DateTime dt1 = new DateTime(c);
			DateTime dt2 = new DateTime(rta.getCreatedDatetime());

			timeOpen = (Hours.hoursBetween(dt2, dt1).getHours() % 24) + ":"
					+ (Minutes.minutesBetween(dt2, dt1).getMinutes() % 60) + " (HH:mm) ";

			billsDetail = manageRestBillDetail.findAllRestBillDetailFromTableAccount(rta);
			List<RestBill> billsOld = new ArrayList<RestBill>();
			billsOld = manageRestBill.findOpenBillsWithRestTableAccount(rta);
			bills.addAll(billsOld);
			Integer acNum = manageRestBill.getMaxBillId();
			while (i < Integer.parseInt(billNum)) {

				UserController userController = (UserController) faceContext.getApplication()
						.evaluateExpressionGet(faceContext, "#{userController}", UserController.class);
				RestBill rb = new RestBill();

				rb.setBillName("Cuenta # " + (acNum + 1));
				rb.setBillSubtotal(0.0);
				rb.setBillTip(0.0);
				rb.setBillTotal(0.0);
				rb.setRestTableAccount(manageTablesAccount.findRestTableAccountOpen(hController.getTableSelected()));
				rb.setEntryDate(new Date());
				rb.setEntryUser(userController.getUser().getSysUser().getUserCode());
				if (rta.getRestTable().getRestArea().getType() != null) {
					rb.setCtgTip(manageCtgTip.findZeroCtgTip());

				} else {
					rb.setCtgTip(manageCtgTip.findDefaultCtgTip());
				}
				bills.add(rb);
				i++;
				acNum++;
			}

			System.out.println("*************billsSize" + bills.size());
			System.out.println("*************rtaid" + rta.getTableAccountId());

			return "reorderOrders.xhtml?face-redirect=true";
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}

	}

	public void onOrderDrop(DragDropEvent ddEvent) {
		try {
			RestBillDetail bd = ((RestBillDetail) ddEvent.getData());
			String idC[] = ddEvent.getDropId().split(":");
			// billsDetail.get(billsDetail.indexOf(bd)).setRestBill(bills.get(Integer.parseInt(idC[3])));
			if (!billsDetailNew.contains(bd)) {
				billsDetail.remove(bd);
				bd.setOldBill(bd.getRestBill());
				bd.setRestBill(bills.get(Integer.parseInt(idC[3])));
				billsDetailNew.add(bd);
				bd.getRestOrder().setBill(bd.getRestBill());
				menuOrderChanged.add(bd.getRestOrder());

			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public List<RestBillDetail> billsToShow(RestBill rb) {
		List<RestBillDetail> bdToReturn = new ArrayList<RestBillDetail>();
		for (RestBillDetail rbdn : billsDetailNew) {
			if (rbdn.getRestBill().getBillName().contains(rb.getBillName())) {
				bdToReturn.add(rbdn);

			}

		}
		for (RestBillDetail rbd : billsDetail) {
			if (rbd.getRestBill().getBillId() == rb.getBillId()) {
				bdToReturn.add(rbd);

			}

		}

		return bdToReturn;

	}

	public void removeMenuItemChanged(RestBillDetail rbd) {
		try {
			billsDetailNew.remove(rbd);
			if (rbd.getOldBill() != null) {
				rbd.setRestBill(rbd.getOldBill());
			}
			if (!billsDetail.contains(rbd)) {
				billsDetail.add(rbd);
			}

			menuOrderChanged.remove(rbd.getRestOrder());
			// System.out.println("size
			// original"+billsDetail.size()+"billId"+rbd.getRestBill().getBillId());
			// System.out.println("size nuevo"+billsDetailNew.size());

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public String confirmChangeAccounts() {
		try {

			for (RestBill b : bills) {
				if (manageRestBill.findRestBill(b.getBillId()) == null) {
					if (b.getBillStatus() != 1) {
						manageRestBill.insertRestBill(b);

					}
				} else {

					manageRestBill.updateRestBill(b);
					if (b.getBillStatus() == 1) {
						manageRestBill.deleteRestBill(b);

					}
				}

			}

			for (RestBillDetail rbd : billsDetailNew) {
				manageRestBillDetail.updateRestBillDetail(rbd);

			}
			this.bills.clear();
			this.billsDetail.clear();
			this.billsDetailNew.clear();
			this.menuOrderChanged.clear();
			FacesContext faceContext = FacesContext.getCurrentInstance();
			HomeController hController = (HomeController) faceContext.getApplication()
					.evaluateExpressionGet(faceContext, "#{homeController}", HomeController.class);
			hController.setMostrarDetalleOrden(false);
			hController.getTableSelected().setTableId(0);
			return "home.xhtml?face-redirect=true";

		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}

	}

	public void changeNameAccount() {
		try {
			bills.get(bills.indexOf(restBillSelected)).setBillName(newNameAccount);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void delRestBillSelected(RestBill rb) {

		try {
			if (bills.get(bills.indexOf(rb)).getBillStatus() == 1) {
				System.out.println("quite la eliminacion");
				bills.get(bills.indexOf(rb)).setBillStatus(0);
			} else {
				System.out.println("puse la eliminacion");

				bills.get(bills.indexOf(rb)).setBillStatus(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@PostConstruct
	private void init() {
		ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
		ServletContext servletContext = (ServletContext) externalContext.getContext();
		WebApplicationContextUtils.getRequiredWebApplicationContext(servletContext).getAutowireCapableBeanFactory()
				.autowireBean(this);
		FacesContext.getCurrentInstance().getExternalContext().getSession(true);

	}

	public List<RestArea> getAreas() {
		return areas;
	}

	public void setAreas(List<RestArea> areas) {
		this.areas = areas;
	}

	public List<RestTable> getTables() {
		return tables;
	}

	public void setTables(List<RestTable> tables) {
		this.tables = tables;
	}

	public RestArea getSelectedArea() {
		return selectedArea;
	}

	public void setSelectedArea(RestArea selectedArea) {
		this.selectedArea = selectedArea;
	}

	public boolean isMostrarAreas() {
		return mostrarAreas;
	}

	public void setMostrarAreas(boolean mostrarAreas) {
		this.mostrarAreas = mostrarAreas;
	}

	public boolean isMostrarTables() {
		return mostrarTables;
	}

	public void setMostrarTables(boolean mostrarTables) {
		this.mostrarTables = mostrarTables;
	}

	public boolean isMostrarMenuType() {
		return mostrarMenuType;
	}

	public void setMostrarMenuType(boolean mostrarMenuType) {
		this.mostrarMenuType = mostrarMenuType;
	}

	public boolean isMostrarMenuItems() {
		return mostrarMenuItems;
	}

	public void setMostrarMenuItems(boolean mostrarMenuItems) {
		this.mostrarMenuItems = mostrarMenuItems;
	}

	public List<String> getHowManyBillsList() {
		return howManyBillsList;
	}

	public void setHowManyBillsList(List<String> howManyBillsList) {
		this.howManyBillsList = howManyBillsList;
	}

	public List<RestBill> getBills() {
		return bills;
	}

	public void setBills(List<RestBill> bills) {
		this.bills = bills;
	}

	public List<CtgMenuSubType> getMenuSubType() {
		return menuSubType;
	}

	public void setMenuSubType(List<CtgMenuSubType> menuSubType) {
		this.menuSubType = menuSubType;
	}

	public List<CtgMenuType> getMenuType() {
		return menuType;
	}

	public void setMenuType(List<CtgMenuType> menuType) {
		this.menuType = menuType;
	}

	public List<RestMenuItem> getMenuItem() {
		return menuItem;
	}

	public void setMenuItem(List<RestMenuItem> menuItem) {
		this.menuItem = menuItem;
	}

	public List<RestBillDetail> getBillsDetail() {
		return billsDetail;
	}

	public void setBillsDetail(List<RestBillDetail> billsDetail) {
		this.billsDetail = billsDetail;
	}

	public String getOrderComment() {
		return orderComment;
	}

	public void setOrderComment(String orderComment) {
		this.orderComment = orderComment;
	}

	public RestOrder getOrderSelected() {
		return orderSelected;
	}

	public void setOrderSelected(RestOrder orderSelected) {
		this.orderSelected = orderSelected;
	}

	public RestBill getRestBillSelected() {
		return restBillSelected;
	}

	public void setRestBillSelected(RestBill restBillSelected) {
		this.restBillSelected = restBillSelected;
	}

	public String getNumPersons() {
		return numPersons;
	}

	public void setNumPersons(String numPersons) {
		this.numPersons = numPersons;
	}

	public double getSubTotal() {
		return subTotal;
	}

	public void setSubTotal(double subTotal) {
		this.subTotal = subTotal;
	}

	public CtgTip getCtgTipForAccount() {
		return ctgTipForAccount;
	}

	public void setCtgTipForAccount(CtgTip ctgTipForAccount) {
		this.ctgTipForAccount = ctgTipForAccount;
	}

	public boolean isEditandoOrden() {
		return editandoOrden;
	}

	public void setEditandoOrden(boolean editandoOrden) {
		this.editandoOrden = editandoOrden;
	}

	public boolean isMostrarOptions() {
		return mostrarOptions;
	}

	public void setMostrarOptions(boolean mostrarOptions) {
		this.mostrarOptions = mostrarOptions;
	}

	public List<RestBillDetail> getBillsDetailNew() {
		return billsDetailNew;
	}

	public void setBillsDetailNew(List<RestBillDetail> billsDetailNew) {
		this.billsDetailNew = billsDetailNew;
	}

	public String getNumBillsDigit() {
		return numBillsDigit;
	}

	public void setNumBillsDigit(String numBillsDigit) {
		this.numBillsDigit = numBillsDigit;
	}

	public List<RestOrder> getMenuOrderChanged() {
		return menuOrderChanged;
	}

	public void setMenuOrderChanged(List<RestOrder> menuOrderChanged) {
		this.menuOrderChanged = menuOrderChanged;
	}

	public String getNewNameAccount() {
		return newNameAccount;
	}

	public void setNewNameAccount(String newNameAccount) {
		this.newNameAccount = newNameAccount;
	}

	public String getTimeOpen() {
		return timeOpen;
	}

	public void setTimeOpen(String timeOpen) {
		this.timeOpen = timeOpen;
	}

}

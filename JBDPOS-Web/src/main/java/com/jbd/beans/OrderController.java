package com.jbd.beans;

import java.io.IOException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;

import org.joda.time.DateTime;
import org.joda.time.Hours;
import org.joda.time.Minutes;
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

@ManagedBean(name = "orderController")
@ViewScoped
public class OrderController {
	@Autowired
	private IRestAreaManagement manageAreas;
	@Autowired
	private IRestTableManagement manageTables;
	@Autowired
	private IRestTableAccountManagement manageTablesAccount;
	@Autowired
	private ICtgMenuTypeManagement manageMenuType;
	private DecimalFormat decimFormat = new DecimalFormat("#.00");
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

	@Autowired
	private RestOrder orderSelected;
	private String timeOpen;
	@Autowired
	private RestMenuItem itemSelected;

	@Autowired
	private CtgMenuType menuTypeSelected;

	private String orderComment, elementName;
	private Integer numPersons;

	private boolean editandoOrden = false, mostrarOptions = true;

	@Autowired
	private RestTableAccount rta;

	private double subTotal = 0.0, total = 0.0, tip = 0.0;

	private List<RestArea> areas = new ArrayList<RestArea>();
	private List<RestTable> tables = new ArrayList<RestTable>();
	private List<String> howManyBillsList = new ArrayList<String>();
	private List<RestBill> bills = new ArrayList<RestBill>();
	private List<CtgMenuType> menuType = new ArrayList<CtgMenuType>();
	private List<CtgMenuSubType> menuSubType = new ArrayList<CtgMenuSubType>();
	private List<RestMenuItem> menuItem = new ArrayList<RestMenuItem>();
	private List<RestOrder> menuItemSelectedOrder = new ArrayList<RestOrder>();

	public boolean mostrarAreas = true, mostrarTables = false, mostrarMenuType = false, mostrarMenuItems = false,
			mostrarMenuSubType = false;

	public OrderController() {
	}

	public void loadPanesForTables(RestArea a) {

		setTables(manageTables.findTablesByArea(a));
		mostrarAreas = false;
		mostrarTables = true;

	}

	public void setTableSelected(RestTable rt) {
		FacesContext faceContext = FacesContext.getCurrentInstance();
		UserController userController = (UserController) faceContext.getApplication().evaluateExpressionGet(faceContext,
				"#{userController}", UserController.class);
		rta = new RestTableAccount();
		rta.setRestShift2(manageRestShift.alreadyExistShift());
		rta.setAccountStatus("OPEN");
		rta.setRestShift1(null);
		rta.setCreatedBy(userController.getUser().getSysUser().getUserCode());
		rta.setCreatedDatetime(new Date());
		rt.setStatus("OCUPADO");
		rta.setRestTable(rt);

	}

	public void addNumGuest() {
		rta.setGuestNum(numPersons);

	}

	public void setHowManyBills(String billNum) {
		try {
			int i = 0;
			Integer acNum = manageRestBill.getMaxBillId();
			while (i < Integer.parseInt(billNum)) {
				FacesContext faceContext = FacesContext.getCurrentInstance();
				UserController userController = (UserController) faceContext.getApplication()
						.evaluateExpressionGet(faceContext, "#{userController}", UserController.class);
				RestBill rb = new RestBill();

				rb.setBillName("Cuenta # " + (acNum + 1));
				rb.setBillSubtotal(0.0);
				rb.setBillTip(0.0);
				rb.setBillTotal(0.0);
				rb.setRestTableAccount(rta);
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
			mostrarTables = false;

			menuType = manageMenuType.findAll();

			mostrarMenuType = true;
			addNumGuest();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void setMenuTypeSelected(CtgMenuType mt) {
		try {

			setMenuItem(manageMenuItem.findMenuItemByTypeMenu(mt));
			setMenuSubType(manageMenuSubType.findByMenuType(mt));
			mostrarMenuItems = true;
			mostrarMenuSubType = true;
			menuTypeSelected = mt;

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void searchByElementName() {
		try {
			setMenuItem(manageMenuItem.findMenuItemByName(elementName));
			mostrarMenuItems = true;
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void searchMenuSubTypeSelected(CtgMenuSubType mst) {
		try {
			System.out.println("cargue");

			setMenuItem(manageMenuItem.findMenuItemBySubTypeMenu(mst));
			mostrarMenuItems = true;
			mostrarMenuSubType = true;

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void setBillSelected(RestBill rb) {

		try {
			RestOrder o = new RestOrder();

			o.setRestMenuItem(itemSelected);
			o.setEntryDate(new Date());
			o.setRestShift(manageRestShift.alreadyExistShift());
			o.setRestTableAccount(rta);
			o.setOrderStatus("PROCESO");
			o.setBill(rb);
			menuItemSelectedOrder.add(o);
			subTotal = Double.parseDouble(decimFormat.format(subTotal + itemSelected.getMenuItemPrice()));
			tip = Double.parseDouble(decimFormat.format(subTotal *  (rb.getCtgTip().getPercentValue() / 100.0)));
			total = Double.parseDouble(decimFormat.format(subTotal + tip));

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void setBillSelectedForOne(RestMenuItem rm) {

		try {
			// System.out.println("entre a selection");
			RestOrder o = new RestOrder();

			o.setRestMenuItem(rm);
			o.setEntryDate(new Date());
			o.setRestShift(manageRestShift.alreadyExistShift());
			o.setRestTableAccount(rta);
			o.setOrderStatus("PROCESO");
			o.setBill(bills.get(0));
			// System.out.println("Item selected: " + rm.getMenuItemName());
			menuItemSelectedOrder.add(o);
			subTotal = Double.parseDouble(decimFormat.format(subTotal + rm.getMenuItemPrice()));
			tip = Double
					.parseDouble(decimFormat.format(subTotal * (bills.get(0).getCtgTip().getPercentValue() / 100.0)));
			total = Double.parseDouble(decimFormat.format(subTotal + tip));
			// System.out.println("Item inserted: " +
			// o.getRestMenuItem().getMenuItemName());

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void removeMenuItemOrder(RestOrder or) {
		try {

			// era una orden ya existente
			if (or.getOrderStatus().contains("COCINA")) {

				RestBillDetail rbd = manageRestBillDetail.findRestBillDetailByOrder(or);
				manageRestBillDetail.deleteRestBillDetail(rbd);
				or.setOrderStatus("CANCELADA");
				manageRestOrder.updateRestOrder(or);
			}

			menuItemSelectedOrder.remove(or);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void addCommentOrder() {
		try {

			if (orderSelected.getOrderStatus().contains("COCINA")) {
				orderSelected.setOrderComment(orderComment);
				manageRestOrder.updateRestOrder(orderSelected);
			} else {

				menuItemSelectedOrder.get(menuItemSelectedOrder.indexOf(orderSelected)).setOrderComment(orderComment);
				orderComment = "";
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public String cancelAccount() {
		try {
			RestTable rt = rta.getRestTable();
			rt.setStatus("DESOCUPADO");
			manageTables.updateRestTable(rt);
			rta.setAccountStatus("CANCELADA");
			rta.setClosedDatetime(new Date());
			rta.setRestShift2(manageRestShift.alreadyExistShift());
			manageTablesAccount.updateRestTableAccount(rta);
			List<RestOrder> ordersToCancel = new ArrayList<RestOrder>();
			ordersToCancel = manageRestOrder.findAllRestOrdersFromTableAccount(rta);
			for (RestOrder o : ordersToCancel) {
				o.setOrderStatus("CANCELADA");
				manageRestOrder.updateRestOrder(o);

			}
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

	public String confirmOrder() {
		try {

			// for (RestOrder o : menuItemSelectedOrder) {
			// System.out.println(o.getRestMenuItem().getMenuItemName());
			// System.out.println(o.getRestMenuItem().getMenuItemPrice());
			// System.out.println(o.getOrderComment());
			//
			// }
			int i = 0;
			if (!editandoOrden) {
				manageTables.updateRestTable(rta.getRestTable());
				manageTablesAccount.insertRestTableAccount(rta);

				while (i < bills.size()) {

					manageRestBill.insertRestBill(bills.get(i));
					i++;

				}
				i = 0;
			}
			while (i < menuItemSelectedOrder.size()) {
				if (menuItemSelectedOrder.get(i).getOrderStatus().contains("PROCESO")) {

					RestBillDetail rbd = new RestBillDetail();
					RestOrder ro = new RestOrder();
					RestOrder roReturned = new RestOrder();
					ro = menuItemSelectedOrder.get(i);
					ro.setOrderStatus("COCINA");

					roReturned = manageRestOrder.insertRestOrder(ro);

					rbd.setRestOrder(roReturned);
					rbd.setBillDetailSubtotal(ro.getRestMenuItem().getMenuItemPrice());
					rbd.setBillDetailTotal(ro.getRestMenuItem().getMenuItemPrice()
							* (1 + (ro.getBill().getCtgTip().getPercentValue() / 100.0)));
					rbd.setRestBill(ro.getBill());

					manageRestBillDetail.insertRestBillDetail(rbd);

					i++;
				} else {

					i++;
				}

			}
			manageRestBill.cuadrarCuentas(rta);
			bills.clear();
			menuItemSelectedOrder.clear();
			FacesContext faceContext = FacesContext.getCurrentInstance();
			HomeController hController = (HomeController) faceContext.getApplication()
					.evaluateExpressionGet(faceContext, "#{homeController}", HomeController.class);
			hController.setMostrarDetalleOrden(false);
			hController.getTableSelected().setTableId(0);
			return "home.xhtml?face-redirect=true";

		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", e.getMessage()));
			e.printStackTrace();
			return "";

		}

	}

	// *********************************************Metodos para la edicion de
	// ordenes*******************************//

	public void lookForOpenAccounts(RestTable rt) {

		try {
			rta = manageTablesAccount.findRestTableAccountOpen(rt);
			if (rta != null) {
				manageRestBill.cuadrarCuentas(rta);

				menuItemSelectedOrder = manageRestOrder.findAllRestOrdersFromTableAccount(rta);
				bills = manageRestBill.findBillsWithRestTableAccount(rta);
				ctgTipForAccount = bills.get(0).getCtgTip();

				subTotal = manageRestBill.getSubTotalAccountFromTable(rta);
				tip = manageRestBill.getTotalTipAccountFromTable(rta);
				total = manageRestBill.getTotalAccountFromTable(rta);

				SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

				Date c = format.parse(format.format(new Date()));
				DateTime dt1 = new DateTime(c);
				DateTime dt2 = new DateTime(rta.getCreatedDatetime());

				timeOpen = (Hours.hoursBetween(dt2, dt1).getHours() % 24) + ":"
						+ (Minutes.minutesBetween(dt2, dt1).getMinutes() % 60) + " (HH:mm) ";

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void setEditarOrden(String billNum) {
		try {

			mostrarTables = false;

			menuType = manageMenuType.findAll();

			mostrarMenuType = true;
			editandoOrden = true;
			mostrarOptions = true;

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void showTablesForEditAccount() {
		mostrarOptions = false;
	}

	public String changeAccountToTable(RestTable rt) {
		RestTable oldTable = rta.getRestTable();
		oldTable.setStatus("DESOCUPADO");
		manageTables.updateRestTable(oldTable);
		rt.setStatus("OCUPADO");
		rta.setRestTable(rt);
		manageTables.updateRestTable(rt);
		manageTablesAccount.updateRestTableAccount(rta);

		// setTables(manageTables.findTablesByArea(rta.getRestTable().getRestArea()));

		FacesContext faceContext = FacesContext.getCurrentInstance();
		HomeController hController = (HomeController) faceContext.getApplication().evaluateExpressionGet(faceContext,
				"#{homeController}", HomeController.class);
		hController.setMostrarDetalleOrden(false);
		hController.getTableSelected().setTableId(0);
		return "home.xhtml?face-redirect=true";

	}

	@PostConstruct
	private void init() {
		ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
		ServletContext servletContext = (ServletContext) externalContext.getContext();
		WebApplicationContextUtils.getRequiredWebApplicationContext(servletContext).getAutowireCapableBeanFactory()
				.autowireBean(this);
		FacesContext.getCurrentInstance().getExternalContext().getSession(true);

		// FacesContext faceContext = FacesContext.getCurrentInstance();
		// this.loadBrands();

		areas = manageAreas.findAll();
		howManyBillsList.add("1");
		howManyBillsList.add("2");
		howManyBillsList.add("3");
		howManyBillsList.add("4");
		howManyBillsList.add("5");
		howManyBillsList.add("6");
		howManyBillsList.add("7");
		howManyBillsList.add("8");
		howManyBillsList.add("9");
		howManyBillsList.add("10");

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

	public List<RestOrder> getMenuItemSelectedOrder() {
		return menuItemSelectedOrder;
	}

	public void setMenuItemSelectedOrder(List<RestOrder> menuItemSelectedOrder) {
		this.menuItemSelectedOrder = menuItemSelectedOrder;
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

	public Integer getNumPersons() {
		return numPersons;
	}

	public void setNumPersons(Integer numPersons) {
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

	public RestTableAccount getRta() {
		return rta;
	}

	public void setRta(RestTableAccount rta) {
		this.rta = rta;
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

	public RestMenuItem getItemSelected() {
		return itemSelected;
	}

	public void setItemSelected(RestMenuItem itemSelected) {
		this.itemSelected = itemSelected;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public double getTip() {
		return tip;
	}

	public void setTip(double tip) {
		this.tip = tip;
	}

	public String getElementName() {
		return elementName;
	}

	public void setElementName(String elementName) {
		this.elementName = elementName;
	}

	public CtgMenuType getMenuTypeSelected() {
		return menuTypeSelected;
	}

	public boolean isMostrarMenuSubType() {
		return mostrarMenuSubType;
	}

	public void setMostrarMenuSubType(boolean mostrarMenuSubType) {
		this.mostrarMenuSubType = mostrarMenuSubType;
	}

	public String getTimeOpen() {
		return timeOpen;
	}

	public void setTimeOpen(String timeOpen) {
		this.timeOpen = timeOpen;
	}

}

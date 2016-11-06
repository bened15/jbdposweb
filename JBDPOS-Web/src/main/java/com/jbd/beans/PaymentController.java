package com.jbd.beans;

import java.io.IOException;
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
import org.primefaces.context.RequestContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.SystemPropertyUtils;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.jbd.hibernate.interfaces.ICtgMenuSubTypeManagement;
import com.jbd.hibernate.interfaces.ICtgMenuTypeManagement;
import com.jbd.hibernate.interfaces.ICtgPaymentMethodManagement;
import com.jbd.hibernate.interfaces.ICtgTipManagement;
import com.jbd.hibernate.interfaces.IRestAreaManagement;
import com.jbd.hibernate.interfaces.IRestBillDetailManagement;
import com.jbd.hibernate.interfaces.IRestBillManagement;
import com.jbd.hibernate.interfaces.IRestBillPaymentManagement;
import com.jbd.hibernate.interfaces.IRestMenuItemManagement;
import com.jbd.hibernate.interfaces.IRestOrderManagement;
import com.jbd.hibernate.interfaces.IRestShiftManagement;
import com.jbd.hibernate.interfaces.IRestTableAccountManagement;
import com.jbd.hibernate.interfaces.IRestTableManagement;
import com.jbd.model.CtgMenuSubType;
import com.jbd.model.CtgMenuType;
import com.jbd.model.CtgPaymentMethod;
import com.jbd.model.CtgTip;
import com.jbd.model.RestArea;
import com.jbd.model.RestBill;
import com.jbd.model.RestBillDetail;
import com.jbd.model.RestBillPayment;
import com.jbd.model.RestMenuItem;
import com.jbd.model.RestOrder;
import com.jbd.model.RestTable;
import com.jbd.model.RestTableAccount;

@ManagedBean(name = "paymentController")
@ViewScoped
public class PaymentController {
	@Autowired
	private IRestAreaManagement manageAreas;
	@Autowired
	private IRestTableManagement manageTables;
	@Autowired
	private IRestTableAccountManagement manageTablesAccount;
	@Autowired
	private ICtgMenuTypeManagement manageMenuType;
	@Autowired
	private ICtgPaymentMethodManagement manageCtgPaymentsMethods;
	private String timeOpen;
	@Autowired
	private IRestBillPaymentManagement manageResBillPayment;

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
	private CtgPaymentMethod paymentMethodSelected;

	@Autowired
	private RestOrder orderSelected;

	private String orderComment, numPersons, numBillsDigit, newNameAccount, totalAbono = "", infoPago = "";

	private boolean editandoOrden = false, mostrarOptions = true;

	@Autowired
	private RestTableAccount rta;

	private double subTotal = 0.0, total = 0.0, tip = 0.0, totalRestante = 0.0;

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
	private List<CtgPaymentMethod> paymentTypes = new ArrayList<CtgPaymentMethod>();
	private List<RestOrder> ordenForPreCuenta = new ArrayList<RestOrder>();
	private List<String> calculatorValues = new ArrayList<String>();

	public boolean mostrarAreas = true, mostrarTables = false, mostrarMenuType = false, mostrarMenuItems = false;

	public PaymentController() {
	}

	public void affectPayment(String value) {
		try {

			if (value.contains("BORRAR")) {
				this.totalAbono = this.totalAbono.substring(0, totalAbono.length() - 1);
			} else {

				this.totalAbono = this.totalAbono + value;
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

	public String setPaymentMethodSelectedAction(CtgPaymentMethod pay) {
		try {
			this.setPaymentMethodSelected(pay);
			this.setInfoPago("");
			this.totalAbono = "";
			RequestContext context = RequestContext.getCurrentInstance();
			if (pay.getName().contains("RAPIDO")) {
				RestBillPayment bp = new RestBillPayment();
				bp.setCtgPaymentMethod(manageCtgPaymentsMethods.findCtgPaymentMethod("EFECTIVO"));
				bp.setAmount(manageRestBill.getTotalAccountFromTableCheckingDiscount(rta, restBillSelected));
				bp.setComments("PAGO RAPIDO!");
				bp.setRestBill(restBillSelected);
				manageResBillPayment.insertRestBillPayment(bp);
				restBillSelected.setStatus("CLOSED");
				manageRestBill.updateRestBill(restBillSelected);
				bills = manageRestBill.findOpenBillsWithRestTableAccount(rta);

				if (bills.size() == 0) {
					rta.getRestTable().setStatus("DESOCUPADO");
					rta.setAccountStatus("CLOSED");
					rta.setRestShift1(manageRestShift.alreadyExistShift());
					rta.setClosedDatetime(new Date());

					manageTables.updateRestTable(rta.getRestTable());
					manageTablesAccount.updateRestTableAccount(rta);
					FacesContext faceContext = FacesContext.getCurrentInstance();
					HomeController hController = (HomeController) faceContext.getApplication()
							.evaluateExpressionGet(faceContext, "#{homeController}", HomeController.class);
					hController.getTableSelected().setTableId(0);

					return "home.xhtml?faces-redirect=true";
				}

			}
			if (pay.getName().contains("Tarjeta")) {
				// System.out.println("Entre");
				totalRestante = manageResBillPayment.totalBillPending(restBillSelected);
				context.execute("PF('ctgPaymentsDialog').hide()");
				context.execute("PF('paymentBar').show()");
				context.update("paymentBarId");

			}
			if (pay.getName().contains("Cupon")) {
				// System.out.println("Entre");
				totalRestante = manageResBillPayment.totalBillPending(restBillSelected);
				context.execute("PF('ctgPaymentsDialog').hide()");
				context.execute("PF('paymentBar').show()");
				context.update("paymentBarId");

			}
			if (pay.getName().contains("Efectivo")) {
				// System.out.println("Entre");
				totalRestante = manageResBillPayment.totalBillPending(restBillSelected);
				context.execute("PF('ctgPaymentsDialog').hide()");
				context.execute("PF('paymentBar').show()");
				context.update("paymentBarId");

			}
			return "";
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}

	public void createPreCuenta(RestBill rb) {
		try {
			this.setRestBillSelected(rb);
			ordenForPreCuenta = manageRestBillDetail.findAllRestBillDetailFromRestBillForTable(rb);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void printPreCuenta() {
		System.out.println("Print precuenta supuestament");
	}

	public String generarPago() {
		try {
			if (!this.totalAbono.isEmpty()) {
				if (Double.parseDouble(this.totalAbono) <= 0.0) {

					FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
							"Error!", "No se puede efectuar un pago menor a cero"));
					return "";
				}
				if (Double.parseDouble(this.totalAbono) > totalRestante) {

					FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
							"Error!", "No se puede efectuar un pago mayor al monto total de la factura"));
					return "";
				}
				RestBillPayment bp = new RestBillPayment();
				bp.setCtgPaymentMethod(this.paymentMethodSelected);
				bp.setAmount(Double.parseDouble(this.totalAbono));
				bp.setComments(infoPago);
				bp.setRestBill(restBillSelected);
				manageResBillPayment.insertRestBillPayment(bp);
				totalRestante = manageResBillPayment.totalBillPending(restBillSelected);

				if (totalRestante == 0) {

					restBillSelected.setStatus("CLOSED");
					manageRestBill.updateRestBill(restBillSelected);
				}

				bills = manageRestBill.findOpenBillsWithRestTableAccount(rta);

				if (bills.size() == 0) {
					rta.getRestTable().setStatus("DESOCUPADO");
					rta.setAccountStatus("CLOSED");
					rta.setRestShift1(manageRestShift.alreadyExistShift());
					rta.setClosedDatetime(new Date());

					manageTables.updateRestTable(rta.getRestTable());
					manageTablesAccount.updateRestTableAccount(rta);
					FacesContext faceContext = FacesContext.getCurrentInstance();
					HomeController hController = (HomeController) faceContext.getApplication()
							.evaluateExpressionGet(faceContext, "#{homeController}", HomeController.class);
					hController.getTableSelected().setTableId(0);

					return "home.xhtml?faces-redirect=true";
				}
				RequestContext context = RequestContext.getCurrentInstance();
				context.update("paymentForm:totalBill");
				context.update("paymentForm:totalAbonoId");
				totalAbono = "";
				infoPago = "";
				return "";
			} else {

				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Ingrese una cantidad"));
				return "";
			}
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}

	@PostConstruct
	private void init() {
		ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
		ServletContext servletContext = (ServletContext) externalContext.getContext();
		WebApplicationContextUtils.getRequiredWebApplicationContext(servletContext).getAutowireCapableBeanFactory()
				.autowireBean(this);
		FacesContext.getCurrentInstance().getExternalContext().getSession(true);

		FacesContext faceContext = FacesContext.getCurrentInstance();

		try {
			bills.clear();

			HomeController hController = (HomeController) faceContext.getApplication()
					.evaluateExpressionGet(faceContext, "#{homeController}", HomeController.class);

			if (hController.getTableSelected().getTableId() != 0 && !hController.getOptionSelected().contains("ADMINISTRAR CUENTAS")) {
				hController.setMostrarDetalleOrden(false);
				rta = manageTablesAccount.findRestTableAccountOpen(hController.getTableSelected());
				manageRestBill.cuadrarCuentas(rta);
				billsDetail = manageRestBillDetail.findAllRestBillDetailFromTableAccount(rta);

				bills = manageRestBill.findOpenBillsWithRestTableAccount(rta);
				subTotal = manageRestBill.getSubTotalAccountFromTable(rta);
				tip = manageRestBill.getTotalTipAccountFromTable(rta);
				total = manageRestBill.getTotalAccountFromTable(rta);
				SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

				Date c = format.parse(format.format(new Date()));
				DateTime dt1 = new DateTime(c);
				DateTime dt2 = new DateTime(rta.getCreatedDatetime());

				timeOpen = (Hours.hoursBetween(dt2, dt1).getHours() % 24) + ":"
						+ (Minutes.minutesBetween(dt2, dt1).getMinutes() % 60) + " (HH:mm) ";

				paymentTypes = manageCtgPaymentsMethods.findAll();
				CtgPaymentMethod p = new CtgPaymentMethod();
				p.setName("PAGO RAPIDO!");
				paymentTypes.add(p);
				hController.setOptionSelected("EFECTUAR PAGO");

				// fill calculator
				this.calculatorValues.add("7");
				this.calculatorValues.add("8");
				this.calculatorValues.add("9");
				this.calculatorValues.add("4");
				this.calculatorValues.add("5");
				this.calculatorValues.add("6");
				this.calculatorValues.add("1");
				this.calculatorValues.add("2");
				this.calculatorValues.add("3");
				this.calculatorValues.add("0");
				this.calculatorValues.add(".");
				this.calculatorValues.add("BORRAR");

			}

		} catch (Exception e) {
			e.printStackTrace();

		}

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

	public List<CtgPaymentMethod> getPaymentTypes() {
		return paymentTypes;
	}

	public void setPaymentTypes(List<CtgPaymentMethod> paymentTypes) {
		this.paymentTypes = paymentTypes;
	}

	public CtgPaymentMethod getPaymentMethodSelected() {
		return paymentMethodSelected;
	}

	public void setPaymentMethodSelected(CtgPaymentMethod paymentMethodSelected) {
		this.paymentMethodSelected = paymentMethodSelected;

	}

	public IRestTableAccountManagement getManageTablesAccount() {
		return manageTablesAccount;
	}

	public void setManageTablesAccount(IRestTableAccountManagement manageTablesAccount) {
		this.manageTablesAccount = manageTablesAccount;
	}

	public IRestBillManagement getManageRestBill() {
		return manageRestBill;
	}

	public void setManageRestBill(IRestBillManagement manageRestBill) {
		this.manageRestBill = manageRestBill;
	}

	public IRestBillDetailManagement getManageRestBillDetail() {
		return manageRestBillDetail;
	}

	public void setManageRestBillDetail(IRestBillDetailManagement manageRestBillDetail) {
		this.manageRestBillDetail = manageRestBillDetail;
	}

	public RestTableAccount getRta() {
		return rta;
	}

	public void setRta(RestTableAccount rta) {
		this.rta = rta;
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

	public String getTimeOpen() {
		return timeOpen;
	}

	public void setTimeOpen(String timeOpen) {
		this.timeOpen = timeOpen;
	}

	public List<RestOrder> getOrdenForPreCuenta() {
		return ordenForPreCuenta;
	}

	public void setOrdenForPreCuenta(List<RestOrder> ordenForPreCuenta) {
		this.ordenForPreCuenta = ordenForPreCuenta;
	}

	public List<String> getCalculatorValues() {
		return calculatorValues;
	}

	public void setCalculatorValues(List<String> calculatorValues) {
		this.calculatorValues = calculatorValues;
	}

	public String getTotalAbono() {
		return totalAbono;
	}

	public void setTotalAbono(String totalAbono) {
		this.totalAbono = totalAbono;
	}

	public String getInfoPago() {
		return infoPago;
	}

	public void setInfoPago(String infoPago) {
		this.infoPago = infoPago;
	}

	public double getTotalRestante() {
		return totalRestante;
	}

	public void setTotalRestante(double totalRestante) {
		this.totalRestante = totalRestante;
	}

	public IRestBillPaymentManagement getManageResBillPayment() {
		return manageResBillPayment;
	}

	public void setManageResBillPayment(IRestBillPaymentManagement manageResBillPayment) {
		this.manageResBillPayment = manageResBillPayment;
	}

}

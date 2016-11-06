package com.jbd.beans;

import java.util.Date;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.jbd.hibernate.interfaces.IRestShiftManagement;
import com.jbd.model.RestShift;
import com.jbd.model.RestTable;

@ManagedBean(name = "homeController")
@SessionScoped
public class HomeController {

	private boolean mostrarDetalleOrden = false;
	@Autowired
	private RestTable tableSelected;
	private double iniMoney;
	private String optionSelected;
	@Autowired
	private IRestShiftManagement manageRestShift;
	@Autowired
	private RestShift aShift;

	public HomeController() {

	}

	public String takeNewOrder() {
		mostrarDetalleOrden = true;
		optionSelected = "NUEVA ORDEN";
		return "takeOrder.xhtml?faces-redirect=true";

	}

	public String editOrder() {
		mostrarDetalleOrden = true;
		optionSelected = "EDITAR ORDEN";
		this.tableSelected.setTableId(0);

		return "editOrder.xhtml?faces-redirect=true";

	}

	public String orderToGo() {
		mostrarDetalleOrden = true;
		optionSelected = "ORDEN PARA LLEVAR";
		return "takeOrderToGo.xhtml?faces-redirect=true";
	}

	public String goHomePage() {
		mostrarDetalleOrden = false;
		optionSelected = "PAGINA PRINCIPAL";
		FacesContext faceContext = FacesContext.getCurrentInstance();
		SplitOrderController splitController = (SplitOrderController) faceContext.getApplication()
				.evaluateExpressionGet(faceContext, "#{splitOrderController}", SplitOrderController.class);
		splitController.getBills().clear();
		splitController.getBillsDetail().clear();
		splitController.getBillsDetailNew().clear();
		splitController.getMenuOrderChanged().clear();
		this.tableSelected.setTableId(0);

		return "home.xhtml?faces-redirect=true";

	}

	public void iniciarShift() {
		try {
			if (manageRestShift.alreadyExistShift() == null) {
				FacesContext faceContext = FacesContext.getCurrentInstance();
				UserController userController = (UserController) faceContext.getApplication()
						.evaluateExpressionGet(faceContext, "#{userController}", UserController.class);
				RestShift newShift = new RestShift();
				newShift.setInitialMoney(iniMoney);
				newShift.setOpenedBy(userController.getUser().getSysUser().getUserCode());
				newShift.setOpeningDatetime(new Date());
				newShift.setStatus("OPEN");
				aShift = manageRestShift.insertRestShift(newShift);

				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
						"Se aperturo un nuevo turno!", "SE EFECTUO LA APERTURA DEL TURNO EXITOSAMENTE"));
			} else {

				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Ya existe un turno abierto!"));

			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void cerrarShift() {
		try {
			RestShift actualShift = manageRestShift.alreadyExistShift();

			if (actualShift != null) {
				FacesContext faceContext = FacesContext.getCurrentInstance();
				UserController userController = (UserController) faceContext.getApplication()
						.evaluateExpressionGet(faceContext, "#{userController}", UserController.class);
				manageRestShift.closeShift(actualShift.getIdShift(),
						userController.getUser().getSysUser().getUserCode());
				aShift = null;
				// restTableAccount.setRestShift1(restS);
				// restTableAccount.setAccountStatus("CLOSED");
				// manageRestTableAccount.updateRestTableAccount(restTableAccount);

				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_INFO, "Se cerro el turno!", "SE HA CERRADO EL TURNO"));

			}

		} catch (

		Exception e) {
			e.printStackTrace();
		}

	}

	public String lockApp() {
		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();

		HttpServletRequest httpServletRequest = (HttpServletRequest) FacesContext.getCurrentInstance()
				.getExternalContext().getRequest();

		httpServletRequest.getSession().invalidate();
		return "login.xhtml?faces-redirect=true";

	}

	@PostConstruct
	private void init() {
		ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
		ServletContext servletContext = (ServletContext) externalContext.getContext();
		WebApplicationContextUtils.getRequiredWebApplicationContext(servletContext).getAutowireCapableBeanFactory()
				.autowireBean(this);
		FacesContext.getCurrentInstance().getExternalContext().getSession(true);

		FacesContext faceContext = FacesContext.getCurrentInstance();
		SplitOrderController splitController = (SplitOrderController) faceContext.getApplication()
				.evaluateExpressionGet(faceContext, "#{splitOrderController}", SplitOrderController.class);
		splitController.getBills().clear();
		splitController.getBillsDetail().clear();
		splitController.getBillsDetailNew().clear();
		splitController.getMenuOrderChanged().clear();
		aShift = manageRestShift.alreadyExistShift();

		// FacesContext faceContext = FacesContext.getCurrentInstance();

	}

	public double getIniMoney() {
		return iniMoney;
	}

	public void setIniMoney(double iniMoney) {
		this.iniMoney = iniMoney;
	}

	public boolean isMostrarDetalleOrden() {
		return mostrarDetalleOrden;
	}

	public void setMostrarDetalleOrden(boolean mostrarDetalleOrden) {
		this.mostrarDetalleOrden = mostrarDetalleOrden;
	}

	public RestTable getTableSelected() {
		return tableSelected;
	}

	public void setTableSelected(RestTable tableSelected) {
		this.tableSelected = tableSelected;
	}

	public RestShift getaShift() {
		return aShift;
	}

	public void setaShift(RestShift aShift) {
		this.aShift = aShift;
	}

	public String getOptionSelected() {
		return optionSelected;
	}

	public void setOptionSelected(String optionSelected) {
		this.optionSelected = optionSelected;
	}

}

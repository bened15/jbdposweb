package com.jbd.beans;

import java.io.IOException;
import java.util.Date;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.SystemPropertyUtils;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.jbd.hibernate.interfaces.IRestShiftManagement;
import com.jbd.hibernate.interfaces.ISysUserManagement;
import com.jbd.hibernate.interfaces.ISysUserRolManagement;
import com.jbd.model.RestShift;
import com.jbd.model.RestTable;
import com.jbd.model.SysUserRol;
import com.jbd.utils.Crypter;

@ManagedBean(name = "userController")
@SessionScoped
public class UserController {

	private boolean mostrarDetalleOrden = false;
	@Autowired
	private RestTable tableSelected;
	private double iniMoney;
	@Autowired
	private IRestShiftManagement manageRestShift;
	@Autowired
	private RestShift aShift;
	@Autowired
	private SysUserRol user;

	private String userName, userPass;
	@Autowired
	private ISysUserRolManagement manageSysUserRol;
	private Crypter crypter = new Crypter();

	public UserController() {

	}

	public String validateUser() {
		try {
			// System.out.println(crypter.encrypt(userPass));
			user = manageSysUserRol.findSysUserRol(userName, crypter.encrypt(userPass));
			if (user != null) {

				return "home.xhtml?faces-redirect=true";

			} else {
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_ERROR, "Usuario o contrasena incorrecta!", ""));
				return "";
			}

		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}

	}

	public String validateSession() {
//		ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
		
		if (this.user.getSysUser() == null) {
			System.out.println("entre");
			return "login.xhtml?faces-redirect=true";
		}
		return "";
	}

	@PostConstruct
	private void init() {
		ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
		ServletContext servletContext = (ServletContext) externalContext.getContext();
		WebApplicationContextUtils.getRequiredWebApplicationContext(servletContext).getAutowireCapableBeanFactory()
				.autowireBean(this);
		FacesContext.getCurrentInstance().getExternalContext().getSession(true);

		// FacesContext faceContext = FacesContext.getCurrentInstance();
		// SplitOrderController splitController = (SplitOrderController)
		// faceContext.getApplication()
		// .evaluateExpressionGet(faceContext, "#{splitOrderController}",
		// SplitOrderController.class);
		// splitController.getBills().clear();
		// splitController.getBillsDetail().clear();
		// splitController.getBillsDetailNew().clear();
		// splitController.getMenuOrderChanged().clear();
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

	public SysUserRol getUser() {
		return user;
	}

	public void setUser(SysUserRol user) {
		this.user = user;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserPass() {
		return userPass;
	}

	public void setUserPass(String userPass) {
		this.userPass = userPass;
	}

}

package com.jbd.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the sys_user database table.
 *
 */
@Entity
@Table(name="sys_user")
@NamedQuery(name="SysUser.findAll", query="SELECT s FROM SysUser s")
public class SysUser implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="USER_CODE")
	private String userCode;

	@Temporal(TemporalType.DATE)
	@Column(name="EMPLOYMENT_BEGIN")
	private Date employmentBegin;

	@Temporal(TemporalType.DATE)
	@Column(name="EMPLOYMENT_END")
	private Date employmentEnd;

	@Temporal(TemporalType.DATE)
	@Column(name="ENTRY_DATE")
	private Date entryDate;

	@Column(name="ENTRY_USER")
	private String entryUser;

	@Column(name="USER_ADDRESS")
	private String userAddress;

	@Temporal(TemporalType.DATE)
	@Column(name="USER_BIRTH_DATE")
	private Date userBirthDate;

	@Column(name="USER_EMAIL")
	private String userEmail;

	@Column(name="USER_LASTNAME")
	private String userLastname;

	@Column(name="USER_NAME")
	private String userName;

	@Column(name="USER_PASSWORD")
	private String userPassword;

	@Column(name="USER_PHONE1")
	private String userPhone1;

	@Column(name="USER_PHONE2")
	private String userPhone2;

	//bi-directional many-to-one association to SysUserRol
	@OneToMany(mappedBy="sysUser",fetch = FetchType.EAGER)
	private List<SysUserRol> sysUserRols;

	public SysUser() {
	}

	public String getUserCode() {
		return this.userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	public Date getEmploymentBegin() {
		return this.employmentBegin;
	}

	public void setEmploymentBegin(Date employmentBegin) {
		this.employmentBegin = employmentBegin;
	}

	public Date getEmploymentEnd() {
		return this.employmentEnd;
	}

	public void setEmploymentEnd(Date empolymentEnd) {
		this.employmentEnd = empolymentEnd;
	}

	public Date getEntryDate() {
		return this.entryDate;
	}

	public void setEntryDate(Date entryDate) {
		this.entryDate = entryDate;
	}

	public String getEntryUser() {
		return this.entryUser;
	}

	public void setEntryUser(String entryUser) {
		this.entryUser = entryUser;
	}

	public String getUserAddress() {
		return this.userAddress;
	}

	public void setUserAddress(String userAddress) {
		this.userAddress = userAddress;
	}

	public Date getUserBirthDate() {
		return this.userBirthDate;
	}

	public void setUserBirthDate(Date userBirthDate) {
		this.userBirthDate = userBirthDate;
	}

	public String getUserEmail() {
		return this.userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public String getUserLastname() {
		return this.userLastname;
	}

	public void setUserLastname(String userLastname) {
		this.userLastname = userLastname;
	}

	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserPassword() {
		return this.userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public String getUserPhone1() {
		return this.userPhone1;
	}

	public void setUserPhone1(String userPhone1) {
		this.userPhone1 = userPhone1;
	}

	public String getUserPhone2() {
		return this.userPhone2;
	}

	public void setUserPhone2(String userPhone2) {
		this.userPhone2 = userPhone2;
	}

	public List<SysUserRol> getSysUserRols() {
		return this.sysUserRols;
	}

	public void setSysUserRols(List<SysUserRol> sysUserRols) {
		this.sysUserRols = sysUserRols;
	}

	public SysUserRol addSysUserRol(SysUserRol sysUserRol) {
		getSysUserRols().add(sysUserRol);
		sysUserRol.setSysUser(this);

		return sysUserRol;
	}

	public SysUserRol removeSysUserRol(SysUserRol sysUserRol) {
		getSysUserRols().remove(sysUserRol);
		sysUserRol.setSysUser(null);

		return sysUserRol;
	}

}
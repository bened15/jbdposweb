package com.jbd.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the sys_user_rol_log database table.
 *
 */
@Entity
@Table(name="sys_user_rol_log")
@NamedQuery(name="SysUserRolLog.findAll", query="SELECT s FROM SysUserRolLog s")
public class SysUserRolLog implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="USER_ROL_LOG_ID")
	private int userRolLogId;

	@Temporal(TemporalType.DATE)
	@Column(name="ENTRY_DATE")
	private Date entryDate;

	@Column(name="ENTRY_USER")
	private String entryUser;

	@Column(name="ROL_CODE")
	private String rolCode;

	@Column(name="USER_CODE")
	private String userCode;

	//bi-directional many-to-one association to SysUserRol
	@ManyToOne
	@JoinColumn(name="USER_ROL_ID")
	private SysUserRol sysUserRol;

	public SysUserRolLog() {
	}

	public int getUserRolLogId() {
		return this.userRolLogId;
	}

	public void setUserRolLogId(int userRolLogId) {
		this.userRolLogId = userRolLogId;
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

	public String getRolCode() {
		return this.rolCode;
	}

	public void setRolCode(String rolCode) {
		this.rolCode = rolCode;
	}

	public String getUserCode() {
		return this.userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	public SysUserRol getSysUserRol() {
		return this.sysUserRol;
	}

	public void setSysUserRol(SysUserRol sysUserRol) {
		this.sysUserRol = sysUserRol;
	}

}
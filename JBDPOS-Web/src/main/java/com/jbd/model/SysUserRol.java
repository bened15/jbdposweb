package com.jbd.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the sys_user_rol database table.
 *
 */
@Entity
@Table(name="sys_user_rol")
@NamedQuery(name="SysUserRol.findAll", query="SELECT s FROM SysUserRol s")
public class SysUserRol implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="USER_ROL_ID")
	private int userRolId;

	@Temporal(TemporalType.DATE)
	@Column(name="ENTRY_DATE")
	private Date entryDate;

	@Column(name="ENTRY_USER")
	private String entryUser;

	//bi-directional many-to-one association to SysRole
	@ManyToOne
	@JoinColumn(name="ROL_CODE")
	private SysRole sysRole;

	//bi-directional many-to-one association to SysUser
	@ManyToOne
	@JoinColumn(name="USER_CODE")
	private SysUser sysUser;

	//bi-directional many-to-one association to SysUserRolLog
	@OneToMany(mappedBy="sysUserRol")
	private List<SysUserRolLog> sysUserRolLogs;

	public SysUserRol() {
	}

	public int getUserRolId() {
		return this.userRolId;
	}

	public void setUserRolId(int userRolId) {
		this.userRolId = userRolId;
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

	public SysRole getSysRole() {
		return this.sysRole;
	}

	public void setSysRole(SysRole sysRole) {
		this.sysRole = sysRole;
	}

	public SysUser getSysUser() {
		return this.sysUser;
	}

	public void setSysUser(SysUser sysUser) {
		this.sysUser = sysUser;
	}

	public List<SysUserRolLog> getSysUserRolLogs() {
		return this.sysUserRolLogs;
	}

	public void setSysUserRolLogs(List<SysUserRolLog> sysUserRolLogs) {
		this.sysUserRolLogs = sysUserRolLogs;
	}

	public SysUserRolLog addSysUserRolLog(SysUserRolLog sysUserRolLog) {
		getSysUserRolLogs().add(sysUserRolLog);
		sysUserRolLog.setSysUserRol(this);

		return sysUserRolLog;
	}

	public SysUserRolLog removeSysUserRolLog(SysUserRolLog sysUserRolLog) {
		getSysUserRolLogs().remove(sysUserRolLog);
		sysUserRolLog.setSysUserRol(null);

		return sysUserRolLog;
	}

}
package com.jbd.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the sys_role database table.
 *
 */
@Entity
@Table(name="sys_role")
@NamedQuery(name="SysRole.findAll", query="SELECT s FROM SysRole s")
public class SysRole implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="ROL_CODE")
	private String rolCode;

	@Temporal(TemporalType.DATE)
	@Column(name="ENTRY_DATE")
	private Date entryDate;

	@Column(name="ENTRY_USER")
	private String entryUser;

	@Column(name="ROL_DESCRIPTION")
	private String rolDescription;

	@Column(name="ROL_NAME")
	private String rolName;

	//bi-directional many-to-one association to SysUserRol
	@OneToMany(mappedBy="sysRole")
	private List<SysUserRol> sysUserRols;

	public SysRole() {
	}

	public String getRolCode() {
		return this.rolCode;
	}

	public void setRolCode(String rolCode) {
		this.rolCode = rolCode;
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

	public String getRolDescription() {
		return this.rolDescription;
	}

	public void setRolDescription(String rolDescription) {
		this.rolDescription = rolDescription;
	}

	public String getRolName() {
		return this.rolName;
	}

	public void setRolName(String rolName) {
		this.rolName = rolName;
	}

	public List<SysUserRol> getSysUserRols() {
		return this.sysUserRols;
	}

	public void setSysUserRols(List<SysUserRol> sysUserRols) {
		this.sysUserRols = sysUserRols;
	}

	public SysUserRol addSysUserRol(SysUserRol sysUserRol) {
		getSysUserRols().add(sysUserRol);
		sysUserRol.setSysRole(this);

		return sysUserRol;
	}

	public SysUserRol removeSysUserRol(SysUserRol sysUserRol) {
		getSysUserRols().remove(sysUserRol);
		sysUserRol.setSysRole(null);

		return sysUserRol;
	}
	
	@Override
	public String toString(){
		return this.rolCode;
	}

}
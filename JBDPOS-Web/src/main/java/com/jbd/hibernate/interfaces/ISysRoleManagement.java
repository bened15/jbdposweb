package com.jbd.hibernate.interfaces;

import java.util.List;

import com.jbd.model.SysRole;

public interface ISysRoleManagement {

	public SysRole insertSysRole(SysRole o);
	public SysRole updateSysRole(SysRole o);
	public void deleteSysRole(SysRole o);
	public List<SysRole> findAll();
	public SysRole findSysRole(String oId);
	public List<SysRole> findRoleByExample(String roleCode, String roleName);

}

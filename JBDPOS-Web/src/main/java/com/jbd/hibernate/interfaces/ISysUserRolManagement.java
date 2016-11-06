package com.jbd.hibernate.interfaces;

import com.jbd.model.SysUserRol;

public interface ISysUserRolManagement {

	public SysUserRol insertSysUserRol(SysUserRol o);
	public SysUserRol updateSysUserRol(SysUserRol o);
	public void deleteSysUserRol(SysUserRol o);
	public SysUserRol findSysUserRol(Integer oId);
	boolean findWaitress(String uName);
	SysUserRol findSysUserRol(String uName, String uPass);

}

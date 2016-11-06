package com.jbd.hibernate.interfaces;

import com.jbd.model.SysUserRolLog;

public interface ISysUserRolLogManagement {

	public SysUserRolLog insertSysUserRolLog(SysUserRolLog o);
	public void updateSysUserRolLog(SysUserRolLog o);
	public void deleteSysUserRolLog(SysUserRolLog o);
	public SysUserRolLog findSysUserRolLog(Integer oId);

}

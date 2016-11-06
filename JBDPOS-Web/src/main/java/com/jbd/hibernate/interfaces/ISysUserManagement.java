package com.jbd.hibernate.interfaces;

import java.util.List;

import com.jbd.model.SysUser;

public interface ISysUserManagement {

	public SysUser insertSysUser(SysUser o);
	public SysUser updateSysUser(SysUser o);
	public boolean deleteSysUser(SysUser o);
	public SysUser findSysUser(String oId);
	public List<SysUser> findAll();
	public List<SysUser> findByUserExample(String name, String lastname, String userCode);

}

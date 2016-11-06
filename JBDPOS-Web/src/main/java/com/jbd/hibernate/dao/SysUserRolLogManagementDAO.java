package com.jbd.hibernate.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.transaction.annotation.Transactional;

import com.jbd.hibernate.interfaces.ISysUserRolLogManagement;
import com.jbd.model.SysUserRolLog;


public class SysUserRolLogManagementDAO implements ISysUserRolLogManagement {


	@PersistenceContext
	public EntityManager em;

	public SysUserRolLogManagementDAO() {
		// TODO Auto-generated constructor stub
	}

	@Transactional
	@Override
	public SysUserRolLog insertSysUserRolLog(SysUserRolLog o) {
		try {
			em.persist(o);
			return o;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

	@Override
	public void updateSysUserRolLog(SysUserRolLog o) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteSysUserRolLog(SysUserRolLog o) {
		// TODO Auto-generated method stub

	}

	@Override
	public SysUserRolLog findSysUserRolLog(Integer oId) {
		return null;
		// TODO Auto-generated method stub

	}

}

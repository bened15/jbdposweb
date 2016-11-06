package com.jbd.hibernate.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.transaction.annotation.Transactional;

import com.jbd.hibernate.interfaces.ICtgProductLogManagement;
import com.jbd.model.CtgProductLog;


public class CtgProductLogManagementDAO implements ICtgProductLogManagement {


	@PersistenceContext
	public EntityManager em;

	public CtgProductLogManagementDAO() {
		// TODO Auto-generated constructor stub
	}

	@Transactional
	@Override
	public CtgProductLog insertCtgProductLog(CtgProductLog o) {
		try {
			em.persist(o);
			return o;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

	@Override
	public void updateCtgProductLog(CtgProductLog o) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteCtgProductLog(CtgProductLog o) {
		// TODO Auto-generated method stub

	}

	@Override
	public CtgProductLog findCtgProductLog(Integer oId) {
		return null;
		// TODO Auto-generated method stub

	}

}

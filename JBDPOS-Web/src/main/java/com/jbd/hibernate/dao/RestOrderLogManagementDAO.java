package com.jbd.hibernate.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.transaction.annotation.Transactional;

import com.jbd.hibernate.interfaces.IRestOrderLogManagement;
import com.jbd.model.RestOrderLog;


public class RestOrderLogManagementDAO implements IRestOrderLogManagement {


	@PersistenceContext
	public EntityManager em;

	public RestOrderLogManagementDAO() {
		// TODO Auto-generated constructor stub
	}

	@Transactional
	@Override
	public RestOrderLog insertRestOrderLog(RestOrderLog o) {
		try {
			em.persist(o);
			return o;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

	@Override
	public void updateRestOrderLog(RestOrderLog o) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteRestOrderLog(RestOrderLog o) {
		// TODO Auto-generated method stub

	}

	@Override
	public RestOrderLog findRestOrderLog(Integer oId) {
		return null;
		// TODO Auto-generated method stub

	}

}

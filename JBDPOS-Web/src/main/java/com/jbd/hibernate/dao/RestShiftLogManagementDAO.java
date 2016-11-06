package com.jbd.hibernate.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.transaction.annotation.Transactional;

import com.jbd.hibernate.interfaces.IRestShiftLogManagement;
import com.jbd.model.RestShiftLog;


public class RestShiftLogManagementDAO implements IRestShiftLogManagement {


	@PersistenceContext
	public EntityManager em;

	public RestShiftLogManagementDAO() {
		// TODO Auto-generated constructor stub
	}

	@Transactional
	@Override
	public RestShiftLog insertRestShiftLog(RestShiftLog o) {
		try {
			em.persist(o);
			return o;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

	@Override
	public void updateRestShiftLog(RestShiftLog o) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteRestShiftLog(RestShiftLog o) {
		// TODO Auto-generated method stub

	}

	@Override
	public RestShiftLog findRestShiftLog(Integer oId) {
		return null;
		// TODO Auto-generated method stub

	}

}

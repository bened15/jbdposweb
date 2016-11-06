package com.jbd.hibernate.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.transaction.annotation.Transactional;

import com.jbd.hibernate.interfaces.IRestShiftDetailManagement;
import com.jbd.model.RestShiftDetail;


public class RestShiftDetailManagementDAO implements IRestShiftDetailManagement {


	@PersistenceContext
	public EntityManager em;

	public RestShiftDetailManagementDAO() {
		// TODO Auto-generated constructor stub
	}

	@Transactional
	@Override
	public RestShiftDetail insertRestShiftDetail(RestShiftDetail o) {
		try {
			em.persist(o);
			return o;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

	@Override
	public void updateRestShiftDetail(RestShiftDetail o) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteRestShiftDetail(RestShiftDetail o) {
		// TODO Auto-generated method stub

	}

	@Override
	public RestShiftDetail findRestShiftDetail(Integer oId) {
		return null;
		// TODO Auto-generated method stub

	}

}

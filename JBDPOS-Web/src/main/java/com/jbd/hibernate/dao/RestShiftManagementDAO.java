package com.jbd.hibernate.dao;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;
import javax.persistence.TypedQuery;

import org.springframework.transaction.annotation.Transactional;

import com.jbd.hibernate.interfaces.IRestShiftManagement;
import com.jbd.model.RestShift;

public class RestShiftManagementDAO implements IRestShiftManagement {

	@PersistenceContext
	public EntityManager em;

	public RestShiftManagementDAO() {
		// TODO Auto-generated constructor stub
	}

	@Transactional
	@Override
	public RestShift insertRestShift(RestShift o) {
		try {
			em.persist(o);
			return o;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

	@Override
	public void updateRestShift(RestShift o) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteRestShift(RestShift o) {
		// TODO Auto-generated method stub

	}

	@Override
	public RestShift findRestShift(Integer oId) {
		return null;
		// TODO Auto-generated method stub

	}

	@Override
	public RestShift alreadyExistShift() {
		try {
			TypedQuery<RestShift> q = em.createQuery("select s from RestShift s where s.closingDatetime is null",
					RestShift.class);

			RestShift rs = q.getSingleResult();
			System.out.println(rs.getInitialMoney() + rs.getOpenedBy());
			return rs;
		} catch (Exception e) {
//			e.printStackTrace();
			return null;
		}

	}

	@Transactional
	@Override
	public void closeShift(int rShift, String closedBy) {
		StoredProcedureQuery storedProcedure = em.createStoredProcedureQuery("CLOSE_SHIFT");
		// set parameters
		storedProcedure.registerStoredProcedureParameter("PRM_SHIFT_ID", Integer.class, ParameterMode.IN);
		storedProcedure.registerStoredProcedureParameter("PRM_USER", String.class, ParameterMode.IN);
		storedProcedure.setParameter("PRM_SHIFT_ID", rShift);
		storedProcedure.setParameter("PRM_USER", closedBy);
		// execute SP
		storedProcedure.execute();
		// si retornara algun resultado
		// String res = (String) storedProcedure.getOutputParameterValue("res");

	}

}

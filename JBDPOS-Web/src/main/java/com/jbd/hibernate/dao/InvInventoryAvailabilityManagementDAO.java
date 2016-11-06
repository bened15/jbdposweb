package com.jbd.hibernate.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.transaction.annotation.Transactional;

import com.jbd.hibernate.interfaces.IInvInventoryAvailabilityManagement;
import com.jbd.model.InvInventoryAvailability;


public class InvInventoryAvailabilityManagementDAO implements IInvInventoryAvailabilityManagement {


	@PersistenceContext
	public EntityManager em;

	public InvInventoryAvailabilityManagementDAO() {
		// TODO Auto-generated constructor stub
	}

	@Transactional
	@Override
	public InvInventoryAvailability insertInvInventoryAvailability(InvInventoryAvailability o) {
		try {
			em.persist(o);
			return o;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

	@Override
	public void updateInvInventoryAvailability(InvInventoryAvailability o) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteInvInventoryAvailability(InvInventoryAvailability o) {
		// TODO Auto-generated method stub

	}

	@Override
	public InvInventoryAvailability findInvInventoryAvailability(Integer oId) {
		return null;
		// TODO Auto-generated method stub

	}

}

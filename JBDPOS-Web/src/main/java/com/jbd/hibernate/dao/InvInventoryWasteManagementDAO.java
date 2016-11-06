package com.jbd.hibernate.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.transaction.annotation.Transactional;

import com.jbd.hibernate.interfaces.IInvInventoryWasteManagement;
import com.jbd.model.InvInventoryWaste;


public class InvInventoryWasteManagementDAO implements IInvInventoryWasteManagement {


	@PersistenceContext
	public EntityManager em;

	public InvInventoryWasteManagementDAO() {
		// TODO Auto-generated constructor stub
	}

	@Transactional
	@Override
	public InvInventoryWaste insertInvInventoryWaste(InvInventoryWaste o) {
		try {
			em.persist(o);
			return o;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

	@Transactional
	@Override
	public InvInventoryWaste updateInvInventoryWaste(InvInventoryWaste o) {
		// TODO Auto-generated method stub
		try {
			em.merge(o);
			return o;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

	@Override
	public void deleteInvInventoryWaste(InvInventoryWaste o) {
		// TODO Auto-generated method stub

	}

	@Override
	public InvInventoryWaste findInvInventoryWaste(Integer oId) {
		return null;
		// TODO Auto-generated method stub

	}

}

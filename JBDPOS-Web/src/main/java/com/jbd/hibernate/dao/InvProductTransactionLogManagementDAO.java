package com.jbd.hibernate.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.transaction.annotation.Transactional;

import com.jbd.hibernate.interfaces.IInvProductTransactionLogManagement;
import com.jbd.model.InvProductTransactionLog;


public class InvProductTransactionLogManagementDAO implements IInvProductTransactionLogManagement {


	@PersistenceContext
	public EntityManager em;

	public InvProductTransactionLogManagementDAO() {
		// TODO Auto-generated constructor stub
	}

	@Transactional
	@Override
	public InvProductTransactionLog insertInvProductTransactionLog(InvProductTransactionLog o) {
		try {
			em.persist(o);
			return o;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

	@Override
	public void updateInvProductTransactionLog(InvProductTransactionLog o) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteInvProductTransactionLog(InvProductTransactionLog o) {
		// TODO Auto-generated method stub

	}

	@Override
	public InvProductTransactionLog findInvProductTransactionLog(Integer oId) {
		return null;
		// TODO Auto-generated method stub

	}

}

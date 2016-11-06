package com.jbd.hibernate.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.transaction.annotation.Transactional;

import com.jbd.hibernate.interfaces.ICtgTransactionTypeManagement;
import com.jbd.model.CtgTransactionType;
import com.jbd.model.CtgTransactionType;


public class CtgTransactionTypeManagementDAO implements ICtgTransactionTypeManagement {


	@PersistenceContext
	public EntityManager em;

	public CtgTransactionTypeManagementDAO() {
		// TODO Auto-generated constructor stub
	}

	@Transactional
	@Override
	public CtgTransactionType insertCtgTransactionType(CtgTransactionType o) {
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
	public CtgTransactionType updateCtgTransactionType(CtgTransactionType o) {
		try {
			em.merge(o);
			return o;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

	@Override
	public void deleteCtgTransactionType(CtgTransactionType o) {
		// TODO Auto-generated method stub

	}

	@Override
	public CtgTransactionType findCtgTransactionType(Integer oId) {
		try {
			CtgTransactionType user;
				TypedQuery<CtgTransactionType> tq = em.createQuery("select o from CtgTransactionType o where o.transactionTypeId=:prmTransactionTypeId",
						CtgTransactionType.class);
				tq.setParameter("prmTransactionTypeId", oId);

				user = tq.getSingleResult();
				return user;

			} catch (Exception e) {
				e.printStackTrace();
				return null;

			}
		// TODO Auto-generated method stub

	}

	@Override
	public List<CtgTransactionType> findbyAdittion(Integer oId) {
		// TODO Auto-generated method stub
	try {
			
			List<CtgTransactionType> transactionList;
			TypedQuery<CtgTransactionType> tq = em.createQuery("select o from CtgTransactionType o where o.transactionAddition=:prmTransactionAddition",
					CtgTransactionType.class);
			tq.setParameter("prmTransactionAddition", oId);
			
			transactionList = tq.getResultList();
			return transactionList;

		} catch (Exception e) {
			e.printStackTrace();
			return null;

		}
	}
  

}

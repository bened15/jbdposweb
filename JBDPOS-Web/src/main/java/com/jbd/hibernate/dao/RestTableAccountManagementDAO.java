package com.jbd.hibernate.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.springframework.transaction.annotation.Transactional;

import com.jbd.hibernate.interfaces.IRestTableAccountManagement;
import com.jbd.model.RestTable;
import com.jbd.model.RestTableAccount;

public class RestTableAccountManagementDAO implements IRestTableAccountManagement {

	@PersistenceContext
	public EntityManager em;

	public RestTableAccountManagementDAO() {
		// TODO Auto-generated constructor stub
	}

	@Transactional
	@Override
	public RestTableAccount insertRestTableAccount(RestTableAccount o) {
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
	public void updateRestTableAccount(RestTableAccount o) {
		try {
			em.merge(o);
			em.flush();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Transactional
	@Override
	public void deleteRestTableAccount(RestTableAccount o) {
		try {
			RestTableAccount r = em.find(RestTableAccount.class, o.getTableAccountId());
			em.remove(r);
			// em.flush();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public RestTableAccount findRestTableAccount(Integer oId) {
		return null;
		// TODO Auto-generated method stub

	}

	@Override
	public boolean isRestTableAccountOpen(RestTable table) {
		try {

			TypedQuery<RestTableAccount> q = em.createQuery(
					"select t from RestTableAccount t where t.restTable=:table and t.closedDatetime is null",
					RestTableAccount.class);
			q.setParameter("table", table);
			int i = q.getResultList().size();
			if (i == 0) {

				return false;

			} else {

				return true;
			}

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

	}

	@Override
	public RestTableAccount findRestTableAccountOpen(RestTable table) {
		try {

			Query q = em.createQuery(
					"select t from RestTableAccount t where t.restTable=:table and t.closedDatetime is null",
					RestTableAccount.class);
			q.setParameter("table", table);
			RestTableAccount taccount = (RestTableAccount) q.getSingleResult();

			return taccount;

		} catch (Exception e) {
			e.printStackTrace();
			RestTableAccount taccount = null;
			return taccount;
		}

	}

}

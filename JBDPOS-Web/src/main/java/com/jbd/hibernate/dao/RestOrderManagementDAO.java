package com.jbd.hibernate.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.transaction.annotation.Transactional;

import com.jbd.hibernate.interfaces.IRestOrderManagement;
import com.jbd.model.RestOrder;
import com.jbd.model.RestTableAccount;

public class RestOrderManagementDAO implements IRestOrderManagement {

	@PersistenceContext
	public EntityManager em;

	public RestOrderManagementDAO() {
		// TODO Auto-generated constructor stub
	}

	@Transactional
	@Override
	public RestOrder insertRestOrder(RestOrder o) {
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
	public void updateRestOrder(RestOrder o) {
		try {
			em.merge(o);
			System.out.println("Actualizado");
		} catch (Exception e) {
			e.printStackTrace();

		}

	}

	@Transactional
	@Override
	public void deleteRestOrder(RestOrder o) {
		try {

			RestOrder or = em.find(RestOrder.class, o.getOrderId());
			em.remove(or);
			em.flush();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public RestOrder findRestOrder(Integer oId) {
		return null;

	}

	@Override
	public List<RestOrder> findAllRestOrdersFromTableAccount(RestTableAccount account) {
		try {
			TypedQuery<RestOrder> tq = em.createQuery(
					"select o from RestOrder o where o.restTableAccount=:account and o.orderStatus =:status ",
					RestOrder.class);
			tq.setParameter("account", account);
			tq.setParameter("status", "COCINA");
			List<RestOrder> ordenes = tq.getResultList();
			return ordenes;

		} catch (Exception e) {
			e.printStackTrace();
			List<RestOrder> ordenes = null;
			return ordenes;

		}

	}

}

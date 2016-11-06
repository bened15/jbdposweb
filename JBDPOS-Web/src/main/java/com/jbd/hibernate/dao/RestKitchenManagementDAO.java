package com.jbd.hibernate.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.transaction.annotation.Transactional;

import com.jbd.hibernate.interfaces.IRestKitchenManagement;
import com.jbd.model.RestKitchen;


public class RestKitchenManagementDAO implements IRestKitchenManagement {

	@PersistenceContext
	public EntityManager em;

	public RestKitchenManagementDAO() {
		// TODO Auto-generated constructor stub
	}

	@Transactional
	@Override
	public RestKitchen insertRestKitchen(RestKitchen o) {
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
	public RestKitchen updateRestKitchen(RestKitchen o) {
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
	public void deleteRestKitchen(RestKitchen o) {
		// TODO Auto-generated method stub

	}

	@Override
	public RestKitchen findRestKitchen(Integer oId) {
		// TODO Auto-generated method stub
		try {
			RestKitchen menuType;
				TypedQuery<RestKitchen> tq = em.createQuery("select o from RestKitchen o where o.kitchenId=:prmKitchenId",
						RestKitchen.class);
				tq.setParameter("prmKitchenId", oId);

				menuType = tq.getSingleResult();
				return menuType;

			} catch (Exception e) {
				e.printStackTrace();
				return null;

			}
	}

	

	@Override
	public List<RestKitchen> findAll() {
		try {

			TypedQuery<RestKitchen> tq = em.createNamedQuery("RestKitchen.findAll", RestKitchen.class);

			List<RestKitchen> kitchen = tq.getResultList();

			return kitchen;
		} catch (Exception e) {
			e.printStackTrace();
			return null;

		}
	}

	@Override
	public List<RestKitchen> findMenyTypeByExample(String name) {
		try {

			TypedQuery<RestKitchen> tq = em.createQuery(
					"select p from RestKitchen p where UPPER(p.kitchenName) like '%' || :prmKitchenName || '%'",
					RestKitchen.class);
			tq.setParameter("prmKitchenName", name.toUpperCase());


			List<RestKitchen> kitchen = tq.getResultList();

			return kitchen;
		} catch (Exception e) {
			e.printStackTrace();
			return null;

		}
	}


}

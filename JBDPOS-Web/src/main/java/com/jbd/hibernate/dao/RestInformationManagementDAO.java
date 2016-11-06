package com.jbd.hibernate.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.transaction.annotation.Transactional;

import com.jbd.hibernate.interfaces.IRestInformationManagement;
import com.jbd.model.RestInformation;
import com.jbd.model.RestInformation;


public class RestInformationManagementDAO implements IRestInformationManagement {


	@PersistenceContext
	public EntityManager em;

	public RestInformationManagementDAO() {
		// TODO Auto-generated constructor stub
	}

	@Transactional
	@Override
	public RestInformation insertRestInformation(RestInformation o) {
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
	public RestInformation updateRestInformation(RestInformation o) {
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
	public void deleteRestInformation(RestInformation o) {
		// TODO Auto-generated method stub

	}

	@Override
	public RestInformation findRestInformation(Integer oId) {
		
		try {
			RestInformation restaurant;
				TypedQuery<RestInformation> tq = em.createQuery("select o from RestInformation o where o.informationId=:prmRestaurantId",
						RestInformation.class);
				tq.setParameter("prmRestaurantId", oId);

				restaurant = tq.getSingleResult();
				return restaurant;

			} catch (Exception e) {
				e.printStackTrace();
				return null;

			}
	}

	@Override
	public RestInformation findFirst() {
		RestInformation restaurant = new RestInformation();
		
		try {
			List<RestInformation> restaurants;
				TypedQuery<RestInformation> tq = em.createQuery("select o from RestInformation o ",
						RestInformation.class);
				
				restaurants = tq.getResultList();
				if(restaurants.size()>0){
					restaurant = restaurants.get(0);
				}
				
				return restaurant;

			} catch (Exception e) {
				e.printStackTrace();
				return restaurant;

			}
	}

}

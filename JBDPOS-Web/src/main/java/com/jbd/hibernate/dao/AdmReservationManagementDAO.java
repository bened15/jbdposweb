package com.jbd.hibernate.dao;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.transaction.annotation.Transactional;

import com.jbd.hibernate.interfaces.IAdmReservationManagement;
import com.jbd.model.AdmReservation;
import com.jbd.model.AdmReservation;
import com.jbd.model.AdmReservation;
import com.jbd.model.AdmReservation;


public class AdmReservationManagementDAO implements IAdmReservationManagement {


	@PersistenceContext
	public EntityManager em;

	public AdmReservationManagementDAO() {
		// TODO Auto-generated constructor stub
	}

	@Transactional
	@Override
	public AdmReservation insertAdmReservation(AdmReservation o) {
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
	public AdmReservation updateAdmReservation(AdmReservation o) {
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
	public void deleteAdmReservation(AdmReservation o) {
		// TODO Auto-generated method stub

	}

	@Override
	public AdmReservation findAdmReservation(Integer oId) {
		try {
			AdmReservation reservation;
				TypedQuery<AdmReservation> tq = em.createQuery("select o from AdmReservation o where o.reservationId=:prmReservationId",
						AdmReservation.class);
				tq.setParameter("prmReservationId", oId);

				reservation = tq.getSingleResult();
				return reservation;

			} catch (Exception e) {
				e.printStackTrace();
				return null;

			}		// TODO Auto-generated method stub

	}

	@Override
	public List<AdmReservation> findAll() {
		try {
			
			List<AdmReservation> reservationList;
			TypedQuery<AdmReservation> tq = em.createQuery("select o from AdmReservation o ",
					AdmReservation.class);
			reservationList = tq.getResultList();
			return reservationList;

		} catch (Exception e) {
			e.printStackTrace();
			return null;

		}
	}

	@Override
	public List<AdmReservation> findByReservationExample(String customerName, String customerLastName, Date reservation) {
		// TODO Auto-generated method stub
				StringBuilder sqlQuery = new StringBuilder(); 
				Boolean isFirst = true;
				Boolean useReservationDate = false;
				Boolean useContactLastName = false;
				Boolean useContactName = false;
				sqlQuery.append("select t from AdmReservation t where ");
				try {
					List<AdmReservation> reservations ;
					if( (customerName == null || customerName.isEmpty()) && (customerLastName == null || customerLastName.isEmpty()) && reservation ==null){
						reservations = findAll();
					}else{
						if( reservation != null ){
							useReservationDate = true;
							if(isFirst){
								sqlQuery.append(" reservationDate = :prmReservationDate ");
								isFirst= false;					
								}
						}
						if( (customerName != null && !customerName.isEmpty())){
							useContactName = true;
							if(isFirst){
								sqlQuery.append(" upper(t.admCustomer.customerName) like '%' ||:prmCustomerName || '%'  ");
								isFirst= false;					
							}else{
								sqlQuery.append(" and upper(t.admCustomer.customerName) like '%' ||:prmCustomerName || '%'  ");						
							}
						}
						
						
						if( (customerLastName != null && !customerLastName.isEmpty())){
							useContactLastName = true;
							if(isFirst){
								sqlQuery.append(" upper(t.admCustomer.customerLastname) like '%' ||:prmCustomerLastName || '%' ");
								isFirst= false;					
								}else{
									sqlQuery.append(" and upper(t.admCustomer.customerLastname) like '%' ||:prmCustomerLastName || '%'  ");						
								}
						}
							TypedQuery<AdmReservation> tq = em.createQuery(sqlQuery.toString(),
									AdmReservation.class);
							if(useReservationDate){
								tq.setParameter("prmReservationDate", reservation);
								
							}
							if(useContactName){
								tq.setParameter("prmCustomerName", customerName.toUpperCase());
								
							}
							if(useContactLastName){
								tq.setParameter("prmCustomerLastName", customerLastName.toUpperCase());
								
							}
							
							reservations = tq.getResultList();
					}

					return reservations;
				} catch (Exception e) {
					e.printStackTrace();
					return null;

				}
	}
}

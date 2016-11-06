package com.jbd.hibernate.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.transaction.annotation.Transactional;

import com.jbd.hibernate.interfaces.IAdmCustomerManagement;
import com.jbd.model.AdmCustomer;


public class AdmCustomerManagementDAO implements IAdmCustomerManagement {


	@PersistenceContext
	public EntityManager em;

	public AdmCustomerManagementDAO() {
		// TODO Auto-generated constructor stub
	}

	@Transactional
	@Override
	public AdmCustomer insertAdmCustomer(AdmCustomer o) {
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
	public AdmCustomer updateAdmCustomer(AdmCustomer o) {
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
	public void deleteAdmCustomer(AdmCustomer o) {
		// TODO Auto-generated method stub

	}

	@Override
	public AdmCustomer findAdmCustomer(Integer oId) {
		// TODO Auto-generated method stub
		try {
			AdmCustomer supplier;
				TypedQuery<AdmCustomer> tq = em.createQuery("select o from AdmCustomer o where o.customerId=:prmCustomerId",
						AdmCustomer.class);
				tq.setParameter("prmCustomerId", oId);

				supplier = tq.getSingleResult();
				return supplier;

			} catch (Exception e) {
				e.printStackTrace();
				return null;

			}
	}
	@Override
	public List<AdmCustomer> findAll() {
		try {
			
			List<AdmCustomer> customerList;
			TypedQuery<AdmCustomer> tq = em.createQuery("select o from AdmCustomer o ",
					AdmCustomer.class);
			customerList = tq.getResultList();
			return customerList;

		} catch (Exception e) {
			e.printStackTrace();
			return null;

		}
	}
	
	@Override
	public List<AdmCustomer> findByCustomerExample(String customerName, String customerLastName) {
		// TODO Auto-generated method stub
		StringBuilder sqlQuery = new StringBuilder(); 
		Boolean isFirst = true;
		Boolean useContactLastName = false;
		Boolean useContactName = false;
		sqlQuery.append("select t from AdmCustomer t where ");
		try {
			List<AdmCustomer> customers ;
			if( (customerName == null || customerName.isEmpty()) && (customerLastName == null || customerLastName.isEmpty()) ){
				customers = findAll();
			}else{
				if( (customerName != null && !customerName.isEmpty())){
					useContactName = true;
					if(isFirst){
						sqlQuery.append(" upper(t.customerName) like '%' ||:prmCustomerName || '%'  ");
						isFirst= false;					
						}
				}
				
				
				if( (customerLastName != null && !customerLastName.isEmpty())){
					useContactLastName = true;
					if(isFirst){
						sqlQuery.append(" upper(t.customerLastname) like '%' ||:prmCustomerLastName || '%' ");
						isFirst= false;					
						}else{
							sqlQuery.append(" and upper(t.customerLastname) like '%' ||:prmCustomerLastName || '%'  ");						
						}
				}
					TypedQuery<AdmCustomer> tq = em.createQuery(sqlQuery.toString(),
							AdmCustomer.class);
					if(useContactName){
						tq.setParameter("prmCustomerName", customerName.toUpperCase());
						
					}
					if(useContactLastName){
						tq.setParameter("prmCustomerLastName", customerLastName.toUpperCase());
						
					}
					
					customers = tq.getResultList();
			}

			return customers;
		} catch (Exception e) {
			e.printStackTrace();
			return null;

		}

	}

}

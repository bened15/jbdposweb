package com.jbd.hibernate.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.transaction.annotation.Transactional;

import com.jbd.hibernate.interfaces.ICtgSupplierManagement;
import com.jbd.model.CtgSupplier;
import com.jbd.model.CtgSupplier;


public class CtgSupplierManagementDAO implements ICtgSupplierManagement {


	@PersistenceContext
	public EntityManager em;

	public CtgSupplierManagementDAO() {
		// TODO Auto-generated constructor stub
	}

	@Transactional
	@Override
	public CtgSupplier insertCtgSupplier(CtgSupplier o) {
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
	public CtgSupplier updateCtgSupplier(CtgSupplier o) {
		try {
			em.merge(o);
			return o;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteCtgSupplier(CtgSupplier o) {
		// TODO Auto-generated method stub

	}

	@Override
	public CtgSupplier findCtgSupplier(Integer oId) {
		try {
			CtgSupplier supplier;
				TypedQuery<CtgSupplier> tq = em.createQuery("select o from CtgSupplier o where o.supplierId=:prmSupplierId",
						CtgSupplier.class);
				tq.setParameter("prmSupplierId", oId);

				supplier = tq.getSingleResult();
				return supplier;

			} catch (Exception e) {
				e.printStackTrace();
				return null;

			}
		// TODO Auto-generated method stub

	}

	@Override
	public List<CtgSupplier> findAll() {
		try {
			
			List<CtgSupplier> supplierList;
			TypedQuery<CtgSupplier> tq = em.createQuery("select o from CtgSupplier o ",
					CtgSupplier.class);
			supplierList = tq.getResultList();
			return supplierList;

		} catch (Exception e) {
			e.printStackTrace();
			return null;

		}
	}
	
	@Override
	public List<CtgSupplier> findBySupplierExample(String supplierName, String contactName, String contactLastName) {
		// TODO Auto-generated method stub
		StringBuilder sqlQuery = new StringBuilder(); 
		Boolean isFirst = true;
		Boolean useSupplierName = false;
		Boolean useContactLastName = false;
		Boolean userContactName = false;
		sqlQuery.append("select t from CtgSupplier t where ");
		try {
			List<CtgSupplier> suppliers ;
			if( (supplierName == null || supplierName.isEmpty()) && (contactName == null || contactName.isEmpty()) && (contactLastName == null || contactLastName.isEmpty()) ){
				suppliers = findAll();
			}else{
				if( (supplierName != null && !supplierName.isEmpty())){
					useSupplierName = true;
					if(isFirst){
						sqlQuery.append(" upper(t.supplierName) like '%' ||:prmSupplierName || '%'  ");
						isFirst= false;					
						}
				}
				if( (contactName != null && !contactName.isEmpty())){
					userContactName = true;
					if(isFirst){
						sqlQuery.append(" upper(t.contactName) like '%' ||:prmContactName || '%' ");
						isFirst= false;					
						}else{
							sqlQuery.append(" and upper(t.contactName) like '%' ||:prmContactName || '%'  ");						
						}

				}
				if( (contactLastName != null && !contactLastName.isEmpty())){
					useContactLastName = true;
					if(isFirst){
						sqlQuery.append(" upper(t.contactLastname) like '%' ||:prmContactLastname || '%' ");
						isFirst= false;					
						}else{
							sqlQuery.append(" and upper(t.contactLastname) like '%' ||:prmContactLastname || '%'  ");						
						}
				}
					TypedQuery<CtgSupplier> tq = em.createQuery(sqlQuery.toString(),
							CtgSupplier.class);
					if(useSupplierName){
						tq.setParameter("prmSupplierName", supplierName.toUpperCase());
						
					}
					if(useContactLastName){
						tq.setParameter("prmContactLastname", contactLastName.toUpperCase());
						
					}
					if(userContactName){
						tq.setParameter("prmContactName", contactName.toUpperCase());						
					}
					suppliers = tq.getResultList();
			}

			return suppliers;
		} catch (Exception e) {
			e.printStackTrace();
			return null;

		}

	}
	

}

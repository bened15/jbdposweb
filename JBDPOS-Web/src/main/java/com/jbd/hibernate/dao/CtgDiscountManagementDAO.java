package com.jbd.hibernate.dao;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.springframework.transaction.annotation.Transactional;

import com.jbd.hibernate.interfaces.ICtgDiscountManagement;
import com.jbd.model.CtgDiscount;


public class CtgDiscountManagementDAO implements ICtgDiscountManagement {


	@PersistenceContext
	public EntityManager em;

	public CtgDiscountManagementDAO() {
		// TODO Auto-generated constructor stub
	}

	@Transactional
	@Override
	public CtgDiscount insertCtgDiscount(CtgDiscount o) {
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
	public CtgDiscount updateCtgDiscount(CtgDiscount o) {
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
	public void deleteCtgDiscount(CtgDiscount o) {
		// TODO Auto-generated method stub

	}

	@Override
	public CtgDiscount findCtgDiscount(Integer oId) {
		
		// TODO Auto-generated method stub
		try {
			CtgDiscount discount;
				TypedQuery<CtgDiscount> tq = em.createQuery("select o from CtgDiscount o where o.discountId=:prmDiscountId",
						CtgDiscount.class);
				tq.setParameter("prmDiscountId", oId);

				discount = tq.getSingleResult();
				return discount;

			} catch (Exception e) {
				e.printStackTrace();
				return null;

			}
	}

	@Override
	public List<CtgDiscount> findAll() {
		// TODO Auto-generated method stub
try {
			
			List<CtgDiscount> discountList;
			TypedQuery<CtgDiscount> tq = em.createQuery("select o from CtgDiscount o ",
					CtgDiscount.class);
			discountList = tq.getResultList();
			return discountList;

		} catch (Exception e) {
			e.printStackTrace();
			return null;

		}
	}

	@Override
	public List<CtgDiscount> findDiscountByExample(String discountName) {
		try {
			List<CtgDiscount> discounts ;
			if(discountName == null || discountName.isEmpty() ){
				discounts = findAll();
			}else{
					TypedQuery<CtgDiscount> tq = em.createQuery("select t from CtgDiscount t where upper(t.discountName) like '%' ||:prmDiscountName || '%' ",
							CtgDiscount.class);
					tq.setParameter("prmDiscountName", discountName.toUpperCase());
					discounts = tq.getResultList();
					
			}

			return discounts;
		} catch (Exception e) {
			e.printStackTrace();
			return null;

		}
	}

	@Override
	public List<CtgDiscount> findDiscountByExample(Date discountDate) {
		try {
			List<CtgDiscount> discounts ;
			if(discountDate == null){
				discounts = findAll();
			}else{
				TypedQuery<CtgDiscount> tq = em.createQuery("select t from CtgDiscount t where :prmDiscountDate between discountValidSince and discountValidUntil ",
						CtgDiscount.class);
				tq.setParameter("prmDiscountDate", discountDate);
				discounts = tq.getResultList();
					
			}

			return discounts;
		} catch (Exception e) {
			e.printStackTrace();
			return null;

		}
	}

		@Override
		public List<CtgDiscount> findDiscountByExample(String discountName, Date discountDate) {
			try {
				List<CtgDiscount> discounts ;
				if( (discountName == null || discountName.isEmpty()) && discountDate == null){
					discounts = findAll();
				}else{
					if( (discountName != null && !discountName.isEmpty()) && discountDate != null){
						TypedQuery<CtgDiscount> tq = em.createQuery("select t from CtgDiscount t where upper(t.discountName) like '%' ||:prmDiscountName || '%' "
								+ "and trunc(:prmDiscountDate) between trunc(discountValidSince) and trunc(discountValidUntil) ",
								CtgDiscount.class);
						tq.setParameter("prmDiscountName", discountName.toUpperCase());
						tq.setParameter("prmDiscountDate", discountDate);
						discounts = tq.getResultList();
					}else{
						if (discountDate != null){
							discounts = findDiscountByExample(discountDate);
						}else{
							discounts = findDiscountByExample(discountName);							
						}
						
					}						
				}

				return discounts;
			} catch (Exception e) {
				e.printStackTrace();
				return null;

			}

	}

		@Override
		public Date convertToDate(Date dateValue, String timeValue) {
			// TODO Auto-generated method stub
			try {
				Query query = em.createNativeQuery(  "SELECT STR_TO_DATE(CONCAT(SUBSTRING(DATE_FORMAT(?, '%Y-%m-%d'),1), lpad(?,4,'0')),'%Y-%m-%d%H%i') from dual");  
				   query.setParameter(1, dateValue);  
				   query.setParameter(2, timeValue);  
				   Date val = (Date) query.getSingleResult(); 
				return val;
				} catch (Exception e) {
					e.printStackTrace();
					return null;

				}
		}
}

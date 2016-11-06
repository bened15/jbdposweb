package com.jbd.hibernate.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.transaction.annotation.Transactional;

import com.jbd.hibernate.interfaces.IInvProductItemManagement;
import com.jbd.model.InvProductItem;
import com.jbd.model.InvProductItem;
import com.jbd.model.SysUser;


public class InvProductItemManagementDAO implements IInvProductItemManagement {


	@PersistenceContext
	public EntityManager em;

	public InvProductItemManagementDAO() {
		// TODO Auto-generated constructor stub
	}

	@Transactional
	@Override
	public InvProductItem insertInvProductItem(InvProductItem o) {
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
	public InvProductItem updateInvProductItem(InvProductItem o) {
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
	public void deleteInvProductItem(InvProductItem o) {
		// TODO Auto-generated method stub

	}

	@Override
	public InvProductItem findInvProductItem(Integer oId) {
		try {
			InvProductItem user;
				TypedQuery<InvProductItem> tq = em.createQuery("select o from InvProductItem o where o.invProductId=:prmInvProductId",
						InvProductItem.class);
				tq.setParameter("prmInvProductId", oId);

				user = tq.getSingleResult();
				return user;

			} catch (Exception e) {
				e.printStackTrace();
				return null;

			}
	}
	
	@Override
	public List<InvProductItem> findAll() {
		try {
			
			List<InvProductItem> userList;
			TypedQuery<InvProductItem> tq = em.createQuery("select o from InvProductItem o order by entryDate desc ",
					InvProductItem.class);
			userList = tq.getResultList();
			return userList;

		} catch (Exception e) {
			e.printStackTrace();
			return null;

		}
	}
	
	@Override
	public List<InvProductItem> findInvProductItemByExample(int productTypeId, int productId) {
		// TODO Auto-generated method stub
		StringBuilder sqlQuery = new StringBuilder(); 
		Boolean isFirst = true;
		Boolean useProductTypeId = false;
		Boolean useProductId = false;
		sqlQuery.append("select t from InvProductItem t where ");
		try {
			List<InvProductItem> products ;
			if( (productTypeId == 0 ) && (productId==0)   ){
				products = findAll();
			}else{
				if( productTypeId != 0 ){
					useProductTypeId = true;
					if(isFirst){
						sqlQuery.append(" t.restProduct.ctgProductType.productTypeId = :prmProductTypeId  ");
						isFirst= false;					
						}else{
							sqlQuery.append(" and t.restProduct.ctgProductType.productTypeId = :prmProductTypeId   ");						
						}

				}
				if( productId != 0 ){
					useProductId = true;
					if(isFirst){
						sqlQuery.append(" t.restProduct.productId = :prmProductId  ");
						isFirst= false;					
						}else{
							sqlQuery.append(" and t.restProduct.productId = :prmProductId   ");						
						}

				}
					TypedQuery<InvProductItem> tq = em.createQuery(sqlQuery.toString(),
							InvProductItem.class);
					if(useProductTypeId){
						tq.setParameter("prmProductTypeId", productTypeId);
						
					}
					if(useProductId){
						tq.setParameter("prmProductId", productTypeId);
						
					}
					products = tq.getResultList();
			}

			return products;
		} catch (Exception e) {
			e.printStackTrace();
			return null;

		}

	}

	@Override
	public List<InvProductItem> findAllOpen() {
		// TODO Auto-generated method stub
	try {
			
			List<InvProductItem> userList;
			TypedQuery<InvProductItem> tq = em.createQuery("select o from InvProductItem o where o.state = 'OPEN' order by entryDate asc ",
					InvProductItem.class);
			userList = tq.getResultList();
			return userList;

		} catch (Exception e) {
			e.printStackTrace();
			return null;

		}
	}

	@Override
	public List<InvProductItem> findInvProductItemByExampleOpen(int productTypeId, int productId) {
		// TODO Auto-generated method stub
		StringBuilder sqlQuery = new StringBuilder(); 
		Boolean isFirst = false;
		Boolean useProductTypeId = false;
		Boolean useProductId = false;
		sqlQuery.append("select t from InvProductItem t where t.state = 'OPEN' ");
		try {
			List<InvProductItem> products ;
			if( (productTypeId == 0 ) && (productId==0)   ){
				products = findAllOpen();
			}else{
				if( productTypeId != 0 ){
					useProductTypeId = true;
							sqlQuery.append(" and t.restProduct.ctgProductType.productTypeId = :prmProductTypeId   ");						

				}
				if( productId != 0 ){
					useProductId = true;
							sqlQuery.append(" and t.restProduct.productId = :prmProductId   ");						

				}
				sqlQuery.append("order by entryDate asc ");
					TypedQuery<InvProductItem> tq = em.createQuery(sqlQuery.toString(),
							InvProductItem.class);
					if(useProductTypeId){
						tq.setParameter("prmProductTypeId", productTypeId);
						
					}
					if(useProductId){
						tq.setParameter("prmProductId", productTypeId);
						
					}
					products = tq.getResultList();
			}

			return products;
		} catch (Exception e) {
			e.printStackTrace();
			return null;

		}
	}

}

package com.jbd.hibernate.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.transaction.annotation.Transactional;

import com.jbd.hibernate.interfaces.IRestProductManagement;
import com.jbd.model.RestProduct;
import com.jbd.model.RestProduct;
import com.jbd.model.SysUser;


public class RestProductManagementDAO implements IRestProductManagement {


	@PersistenceContext
	public EntityManager em;

	public RestProductManagementDAO() {
		// TODO Auto-generated constructor stub
	}

	@Transactional
	@Override
	public RestProduct insertRestProduct(RestProduct o) {
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
	public RestProduct updateRestProduct(RestProduct o) {
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
	public void deleteRestProduct(RestProduct o) {
		// TODO Auto-generated method stub

	}

	@Override
	public RestProduct findRestProduct(Integer oId) {
		try {
			RestProduct user;
			TypedQuery<RestProduct> tq = em.createQuery("select o from RestProduct o where o.productId=:prmProductId",
					RestProduct.class);
			tq.setParameter("prmProductId", oId);

			user = tq.getSingleResult();
			return user;

		} catch (Exception e) {
			e.printStackTrace();
			return null;

		}

	}
	
	@Override
	public List<RestProduct> findAll() {
		try {
			
			List<RestProduct> productList;
			TypedQuery<RestProduct> tq = em.createQuery("select o from RestProduct o ",
					RestProduct.class);
			productList = tq.getResultList();
			return productList;

		} catch (Exception e) {
			e.printStackTrace();
			return null;

		}
	}
	@Override
	public List<RestProduct> findByProductType(int productTypeId) {
		try {
			
			List<RestProduct> productList;
			TypedQuery<RestProduct> tq = em.createQuery("select o from RestProduct o where o.ctgProductType.productTypeId=:prmProductTypeId",
					RestProduct.class);
			tq.setParameter("prmProductTypeId", productTypeId);
			productList = tq.getResultList();
			return productList;

		} catch (Exception e) {
			e.printStackTrace();
			return null;

		}
	}

	@Override
	public List<RestProduct> findProductByExample(String productName, int productTypeId, int productSupplierId) {
		// TODO Auto-generated method stub
		StringBuilder sqlQuery = new StringBuilder(); 
		Boolean isFirst = true;
		Boolean userProductName = false;
		Boolean userProductTypeId = false;
		Boolean userProductSupplierId = false;
		sqlQuery.append("select t from RestProduct t where ");
		try {
			List<RestProduct> roles ;
			if( (productName == null || productName.isEmpty()) && (productTypeId == 0 )  && (productSupplierId == 0 )  ){
				roles = findAll();
			}else{
				if( (productName != null || !productName.isEmpty())){
					userProductName = true;
					if(isFirst){
						sqlQuery.append(" upper(t.productName) like '%' ||:prmProductName || '%'  ");
						isFirst= false;					
						}
				}
				if( productTypeId != 0 ){
					userProductTypeId = true;
					if(isFirst){
						sqlQuery.append(" t.ctgProductType.productTypeId = :prmProductTypeId  ");
						isFirst= false;					
						}else{
							sqlQuery.append(" and t.ctgProductType.productTypeId = :prmProductTypeId   ");						
						}

				}
				if( productSupplierId != 0 ){
					userProductSupplierId = true;
					if(isFirst){
						sqlQuery.append(" t.ctgSupplier.supplierId = :prmProductSupplierId  ");
						isFirst= false;					
						}else{
							sqlQuery.append(" and t.ctgSupplier.supplierId = :prmProductSupplierId   ");						
						}

				}
					TypedQuery<RestProduct> tq = em.createQuery(sqlQuery.toString(),
							RestProduct.class);
					if(userProductName){
						tq.setParameter("prmProductName", productName.toUpperCase());
						
					}
					if(userProductTypeId){
						tq.setParameter("prmProductTypeId", productTypeId);
						
					}
					if(userProductSupplierId){
						tq.setParameter("prmProductSupplierId", productSupplierId);
						
					}
					roles = tq.getResultList();
			}

			return roles;
		} catch (Exception e) {
			e.printStackTrace();
			return null;

		}

	}
	

}

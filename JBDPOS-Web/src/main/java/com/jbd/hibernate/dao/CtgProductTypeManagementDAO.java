package com.jbd.hibernate.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.transaction.annotation.Transactional;

import com.jbd.hibernate.interfaces.ICtgProductTypeManagement;
import com.jbd.model.CtgProductType;
import com.jbd.model.CtgProductType;
import com.jbd.model.SysUser;


public class CtgProductTypeManagementDAO implements ICtgProductTypeManagement {


	@PersistenceContext
	public EntityManager em;

	public CtgProductTypeManagementDAO() {
		// TODO Auto-generated constructor stub
	}

	@Transactional
	@Override
	public CtgProductType insertCtgProductType(CtgProductType o) {
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
	public CtgProductType updateCtgProductType(CtgProductType o) {
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
	public void deleteCtgProductType(CtgProductType o) {
		// TODO Auto-generated method stub

	}

	@Override
	public CtgProductType findCtgProductType(Integer oId) {
		try {
			CtgProductType productType;
			TypedQuery<CtgProductType> tq = em.createQuery("select o from CtgProductType o where o.productTypeId=:prmProductTypeId",
					CtgProductType.class);
			tq.setParameter("prmProductTypeId", oId);

			productType = tq.getSingleResult();
			return productType;

		} catch (Exception e) {
			e.printStackTrace();
			return null;

		}

	}


	@Override
	public List<CtgProductType> findAll() {
		// TODO Auto-generated method stub
		try {
			
			List<CtgProductType> productTypeList;
			TypedQuery<CtgProductType> tq = em.createQuery("select o from CtgProductType o ",
					CtgProductType.class);
			productTypeList = tq.getResultList();
			return productTypeList;

		} catch (Exception e) {
			e.printStackTrace();
			return null;

		}
	}
	
	@Override
	public List<CtgProductType> findProductTypeByExample(String typeName) {
		try {

			TypedQuery<CtgProductType> tq = em.createQuery(
					"select p from CtgProductType p where UPPER(p.typeName) like '%' || :prmProductTypeName || '%'",
					CtgProductType.class);
			tq.setParameter("prmProductTypeName", typeName.toUpperCase());


			List<CtgProductType> productType = tq.getResultList();

			return productType;
		} catch (Exception e) {
			e.printStackTrace();
			return null;

		}
	}


}

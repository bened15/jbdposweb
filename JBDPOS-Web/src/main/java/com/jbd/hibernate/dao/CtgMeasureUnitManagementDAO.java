package com.jbd.hibernate.dao;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.transaction.annotation.Transactional;

import com.jbd.hibernate.interfaces.ICtgMeasureUnitManagement;
import com.jbd.model.CtgMeasureUnit;
import com.jbd.model.CtgMeasureUnit;
import com.jbd.model.SysUser;


public class CtgMeasureUnitManagementDAO implements ICtgMeasureUnitManagement {


	@PersistenceContext
	public EntityManager em;

	public CtgMeasureUnitManagementDAO() {
		// TODO Auto-generated constructor stub
	}

	@Transactional
	@Override
	public CtgMeasureUnit insertCtgMeasureUnit(CtgMeasureUnit o) {
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
	public CtgMeasureUnit updateCtgMeasureUnit(CtgMeasureUnit o) {
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
	public void deleteCtgMeasureUnit(CtgMeasureUnit o) {
		// TODO Auto-generated method stub

	}

	@Override
	public CtgMeasureUnit findCtgMeasureUnit(Integer oId) {
		// TODO Auto-generated method stub
		try {
			CtgMeasureUnit user;
				TypedQuery<CtgMeasureUnit> tq = em.createQuery("select o from CtgMeasureUnit o where o.measureUnitId=:prmMeasureUnitId",
						CtgMeasureUnit.class);
				tq.setParameter("prmMeasureUnitId", oId);

				user = tq.getSingleResult();
				return user;

			} catch (Exception e) {
				e.printStackTrace();
				return null;

			}
	}

	@Override
	public List<CtgMeasureUnit> findAll() {
		// TODO Auto-generated method stub
try {
			
			List<CtgMeasureUnit> measureList;
			TypedQuery<CtgMeasureUnit> tq = em.createQuery("select o from CtgMeasureUnit o ",
					CtgMeasureUnit.class);
			measureList = tq.getResultList();
			return measureList;

		} catch (Exception e) {
			e.printStackTrace();
			return null;

		}
	}
	
	
	@Override
	public List<CtgMeasureUnit> findMeasureByExample(String measureName, String measureUnit) {
		try {
			List<CtgMeasureUnit> measures ;
			if( (measureName == null || measureName.isEmpty()) && (measureUnit == null || measureUnit.isEmpty()) ){
				measures = findAll();
			}else{
				if( (measureName != null && !measureName.isEmpty())  && (measureUnit != null && !measureUnit.isEmpty()) ){
					TypedQuery<CtgMeasureUnit> tq = em.createQuery("select t from CtgMeasureUnit t where upper(t.measureUni) like '%' ||:prmMeasureUni || '%' "
							+ "and upper(t.measureName) like '%' ||:prmMeasureName || '%' ",
							CtgMeasureUnit.class);
					tq.setParameter("prmMeasureUni", measureUnit.toUpperCase());
					tq.setParameter("prmMeasureName", measureName.toUpperCase());
					measures = tq.getResultList();
				}else{
					if ((measureName != null && !measureName.isEmpty())){
						TypedQuery<CtgMeasureUnit> tq = em.createQuery("select t from CtgMeasureUnit t where upper(t.measureName) like '%' ||:prmMeasureName || '%' ",
								CtgMeasureUnit.class);
						tq.setParameter("prmMeasureName", measureName.toUpperCase());
						measures = tq.getResultList();
					}else{
						TypedQuery<CtgMeasureUnit> tq = em.createQuery("select t from CtgMeasureUnit t where upper(t.measureUni) like '%' ||:prmMeasureUni || '%' ",
								CtgMeasureUnit.class);
						tq.setParameter("prmMeasureUni", measureUnit.toUpperCase());
						measures = tq.getResultList();
					}
					
				}						
			}

			return measures;
		} catch (Exception e) {
			e.printStackTrace();
			return null;

		}

}


}

package com.jbd.hibernate.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.transaction.annotation.Transactional;

import com.jbd.hibernate.interfaces.IRestAreaManagement;
import com.jbd.model.CtgDiscount;
import com.jbd.model.RestArea;
import com.jbd.model.RestArea;
import com.jbd.model.RestTable;

public class RestAreaManagementDAO implements IRestAreaManagement {

	@PersistenceContext
	public EntityManager em;

	public RestAreaManagementDAO() {
		// TODO Auto-generated constructor stub
	}

	@Transactional
	@Override
	public RestArea insertRestArea(RestArea o) {
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
	public RestArea updateRestArea(RestArea o) {
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
	public void deleteRestArea(RestArea o) {
		// TODO Auto-generated method stub

	}

	@Override
	public RestArea findRestArea(Integer oId) {
		try {
			RestArea table;
			TypedQuery<RestArea> tq = em.createQuery("select o from RestArea o where o.areaId=:prmAreaId",
					RestArea.class);
			tq.setParameter("prmAreaId", oId);

			table = tq.getSingleResult();
			return table;

		} catch (Exception e) {
			e.printStackTrace();
			return null;

		}
	}

	@Override
	public List<RestArea> getAllAreas() {
		try {
			TypedQuery<RestArea> tq = em.createNamedQuery("RestArea.findAll", RestArea.class);
			List<RestArea> areas = tq.getResultList();
			return areas;
		} catch (Exception e) {
			e.printStackTrace();
			return null;

		}

	}

	@Override
	public List<RestArea> findAll() {
		// TODO Auto-generated method stub
		try {

			List<RestArea> areaList;
			TypedQuery<RestArea> tq = em.createQuery("select o from RestArea o ", RestArea.class);
			areaList = tq.getResultList();
			return areaList;

		} catch (Exception e) {
			e.printStackTrace();
			return null;

		}

	}

	@Override
	public List<RestArea> findAreaByExample(String areaName, int smokingArea) {
		// TODO Auto-generated method stub
		StringBuilder sqlQuery = new StringBuilder();
		Boolean isFirst = true;
		Boolean useAreaName = false;
		Boolean useSmokingArea = false;
		sqlQuery.append("select t from RestArea t where ");
		try {
			List<RestArea> areas;
			if ((areaName == null || areaName.isEmpty()) && (smokingArea == 9)) {
				areas = findAll();
			} else {
				if ((areaName != null || !areaName.isEmpty())) {
					useAreaName = true;
					if (isFirst) {
						sqlQuery.append(" upper(t.areaName) like '%' ||:prmAreaName || '%'  ");
						isFirst = false;
					}
				}
				if (smokingArea != 9) {
					useSmokingArea = true;
					if (isFirst) {
						sqlQuery.append(" t.isSmokerArea = :prmSmokingArea  ");
						isFirst = false;
					} else {
						sqlQuery.append(" and t.isSmokerArea = :prmSmokingArea   ");
					}

				}
				TypedQuery<RestArea> tq = em.createQuery(sqlQuery.toString(), RestArea.class);
				if (useAreaName) {
					tq.setParameter("prmAreaName", areaName.toUpperCase());

				}
				if (useSmokingArea) {
					tq.setParameter("prmSmokingArea", smokingArea);

				}
				areas = tq.getResultList();
			}

			return areas;
		} catch (Exception e) {
			e.printStackTrace();
			return null;

		}

	}

}

package com.jbd.hibernate.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.NumberUtils;

import com.jbd.hibernate.interfaces.IRestTableManagement;
import com.jbd.model.RestArea;
import com.jbd.model.RestTable;

public class RestTableManagementDAO implements IRestTableManagement {

	@PersistenceContext
	public EntityManager em;

	public RestTableManagementDAO() {
		// TODO Auto-generated constructor stub
	}

	@Transactional
	@Override
	public RestTable insertRestTable(RestTable o) {
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
	public RestTable updateRestTable(RestTable o) {
		// TODO Auto-generated method stub
		try {
			em.merge(o);
			em.flush();
			return o;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public void deleteRestTable(RestTable o) {
		// TODO Auto-generated method stub

	}

	@Override
	public RestTable findRestTable(Integer oId) {
		try {
			RestTable table;
			TypedQuery<RestTable> tq = em.createQuery("select o from RestTable o where o.tableId=:prmTableId",
					RestTable.class);
			tq.setParameter("prmTableId", oId);

			table = tq.getSingleResult();
			return table;

		} catch (Exception e) {
			e.printStackTrace();
			return null;

		}

	}

	@Override
	public RestTable findRestTableByNameOrId(String tableName) {

		RestTable table;
		TypedQuery<RestTable> tq2 = em.createQuery("select o from RestTable o where o.tableName=:prmTableName",
				RestTable.class);
		tq2.setParameter("prmTableName", tableName);
		try {
			table = tq2.getSingleResult();
			return table;
		} catch (NoResultException e2) {
			TypedQuery<RestTable> tq = em.createQuery("select o from RestTable o where o.tableId=:prmTableId",
					RestTable.class);
			tq.setParameter("prmTableId", Integer.parseInt(tableName));
			try {
				table = tq.getSingleResult();
				return table;
			} catch (NoResultException e) {

				return null;
			}
		}

	}

	@Override
	public List<RestTable> findAll() {
		try {
			TypedQuery<RestTable> tq = em.createQuery("select t from RestTable t", RestTable.class);
			List<RestTable> tables = tq.getResultList();
			return tables;
		} catch (Exception e) {
			e.printStackTrace();
			return null;

		}

	}

	@Override
	public List<RestTable> findTablesByArea(RestArea area) {
		try {
			System.out.println(area.getAreaId() + "--Nombre primera are");
			TypedQuery<RestTable> tq = em.createQuery("select t from RestTable t where t.restArea=:areas",
					RestTable.class);
			tq.setParameter("areas", area);
			List<RestTable> tables = tq.getResultList();
			System.out.println("Tmaño de tables" + tables.size());
			// for (RestTable t : tables) {
			// System.out.println("Resultados :" + t.getTableId());
			//
			// }

			return tables;
		} catch (Exception e) {
			e.printStackTrace();
			return null;

		}

	}

	@Override
	public List<RestTable> findTablesByExample(int areaId) {
		// TODO Auto-generated method stub
		StringBuilder sqlQuery = new StringBuilder();
		Boolean isFirst = true;
		Boolean useAreaId = false;
		sqlQuery.append("select t from RestTable t where ");
		try {
			List<RestTable> tables;
			if ((areaId == 0)) {
				tables = findAll();
			} else {
				if (areaId != 0) {
					useAreaId = true;
					if (isFirst) {
						sqlQuery.append(" t.restArea.areaId = :prmAreaId  ");
						isFirst = false;
					} else {
						sqlQuery.append(" and t.restArea.areaId = :prmAreaId   ");
					}

				}
				TypedQuery<RestTable> tq = em.createQuery(sqlQuery.toString(), RestTable.class);
				if (useAreaId) {
					tq.setParameter("prmAreaId", areaId);

				}
				tables = tq.getResultList();
			}

			return tables;
		} catch (Exception e) {
			e.printStackTrace();
			return null;

		}

	}

}

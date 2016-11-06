package com.jbd.hibernate.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.transaction.annotation.Transactional;

import com.jbd.hibernate.interfaces.ICtgMenuSubTypeManagement;
import com.jbd.model.CtgMenuSubType;
import com.jbd.model.CtgMenuType;

public class CtgMenuSubTypeManagementDAO implements ICtgMenuSubTypeManagement {

	@PersistenceContext
	public EntityManager em;

	public CtgMenuSubTypeManagementDAO() {
		// TODO Auto-generated constructor stub
	}

	@Transactional
	@Override
	public CtgMenuSubType insertCtgMenuSubType(CtgMenuSubType o) {
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
	public CtgMenuSubType updateCtgMenuSubType(CtgMenuSubType o) {
		// TODO Auto-generated method stub
		try {
			em.merge(o);
			return o;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Transactional
	@Override
	public boolean deleteCtgMenuSubType(CtgMenuSubType o) {
		// TODO Auto-generated method stub
		try {
			em.remove(em.merge(o));
			return true;
		} catch (Exception e) {
			// e.printStackTrace();
			return false;
		}

	}

	@Override
	public CtgMenuSubType findCtgMenuSubType(Integer oId) {
		// TODO Auto-generated method stub
		try {
			CtgMenuSubType menuType;
			TypedQuery<CtgMenuSubType> tq = em.createQuery(
					"select o from CtgMenuSubType o where o.menuSubTypeId=:prmMenuSubTypeId", CtgMenuSubType.class);
			tq.setParameter("prmMenuSubTypeId", oId);

			menuType = tq.getSingleResult();
			return menuType;

		} catch (Exception e) {
			e.printStackTrace();
			return null;

		}
	}

	@Override
	public List<CtgMenuSubType> loadMenuType() {
		try {

			TypedQuery<CtgMenuSubType> tq = em.createNamedQuery("CtgMenuSubType.findAll", CtgMenuSubType.class);

			List<CtgMenuSubType> mtype = tq.getResultList();
			// System.out.println("Tmaño de menutype" + mtype.size());
			// for (CtgMenuSubType t : mtype) {
			// System.out.println("Resultados :" + t.getMenuTypeId());

			// }

			return mtype;
		} catch (Exception e) {
			e.printStackTrace();
			return null;

		}
	}

	@Override
	public List<CtgMenuSubType> findAll() {
		try {

			TypedQuery<CtgMenuSubType> tq = em.createNamedQuery("CtgMenuSubType.findAll", CtgMenuSubType.class);

			List<CtgMenuSubType> mtype = tq.getResultList();

			return mtype;
		} catch (Exception e) {
			e.printStackTrace();
			return null;

		}
	}

	@Override
	public List<CtgMenuSubType> findAllByType(CtgMenuType type) {
		try {

			TypedQuery<CtgMenuSubType> tq = em.createQuery("select ctgsubt from CtgMenuSubType ctgsubt where menuTypeId=:type ",
					CtgMenuSubType.class);
			tq.setParameter("type", type.getMenuTypeId());

			List<CtgMenuSubType> mtype = tq.getResultList();

			return mtype;
		} catch (Exception e) {
			e.printStackTrace();
			return null;

		}
	}

	@Override
	public List<CtgMenuSubType> findMenyTypeByExample(String name) {
		try {

			TypedQuery<CtgMenuSubType> tq = em.createQuery(
					"select p from CtgMenuSubType p where UPPER(p.menuSubTypeName) like '%' || :prmMenuSubTypeName || '%'",
					CtgMenuSubType.class);
			tq.setParameter("prmMenuSubTypeName", name.toUpperCase());

			List<CtgMenuSubType> menuType = tq.getResultList();

			return menuType;
		} catch (Exception e) {
			e.printStackTrace();
			return null;

		}
	}

	@Override
	public List<CtgMenuSubType> findByMenuType(CtgMenuType menuType) {
		// TODO Auto-generated method stub
		List<CtgMenuSubType> list = new ArrayList<>();
		try {

			TypedQuery<CtgMenuSubType> tq = em.createQuery(
					"select p from CtgMenuSubType p where p.menuTypeId = :prmMenutype ", CtgMenuSubType.class);
			tq.setParameter("prmMenutype", menuType.getMenuTypeId());

			list = tq.getResultList();

			return list;
		} catch (Exception e) {
			e.printStackTrace();

		}

		return list;
	}

}

package com.jbd.hibernate.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.transaction.annotation.Transactional;

import com.jbd.hibernate.interfaces.ICtgMenuTypeManagement;
import com.jbd.model.CtgMenuType;


public class CtgMenuTypeManagementDAO implements ICtgMenuTypeManagement {

	@PersistenceContext
	public EntityManager em;

	public CtgMenuTypeManagementDAO() {
		// TODO Auto-generated constructor stub
	}

	@Transactional
	@Override
	public CtgMenuType insertCtgMenuType(CtgMenuType o) {
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
	public CtgMenuType updateCtgMenuType(CtgMenuType o) {
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

	public boolean deleteCtgMenuType(CtgMenuType o) {
		// TODO Auto-generated method stub
		try {
			em.remove(em.merge(o));
			return true;
		} catch (Exception e) {
			//e.printStackTrace();
			return false;
		}


	}

	@Override
	public CtgMenuType findCtgMenuType(Integer oId) {
		// TODO Auto-generated method stub
		try {
			CtgMenuType menuType;
				TypedQuery<CtgMenuType> tq = em.createQuery("select o from CtgMenuType o where o.menuTypeId=:prmMenuTypeId",
						CtgMenuType.class);
				tq.setParameter("prmMenuTypeId", oId);

				menuType = tq.getSingleResult();
				return menuType;

			} catch (Exception e) {
				e.printStackTrace();
				return null;

			}
	}

	@Override
	public List<CtgMenuType> loadMenuType() {
		try {

			TypedQuery<CtgMenuType> tq = em.createNamedQuery("CtgMenuType.findAll", CtgMenuType.class);

			List<CtgMenuType> mtype = tq.getResultList();
//			System.out.println("Tmaño de menutype" + mtype.size());
//			for (CtgMenuType t : mtype) {
//				System.out.println("Resultados :" + t.getMenuTypeId());

//			}

			return mtype;
		} catch (Exception e) {
			e.printStackTrace();
			return null;

		}
	}

	@Override
	public List<CtgMenuType> findAll() {
		try {

			TypedQuery<CtgMenuType> tq = em.createNamedQuery("CtgMenuType.findAll", CtgMenuType.class);

			List<CtgMenuType> mtype = tq.getResultList();

			return mtype;
		} catch (Exception e) {
			e.printStackTrace();
			return null;

		}
	}

	@Override
	public List<CtgMenuType> findMenyTypeByExample(String name) {
		try {

			TypedQuery<CtgMenuType> tq = em.createQuery(
					"select p from CtgMenuType p where UPPER(p.menuTypeName) like '%' || :prmMenuTypeName || '%'",
					CtgMenuType.class);
			tq.setParameter("prmMenuTypeName", name.toUpperCase());


			List<CtgMenuType> menuType = tq.getResultList();

			return menuType;
		} catch (Exception e) {
			e.printStackTrace();
			return null;

		}
	}


}

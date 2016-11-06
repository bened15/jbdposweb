package com.jbd.hibernate.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.transaction.annotation.Transactional;

import com.jbd.hibernate.interfaces.ICtgTipManagement;
import com.jbd.model.CtgTip;

public class CtgTipManagementDAO implements ICtgTipManagement {

	@PersistenceContext
	public EntityManager em;

	public CtgTipManagementDAO() {
		// TODO Auto-generated constructor stub
	}

	@Transactional
	@Override
	public CtgTip insertCtgTip(CtgTip o) {
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
	public CtgTip updateCtgTip(CtgTip o) {
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
	public boolean deleteCtgTip(CtgTip o) {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		try {
			em.remove(em.merge(o));
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

	}

	@Override
	public CtgTip findCtgTip(Integer oId) {
		// TODO Auto-generated method stub
		try {
			CtgTip ctgTip;
			TypedQuery<CtgTip> tq = em.createQuery("select o from CtgTip o where o.idCtgTip=:prmTipId", CtgTip.class);
			tq.setParameter("prmTipId", oId);

			ctgTip = tq.getSingleResult();
			return ctgTip;

		} catch (Exception e) {
			e.printStackTrace();
			return null;

		}
	}

	@Override
	public List<CtgTip> findAll() {
		// TODO Auto-generated method stub
		try {

			List<CtgTip> ctgTipList;
			TypedQuery<CtgTip> tq = em.createQuery("select o from CtgTip o ", CtgTip.class);
			ctgTipList = tq.getResultList();
			return ctgTipList;

		} catch (Exception e) {
			e.printStackTrace();
			return null;

		}
	}

	@Override
	public List<CtgTip> findByDiscount(int tipId) {
		try {

			List<CtgTip> ctgTipList;
			TypedQuery<CtgTip> tq = em.createQuery("select o from CtgTip o where o.ctgDiscount.ctgTipId=:prmTipId",
					CtgTip.class);
			tq.setParameter("prmTipId", tipId);

			ctgTipList = tq.getResultList();
			return ctgTipList;

		} catch (Exception e) {
			e.printStackTrace();
			return null;

		}

	}

	@Override
	public CtgTip findDefaultCtgTip() {
		// TODO Auto-generated method stub
		try {
			CtgTip ctgTip;
			TypedQuery<CtgTip> tq = em.createQuery("select o from CtgTip o where o.byDefault='y'", CtgTip.class);

			ctgTip = tq.getSingleResult();
			return ctgTip;

		} catch (Exception e) {
			e.printStackTrace();
			return null;

		}
	}
	@Override
	public CtgTip findZeroCtgTip() {
		// TODO Auto-generated method stub
		try {
			CtgTip ctgTip;
			TypedQuery<CtgTip> tq = em.createQuery("select o from CtgTip o where o.percentValue=:per", CtgTip.class);
			tq.setParameter("per",0);

			ctgTip = tq.getSingleResult();
			return ctgTip;

		} catch (Exception e) {
			e.printStackTrace();
			return null;

		}
	}
}

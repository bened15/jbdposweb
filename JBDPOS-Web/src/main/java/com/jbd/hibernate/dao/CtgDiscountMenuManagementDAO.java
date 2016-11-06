package com.jbd.hibernate.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.transaction.annotation.Transactional;

import com.jbd.hibernate.interfaces.ICtgDiscountMenuManagement;
import com.jbd.model.CtgDiscountMenu;


public class CtgDiscountMenuManagementDAO implements ICtgDiscountMenuManagement {


	@PersistenceContext
	public EntityManager em;

	public CtgDiscountMenuManagementDAO() {
		// TODO Auto-generated constructor stub
	}

	@Transactional
	@Override
	public CtgDiscountMenu insertCtgDiscountMenu(CtgDiscountMenu o) {
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
	public CtgDiscountMenu updateCtgDiscountMenu(CtgDiscountMenu o) {
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
	public boolean deleteCtgDiscountMenu(CtgDiscountMenu o) {
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
	public CtgDiscountMenu findCtgDiscountMenu(Integer oId) {
		// TODO Auto-generated method stub
		try {
			CtgDiscountMenu discountMenu;
				TypedQuery<CtgDiscountMenu> tq = em.createQuery("select o from CtgDiscountMenu o where o.menuItemDiscountId=:prmMenuItemDiscountId",
						CtgDiscountMenu.class);
				tq.setParameter("prmMenuItemDiscountId", oId);

				discountMenu = tq.getSingleResult();
				return discountMenu;

			} catch (Exception e) {
				e.printStackTrace();
				return null;

			}
	}

	@Override
	public List<CtgDiscountMenu> findAll() {
		// TODO Auto-generated method stub
	try {
			
			List<CtgDiscountMenu> menuItemDiscountList;
			TypedQuery<CtgDiscountMenu> tq = em.createQuery("select o from CtgDiscountMenu o ",
					CtgDiscountMenu.class);
			menuItemDiscountList = tq.getResultList();
			return menuItemDiscountList;

		} catch (Exception e) {
			e.printStackTrace();
			return null;

		}
	}
	
	
	@Override
	public List<CtgDiscountMenu> findByDiscount(int DiscountId) {
		try {
			
			List<CtgDiscountMenu> menuItemDiscountList;
			TypedQuery<CtgDiscountMenu> tq = em.createQuery("select o from CtgDiscountMenu o where o.ctgDiscount.discountId=:prmDiscountId",
					CtgDiscountMenu.class);
			tq.setParameter("prmDiscountId", DiscountId);

			menuItemDiscountList = tq.getResultList();
			return menuItemDiscountList;

		} catch (Exception e) {
			e.printStackTrace();
			return null;

		}

}


}

package com.jbd.hibernate.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.transaction.annotation.Transactional;

import com.jbd.hibernate.interfaces.IRestMenuItemManagement;
import com.jbd.model.CtgMenuSubType;
import com.jbd.model.CtgMenuType;
import com.jbd.model.RestMenuItem;

public class RestMenuItemManagementDAO implements IRestMenuItemManagement {

	@PersistenceContext
	public EntityManager em;

	public RestMenuItemManagementDAO() {
		// TODO Auto-generated constructor stub
	}

	@Transactional
	@Override
	public RestMenuItem insertRestMenuItem(RestMenuItem o) {
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
	public RestMenuItem updateRestMenuItem(RestMenuItem o) {
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
	public void deleteRestMenuItem(RestMenuItem o) {
		// TODO Auto-generated method stub

	}

	@Override
	public RestMenuItem findRestMenuItem(Integer oId) {
		try {
			RestMenuItem user;
			TypedQuery<RestMenuItem> tq = em.createQuery(
					"select o from RestMenuItem o where o.menuItemId=:prmRestMenuItemId", RestMenuItem.class);
			tq.setParameter("prmRestMenuItemId", oId);

			user = tq.getSingleResult();
			return user;

		} catch (Exception e) {
			e.printStackTrace();
			return null;

		}
		// TODO Auto-generated method stub

	}

	@Override
	public List<RestMenuItem> findAll() {
		try {

			List<RestMenuItem> menuItemList;
			TypedQuery<RestMenuItem> tq = em.createQuery("select o from RestMenuItem o ", RestMenuItem.class);
			menuItemList = tq.getResultList();
			return menuItemList;

		} catch (Exception e) {
			e.printStackTrace();
			return null;

		}
	}

	@Override
	public List<RestMenuItem> findMenuItemByTypeMenu(CtgMenuType typeMenu) {
		try {

			TypedQuery<RestMenuItem> tq = em.createQuery("select t from RestMenuItem t where t.ctgMenuType=:menuType and t.available=1",
					RestMenuItem.class);
			tq.setParameter("menuType", typeMenu);
			List<RestMenuItem> menuItem = tq.getResultList();
//			System.out.println("Tmaño de items" + menuItem.size());
//			for (RestMenuItem t : menuItem) {
//				System.out.println("Resultados :" + t.getMenuItemId());
//
//			}

			return menuItem;
		} catch (Exception e) {
			e.printStackTrace();
			return null;

		}
	}

	@Override
	public List<RestMenuItem> findMenuItemBySubTypeMenu(CtgMenuSubType typeSubMenu) {
		try {

			TypedQuery<RestMenuItem> tq = em.createQuery("select t from RestMenuItem t where t.ctgMenuSubType=:menuSubType and t.available=1",
					RestMenuItem.class);
			tq.setParameter("menuSubType", typeSubMenu);
			List<RestMenuItem> menuItem = tq.getResultList();
			System.out.println("Tmaño de items" + menuItem.size());
			for (RestMenuItem t : menuItem) {
				System.out.println("Resultados :" + t.getMenuItemId());

			}

			return menuItem;
		} catch (Exception e) {
			e.printStackTrace();
			return null;

		}
	}

	@Override
	public List<RestMenuItem> findMenuItemByName(String nameMenuItem) {
		try {

			TypedQuery<RestMenuItem> tq = em.createQuery(
					"select t from RestMenuItem t where t.menuItemName like '%" + nameMenuItem + "%'",
					RestMenuItem.class);

			List<RestMenuItem> menuItem = tq.getResultList();
			System.out.println("Tmaño de items" + menuItem.size());
			for (RestMenuItem t : menuItem) {
				System.out.println("Resultados :" + t.getMenuItemId());

			}

			return menuItem;
		} catch (Exception e) {
			e.printStackTrace();
			return null;

		}
	}

	@Override
	public List<RestMenuItem> findMenuItemByExample(String menuItemName, int menuItemTypeId) {
		// TODO Auto-generated method stub
		StringBuilder sqlQuery = new StringBuilder();
		Boolean isFirst = true;
		Boolean useMenuItemName = false;
		Boolean useMenuItemTypeId = false;
		Boolean userProductSupplierId = false;
		sqlQuery.append("select t from RestMenuItem t where ");
		try {
			List<RestMenuItem> products;
			if ((menuItemName == null || menuItemName.isEmpty()) && (menuItemTypeId == 0)) {
				products = findAll();
			} else {
				if ((menuItemName != null && !menuItemName.isEmpty())) {
					useMenuItemName = true;
					if (isFirst) {
						sqlQuery.append(" upper(t.menuItemName) like '%' ||:prmMenuItemName || '%'  ");
						isFirst = false;
					}
				}
				if (menuItemTypeId != 0) {
					useMenuItemTypeId = true;
					if (isFirst) {
						sqlQuery.append(" t.ctgMenuType.menuTypeId = :prmMenuItemTypeId  ");
						isFirst = false;
					} else {
						sqlQuery.append(" and t.ctgMenuType.menuTypeId = :prmMenuItemTypeId   ");
					}

				}
				TypedQuery<RestMenuItem> tq = em.createQuery(sqlQuery.toString(), RestMenuItem.class);
				if (useMenuItemName) {
					tq.setParameter("prmMenuItemName", menuItemName.toUpperCase());

				}
				if (useMenuItemTypeId) {
					tq.setParameter("prmMenuItemTypeId", menuItemTypeId);

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

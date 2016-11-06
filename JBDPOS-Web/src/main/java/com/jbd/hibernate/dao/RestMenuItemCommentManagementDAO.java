package com.jbd.hibernate.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.transaction.annotation.Transactional;

import com.jbd.hibernate.interfaces.IRestMenuItemCommentManagement;
import com.jbd.model.RestMenuItem;
import com.jbd.model.RestMenuItemComment;

public class RestMenuItemCommentManagementDAO implements IRestMenuItemCommentManagement {

	@PersistenceContext
	public EntityManager em;

	public RestMenuItemCommentManagementDAO() {
		// TODO Auto-generated constructor stub
	}

	@Transactional
	@Override
	public RestMenuItemComment insertRestMenuItemComment(RestMenuItemComment o) {
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
	public RestMenuItemComment updateRestMenuItemComment(RestMenuItemComment o) {
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
	public boolean deleteRestMenuItemComment(RestMenuItemComment o) {
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
	public RestMenuItemComment findRestMenuItemComment(Integer oId) {
		try {
			RestMenuItemComment menuItemComment;
				TypedQuery<RestMenuItemComment> tq = em.createQuery("select o from RestMenuItemComment o where o.menuItemCommentId=:prmMenuItemCommentId",
						RestMenuItemComment.class);
				tq.setParameter("prmMenuItemCommentId", oId);

				menuItemComment = tq.getSingleResult();
				return menuItemComment;

			} catch (Exception e) {
				e.printStackTrace();
				return null;

			}

	}


	@Override
	public List<RestMenuItemComment> findByMenuItem(RestMenuItem menuItem) {
		// TODO Auto-generated method stub
		try {
			TypedQuery<RestMenuItemComment> q = em.createQuery(
					"select c from RestMenuItemComment c where c.restMenuItem=:menuItem ", RestMenuItemComment.class);
			q.setParameter("menuItem", menuItem);
			List<RestMenuItemComment> comments = q.getResultList();
			return comments;

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}

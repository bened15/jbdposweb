package com.jbd.hibernate.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.transaction.annotation.Transactional;

import com.jbd.hibernate.interfaces.ICtgMenuLanguageManagement;
import com.jbd.model.CtgMenuLanguage;


public class CtgMenuLanguageManagementDAO implements ICtgMenuLanguageManagement {


	@PersistenceContext
	public EntityManager em;

	public CtgMenuLanguageManagementDAO() {
		// TODO Auto-generated constructor stub
	}

	@Transactional
	@Override
	public CtgMenuLanguage insertCtgMenuLanguage(CtgMenuLanguage o) {
		try {
			em.persist(o);
			return o;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

	@Override
	public void updateCtgMenuLanguage(CtgMenuLanguage o) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteCtgMenuLanguage(CtgMenuLanguage o) {
		// TODO Auto-generated method stub

	}

	@Override
	public CtgMenuLanguage findCtgMenuLanguage(Integer oId) {
		return null;
		// TODO Auto-generated method stub

	}

}

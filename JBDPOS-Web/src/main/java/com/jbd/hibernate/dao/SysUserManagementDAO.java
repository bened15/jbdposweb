package com.jbd.hibernate.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.transaction.annotation.Transactional;

import com.jbd.hibernate.interfaces.ISysUserManagement;
import com.jbd.model.SysUser;
import com.jbd.model.RestOrder;
import com.jbd.model.RestTableAccount;
import com.jbd.model.SysUser;


public class SysUserManagementDAO implements ISysUserManagement {


	@PersistenceContext
	public EntityManager em;

	public SysUserManagementDAO() {
		// TODO Auto-generated constructor stub
	}

	@Transactional
	@Override
	public SysUser insertSysUser(SysUser o) {
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
	public SysUser updateSysUser(SysUser o) {
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
		public boolean deleteSysUser(SysUser o) {
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
	public SysUser findSysUser(String oId) {
		try {
		SysUser user;
			TypedQuery<SysUser> tq = em.createQuery("select o from SysUser o where o.userCode=:prmUserCode",
					SysUser.class);
			tq.setParameter("prmUserCode", oId);

			user = tq.getSingleResult();
			return user;

		} catch (Exception e) {
			e.printStackTrace();
			return null;

		}
		// TODO Auto-generated method stub

	}
	
	@Override
	public List<SysUser> findAll() {
		try {
			
			List<SysUser> userList;
			TypedQuery<SysUser> tq = em.createQuery("select o from SysUser o ",
					SysUser.class);
			userList = tq.getResultList();
			return userList;

		} catch (Exception e) {
			e.printStackTrace();
			return null;

		}
	}

	@Override
	public List<SysUser> findByUserExample(String name, String lastName, String userCode) {
		// TODO Auto-generated method stub
		StringBuilder sqlQuery = new StringBuilder(); 
		Boolean isFirst = true;
		Boolean useName = false;
		Boolean useLastName = false;
		Boolean useCode = false;
		sqlQuery.append("select t from SysUser t where ");
		try {
			List<SysUser> users ;
			if( (name == null || name.isEmpty()) && (lastName == null || lastName.isEmpty()) && (userCode == null || userCode.isEmpty()) ){
				users = findAll();
			}else{
				if( (name != null && !name.isEmpty())){
					useName = true;
					if(isFirst){
						sqlQuery.append(" upper(t.userName) like '%' ||:prmUserName || '%'  ");
						isFirst= false;					
						}
				}
				if( (lastName != null && !lastName.isEmpty())){
					useLastName = true;
					if(isFirst){
						sqlQuery.append(" upper(t.userLastname) like '%' ||:prmUserLastName || '%' ");
						isFirst= false;					
						}else{
							sqlQuery.append(" and upper(t.userLastname) like '%' ||:prmUserLastName || '%'  ");						
						}

				}
				if( (userCode != null && !userCode.isEmpty())){
					useCode = true;
					if(isFirst){
						sqlQuery.append(" upper(t.userCode) like '%' ||:prmUserCode || '%' ");
						isFirst= false;					
						}else{
							sqlQuery.append(" and upper(t.userCode) like '%' ||:prmUserCode || '%'  ");						
						}
				}
					TypedQuery<SysUser> tq = em.createQuery(sqlQuery.toString(),
							SysUser.class);
					if(useName){
						tq.setParameter("prmUserName", name.toUpperCase());
						
					}
					if(useLastName){
						tq.setParameter("prmUserLastName", lastName.toUpperCase());
						
					}
					if(useCode){
						tq.setParameter("prmUserCode", userCode.toUpperCase());						
					}
					users = tq.getResultList();
			}

			return users;
		} catch (Exception e) {
			e.printStackTrace();
			return null;

		}

	}
	
	

}

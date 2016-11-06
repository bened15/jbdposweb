package com.jbd.hibernate.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.transaction.annotation.Transactional;

import com.jbd.hibernate.interfaces.ISysRoleManagement;
import com.jbd.model.SysRole;
import com.jbd.model.SysRole;


public class SysRoleManagementDAO implements ISysRoleManagement {


	@PersistenceContext
	public EntityManager em;

	public SysRoleManagementDAO() {
		// TODO Auto-generated constructor stub
	}

	@Transactional
	@Override
	public SysRole insertSysRole(SysRole o) {
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
	public SysRole updateSysRole(SysRole o) {
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
	public void deleteSysRole(SysRole o) {
		// TODO Auto-generated method stub

	}

	@Override
	public SysRole findSysRole(String oId) {
		try {
			SysRole role;
				TypedQuery<SysRole> tq = em.createQuery("select o from SysRole o where o.rolCode=:prmRolCode",
						SysRole.class);
				tq.setParameter("prmRolCode", oId);

				role = tq.getSingleResult();
				return role;

			} catch (Exception e) {
				e.printStackTrace();
				return null;

			}
		// TODO Auto-generated method stub

	}
	
	@Override
	public List<SysRole> findAll() {
		try {
			
			List<SysRole> roleList;
			TypedQuery<SysRole> tq = em.createQuery("select o from SysRole o ",
					SysRole.class);
			roleList = tq.getResultList();
			return roleList;

		} catch (Exception e) {
			e.printStackTrace();
			return null;

		}
	}

	@Override
	public List<SysRole> findRoleByExample(String roleCode, String roleName) {
		// TODO Auto-generated method stub
		StringBuilder sqlQuery = new StringBuilder(); 
		Boolean isFirst = true;
		Boolean useRoleCode = false;
		Boolean userRoleName = false;
		sqlQuery.append("select t from SysRole t where ");
		try {
			List<SysRole> roles ;
			if( (roleCode == null || roleCode.isEmpty()) && (roleName == null || roleName.isEmpty())  ){
				roles = findAll();
			}else{
				if( (roleCode != null && !roleCode.isEmpty())){
					useRoleCode = true;
					if(isFirst){
						sqlQuery.append(" upper(t.rolCode) like '%' ||:prmRoleCode || '%'  ");
						isFirst= false;					
						}
				}
				if( (roleName != null && !roleName.isEmpty())){
					userRoleName = true;
					if(isFirst){
						sqlQuery.append(" upper(t.rolName) like '%' ||:prmRoleName || '%' ");
						isFirst= false;					
						}else{
							sqlQuery.append(" and upper(t.rolName) like '%' ||:prmRoleName || '%'  ");						
						}

				}
					TypedQuery<SysRole> tq = em.createQuery(sqlQuery.toString(),
							SysRole.class);
					if(useRoleCode){
						tq.setParameter("prmRoleCode", roleCode.toUpperCase());
						
					}
					if(userRoleName){
						tq.setParameter("prmRoleName", roleName.toUpperCase());
						
					}
					roles = tq.getResultList();
			}

			return roles;
		} catch (Exception e) {
			e.printStackTrace();
			return null;

		}

	}
	

	
}

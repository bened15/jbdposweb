package com.jbd.hibernate.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.transaction.annotation.Transactional;

import com.jbd.hibernate.interfaces.IRestBillDetailManagement;
import com.jbd.model.RestBill;
import com.jbd.model.RestBillDetail;
import com.jbd.model.RestOrder;
import com.jbd.model.RestTableAccount;

public class RestBillDetailManagementDAO implements IRestBillDetailManagement {

	@PersistenceContext
	public EntityManager em;

	public RestBillDetailManagementDAO() {
		// TODO Auto-generated constructor stub
	}

	@Transactional
	@Override
	public RestBillDetail insertRestBillDetail(RestBillDetail o) {
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
	public void updateRestBillDetail(RestBillDetail o) {
		try {
			em.merge(o);
			em.flush();
			System.out.println("Actualizado");
		} catch (Exception e) {
			e.printStackTrace();

		}
	}

	@Transactional
	@Override
	public void deleteRestBillDetail(RestBillDetail o) {
		try {
			RestBillDetail aeliminar = em.find(RestBillDetail.class, o.getBillDetailId());
			if (aeliminar != null) {
				em.remove(aeliminar);
				em.flush();
				em.clear();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public RestBillDetail findRestBillDetail(Integer oId) {
		return null;
		// TODO Auto-generated method stub

	}

	@Override
	public List<RestBillDetail> findAllRestBillDetailFromTableAccount(RestTableAccount Taccount) {
		try {
			TypedQuery<RestBillDetail> tq = em.createQuery(
					"select d from RestBillDetail d where d.restBill.restTableAccount=:Taccount", RestBillDetail.class);
			tq.setParameter("Taccount", Taccount);
			List<RestBillDetail> billDetail = tq.getResultList();
			return billDetail;

		} catch (Exception e) {
			e.printStackTrace();
			List<RestBillDetail> billDetail = null;
			return billDetail;

		}
	}

	@Override
	public List<RestBillDetail> findAllRestBillDetailFromRestBill(RestBill bill) {
		try {
			TypedQuery<RestBillDetail> tq = em.createQuery("select d from RestBillDetail d where d.restBill=:bill",
					RestBillDetail.class);
			tq.setParameter("bill", bill);
			List<RestBillDetail> billDetail = tq.getResultList();
			return billDetail;

		} catch (Exception e) {
			e.printStackTrace();
			List<RestBillDetail> billDetail = null;
			return billDetail;

		}
	}

	@Override
	public List<RestOrder> findAllRestBillDetailFromRestBillForTable(RestBill bill) {
		try {
			TypedQuery<RestOrder> tq = em.createQuery(
					"select new RestOrder(d.restOrder.restMenuItem.menuItemId,d.restOrder.restMenuItem.menuItemName,ROUND(d.restOrder.restMenuItem.menuItemPrice,2),count(d.restOrder)) from RestBillDetail d where d.restBill=:bill group by d.restOrder.restMenuItem.menuItemId,d.restOrder.restMenuItem.menuItemName,d.restOrder.restMenuItem.menuItemPrice ",
					RestOrder.class);
			tq.setParameter("bill", bill);
			List<RestOrder> orderDetail = tq.getResultList();
			System.out.println(orderDetail.get(0).getCantidad() + "esta es la cantidad");
			return orderDetail;

		} catch (Exception e) {
			e.printStackTrace();
			List<RestOrder> orderDetail = null;
			return orderDetail;

		}
	}

	@Override
	public RestBillDetail findRestBillDetailByOrder(RestOrder or) {
		TypedQuery<RestBillDetail> q = em.createQuery("select rbd from RestBillDetail rbd where rbd.restOrder=:ro",
				RestBillDetail.class);
		q.setParameter("ro", or);

		try {
			return q.getSingleResult();

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

	

}

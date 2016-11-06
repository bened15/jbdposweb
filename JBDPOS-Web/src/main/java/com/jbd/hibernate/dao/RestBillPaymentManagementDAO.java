package com.jbd.hibernate.dao;

import java.text.DecimalFormat;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.springframework.transaction.annotation.Transactional;

import com.jbd.hibernate.interfaces.IRestBillPaymentManagement;
import com.jbd.model.RestBill;
import com.jbd.model.RestBillPayment;
import com.jbd.model.RestTableAccount;

public class RestBillPaymentManagementDAO implements IRestBillPaymentManagement {

	@PersistenceContext
	public EntityManager em;
	private DecimalFormat decimFormat = new DecimalFormat("#.00");

	public RestBillPaymentManagementDAO() {
		// TODO Auto-generated constructor stub
	}

	@Transactional
	@Override
	public RestBillPayment insertRestBillPayment(RestBillPayment o) {
		try {
			em.persist(o);
			return o;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

	@Override
	public void updateRestBillPayment(RestBillPayment o) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteRestBillPayment(RestBillPayment o) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<RestBillPayment> findRestBillPayments(Integer billId) {
		try {
			TypedQuery<RestBillPayment> q = em.createQuery(
					"select rbp from RestBillPayment rbp where rbp.restBill.billId=:billId", RestBillPayment.class);
			q.setParameter("billId", billId);
			return q.getResultList();

		} catch (Exception e) {
			e.printStackTrace();
			return null;

		}

	}

	@Override
	public boolean isAmmountPaymentEqualOrMoreThanAccount(double totalAccount, RestTableAccount tableAc) {
		try {
			System.out.println("Total account supuestamente" + totalAccount);
			System.out.println("Mesa supuestamente" + tableAc.getRestTable().getTableName());
			Query q = em.createQuery(
					"select sum(p.amount) from RestBillPayment p where p.restBill.restTableAccount=:tableAc",
					Double.class);
			q.setParameter("tableAc", tableAc);

			// System.out.println(q.getSingleResult()+"asdfasfasdfsdfsdfsfsfasfsdf");
			double res = (double) q.getSingleResult();

			double result = Double.parseDouble(decimFormat.format(res));
			System.out.println("resultado comparacion" + result);
			if (result >= totalAccount) {
				return true;

			} else {

				return false;
			}

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public Double sumPaymentsForBill(RestBill bill) {
		try {
			TypedQuery<Double> q = em.createQuery(
					"select round(sum(rbp.amount),2) from RestBillPayment rbp where rbp.restBill=:bill", Double.class);
			q.setParameter("bill", bill);
			Double result = q.getSingleResult();
			if (result != null) {
				return result;
			} else {
				return 0.0;
			}

		} catch (Exception e) {
			e.printStackTrace();
			return null;

		}

	}

	@Override
	public Double totalBillPending(RestBill bill) {
		try {
			TypedQuery<Double> q = em.createQuery(
					"select round(rbp.restBill.billTotal - round(sum(rbp.amount),2),2) from RestBillPayment rbp where rbp.restBill=:bill",
					Double.class);
			q.setParameter("bill", bill);
			Double result = q.getSingleResult();
			if (result != null) {
				return result;
			} else {
				return bill.getBillTotal();
			}

		} catch (Exception e) {
			e.printStackTrace();
			return null;

		}

	}
}

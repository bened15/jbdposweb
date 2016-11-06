
package com.jbd.hibernate.dao;

import java.text.DecimalFormat;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.springframework.transaction.annotation.Transactional;

import com.jbd.hibernate.interfaces.IRestBillManagement;
import com.jbd.model.RestBill;
import com.jbd.model.RestTableAccount;

public class RestBillManagementDAO implements IRestBillManagement {

	@PersistenceContext
	public EntityManager em;
	

	public RestBillManagementDAO() {
		// TODO Auto-generated constructor stub
	}

	@Transactional
	@Override
	public RestBill insertRestBill(RestBill o) {
		try {
			em.persist(o);
			em.flush();
			return o;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

	@Transactional
	@Override
	public void updateRestBill(RestBill o) {
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
	public void deleteRestBill(RestBill o) {
		try {
			RestBill aeliminar = em.find(RestBill.class, o.getBillId());
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
	public RestBill findRestBill(Integer oId) {
		try {
			return em.find(RestBill.class, oId);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

	@Override
	public List<RestBill> findBillsWithRestTableAccount(RestTableAccount account) {
		try {

			TypedQuery<RestBill> tq = em.createQuery(
					"select b from RestBill b where b.restTableAccount=:tableAccount and b not in(select p.restBill from RestBillPayment p where p.restBill=b and p.amount=p.restBill.billTotal)",
					RestBill.class);
			tq.setParameter("tableAccount", account);
			List<RestBill> billItems = tq.getResultList();
			System.out.println("Tmaño de items" + billItems.size());
			// for (RestMenuItem t : menuItem) {
			// System.out.println("Resultados :" + t.getMenuItemId());
			//
			// }

			return billItems;
		} catch (Exception e) {
			e.printStackTrace();
			return null;

		}
	}

	@Override
	public List<RestBill> findOpenBillsWithRestTableAccount(RestTableAccount account) {
		try {

			TypedQuery<RestBill> tq = em.createQuery(
					"select b from RestBill b where b.restTableAccount=:tableAccount and b.status is null",
					RestBill.class);
			tq.setParameter("tableAccount", account);

			List<RestBill> billItems = tq.getResultList();
			System.out.println("Tmaño de items" + billItems.size());
			// for (RestMenuItem t : menuItem) {
			// System.out.println("Resultados :" + t.getMenuItemId());
			//
			// }

			return billItems;
		} catch (Exception e) {
			e.printStackTrace();
			return null;

		}
	}

	@Override
	public Double getTotalAccountFromTableCheckingDiscount(RestTableAccount ta, RestBill rb) {
		try {
			Query q = null;

			if (rb.getCtgDiscount() != null) {
				q = em.createQuery(
						"select round(sum(b.billTotal-(b.billTotal*(b.ctgDiscount.discountPercentage/100.0))),2) from RestBill b where b.restTableAccount=:ta and b.restTableAccount.closedDatetime is null and b=:rb",
						Double.class);
				q.setParameter("ta", ta);
				q.setParameter("rb", rb);
				System.out.println("Tiene descuento");

			} else {
				q = em.createQuery(
						"select  round(sum(b.billTotal),2) from RestBill b where b.restTableAccount=:ta and b.restTableAccount.closedDatetime is null and b=:rb",
						Double.class);
				System.out.println("No Tiene descuento");
				q.setParameter("ta", ta);
				q.setParameter("rb", rb);

			}
			double res = (double) q.getSingleResult();
			System.out.println("total de la cuenta con descuento" + res);

			// double result = Double
			// .parseDouble(decimFormat.format(res * (1 +
			// rb.getCtgTip().getPercentValue() / 100.0)));
			// System.out.println("total de la cuenta con descuento2"+result);
			return res;

		} catch (Exception e) {

			e.printStackTrace();
			return null;
		}

	}

	@Override
	public Double getTotalAccountFromTable(RestTableAccount ta) {
		try {
			Query q = em.createQuery(
					"select  round(sum(b.billTotal),2) from RestBill b where b.restTableAccount=:ta and b.restTableAccount.closedDatetime is null",
					Double.class);

			q.setParameter("ta", ta);
			return (Double) q.getSingleResult();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

	@Override
	public Double getSubTotalAccountFromTable(RestTableAccount ta) {
		try {
			Query q = em.createQuery(
					"select  round(sum(b.billSubtotal),2) from RestBill b where b.restTableAccount=:ta and b.restTableAccount.closedDatetime is null",
					Double.class);

			q.setParameter("ta", ta);
			return (Double) q.getSingleResult();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

	@Override
	public Double getTotalTipAccountFromTable(RestTableAccount ta) {
		try {
			Query q = em.createQuery(
					"select  round(sum(b.billTip),2) from RestBill b where b.restTableAccount=:ta and b.restTableAccount.closedDatetime is null",
					Double.class);

			q.setParameter("ta", ta);
			return (Double) q.getSingleResult();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

	@Override
	public Integer getMaxBillId() {
		try {
			Query q = em.createQuery("select  max(b.billId) from RestBill b", Integer.class);

			Integer v = (Integer) q.getSingleResult();
			System.out.println("value to return" + v);
			if (v == null) {
				return 0;
			} else {

				return v;
			}

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

	@Transactional
	@Override
	public void cuadrarCuentas(RestTableAccount rta) {
		try {
			TypedQuery<RestBill> bQ = em.createQuery(
					"select new RestBill(rbd.restBill.billId,round(sum(rbd.billDetailSubtotal),2),round(sum(rbd.billDetailTotal),2),round(round(sum(rbd.billDetailSubtotal),2)*(rbd.restBill.ctgTip.percentValue/100.0),2)) from RestBillDetail rbd  where rbd.restBill.restTableAccount=:rta group by rbd.restBill.billId ",
					RestBill.class);
			bQ.setParameter("rta", rta);

			List<RestBill> rb = bQ.getResultList();

			for (RestBill b : rb) {
				RestBill billToUpdate = this.findRestBill(b.getBillId());
				billToUpdate.setBillSubtotal(b.getBillSubtotal());
				if (billToUpdate.getCtgDiscount() != null) {

					billToUpdate.setBillTotal(b.getBillTotal()
							- (b.getBillSubtotal() * (billToUpdate.getCtgDiscount().getDiscountPercentage() / 100.0)));
					billToUpdate.setBillTip(b.getBillTip()
							- (b.getBillTip() * (billToUpdate.getCtgDiscount().getDiscountPercentage() / 100.0)));
				} else {

					billToUpdate.setBillTotal(b.getBillTotal());
					billToUpdate.setBillTip(b.getBillTip());
				}

				this.updateRestBill(billToUpdate);

			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}

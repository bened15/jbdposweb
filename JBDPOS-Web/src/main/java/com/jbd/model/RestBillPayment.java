package com.jbd.model;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The persistent class for the rest_bill_payment database table.
 *
 */
@Entity
@Table(name = "rest_bill_payment")
@NamedQuery(name = "RestBillPayment.findAll", query = "SELECT r FROM RestBillPayment r")
public class RestBillPayment implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_BILL_PAYMENT")
	private int idBillPayment;

	private double amount;

	// bi-directional many-to-one association to RestBill
	@ManyToOne
	@JoinColumn(name = "ID_BILL")
	private RestBill restBill;

	// bi-directional many-to-one association to CtgPaymentMethod
	@ManyToOne
	@JoinColumn(name = "ID_PAYMENT_METHOD")
	private CtgPaymentMethod ctgPaymentMethod;

	private String comments;

	public RestBillPayment() {
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public int getIdBillPayment() {
		return this.idBillPayment;
	}

	public void setIdBillPayment(int idBillPayment) {
		this.idBillPayment = idBillPayment;
	}

	public double getAmount() {
		return this.amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public RestBill getRestBill() {
		return this.restBill;
	}

	public void setRestBill(RestBill restBill) {
		this.restBill = restBill;
	}

	public CtgPaymentMethod getCtgPaymentMethod() {
		return this.ctgPaymentMethod;
	}

	public void setCtgPaymentMethod(CtgPaymentMethod ctgPaymentMethod) {
		this.ctgPaymentMethod = ctgPaymentMethod;
	}

}
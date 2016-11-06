package com.jbd.model;

import java.io.Serializable;
import javax.persistence.*;

import java.util.Date;
import java.util.List;


/**
 * The persistent class for the ctg_payment_method database table.
 *
 */
@Entity
@Table(name="ctg_payment_method")
@NamedQuery(name="CtgPaymentMethod.findAll", query="SELECT c FROM CtgPaymentMethod c")
public class CtgPaymentMethod implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="PAYMENT_METHOD_ID")
	private int paymentMethodId;

	@Column(name="DESCRIPTION")
	private String description;

	@Column(name="NAME")
	private String name;

	@Temporal(TemporalType.DATE)
	@Column(name="ENTRY_DATE")
	private Date entryDate;

	@Column(name="ENTRY_USER")
	private String entryUser;

	//bi-directional many-to-one association to RestBill
	@OneToMany(mappedBy="ctgPaymentMethod")
	private List<RestBill> restBills;

	//bi-directional many-to-one association to RestBillPayment
	@OneToMany(mappedBy="ctgPaymentMethod")
	private List<RestBillPayment> restBillPayments;

	// bi-directional many-to-one association to AdmReservation
		@OneToMany(mappedBy = "ctgPaymentMethod")
		private List<AdmReservation> admReservations;
		
	public CtgPaymentMethod() {
	}

	public int getPaymentMethodId() {
		return this.paymentMethodId;
	}

	public void setPaymentMethodId(int paymentMethodId) {
		this.paymentMethodId = paymentMethodId;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	
	
	public Date getEntryDate() {
		return entryDate;
	}

	public void setEntryDate(Date entryDate) {
		this.entryDate = entryDate;
	}

	public String getEntryUser() {
		return entryUser;
	}

	public void setEntryUser(String entryUser) {
		this.entryUser = entryUser;
	}

	public List<RestBill> getRestBills() {
		return this.restBills;
	}

	public void setRestBills(List<RestBill> restBills) {
		this.restBills = restBills;
	}

	public RestBill addRestBill(RestBill restBill) {
		getRestBills().add(restBill);
		restBill.setCtgPaymentMethod(this);

		return restBill;
	}

	public RestBill removeRestBill(RestBill restBill) {
		getRestBills().remove(restBill);
		restBill.setCtgPaymentMethod(null);

		return restBill;
	}

	public List<RestBillPayment> getRestBillPayments() {
		return this.restBillPayments;
	}

	public void setRestBillPayments(List<RestBillPayment> restBillPayments) {
		this.restBillPayments = restBillPayments;
	}

	public RestBillPayment addRestBillPayment(RestBillPayment restBillPayment) {
		getRestBillPayments().add(restBillPayment);
		restBillPayment.setCtgPaymentMethod(this);

		return restBillPayment;
	}

	public RestBillPayment removeRestBillPayment(RestBillPayment restBillPayment) {
		getRestBillPayments().remove(restBillPayment);
		restBillPayment.setCtgPaymentMethod(null);

		return restBillPayment;
	}

	
	public List<AdmReservation> getAdmReservations() {
		return this.admReservations;
	}

	public void setAdmReservations(List<AdmReservation> admReservations) {
		this.admReservations = admReservations;
	}

	public AdmReservation addRestBillPayment(AdmReservation admReservation) {
		getAdmReservations().add(admReservation);
		admReservation.setCtgPaymentMethod(this);

		return admReservation;
	}

	public AdmReservation removeRestBillPayment(AdmReservation admReservation) {
		getAdmReservations().remove(admReservation);
		admReservation.setCtgPaymentMethod(null);

		return admReservation;
	}
	
	
	@Override
	public String toString(){
		return this.getPaymentMethodId() + " - " + this.getName();
	}
	
}
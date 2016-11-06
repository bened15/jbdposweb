package com.jbd.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * The persistent class for the adm_customer database table.
 *
 */
@Entity
@Table(name = "adm_customer")
@NamedQuery(name = "AdmCustomer.findAll", query = "SELECT a FROM AdmCustomer a")
public class AdmCustomer implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "CUSTOMER_ID")
	private int customerId;

	@Column(name = "CUSTOMER_ADDRESS1")
	private String customerAddress1;

	@Column(name = "CUSTOMER_ADDRESS2")
	private String customerAddress2;

	@Column(name = "CUSTOMER_EMAIL")
	private String customerEmail;

	@Column(name = "CUSTOMER_LASTNAME")
	private String customerLastname;

	@Column(name = "CUSTOMER_NAME")
	private String customerName;

	@Column(name = "CUSTOMER_PHONE1")
	private String customerPhone1;

	@Column(name = "CUSTOMER_PHONE2")
	private String customerPhone2;

	@Temporal(TemporalType.DATE)
	@Column(name = "ENTRY_DATE")
	private Date entryDate;

	@Column(name = "ENTRY_USER")
	private String entryUser;

	// bi-directional many-to-one association to AdmReservation
	@OneToMany(mappedBy = "admCustomer")
	private List<AdmReservation> admReservations;

	public AdmCustomer() {
	}

	public int getCustomerId() {
		return this.customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public String getCustomerAddress1() {
		return this.customerAddress1;
	}

	public void setCustomerAddress1(String customerAddress1) {
		this.customerAddress1 = customerAddress1;
	}

	public String getCustomerAddress2() {
		return this.customerAddress2;
	}

	public void setCustomerAddress2(String customerAddress2) {
		this.customerAddress2 = customerAddress2;
	}

	public String getCustomerEmail() {
		return this.customerEmail;
	}

	public void setCustomerEmail(String customerEmail) {
		this.customerEmail = customerEmail;
	}

	public String getCustomerLastname() {
		return this.customerLastname;
	}

	public void setCustomerLastname(String customerLastname) {
		this.customerLastname = customerLastname;
	}

	public String getCustomerName() {
		return this.customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getCustomerPhone1() {
		return this.customerPhone1;
	}

	public void setCustomerPhone1(String customerPhone1) {
		this.customerPhone1 = customerPhone1;
	}

	public String getCustomerPhone2() {
		return this.customerPhone2;
	}

	public void setCustomerPhone2(String customerPhone2) {
		this.customerPhone2 = customerPhone2;
	}

	public Date getEntryDate() {
		return this.entryDate;
	}

	public void setEntryDate(Date entryDate) {
		this.entryDate = entryDate;
	}

	public String getEntryUser() {
		return this.entryUser;
	}

	public void setEntryUser(String entryUser) {
		this.entryUser = entryUser;
	}

	public List<AdmReservation> getAdmReservations() {
		return this.admReservations;
	}

	public void setAdmReservations(List<AdmReservation> admReservations) {
		this.admReservations = admReservations;
	}

	public AdmReservation addAdmReservation(AdmReservation admReservation) {
		getAdmReservations().add(admReservation);
		admReservation.setAdmCustomer(this);

		return admReservation;
	}

	public AdmReservation removeAdmReservation(AdmReservation admReservation) {
		getAdmReservations().remove(admReservation);
		admReservation.setAdmCustomer(null);

		return admReservation;
	}

	
	@Override
	public String toString(){
		return this.getCustomerLastname() + ", " + this.getCustomerName();
	}

}
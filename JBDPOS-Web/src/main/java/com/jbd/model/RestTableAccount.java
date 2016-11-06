package com.jbd.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

/**
 * The persistent class for the rest_table_account database table.
 *
 */
@Entity
@Table(name = "rest_table_account")
@NamedQuery(name = "RestTableAccount.findAll", query = "SELECT r FROM RestTableAccount r")
public class RestTableAccount implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "TABLE_ACCOUNT_ID")
	private int tableAccountId;

	@Column(name = "ACCOUNT_STATUS")
	private String accountStatus;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CLOSED_DATETIME")
	private Date closedDatetime;

	@Column(name = "CREATED_BY")
	private String createdBy;

	@Column(name = "GUEST_NUM")
	private Integer guestNum;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CREATED_DATETIME")
	private Date createdDatetime;

	// bi-directional many-to-one association to RestBill
	@OneToMany(mappedBy = "restTableAccount")
	@Cascade({ CascadeType.DELETE })
	private List<RestBill> restBills;

	// bi-directional many-to-one association to RestOrder
	@OneToMany(mappedBy = "restTableAccount")
//	@Cascade({ CascadeType.DELETE })
	private List<RestOrder> restOrders;

	// bi-directional many-to-one association to RestShift
	@ManyToOne
	@JoinColumn(name = "ID_SHIFT_CLOSED")
	private RestShift restShift1;

	// bi-directional many-to-one association to RestShift
	@ManyToOne
	@JoinColumn(name = "ID_SHIFT_OPENED")
	private RestShift restShift2;

	// bi-directional many-to-one association to RestTable
	@ManyToOne
	@JoinColumn(name = "TABLE_ID")
	private RestTable restTable;

	public RestTableAccount() {
	}

	public Integer getGuestNum() {
		return guestNum;
	}

	public void setGuestNum(Integer guestNum) {
		this.guestNum = guestNum;
	}

	public int getTableAccountId() {
		return this.tableAccountId;
	}

	public void setTableAccountId(int tableAccountId) {
		this.tableAccountId = tableAccountId;
	}

	public String getAccountStatus() {
		return this.accountStatus;
	}

	public void setAccountStatus(String accountStatus) {
		this.accountStatus = accountStatus;
	}

	public Date getClosedDatetime() {
		return this.closedDatetime;
	}

	public void setClosedDatetime(Date closedDatetime) {
		this.closedDatetime = closedDatetime;
	}

	public String getCreatedBy() {
		return this.createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreatedDatetime() {
		return this.createdDatetime;
	}

	public void setCreatedDatetime(Date createdDatetime) {
		this.createdDatetime = createdDatetime;
	}

	public List<RestBill> getRestBills() {
		return this.restBills;
	}

	public void setRestBills(List<RestBill> restBills) {
		this.restBills = restBills;
	}

	public RestBill addRestBill(RestBill restBill) {
		getRestBills().add(restBill);
		restBill.setRestTableAccount(this);

		return restBill;
	}

	public RestBill removeRestBill(RestBill restBill) {
		getRestBills().remove(restBill);
		restBill.setRestTableAccount(null);

		return restBill;
	}

	public List<RestOrder> getRestOrders() {
		return this.restOrders;
	}

	public void setRestOrders(List<RestOrder> restOrders) {
		this.restOrders = restOrders;
	}

	public RestOrder addRestOrder(RestOrder restOrder) {
		getRestOrders().add(restOrder);
		restOrder.setRestTableAccount(this);

		return restOrder;
	}

	public RestOrder removeRestOrder(RestOrder restOrder) {
		getRestOrders().remove(restOrder);
		restOrder.setRestTableAccount(null);

		return restOrder;
	}

	public RestShift getRestShift1() {
		return this.restShift1;
	}

	public void setRestShift1(RestShift restShift1) {
		this.restShift1 = restShift1;
	}

	public RestShift getRestShift2() {
		return this.restShift2;
	}

	public void setRestShift2(RestShift restShift2) {
		this.restShift2 = restShift2;
	}

	public RestTable getRestTable() {
		return this.restTable;
	}

	public void setRestTable(RestTable restTable) {
		this.restTable = restTable;
	}

}
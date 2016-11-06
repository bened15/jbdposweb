package com.jbd.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the rest_shift database table.
 *
 */
@Entity
@Table(name="rest_shift")
@NamedQuery(name="RestShift.findAll", query="SELECT r FROM RestShift r")
public class RestShift implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="ID_SHIFT")
	private int idShift;

	@Column(name="CLOSED_BY")
	private String closedBy;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="CLOSING_DATETIME")
	private Date closingDatetime;

	@Column(name="INITIAL_MONEY")
	private double initialMoney;

	@Column(name="OPENED_BY")
	private String openedBy;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="OPENING_DATETIME")
	private Date openingDatetime;

	@Column(name="ORDERS_TAKEN")
	private int ordersTaken;

	@Column(name="PENDING_TABLES")
	private int pendingTables;

	@Column(name="RECEIVED_TABLES")
	private int receivedTables;

	@Column(name="SERVED_TABLES")
	private int servedTables;

	private String status;

	//bi-directional many-to-one association to RestOrder
	@OneToMany(mappedBy="restShift")
	private List<RestOrder> restOrders;

	//bi-directional many-to-one association to RestShiftLog
	@OneToMany(mappedBy="restShift")
	private List<RestShiftLog> restShiftLogs;

	//bi-directional many-to-one association to RestTableAccount
	@OneToMany(mappedBy="restShift1")
	private List<RestTableAccount> restTableAccounts1;

	//bi-directional many-to-one association to RestTableAccount
	@OneToMany(mappedBy="restShift2")
	private List<RestTableAccount> restTableAccounts2;

	public RestShift() {
	}

	public int getIdShift() {
		return this.idShift;
	}

	public void setIdShift(int idShift) {
		this.idShift = idShift;
	}

	public String getClosedBy() {
		return this.closedBy;
	}

	public void setClosedBy(String closedBy) {
		this.closedBy = closedBy;
	}

	public Date getClosingDatetime() {
		return this.closingDatetime;
	}

	public void setClosingDatetime(Date closingDatetime) {
		this.closingDatetime = closingDatetime;
	}

	public double getInitialMoney() {
		return this.initialMoney;
	}

	public void setInitialMoney(double initialMoney) {
		this.initialMoney = initialMoney;
	}

	public String getOpenedBy() {
		return this.openedBy;
	}

	public void setOpenedBy(String openedBy) {
		this.openedBy = openedBy;
	}

	public Date getOpeningDatetime() {
		return this.openingDatetime;
	}

	public void setOpeningDatetime(Date openingDatetime) {
		this.openingDatetime = openingDatetime;
	}

	public int getOrdersTaken() {
		return this.ordersTaken;
	}

	public void setOrdersTaken(int ordersTaken) {
		this.ordersTaken = ordersTaken;
	}

	public int getPendingTables() {
		return this.pendingTables;
	}

	public void setPendingTables(int pendingTables) {
		this.pendingTables = pendingTables;
	}

	public int getReceivedTables() {
		return this.receivedTables;
	}

	public void setReceivedTables(int receivedTables) {
		this.receivedTables = receivedTables;
	}

	public int getServedTables() {
		return this.servedTables;
	}

	public void setServedTables(int servedTables) {
		this.servedTables = servedTables;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public List<RestOrder> getRestOrders() {
		return this.restOrders;
	}

	public void setRestOrders(List<RestOrder> restOrders) {
		this.restOrders = restOrders;
	}

	public RestOrder addRestOrder(RestOrder restOrder) {
		getRestOrders().add(restOrder);
		restOrder.setRestShift(this);

		return restOrder;
	}

	public RestOrder removeRestOrder(RestOrder restOrder) {
		getRestOrders().remove(restOrder);
		restOrder.setRestShift(null);

		return restOrder;
	}

	public List<RestShiftLog> getRestShiftLogs() {
		return this.restShiftLogs;
	}

	public void setRestShiftLogs(List<RestShiftLog> restShiftLogs) {
		this.restShiftLogs = restShiftLogs;
	}

	public RestShiftLog addRestShiftLog(RestShiftLog restShiftLog) {
		getRestShiftLogs().add(restShiftLog);
		restShiftLog.setRestShift(this);

		return restShiftLog;
	}

	public RestShiftLog removeRestShiftLog(RestShiftLog restShiftLog) {
		getRestShiftLogs().remove(restShiftLog);
		restShiftLog.setRestShift(null);

		return restShiftLog;
	}

	public List<RestTableAccount> getRestTableAccounts1() {
		return this.restTableAccounts1;
	}

	public void setRestTableAccounts1(List<RestTableAccount> restTableAccounts1) {
		this.restTableAccounts1 = restTableAccounts1;
	}

	public RestTableAccount addRestTableAccounts1(RestTableAccount restTableAccounts1) {
		getRestTableAccounts1().add(restTableAccounts1);
		restTableAccounts1.setRestShift1(this);

		return restTableAccounts1;
	}

	public RestTableAccount removeRestTableAccounts1(RestTableAccount restTableAccounts1) {
		getRestTableAccounts1().remove(restTableAccounts1);
		restTableAccounts1.setRestShift1(null);

		return restTableAccounts1;
	}

	public List<RestTableAccount> getRestTableAccounts2() {
		return this.restTableAccounts2;
	}

	public void setRestTableAccounts2(List<RestTableAccount> restTableAccounts2) {
		this.restTableAccounts2 = restTableAccounts2;
	}

	public RestTableAccount addRestTableAccounts2(RestTableAccount restTableAccounts2) {
		getRestTableAccounts2().add(restTableAccounts2);
		restTableAccounts2.setRestShift2(this);

		return restTableAccounts2;
	}

	public RestTableAccount removeRestTableAccounts2(RestTableAccount restTableAccounts2) {
		getRestTableAccounts2().remove(restTableAccounts2);
		restTableAccounts2.setRestShift2(null);

		return restTableAccounts2;
	}

}
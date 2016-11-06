package com.jbd.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the rest_shift_log database table.
 *
 */
@Entity
@Table(name="rest_shift_log")
@NamedQuery(name="RestShiftLog.findAll", query="SELECT r FROM RestShiftLog r")
public class RestShiftLog implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="ID_SHIFT_LOG")
	private int idShiftLog;

	@Column(name="CLOSED_BY")
	private String closedBy;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="CLOSING_DATETIME")
	private Date closingDatetime;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="ENTRY_DATE")
	private Date entryDate;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="ENTRY_DATE_LOG")
	private Date entryDateLog;

	@Column(name="ENTRY_USER")
	private String entryUser;

	@Column(name="INITIAL_MONEY")
	private float initialMoney;

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

	//bi-directional many-to-one association to RestShift
	@ManyToOne
	@JoinColumn(name="ID_SHIFT")
	private RestShift restShift;

	public RestShiftLog() {
	}

	public int getIdShiftLog() {
		return this.idShiftLog;
	}

	public void setIdShiftLog(int idShiftLog) {
		this.idShiftLog = idShiftLog;
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

	public Date getEntryDate() {
		return this.entryDate;
	}

	public void setEntryDate(Date entryDate) {
		this.entryDate = entryDate;
	}

	public Date getEntryDateLog() {
		return this.entryDateLog;
	}

	public void setEntryDateLog(Date entryDateLog) {
		this.entryDateLog = entryDateLog;
	}

	public String getEntryUser() {
		return this.entryUser;
	}

	public void setEntryUser(String entryUser) {
		this.entryUser = entryUser;
	}

	public float getInitialMoney() {
		return this.initialMoney;
	}

	public void setInitialMoney(float initialMoney) {
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

	public RestShift getRestShift() {
		return this.restShift;
	}

	public void setRestShift(RestShift restShift) {
		this.restShift = restShift;
	}

}
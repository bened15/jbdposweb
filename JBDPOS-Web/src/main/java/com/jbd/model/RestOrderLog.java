package com.jbd.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the rest_order_log database table.
 *
 */
@Entity
@Table(name="rest_order_log")
@NamedQuery(name="RestOrderLog.findAll", query="SELECT r FROM RestOrderLog r")
public class RestOrderLog implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="ORDER_LOG_ID")
	private int orderLogId;

	private String attendant;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="ENTRY_DATE")
	private Date entryDate;

	@Column(name="MENU_ITEM_ID")
	private int menuItemId;

	@Column(name="ORDER_COMMENT")
	private String orderComment;

	@Column(name="ORDER_STATUS")
	private String orderStatus;

	private int quantity;

	@Column(name="TABLE_ACCOUNT_ID")
	private int tableAccountId;

	@Column(name="TABLE_ID")
	private int tableId;

	//bi-directional many-to-one association to RestOrder
	@ManyToOne
	@JoinColumn(name="ORDER_ID")
	private RestOrder restOrder;

	public RestOrderLog() {
	}

	public int getOrderLogId() {
		return this.orderLogId;
	}

	public void setOrderLogId(int orderLogId) {
		this.orderLogId = orderLogId;
	}

	public String getAttendant() {
		return this.attendant;
	}

	public void setAttendant(String attendant) {
		this.attendant = attendant;
	}

	public Date getEntryDate() {
		return this.entryDate;
	}

	public void setEntryDate(Date entryDate) {
		this.entryDate = entryDate;
	}

	public int getMenuItemId() {
		return this.menuItemId;
	}

	public void setMenuItemId(int menuItemId) {
		this.menuItemId = menuItemId;
	}

	public String getOrderComment() {
		return this.orderComment;
	}

	public void setOrderComment(String orderComment) {
		this.orderComment = orderComment;
	}

	public String getOrderStatus() {
		return this.orderStatus;
	}

	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}

	public int getQuantity() {
		return this.quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public int getTableAccountId() {
		return this.tableAccountId;
	}

	public void setTableAccountId(int tableAccountId) {
		this.tableAccountId = tableAccountId;
	}

	public int getTableId() {
		return this.tableId;
	}

	public void setTableId(int tableId) {
		this.tableId = tableId;
	}

	public RestOrder getRestOrder() {
		return this.restOrder;
	}

	public void setRestOrder(RestOrder restOrder) {
		this.restOrder = restOrder;
	}

}
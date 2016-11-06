package com.jbd.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;


/**
 * The persistent class for the inv_inventory_waste database table.
 *
 */
@Entity
@Table(name="inv_inventory_waste")
@NamedQuery(name="InvInventoryWaste.findAll", query="SELECT i FROM InvInventoryWaste i")
public class InvInventoryWaste implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="ID_INVENTORY_WASTE")
	private int idInventoryWaste;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="ENTRY_DATE")
	private Date entryDate;

	@Column(name="ENTRY_USER")
	private String entryUser;

	@Column(name="STATE")
	private String state;

	@Column(name="PRODUCT_QTY_WASTE")
	private int productQtyWaste;

	//bi-directional many-to-one association to InvProductItem
	@ManyToOne
	@JoinColumn(name="INV_PRODUCT_ID")
	private InvProductItem invProductItem;

	public InvInventoryWaste() {
	}

	public int getIdInventoryWaste() {
		return this.idInventoryWaste;
	}

	public void setIdInventoryWaste(int idInventoryWaste) {
		this.idInventoryWaste = idInventoryWaste;
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

	public int getProductQtyWaste() {
		return this.productQtyWaste;
	}

	public void setProductQtyWaste(int productQtyWaste) {
		this.productQtyWaste = productQtyWaste;
	}

	public InvProductItem getInvProductItem() {
		return this.invProductItem;
	}

	public void setInvProductItem(InvProductItem invProductItem) {
		this.invProductItem = invProductItem;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	
}
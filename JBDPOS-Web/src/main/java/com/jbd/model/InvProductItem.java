package com.jbd.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the inv_product_item database table.
 *
 */
@Entity
@Table(name="inv_product_item")
@NamedQuery(name="InvProductItem.findAll", query="SELECT i FROM InvProductItem i")
public class InvProductItem implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="INV_PRODUCT_ID")
	private int invProductId;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="ENTRY_DATE")
	private Date entryDate;

	@Temporal(TemporalType.DATE)
	@Column(name="EXPIRATION_DATE")
	private Date expirationDate;

	@Column(name="ENTRY_USER")
	private String entryUser;

	@Column(name="PRODUCT_PRICE")
	private float productPrice;

	@Column(name="PRODUCT_QTY")
	private int productQty;

	@Column(name="PRODUCT_QTY_AVAILABILITY")
	private int productQtyAvailability;

	private String state;

	@Column(name="TRANSACTION_TYPE_ID")
	private String transactionTypeId;

	//bi-directional many-to-one association to InvInventoryWaste
	@OneToMany(mappedBy="invProductItem")
	private List<InvInventoryWaste> invInventoryWastes;

	//bi-directional many-to-one association to RestProduct
	@ManyToOne
	@JoinColumn(name="PRODUCT_ID")
	private RestProduct restProduct;

	public InvProductItem() {
	}

	public int getInvProductId() {
		return this.invProductId;
	}

	public void setInvProductId(int invProductId) {
		this.invProductId = invProductId;
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

	public float getProductPrice() {
		return this.productPrice;
	}

	public void setProductPrice(float productPrice) {
		this.productPrice = productPrice;
	}

	public int getProductQty() {
		return this.productQty;
	}

	public void setProductQty(int productQty) {
		this.productQty = productQty;
	}

	public int getProductQtyAvailability() {
		return this.productQtyAvailability;
	}

	public void setProductQtyAvailability(int productQtyAvailability) {
		this.productQtyAvailability = productQtyAvailability;
	}

	public String getState() {
		return this.state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getTransactionTypeId() {
		return this.transactionTypeId;
	}

	public void setTransactionTypeId(String transactionTypeId) {
		this.transactionTypeId = transactionTypeId;
	}

	public List<InvInventoryWaste> getInvInventoryWastes() {
		return this.invInventoryWastes;
	}

	public void setInvInventoryWastes(List<InvInventoryWaste> invInventoryWastes) {
		this.invInventoryWastes = invInventoryWastes;
	}

	public InvInventoryWaste addInvInventoryWaste(InvInventoryWaste invInventoryWaste) {
		getInvInventoryWastes().add(invInventoryWaste);
		invInventoryWaste.setInvProductItem(this);

		return invInventoryWaste;
	}

	public InvInventoryWaste removeInvInventoryWaste(InvInventoryWaste invInventoryWaste) {
		getInvInventoryWastes().remove(invInventoryWaste);
		invInventoryWaste.setInvProductItem(null);

		return invInventoryWaste;
	}

	public RestProduct getRestProduct() {
		return this.restProduct;
	}

	public void setRestProduct(RestProduct restProduct) {
		this.restProduct = restProduct;
	}
	
	@Transient
	private String restProductNameText;
	
	@Transient
	private String restProductTypeText;

	public String getRestProductNameText() {
		return restProductNameText;
	}

	public void setRestProductNameText(String restProductNameText) {
		this.restProductNameText = restProductNameText;
	}

	public String getRestProductTypeText() {
		return restProductTypeText;
	}

	public void setRestProductTypeText(String restProductTypeText) {
		this.restProductTypeText = restProductTypeText;
	}

	public Date getExpirationDate() {
		return expirationDate;
	}

	public void setExpirationDate(Date expirationDate) {
		this.expirationDate = expirationDate;
	}
	
	
	

}
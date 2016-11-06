package com.jbd.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the rest_product database table.
 *
 */
@Entity
@Table(name="rest_product")
@NamedQuery(name="RestProduct.findAll", query="SELECT r FROM RestProduct r")
public class RestProduct implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="PRODUCT_ID")
	private int productId;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="ENTRY_DATE")
	private Date entryDate;

	@Column(name="ENTRY_USER")
	private String entryUser;

	@Column(name="PRODUCT_DESCRIPTION")
	private String productDescription;

	@Column(name="PRODUCT_NAME")
	private String productName;

	@Column(name="PRODUCT_QTY_AVAILABILITY")
	private float productQtyAvailability;

	@Column(name="PRODUCT_QTY_TRESHOLD")
	private float productQtyTreshold;

	@Column(name="PRODUCT_WASTE")
	private float productWaste;

	//bi-directional many-to-one association to InvProductItem
	@OneToMany(mappedBy="restProduct")
	private List<InvProductItem> invProductItems;

	//bi-directional many-to-one association to InvProductTransactionLog
	@OneToMany(mappedBy="restProduct")
	private List<InvProductTransactionLog> invProductTransactionLogs;

	//bi-directional many-to-one association to RestMenuItemProduct
	@OneToMany(mappedBy="restProduct")
	private List<RestMenuItemProduct> restMenuItemProducts;

	//bi-directional many-to-one association to CtgMeasureUnit
	@ManyToOne
	@JoinColumn(name="MEASURE_UNIT_ID")
	private CtgMeasureUnit ctgMeasureUnit;

	//bi-directional many-to-one association to CtgProductType
	@ManyToOne
	@JoinColumn(name="PRODUCT_TYPE_ID")
	private CtgProductType ctgProductType;

	//bi-directional many-to-one association to CtgSupplier
	@ManyToOne
	@JoinColumn(name="SUPPLIER_ID")
	private CtgSupplier ctgSupplier;

	public RestProduct() {
	}

	public int getProductId() {
		return this.productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
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

	public String getProductDescription() {
		return this.productDescription;
	}

	public void setProductDescription(String productDescription) {
		this.productDescription = productDescription;
	}

	public String getProductName() {
		return this.productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public float getProductQtyAvailability() {
		return this.productQtyAvailability;
	}

	public void setProductQtyAvailability(float productQtyAvailability) {
		this.productQtyAvailability = productQtyAvailability;
	}

	public float getProductQtyTreshold() {
		return this.productQtyTreshold;
	}

	public void setProductQtyTreshold(float productQtyTreshold) {
		this.productQtyTreshold = productQtyTreshold;
	}

	public float getProductWaste() {
		return this.productWaste;
	}

	public void setProductWaste(float productWaste) {
		this.productWaste = productWaste;
	}

	public List<InvProductItem> getInvProductItems() {
		return this.invProductItems;
	}

	public void setInvProductItems(List<InvProductItem> invProductItems) {
		this.invProductItems = invProductItems;
	}

	public InvProductItem addInvProductItem(InvProductItem invProductItem) {
		getInvProductItems().add(invProductItem);
		invProductItem.setRestProduct(this);

		return invProductItem;
	}

	public InvProductItem removeInvProductItem(InvProductItem invProductItem) {
		getInvProductItems().remove(invProductItem);
		invProductItem.setRestProduct(null);

		return invProductItem;
	}

	public List<InvProductTransactionLog> getInvProductTransactionLogs() {
		return this.invProductTransactionLogs;
	}

	public void setInvProductTransactionLogs(List<InvProductTransactionLog> invProductTransactionLogs) {
		this.invProductTransactionLogs = invProductTransactionLogs;
	}

	public InvProductTransactionLog addInvProductTransactionLog(InvProductTransactionLog invProductTransactionLog) {
		getInvProductTransactionLogs().add(invProductTransactionLog);
		invProductTransactionLog.setRestProduct(this);

		return invProductTransactionLog;
	}

	public InvProductTransactionLog removeInvProductTransactionLog(InvProductTransactionLog invProductTransactionLog) {
		getInvProductTransactionLogs().remove(invProductTransactionLog);
		invProductTransactionLog.setRestProduct(null);

		return invProductTransactionLog;
	}

	public List<RestMenuItemProduct> getRestMenuItemProducts() {
		return this.restMenuItemProducts;
	}

	public void setRestMenuItemProducts(List<RestMenuItemProduct> restMenuItemProducts) {
		this.restMenuItemProducts = restMenuItemProducts;
	}

	public RestMenuItemProduct addRestMenuItemProduct(RestMenuItemProduct restMenuItemProduct) {
		getRestMenuItemProducts().add(restMenuItemProduct);
		restMenuItemProduct.setRestProduct(this);

		return restMenuItemProduct;
	}

	public RestMenuItemProduct removeRestMenuItemProduct(RestMenuItemProduct restMenuItemProduct) {
		getRestMenuItemProducts().remove(restMenuItemProduct);
		restMenuItemProduct.setRestProduct(null);

		return restMenuItemProduct;
	}

	public CtgMeasureUnit getCtgMeasureUnit() {
		return this.ctgMeasureUnit;
	}

	public void setCtgMeasureUnit(CtgMeasureUnit ctgMeasureUnit) {
		this.ctgMeasureUnit = ctgMeasureUnit;
	}

	public CtgProductType getCtgProductType() {
		return this.ctgProductType;
	}

	public void setCtgProductType(CtgProductType ctgProductType) {
		this.ctgProductType = ctgProductType;
	}

	public CtgSupplier getCtgSupplier() {
		return this.ctgSupplier;
	}

	public void setCtgSupplier(CtgSupplier ctgSupplier) {
		this.ctgSupplier = ctgSupplier;
	}

	@Transient
	public String productTypeText;

	public String getProductTypeText() {
		return productTypeText;
	}

	public void setProductTypeText(String productTypeText) {
		this.productTypeText = productTypeText;
	}

	@Override
	public String toString(){
		return this.productId + " - " + this.productName;
	}
	
}
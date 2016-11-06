package com.jbd.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the ctg_product_type database table.
 *
 */
@Entity
@Table(name="ctg_product_type")
@NamedQuery(name="CtgProductType.findAll", query="SELECT c FROM CtgProductType c")
public class CtgProductType implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="PRODUCT_TYPE_ID")
	private int productTypeId;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="ENTRY_DATE")
	private Date entryDate;

	@Column(name="ENTRY_USER")
	private String entryUser;

	@Column(name="TYPE_DESCRIPTION")
	private String typeDescription;

	@Column(name="TYPE_NAME")
	private String typeName;

	//bi-directional many-to-one association to RestProduct
	@OneToMany(mappedBy="ctgProductType")
	private List<RestProduct> restProducts;

	public CtgProductType() {
	}

	public int getProductTypeId() {
		return this.productTypeId;
	}

	public void setProductTypeId(int productTypeId) {
		this.productTypeId = productTypeId;
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

	public String getTypeDescription() {
		return this.typeDescription;
	}

	public void setTypeDescription(String typeDescription) {
		this.typeDescription = typeDescription;
	}

	public String getTypeName() {
		return this.typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public List<RestProduct> getRestProducts() {
		return this.restProducts;
	}

	public void setRestProducts(List<RestProduct> restProducts) {
		this.restProducts = restProducts;
	}

	public RestProduct addRestProduct(RestProduct restProduct) {
		getRestProducts().add(restProduct);
		restProduct.setCtgProductType(this);

		return restProduct;
	}

	public RestProduct removeRestProduct(RestProduct restProduct) {
		getRestProducts().remove(restProduct);
		restProduct.setCtgProductType(null);

		return restProduct;
	}

	@Override
	public String  toString(){
		return this.productTypeId + " - " + this.typeName;
	}
	
}
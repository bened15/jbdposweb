package com.jbd.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the ctg_supplier database table.
 *
 */
@Entity
@Table(name="ctg_supplier")
@NamedQuery(name="CtgSupplier.findAll", query="SELECT c FROM CtgSupplier c")
public class CtgSupplier implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="SUPPLIER_ID")
	private int supplierId;

	private String address;

	private String city;

	@Column(name="CONTACT_EMAIL")
	private String contactEmail;

	@Column(name="CONTACT_LASTNAME")
	private String contactLastname;

	@Column(name="CONTACT_NAME")
	private String contactName;

	@Column(name="CONTACT_PHONE_NUMBER")
	private String contactPhoneNumber;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="ENTRY_DATE")
	private Date entryDate;

	@Column(name="ENTRY_USER")
	private String entryUser;

	@Column(name="SUPPLIER_EMAIL")
	private String supplierEmail;

	@Column(name="SUPPLIER_NAME")
	private String supplierName;

	@Column(name="SUPPLIER_PHONE_NUMBER")
	private String supplierPhoneNumber;

	//bi-directional many-to-one association to RestProduct
	@OneToMany(mappedBy="ctgSupplier")
	private List<RestProduct> restProducts;

	public CtgSupplier() {
	}

	public int getSupplierId() {
		return this.supplierId;
	}

	public void setSupplierId(int supplierId) {
		this.supplierId = supplierId;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCity() {
		return this.city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getContactEmail() {
		return this.contactEmail;
	}

	public void setContactEmail(String contactEmail) {
		this.contactEmail = contactEmail;
	}

	public String getContactLastname() {
		return this.contactLastname;
	}

	public void setContactLastname(String contactLastname) {
		this.contactLastname = contactLastname;
	}

	public String getContactName() {
		return this.contactName;
	}

	public void setContactName(String contactName) {
		this.contactName = contactName;
	}

	public String getContactPhoneNumber() {
		return this.contactPhoneNumber;
	}

	public void setContactPhoneNumber(String contactPhoneNumber) {
		this.contactPhoneNumber = contactPhoneNumber;
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

	public String getSupplierEmail() {
		return this.supplierEmail;
	}

	public void setSupplierEmail(String supplierEmail) {
		this.supplierEmail = supplierEmail;
	}

	public String getSupplierName() {
		return this.supplierName;
	}

	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}

	public String getSupplierPhoneNumber() {
		return this.supplierPhoneNumber;
	}

	public void setSupplierPhoneNumber(String supplierPhoneNumber) {
		this.supplierPhoneNumber = supplierPhoneNumber;
	}

	public List<RestProduct> getRestProducts() {
		return this.restProducts;
	}

	public void setRestProducts(List<RestProduct> restProducts) {
		this.restProducts = restProducts;
	}

	public RestProduct addRestProduct(RestProduct restProduct) {
		getRestProducts().add(restProduct);
		restProduct.setCtgSupplier(this);

		return restProduct;
	}

	public RestProduct removeRestProduct(RestProduct restProduct) {
		getRestProducts().remove(restProduct);
		restProduct.setCtgSupplier(null);

		return restProduct;
	}
	
	@Override
	public String toString(){
		return this.supplierId + " - " + this.supplierName;
	}

	@Transient
	public String contactNameText;

	public String getContactNameText() {
		return contactNameText;
	}

	public void setContactNameText(String contactNameText) {
		this.contactNameText = contactNameText;
	}
	
}
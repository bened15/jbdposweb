package com.jbd.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.jbd.model.RestMenuItemProduct;
import com.jbd.model.RestProduct;


/**
 * The persistent class for the ctg_measure_unit database table.
 *
 */
@Entity
@Table(name="ctg_measure_unit")
@NamedQuery(name="CtgMeasureUnit.findAll", query="SELECT c FROM CtgMeasureUnit c")
public class CtgMeasureUnit implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="MEASURE_UNIT_ID")
	private int measureUnitId;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="ENTRY_DATE")
	private Date entryDate;

	@Column(name="ENTRY_USER")
	private String entryUser;

	@Column(name="MEASURE_NAME")
	private String measureName;

	@Column(name="MEASURE_UNI")
	private String measureUni;

	//bi-directional many-to-one association to RestMenuItemProduct
	@OneToMany(mappedBy="ctgMeasureUnit")
	private List<RestMenuItemProduct> restMenuItemProducts;

	//bi-directional many-to-one association to RestProduct
	@OneToMany(mappedBy="ctgMeasureUnit")
	private List<RestProduct> restProducts;

	public CtgMeasureUnit() {
	}

	public int getMeasureUnitId() {
		return this.measureUnitId;
	}

	public void setMeasureUnitId(int measureUnitId) {
		this.measureUnitId = measureUnitId;
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

	public String getMeasureName() {
		return this.measureName;
	}

	public void setMeasureName(String measureName) {
		this.measureName = measureName;
	}

	public String getMeasureUni() {
		return this.measureUni;
	}

	public void setMeasureUni(String measureUni) {
		this.measureUni = measureUni;
	}

	public List<RestMenuItemProduct> getRestMenuItemProducts() {
		return this.restMenuItemProducts;
	}

	public void setRestMenuItemProducts(List<RestMenuItemProduct> restMenuItemProducts) {
		this.restMenuItemProducts = restMenuItemProducts;
	}

	public RestMenuItemProduct addRestMenuItemProduct(RestMenuItemProduct restMenuItemProduct) {
		getRestMenuItemProducts().add(restMenuItemProduct);
		restMenuItemProduct.setCtgMeasureUnit(this);

		return restMenuItemProduct;
	}

	public RestMenuItemProduct removeRestMenuItemProduct(RestMenuItemProduct restMenuItemProduct) {
		getRestMenuItemProducts().remove(restMenuItemProduct);
		restMenuItemProduct.setCtgMeasureUnit(null);

		return restMenuItemProduct;
	}

	public List<RestProduct> getRestProducts() {
		return this.restProducts;
	}

	public void setRestProducts(List<RestProduct> restProducts) {
		this.restProducts = restProducts;
	}

	public RestProduct addRestProduct(RestProduct restProduct) {
		getRestProducts().add(restProduct);
		restProduct.setCtgMeasureUnit(this);

		return restProduct;
	}

	public RestProduct removeRestProduct(RestProduct restProduct) {
		getRestProducts().remove(restProduct);
		restProduct.setCtgMeasureUnit(null);

		return restProduct;
	}
	@Override
	public String toString(){
		return this.measureUni + " - "+ this.measureName;
	}


}
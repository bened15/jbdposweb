package com.jbd.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the rest_menu_item_product database table.
 *
 */
@Entity
@Table(name="rest_menu_item_product")
@NamedQuery(name="RestMenuItemProduct.findAll", query="SELECT r FROM RestMenuItemProduct r")
public class RestMenuItemProduct implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="MENU_ITEM_PRODUCT_ID")
	private int menuItemProductId;

	private float quantity;

	//bi-directional many-to-one association to CtgMeasureUnit
	@ManyToOne
	@JoinColumn(name="MEASURE_UNIT_ID")
	private CtgMeasureUnit ctgMeasureUnit;

	//bi-directional many-to-one association to RestMenuItem
	@ManyToOne
	@JoinColumn(name="MENU_ITEM_ID")
	private RestMenuItem restMenuItem;

	//bi-directional many-to-one association to RestProduct
	@ManyToOne
	@JoinColumn(name="PRODUCT_ID")
	private RestProduct restProduct;

	public RestMenuItemProduct() {
	}

	public int getMenuItemProductId() {
		return this.menuItemProductId;
	}

	public void setMenuItemProductId(int menuItemProductId) {
		this.menuItemProductId = menuItemProductId;
	}

	public float getQuantity() {
		return this.quantity;
	}

	public void setQuantity(float quantity) {
		this.quantity = quantity;
	}

	public CtgMeasureUnit getCtgMeasureUnit() {
		return this.ctgMeasureUnit;
	}

	public void setCtgMeasureUnit(CtgMeasureUnit ctgMeasureUnit) {
		this.ctgMeasureUnit = ctgMeasureUnit;
	}

	public RestMenuItem getRestMenuItem() {
		return this.restMenuItem;
	}

	public void setRestMenuItem(RestMenuItem restMenuItem) {
		this.restMenuItem = restMenuItem;
	}

	public RestProduct getRestProduct() {
		return this.restProduct;
	}

	public void setRestProduct(RestProduct restProduct) {
		this.restProduct = restProduct;
	}
	
	@Transient
	private String restMenuItemProductTypeText;

	@Transient
	private String restMenuItemProductNameText;

	public String getRestMenuItemProductTypeText() {
		return restMenuItemProductTypeText;
	}

	public void setRestMenuItemProductTypeText(String restMenuItemProductTypeText) {
		this.restMenuItemProductTypeText = restMenuItemProductTypeText;
	}

	public String getRestMenuItemProductNameText() {
		return restMenuItemProductNameText;
	}

	public void setRestMenuItemProductNameText(String restMenuItemProductNameText) {
		this.restMenuItemProductNameText = restMenuItemProductNameText;
	}
	

	

}
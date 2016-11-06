package com.jbd.model;

import java.io.Serializable;

import javax.persistence.*;
import com.jbd.model.RestMenuItem;


/**
 * The persistent class for the ctg_measure_unit database table.
 *
 */
@Entity
@Table(name="ctg_discount_menu")
@NamedQuery(name="CtgDiscountMenu.findAll", query="SELECT c FROM CtgDiscountMenu c")
public class CtgDiscountMenu implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="MENU_ITEM_DISCOUNT_ID")
	private int menuItemDiscountId;


	//bi-directional many-to-one association to CtgMeasureUnit
	@ManyToOne
	@JoinColumn(name="DISCOUNT_ID")
	private CtgDiscount ctgDiscount;

	//bi-directional many-to-one association to RestMenuItem
	@ManyToOne
	@JoinColumn(name="MENU_ITEM_ID")
	private RestMenuItem restMenuItem;

	
	public CtgDiscountMenu() {
	}

	public int getMenuItemDiscountId() {
		return menuItemDiscountId;
	}

	public void setMenuItemDiscountId(int menuItemDiscountId) {
		this.menuItemDiscountId = menuItemDiscountId;
	}

	public CtgDiscount getCtgDiscount() {
		return ctgDiscount;
	}

	public void setCtgDiscount(CtgDiscount ctgDiscount) {
		this.ctgDiscount = ctgDiscount;
	}

	public RestMenuItem getRestMenuItem() {
		return restMenuItem;
	}

	public void setRestMenuItem(RestMenuItem restMenuItem) {
		this.restMenuItem = restMenuItem;
	}

	@Transient
	private String discountMenuItemTypeText;
	@Transient
	private String discountMenuItemNameText;


	public String getDiscountMenuItemTypeText() {
		return discountMenuItemTypeText;
	}

	public void setDiscountMenuItemTypeText(String discountMenuItemTypeText) {
		this.discountMenuItemTypeText = discountMenuItemTypeText;
	}

	public String getDiscountMenuItemNameText() {
		return discountMenuItemNameText;
	}

	public void setDiscountMenuItemNameText(String discountMenuItemNameText) {
		this.discountMenuItemNameText = discountMenuItemNameText;
	}
	

}
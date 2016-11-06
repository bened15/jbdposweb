package com.jbd.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the rest_menu_item_type database table.
 *
 */
@Entity
@Table(name="rest_menu_item_type")
@NamedQuery(name="RestMenuItemType.findAll", query="SELECT r FROM RestMenuItemType r")
public class RestMenuItemType implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="MENU_ITEM_TYPE_ID")
	private int menuItemTypeId;

	private int available;

	@Column(name="MENU_ITEM_ID")
	private int menuItemId;

	@Column(name="MENU_TYPE_ID")
	private int menuTypeId;

	public RestMenuItemType() {
	}

	public int getMenuItemTypeId() {
		return this.menuItemTypeId;
	}

	public void setMenuItemTypeId(int menuItemTypeId) {
		this.menuItemTypeId = menuItemTypeId;
	}

	public int getAvailable() {
		return this.available;
	}

	public void setAvailable(int available) {
		this.available = available;
	}

	public int getMenuItemId() {
		return this.menuItemId;
	}

	public void setMenuItemId(int menuItemId) {
		this.menuItemId = menuItemId;
	}

	public int getMenuTypeId() {
		return this.menuTypeId;
	}

	public void setMenuTypeId(int menuTypeId) {
		this.menuTypeId = menuTypeId;
	}

}
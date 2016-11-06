package com.jbd.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the ctg_menu_type database table.
 *
 */
@Entity
@Table(name="ctg_menu_sub_type")
@NamedQuery(name="CtgMenuSubType.findAll", query="SELECT c FROM CtgMenuSubType c")
public class CtgMenuSubType implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="MENU_SUB_TYPE_ID")
	private int menuSubTypeId;

	@Column(name="MENU_TYPE_ID")
	private int menuTypeId;

	@Temporal(TemporalType.DATE)
	@Column(name="ENTRY_DATE")
	private Date entryDate;

	@Column(name="ENTRY_USER")
	private String entryUser;

	@Column(name="MENU_SUB_TYPE_DESCRIPTION")
	private String menuSubTypeDescription;

	@Column(name="MENU_SUB_TYPE_NAME")
	private String menuSubTypeName;

	//bi-directional many-to-one association to RestMenuItem
	@OneToMany(mappedBy="ctgMenuSubType")
	private List<RestMenuItem> restMenuItems;

	public CtgMenuSubType() {
	}

	public int getMenuSubTypeId() {
		return this.menuSubTypeId;
	}

	public void setMenuSubTypeId(int menuSubTypeId) {
		this.menuSubTypeId = menuSubTypeId;
	}

	public int getMenuTypeId() {
		return menuTypeId;
	}

	public void setMenuTypeId(int menuTypeId) {
		this.menuTypeId = menuTypeId;
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

	public String getMenuSubTypeDescription() {
		return this.menuSubTypeDescription;
	}

	public void setMenuSubTypeDescription(String menuSubTypeDescription) {
		this.menuSubTypeDescription = menuSubTypeDescription;
	}

	public String getMenuSubTypeName() {
		return this.menuSubTypeName;
	}

	public void setMenuSubTypeName(String menuSubTypeName) {
		this.menuSubTypeName = menuSubTypeName;
	}

	public List<RestMenuItem> getRestMenuItems() {
		return this.restMenuItems;
	}

	public void setRestMenuItems(List<RestMenuItem> restMenuItems) {
		this.restMenuItems = restMenuItems;
	}

	public RestMenuItem addRestMenuItem(RestMenuItem restMenuItem) {
		getRestMenuItems().add(restMenuItem);
		restMenuItem.setCtgMenuSubType(this);

		return restMenuItem;
	}

	public RestMenuItem removeRestMenuItem(RestMenuItem restMenuItem) {
		getRestMenuItems().remove(restMenuItem);
		restMenuItem.setCtgMenuSubType(null);

		return restMenuItem;
	}


	@Override
	public String toString(){
		return this.menuSubTypeId + " - "+ this.menuSubTypeName;
	}
	
}
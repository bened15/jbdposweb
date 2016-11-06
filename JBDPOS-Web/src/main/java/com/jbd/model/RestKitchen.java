package com.jbd.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the ctg_menu_type database table.
 *
 */
@Entity
@Table(name="rest_kitchen")
@NamedQuery(name="RestKitchen.findAll", query="SELECT c FROM RestKitchen c")
public class RestKitchen implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="KITCHEN_ID")
	private int kitchenId;

	

	@Column(name="KITCHEN_DESCRIPTION")
	private String kitchenDescription;

	@Column(name="KITCHEN_NAME")
	private String kitchenName;

	@Column(name="KITCHEN_PRINTER")
	private String kitchenPrinter;

	//bi-directional many-to-one association to RestMenuItem
	@OneToMany(mappedBy="restKitchen")
	private List<RestMenuItem> restMenuItems;

	public RestKitchen() {
	}

	public int getKitchenId() {
		return this.kitchenId;
	}

	public void setKitchenId(int kitchenId) {
		this.kitchenId = kitchenId;
	}

	
	public String getKitchenDescription() {
		return this.kitchenDescription;
	}

	public void setKitchenDescription(String kitchenDescription) {
		this.kitchenDescription = kitchenDescription;
	}

	public String getKitchenName() {
		return this.kitchenName;
	}

	public void setKitchenName(String kitchenName) {
		this.kitchenName = kitchenName;
	}

	public List<RestMenuItem> getRestMenuItems() {
		return this.restMenuItems;
	}

	public void setRestMenuItems(List<RestMenuItem> restMenuItems) {
		this.restMenuItems = restMenuItems;
	}

	public RestMenuItem addRestMenuItem(RestMenuItem restMenuItem) {
		getRestMenuItems().add(restMenuItem);
		restMenuItem.setRestKitchen(this);

		return restMenuItem;
	}

	public RestMenuItem removeRestMenuItem(RestMenuItem restMenuItem) {
		getRestMenuItems().remove(restMenuItem);
		restMenuItem.setRestKitchen(null);

		return restMenuItem;
	}

	

	public String getKitchenPrinter() {
		return kitchenPrinter;
	}

	public void setKitchenPrinter(String kitchenPrinter) {
		this.kitchenPrinter = kitchenPrinter;
	}

	@Override
	public String toString(){
		return this.kitchenId + " - "+ this.kitchenName;
	}
	
}
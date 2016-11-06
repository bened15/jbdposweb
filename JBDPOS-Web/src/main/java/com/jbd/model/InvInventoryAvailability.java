package com.jbd.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the inv_inventory_availability database table.
 *
 */
@Entity
@Table(name="inv_inventory_availability")
@NamedQuery(name="InvInventoryAvailability.findAll", query="SELECT i FROM InvInventoryAvailability i")
public class InvInventoryAvailability implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="ID_INVENTORY_AVAILABILITY")
	private int idInventoryAvailability;

	public InvInventoryAvailability() {
	}

	public int getIdInventoryAvailability() {
		return this.idInventoryAvailability;
	}

	public void setIdInventoryAvailability(int idInventoryAvailability) {
		this.idInventoryAvailability = idInventoryAvailability;
	}

}
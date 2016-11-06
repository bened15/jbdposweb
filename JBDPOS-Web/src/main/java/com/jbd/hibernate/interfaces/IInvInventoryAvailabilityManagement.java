package com.jbd.hibernate.interfaces;

import com.jbd.model.InvInventoryAvailability;

public interface IInvInventoryAvailabilityManagement {

	public InvInventoryAvailability insertInvInventoryAvailability(InvInventoryAvailability o);
	public void updateInvInventoryAvailability(InvInventoryAvailability o);
	public void deleteInvInventoryAvailability(InvInventoryAvailability o);
	public InvInventoryAvailability findInvInventoryAvailability(Integer oId);

}

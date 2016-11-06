package com.jbd.hibernate.interfaces;

import com.jbd.model.RestMenuItemType;

public interface IRestMenuItemTypeManagement {

	public RestMenuItemType insertRestMenuItemType(RestMenuItemType o);
	public void updateRestMenuItemType(RestMenuItemType o);
	public void deleteRestMenuItemType(RestMenuItemType o);
	public RestMenuItemType findRestMenuItemType(Integer oId);

}

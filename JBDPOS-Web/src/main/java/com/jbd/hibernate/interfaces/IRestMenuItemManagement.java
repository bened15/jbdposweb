package com.jbd.hibernate.interfaces;

import java.util.List;

import com.jbd.model.CtgMenuSubType;
import com.jbd.model.CtgMenuType;
import com.jbd.model.RestMenuItem;

public interface IRestMenuItemManagement {

	public RestMenuItem insertRestMenuItem(RestMenuItem o);

	public RestMenuItem updateRestMenuItem(RestMenuItem o);

	public void deleteRestMenuItem(RestMenuItem o);

	public RestMenuItem findRestMenuItem(Integer oId);

	public List<RestMenuItem> findMenuItemByTypeMenu(CtgMenuType typeMenu);

	public List<RestMenuItem> findAll();

	public List<RestMenuItem> findMenuItemByExample(String menuItemName, int menuItemTypeId);

	public List<RestMenuItem> findMenuItemByName(String nameMenuItem);

	public List<RestMenuItem> findMenuItemBySubTypeMenu(CtgMenuSubType typeSubMenu);

}

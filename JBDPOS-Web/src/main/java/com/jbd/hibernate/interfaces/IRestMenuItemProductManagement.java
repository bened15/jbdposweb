package com.jbd.hibernate.interfaces;

import java.util.List;

import com.jbd.model.RestMenuItem;
import com.jbd.model.RestMenuItemProduct;

public interface IRestMenuItemProductManagement {

	public RestMenuItemProduct insertRestMenuItemProduct(RestMenuItemProduct o);
	public RestMenuItemProduct updateRestMenuItemProduct(RestMenuItemProduct o);
	public boolean deleteRestMenuItemProduct(RestMenuItemProduct o);
	public RestMenuItemProduct findRestMenuItemProduct(Integer oId);
	public List<RestMenuItemProduct> findIngredientes(RestMenuItem menuItem);

}

package com.jbd.hibernate.interfaces;

import java.util.List;

import com.jbd.model.CtgDiscountMenu;

public interface ICtgDiscountMenuManagement {

	public CtgDiscountMenu insertCtgDiscountMenu(CtgDiscountMenu o);
	public CtgDiscountMenu updateCtgDiscountMenu(CtgDiscountMenu o);
	public boolean deleteCtgDiscountMenu(CtgDiscountMenu o);
	public CtgDiscountMenu findCtgDiscountMenu(Integer oId);
	public List<CtgDiscountMenu> findAll();
	public List<CtgDiscountMenu> findByDiscount(int DiscountId);

}

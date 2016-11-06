package com.jbd.hibernate.interfaces;

import java.util.Date;
import java.util.List;

import com.jbd.model.CtgDiscount;

public interface ICtgDiscountManagement {

	public CtgDiscount insertCtgDiscount(CtgDiscount o);
	public CtgDiscount updateCtgDiscount(CtgDiscount o);
	public void deleteCtgDiscount(CtgDiscount o);
	public CtgDiscount findCtgDiscount(Integer oId);
	public List<CtgDiscount> findAll();
	public List<CtgDiscount> findDiscountByExample(String discountName);
	public List<CtgDiscount> findDiscountByExample(String discountName, Date discountDate);
	public List<CtgDiscount> findDiscountByExample(Date discountDate);
	public Date convertToDate(Date dateValue, String timeValue);

}

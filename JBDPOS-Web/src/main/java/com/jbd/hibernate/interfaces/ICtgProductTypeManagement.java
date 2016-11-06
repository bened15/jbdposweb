package com.jbd.hibernate.interfaces;

import java.util.List;

import com.jbd.model.CtgProductType;

public interface ICtgProductTypeManagement {

	public CtgProductType insertCtgProductType(CtgProductType o);
	public CtgProductType updateCtgProductType(CtgProductType o);
	public void deleteCtgProductType(CtgProductType o);
	public CtgProductType findCtgProductType(Integer oId);
	public List<CtgProductType> findAll();
	public List<CtgProductType> findProductTypeByExample(String typeName);

}

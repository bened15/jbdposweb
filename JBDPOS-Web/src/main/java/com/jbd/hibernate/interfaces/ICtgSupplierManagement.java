package com.jbd.hibernate.interfaces;

import java.util.List;

import com.jbd.model.CtgSupplier;

public interface ICtgSupplierManagement {

	public CtgSupplier insertCtgSupplier(CtgSupplier o);
	public CtgSupplier updateCtgSupplier(CtgSupplier o);
	public void deleteCtgSupplier(CtgSupplier o);
	public CtgSupplier findCtgSupplier(Integer oId);
	public List<CtgSupplier> findAll();
	public List<CtgSupplier> findBySupplierExample(String supplierName, String contactName, String contactLastName);

}

package com.jbd.hibernate.interfaces;

import java.util.List;

import com.jbd.model.AdmCustomer;

public interface IAdmCustomerManagement {

	public AdmCustomer insertAdmCustomer(AdmCustomer o);
	public AdmCustomer updateAdmCustomer(AdmCustomer o);
	public void deleteAdmCustomer(AdmCustomer o);
	public AdmCustomer findAdmCustomer(Integer oId);
	public List<AdmCustomer> findAll();
	public List<AdmCustomer> findByCustomerExample(String customerName, String customerLastName);

}

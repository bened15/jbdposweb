package com.jbd.hibernate.interfaces;

import java.util.List;

import com.jbd.model.CtgPaymentMethod;

public interface ICtgPaymentMethodManagement {

	public CtgPaymentMethod insertCtgPaymentMethod(CtgPaymentMethod o);
	public CtgPaymentMethod updateCtgPaymentMethod(CtgPaymentMethod o);
	public void deleteCtgPaymentMethod(CtgPaymentMethod o);
	public CtgPaymentMethod findCtgPaymentMethod(String name);
	public CtgPaymentMethod findCtgPaymentMethod(Integer oId);
	public List<CtgPaymentMethod> findAll();
	public List<CtgPaymentMethod> findCtgPaymentMethodByExample(String name);

}

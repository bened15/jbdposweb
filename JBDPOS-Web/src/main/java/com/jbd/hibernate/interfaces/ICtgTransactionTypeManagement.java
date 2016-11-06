package com.jbd.hibernate.interfaces;

import java.util.List;

import com.jbd.model.CtgTransactionType;

public interface ICtgTransactionTypeManagement {

	public CtgTransactionType insertCtgTransactionType(CtgTransactionType o);
	public CtgTransactionType updateCtgTransactionType(CtgTransactionType o);
	public void deleteCtgTransactionType(CtgTransactionType o);
	public CtgTransactionType findCtgTransactionType(Integer oId);
	public List<CtgTransactionType> findbyAdittion(Integer oId);

}

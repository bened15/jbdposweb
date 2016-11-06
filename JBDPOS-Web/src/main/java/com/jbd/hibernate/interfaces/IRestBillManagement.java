package com.jbd.hibernate.interfaces;

import java.util.List;

import com.jbd.model.RestBill;
import com.jbd.model.RestTableAccount;

public interface IRestBillManagement {

	public RestBill insertRestBill(RestBill o);

	public void updateRestBill(RestBill o);

	public void deleteRestBill(RestBill o);

	public RestBill findRestBill(Integer oId);

	public List<RestBill> findBillsWithRestTableAccount(RestTableAccount account);

	public Double getTotalAccountFromTable(RestTableAccount ta);

	public Double getTotalAccountFromTableCheckingDiscount(RestTableAccount ta, RestBill rb);
	public Double getSubTotalAccountFromTable(RestTableAccount ta);
	public Double getTotalTipAccountFromTable(RestTableAccount ta);
	public Integer getMaxBillId();
	
	public List<RestBill> findOpenBillsWithRestTableAccount(RestTableAccount account);
	public void cuadrarCuentas(RestTableAccount rta);

}

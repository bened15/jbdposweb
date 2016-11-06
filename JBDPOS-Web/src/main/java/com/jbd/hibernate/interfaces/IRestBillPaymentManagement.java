package com.jbd.hibernate.interfaces;

import java.util.List;

import com.jbd.model.RestBill;
import com.jbd.model.RestBillPayment;
import com.jbd.model.RestTableAccount;

public interface IRestBillPaymentManagement {

	public RestBillPayment insertRestBillPayment(RestBillPayment o);
	public void updateRestBillPayment(RestBillPayment o);
	public void deleteRestBillPayment(RestBillPayment o);
	public List<RestBillPayment> findRestBillPayments(Integer billId) ;
	public boolean isAmmountPaymentEqualOrMoreThanAccount(double totalAccount,RestTableAccount tableAc);
	public Double sumPaymentsForBill(RestBill bill);
	public Double totalBillPending(RestBill bill);

}

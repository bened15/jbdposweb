package com.jbd.hibernate.interfaces;

import java.util.List;

import com.jbd.model.RestBill;
import com.jbd.model.RestBillDetail;
import com.jbd.model.RestOrder;
import com.jbd.model.RestTableAccount;

public interface IRestBillDetailManagement {

	public RestBillDetail insertRestBillDetail(RestBillDetail o);

	public void updateRestBillDetail(RestBillDetail o);

	public void deleteRestBillDetail(RestBillDetail o);

	public RestBillDetail findRestBillDetail(Integer oId);

	public RestBillDetail findRestBillDetailByOrder(RestOrder or);

	public List<RestBillDetail> findAllRestBillDetailFromTableAccount(RestTableAccount Taccount);

	public List<RestBillDetail> findAllRestBillDetailFromRestBill(RestBill bill);

	public List<RestOrder> findAllRestBillDetailFromRestBillForTable(RestBill bill);
	
	

}

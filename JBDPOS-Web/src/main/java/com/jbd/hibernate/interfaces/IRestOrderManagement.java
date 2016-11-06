package com.jbd.hibernate.interfaces;

import java.util.List;

import com.jbd.model.RestOrder;
import com.jbd.model.RestTableAccount;

public interface IRestOrderManagement {

	public RestOrder insertRestOrder(RestOrder o);

	public void updateRestOrder(RestOrder o);

	public void deleteRestOrder(RestOrder o);

	public RestOrder findRestOrder(Integer oId);

	public List<RestOrder> findAllRestOrdersFromTableAccount(RestTableAccount account);


}

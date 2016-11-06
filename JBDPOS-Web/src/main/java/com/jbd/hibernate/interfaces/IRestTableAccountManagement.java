package com.jbd.hibernate.interfaces;

import com.jbd.model.RestTable;
import com.jbd.model.RestTableAccount;

public interface IRestTableAccountManagement {

	public RestTableAccount insertRestTableAccount(RestTableAccount o);

	public void updateRestTableAccount(RestTableAccount o);

	public void deleteRestTableAccount(RestTableAccount o);

	public RestTableAccount findRestTableAccount(Integer oId);

	public boolean isRestTableAccountOpen(RestTable table);

	public RestTableAccount findRestTableAccountOpen(RestTable table);

}

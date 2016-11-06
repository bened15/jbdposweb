package com.jbd.hibernate.interfaces;

import com.jbd.model.RestShift;

public interface IRestShiftManagement {

	public RestShift insertRestShift(RestShift o);

	public void updateRestShift(RestShift o);

	public void deleteRestShift(RestShift o);

	public RestShift findRestShift(Integer oId);

	public RestShift alreadyExistShift();

	public void closeShift(int rShift, String closedBy);

}

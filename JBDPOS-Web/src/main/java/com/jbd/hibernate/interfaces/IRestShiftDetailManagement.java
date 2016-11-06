package com.jbd.hibernate.interfaces;

import com.jbd.model.RestShiftDetail;

public interface IRestShiftDetailManagement {

	public RestShiftDetail insertRestShiftDetail(RestShiftDetail o);
	public void updateRestShiftDetail(RestShiftDetail o);
	public void deleteRestShiftDetail(RestShiftDetail o);
	public RestShiftDetail findRestShiftDetail(Integer oId);

}

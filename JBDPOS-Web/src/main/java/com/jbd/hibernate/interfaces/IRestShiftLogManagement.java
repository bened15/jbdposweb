package com.jbd.hibernate.interfaces;

import com.jbd.model.RestShiftLog;

public interface IRestShiftLogManagement {

	public RestShiftLog insertRestShiftLog(RestShiftLog o);
	public void updateRestShiftLog(RestShiftLog o);
	public void deleteRestShiftLog(RestShiftLog o);
	public RestShiftLog findRestShiftLog(Integer oId);

}

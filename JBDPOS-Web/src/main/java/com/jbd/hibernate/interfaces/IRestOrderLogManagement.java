package com.jbd.hibernate.interfaces;

import com.jbd.model.RestOrderLog;

public interface IRestOrderLogManagement {

	public RestOrderLog insertRestOrderLog(RestOrderLog o);
	public void updateRestOrderLog(RestOrderLog o);
	public void deleteRestOrderLog(RestOrderLog o);
	public RestOrderLog findRestOrderLog(Integer oId);

}

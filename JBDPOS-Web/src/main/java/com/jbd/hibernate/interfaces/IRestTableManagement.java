package com.jbd.hibernate.interfaces;

import java.util.List;

import com.jbd.model.RestArea;
import com.jbd.model.RestTable;

public interface IRestTableManagement {

	public RestTable insertRestTable(RestTable o);

	public RestTable updateRestTable(RestTable o);

	public void deleteRestTable(RestTable o);

	public RestTable findRestTable(Integer oId);

	public List<RestTable> findTablesByArea(RestArea area);

	public List<RestTable> findAll();

	public List<RestTable> findTablesByExample(int areaId);
	public RestTable findRestTableByNameOrId(String tableName) ;


}

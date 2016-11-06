package com.jbd.hibernate.interfaces;

import java.util.List;

import com.jbd.model.RestArea;

public interface IRestAreaManagement {

	public RestArea insertRestArea(RestArea o);

	public RestArea updateRestArea(RestArea o);

	public void deleteRestArea(RestArea o);

	public RestArea findRestArea(Integer oId);

	public List<RestArea> getAllAreas();

	public List<RestArea> findAll();

	public List<RestArea> findAreaByExample(String areaName, int smokingArea);

}

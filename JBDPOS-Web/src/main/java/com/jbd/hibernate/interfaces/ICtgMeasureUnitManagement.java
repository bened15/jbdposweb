package com.jbd.hibernate.interfaces;

import java.util.List;

import com.jbd.model.CtgMeasureUnit;

public interface ICtgMeasureUnitManagement {

	public CtgMeasureUnit insertCtgMeasureUnit(CtgMeasureUnit o);
	public CtgMeasureUnit updateCtgMeasureUnit(CtgMeasureUnit o);
	public void deleteCtgMeasureUnit(CtgMeasureUnit o);
	public CtgMeasureUnit findCtgMeasureUnit(Integer oId);
	public List<CtgMeasureUnit> findAll();
	public List<CtgMeasureUnit> findMeasureByExample(String measureName, String measureUnit);

}

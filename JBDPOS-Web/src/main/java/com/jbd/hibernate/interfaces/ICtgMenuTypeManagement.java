package com.jbd.hibernate.interfaces;

import java.util.List;

import com.jbd.model.CtgMenuType;

public interface ICtgMenuTypeManagement {

	public CtgMenuType insertCtgMenuType(CtgMenuType o);

	public CtgMenuType updateCtgMenuType(CtgMenuType o);

	public boolean deleteCtgMenuType(CtgMenuType o);

	public CtgMenuType findCtgMenuType(Integer oId);

	public List<CtgMenuType> loadMenuType();

	public List<CtgMenuType> findAll();

	public List<CtgMenuType> findMenyTypeByExample(String name);

}

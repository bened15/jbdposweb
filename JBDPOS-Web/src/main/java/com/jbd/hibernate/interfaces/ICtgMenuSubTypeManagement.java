package com.jbd.hibernate.interfaces;

import java.util.List;

import com.jbd.model.CtgMenuSubType;
import com.jbd.model.CtgMenuType;

public interface ICtgMenuSubTypeManagement {

	public CtgMenuSubType insertCtgMenuSubType(CtgMenuSubType o);

	public CtgMenuSubType updateCtgMenuSubType(CtgMenuSubType o);

	public boolean deleteCtgMenuSubType(CtgMenuSubType o);

	public CtgMenuSubType findCtgMenuSubType(Integer oId);

	public List<CtgMenuSubType> loadMenuType();

	public List<CtgMenuSubType> findAll();

	public List<CtgMenuSubType> findByMenuType(CtgMenuType menuType);

	public List<CtgMenuSubType> findMenyTypeByExample(String name);

	public List<CtgMenuSubType> findAllByType(CtgMenuType type);

}

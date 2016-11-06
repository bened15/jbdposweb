package com.jbd.hibernate.interfaces;

import com.jbd.model.CtgMenuLanguage;

public interface ICtgMenuLanguageManagement {

	public CtgMenuLanguage insertCtgMenuLanguage(CtgMenuLanguage o);
	public void updateCtgMenuLanguage(CtgMenuLanguage o);
	public void deleteCtgMenuLanguage(CtgMenuLanguage o);
	public CtgMenuLanguage findCtgMenuLanguage(Integer oId);

}

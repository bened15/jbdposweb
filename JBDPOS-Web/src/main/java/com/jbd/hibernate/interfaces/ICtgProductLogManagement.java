package com.jbd.hibernate.interfaces;

import com.jbd.model.CtgProductLog;

public interface ICtgProductLogManagement {

	public CtgProductLog insertCtgProductLog(CtgProductLog o);
	public void updateCtgProductLog(CtgProductLog o);
	public void deleteCtgProductLog(CtgProductLog o);
	public CtgProductLog findCtgProductLog(Integer oId);

}

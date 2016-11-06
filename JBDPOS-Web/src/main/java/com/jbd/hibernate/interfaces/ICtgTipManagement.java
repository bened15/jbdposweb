package com.jbd.hibernate.interfaces;

import java.util.List;

import com.jbd.model.CtgTip;

public interface ICtgTipManagement {

	public CtgTip insertCtgTip(CtgTip o);
	public CtgTip updateCtgTip(CtgTip o);
	public boolean deleteCtgTip(CtgTip o);
	public CtgTip findCtgTip(Integer oId);

	public CtgTip findDefaultCtgTip();
	public List<CtgTip> findAll();
	public List<CtgTip> findByDiscount(int tipId);
	public CtgTip findZeroCtgTip() ;


}

package com.jbd.hibernate.interfaces;

import java.util.List;

import com.jbd.model.InvProductItem;

public interface IInvProductItemManagement {

	public InvProductItem insertInvProductItem(InvProductItem o);
	public InvProductItem updateInvProductItem(InvProductItem o);
	public void deleteInvProductItem(InvProductItem o);
	public InvProductItem findInvProductItem(Integer oId);
	public List<InvProductItem> findAll();
	public List<InvProductItem> findInvProductItemByExample(int productTypeId, int productId);
	public List<InvProductItem> findInvProductItemByExampleOpen(int productTypeId, int productId);
	public List<InvProductItem> findAllOpen();

}

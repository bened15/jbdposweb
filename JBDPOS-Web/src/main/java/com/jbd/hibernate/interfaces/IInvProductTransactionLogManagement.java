package com.jbd.hibernate.interfaces;

import com.jbd.model.InvProductTransactionLog;

public interface IInvProductTransactionLogManagement {

	public InvProductTransactionLog insertInvProductTransactionLog(InvProductTransactionLog o);
	public void updateInvProductTransactionLog(InvProductTransactionLog o);
	public void deleteInvProductTransactionLog(InvProductTransactionLog o);
	public InvProductTransactionLog findInvProductTransactionLog(Integer oId);

}

package com.jbd.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the ctg_transaction_type database table.
 *
 */
@Entity
@Table(name="ctg_transaction_type")
@NamedQuery(name="CtgTransactionType.findAll", query="SELECT c FROM CtgTransactionType c")
public class CtgTransactionType implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="TRANSACTION_TYPE_ID")
	private String transactionTypeId;

	@Column(name="TRANSACTION_TYPE_DESC")
	private String transactionTypeDesc;

	@Column(name="TRANSACTION_TYPE_NAME")
	private String transactionTypeName;

	@Column(name="TRANSACTION_ADDITION")
	private int transactionAddition;

	public CtgTransactionType() {
	}

	public String getTransactionTypeId() {
		return this.transactionTypeId;
	}

	public void setTransactionTypeId(String transactionTypeId) {
		this.transactionTypeId = transactionTypeId;
	}

	public String getTransactionTypeDesc() {
		return this.transactionTypeDesc;
	}

	public void setTransactionTypeDesc(String transactionTypeDesc) {
		this.transactionTypeDesc = transactionTypeDesc;
	}

	public String getTransactionTypeName() {
		return this.transactionTypeName;
	}

	public void setTransactionTypeName(String transactionTypeName) {
		this.transactionTypeName = transactionTypeName;
	}
	
	
	public int getTransactionAddition() {
		return transactionAddition;
	}

	public void setTransactionAddition(int transactionAddition) {
		this.transactionAddition = transactionAddition;
	}

	@Override
	public String toString(){
		return this.transactionTypeId + " - " +this.transactionTypeName;
	}
}
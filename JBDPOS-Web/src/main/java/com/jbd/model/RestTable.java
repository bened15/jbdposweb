package com.jbd.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;

/**
 * The persistent class for the rest_table database table.
 *
 */
@Entity
@Table(name = "rest_table")
@NamedQuery(name = "RestTable.findAll", query = "SELECT r FROM RestTable r")
public class RestTable implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "TABLE_ID")
	private int tableId;

	@ManyToOne
	@JoinColumn(name = "AREA_ID")
	private RestArea restArea;

	@Column(name = "CHAIR_AMMOUNT")
	private int chairAmmount;

	@Column(name = "TABLE_NAME")
	private String tableName;

	private String status;
	
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	// bi-directional many-to-one association to RestTableAccount
	@OneToMany(mappedBy = "restTable")
	private List<RestTableAccount> restTableAccounts;

	public RestTable() {
	}

	public int getTableId() {
		return this.tableId;
	}

	public void setTableId(int tableId) {
		this.tableId = tableId;
	}

	public RestArea getRestArea() {
		return restArea;
	}

	public void setRestArea(RestArea restArea) {
		this.restArea = restArea;
	}

	public int getChairAmmount() {
		return this.chairAmmount;
	}

	public void setChairAmmount(int chairAmmount) {
		this.chairAmmount = chairAmmount;
	}

	public String getTableName() {
		return this.tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public List<RestTableAccount> getRestTableAccounts() {
		return this.restTableAccounts;
	}

	public void setRestTableAccounts(List<RestTableAccount> restTableAccounts) {
		this.restTableAccounts = restTableAccounts;
	}

	public RestTableAccount addRestTableAccount(RestTableAccount restTableAccount) {
		getRestTableAccounts().add(restTableAccount);
		restTableAccount.setRestTable(this);

		return restTableAccount;
	}

	public RestTableAccount removeRestTableAccount(RestTableAccount restTableAccount) {
		getRestTableAccounts().remove(restTableAccount);
		restTableAccount.setRestTable(null);

		return restTableAccount;
	}
	
	@Transient
	private String tableAreaName;

	public String getTableAreaName() {
		return tableAreaName;
	}

	public void setTableAreaName(String tableAreaName) {
		this.tableAreaName = tableAreaName;
	}
	
	
	

}
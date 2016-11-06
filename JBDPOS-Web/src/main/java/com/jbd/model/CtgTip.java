package com.jbd.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;

/**
 * The persistent class for the ctg_tip database table.
 *
 */
@Entity
@Table(name = "ctg_tip")
@NamedQuery(name = "CtgTip.findAll", query = "SELECT c FROM CtgTip c")
public class CtgTip implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id_ctg_tip")
	private int idCtgTip;

	@Column(name = "by_default")
	private String byDefault;

	private String description;

	@Column(name = "tip_name")
	private String tipName;

	@Column(name = "percent_value")
	private int percentValue;

	// bi-directional many-to-one association to RestBill
	@OneToMany(mappedBy = "ctgTip")
	private List<RestBill> restBills;

	public CtgTip() {
	}

	public CtgTip(int id) {
		this.idCtgTip = id;
	}

	public int getIdCtgTip() {
		return this.idCtgTip;
	}

	public void setIdCtgTip(int idCtgTip) {
		this.idCtgTip = idCtgTip;
	}

	public String getByDefault() {
		return this.byDefault;
	}

	public void setByDefault(String byDefault) {
		this.byDefault = byDefault;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getTipName() {
		return this.tipName;
	}

	public void setTipName(String tipName) {
		this.tipName = tipName;
	}

	public int getPercentValue() {
		return this.percentValue;
	}

	public void setPercentValue(int percentValue) {
		this.percentValue = percentValue;
	}

	public List<RestBill> getRestBills() {
		return this.restBills;
	}

	public void setRestBills(List<RestBill> restBills) {
		this.restBills = restBills;
	}

	public RestBill addRestBill(RestBill restBill) {
		getRestBills().add(restBill);
		restBill.setCtgTip(this);

		return restBill;
	}

	public RestBill removeRestBill(RestBill restBill) {
		getRestBills().remove(restBill);
		restBill.setCtgTip(null);

		return restBill;
	}

}
package com.jbd.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the rest_area database table.
 *
 */
@Entity
@Table(name="rest_area")
@NamedQuery(name="RestArea.findAll", query="SELECT r FROM RestArea r")
public class RestArea implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="AREA_ID")
	private int areaId;

	@Column(name="AREA_NAME")
	private String areaName;

	@Temporal(TemporalType.DATE)
	@Column(name="ENTRY_DATE")
	private Date entryDate;

	@Column(name="ENTRY_USER")
	private String entryUser;

	@Column(name="IS_SMOKER_AREA")
	private Integer isSmokerArea;
	
	private String type;
	

	public RestArea() {
	}

	public int getAreaId() {
		return this.areaId;
	}

	public void setAreaId(int areaId) {
		this.areaId = areaId;
	}

	public String getAreaName() {
		return this.areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}

	public Date getEntryDate() {
		return this.entryDate;
	}

	public void setEntryDate(Date entryDate) {
		this.entryDate = entryDate;
	}

	public String getEntryUser() {
		return this.entryUser;
	}

	public void setEntryUser(String entryUser) {
		this.entryUser = entryUser;
	}

	public Integer getIsSmokerArea() {
		return this.isSmokerArea;
	}

	public void setIsSmokerArea(Integer isSmokerArea) {
		this.isSmokerArea = isSmokerArea;
	}
	

	@Transient
	private String isSmokerAreaText;

	public String getIsSmokerAreaText() {
		return isSmokerAreaText;
	}

	public void setIsSmokerAreaText(String isSmokerAreaText) {
		this.isSmokerAreaText = isSmokerAreaText;
	}
	
	@Override
	public String toString(){
		return this.areaId + " - " + this.areaName;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
}
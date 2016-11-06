package com.jbd.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the rest_information database table.
 *
 */
@Entity
@Table(name="rest_information")
@NamedQuery(name="RestInformation.findAll", query="SELECT r FROM RestInformation r")
public class RestInformation implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="INFORMATION_ID")
	private int informationId;

	@Column(name="INF_ACTIVE")
	private int infActive;

	@Column(name="REST_NAME")
	private String restName;

	@Column(name="REST_ADDRESS")
	private String restAddress;

	@Column(name="REST_EMAIL")
	private String restEmail;

	@Column(name="REST_LOGO_IMAGE")
	private String restLogoImage;

	@Column(name="REST_PHONE1")
	private String restPhone1;

	@Column(name="REST_PHONE2")
	private String restPhone2;

	@Column(name="REST_NIT")
	private String restNIT;
	
	public RestInformation() {
	}

	public int getInformationId() {
		return this.informationId;
	}

	public void setInformationId(int informationId) {
		this.informationId = informationId;
	}

	public int getInfActive() {
		return this.infActive;
	}

	public void setInfActive(int infActive) {
		this.infActive = infActive;
	}

	public String getRestName() {
		return this.restName;
	}

	public void setRestName(String restName) {
		this.restName = restName;
	}

	public String getRestAddress() {
		return this.restAddress;
	}

	public void setRestAddress(String restAddress) {
		this.restAddress = restAddress;
	}

	public String getRestEmail() {
		return this.restEmail;
	}

	public void setRestEmail(String restEmail) {
		this.restEmail = restEmail;
	}

	public String getRestLogoImage() {
		return this.restLogoImage;
	}

	public void setRestLogoImage(String restLogoImage) {
		this.restLogoImage = restLogoImage;
	}

	public String getRestPhone1() {
		return this.restPhone1;
	}

	public void setRestPhone1(String restPhone1) {
		this.restPhone1 = restPhone1;
	}

	public String getRestPhone2() {
		return this.restPhone2;
	}

	public void setRestPhone2(String restPhone2) {
		this.restPhone2 = restPhone2;
	}

	public String getRestNIT() {
		return restNIT;
	}

	public void setRestNIT(String restNIT) {
		this.restNIT = restNIT;
	}

	
}
package com.jbd.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the ctg_menu_language database table.
 *
 */
@Entity
@Table(name="ctg_menu_language")
@NamedQuery(name="CtgMenuLanguage.findAll", query="SELECT c FROM CtgMenuLanguage c")
public class CtgMenuLanguage implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="MENU_LANGUAE_ID")
	private int menuLanguaeId;

	@Temporal(TemporalType.DATE)
	@Column(name="ENTRY_DATE")
	private Date entryDate;

	@Column(name="ENTRY_USER")
	private String entryUser;

	@Column(name="LANGUAGE_ISO2")
	private String languageIso2;

	@Column(name="LANGUAGE_NAME")
	private String languageName;

	public CtgMenuLanguage() {
	}

	public int getMenuLanguaeId() {
		return this.menuLanguaeId;
	}

	public void setMenuLanguaeId(int menuLanguaeId) {
		this.menuLanguaeId = menuLanguaeId;
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

	public String getLanguageIso2() {
		return this.languageIso2;
	}

	public void setLanguageIso2(String languageIso2) {
		this.languageIso2 = languageIso2;
	}

	public String getLanguageName() {
		return this.languageName;
	}

	public void setLanguageName(String languageName) {
		this.languageName = languageName;
	}

}
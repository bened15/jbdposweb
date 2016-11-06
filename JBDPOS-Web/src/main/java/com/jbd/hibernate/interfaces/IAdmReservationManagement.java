package com.jbd.hibernate.interfaces;

import java.util.Date;
import java.util.List;

import com.jbd.model.AdmReservation;

public interface IAdmReservationManagement {

	public AdmReservation insertAdmReservation(AdmReservation o);
	public AdmReservation updateAdmReservation(AdmReservation o);
	public void deleteAdmReservation(AdmReservation o);
	public AdmReservation findAdmReservation(Integer oId);
	public List<AdmReservation> findAll();
	public List<AdmReservation> findByReservationExample(String name, String lastName, Date reservation);

}

package com.jbd.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the rest_shift_detail database table.
 *
 */
@Entity
@Table(name="rest_shift_detail")
@NamedQuery(name="RestShiftDetail.findAll", query="SELECT r FROM RestShiftDetail r")
public class RestShiftDetail implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="ID_SHIFT_DETAIL")
	private int idShiftDetail;

	private float amount;

	@Column(name="ID_PAYMENT_METHOD")
	private int idPaymentMethod;

	public RestShiftDetail() {
	}

	public int getIdShiftDetail() {
		return this.idShiftDetail;
	}

	public void setIdShiftDetail(int idShiftDetail) {
		this.idShiftDetail = idShiftDetail;
	}

	public float getAmount() {
		return this.amount;
	}

	public void setAmount(float amount) {
		this.amount = amount;
	}

	public int getIdPaymentMethod() {
		return this.idPaymentMethod;
	}

	public void setIdPaymentMethod(int idPaymentMethod) {
		this.idPaymentMethod = idPaymentMethod;
	}

}
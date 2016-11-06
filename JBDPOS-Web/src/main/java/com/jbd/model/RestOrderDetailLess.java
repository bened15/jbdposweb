package com.jbd.model;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The persistent class for the rest_order_detail_less database table.
 *
 */
@Entity
@Table(name = "rest_order_detail_less")
@NamedQuery(name = "RestOrderDetailLess.findAll", query = "SELECT r FROM RestOrderDetailLess r")
public class RestOrderDetailLess implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_rest_order_detail_less")
	private int idRestOrderDetailLess;

	private double quantity;

	// bi-directional many-to-one association to RestTableAccount
	@ManyToOne
	@JoinColumn(name = "table_account_id")
	private RestTableAccount restTableAccount;

	// bi-directional many-to-one association to RestProduct
	@ManyToOne
	@JoinColumn(name = "id_product")
	private RestProduct restProduct;

	public RestOrderDetailLess() {
	}

	public int getIdRestOrderDetailLess() {
		return this.idRestOrderDetailLess;
	}

	public void setIdRestOrderDetailLess(int idRestOrderDetailLess) {
		this.idRestOrderDetailLess = idRestOrderDetailLess;
	}

	public double getQuantity() {
		return this.quantity;
	}

	public void setQuantity(double quantity) {
		this.quantity = quantity;
	}

	public RestTableAccount getRestTableAccount() {
		return this.restTableAccount;
	}

	public void setRestTableAccount(RestTableAccount restTableAccount) {
		this.restTableAccount = restTableAccount;
	}

	public RestProduct getRestProduct() {
		return this.restProduct;
	}

	public void setRestProduct(RestProduct restProduct) {
		this.restProduct = restProduct;
	}

}
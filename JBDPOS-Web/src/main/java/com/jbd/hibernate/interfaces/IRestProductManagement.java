package com.jbd.hibernate.interfaces;

import java.util.List;

import com.jbd.model.RestProduct;

public interface IRestProductManagement {

	public RestProduct insertRestProduct(RestProduct o);

	public RestProduct updateRestProduct(RestProduct o);

	public void deleteRestProduct(RestProduct o);

	public RestProduct findRestProduct(Integer oId);

	public List<RestProduct> findAll();

	public List<RestProduct> findByProductType(int productTypeId);

	public List<RestProduct> findProductByExample(String productName, int productTypeId, int productSupplierId);

}

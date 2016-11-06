package com.jbd.hibernate.interfaces;

import java.util.List;

import com.jbd.model.RestKitchen;

public interface IRestKitchenManagement {

	public RestKitchen insertRestKitchen(RestKitchen o);

	public RestKitchen updateRestKitchen(RestKitchen o);

	public void deleteRestKitchen(RestKitchen o);

	public RestKitchen findRestKitchen(Integer oId);


	public List<RestKitchen> findAll();

	public List<RestKitchen> findMenyTypeByExample(String name);

}

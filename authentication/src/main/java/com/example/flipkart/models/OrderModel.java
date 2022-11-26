package com.example.flipkart.models;

import java.util.ArrayList;
import java.util.List;

public class OrderModel {
	private String name;
	private String email;
	List<CustomerModel> customers = new ArrayList<>();
	private String mealType;
	private String tripType;

	public OrderModel() {
	}

	public OrderModel(String name, String email, List<CustomerModel> customers, String mealType, String tripType) {
		this.name = name;
		this.email = email;
		this.customers = customers;
		this.mealType = mealType;
		this.tripType = tripType;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<CustomerModel> getCustomers() {
		return customers;
	}

	public void setCustomers(List<CustomerModel> customers) {
		this.customers = customers;
	}

	public String getMealType() {
		return mealType;
	}

	public void setMealType(String mealType) {
		this.mealType = mealType;
	}

	public String getTripType() {
		return tripType;
	}

	public void setTripType(String tripType) {
		this.tripType = tripType;
	}

	@Override
	public String toString() {
		return "OrderModel{" +
				"name='" + name + '\'' +
				", email='" + email + '\'' +
				", customers=" + customers +
				", mealType='" + mealType + '\'' +
				", tripType='" + tripType + '\'' +
				'}';
	}
}

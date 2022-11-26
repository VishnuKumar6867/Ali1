package com.example.flipkart.models;

import java.time.LocalDateTime;

public class SearchModel {

	private LocalDateTime date;
	//private String fromPlace;
	//	private String toPlace;
	private String features;
	private  int quantity;

	public SearchModel(LocalDateTime date, String features, int quantity) {
		this.date = date;
		this.features = features;
		this.quantity = quantity;
	}

	public SearchModel() {
	}

	public LocalDateTime getDate() {
		return date;
	}

	public void setDate(LocalDateTime date) {
		this.date = date;
	}

	public String getFeatures() {
		return features;
	}

	public void setFeatures(String features) {
		this.features = features;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}




}

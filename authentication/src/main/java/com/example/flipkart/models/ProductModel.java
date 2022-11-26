package com.example.flipkart.models;

public class ProductModel {
	
	private String productName;
	private String contactNumber;
	private String contactAddress;

	public ProductModel() {
	}

	public ProductModel(String productName, String contactNumber, String contactAddress) {
		this.productName = productName;
		this.contactNumber = contactNumber;
		this.contactAddress = contactAddress;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getContactNumber() {
		return contactNumber;
	}

	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}

	public String getContactAddress() {
		return contactAddress;
	}

	public void setContactAddress(String contactAddress) {
		this.contactAddress = contactAddress;
	}

	@Override
	public String toString() {
		return "ProductModel{" +
				"productName='" + productName + '\'' +
				", contactNumber='" + contactNumber + '\'' +
				", contactAddress='" + contactAddress + '\'' +
				'}';
	}
}

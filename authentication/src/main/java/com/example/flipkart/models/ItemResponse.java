package com.example.flipkart.models;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;

public class ItemResponse {
    private long itemId;
    private long itemNumber;
    private String productName;
    @ManyToOne
    @JoinColumn(name = "productId")
    private ProductResponse product;
    //private String fromPlace;
    //private String toPlace;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private int quantity;
    // private int economySeats;
    private double itemPrice;
    // private int rows;
    // private String mealType;
    private String features;

    public ItemResponse() {
    }

    @Override
    public String toString() {
        return "ItemResponse{" +
                "itemId=" + itemId +
                ", itemNumber=" + itemNumber +
                ", productName='" + productName + '\'' +
                ", product=" + product +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", quantity=" + quantity +
                ", itemPrice=" + itemPrice +
                ", features='" + features + '\'' +
                '}';
    }

    public long getItemId() {
        return itemId;
    }

    public void setItemId(long itemId) {
        this.itemId = itemId;
    }

    public long getItemNumber() {
        return itemNumber;
    }

    public void setItemNumber(long itemNumber) {
        this.itemNumber = itemNumber;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public ProductResponse getProduct() {
        return product;
    }

    public void setProduct(ProductResponse product) {
        this.product = product;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(double itemPrice) {
        this.itemPrice = itemPrice;
    }

    public String getFeatures() {
        return features;
    }

    public void setFeatures(String features) {
        this.features = features;
    }

    public ItemResponse(long itemId, long itemNumber, String productName, ProductResponse product, LocalDateTime startDate, LocalDateTime endDate, int quantity, double itemPrice, String features) {
        this.itemId = itemId;
        this.itemNumber = itemNumber;
        this.productName = productName;
        this.product = product;
        this.startDate = startDate;
        this.endDate = endDate;
        this.quantity = quantity;
        this.itemPrice = itemPrice;
        this.features = features;
    }
}

package com.marom.restfulwebserivces.shop;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Date;

@JsonIgnoreProperties(value = {"product"})
public class Order {

    private String product = null;
    private Integer quantity = null;

    @JsonIgnore
    private Date shipDate = null;

    public Order(String product, Integer quantity, Date shipDate) {
        this.product = product;
        this.quantity = quantity;
        this.shipDate = shipDate;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Date getShipDate() {
        return shipDate;
    }

    public void setShipDate(Date shipDate) {
        this.shipDate = shipDate;
    }
}


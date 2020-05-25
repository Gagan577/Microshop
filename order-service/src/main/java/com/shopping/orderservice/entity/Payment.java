package com.shopping.orderservice.entity;

import java.util.Set;

public class Payment {

    private String id;
    private double totalCharge;
    private String customerId;
    private Set<String> productIds;

    public Payment() {}

    public Payment(final String id, final double totalCharge, final String customerId, final Set<String> productIds) {
        this.id = id;
        this.totalCharge = totalCharge;
        this.customerId = customerId;
        this.productIds = productIds;
    }

    public String getId() {
        return id;
    }

    public void setId(final String id) {
        this.id = id;
    }

    public double getTotalCharge() {
        return totalCharge;
    }

    public void setTotalCharge(final double totalCharge) {
        this.totalCharge = totalCharge;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(final String customerId) {
        this.customerId = customerId;
    }

    public Set<String> getProductIds() {
        return productIds;
    }

    public void setProductIds(final Set<String> productIds) {
        this.productIds = productIds;
    }
}

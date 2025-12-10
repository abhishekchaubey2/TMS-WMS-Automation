package com.delhivery.TMS_WMS.pojo.wmsorder.request;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Order Line POJO for WMS Order Creation
 */
public class OrderLine {
    
    @JsonProperty("number")
    private String number;
    
    @JsonProperty("product_sku")
    private String productSku;
    
    @JsonProperty("quantity")
    private Integer quantity;
    
    @JsonProperty("client_id")
    private String clientId;
    
    @JsonProperty("bucket")
    private String bucket;
    
    public OrderLine() {
    }
    
    public OrderLine(String number, String productSku, Integer quantity, String clientId, String bucket) {
        this.number = number;
        this.productSku = productSku;
        this.quantity = quantity;
        this.clientId = clientId;
        this.bucket = bucket;
    }
    
    // Getters and Setters
    public String getNumber() { return number; }
    public void setNumber(String number) { this.number = number; }
    
    public String getProductSku() { return productSku; }
    public void setProductSku(String productSku) { this.productSku = productSku; }
    
    public Integer getQuantity() { return quantity; }
    public void setQuantity(Integer quantity) { this.quantity = quantity; }
    
    public String getClientId() { return clientId; }
    public void setClientId(String clientId) { this.clientId = clientId; }
    
    public String getBucket() { return bucket; }
    public void setBucket(String bucket) { this.bucket = bucket; }
}

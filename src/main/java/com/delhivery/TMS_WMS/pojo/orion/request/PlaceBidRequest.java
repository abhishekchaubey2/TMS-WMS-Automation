package com.delhivery.TMS_WMS.pojo.orion.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Request POJO for Orion Place Bid API
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class PlaceBidRequest {
    
    @JsonProperty("transaction_id")
    private String transactionId;
    
    @JsonProperty("supplier_id")
    private String supplierId;
    
    @JsonProperty("supplier_name")
    private String supplierName;
    
    @JsonProperty("bid_price")
    private Integer bidPrice;
    
    @JsonProperty("bidding_type")
    private String biddingType;
    
    @JsonProperty("originator")
    private String originator;
    
    public PlaceBidRequest() {
    }
    
    public PlaceBidRequest(String transactionId, String supplierId, String supplierName, 
                          Integer bidPrice, String biddingType, String originator) {
        this.transactionId = transactionId;
        this.supplierId = supplierId;
        this.supplierName = supplierName;
        this.bidPrice = bidPrice;
        this.biddingType = biddingType;
        this.originator = originator;
    }
    
    public String getTransactionId() {
        return transactionId;
    }
    
    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }
    
    public String getSupplierId() {
        return supplierId;
    }
    
    public void setSupplierId(String supplierId) {
        this.supplierId = supplierId;
    }
    
    public String getSupplierName() {
        return supplierName;
    }
    
    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }
    
    public Integer getBidPrice() {
        return bidPrice;
    }
    
    public void setBidPrice(Integer bidPrice) {
        this.bidPrice = bidPrice;
    }
    
    public String getBiddingType() {
        return biddingType;
    }
    
    public void setBiddingType(String biddingType) {
        this.biddingType = biddingType;
    }
    
    public String getOriginator() {
        return originator;
    }
    
    public void setOriginator(String originator) {
        this.originator = originator;
    }
}


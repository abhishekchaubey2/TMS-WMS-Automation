package com.delhivery.TMS_WMS.pojo.orion.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Response POJO for Orion Place Bid API
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class PlaceBidResponse {
    
    @JsonProperty("success")
    private Boolean success;
    
    @JsonProperty("message")
    private String message;
    
    @JsonProperty("data")
    private BidData data;
    
    public Boolean getSuccess() {
        return success;
    }
    
    public void setSuccess(Boolean success) {
        this.success = success;
    }
    
    public String getMessage() {
        return message;
    }
    
    public void setMessage(String message) {
        this.message = message;
    }
    
    public BidData getData() {
        return data;
    }
    
    public void setData(BidData data) {
        this.data = data;
    }
    
    /**
     * Inner class for bid data
     */
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class BidData {
        @JsonProperty("id")
        private String id;
        
        @JsonProperty("transaction_id")
        private String transactionId;
        
        @JsonProperty("message")
        private String message;
        
        public String getId() {
            return id;
        }
        
        public void setId(String id) {
            this.id = id;
        }
        
        public String getTransactionId() {
            return transactionId;
        }
        
        public void setTransactionId(String transactionId) {
            this.transactionId = transactionId;
        }
        
        public String getMessage() {
            return message;
        }
        
        public void setMessage(String message) {
            this.message = message;
        }
        
        /**
         * Getter for bid_id (alias for id)
         */
        public String getBidId() {
            return id;
        }
    }
}


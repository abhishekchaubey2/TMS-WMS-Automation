package com.delhivery.TMS_WMS.pojo.orion.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

/**
 * Response POJO for Orion Get Trip API
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class GetTripResponse {
    
    @JsonProperty("success")
    private Boolean success;
    
    @JsonProperty("data")
    private TripData data;
    
    public Boolean getSuccess() {
        return success;
    }
    
    public void setSuccess(Boolean success) {
        this.success = success;
    }
    
    public TripData getData() {
        return data;
    }
    
    public void setData(TripData data) {
        this.data = data;
    }
    
    /**
     * Get transaction ID from the first result
     */
    public String getTransactionId() {
        if (data != null && data.getResults() != null && !data.getResults().isEmpty()) {
            TripResult firstResult = data.getResults().get(0);
            return firstResult != null ? firstResult.getTransactionId() : null;
        }
        return null;
    }
    
    /**
     * Inner class for trip data
     */
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class TripData {
        @JsonProperty("count")
        private Integer count;
        
        @JsonProperty("has_next")
        private Boolean hasNext;
        
        @JsonProperty("has_prev")
        private Boolean hasPrev;
        
        @JsonProperty("results")
        private List<TripResult> results;
        
        public Integer getCount() {
            return count;
        }
        
        public void setCount(Integer count) {
            this.count = count;
        }
        
        public Boolean getHasNext() {
            return hasNext;
        }
        
        public void setHasNext(Boolean hasNext) {
            this.hasNext = hasNext;
        }
        
        public Boolean getHasPrev() {
            return hasPrev;
        }
        
        public void setHasPrev(Boolean hasPrev) {
            this.hasPrev = hasPrev;
        }
        
        public List<TripResult> getResults() {
            return results;
        }
        
        public void setResults(List<TripResult> results) {
            this.results = results;
        }
    }
    
    /**
     * Inner class for trip result
     */
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class TripResult {
        @JsonProperty("transaction_id")
        private String transactionId;
        
        @JsonProperty("trip_status")
        private String tripStatus;
        
        @JsonProperty("origin")
        private String origin;
        
        @JsonProperty("destination")
        private String destination;
        
        @JsonProperty("commercial_type")
        private String commercialType;
        
        // Getters and Setters
        public String getTransactionId() {
            return transactionId;
        }
        
        public void setTransactionId(String transactionId) {
            this.transactionId = transactionId;
        }
        
        public String getTripStatus() {
            return tripStatus;
        }
        
        public void setTripStatus(String tripStatus) {
            this.tripStatus = tripStatus;
        }
        
        public String getOrigin() {
            return origin;
        }
        
        public void setOrigin(String origin) {
            this.origin = origin;
        }
        
        public String getDestination() {
            return destination;
        }
        
        public void setDestination(String destination) {
            this.destination = destination;
        }
        
        public String getCommercialType() {
            return commercialType;
        }
        
        public void setCommercialType(String commercialType) {
            this.commercialType = commercialType;
        }
    }
}


package com.delhivery.TMS.pojo.put_api_contracts.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Builder
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RateDetails {
    
    @JsonProperty("rate")
    private Double rate;
    
    @JsonProperty("rateType")
    private String rateType;
    
    // Manual constructors since Lombok is not working
    public RateDetails() {}
    
    public RateDetails(Double rate, String rateType) {
        this.rate = rate;
        this.rateType = rateType;
    }
    
    // Manual builder method since Lombok is not working
    public static RateDetailsBuilder builder() {
        return new RateDetailsBuilder();
    }
    
    public static class RateDetailsBuilder {
        private Double rate;
        private String rateType;
        
        public RateDetailsBuilder rate(Double rate) {
            this.rate = rate;
            return this;
        }
        
        public RateDetailsBuilder rateType(String rateType) {
            this.rateType = rateType;
            return this;
        }
        
        public RateDetails build() {
            return new RateDetails(rate, rateType);
        }
    }
} 
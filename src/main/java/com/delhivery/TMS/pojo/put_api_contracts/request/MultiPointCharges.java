package com.delhivery.TMS.pojo.put_api_contracts.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Builder
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MultiPointCharges {
    
    @JsonProperty("chargesType")
    private String chargesType;
    
    @JsonProperty("chargesAmount")
    private Integer chargesAmount;
    
    // Manual constructors since Lombok is not working
    public MultiPointCharges() {}
    
    public MultiPointCharges(String chargesType, Integer chargesAmount) {
        this.chargesType = chargesType;
        this.chargesAmount = chargesAmount;
    }
    
    // Manual builder method since Lombok is not working
    public static MultiPointChargesBuilder builder() {
        return new MultiPointChargesBuilder();
    }
    
    public static class MultiPointChargesBuilder {
        private String chargesType;
        private Integer chargesAmount;
        
        public MultiPointChargesBuilder chargesType(String chargesType) {
            this.chargesType = chargesType;
            return this;
        }
        
        public MultiPointChargesBuilder chargesAmount(Integer chargesAmount) {
            this.chargesAmount = chargesAmount;
            return this;
        }
        
        public MultiPointCharges build() {
            return new MultiPointCharges(chargesType, chargesAmount);
        }
    }
} 
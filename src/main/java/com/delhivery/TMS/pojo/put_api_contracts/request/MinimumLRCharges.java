package com.delhivery.TMS.pojo.put_api_contracts.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Builder
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MinimumLRCharges {
    
    @JsonProperty("chargesAmount")
    private Integer chargesAmount;
    
    @JsonProperty("chargesType")
    private String chargesType;
    
    // Manual constructors since Lombok is not working
    public MinimumLRCharges() {}
    
    public MinimumLRCharges(Integer chargesAmount, String chargesType) {
        this.chargesAmount = chargesAmount;
        this.chargesType = chargesType;
    }
    
    // Manual builder method since Lombok is not working
    public static MinimumLRChargesBuilder builder() {
        return new MinimumLRChargesBuilder();
    }
    
    public static class MinimumLRChargesBuilder {
        private Integer chargesAmount;
        private String chargesType;
        
        public MinimumLRChargesBuilder chargesAmount(Integer chargesAmount) {
            this.chargesAmount = chargesAmount;
            return this;
        }
        
        public MinimumLRChargesBuilder chargesType(String chargesType) {
            this.chargesType = chargesType;
            return this;
        }
        
        public MinimumLRCharges build() {
            return new MinimumLRCharges(chargesAmount, chargesType);
        }
    }
} 
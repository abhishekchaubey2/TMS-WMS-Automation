package com.delhivery.TMS.pojo.Get_Api_contracts.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Builder
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MinimumLRCharges {
    
    @JsonProperty("chargesType")
    private String chargesType;
    
    @JsonProperty("chargesAmount")
    private Integer chargesAmount;
    
    @JsonProperty("overheadCharge")
    private Boolean overheadCharge;
    
    // Manual constructors since Lombok is not working
    public MinimumLRCharges() {}
    
    public MinimumLRCharges(String chargesType, Integer chargesAmount, Boolean overheadCharge) {
        this.chargesType = chargesType;
        this.chargesAmount = chargesAmount;
        this.overheadCharge = overheadCharge;
    }
    
    // Manual builder method since Lombok is not working
    public static MinimumLRChargesBuilder builder() {
        return new MinimumLRChargesBuilder();
    }
    
    public static class MinimumLRChargesBuilder {
        private String chargesType;
        private Integer chargesAmount;
        private Boolean overheadCharge;
        
        public MinimumLRChargesBuilder chargesType(String chargesType) {
            this.chargesType = chargesType;
            return this;
        }
        
        public MinimumLRChargesBuilder chargesAmount(Integer chargesAmount) {
            this.chargesAmount = chargesAmount;
            return this;
        }
        
        public MinimumLRChargesBuilder overheadCharge(Boolean overheadCharge) {
            this.overheadCharge = overheadCharge;
            return this;
        }
        
        public MinimumLRCharges build() {
            return new MinimumLRCharges(chargesType, chargesAmount, overheadCharge);
        }
    }
} 
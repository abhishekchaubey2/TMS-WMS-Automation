package com.delhivery.TMS.pojo.Get_Api_contracts.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Builder
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MaxDamageCharges {
    
    @JsonProperty("chargesAmount")
    private Integer chargesAmount;
    
    @JsonProperty("deductionCharge")
    private Boolean deductionCharge;
    
    // Manual constructors since Lombok is not working
    public MaxDamageCharges() {}
    
    public MaxDamageCharges(Integer chargesAmount, Boolean deductionCharge) {
        this.chargesAmount = chargesAmount;
        this.deductionCharge = deductionCharge;
    }
    
    // Manual builder method since Lombok is not working
    public static MaxDamageChargesBuilder builder() {
        return new MaxDamageChargesBuilder();
    }
    
    public static class MaxDamageChargesBuilder {
        private Integer chargesAmount;
        private Boolean deductionCharge;
        
        public MaxDamageChargesBuilder chargesAmount(Integer chargesAmount) {
            this.chargesAmount = chargesAmount;
            return this;
        }
        
        public MaxDamageChargesBuilder deductionCharge(Boolean deductionCharge) {
            this.deductionCharge = deductionCharge;
            return this;
        }
        
        public MaxDamageCharges build() {
            return new MaxDamageCharges(chargesAmount, deductionCharge);
        }
    }
} 
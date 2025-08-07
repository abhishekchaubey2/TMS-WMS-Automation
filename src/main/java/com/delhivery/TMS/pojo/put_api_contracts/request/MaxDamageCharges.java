package com.delhivery.TMS.pojo.put_api_contracts.request;

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
    
    // Manual constructors since Lombok is not working
    public MaxDamageCharges() {}
    
    public MaxDamageCharges(Integer chargesAmount) {
        this.chargesAmount = chargesAmount;
    }
    
    // Manual builder method since Lombok is not working
    public static MaxDamageChargesBuilder builder() {
        return new MaxDamageChargesBuilder();
    }
    
    public static class MaxDamageChargesBuilder {
        private Integer chargesAmount;
        
        public MaxDamageChargesBuilder chargesAmount(Integer chargesAmount) {
            this.chargesAmount = chargesAmount;
            return this;
        }
        
        public MaxDamageCharges build() {
            return new MaxDamageCharges(chargesAmount);
        }
    }
} 
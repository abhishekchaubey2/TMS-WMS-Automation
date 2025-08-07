package com.delhivery.TMS.pojo.Get_Api_contracts.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Builder
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class LoadingCharges {
    
    @JsonProperty("chargesType")
    private String chargesType;
    
    @JsonProperty("chargesAmount")
    private Integer chargesAmount;
    
    @JsonProperty("overheadCharge")
    private Boolean overheadCharge;
    
    // Manual constructors since Lombok is not working
    public LoadingCharges() {}
    
    public LoadingCharges(String chargesType, Integer chargesAmount, Boolean overheadCharge) {
        this.chargesType = chargesType;
        this.chargesAmount = chargesAmount;
        this.overheadCharge = overheadCharge;
    }
    
    // Manual builder method since Lombok is not working
    public static LoadingChargesBuilder builder() {
        return new LoadingChargesBuilder();
    }
    
    public static class LoadingChargesBuilder {
        private String chargesType;
        private Integer chargesAmount;
        private Boolean overheadCharge;
        
        public LoadingChargesBuilder chargesType(String chargesType) {
            this.chargesType = chargesType;
            return this;
        }
        
        public LoadingChargesBuilder chargesAmount(Integer chargesAmount) {
            this.chargesAmount = chargesAmount;
            return this;
        }
        
        public LoadingChargesBuilder overheadCharge(Boolean overheadCharge) {
            this.overheadCharge = overheadCharge;
            return this;
        }
        
        public LoadingCharges build() {
            return new LoadingCharges(chargesType, chargesAmount, overheadCharge);
        }
    }
} 
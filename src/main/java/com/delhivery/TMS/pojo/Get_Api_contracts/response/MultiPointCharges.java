package com.delhivery.TMS.pojo.Get_Api_contracts.response;

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
    
    @JsonProperty("overheadCharge")
    private Boolean overheadCharge;
    
    // Manual constructors since Lombok is not working
    public MultiPointCharges() {}
    
    public MultiPointCharges(String chargesType, Integer chargesAmount, Boolean overheadCharge) {
        this.chargesType = chargesType;
        this.chargesAmount = chargesAmount;
        this.overheadCharge = overheadCharge;
    }
    
    // Manual builder method since Lombok is not working
    public static MultiPointChargesBuilder builder() {
        return new MultiPointChargesBuilder();
    }
    
    public static class MultiPointChargesBuilder {
        private String chargesType;
        private Integer chargesAmount;
        private Boolean overheadCharge;
        
        public MultiPointChargesBuilder chargesType(String chargesType) {
            this.chargesType = chargesType;
            return this;
        }
        
        public MultiPointChargesBuilder chargesAmount(Integer chargesAmount) {
            this.chargesAmount = chargesAmount;
            return this;
        }
        
        public MultiPointChargesBuilder overheadCharge(Boolean overheadCharge) {
            this.overheadCharge = overheadCharge;
            return this;
        }
        
        public MultiPointCharges build() {
            return new MultiPointCharges(chargesType, chargesAmount, overheadCharge);
        }
    }
} 
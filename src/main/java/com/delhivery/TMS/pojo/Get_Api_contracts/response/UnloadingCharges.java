package com.delhivery.TMS.pojo.Get_Api_contracts.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Builder
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UnloadingCharges {
    
    @JsonProperty("chargesType")
    private String chargesType;
    
    @JsonProperty("chargesAmount")
    private Integer chargesAmount;
    
    @JsonProperty("overheadCharge")
    private Boolean overheadCharge;
    
    // Manual constructors since Lombok is not working
    public UnloadingCharges() {}
    
    public UnloadingCharges(String chargesType, Integer chargesAmount, Boolean overheadCharge) {
        this.chargesType = chargesType;
        this.chargesAmount = chargesAmount;
        this.overheadCharge = overheadCharge;
    }
    
    // Manual builder method since Lombok is not working
    public static UnloadingChargesBuilder builder() {
        return new UnloadingChargesBuilder();
    }
    
    public static class UnloadingChargesBuilder {
        private String chargesType;
        private Integer chargesAmount;
        private Boolean overheadCharge;
        
        public UnloadingChargesBuilder chargesType(String chargesType) {
            this.chargesType = chargesType;
            return this;
        }
        
        public UnloadingChargesBuilder chargesAmount(Integer chargesAmount) {
            this.chargesAmount = chargesAmount;
            return this;
        }
        
        public UnloadingChargesBuilder overheadCharge(Boolean overheadCharge) {
            this.overheadCharge = overheadCharge;
            return this;
        }
        
        public UnloadingCharges build() {
            return new UnloadingCharges(chargesType, chargesAmount, overheadCharge);
        }
    }
} 
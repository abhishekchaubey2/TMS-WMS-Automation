package com.delhivery.TMS.pojo.Get_Api_contracts.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.List;

@Builder
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DetentionLoadingCharges {
    
    @JsonProperty("slabs")
    private List<DetentionChargesSlab> slabs;
    
    @JsonProperty("overheadCharge")
    private Boolean overheadCharge;
    
    // Manual constructors since Lombok is not working
    public DetentionLoadingCharges() {}
    
    public DetentionLoadingCharges(List<DetentionChargesSlab> slabs, Boolean overheadCharge) {
        this.slabs = slabs;
        this.overheadCharge = overheadCharge;
    }
    
    // Manual builder method since Lombok is not working
    public static DetentionLoadingChargesBuilder builder() {
        return new DetentionLoadingChargesBuilder();
    }
    
    public static class DetentionLoadingChargesBuilder {
        private List<DetentionChargesSlab> slabs;
        private Boolean overheadCharge;
        
        public DetentionLoadingChargesBuilder slabs(List<DetentionChargesSlab> slabs) {
            this.slabs = slabs;
            return this;
        }
        
        public DetentionLoadingChargesBuilder overheadCharge(Boolean overheadCharge) {
            this.overheadCharge = overheadCharge;
            return this;
        }
        
        public DetentionLoadingCharges build() {
            return new DetentionLoadingCharges(slabs, overheadCharge);
        }
    }
    
    @Builder
    @Getter
    @Setter
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class DetentionChargesSlab {
        
        @JsonProperty("truckType")
        private String truckType;
        
        @JsonProperty("unit")
        private String unit;
        
        @JsonProperty("range")
        private String range;
        
        @JsonProperty("amount")
        private String amount;
        
        // Manual constructors since Lombok is not working
        public DetentionChargesSlab() {}
        
        public DetentionChargesSlab(String truckType, String unit, String range, String amount) {
            this.truckType = truckType;
            this.unit = unit;
            this.range = range;
            this.amount = amount;
        }
        
        // Manual builder method since Lombok is not working
        public static DetentionChargesSlabBuilder builder() {
            return new DetentionChargesSlabBuilder();
        }
        
        public static class DetentionChargesSlabBuilder {
            private String truckType;
            private String unit;
            private String range;
            private String amount;
            
            public DetentionChargesSlabBuilder truckType(String truckType) {
                this.truckType = truckType;
                return this;
            }
            
            public DetentionChargesSlabBuilder unit(String unit) {
                this.unit = unit;
                return this;
            }
            
            public DetentionChargesSlabBuilder range(String range) {
                this.range = range;
                return this;
            }
            
            public DetentionChargesSlabBuilder amount(String amount) {
                this.amount = amount;
                return this;
            }
            
            public DetentionChargesSlab build() {
                return new DetentionChargesSlab(truckType, unit, range, amount);
            }
        }
    }
} 
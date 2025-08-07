package com.delhivery.TMS.pojo.put_api_contracts.request;

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
    private List<DetentionLoadingChargesSlab> slabs;
    
    // Manual constructors since Lombok is not working
    public DetentionLoadingCharges() {}
    
    public DetentionLoadingCharges(List<DetentionLoadingChargesSlab> slabs) {
        this.slabs = slabs;
    }
    
    // Manual builder method since Lombok is not working
    public static DetentionLoadingChargesBuilder builder() {
        return new DetentionLoadingChargesBuilder();
    }
    
    public static class DetentionLoadingChargesBuilder {
        private List<DetentionLoadingChargesSlab> slabs;
        
        public DetentionLoadingChargesBuilder slabs(List<DetentionLoadingChargesSlab> slabs) {
            this.slabs = slabs;
            return this;
        }
        
        public DetentionLoadingCharges build() {
            return new DetentionLoadingCharges(slabs);
        }
    }
    
    // Inner class for slab details
    @Builder
    @Getter
    @Setter
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class DetentionLoadingChargesSlab {
        
        @JsonProperty("truckType")
        private String truckType;
        
        @JsonProperty("unit")
        private String unit;
        
        @JsonProperty("range")
        private String range;
        
        @JsonProperty("amount")
        private String amount;
        
        // Manual constructors since Lombok is not working
        public DetentionLoadingChargesSlab() {}
        
        public DetentionLoadingChargesSlab(String truckType, String unit, String range, String amount) {
            this.truckType = truckType;
            this.unit = unit;
            this.range = range;
            this.amount = amount;
        }
        
        // Manual builder method since Lombok is not working
        public static DetentionLoadingChargesSlabBuilder builder() {
            return new DetentionLoadingChargesSlabBuilder();
        }
        
        public static class DetentionLoadingChargesSlabBuilder {
            private String truckType;
            private String unit;
            private String range;
            private String amount;
            
            public DetentionLoadingChargesSlabBuilder truckType(String truckType) {
                this.truckType = truckType;
                return this;
            }
            
            public DetentionLoadingChargesSlabBuilder unit(String unit) {
                this.unit = unit;
                return this;
            }
            
            public DetentionLoadingChargesSlabBuilder range(String range) {
                this.range = range;
                return this;
            }
            
            public DetentionLoadingChargesSlabBuilder amount(String amount) {
                this.amount = amount;
                return this;
            }
            
            public DetentionLoadingChargesSlab build() {
                return new DetentionLoadingChargesSlab(truckType, unit, range, amount);
            }
        }
    }
} 
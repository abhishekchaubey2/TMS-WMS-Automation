package com.delhivery.TMS.pojo.put_api_contracts.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.List;

@Builder
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DetentionUnloadingCharges {
    
    @JsonProperty("slabs")
    private List<DetentionUnloadingChargesSlab> slabs;
    
    // Manual constructors since Lombok is not working
    public DetentionUnloadingCharges() {}
    
    public DetentionUnloadingCharges(List<DetentionUnloadingChargesSlab> slabs) {
        this.slabs = slabs;
    }
    
    // Manual builder method since Lombok is not working
    public static DetentionUnloadingChargesBuilder builder() {
        return new DetentionUnloadingChargesBuilder();
    }
    
    public static class DetentionUnloadingChargesBuilder {
        private List<DetentionUnloadingChargesSlab> slabs;
        
        public DetentionUnloadingChargesBuilder slabs(List<DetentionUnloadingChargesSlab> slabs) {
            this.slabs = slabs;
            return this;
        }
        
        public DetentionUnloadingCharges build() {
            return new DetentionUnloadingCharges(slabs);
        }
    }
    
    // Inner class for slab details
    @Builder
    @Getter
    @Setter
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class DetentionUnloadingChargesSlab {
        
        @JsonProperty("truckType")
        private String truckType;
        
        @JsonProperty("unit")
        private String unit;
        
        @JsonProperty("range")
        private String range;
        
        @JsonProperty("amount")
        private String amount;
        
        // Manual constructors since Lombok is not working
        public DetentionUnloadingChargesSlab() {}
        
        public DetentionUnloadingChargesSlab(String truckType, String unit, String range, String amount) {
            this.truckType = truckType;
            this.unit = unit;
            this.range = range;
            this.amount = amount;
        }
        
        // Manual builder method since Lombok is not working
        public static DetentionUnloadingChargesSlabBuilder builder() {
            return new DetentionUnloadingChargesSlabBuilder();
        }
        
        public static class DetentionUnloadingChargesSlabBuilder {
            private String truckType;
            private String unit;
            private String range;
            private String amount;
            
            public DetentionUnloadingChargesSlabBuilder truckType(String truckType) {
                this.truckType = truckType;
                return this;
            }
            
            public DetentionUnloadingChargesSlabBuilder unit(String unit) {
                this.unit = unit;
                return this;
            }
            
            public DetentionUnloadingChargesSlabBuilder range(String range) {
                this.range = range;
                return this;
            }
            
            public DetentionUnloadingChargesSlabBuilder amount(String amount) {
                this.amount = amount;
                return this;
            }
            
            public DetentionUnloadingChargesSlab build() {
                return new DetentionUnloadingChargesSlab(truckType, unit, range, amount);
            }
        }
    }
} 
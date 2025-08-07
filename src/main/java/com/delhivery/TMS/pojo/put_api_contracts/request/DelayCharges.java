package com.delhivery.TMS.pojo.put_api_contracts.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.List;

@Builder
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DelayCharges {
    
    @JsonProperty("slabs")
    private List<DelayChargesSlab> slabs;
    
    // Manual constructors since Lombok is not working
    public DelayCharges() {}
    
    public DelayCharges(List<DelayChargesSlab> slabs) {
        this.slabs = slabs;
    }
    
    // Manual builder method since Lombok is not working
    public static DelayChargesBuilder builder() {
        return new DelayChargesBuilder();
    }
    
    public static class DelayChargesBuilder {
        private List<DelayChargesSlab> slabs;
        
        public DelayChargesBuilder slabs(List<DelayChargesSlab> slabs) {
            this.slabs = slabs;
            return this;
        }
        
        public DelayCharges build() {
            return new DelayCharges(slabs);
        }
    }
    
    // Inner class for slab details
    @Builder
    @Getter
    @Setter
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class DelayChargesSlab {
        
        @JsonProperty("truckType")
        private String truckType;
        
        @JsonProperty("unit")
        private String unit;
        
        @JsonProperty("range")
        private String range;
        
        @JsonProperty("amount")
        private String amount;
        
        // Manual constructors since Lombok is not working
        public DelayChargesSlab() {}
        
        public DelayChargesSlab(String truckType, String unit, String range, String amount) {
            this.truckType = truckType;
            this.unit = unit;
            this.range = range;
            this.amount = amount;
        }
        
        // Manual builder method since Lombok is not working
        public static DelayChargesSlabBuilder builder() {
            return new DelayChargesSlabBuilder();
        }
        
        public static class DelayChargesSlabBuilder {
            private String truckType;
            private String unit;
            private String range;
            private String amount;
            
            public DelayChargesSlabBuilder truckType(String truckType) {
                this.truckType = truckType;
                return this;
            }
            
            public DelayChargesSlabBuilder unit(String unit) {
                this.unit = unit;
                return this;
            }
            
            public DelayChargesSlabBuilder range(String range) {
                this.range = range;
                return this;
            }
            
            public DelayChargesSlabBuilder amount(String amount) {
                this.amount = amount;
                return this;
            }
            
            public DelayChargesSlab build() {
                return new DelayChargesSlab(truckType, unit, range, amount);
            }
        }
    }
} 
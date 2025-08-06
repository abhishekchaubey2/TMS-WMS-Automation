package com.delhivery.TMS.pojo.contracts.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Builder @Getter @Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TransitDetails {
    @JsonProperty("tat") private Integer tat;
    @JsonProperty("tatDisplayUnit") private String tatDisplayUnit;
    
    // Manual constructors since Lombok is not working
    public TransitDetails() {}
    
    public TransitDetails(Integer tat, String tatDisplayUnit) {
        this.tat = tat;
        this.tatDisplayUnit = tatDisplayUnit;
    }
    
    // Manual builder method since Lombok is not working
    public static TransitDetailsBuilder builder() {
        return new TransitDetailsBuilder();
    }
    
    public static class TransitDetailsBuilder {
        private Integer tat;
        private String tatDisplayUnit;
        
        public TransitDetailsBuilder tat(Integer tat) {
            this.tat = tat;
            return this;
        }
        
        public TransitDetailsBuilder tatDisplayUnit(String tatDisplayUnit) {
            this.tatDisplayUnit = tatDisplayUnit;
            return this;
        }
        
        public TransitDetails build() {
            return new TransitDetails(tat, tatDisplayUnit);
        }
    }
} 
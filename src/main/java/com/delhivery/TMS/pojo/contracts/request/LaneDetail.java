package com.delhivery.TMS.pojo.contracts.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.List;

@Builder @Getter @Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class LaneDetail {
    @JsonProperty("origin") private String origin;
    @JsonProperty("destination") private String destination;
    @JsonProperty("vehicleName") private String vehicleName;
    @JsonProperty("rateDetails") private RateDetails rateDetails;
    @JsonProperty("transitDetails") private TransitDetails transitDetails;
    
    // Manual constructors since Lombok is not working
    public LaneDetail() {}
    
    public LaneDetail(String origin, String destination, String vehicleName, RateDetails rateDetails, TransitDetails transitDetails) {
        this.origin = origin;
        this.destination = destination;
        this.vehicleName = vehicleName;
        this.rateDetails = rateDetails;
        this.transitDetails = transitDetails;
    }
    
    // Manual builder method since Lombok is not working
    public static LaneDetailBuilder builder() {
        return new LaneDetailBuilder();
    }
    
    public static class LaneDetailBuilder {
        private String origin;
        private String destination;
        private String vehicleName;
        private RateDetails rateDetails;
        private TransitDetails transitDetails;
        
        public LaneDetailBuilder origin(String origin) {
            this.origin = origin;
            return this;
        }
        
        public LaneDetailBuilder destination(String destination) {
            this.destination = destination;
            return this;
        }
        
        public LaneDetailBuilder vehicleName(String vehicleName) {
            this.vehicleName = vehicleName;
            return this;
        }
        
        public LaneDetailBuilder rateDetails(RateDetails rateDetails) {
            this.rateDetails = rateDetails;
            return this;
        }
        
        public LaneDetailBuilder transitDetails(TransitDetails transitDetails) {
            this.transitDetails = transitDetails;
            return this;
        }
        
        public LaneDetail build() {
            return new LaneDetail(origin, destination, vehicleName, rateDetails, transitDetails);
        }
    }
} 
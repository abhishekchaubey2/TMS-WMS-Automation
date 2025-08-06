package com.delhivery.TMS.pojo.contracts.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.List;

@Builder
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ContractRequestPayload {
    
    @JsonProperty("vendorId")
    private String vendorId;
    
    @JsonProperty("contractName")
    private String contractName;
    
    @JsonProperty("startDate")
    private Long startDate;
    
    @JsonProperty("endDate")
    private Long endDate;
    
    @JsonProperty("serviceType")
    private String serviceType;
    
    @JsonProperty("contractType")
    private String contractType;
    
    @JsonProperty("requestType")
    private String requestType;
    
    @JsonProperty("laneDetails")
    private List<LaneDetail> laneDetails;
    
    @JsonProperty("remarks")
    private String remarks;
    
    @JsonProperty("vendorName")
    private String vendorName;
    
    @JsonProperty("volumetricCoefficient")
    private Integer volumetricCoefficient;
    
    @JsonProperty("minChargableWt")
    private Integer minChargableWt;
    
    @JsonProperty("minCharge")
    private Integer minCharge;
    
    // Manual constructors since Lombok is not working
    public ContractRequestPayload() {}
    
    public ContractRequestPayload(String vendorId, String contractName, Long startDate, Long endDate,
                                String serviceType, String contractType, String requestType,
                                List<LaneDetail> laneDetails, String remarks, String vendorName,
                                Integer volumetricCoefficient, Integer minChargableWt, Integer minCharge) {
        this.vendorId = vendorId;
        this.contractName = contractName;
        this.startDate = startDate;
        this.endDate = endDate;
        this.serviceType = serviceType;
        this.contractType = contractType;
        this.requestType = requestType;
        this.laneDetails = laneDetails;
        this.remarks = remarks;
        this.vendorName = vendorName;
        this.volumetricCoefficient = volumetricCoefficient;
        this.minChargableWt = minChargableWt;
        this.minCharge = minCharge;
    }
    
    // Method to apply conditional nullification based on is_submit
    public void applyConditionalNullification(boolean isSubmit) {
        if (isSubmit) {
            // When is_submit is true, set vehicleName to null in laneDetails
            if (laneDetails != null) {
                for (LaneDetail laneDetail : laneDetails) {
                    laneDetail.setVehicleName(null);
                }
            }
        }
        // Note: We don't nullify V2 fields (volumetricCoefficient, minChargableWt, minCharge)
        // because the API requires them for LTL contracts regardless of is_submit value
    }
    
    // Manual builder method since Lombok is not working
    public static ContractRequestPayloadBuilder builder() {
        return new ContractRequestPayloadBuilder();
    }
    
    public static class ContractRequestPayloadBuilder {
        private String vendorId;
        private String contractName;
        private Long startDate;
        private Long endDate;
        private String serviceType;
        private String contractType;
        private String requestType;
        private List<LaneDetail> laneDetails;
        private String remarks;
        private String vendorName;
        private Integer volumetricCoefficient;
        private Integer minChargableWt;
        private Integer minCharge;
        
        public ContractRequestPayloadBuilder vendorId(String vendorId) {
            this.vendorId = vendorId;
            return this;
        }
        
        public ContractRequestPayloadBuilder contractName(String contractName) {
            this.contractName = contractName;
            return this;
        }
        
        public ContractRequestPayloadBuilder startDate(Long startDate) {
            this.startDate = startDate;
            return this;
        }
        
        public ContractRequestPayloadBuilder endDate(Long endDate) {
            this.endDate = endDate;
            return this;
        }
        
        public ContractRequestPayloadBuilder serviceType(String serviceType) {
            this.serviceType = serviceType;
            return this;
        }
        
        public ContractRequestPayloadBuilder contractType(String contractType) {
            this.contractType = contractType;
            return this;
        }
        
        public ContractRequestPayloadBuilder requestType(String requestType) {
            this.requestType = requestType;
            return this;
        }
        
        public ContractRequestPayloadBuilder laneDetails(List<LaneDetail> laneDetails) {
            this.laneDetails = laneDetails;
            return this;
        }
        
        public ContractRequestPayloadBuilder remarks(String remarks) {
            this.remarks = remarks;
            return this;
        }
        
        public ContractRequestPayloadBuilder vendorName(String vendorName) {
            this.vendorName = vendorName;
            return this;
        }
        
        public ContractRequestPayloadBuilder volumetricCoefficient(Integer volumetricCoefficient) {
            this.volumetricCoefficient = volumetricCoefficient;
            return this;
        }
        
        public ContractRequestPayloadBuilder minChargableWt(Integer minChargableWt) {
            this.minChargableWt = minChargableWt;
            return this;
        }
        
        public ContractRequestPayloadBuilder minCharge(Integer minCharge) {
            this.minCharge = minCharge;
            return this;
        }
        
        public ContractRequestPayload build() {
            return new ContractRequestPayload(vendorId, contractName, startDate, endDate, serviceType, 
                                           contractType, requestType, laneDetails, remarks, vendorName,
                                           volumetricCoefficient, minChargableWt, minCharge);
        }
    }
} 
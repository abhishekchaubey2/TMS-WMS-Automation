package com.delhivery.TMS.pojo.contracts.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.List;

@Builder
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ContractRequestPayloadV2 {
    
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
    
    @JsonProperty("vendorName")
    private String vendorName;
    
    @JsonProperty("volumetricCoefficient")
    private Integer volumetricCoefficient;
    
    @JsonProperty("minChargableWt")
    private Integer minChargableWt;
    
    @JsonProperty("minCharge")
    private Integer minCharge;
    
    // Manual constructors since Lombok is not working
    public ContractRequestPayloadV2() {}
    
    public ContractRequestPayloadV2(String vendorId, String contractName, Long startDate, Long endDate,
                                   String serviceType, String contractType, String requestType,
                                   List<LaneDetail> laneDetails, String vendorName,
                                   Integer volumetricCoefficient, Integer minChargableWt, Integer minCharge) {
        this.vendorId = vendorId;
        this.contractName = contractName;
        this.startDate = startDate;
        this.endDate = endDate;
        this.serviceType = serviceType;
        this.contractType = contractType;
        this.requestType = requestType;
        this.laneDetails = laneDetails;
        this.vendorName = vendorName;
        this.volumetricCoefficient = volumetricCoefficient;
        this.minChargableWt = minChargableWt;
        this.minCharge = minCharge;
    }
    
    // Manual builder method since Lombok is not working
    public static ContractRequestPayloadV2Builder builder() {
        return new ContractRequestPayloadV2Builder();
    }
    
    public static class ContractRequestPayloadV2Builder {
        private String vendorId;
        private String contractName;
        private Long startDate;
        private Long endDate;
        private String serviceType;
        private String contractType;
        private String requestType;
        private List<LaneDetail> laneDetails;
        private String vendorName;
        private Integer volumetricCoefficient;
        private Integer minChargableWt;
        private Integer minCharge;
        
        public ContractRequestPayloadV2Builder vendorId(String vendorId) {
            this.vendorId = vendorId;
            return this;
        }
        
        public ContractRequestPayloadV2Builder contractName(String contractName) {
            this.contractName = contractName;
            return this;
        }
        
        public ContractRequestPayloadV2Builder startDate(Long startDate) {
            this.startDate = startDate;
            return this;
        }
        
        public ContractRequestPayloadV2Builder endDate(Long endDate) {
            this.endDate = endDate;
            return this;
        }
        
        public ContractRequestPayloadV2Builder serviceType(String serviceType) {
            this.serviceType = serviceType;
            return this;
        }
        
        public ContractRequestPayloadV2Builder contractType(String contractType) {
            this.contractType = contractType;
            return this;
        }
        
        public ContractRequestPayloadV2Builder requestType(String requestType) {
            this.requestType = requestType;
            return this;
        }
        
        public ContractRequestPayloadV2Builder laneDetails(List<LaneDetail> laneDetails) {
            this.laneDetails = laneDetails;
            return this;
        }
        
        public ContractRequestPayloadV2Builder vendorName(String vendorName) {
            this.vendorName = vendorName;
            return this;
        }
        
        public ContractRequestPayloadV2Builder volumetricCoefficient(Integer volumetricCoefficient) {
            this.volumetricCoefficient = volumetricCoefficient;
            return this;
        }
        
        public ContractRequestPayloadV2Builder minChargableWt(Integer minChargableWt) {
            this.minChargableWt = minChargableWt;
            return this;
        }
        
        public ContractRequestPayloadV2Builder minCharge(Integer minCharge) {
            this.minCharge = minCharge;
            return this;
        }
        
        public ContractRequestPayloadV2 build() {
            return new ContractRequestPayloadV2(vendorId, contractName, startDate, endDate, serviceType, contractType, requestType, laneDetails, vendorName, volumetricCoefficient, minChargableWt, minCharge);
        }
    }
} 
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
    
    // Manual constructors since Lombok is not working
    public ContractRequestPayload() {}
    
    public ContractRequestPayload(String vendorId, String contractName, Long startDate, Long endDate,
                                String serviceType, String contractType, String requestType,
                                List<LaneDetail> laneDetails, String remarks, String vendorName) {
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
        
        public ContractRequestPayload build() {
            return new ContractRequestPayload(vendorId, contractName, startDate, endDate, serviceType, contractType, requestType, laneDetails, remarks, vendorName);
        }
    }
} 
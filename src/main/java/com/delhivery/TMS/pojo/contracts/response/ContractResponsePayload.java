package com.delhivery.TMS.pojo.contracts.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.List;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ContractResponsePayload {
    
    @JsonProperty("success")
    private Boolean success;
    
    @JsonProperty("message")
    private String message;
    
    @JsonProperty("data")
    private ContractData data;
    
    @JsonProperty("errors")
    private List<Object> errors;
    
    @JsonProperty("error")
    private String error;
    
    @JsonProperty("_metadata")
    private Object metadata;
    
    @JsonProperty("timestamp")
    private String timestamp;
    
    @JsonProperty("path")
    private String path;
    
    @JsonProperty("status")
    private Integer status;
    
    @Builder
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class ContractData {
        
        @JsonProperty("contractId")
        private String contractId;
        
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
        
        @JsonProperty("status")
        private String status;
        
        @JsonProperty("createdAt")
        private String createdAt;
        
        @JsonProperty("updatedAt")
        private String updatedAt;
        
        @JsonProperty("laneDetails")
        private List<LaneDetail> laneDetails;
        
        @JsonProperty("remarks")
        private String remarks;
        
        @JsonProperty("vendorName")
        private String vendorName;
    }
    

} 
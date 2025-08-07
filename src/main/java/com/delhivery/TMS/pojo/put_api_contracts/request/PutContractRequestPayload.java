package com.delhivery.TMS.pojo.put_api_contracts.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.List;

@Builder
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PutContractRequestPayload {
    
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
    
    @JsonProperty("loadingCharges")
    private LoadingCharges loadingCharges;
    
    @JsonProperty("unloadingCharges")
    private UnloadingCharges unloadingCharges;
    
    @JsonProperty("detentionLoadingCharges")
    private DetentionLoadingCharges detentionLoadingCharges;
    
    @JsonProperty("detentionUnloadingCharges")
    private DetentionUnloadingCharges detentionUnloadingCharges;
    
    @JsonProperty("multiPointCharges")
    private MultiPointCharges multiPointCharges;
    
    @JsonProperty("delayCharges")
    private DelayCharges delayCharges;
    
    @JsonProperty("maxDamageCharges")
    private MaxDamageCharges maxDamageCharges;
    
    @JsonProperty("minimumLRCharges")
    private MinimumLRCharges minimumLRCharges;
    
    // Manual constructors since Lombok is not working
    public PutContractRequestPayload() {}
    
    public PutContractRequestPayload(String vendorId, String contractName, Long startDate, Long endDate,
                                   String serviceType, String contractType, String requestType,
                                   List<LaneDetail> laneDetails, String remarks, String vendorName,
                                   LoadingCharges loadingCharges, UnloadingCharges unloadingCharges,
                                   DetentionLoadingCharges detentionLoadingCharges, DetentionUnloadingCharges detentionUnloadingCharges,
                                   MultiPointCharges multiPointCharges, DelayCharges delayCharges,
                                   MaxDamageCharges maxDamageCharges, MinimumLRCharges minimumLRCharges) {
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
        this.loadingCharges = loadingCharges;
        this.unloadingCharges = unloadingCharges;
        this.detentionLoadingCharges = detentionLoadingCharges;
        this.detentionUnloadingCharges = detentionUnloadingCharges;
        this.multiPointCharges = multiPointCharges;
        this.delayCharges = delayCharges;
        this.maxDamageCharges = maxDamageCharges;
        this.minimumLRCharges = minimumLRCharges;
    }
    
    // Manual builder method since Lombok is not working
    public static PutContractRequestPayloadBuilder builder() {
        return new PutContractRequestPayloadBuilder();
    }
    
    public static class PutContractRequestPayloadBuilder {
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
        private LoadingCharges loadingCharges;
        private UnloadingCharges unloadingCharges;
        private DetentionLoadingCharges detentionLoadingCharges;
        private DetentionUnloadingCharges detentionUnloadingCharges;
        private MultiPointCharges multiPointCharges;
        private DelayCharges delayCharges;
        private MaxDamageCharges maxDamageCharges;
        private MinimumLRCharges minimumLRCharges;
        
        public PutContractRequestPayloadBuilder vendorId(String vendorId) {
            this.vendorId = vendorId;
            return this;
        }
        
        public PutContractRequestPayloadBuilder contractName(String contractName) {
            this.contractName = contractName;
            return this;
        }
        
        public PutContractRequestPayloadBuilder startDate(Long startDate) {
            this.startDate = startDate;
            return this;
        }
        
        public PutContractRequestPayloadBuilder endDate(Long endDate) {
            this.endDate = endDate;
            return this;
        }
        
        public PutContractRequestPayloadBuilder serviceType(String serviceType) {
            this.serviceType = serviceType;
            return this;
        }
        
        public PutContractRequestPayloadBuilder contractType(String contractType) {
            this.contractType = contractType;
            return this;
        }
        
        public PutContractRequestPayloadBuilder requestType(String requestType) {
            this.requestType = requestType;
            return this;
        }
        
        public PutContractRequestPayloadBuilder laneDetails(List<LaneDetail> laneDetails) {
            this.laneDetails = laneDetails;
            return this;
        }
        
        public PutContractRequestPayloadBuilder remarks(String remarks) {
            this.remarks = remarks;
            return this;
        }
        
        public PutContractRequestPayloadBuilder vendorName(String vendorName) {
            this.vendorName = vendorName;
            return this;
        }
        
        public PutContractRequestPayloadBuilder loadingCharges(LoadingCharges loadingCharges) {
            this.loadingCharges = loadingCharges;
            return this;
        }
        
        public PutContractRequestPayloadBuilder unloadingCharges(UnloadingCharges unloadingCharges) {
            this.unloadingCharges = unloadingCharges;
            return this;
        }
        
        public PutContractRequestPayloadBuilder detentionLoadingCharges(DetentionLoadingCharges detentionLoadingCharges) {
            this.detentionLoadingCharges = detentionLoadingCharges;
            return this;
        }
        
        public PutContractRequestPayloadBuilder detentionUnloadingCharges(DetentionUnloadingCharges detentionUnloadingCharges) {
            this.detentionUnloadingCharges = detentionUnloadingCharges;
            return this;
        }
        
        public PutContractRequestPayloadBuilder multiPointCharges(MultiPointCharges multiPointCharges) {
            this.multiPointCharges = multiPointCharges;
            return this;
        }
        
        public PutContractRequestPayloadBuilder delayCharges(DelayCharges delayCharges) {
            this.delayCharges = delayCharges;
            return this;
        }
        
        public PutContractRequestPayloadBuilder maxDamageCharges(MaxDamageCharges maxDamageCharges) {
            this.maxDamageCharges = maxDamageCharges;
            return this;
        }
        
        public PutContractRequestPayloadBuilder minimumLRCharges(MinimumLRCharges minimumLRCharges) {
            this.minimumLRCharges = minimumLRCharges;
            return this;
        }
        
        public PutContractRequestPayload build() {
            return new PutContractRequestPayload(vendorId, contractName, startDate, endDate, serviceType, 
                                               contractType, requestType, laneDetails, remarks, vendorName,
                                               loadingCharges, unloadingCharges, detentionLoadingCharges,
                                               detentionUnloadingCharges, multiPointCharges, delayCharges,
                                               maxDamageCharges, minimumLRCharges);
        }
    }
} 
package com.delhivery.TMS.pojo.Get_Api_contracts.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.List;

@Builder
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class GetContractResponsePayload {
    
    @JsonProperty("data")
    private GetContractData data;
    
    @JsonProperty("success")
    private Boolean success;
    
    // Manual constructors since Lombok is not working
    public GetContractResponsePayload() {}
    
    public GetContractResponsePayload(GetContractData data, Boolean success) {
        this.data = data;
        this.success = success;
    }
    
    // Manual builder method since Lombok is not working
    public static GetContractResponsePayloadBuilder builder() {
        return new GetContractResponsePayloadBuilder();
    }
    
    public static class GetContractResponsePayloadBuilder {
        private GetContractData data;
        private Boolean success;
        
        public GetContractResponsePayloadBuilder data(GetContractData data) {
            this.data = data;
            return this;
        }
        
        public GetContractResponsePayloadBuilder success(Boolean success) {
            this.success = success;
            return this;
        }
        
        public GetContractResponsePayload build() {
            return new GetContractResponsePayload(data, success);
        }
    }
    
    @Builder
    @Getter
    @Setter
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class GetContractData {
        
        @JsonProperty("id")
        private String id;
        
        @JsonProperty("state")
        private String state;
        
        @JsonProperty("status")
        private String status;
        
        @JsonProperty("contractId")
        private String contractId;
        
        @JsonProperty("contractName")
        private String contractName;
        
        @JsonProperty("contractType")
        private String contractType;
        
        @JsonProperty("endDate")
        private Long endDate;
        
        @JsonProperty("isEdited")
        private Boolean isEdited;
        
        @JsonProperty("requestType")
        private String requestType;
        
        @JsonProperty("serviceType")
        private String serviceType;
        
        @JsonProperty("startDate")
        private Long startDate;
        
        @JsonProperty("vendorId")
        private String vendorId;
        
        @JsonProperty("vendorName")
        private String vendorName;
        
        @JsonProperty("version")
        private Integer version;
        
        @JsonProperty("minChargableWt")
        private Integer minChargableWt;
        
        @JsonProperty("minCharge")
        private Integer minCharge;
        
        @JsonProperty("numberOfDaysInBillingCycle")
        private Integer numberOfDaysInBillingCycle;
        
        @JsonProperty("volumetricCoefficient")
        private Integer volumetricCoefficient;
        
        @JsonProperty("delayCharges")
        private DelayCharges delayCharges;
        
        @JsonProperty("maxDamageCharges")
        private MaxDamageCharges maxDamageCharges;
        
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
        
        @JsonProperty("minimumLRCharges")
        private MinimumLRCharges minimumLRCharges;
        
        @JsonProperty("remarks")
        private String remarks;
        
        @JsonProperty("subStatus")
        private String subStatus;
        
        @JsonProperty("updatedVersionFields")
        private List<String> updatedVersionFields;
        
        @JsonProperty("approvedBy")
        private String approvedBy;
        
        @JsonProperty("contractAndRequestType")
        private String contractAndRequestType;
        
        @JsonProperty("referenceId")
        private String referenceId;
        
        // Manual constructors since Lombok is not working
        public GetContractData() {}
        
        public GetContractData(String id, String state, String status, String contractId, String contractName,
                              String contractType, Long endDate, Boolean isEdited, String requestType,
                              String serviceType, Long startDate, String vendorId, String vendorName,
                              Integer version, Integer minChargableWt, Integer minCharge, Integer numberOfDaysInBillingCycle,
                              Integer volumetricCoefficient, DelayCharges delayCharges, MaxDamageCharges maxDamageCharges,
                              LoadingCharges loadingCharges, UnloadingCharges unloadingCharges,
                              DetentionLoadingCharges detentionLoadingCharges, DetentionUnloadingCharges detentionUnloadingCharges,
                              MultiPointCharges multiPointCharges, MinimumLRCharges minimumLRCharges,
                              String remarks, String subStatus, List<String> updatedVersionFields,
                              String approvedBy, String contractAndRequestType, String referenceId) {
            this.id = id;
            this.state = state;
            this.status = status;
            this.contractId = contractId;
            this.contractName = contractName;
            this.contractType = contractType;
            this.endDate = endDate;
            this.isEdited = isEdited;
            this.requestType = requestType;
            this.serviceType = serviceType;
            this.startDate = startDate;
            this.vendorId = vendorId;
            this.vendorName = vendorName;
            this.version = version;
            this.minChargableWt = minChargableWt;
            this.minCharge = minCharge;
            this.numberOfDaysInBillingCycle = numberOfDaysInBillingCycle;
            this.volumetricCoefficient = volumetricCoefficient;
            this.delayCharges = delayCharges;
            this.maxDamageCharges = maxDamageCharges;
            this.loadingCharges = loadingCharges;
            this.unloadingCharges = unloadingCharges;
            this.detentionLoadingCharges = detentionLoadingCharges;
            this.detentionUnloadingCharges = detentionUnloadingCharges;
            this.multiPointCharges = multiPointCharges;
            this.minimumLRCharges = minimumLRCharges;
            this.remarks = remarks;
            this.subStatus = subStatus;
            this.updatedVersionFields = updatedVersionFields;
            this.approvedBy = approvedBy;
            this.contractAndRequestType = contractAndRequestType;
            this.referenceId = referenceId;
        }
        
        // Manual builder method since Lombok is not working
        public static GetContractDataBuilder builder() {
            return new GetContractDataBuilder();
        }
        
        public static class GetContractDataBuilder {
            private String id;
            private String state;
            private String status;
            private String contractId;
            private String contractName;
            private String contractType;
            private Long endDate;
            private Boolean isEdited;
            private String requestType;
            private String serviceType;
            private Long startDate;
            private String vendorId;
            private String vendorName;
            private Integer version;
            private Integer minChargableWt;
            private Integer minCharge;
            private Integer numberOfDaysInBillingCycle;
            private Integer volumetricCoefficient;
            private DelayCharges delayCharges;
            private MaxDamageCharges maxDamageCharges;
            private LoadingCharges loadingCharges;
            private UnloadingCharges unloadingCharges;
            private DetentionLoadingCharges detentionLoadingCharges;
            private DetentionUnloadingCharges detentionUnloadingCharges;
            private MultiPointCharges multiPointCharges;
            private MinimumLRCharges minimumLRCharges;
            private String remarks;
            private String subStatus;
            private List<String> updatedVersionFields;
            private String approvedBy;
            private String contractAndRequestType;
            private String referenceId;
            
            public GetContractDataBuilder id(String id) {
                this.id = id;
                return this;
            }
            
            public GetContractDataBuilder state(String state) {
                this.state = state;
                return this;
            }
            
            public GetContractDataBuilder status(String status) {
                this.status = status;
                return this;
            }
            
            public GetContractDataBuilder contractId(String contractId) {
                this.contractId = contractId;
                return this;
            }
            
            public GetContractDataBuilder contractName(String contractName) {
                this.contractName = contractName;
                return this;
            }
            
            public GetContractDataBuilder contractType(String contractType) {
                this.contractType = contractType;
                return this;
            }
            
            public GetContractDataBuilder endDate(Long endDate) {
                this.endDate = endDate;
                return this;
            }
            
            public GetContractDataBuilder isEdited(Boolean isEdited) {
                this.isEdited = isEdited;
                return this;
            }
            
            public GetContractDataBuilder requestType(String requestType) {
                this.requestType = requestType;
                return this;
            }
            
            public GetContractDataBuilder serviceType(String serviceType) {
                this.serviceType = serviceType;
                return this;
            }
            
            public GetContractDataBuilder startDate(Long startDate) {
                this.startDate = startDate;
                return this;
            }
            
            public GetContractDataBuilder vendorId(String vendorId) {
                this.vendorId = vendorId;
                return this;
            }
            
            public GetContractDataBuilder vendorName(String vendorName) {
                this.vendorName = vendorName;
                return this;
            }
            
            public GetContractDataBuilder version(Integer version) {
                this.version = version;
                return this;
            }
            
            public GetContractDataBuilder minChargableWt(Integer minChargableWt) {
                this.minChargableWt = minChargableWt;
                return this;
            }
            
            public GetContractDataBuilder minCharge(Integer minCharge) {
                this.minCharge = minCharge;
                return this;
            }
            
            public GetContractDataBuilder numberOfDaysInBillingCycle(Integer numberOfDaysInBillingCycle) {
                this.numberOfDaysInBillingCycle = numberOfDaysInBillingCycle;
                return this;
            }
            
            public GetContractDataBuilder volumetricCoefficient(Integer volumetricCoefficient) {
                this.volumetricCoefficient = volumetricCoefficient;
                return this;
            }
            
            public GetContractDataBuilder delayCharges(DelayCharges delayCharges) {
                this.delayCharges = delayCharges;
                return this;
            }
            
            public GetContractDataBuilder maxDamageCharges(MaxDamageCharges maxDamageCharges) {
                this.maxDamageCharges = maxDamageCharges;
                return this;
            }
            
            public GetContractDataBuilder loadingCharges(LoadingCharges loadingCharges) {
                this.loadingCharges = loadingCharges;
                return this;
            }
            
            public GetContractDataBuilder unloadingCharges(UnloadingCharges unloadingCharges) {
                this.unloadingCharges = unloadingCharges;
                return this;
            }
            
            public GetContractDataBuilder detentionLoadingCharges(DetentionLoadingCharges detentionLoadingCharges) {
                this.detentionLoadingCharges = detentionLoadingCharges;
                return this;
            }
            
            public GetContractDataBuilder detentionUnloadingCharges(DetentionUnloadingCharges detentionUnloadingCharges) {
                this.detentionUnloadingCharges = detentionUnloadingCharges;
                return this;
            }
            
            public GetContractDataBuilder multiPointCharges(MultiPointCharges multiPointCharges) {
                this.multiPointCharges = multiPointCharges;
                return this;
            }
            
            public GetContractDataBuilder minimumLRCharges(MinimumLRCharges minimumLRCharges) {
                this.minimumLRCharges = minimumLRCharges;
                return this;
            }
            
            public GetContractDataBuilder remarks(String remarks) {
                this.remarks = remarks;
                return this;
            }
            
            public GetContractDataBuilder subStatus(String subStatus) {
                this.subStatus = subStatus;
                return this;
            }
            
            public GetContractDataBuilder updatedVersionFields(List<String> updatedVersionFields) {
                this.updatedVersionFields = updatedVersionFields;
                return this;
            }
            
            public GetContractDataBuilder approvedBy(String approvedBy) {
                this.approvedBy = approvedBy;
                return this;
            }
            
            public GetContractDataBuilder contractAndRequestType(String contractAndRequestType) {
                this.contractAndRequestType = contractAndRequestType;
                return this;
            }
            
            public GetContractDataBuilder referenceId(String referenceId) {
                this.referenceId = referenceId;
                return this;
            }
            
            public GetContractData build() {
                return new GetContractData(id, state, status, contractId, contractName, contractType, endDate,
                                        isEdited, requestType, serviceType, startDate, vendorId, vendorName, version,
                                        minChargableWt, minCharge, numberOfDaysInBillingCycle, volumetricCoefficient,
                                        delayCharges, maxDamageCharges, loadingCharges, unloadingCharges,
                                        detentionLoadingCharges, detentionUnloadingCharges, multiPointCharges,
                                        minimumLRCharges, remarks, subStatus, updatedVersionFields, approvedBy,
                                        contractAndRequestType, referenceId);
            }
        }
    }
} 
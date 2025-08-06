package com.delhivery.TMS.pojo.contracts.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.List;

@Builder
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ContractResponsePayloadV2 {
    
    @JsonProperty("success")
    private Boolean success;
    
    @JsonProperty("message")
    private String message;
    
    @JsonProperty("data")
    private ContractDataV2 data;
    
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
    
    // Manual constructors since Lombok is not working
    public ContractResponsePayloadV2() {}
    
    public ContractResponsePayloadV2(Boolean success, String message, ContractDataV2 data, List<Object> errors, 
                                   String error, Object metadata, String timestamp, String path, Integer status) {
        this.success = success;
        this.message = message;
        this.data = data;
        this.errors = errors;
        this.error = error;
        this.metadata = metadata;
        this.timestamp = timestamp;
        this.path = path;
        this.status = status;
    }
    
    // Manual builder method since Lombok is not working
    public static ContractResponsePayloadV2Builder builder() {
        return new ContractResponsePayloadV2Builder();
    }
    
    public static class ContractResponsePayloadV2Builder {
        private Boolean success;
        private String message;
        private ContractDataV2 data;
        private List<Object> errors;
        private String error;
        private Object metadata;
        private String timestamp;
        private String path;
        private Integer status;
        
        public ContractResponsePayloadV2Builder success(Boolean success) {
            this.success = success;
            return this;
        }
        
        public ContractResponsePayloadV2Builder message(String message) {
            this.message = message;
            return this;
        }
        
        public ContractResponsePayloadV2Builder data(ContractDataV2 data) {
            this.data = data;
            return this;
        }
        
        public ContractResponsePayloadV2Builder errors(List<Object> errors) {
            this.errors = errors;
            return this;
        }
        
        public ContractResponsePayloadV2Builder error(String error) {
            this.error = error;
            return this;
        }
        
        public ContractResponsePayloadV2Builder metadata(Object metadata) {
            this.metadata = metadata;
            return this;
        }
        
        public ContractResponsePayloadV2Builder timestamp(String timestamp) {
            this.timestamp = timestamp;
            return this;
        }
        
        public ContractResponsePayloadV2Builder path(String path) {
            this.path = path;
            return this;
        }
        
        public ContractResponsePayloadV2Builder status(Integer status) {
            this.status = status;
            return this;
        }
        
        public ContractResponsePayloadV2 build() {
            return new ContractResponsePayloadV2(success, message, data, errors, error, metadata, timestamp, path, status);
        }
    }
    
    @Builder
    @Getter
    @Setter
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class ContractDataV2 {
        
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
        
        @JsonProperty("vendorName")
        private String vendorName;
        
        @JsonProperty("volumetricCoefficient")
        private Integer volumetricCoefficient;
        
        @JsonProperty("minChargableWt")
        private Integer minChargableWt;
        
        @JsonProperty("minCharge")
        private Integer minCharge;
        
        // Manual constructors since Lombok is not working
        public ContractDataV2() {}
        
        public ContractDataV2(String contractId, String vendorId, String contractName, Long startDate, Long endDate,
                             String serviceType, String contractType, String requestType, String status,
                             String createdAt, String updatedAt, List<LaneDetail> laneDetails, String vendorName,
                             Integer volumetricCoefficient, Integer minChargableWt, Integer minCharge) {
            this.contractId = contractId;
            this.vendorId = vendorId;
            this.contractName = contractName;
            this.startDate = startDate;
            this.endDate = endDate;
            this.serviceType = serviceType;
            this.contractType = contractType;
            this.requestType = requestType;
            this.status = status;
            this.createdAt = createdAt;
            this.updatedAt = updatedAt;
            this.laneDetails = laneDetails;
            this.vendorName = vendorName;
            this.volumetricCoefficient = volumetricCoefficient;
            this.minChargableWt = minChargableWt;
            this.minCharge = minCharge;
        }
        
        // Manual builder method since Lombok is not working
        public static ContractDataV2Builder builder() {
            return new ContractDataV2Builder();
        }
        
        public static class ContractDataV2Builder {
            private String contractId;
            private String vendorId;
            private String contractName;
            private Long startDate;
            private Long endDate;
            private String serviceType;
            private String contractType;
            private String requestType;
            private String status;
            private String createdAt;
            private String updatedAt;
            private List<LaneDetail> laneDetails;
            private String vendorName;
            private Integer volumetricCoefficient;
            private Integer minChargableWt;
            private Integer minCharge;
            
            public ContractDataV2Builder contractId(String contractId) {
                this.contractId = contractId;
                return this;
            }
            
            public ContractDataV2Builder vendorId(String vendorId) {
                this.vendorId = vendorId;
                return this;
            }
            
            public ContractDataV2Builder contractName(String contractName) {
                this.contractName = contractName;
                return this;
            }
            
            public ContractDataV2Builder startDate(Long startDate) {
                this.startDate = startDate;
                return this;
            }
            
            public ContractDataV2Builder endDate(Long endDate) {
                this.endDate = endDate;
                return this;
            }
            
            public ContractDataV2Builder serviceType(String serviceType) {
                this.serviceType = serviceType;
                return this;
            }
            
            public ContractDataV2Builder contractType(String contractType) {
                this.contractType = contractType;
                return this;
            }
            
            public ContractDataV2Builder requestType(String requestType) {
                this.requestType = requestType;
                return this;
            }
            
            public ContractDataV2Builder status(String status) {
                this.status = status;
                return this;
            }
            
            public ContractDataV2Builder createdAt(String createdAt) {
                this.createdAt = createdAt;
                return this;
            }
            
            public ContractDataV2Builder updatedAt(String updatedAt) {
                this.updatedAt = updatedAt;
                return this;
            }
            
            public ContractDataV2Builder laneDetails(List<LaneDetail> laneDetails) {
                this.laneDetails = laneDetails;
                return this;
            }
            
            public ContractDataV2Builder vendorName(String vendorName) {
                this.vendorName = vendorName;
                return this;
            }
            
            public ContractDataV2Builder volumetricCoefficient(Integer volumetricCoefficient) {
                this.volumetricCoefficient = volumetricCoefficient;
                return this;
            }
            
            public ContractDataV2Builder minChargableWt(Integer minChargableWt) {
                this.minChargableWt = minChargableWt;
                return this;
            }
            
            public ContractDataV2Builder minCharge(Integer minCharge) {
                this.minCharge = minCharge;
                return this;
            }
            
            public ContractDataV2 build() {
                return new ContractDataV2(contractId, vendorId, contractName, startDate, endDate, serviceType, 
                                        contractType, requestType, status, createdAt, updatedAt, laneDetails, vendorName,
                                        volumetricCoefficient, minChargableWt, minCharge);
            }
        }
    }
} 
package com.delhivery.TMS.pojo.contracts.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.List;

@Builder
@Getter
@Setter
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
    
    // Manual constructors since Lombok is not working
    public ContractResponsePayload() {}
    
    public ContractResponsePayload(Boolean success, String message, ContractData data, List<Object> errors, 
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
    public static ContractResponsePayloadBuilder builder() {
        return new ContractResponsePayloadBuilder();
    }
    
    public static class ContractResponsePayloadBuilder {
        private Boolean success;
        private String message;
        private ContractData data;
        private List<Object> errors;
        private String error;
        private Object metadata;
        private String timestamp;
        private String path;
        private Integer status;
        
        public ContractResponsePayloadBuilder success(Boolean success) {
            this.success = success;
            return this;
        }
        
        public ContractResponsePayloadBuilder message(String message) {
            this.message = message;
            return this;
        }
        
        public ContractResponsePayloadBuilder data(ContractData data) {
            this.data = data;
            return this;
        }
        
        public ContractResponsePayloadBuilder errors(List<Object> errors) {
            this.errors = errors;
            return this;
        }
        
        public ContractResponsePayloadBuilder error(String error) {
            this.error = error;
            return this;
        }
        
        public ContractResponsePayloadBuilder metadata(Object metadata) {
            this.metadata = metadata;
            return this;
        }
        
        public ContractResponsePayloadBuilder timestamp(String timestamp) {
            this.timestamp = timestamp;
            return this;
        }
        
        public ContractResponsePayloadBuilder path(String path) {
            this.path = path;
            return this;
        }
        
        public ContractResponsePayloadBuilder status(Integer status) {
            this.status = status;
            return this;
        }
        
        public ContractResponsePayload build() {
            return new ContractResponsePayload(success, message, data, errors, error, metadata, timestamp, path, status);
        }
    }
    
    @Builder
    @Getter
    @Setter
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class ContractData {
        
        @JsonProperty("id")
        private String id;
        
        @JsonProperty("contractId")
        private String contractId;
        
        @JsonProperty("requestId")
        private String requestId;
        
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
        
        // Manual constructors since Lombok is not working
        public ContractData() {}
        
        public ContractData(String id, String contractId, String requestId, String vendorId, String contractName, Long startDate, Long endDate,
                          String serviceType, String contractType, String requestType, String status,
                          String createdAt, String updatedAt, List<LaneDetail> laneDetails, String remarks, String vendorName) {
            this.id = id;
            this.contractId = contractId;
            this.requestId = requestId;
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
            this.remarks = remarks;
            this.vendorName = vendorName;
        }
        
        // Manual builder method since Lombok is not working
        public static ContractDataBuilder builder() {
            return new ContractDataBuilder();
        }
        
        public static class ContractDataBuilder {
            private String id;
            private String contractId;
            private String requestId;
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
            private String remarks;
            private String vendorName;
            
            public ContractDataBuilder id(String id) {
                this.id = id;
                return this;
            }
            
            public ContractDataBuilder contractId(String contractId) {
                this.contractId = contractId;
                return this;
            }
            
            public ContractDataBuilder requestId(String requestId) {
                this.requestId = requestId;
                return this;
            }
            
            public ContractDataBuilder vendorId(String vendorId) {
                this.vendorId = vendorId;
                return this;
            }
            
            public ContractDataBuilder contractName(String contractName) {
                this.contractName = contractName;
                return this;
            }
            
            public ContractDataBuilder startDate(Long startDate) {
                this.startDate = startDate;
                return this;
            }
            
            public ContractDataBuilder endDate(Long endDate) {
                this.endDate = endDate;
                return this;
            }
            
            public ContractDataBuilder serviceType(String serviceType) {
                this.serviceType = serviceType;
                return this;
            }
            
            public ContractDataBuilder contractType(String contractType) {
                this.contractType = contractType;
                return this;
            }
            
            public ContractDataBuilder requestType(String requestType) {
                this.requestType = requestType;
                return this;
            }
            
            public ContractDataBuilder status(String status) {
                this.status = status;
                return this;
            }
            
            public ContractDataBuilder createdAt(String createdAt) {
                this.createdAt = createdAt;
                return this;
            }
            
            public ContractDataBuilder updatedAt(String updatedAt) {
                this.updatedAt = updatedAt;
                return this;
            }
            
            public ContractDataBuilder laneDetails(List<LaneDetail> laneDetails) {
                this.laneDetails = laneDetails;
                return this;
            }
            
            public ContractDataBuilder remarks(String remarks) {
                this.remarks = remarks;
                return this;
            }
            
            public ContractDataBuilder vendorName(String vendorName) {
                this.vendorName = vendorName;
                return this;
            }
            
            public ContractData build() {
                return new ContractData(id, contractId, requestId, vendorId, contractName, startDate, endDate, serviceType, 
                                      contractType, requestType, status, createdAt, updatedAt, laneDetails, remarks, vendorName);
            }
        }
    }
} 
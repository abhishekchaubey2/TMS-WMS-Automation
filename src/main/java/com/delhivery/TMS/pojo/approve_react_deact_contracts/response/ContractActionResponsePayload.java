package com.delhivery.TMS.pojo.approve_react_deact_contracts.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Builder
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ContractActionResponsePayload {
    
    @JsonProperty("data")
    private ContractActionData data;
    
    @JsonProperty("success")
    private Boolean success;
    
    // Manual constructors since Lombok is not working
    public ContractActionResponsePayload() {}
    
    public ContractActionResponsePayload(ContractActionData data, Boolean success) {
        this.data = data;
        this.success = success;
    }
    
    // Manual builder method since Lombok is not working
    public static ContractActionResponsePayloadBuilder builder() {
        return new ContractActionResponsePayloadBuilder();
    }
    
    public static class ContractActionResponsePayloadBuilder {
        private ContractActionData data;
        private Boolean success;
        
        public ContractActionResponsePayloadBuilder data(ContractActionData data) {
            this.data = data;
            return this;
        }
        
        public ContractActionResponsePayloadBuilder success(Boolean success) {
            this.success = success;
            return this;
        }
        
        public ContractActionResponsePayload build() {
            return new ContractActionResponsePayload(data, success);
        }
    }
    
    // Inner class for the data field
    @Builder
    @Getter
    @Setter
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class ContractActionData {
        
        @JsonProperty("id")
        private String id;
        
        @JsonProperty("message")
        private String message;
        
        @JsonProperty("requestId")
        private String requestId;
        
        @JsonProperty("contractId")
        private String contractId;
        
        // Manual constructors
        public ContractActionData() {}
        
        public ContractActionData(String id, String message, String requestId, String contractId) {
            this.id = id;
            this.message = message;
            this.requestId = requestId;
            this.contractId = contractId;
        }
        
        // Manual builder method
        public static ContractActionDataBuilder builder() {
            return new ContractActionDataBuilder();
        }
        
        public static class ContractActionDataBuilder {
            private String id;
            private String message;
            private String requestId;
            private String contractId;
            
            public ContractActionDataBuilder id(String id) {
                this.id = id;
                return this;
            }
            
            public ContractActionDataBuilder message(String message) {
                this.message = message;
                return this;
            }
            
            public ContractActionDataBuilder requestId(String requestId) {
                this.requestId = requestId;
                return this;
            }
            
            public ContractActionDataBuilder contractId(String contractId) {
                this.contractId = contractId;
                return this;
            }
            
            public ContractActionData build() {
                return new ContractActionData(id, message, requestId, contractId);
            }
        }
    }
} 
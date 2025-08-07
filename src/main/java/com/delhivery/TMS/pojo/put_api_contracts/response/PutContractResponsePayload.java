package com.delhivery.TMS.pojo.put_api_contracts.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Builder
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PutContractResponsePayload {
    
    @JsonProperty("data")
    private PutContractData data;
    
    @JsonProperty("success")
    private Boolean success;
    
    // Manual constructors since Lombok is not working
    public PutContractResponsePayload() {}
    
    public PutContractResponsePayload(PutContractData data, Boolean success) {
        this.data = data;
        this.success = success;
    }
    
    // Manual builder method since Lombok is not working
    public static PutContractResponsePayloadBuilder builder() {
        return new PutContractResponsePayloadBuilder();
    }
    
    public static class PutContractResponsePayloadBuilder {
        private PutContractData data;
        private Boolean success;
        
        public PutContractResponsePayloadBuilder data(PutContractData data) {
            this.data = data;
            return this;
        }
        
        public PutContractResponsePayloadBuilder success(Boolean success) {
            this.success = success;
            return this;
        }
        
        public PutContractResponsePayload build() {
            return new PutContractResponsePayload(data, success);
        }
    }
    
    // Inner class for data structure
    @Builder
    @Getter
    @Setter
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class PutContractData {
        
        @JsonProperty("id")
        private String id;
        
        @JsonProperty("message")
        private String message;
        
        @JsonProperty("requestId")
        private String requestId;
        
        @JsonProperty("contractId")
        private String contractId;
        
        // Manual constructors since Lombok is not working
        public PutContractData() {}
        
        public PutContractData(String id, String message, String requestId, String contractId) {
            this.id = id;
            this.message = message;
            this.requestId = requestId;
            this.contractId = contractId;
        }
        
        // Manual builder method since Lombok is not working
        public static PutContractDataBuilder builder() {
            return new PutContractDataBuilder();
        }
        
        public static class PutContractDataBuilder {
            private String id;
            private String message;
            private String requestId;
            private String contractId;
            
            public PutContractDataBuilder id(String id) {
                this.id = id;
                return this;
            }
            
            public PutContractDataBuilder message(String message) {
                this.message = message;
                return this;
            }
            
            public PutContractDataBuilder requestId(String requestId) {
                this.requestId = requestId;
                return this;
            }
            
            public PutContractDataBuilder contractId(String contractId) {
                this.contractId = contractId;
                return this;
            }
            
            public PutContractData build() {
                return new PutContractData(id, message, requestId, contractId);
            }
        }
    }
} 
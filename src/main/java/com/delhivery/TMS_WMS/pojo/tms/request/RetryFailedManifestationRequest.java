package com.delhivery.TMS_WMS.pojo.tms.request;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Retry Failed Manifestation Request
 */
public class RetryFailedManifestationRequest {
    
    @JsonProperty("meta")
    private Meta meta;
    
    @JsonProperty("payload")
    private Payload payload;
    
    public RetryFailedManifestationRequest() {}
    
    public RetryFailedManifestationRequest(Meta meta, Payload payload) {
        this.meta = meta;
        this.payload = payload;
    }
    
    public Meta getMeta() {
        return meta;
    }
    
    public void setMeta(Meta meta) {
        this.meta = meta;
    }
    
    public Payload getPayload() {
        return payload;
    }
    
    public void setPayload(Payload payload) {
        this.payload = payload;
    }
    
    /**
     * Meta nested class
     */
    public static class Meta {
        @JsonProperty("tenantId")
        private String tenantId;
        
        @JsonProperty("appId")
        private String appId;
        
        public Meta() {}
        
        public Meta(String tenantId, String appId) {
            this.tenantId = tenantId;
            this.appId = appId;
        }
        
        public String getTenantId() {
            return tenantId;
        }
        
        public void setTenantId(String tenantId) {
            this.tenantId = tenantId;
        }
        
        public String getAppId() {
            return appId;
        }
        
        public void setAppId(String appId) {
            this.appId = appId;
        }
    }
    
    /**
     * Payload nested class
     */
    public static class Payload {
        @JsonProperty("action")
        private String action;
        
        public Payload() {}
        
        public Payload(String action) {
            this.action = action;
        }
        
        public String getAction() {
            return action;
        }
        
        public void setAction(String action) {
            this.action = action;
        }
    }
}


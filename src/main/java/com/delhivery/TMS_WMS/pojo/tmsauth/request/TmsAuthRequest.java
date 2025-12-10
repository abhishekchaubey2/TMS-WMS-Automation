package com.delhivery.TMS_WMS.pojo.tmsauth.request;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * TMS Client Credentials Authentication Request
 */
public class TmsAuthRequest {
    
    @JsonProperty("clientId")
    private String clientId;
    
    @JsonProperty("clientSecret")
    private String clientSecret;
    
    @JsonProperty("audience")
    private String audience;
    
    public TmsAuthRequest() {
    }
    
    public TmsAuthRequest(String clientId, String clientSecret, String audience) {
        this.clientId = clientId;
        this.clientSecret = clientSecret;
        this.audience = audience;
    }
    
    public String getClientId() {
        return clientId;
    }
    
    public void setClientId(String clientId) {
        this.clientId = clientId;
    }
    
    public String getClientSecret() {
        return clientSecret;
    }
    
    public void setClientSecret(String clientSecret) {
        this.clientSecret = clientSecret;
    }
    
    public String getAudience() {
        return audience;
    }
    
    public void setAudience(String audience) {
        this.audience = audience;
    }
}

package com.delhivery.TMS_WMS.pojo.tmsauth.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * TMS Client Credentials Authentication Response
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class TmsAuthResponse {
    
    @JsonProperty("data")
    private AuthData data;
    
    public TmsAuthResponse() {}
    
    public AuthData getData() { return data; }
    public void setData(AuthData data) { this.data = data; }
    
    public static class AuthData {
        @JsonProperty("accessToken")
        private String accessToken;
        
        @JsonProperty("tokenType")
        private String tokenType;
        
        @JsonProperty("expiresIn")
        private Integer expiresIn;
        
        @JsonProperty("scope")
        private String scope;
        
        public String getAccessToken() { return accessToken; }
        public void setAccessToken(String accessToken) { this.accessToken = accessToken; }
        
        public String getTokenType() { return tokenType; }
        public void setTokenType(String tokenType) { this.tokenType = tokenType; }
        
        public Integer getExpiresIn() { return expiresIn; }
        public void setExpiresIn(Integer expiresIn) { this.expiresIn = expiresIn; }
        
        public String getScope() { return scope; }
        public void setScope(String scope) { this.scope = scope; }
    }
}

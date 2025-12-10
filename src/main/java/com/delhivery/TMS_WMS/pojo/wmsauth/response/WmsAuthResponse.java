package com.delhivery.TMS_WMS.pojo.wmsauth.response;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * WMS Authentication Response Payload
 */
public class WmsAuthResponse {
    
    @JsonProperty("_metadata")
    private Object metadata;
    
    @JsonProperty("success")
    private Boolean success;
    
    @JsonProperty("message")
    private String message;
    
    @JsonProperty("errors")
    private Object errors;
    
    @JsonProperty("data")
    private AuthData data;
    
    public WmsAuthResponse() {}
    
    public Object getMetadata() { return metadata; }
    public void setMetadata(Object metadata) { this.metadata = metadata; }
    
    public Boolean getSuccess() { return success; }
    public void setSuccess(Boolean success) { this.success = success; }
    
    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }
    
    public Object getErrors() { return errors; }
    public void setErrors(Object errors) { this.errors = errors; }
    
    public AuthData getData() { return data; }
    public void setData(AuthData data) { this.data = data; }
    
    public static class AuthData {
        @JsonProperty("access_token")
        private String accessToken;
        
        @JsonProperty("token_type")
        private String tokenType;
        
        @JsonProperty("expires_in")
        private Integer expiresIn;
        
        @JsonProperty("refresh_token")
        private String refreshToken;
        
        public String getAccessToken() { return accessToken; }
        public void setAccessToken(String accessToken) { this.accessToken = accessToken; }
        
        public String getTokenType() { return tokenType; }
        public void setTokenType(String tokenType) { this.tokenType = tokenType; }
        
        public Integer getExpiresIn() { return expiresIn; }
        public void setExpiresIn(Integer expiresIn) { this.expiresIn = expiresIn; }
        
        public String getRefreshToken() { return refreshToken; }
        public void setRefreshToken(String refreshToken) { this.refreshToken = refreshToken; }
    }
}

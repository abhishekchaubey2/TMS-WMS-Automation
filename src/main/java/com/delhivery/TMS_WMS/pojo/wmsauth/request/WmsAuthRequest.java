package com.delhivery.TMS_WMS.pojo.wmsauth.request;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * WMS Authentication Request Payload
 */
public class WmsAuthRequest {
    
    @JsonProperty("username")
    private String username;
    
    @JsonProperty("password")
    private String password;
    
    public WmsAuthRequest() {
    }
    
    public WmsAuthRequest(String username, String password) {
        this.username = username;
        this.password = password;
    }
    
    public String getUsername() {
        return username;
    }
    
    public void setUsername(String username) {
        this.username = username;
    }
    
    public String getPassword() {
        return password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
}

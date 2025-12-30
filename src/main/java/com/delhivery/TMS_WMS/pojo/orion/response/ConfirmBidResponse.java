package com.delhivery.TMS_WMS.pojo.orion.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Response POJO for Orion Confirm Bid API
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ConfirmBidResponse {
    
    @JsonProperty("success")
    private Boolean success;
    
    @JsonProperty("message")
    private String message;
    
    @JsonProperty("data")
    private Object data;
    
    public Boolean getSuccess() {
        return success;
    }
    
    public void setSuccess(Boolean success) {
        this.success = success;
    }
    
    public String getMessage() {
        return message;
    }
    
    public void setMessage(String message) {
        this.message = message;
    }
    
    public Object getData() {
        return data;
    }
    
    public void setData(Object data) {
        this.data = data;
    }
}


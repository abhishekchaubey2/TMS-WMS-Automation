package com.delhivery.TMS_WMS.pojo.tms.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Response POJO for creating indent (bulk indent API)
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class CreateIndentResponse {
    
    @JsonProperty("success")
    private Boolean success;
    
    @JsonProperty("message")
    private String message;
    
    @JsonProperty("data")
    private IndentData data;
    
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
    
    public IndentData getData() {
        return data;
    }
    
    public void setData(IndentData data) {
        this.data = data;
    }
    
    /**
     * Inner class for indent data
     */
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class IndentData {
        @JsonProperty("requestId")
        private String requestId;
        
        public String getRequestId() {
            return requestId;
        }
        
        public void setRequestId(String requestId) {
            this.requestId = requestId;
        }
    }
}


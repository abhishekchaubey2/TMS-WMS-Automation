package com.delhivery.TMS_WMS.pojo.wmsorder.response;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Response POJO for WMS Order Creation
 * Matches actual API response structure
 */
public class CreateOrderResponse {
    
    @JsonProperty("request_id")
    private String requestId;
    
    @JsonProperty("_metadata")
    private Object metadata;
    
    @JsonProperty("success")
    private Boolean success;
    
    @JsonProperty("errors")
    private Object errors;
    
    @JsonProperty("message")
    private String message;
    
    @JsonProperty("data")
    private Object data;
    
    public CreateOrderResponse() {
    }
    
    public String getRequestId() { return requestId; }
    public void setRequestId(String requestId) { this.requestId = requestId; }
    
    public Object getMetadata() { return metadata; }
    public void setMetadata(Object metadata) { this.metadata = metadata; }
    
    public Boolean getSuccess() { return success; }
    public void setSuccess(Boolean success) { this.success = success; }
    
    public Object getErrors() { return errors; }
    public void setErrors(Object errors) { this.errors = errors; }
    
    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }
    
    public Object getData() { return data; }
    public void setData(Object data) { this.data = data; }
}

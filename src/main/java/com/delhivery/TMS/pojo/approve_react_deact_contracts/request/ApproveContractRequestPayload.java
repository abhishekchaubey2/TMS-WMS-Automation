package com.delhivery.TMS.pojo.approve_react_deact_contracts.request;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Request payload for contract approval
 */
public class ApproveContractRequestPayload {
    
    @JsonProperty("approval_flag")
    private boolean approvalFlag;
    
    // Default constructor
    public ApproveContractRequestPayload() {
    }
    
    // Constructor with approval flag
    public ApproveContractRequestPayload(boolean approvalFlag) {
        this.approvalFlag = approvalFlag;
    }
    
    // Getter and Setter
    public boolean isApprovalFlag() {
        return approvalFlag;
    }
    
    public void setApprovalFlag(boolean approvalFlag) {
        this.approvalFlag = approvalFlag;
    }
} 
package com.delhivery.TMS.utils;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * Utility class to track contract state throughout test flows
 */
@Builder
@Getter
@Setter
public class ContractStateTracker {
    
    private String contractId;
    private String status;
    private Integer version;
    private Boolean success;
    
    public ContractStateTracker() {}
    
    public ContractStateTracker(String contractId, String status, Integer version, Boolean success) {
        this.contractId = contractId;
        this.status = status;
        this.version = version;
        this.success = success;
    }
    
    /**
     * Update contract state from response data
     */
    public void updateState(String contractId, String status, Integer version, Boolean success) {
        this.contractId = contractId;
        this.status = status;
        this.version = version;
        this.success = success;
        
        System.out.println("=== Contract State Updated ===");
        System.out.println("Contract ID: " + this.contractId);
        System.out.println("Status: " + this.status);
        System.out.println("Version: " + this.version);
        System.out.println("Success: " + this.success);
        System.out.println("=============================");
    }
    
    /**
     * Update only contract ID (for PUT responses)
     */
    public void updateContractId(String contractId) {
        this.contractId = contractId;
        System.out.println("=== Contract ID Updated ===");
        System.out.println("New Contract ID: " + this.contractId);
        System.out.println("===========================");
    }
    
    /**
     * Update only status (for state changes)
     */
    public void updateStatus(String status) {
        this.status = status;
        System.out.println("=== Status Updated ===");
        System.out.println("New Status: " + this.status);
        System.out.println("=====================");
    }
    
    /**
     * Reset state for new test
     */
    public void reset() {
        this.contractId = null;
        this.status = null;
        this.version = null;
        this.success = null;
        System.out.println("=== Contract State Reset ===");
    }
} 
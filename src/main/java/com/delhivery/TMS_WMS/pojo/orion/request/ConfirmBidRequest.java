package com.delhivery.TMS_WMS.pojo.orion.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Request POJO for Orion Confirm Bid API
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ConfirmBidRequest {
    
    @JsonProperty("action_code")
    private String actionCode;
    
    @JsonProperty("action_sub_code")
    private String actionSubCode;
    
    @JsonProperty("trip_id")
    private String tripId;
    
    @JsonProperty("data")
    private ConfirmBidData data;
    
    public ConfirmBidRequest() {
    }
    
    public ConfirmBidRequest(String actionCode, String actionSubCode, String tripId, ConfirmBidData data) {
        this.actionCode = actionCode;
        this.actionSubCode = actionSubCode;
        this.tripId = tripId;
        this.data = data;
    }
    
    public String getActionCode() {
        return actionCode;
    }
    
    public void setActionCode(String actionCode) {
        this.actionCode = actionCode;
    }
    
    public String getActionSubCode() {
        return actionSubCode;
    }
    
    public void setActionSubCode(String actionSubCode) {
        this.actionSubCode = actionSubCode;
    }
    
    public String getTripId() {
        return tripId;
    }
    
    public void setTripId(String tripId) {
        this.tripId = tripId;
    }
    
    public ConfirmBidData getData() {
        return data;
    }
    
    public void setData(ConfirmBidData data) {
        this.data = data;
    }
    
    /**
     * Inner class for confirm bid data
     */
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class ConfirmBidData {
        @JsonProperty("uuid")
        private String uuid;
        
        @JsonProperty("sp_id")
        private String spId;
        
        @JsonProperty("sp_name")
        private String spName;
        
        @JsonProperty("advance_payout")
        private Integer advancePayout;
        
        @JsonProperty("bid_amount")
        private Integer bidAmount;
        
        @JsonProperty("selected_bid")
        private String selectedBid;
        
        @JsonProperty("truck_type")
        private String truckType;
        
        @JsonProperty("truck_display_name")
        private String truckDisplayName;
        
        @JsonProperty("status")
        private String status;
        
        @JsonProperty("placed_truck_passing")
        private Integer placedTruckPassing;
        
        @JsonProperty("driver_phone")
        private String driverPhone;
        
        @JsonProperty("vehicle_no")
        private String vehicleNo;
        
        @JsonProperty("expected_arrival_time_pickup")
        private String expectedArrivalTimePickup;
        
        @JsonProperty("user_confirmation")
        private Boolean userConfirmation;
        
        @JsonProperty("vendor_advance_percent")
        private Integer vendorAdvancePercent;
        
        @JsonProperty("allocated_vendor_classification")
        private String allocatedVendorClassification;
        
        @JsonProperty("allocated_recommended_vendor")
        private Boolean allocatedRecommendedVendor;
        
        @JsonProperty("inventory_list")
        private java.util.List<Object> inventoryList;
        
        @JsonProperty("driver_name")
        private String driverName;
        
        @JsonProperty("allocated_by_name")
        private String allocatedByName;
        
        @JsonProperty("allocated_by_phone")
        private String allocatedByPhone;
        
        // Getters and Setters
        public String getUuid() {
            return uuid;
        }
        
        public void setUuid(String uuid) {
            this.uuid = uuid;
        }
        
        public String getSpId() {
            return spId;
        }
        
        public void setSpId(String spId) {
            this.spId = spId;
        }
        
        public String getSpName() {
            return spName;
        }
        
        public void setSpName(String spName) {
            this.spName = spName;
        }
        
        public Integer getAdvancePayout() {
            return advancePayout;
        }
        
        public void setAdvancePayout(Integer advancePayout) {
            this.advancePayout = advancePayout;
        }
        
        public Integer getBidAmount() {
            return bidAmount;
        }
        
        public void setBidAmount(Integer bidAmount) {
            this.bidAmount = bidAmount;
        }
        
        public String getSelectedBid() {
            return selectedBid;
        }
        
        public void setSelectedBid(String selectedBid) {
            this.selectedBid = selectedBid;
        }
        
        public String getTruckType() {
            return truckType;
        }
        
        public void setTruckType(String truckType) {
            this.truckType = truckType;
        }
        
        public String getTruckDisplayName() {
            return truckDisplayName;
        }
        
        public void setTruckDisplayName(String truckDisplayName) {
            this.truckDisplayName = truckDisplayName;
        }
        
        public String getStatus() {
            return status;
        }
        
        public void setStatus(String status) {
            this.status = status;
        }
        
        public Integer getPlacedTruckPassing() {
            return placedTruckPassing;
        }
        
        public void setPlacedTruckPassing(Integer placedTruckPassing) {
            this.placedTruckPassing = placedTruckPassing;
        }
        
        public String getDriverPhone() {
            return driverPhone;
        }
        
        public void setDriverPhone(String driverPhone) {
            this.driverPhone = driverPhone;
        }
        
        public String getVehicleNo() {
            return vehicleNo;
        }
        
        public void setVehicleNo(String vehicleNo) {
            this.vehicleNo = vehicleNo;
        }
        
        public String getExpectedArrivalTimePickup() {
            return expectedArrivalTimePickup;
        }
        
        public void setExpectedArrivalTimePickup(String expectedArrivalTimePickup) {
            this.expectedArrivalTimePickup = expectedArrivalTimePickup;
        }
        
        public Boolean getUserConfirmation() {
            return userConfirmation;
        }
        
        public void setUserConfirmation(Boolean userConfirmation) {
            this.userConfirmation = userConfirmation;
        }
        
        public Integer getVendorAdvancePercent() {
            return vendorAdvancePercent;
        }
        
        public void setVendorAdvancePercent(Integer vendorAdvancePercent) {
            this.vendorAdvancePercent = vendorAdvancePercent;
        }
        
        public String getAllocatedVendorClassification() {
            return allocatedVendorClassification;
        }
        
        public void setAllocatedVendorClassification(String allocatedVendorClassification) {
            this.allocatedVendorClassification = allocatedVendorClassification;
        }
        
        public Boolean getAllocatedRecommendedVendor() {
            return allocatedRecommendedVendor;
        }
        
        public void setAllocatedRecommendedVendor(Boolean allocatedRecommendedVendor) {
            this.allocatedRecommendedVendor = allocatedRecommendedVendor;
        }
        
        public java.util.List<Object> getInventoryList() {
            return inventoryList;
        }
        
        public void setInventoryList(java.util.List<Object> inventoryList) {
            this.inventoryList = inventoryList;
        }
        
        public String getDriverName() {
            return driverName;
        }
        
        public void setDriverName(String driverName) {
            this.driverName = driverName;
        }
        
        public String getAllocatedByName() {
            return allocatedByName;
        }
        
        public void setAllocatedByName(String allocatedByName) {
            this.allocatedByName = allocatedByName;
        }
        
        public String getAllocatedByPhone() {
            return allocatedByPhone;
        }
        
        public void setAllocatedByPhone(String allocatedByPhone) {
            this.allocatedByPhone = allocatedByPhone;
        }
    }
}


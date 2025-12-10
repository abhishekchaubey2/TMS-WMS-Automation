package com.delhivery.TMS_WMS.pojo.wmsorder.request;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Consignee POJO for WMS Order Creation
 */
public class Consignee {
    
    @JsonProperty("name")
    private String name;
    
    @JsonProperty("address_line1")
    private String addressLine1;
    
    @JsonProperty("pin_code")
    private Integer pinCode;
    
    @JsonProperty("city")
    private String city;
    
    @JsonProperty("state")
    private String state;
    
    @JsonProperty("country")
    private String country;
    
    @JsonProperty("primary_phone_number")
    private String primaryPhoneNumber;
    
    @JsonProperty("secondary_phone_number")
    private String secondaryPhoneNumber;
    
    public Consignee() {
    }
    
    // Getters and Setters
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    
    public String getAddressLine1() { return addressLine1; }
    public void setAddressLine1(String addressLine1) { this.addressLine1 = addressLine1; }
    
    public Integer getPinCode() { return pinCode; }
    public void setPinCode(Integer pinCode) { this.pinCode = pinCode; }
    
    public String getCity() { return city; }
    public void setCity(String city) { this.city = city; }
    
    public String getState() { return state; }
    public void setState(String state) { this.state = state; }
    
    public String getCountry() { return country; }
    public void setCountry(String country) { this.country = country; }
    
    public String getPrimaryPhoneNumber() { return primaryPhoneNumber; }
    public void setPrimaryPhoneNumber(String primaryPhoneNumber) { this.primaryPhoneNumber = primaryPhoneNumber; }
    
    public String getSecondaryPhoneNumber() { return secondaryPhoneNumber; }
    public void setSecondaryPhoneNumber(String secondaryPhoneNumber) { this.secondaryPhoneNumber = secondaryPhoneNumber; }
}

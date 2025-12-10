package com.delhivery.TMS_WMS.pojo.wmsorder.request;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Invoice POJO for WMS Order Creation
 */
public class Invoice {
    
    @JsonProperty("invoice_number")
    private String invoiceNumber;
    
    @JsonProperty("payment_mode")
    private String paymentMode;
    
    @JsonProperty("net_amount")
    private String netAmount;
    
    public Invoice() {
    }
    
    public Invoice(String invoiceNumber, String paymentMode, String netAmount) {
        this.invoiceNumber = invoiceNumber;
        this.paymentMode = paymentMode;
        this.netAmount = netAmount;
    }
    
    // Getters and Setters
    public String getInvoiceNumber() { return invoiceNumber; }
    public void setInvoiceNumber(String invoiceNumber) { this.invoiceNumber = invoiceNumber; }
    
    public String getPaymentMode() { return paymentMode; }
    public void setPaymentMode(String paymentMode) { this.paymentMode = paymentMode; }
    
    public String getNetAmount() { return netAmount; }
    public void setNetAmount(String netAmount) { this.netAmount = netAmount; }
}

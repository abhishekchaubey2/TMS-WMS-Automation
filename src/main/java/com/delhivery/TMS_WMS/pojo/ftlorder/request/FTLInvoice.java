package com.delhivery.TMS_WMS.pojo.ftlorder.request;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * FTL Invoice POJO for FTL Order Creation
 * Includes all invoice fields from FTL API
 */
public class FTLInvoice {
    
    @JsonProperty("invoice_number")
    private String invoiceNumber;
    
    @JsonProperty("payment_mode")
    private String paymentMode;
    
    @JsonProperty("net_amount")
    private String netAmount;
    
    @JsonProperty("invoice_type")
    private String invoiceType;
    
    @JsonProperty("ewaybill")
    private String ewaybill;
    
    @JsonProperty("invoice_value")
    private String invoiceValue;
    
    @JsonProperty("invoice_date")
    private String invoiceDate;
    
    @JsonProperty("invoice_pdf")
    private String invoicePdf;
    
    @JsonProperty("qr_code")
    private String qrCode;
    
    @JsonProperty("total_amt")
    private String totalAmt;
    
    @JsonProperty("currency")
    private String currency;
    
    @JsonProperty("cod_amount")
    private String codAmount;
    
    public FTLInvoice() {
    }
    
    // Getters and Setters
    public String getInvoiceNumber() { return invoiceNumber; }
    public void setInvoiceNumber(String invoiceNumber) { this.invoiceNumber = invoiceNumber; }
    
    public String getPaymentMode() { return paymentMode; }
    public void setPaymentMode(String paymentMode) { this.paymentMode = paymentMode; }
    
    public String getNetAmount() { return netAmount; }
    public void setNetAmount(String netAmount) { this.netAmount = netAmount; }
    
    public String getInvoiceType() { return invoiceType; }
    public void setInvoiceType(String invoiceType) { this.invoiceType = invoiceType; }
    
    public String getEwaybill() { return ewaybill; }
    public void setEwaybill(String ewaybill) { this.ewaybill = ewaybill; }
    
    public String getInvoiceValue() { return invoiceValue; }
    public void setInvoiceValue(String invoiceValue) { this.invoiceValue = invoiceValue; }
    
    public String getInvoiceDate() { return invoiceDate; }
    public void setInvoiceDate(String invoiceDate) { this.invoiceDate = invoiceDate; }
    
    public String getInvoicePdf() { return invoicePdf; }
    public void setInvoicePdf(String invoicePdf) { this.invoicePdf = invoicePdf; }
    
    public String getQrCode() { return qrCode; }
    public void setQrCode(String qrCode) { this.qrCode = qrCode; }
    
    public String getTotalAmt() { return totalAmt; }
    public void setTotalAmt(String totalAmt) { this.totalAmt = totalAmt; }
    
    public String getCurrency() { return currency; }
    public void setCurrency(String currency) { this.currency = currency; }
    
    public String getCodAmount() { return codAmount; }
    public void setCodAmount(String codAmount) { this.codAmount = codAmount; }
}


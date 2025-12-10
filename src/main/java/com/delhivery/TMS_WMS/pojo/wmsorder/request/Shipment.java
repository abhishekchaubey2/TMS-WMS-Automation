package com.delhivery.TMS_WMS.pojo.wmsorder.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

/**
 * Shipment POJO for WMS Order Creation
 */
public class Shipment {
    
    @JsonProperty("priority")
    private String priority;
    
    @JsonProperty("ewaybillExpiryDate")
    private Long ewaybillExpiryDate;
    
    @JsonProperty("shipped_by")
    private String shippedBy;
    
    @JsonProperty("number")
    private String number;
    
    @JsonProperty("workflow")
    private String workflow;
    
    @JsonProperty("fc")
    private String fc;
    
    @JsonProperty("ewaybill_expiry_date")
    private Long ewaybillExpiryDate2;
    
    @JsonProperty("invoice")
    private Invoice invoice;
    
    @JsonProperty("order_lines")
    private List<OrderLine> orderLines;
    
    public Shipment() {
    }
    
    // Getters and Setters
    public String getPriority() { return priority; }
    public void setPriority(String priority) { this.priority = priority; }
    
    public Long getEwaybillExpiryDate() { return ewaybillExpiryDate; }
    public void setEwaybillExpiryDate(Long ewaybillExpiryDate) { this.ewaybillExpiryDate = ewaybillExpiryDate; }
    
    public String getShippedBy() { return shippedBy; }
    public void setShippedBy(String shippedBy) { this.shippedBy = shippedBy; }
    
    public String getNumber() { return number; }
    public void setNumber(String number) { this.number = number; }
    
    public String getWorkflow() { return workflow; }
    public void setWorkflow(String workflow) { this.workflow = workflow; }
    
    public String getFc() { return fc; }
    public void setFc(String fc) { this.fc = fc; }
    
    public Long getEwaybillExpiryDate2() { return ewaybillExpiryDate2; }
    public void setEwaybillExpiryDate2(Long ewaybillExpiryDate2) { this.ewaybillExpiryDate2 = ewaybillExpiryDate2; }
    
    public Invoice getInvoice() { return invoice; }
    public void setInvoice(Invoice invoice) { this.invoice = invoice; }
    
    public List<OrderLine> getOrderLines() { return orderLines; }
    public void setOrderLines(List<OrderLine> orderLines) { this.orderLines = orderLines; }
}

package com.delhivery.TMS_WMS.pojo.ftlorder.request;

import com.delhivery.TMS_WMS.pojo.wmsorder.request.OrderLine;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

/**
 * FTL Shipment POJO for FTL Order Creation
 * Extends WMS Shipment with FTL-specific fields like child_courier
 */
public class FTLShipment {
    
    @JsonProperty("ewaybillExpiryDate")
    private Long ewaybillExpiryDate;
    
    @JsonProperty("shipped_by")
    private String shippedBy;
    
    @JsonProperty("number")
    private String number;
    
    @JsonProperty("workflow")
    private String workflow;
    
    @JsonProperty("child_courier")
    private String childCourier;
    
    @JsonProperty("fc")
    private String fc;
    
    @JsonProperty("ewaybill_expiry_date")
    private Long ewaybillExpiryDate2;
    
    @JsonProperty("invoice")
    private FTLInvoice invoice;
    
    @JsonProperty("order_lines")
    private List<OrderLine> orderLines;
    
    public FTLShipment() {
    }
    
    // Getters and Setters
    public Long getEwaybillExpiryDate() { return ewaybillExpiryDate; }
    public void setEwaybillExpiryDate(Long ewaybillExpiryDate) { this.ewaybillExpiryDate = ewaybillExpiryDate; }
    
    public String getShippedBy() { return shippedBy; }
    public void setShippedBy(String shippedBy) { this.shippedBy = shippedBy; }
    
    public String getNumber() { return number; }
    public void setNumber(String number) { this.number = number; }
    
    public String getWorkflow() { return workflow; }
    public void setWorkflow(String workflow) { this.workflow = workflow; }
    
    public String getChildCourier() { return childCourier; }
    public void setChildCourier(String childCourier) { this.childCourier = childCourier; }
    
    public String getFc() { return fc; }
    public void setFc(String fc) { this.fc = fc; }
    
    public Long getEwaybillExpiryDate2() { return ewaybillExpiryDate2; }
    public void setEwaybillExpiryDate2(Long ewaybillExpiryDate2) { this.ewaybillExpiryDate2 = ewaybillExpiryDate2; }
    
    public FTLInvoice getInvoice() { return invoice; }
    public void setInvoice(FTLInvoice invoice) { this.invoice = invoice; }
    
    public List<OrderLine> getOrderLines() { return orderLines; }
    public void setOrderLines(List<OrderLine> orderLines) { this.orderLines = orderLines; }
}


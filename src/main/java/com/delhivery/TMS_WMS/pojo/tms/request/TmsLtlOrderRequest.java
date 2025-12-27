package com.delhivery.TMS_WMS.pojo.tms.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

/**
 * TMS LTL Order Creation Request
 */
public class TmsLtlOrderRequest {
    
    @JsonProperty("origin")
    private String origin;
    
    @JsonProperty("destination")
    private String destination;
    
    @JsonProperty("readyToManifest")
    private Boolean readyToManifest;
    
    @JsonProperty("isPacked")
    private Boolean isPacked;
    
    @JsonProperty("boxCount")
    private Integer boxCount;
    
    @JsonProperty("orderNumber")
    private String orderNumber;
    
    @JsonProperty("soldToFacility")
    private String soldToFacility;
    
    @JsonProperty("customerReferenceNumber")
    private String customerReferenceNumber;
    
    @JsonProperty("orderPriority")
    private String orderPriority;
    
    @JsonProperty("clientId")
    private String clientId;
    
    @JsonProperty("invoiceDetails")
    private List<InvoiceDetails> invoiceDetails;
    
    @JsonProperty("lineItems")
    private List<LineItem> lineItems;
    
    @JsonProperty("callbackUrl")
    private String callbackUrl;
    
    public TmsLtlOrderRequest() {}
    
    // Getters and Setters
    public String getOrigin() { return origin; }
    public void setOrigin(String origin) { this.origin = origin; }
    
    public String getDestination() { return destination; }
    public void setDestination(String destination) { this.destination = destination; }
    
    public Boolean getReadyToManifest() { return readyToManifest; }
    public void setReadyToManifest(Boolean readyToManifest) { this.readyToManifest = readyToManifest; }
    
    public Boolean getIsPacked() { return isPacked; }
    public void setIsPacked(Boolean isPacked) { this.isPacked = isPacked; }
    
    public Integer getBoxCount() { return boxCount; }
    public void setBoxCount(Integer boxCount) { this.boxCount = boxCount; }
    
    public String getOrderNumber() { return orderNumber; }
    public void setOrderNumber(String orderNumber) { this.orderNumber = orderNumber; }
    
    public String getSoldToFacility() { return soldToFacility; }
    public void setSoldToFacility(String soldToFacility) { this.soldToFacility = soldToFacility; }
    
    public String getCustomerReferenceNumber() { return customerReferenceNumber; }
    public void setCustomerReferenceNumber(String customerReferenceNumber) { this.customerReferenceNumber = customerReferenceNumber; }
    
    public String getOrderPriority() { return orderPriority; }
    public void setOrderPriority(String orderPriority) { this.orderPriority = orderPriority; }
    
    public String getClientId() { return clientId; }
    public void setClientId(String clientId) { this.clientId = clientId; }
    
    public List<InvoiceDetails> getInvoiceDetails() { return invoiceDetails; }
    public void setInvoiceDetails(List<InvoiceDetails> invoiceDetails) { this.invoiceDetails = invoiceDetails; }
    
    public List<LineItem> getLineItems() { return lineItems; }
    public void setLineItems(List<LineItem> lineItems) { this.lineItems = lineItems; }
    
    public String getCallbackUrl() { return callbackUrl; }
    public void setCallbackUrl(String callbackUrl) { this.callbackUrl = callbackUrl; }
    
    /**
     * Invoice Details nested class
     */
    public static class InvoiceDetails {
        @JsonProperty("invoiceNumber")
        private String invoiceNumber;
        
        @JsonProperty("invoiceDt")
        private Long invoiceDt;
        
        @JsonProperty("invoiceAmount")
        private Double invoiceAmount;
        
        @JsonProperty("isDeleted")
        private Boolean isDeleted;
        
        public InvoiceDetails() {}
        
        public String getInvoiceNumber() { return invoiceNumber; }
        public void setInvoiceNumber(String invoiceNumber) { this.invoiceNumber = invoiceNumber; }
        
        public Long getInvoiceDt() { return invoiceDt; }
        public void setInvoiceDt(Long invoiceDt) { this.invoiceDt = invoiceDt; }
        
        public Double getInvoiceAmount() { return invoiceAmount; }
        public void setInvoiceAmount(Double invoiceAmount) { this.invoiceAmount = invoiceAmount; }
        
        public Boolean getIsDeleted() { return isDeleted; }
        public void setIsDeleted(Boolean isDeleted) { this.isDeleted = isDeleted; }
    }
    
    /**
     * Line Item nested class
     */
    public static class LineItem {
        @JsonProperty("quantity")
        private Integer quantity;
        
        @JsonProperty("lineItemNumber")
        private String lineItemNumber;
        
        @JsonProperty("measurementUnit")
        private String measurementUnit;
        
        @JsonProperty("volume")
        private Volume volume;
        
        @JsonProperty("weight")
        private Weight weight;
        
        @JsonProperty("product")
        private Product product;
        
        @JsonProperty("isDeleted")
        private Boolean isDeleted;
        
        public LineItem() {}
        
        public Integer getQuantity() { return quantity; }
        public void setQuantity(Integer quantity) { this.quantity = quantity; }
        
        public String getLineItemNumber() { return lineItemNumber; }
        public void setLineItemNumber(String lineItemNumber) { this.lineItemNumber = lineItemNumber; }
        
        public String getMeasurementUnit() { return measurementUnit; }
        public void setMeasurementUnit(String measurementUnit) { this.measurementUnit = measurementUnit; }
        
        public Volume getVolume() { return volume; }
        public void setVolume(Volume volume) { this.volume = volume; }
        
        public Weight getWeight() { return weight; }
        public void setWeight(Weight weight) { this.weight = weight; }
        
        public Product getProduct() { return product; }
        public void setProduct(Product product) { this.product = product; }
        
        public Boolean getIsDeleted() { return isDeleted; }
        public void setIsDeleted(Boolean isDeleted) { this.isDeleted = isDeleted; }
    }
    
    /**
     * Volume nested class
     */
    public static class Volume {
        @JsonProperty("value")
        private Double value;
        
        @JsonProperty("unit")
        private String unit;
        
        public Volume() {}
        
        public Double getValue() { return value; }
        public void setValue(Double value) { this.value = value; }
        
        public String getUnit() { return unit; }
        public void setUnit(String unit) { this.unit = unit; }
    }
    
    /**
     * Weight nested class
     */
    public static class Weight {
        @JsonProperty("value")
        private Double value;
        
        @JsonProperty("unit")
        private String unit;
        
        public Weight() {}
        
        public Double getValue() { return value; }
        public void setValue(Double value) { this.value = value; }
        
        public String getUnit() { return unit; }
        public void setUnit(String unit) { this.unit = unit; }
    }
    
    /**
     * Product nested class
     */
    public static class Product {
        @JsonProperty("category")
        private String category;
        
        @JsonProperty("code")
        private String code;
        
        @JsonProperty("MRP")
        private Double mrp;
        
        public Product() {}
        
        public String getCategory() { return category; }
        public void setCategory(String category) { this.category = category; }
        
        public String getCode() { return code; }
        public void setCode(String code) { this.code = code; }
        
        public Double getMrp() { return mrp; }
        public void setMrp(Double mrp) { this.mrp = mrp; }
    }
}


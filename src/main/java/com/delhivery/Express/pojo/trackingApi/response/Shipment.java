
package com.delhivery.Express.pojo.trackingApi.response;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.delhivery.Express.pojo.hqTrackingError.response.HQTrackError;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.*;
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "AWB",
    "CODAmount",
    "ChargedWeight",
    "Consignee",
    "DeliveryDate",
    "DestRecieveDate",
    "Destination",
    "DispatchCount",
    "Ewaybill",
    "ExpectedDeliveryDate",
    "Extras",
    "FirstAttemptDate",
    "InvoiceAmount",
    "OrderType",
    "Origin",
    "OriginRecieveDate",
    "OutDestinationDate",
    "PickUpDate",
    "PickedupDate",
    "PickupLocation",
    "PromisedDeliveryDate",
    "Quantity",
    "RTOStartedDate",
    "ReferenceNo",
    "ReturnPromisedDeliveryDate",
    "ReturnedDate",
    "ReverseInTransit",
    "Scans",
    "SenderName",
    "Status"
})
public class Shipment {

    @JsonProperty("AWB")
    public String awb;
    @JsonProperty("CODAmount")
    public long cODAmount;
    @JsonProperty("ChargedWeight")
    public Object chargedWeight;
    @JsonProperty("Consignee")
    public Consignee consignee;
    @JsonProperty("DeliveryDate")
    public Object deliveryDate;
    @JsonProperty("DestRecieveDate")
    public Object destRecieveDate;
    @JsonProperty("Destination")
    public String destination;
    @JsonProperty("DispatchCount")
    public long dispatchCount;
    @JsonProperty("Ewaybill")
    public List<Object> ewaybill;
    @JsonProperty("ExpectedDeliveryDate")
    public Object expectedDeliveryDate;
    @JsonProperty("Extras")
    public String extras;
    @JsonProperty("FirstAttemptDate")
    public Object firstAttemptDate;
    @JsonProperty("InvoiceAmount")
    public long invoiceAmount;
    @JsonProperty("OrderType")
    public String orderType;
    @JsonProperty("Origin")
    public Object origin;
    @JsonProperty("OriginRecieveDate")
    public Object originRecieveDate;
    @JsonProperty("OutDestinationDate")
    public Object outDestinationDate;
    @JsonProperty("PickUpDate")
    public String pickUpDate;
    @JsonProperty("PickedupDate")
    public Object pickedupDate;
    @JsonProperty("PickupLocation")
    public Object pickupLocation;
    @JsonProperty("PromisedDeliveryDate")
    public Object promisedDeliveryDate;
    @JsonProperty("Quantity")
    public String quantity;
    @JsonProperty("RTOStartedDate")
    public Object rTOStartedDate;
    @JsonProperty("ReferenceNo")
    public String referenceNo;
    @JsonProperty("ReturnPromisedDeliveryDate")
    public Object returnPromisedDeliveryDate;
    @JsonProperty("ReturnedDate")
    public Object returnedDate;
    @JsonProperty("ReverseInTransit")
    public boolean reverseInTransit;
    @JsonProperty("Scans")
    public List<Scan> scans;
    @JsonProperty("SenderName")
    public String senderName;
    @JsonProperty("Status")
    public Status status;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}

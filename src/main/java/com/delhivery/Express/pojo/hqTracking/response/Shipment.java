
package com.delhivery.Express.pojo.hqTracking.response;

import java.util.List;

import com.delhivery.Express.pojo.hqTrackingError.response.HQTrackError;
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
    "PickUpDate",
    "Destination",
    "DestRecieveDate",
    "Scans",
    "Status",
    "ExpectedReturnDate",
    "ExpectedDeliveryDate",
    "ReturnPromisedDeliveryDate",
    "Ewaybill",
    "InvoiceAmount",
    "ChargedWeight",
    "PickedupDate",
    "DeliveryDate",
    "SenderName",
    "AWB",
    "DispatchCount",
    "OrderType",
    "ReturnedDate",
    "RTOStartedDate",
    "Extras",
    "FirstAttemptDate",
    "ReverseInTransit",
    "Quantity",
    "Origin",
    "Consignee",
    "ReferenceNo",
    "OutDestinationDate",
    "CODAmount",
    "PromisedDeliveryDate",
    "PickupLocation",
    "OriginRecieveDate"
})
public class Shipment {

    @JsonProperty("PickUpDate")
    public String pickUpDate;
    @JsonProperty("Destination")
    public String destination;
    @JsonProperty("DestRecieveDate")
    public Object destRecieveDate;
    @JsonProperty("Scans")
    public List<Scan> scans;
    @JsonProperty("Status")
    public Status status;
    @JsonProperty("ExpectedReturnDate")
    public String expectedReturnDate;
    @JsonProperty("ExpectedDeliveryDate")
    public String ExpectedDeliveryDate;
    @JsonProperty("ReturnPromisedDeliveryDate")
    public String returnPromisedDeliveryDate;
    @JsonProperty("Ewaybill")
    public List<Object> ewaybill;
    @JsonProperty("InvoiceAmount")
    public long invoiceAmount;
    @JsonProperty("ChargedWeight")
    public long chargedWeight;
    @JsonProperty("PickedupDate")
    public String pickedupDate;
    @JsonProperty("DeliveryDate")
    public Object deliveryDate;
    @JsonProperty("SenderName")
    public String senderName;
    @JsonProperty("AWB")
    public String awb;
    @JsonProperty("DispatchCount")
    public long dispatchCount;
    @JsonProperty("OrderType")
    public String orderType;
    @JsonProperty("ReturnedDate")
    public Object returnedDate;
    @JsonProperty("RTOStartedDate")
    public String rTOStartedDate;
    @JsonProperty("Extras")
    public String extras;
    @JsonProperty("FirstAttemptDate")
    public String firstAttemptDate;
    @JsonProperty("ReverseInTransit")
    public boolean reverseInTransit;
    @JsonProperty("Quantity")
    public String quantity;
    @JsonProperty("Origin")
    public String origin;
    @JsonProperty("Consignee")
    public Consignee consignee;
    @JsonProperty("ReferenceNo")
    public String referenceNo;
    @JsonProperty("OutDestinationDate")
    public Object outDestinationDate;
    @JsonProperty("CODAmount")
    public long cODAmount;
    @JsonProperty("PromisedDeliveryDate")
    public Object promisedDeliveryDate;
    @JsonProperty("PickupLocation")
    public Object pickupLocation;
    @JsonProperty("OriginRecieveDate")
    public String originRecieveDate;

}

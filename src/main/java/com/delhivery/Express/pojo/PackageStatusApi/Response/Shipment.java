package com.delhivery.Express.pojo.PackageStatusApi.Response;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "expectedDate",
    "Slot",
    "PickUpDate",
    "packageType",
    "containmentArea",
    "Destination",
    "covidZone",
    "isAddressSpecific",
    "sourcePin",
    "codAmount",
    "estimatedDate",
    "dispatchCount",
    "Scans",
    "Status",
    "nextTrialDate",
    "mpsAmount",
    "dispatchCenterId",
    "parentReturnCenter",
    "MCount",
    "AWB",
    "consigneeAddress",
    "destinationPin",
    "returnCenter",
    "ReceiverName",
    "isPersonSpecific",
    "productName",
    "isInternational",
    "orderAmount",
    "Origin",
    "returnPromiseDeliveryDate",
    "ReferenceNo",
    "expectedDeliveryDate",
    "productType",
    "reverseInTransit",
    "essential",
    "clientName"
})

public class Shipment {

    @JsonProperty("expectedDate")
    public Object expectedDate;
    @JsonProperty("Slot")
    public Slot slot;
    @JsonProperty("PickUpDate")
    public String pickUpDate;
    @JsonProperty("packageType")
    public String packageType;
    @JsonProperty("containmentArea")
    public boolean containmentArea;
    @JsonProperty("Destination")
    public String destination;
    @JsonProperty("covidZone")
    public String covidZone;
    @JsonProperty("isAddressSpecific")
    public boolean isAddressSpecific;
    @JsonProperty("sourcePin")
    public long sourcePin;
    @JsonProperty("codAmount")
    public Object codAmount;
    @JsonProperty("estimatedDate")
    public Object estimatedDate;
    @JsonProperty("dispatchCount")
    public long dispatchCount;
    @JsonProperty("Scans")
    public List<Scan> scans;
    @JsonProperty("Status")
    public Status status;
    @JsonProperty("nextTrialDate")
    public String nextTrialDate;
    @JsonProperty("mpsAmount")
    public Object mpsAmount;
    @JsonProperty("dispatchCenterId")
    public String dispatchCenterId;
    @JsonProperty("parentReturnCenter")
    public Object parentReturnCenter;
    @JsonProperty("MCount")
    public String mCount;
    @JsonProperty("AWB")
    public String awb;
    @JsonProperty("consigneeAddress")
    public String consigneeAddress;
    @JsonProperty("destinationPin")
    public Object destinationPin;
    @JsonProperty("returnCenter")
    public String returnCenter;
    @JsonProperty("ReceiverName")
    public String receiverName;
    @JsonProperty("isPersonSpecific")
    public Object isPersonSpecific;
    @JsonProperty("productName")
    public String productName;
    @JsonProperty("isInternational")
    public Object isInternational;
    @JsonProperty("orderAmount")
    public Object orderAmount;
    @JsonProperty("Origin")
    public String origin;
    @JsonProperty("returnPromiseDeliveryDate")
    public Object returnPromiseDeliveryDate;
    @JsonProperty("ReferenceNo")
    public String referenceNo;
    @JsonProperty("expectedDeliveryDate")
    public String expectedDeliveryDate;
    @JsonProperty("productType")
    public String productType;
    @JsonProperty("reverseInTransit")
    public Object reverseInTransit;
    @JsonProperty("essential")
    public Object essential;
    @JsonProperty("clientName")
    public String clientName;

}

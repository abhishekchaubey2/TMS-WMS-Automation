
package com.delhivery.Express.pojo.CreateNoDataUplApi.request;

import java.util.List;
import lombok.*;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "lrn",
    "pickup_location",
    "destination_pincode",
    "shipment_count",
    "invoices",
    "dispatch_id"
})
@Builder
@Getter
@Setter
public class CreateNoDataUplApiRequestPayload {

    @JsonProperty("lrn")
    public String lrn;
    @JsonProperty("pickup_location")
    public String pickup_location;
    @JsonProperty("destination_pincode")
    public String destination_pincode;
    @JsonProperty("source")
    public String source;
    @JsonProperty("shipment_count")
    public int shipment_count;
    @JsonProperty("invoices")
    public List<Invoice> invoices = null;
    @JsonProperty("dispatch_id")
    public String dispatch_id;
    @JsonProperty("phone")
    public String phone;
    @JsonProperty("address")
    public String address;
    @JsonProperty("name")
    public String name;
}

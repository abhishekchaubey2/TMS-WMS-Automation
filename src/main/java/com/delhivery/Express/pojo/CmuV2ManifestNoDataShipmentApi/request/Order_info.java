
package com.delhivery.Express.pojo.CmuV2ManifestNoDataShipmentApi.request;

import lombok.*;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "shipment_length",
    "shipment_width",
    "shipment_height",
    "order",
    "waybill",
    "products_desc"
})
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Order_info {

    @JsonProperty("shipment_length")
    public int shipment_length;
    @JsonProperty("shipment_width")
    public int shipment_width;
    @JsonProperty("shipment_height")
    public int shipment_height;
    @JsonProperty("order")
    public String order;
    @JsonProperty("waybill")
    public String waybill;
    @JsonProperty("products_desc")
    public String products_desc;

}

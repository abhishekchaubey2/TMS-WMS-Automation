
package com.delhivery.Express.pojo.CmuV2ManifestNoDataShipmentApi.request;

import lombok.*;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "amount",
    "ewaybill",
    "invoice"
})
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Invoice {

    @JsonProperty("amount")
    public int amount;
    @JsonProperty("ewaybill")
    public String ewaybill;
    @JsonProperty("invoice")
    public String invoice;

}

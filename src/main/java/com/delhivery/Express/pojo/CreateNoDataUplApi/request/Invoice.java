
package com.delhivery.Express.pojo.CreateNoDataUplApi.request;

import lombok.*;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "invoice",
    "amount",
    "ewaybill"
})
@Builder
@Getter
@Setter
public class Invoice {

    @JsonProperty("invoice")
    public String invoice;
    @JsonProperty("amount")
    public int amount;
    @JsonProperty("ewaybill")
    public String ewaybill;

}


package com.delhivery.Express.pojo.FetchClientUuidDetailsResponsePayloadApi.response;

import lombok.*;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "phone_on_rvp_receipt",
    "feapp",
    "shipping_label",
    "sms",
    "dispatch_sheet",
    "address_on_rvp_receipt",
    "name_on_rvp_receipt"
})
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Seller_details_field {

    @JsonProperty("phone_on_rvp_receipt")
    public boolean phone_on_rvp_receipt;
    @JsonProperty("feapp")
    public boolean feapp;
    @JsonProperty("shipping_label")
    public boolean shipping_label;
    @JsonProperty("sms")
    public boolean sms;
    @JsonProperty("dispatch_sheet")
    public boolean dispatch_sheet;
    @JsonProperty("address_on_rvp_receipt")
    public boolean address_on_rvp_receipt;
    @JsonProperty("name_on_rvp_receipt")
    public boolean name_on_rvp_receipt;

}

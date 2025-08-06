
package com.delhivery.Express.pojo.ClientDetails.Response;

import com.delhivery.Express.pojo.BulkNdrEditApi.Response.Data;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SellerDetailsField {

    @JsonProperty("phone_on_rvp_receipt")
    public boolean phoneOnRvpReceipt;
    @JsonProperty("feapp")
    public boolean feapp;
    @JsonProperty("shipping_label")
    public boolean shippingLabel;
    @JsonProperty("sms")
    public boolean sms;
    @JsonProperty("dispatch_sheet")
    public boolean dispatchSheet;
    @JsonProperty("address_on_rvp_receipt")
    public boolean addressOnRvpReceipt;
    @JsonProperty("name_on_rvp_receipt")
    public boolean nameOnRvpReceipt;
}

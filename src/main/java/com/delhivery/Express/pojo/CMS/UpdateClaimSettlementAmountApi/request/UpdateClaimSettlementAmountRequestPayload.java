
package com.delhivery.Express.pojo.CMS.UpdateClaimSettlementAmountApi.request;

import lombok.*;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "pdt",
    "created_at",
    "action_type",
    "settlement_amount"
})
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateClaimSettlementAmountRequestPayload {

    @JsonProperty("pdt")
    public String pdt;
    @JsonProperty("created_at")
    public String created_at;
    @JsonProperty("action_type")
    public String action_type;
    @JsonProperty("settlement_amount")
    public float settlement_amount;

}

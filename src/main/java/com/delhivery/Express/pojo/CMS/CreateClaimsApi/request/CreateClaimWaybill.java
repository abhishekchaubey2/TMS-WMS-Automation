
package com.delhivery.Express.pojo.CMS.CreateClaimsApi.request;

import lombok.*;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "wbn",
    "dispute_type",
    "claim_amount",
    "client_remarks"
})
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateClaimWaybill {

    @JsonProperty("wbn")
    public String wbn;
    @JsonProperty("dispute_type")
    public String dispute_type;
    @JsonProperty("claim_amount")
    public int claim_amount;
    @JsonProperty("client_remarks")
    public String client_remarks;

}

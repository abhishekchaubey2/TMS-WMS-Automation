
package com.delhivery.Express.pojo.CMS.FetchUcpLossClaimsApi.response;

import lombok.*;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "claim_type",
    "wbn",
    "lrn",
    "created_at",
    "dispute_type",
    "claim_amount",
    "client_remarks",
    "status",
    "closing_remarks"
})
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Datum {

    @JsonProperty("claim_type")
    public String claim_type;
    @JsonProperty("wbn")
    public String wbn;
    @JsonProperty("lrn")
    public Object lrn;
    @JsonProperty("created_at")
    public long created_at;
    @JsonProperty("dispute_type")
    public String dispute_type;
    @JsonProperty("claim_amount")
    public int claim_amount;
    @JsonProperty("client_remarks")
    public String client_remarks;
    @JsonProperty("status")
    public String status;
    @JsonProperty("closing_remarks")
    public Object closing_remarks;

}

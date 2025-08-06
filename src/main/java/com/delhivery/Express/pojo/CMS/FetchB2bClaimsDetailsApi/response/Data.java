
package com.delhivery.Express.pojo.CMS.FetchB2bClaimsDetailsApi.response;

import lombok.*;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "id",
    "client_name",
    "liability_amount",
    "claim_amount",
    "settlement_amount",
    "claim_count",
    "liabilty_rule",
    "lrn",
    "status",
    "mwn",
    "created_at",
    "created_by",
    "updated_at",
    "updated_by",
    "approved_at",
    "approved_by",
    "product_value",
    "scan_st",
    "req_id",
    "waybills"
})
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Data {

    @JsonProperty("id")
    public int id;
    @JsonProperty("client_name")
    public String client_name;
    @JsonProperty("liability_amount")
    public int liability_amount;
    @JsonProperty("claim_amount")
    public int claim_amount;
    @JsonProperty("settlement_amount")
    public int settlement_amount;
    @JsonProperty("claim_count")
    public int claim_count;
    @JsonProperty("liabilty_rule")
    public String liabilty_rule;
    @JsonProperty("lrn")
    public String lrn;
    @JsonProperty("status")
    public String status;
    @JsonProperty("mwn")
    public String mwn;
    @JsonProperty("created_at")
    public long created_at;
    @JsonProperty("created_by")
    public String created_by;
    @JsonProperty("updated_at")
    public long updated_at;
    @JsonProperty("updated_by")
    public String updated_by;
    @JsonProperty("approved_at")
    public long approved_at;
    @JsonProperty("approved_by")
    public String approved_by;
    @JsonProperty("product_value")
    public int product_value;
    @JsonProperty("scan_st")
    public String scan_st;
    @JsonProperty("req_id")
    public int req_id;
    @JsonProperty("waybills")
    public Waybills waybills;

}

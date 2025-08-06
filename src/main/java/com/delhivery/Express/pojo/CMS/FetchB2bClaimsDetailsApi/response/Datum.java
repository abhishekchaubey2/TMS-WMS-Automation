
package com.delhivery.Express.pojo.CMS.FetchB2bClaimsDetailsApi.response;

import lombok.*;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "client_name",
    "wbn",
    "dispute_type",
    "claim_amount",
    "product_value",
    "lrn",
    "created_by",
    "updated_by",
    "status",
    "image_link",
    "closing_remarks",
    "prd",
    "mwn",
    "client_remarks",
    "pickup_date",
    "b2b_claim_aggregate_id",
    "scan_st",
    "pdt",
    "pt",
    "nsl",
    "client_gstin",
    "dlv_gstin",
    "liability_amount",
    "settlement_amount",
    "liabilty_rule",
    "approved_by",
    "cn_number",
    "cof_number",
    "approved_at",
    "exception_clause",
    "req_id",
    "miles_id",
    "claimid",
    "created_at",
    "updated_at"
})
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Datum {

    @JsonProperty("client_name")
    public String client_name;
    @JsonProperty("wbn")
    public String wbn;
    @JsonProperty("dispute_type")
    public String dispute_type;
    @JsonProperty("claim_amount")
    public int claim_amount;
    @JsonProperty("product_value")
    public int product_value;
    @JsonProperty("lrn")
    public String lrn;
    @JsonProperty("created_by")
    public String created_by;
    @JsonProperty("updated_by")
    public Object updated_by;
    @JsonProperty("status")
    public String status;
    @JsonProperty("image_link")
    public String image_link;
    @JsonProperty("closing_remarks")
    public Object closing_remarks;
    @JsonProperty("prd")
    public String prd;
    @JsonProperty("mwn")
    public String mwn;
    @JsonProperty("client_remarks")
    public String client_remarks;
    @JsonProperty("pickup_date")
    public String pickup_date;
    @JsonProperty("b2b_claim_aggregate_id")
    public int b2b_claim_aggregate_id;
    @JsonProperty("scan_st")
    public String scan_st;
    @JsonProperty("pdt")
    public String pdt;
    @JsonProperty("pt")
    public String pt;
    @JsonProperty("nsl")
    public String nsl;
    @JsonProperty("client_gstin")
    public Object client_gstin;
    @JsonProperty("dlv_gstin")
    public Object dlv_gstin;
    @JsonProperty("liability_amount")
    public int liability_amount;
    @JsonProperty("settlement_amount")
    public Object settlement_amount;
    @JsonProperty("liabilty_rule")
    public String liabilty_rule;
    @JsonProperty("approved_by")
    public Object approved_by;
    @JsonProperty("cn_number")
    public Object cn_number;
    @JsonProperty("cof_number")
    public Object cof_number;
    @JsonProperty("approved_at")
    public Object approved_at;
    @JsonProperty("exception_clause")
    public Object exception_clause;
    @JsonProperty("req_id")
    public Object req_id;
    @JsonProperty("miles_id")
    public Object miles_id;
    @JsonProperty("claimid")
    public int claimid;
    @JsonProperty("created_at")
    public long created_at;
    @JsonProperty("updated_at")
    public int updated_at;

}

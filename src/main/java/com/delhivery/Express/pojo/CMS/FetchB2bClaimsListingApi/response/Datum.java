
package com.delhivery.Express.pojo.CMS.FetchB2bClaimsListingApi.response;

import lombok.*;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "mwn",
    "liability_amount",
    "liability_rule",
    "last_scan",
    "lrn",
    "awb_cnt",
    "cl_name",
    "tot_claim_amount",
    "product_value",
    "settlement_amount",
    "status",
    "claim_id",
    "closing_remarks",
    "created_at",
    "updated_by"
})
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Datum {

    @JsonProperty("mwn")
    public String mwn;
    @JsonProperty("liability_amount")
    public int liability_amount;
    @JsonProperty("liability_rule")
    public String liability_rule;
    @JsonProperty("last_scan")
    public String last_scan;
    @JsonProperty("lrn")
    public String lrn;
    @JsonProperty("awb_cnt")
    public int awb_cnt;
    @JsonProperty("cl_name")
    public String cl_name;
    @JsonProperty("tot_claim_amount")
    public int tot_claim_amount;
    @JsonProperty("product_value")
    public int product_value;
    @JsonProperty("settlement_amount")
    public int settlement_amount;
    @JsonProperty("status")
    public String status;
    @JsonProperty("claim_id")
    public int claim_id;
    @JsonProperty("closing_remarks")
    public String closing_remarks;
    @JsonProperty("created_at")
    public long created_at;
    @JsonProperty("updated_by")
    public String updated_by;

}

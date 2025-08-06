
package com.delhivery.Express.pojo.CMS.FetchB2cClaimsListingApi.response;

import java.util.LinkedHashMap;
import java.util.Map;
import lombok.*;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "wbn",
    "dispute_type",
    "last_scan",
    "cl_name",
    "claim_amount",
    "product_value",
    "settlement_amount",
    "status",
    "claim_id",
    "client_remarks",
    "closing_remarks",
    "created_at",
    "updated_by",
    "liability_amount",
    "liability_rule",
    "client_images"
})
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Datum {

    @JsonProperty("wbn")
    public String wbn;
    @JsonProperty("dispute_type")
    public String disputeType;
    @JsonProperty("last_scan")
    public String lastScan;
    @JsonProperty("cl_name")
    public String clName;
    @JsonProperty("claim_amount")
    public int claimAmount;
    @JsonProperty("product_value")
    public int productValue;
    @JsonProperty("settlement_amount")
    public int settlementAmount;
    @JsonProperty("status")
    public String status;
    @JsonProperty("claim_id")
    public int claimId;
    @JsonProperty("client_remarks")
    public String clientRemarks;
    @JsonProperty("closing_remarks")
    public String closingRemarks;
    @JsonProperty("created_at")
    public long createdAt;
    @JsonProperty("updated_by")
    public String updatedBy;
    @JsonProperty("liability_amount")
    public int liabilityAmount;
    @JsonProperty("liability_rule")
    public String liabilityRule;
    @JsonProperty("client_images")
    public String clientImages;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}

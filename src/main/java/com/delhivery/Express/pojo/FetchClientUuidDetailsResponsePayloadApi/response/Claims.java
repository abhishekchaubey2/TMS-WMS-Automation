
package com.delhivery.Express.pojo.FetchClientUuidDetailsResponsePayloadApi.response;

import lombok.*;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "fwd_prod_input",
    "cn",
    "rvr_cof_cond",
    "pod_delay",
    "rvr_exc_clause",
    "rvr_prod_input",
    "sop_violation",
    "delay_gtrthrty",
    "fwd_liability",
    "fwd_prod_val",
    "fwd_cof_cond",
    "delay_thrty",
    "fwd_manifest_val",
    "delay_fifteen",
    "rvr_liability_type",
    "fwd_exc_clause",
    "wrong_rto",
    "rvr_manifest_val",
    "closure_penalty",
    "rvr_prod_val",
    "cof",
    "fwd_liability_type",
    "rvr_liability",
    "rto_dispute"
})
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Claims {

    @JsonProperty("fwd_prod_input")
    public String fwd_prod_input;
    @JsonProperty("cn")
    public boolean cn;
    @JsonProperty("rvr_cof_cond")
    public String rvr_cof_cond;
    @JsonProperty("pod_delay")
    public Object pod_delay;
    @JsonProperty("rvr_exc_clause")
    public String rvr_exc_clause;
    @JsonProperty("rvr_prod_input")
    public String rvr_prod_input;
    @JsonProperty("sop_violation")
    public Object sop_violation;
    @JsonProperty("delay_gtrthrty")
    public Object delay_gtrthrty;
    @JsonProperty("fwd_liability")
    public Object fwd_liability;
    @JsonProperty("fwd_prod_val")
    public boolean fwd_prod_val;
    @JsonProperty("fwd_cof_cond")
    public String fwd_cof_cond;
    @JsonProperty("delay_thrty")
    public Object delay_thrty;
    @JsonProperty("fwd_manifest_val")
    public Object fwd_manifest_val;
    @JsonProperty("delay_fifteen")
    public Object delay_fifteen;
    @JsonProperty("rvr_liability_type")
    public String rvr_liability_type;
    @JsonProperty("fwd_exc_clause")
    public String fwd_exc_clause;
    @JsonProperty("wrong_rto")
    public Object wrong_rto;
    @JsonProperty("rvr_manifest_val")
    public Object rvr_manifest_val;
    @JsonProperty("closure_penalty")
    public Object closure_penalty;
    @JsonProperty("rvr_prod_val")
    public boolean rvr_prod_val;
    @JsonProperty("cof")
    public boolean cof;
    @JsonProperty("fwd_liability_type")
    public String fwd_liability_type;
    @JsonProperty("rvr_liability")
    public Object rvr_liability;
    @JsonProperty("rto_dispute")
    public Object rto_dispute;

}

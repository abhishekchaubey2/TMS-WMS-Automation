
package com.delhivery.Express.pojo.MeeshoManifestApi.response;

import java.util.List;
import lombok.*;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "status",
    "waybill",
    "refnum",
    "client",
    "remarks",
    "cod_amount",
    "payment"
})
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Package {

    @JsonProperty("status")
    public String status;
    @JsonProperty("waybill")
    public String waybill;
    @JsonProperty("refnum")
    public String refnum;
    @JsonProperty("client")
    public String client;
    @JsonProperty("remarks")
    public List<String> remarks;
    @JsonProperty("cod_amount")
    public float cod_amount;
    @JsonProperty("payment")
    public String payment;

}

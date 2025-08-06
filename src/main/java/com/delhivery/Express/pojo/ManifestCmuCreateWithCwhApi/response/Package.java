
package com.delhivery.Express.pojo.ManifestCmuCreateWithCwhApi.response;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Package {

    @JsonProperty("status")
    public String status;
    @JsonProperty("client")
    public String client;
    @JsonProperty("sort_code")
    public String sortCode;
    @JsonProperty("remarks")
    public List<String> remarks = null;
    @JsonProperty("waybill")
    public String waybill;
    @JsonProperty("cod_amount")
    public float codAmount;
    @JsonProperty("payment")
    public String payment;
    @JsonProperty("serviceable")
    public boolean serviceable;
    @JsonProperty("refnum")
    public String refnum;

}

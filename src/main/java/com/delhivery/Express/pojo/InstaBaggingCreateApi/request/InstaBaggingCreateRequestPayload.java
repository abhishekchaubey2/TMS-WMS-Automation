
package com.delhivery.Express.pojo.InstaBaggingCreateApi.request;

import lombok.*;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "bd",
    "bi",
    "bs",
    "bt",
    "dpc",
    "rm",
    "st",
    "wbn"
})
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class InstaBaggingCreateRequestPayload {

    @JsonProperty("bd")
    public String bd;
    @JsonProperty("bi")
    public String bi;
    @JsonProperty("bs")
    public Object bs;
    @JsonProperty("bt")
    public String bt;
    @JsonProperty("dpc")
    public boolean dpc;
    @JsonProperty("rm")
    public boolean rm;
    @JsonProperty("capture_weight")
    public boolean capture_weight;
    @JsonProperty("st")
    public String st;
    @JsonProperty("wbn")
    public String wbn;
    @JsonProperty("center_name")
    public String center_name;

}


package com.delhivery.Express.pojo.InstaBaggingSealApi.request;

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
    "wbn",
    "bar",
    "st",
    "rm",
    "dpc"
})
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class InstaBaggingSealRequestPayload {

    @JsonProperty("bd")
    public String bd;
    @JsonProperty("bi")
    public String bi;
    @JsonProperty("bs")
    public String bs;
    @JsonProperty("bt")
    public String bt;
    @JsonProperty("wbn")
    public String wbn;
    @JsonProperty("bar")
    public String bar;
    @JsonProperty("st")
    public String st;
    @JsonProperty("rm")
    public boolean rm;
    @JsonProperty("dpc")
    public String dpc;
    @JsonProperty("center_name")
    public String center_name;

}

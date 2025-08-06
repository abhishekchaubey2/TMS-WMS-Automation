
package com.delhivery.Express.pojo.BagV3Api.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "bs",
    "destination",
    "device_id",
    "ed",
    "origin",
    "sd",
    "u",
    "bt",
    "wbns"
})

public class BagV3RequestPayload {

    @JsonProperty("bs")
    public String bs;
    @JsonProperty("destination")
    public String destination;
    @JsonProperty("device_id")
    public String device_id;
    @JsonProperty("ed")
    public String ed;
    @JsonProperty("origin")
    public String origin;
    @JsonProperty("sd")
    public String sd;
    @JsonProperty("u")
    public String u;
    @JsonProperty("bt")
    public String bt;
    @JsonProperty("wbns")
    public Wbns wbns;

}

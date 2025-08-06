
package com.delhivery.Express.pojo.BagDetailsFetchRestInfoApi.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "is_bfsi",
    "cvd_zn",
    "is_pod",
    "is_rts",
    "esntl"
})
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown=true)
public class Flags {

    @JsonProperty("is_bfsi")
    public boolean is_bfsi;
    @JsonProperty("cvd_zn")
    public Object cvd_zn;
    @JsonProperty("is_pod")
    public boolean is_pod;
    @JsonProperty("is_rts")
    public boolean is_rts;
    @JsonProperty("esntl")
    public boolean esntl;

}

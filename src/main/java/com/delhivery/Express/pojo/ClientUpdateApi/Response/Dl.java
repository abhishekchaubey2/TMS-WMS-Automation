package com.delhivery.Express.pojo.ClientUpdateApi.Response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "gst_tin",
    "state",
    "sez"
})

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Dl {

    @JsonProperty("gst_tin")
    public String gstTin;
    @JsonProperty("state")
    public String state;
    @JsonProperty("sez")
    public boolean sez;

}

package com.delhivery.Express.pojo.FmApiIncoming.Request;

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
    "client",
    "waybill",
    "center"
})
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FmApiIncomingRequestPayload {

    @JsonProperty("client")
    public String client;
    @JsonProperty("waybill")
    public String waybill;
    @JsonProperty("center")
    public String center;

}

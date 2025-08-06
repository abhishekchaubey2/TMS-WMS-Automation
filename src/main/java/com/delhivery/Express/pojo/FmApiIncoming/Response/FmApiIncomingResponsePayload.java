package com.delhivery.Express.pojo.FmApiIncoming.Response;

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
    "wbn",
    "success",
    "error"
})
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FmApiIncomingResponsePayload {

    @JsonProperty("wbn")
    public String wbn;
    @JsonProperty("success")
    public boolean success;
    @JsonProperty("error")
    public String error;

}

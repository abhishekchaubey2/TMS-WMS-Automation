package com.delhivery.Express.pojo.AutobagApi.Request;

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
    "cn",
    "wbn"
})

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AutobagRequestPayload {

    @JsonProperty("cn")
    public String cn;
    @JsonProperty("wbn")
    public String wbn;

}

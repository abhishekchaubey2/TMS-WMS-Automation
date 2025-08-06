package com.delhivery.Express.pojo.ClientDetails.Response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "data",
    "success",
    "error"
})
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ClientDetailsResponsePayload {

    @JsonProperty("data")
    public Data data;
    @JsonProperty("success")
    public boolean success;
    @JsonProperty("error")
    public String error;

}

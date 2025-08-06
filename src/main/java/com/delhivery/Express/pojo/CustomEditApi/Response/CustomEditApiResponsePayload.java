package com.delhivery.Express.pojo.CustomEditApi.Response;

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
    "status",
    "message"
})
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CustomEditApiResponsePayload {

    @JsonProperty("status")
    public boolean status;
    @JsonProperty("message")
    public String message;

}

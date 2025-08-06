package com.delhivery.Express.pojo.EditPhone.Response;

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
    "message",
    "request_id"
})
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EditPhoneResponsePayload {

    @JsonProperty("message")
    public String message;
    @JsonProperty("request_id")
    public String requestId;

}

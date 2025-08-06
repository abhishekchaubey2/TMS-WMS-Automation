package com.delhivery.Express.pojo.ewaybill.APIRestEWayBill.response;

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
        "success"
})
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class APIRestEWayBillResponsePayload {
    @JsonProperty("message")
    public String message;
    @JsonProperty("success")
    public boolean success;
}

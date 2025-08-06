
package com.delhivery.Express.pojo.trackingApiError.response;

import java.util.Map;

import com.delhivery.Express.pojo.trackingApi.response.Status;
import com.fasterxml.jackson.annotation.JsonIgnore;
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
    "message"
})
public class Error {

    @JsonProperty("message")
    public String message;

}

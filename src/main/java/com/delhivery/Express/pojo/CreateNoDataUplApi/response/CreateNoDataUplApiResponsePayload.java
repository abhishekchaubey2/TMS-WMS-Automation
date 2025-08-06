
package com.delhivery.Express.pojo.CreateNoDataUplApi.response;

import lombok.*;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "upl",
    "error",
    "request_id"
})
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateNoDataUplApiResponsePayload {

    @JsonProperty("upl")
    public String upl;
    @JsonProperty("error")
    public String error;
    @JsonProperty("request_id")
    public String request_id;

}

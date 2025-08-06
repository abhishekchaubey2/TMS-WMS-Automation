
package com.delhivery.Express.pojo.InstaBaggingCreateApi.response;

import lombok.*;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "data",
    "success",
    "error"
})
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class InstaBaggingCreateResponsePayload {

    @JsonProperty("data")
    public Data data;
    @JsonProperty("success")
    public boolean success;
    @JsonProperty("error")
    public String error;

}

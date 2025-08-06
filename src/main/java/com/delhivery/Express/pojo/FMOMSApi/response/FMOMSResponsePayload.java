
package com.delhivery.Express.pojo.FMOMSApi.response;


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
public class FMOMSResponsePayload {

    @JsonProperty("data")
    public Data data;
    @JsonProperty("success")
    public boolean success;
    @JsonProperty("error")
    public Error error;

}


package com.delhivery.Express.pojo.hqTrackingError.response;

import java.util.List;

import com.delhivery.Express.pojo.GIApi.response.Datum;
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
    "rmk",
    "Success",
    "Error"
})
public class HQTrackError {

    @JsonProperty("rmk")
    public String rmk;
    @JsonProperty("Success")
    public boolean success;
    @JsonProperty("Error")
    public String error;

}

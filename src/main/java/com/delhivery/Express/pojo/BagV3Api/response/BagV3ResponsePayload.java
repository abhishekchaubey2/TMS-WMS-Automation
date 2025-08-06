
package com.delhivery.Express.pojo.BagV3Api.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.*;

import java.util.List;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "data",
    "success",
    "error"
})

public class BagV3ResponsePayload {

    @JsonProperty("data")
    public List<Datum> data = null;
    @JsonProperty("success")
    public boolean success;
    @JsonProperty("error")
    public List<Object> error = null;

}

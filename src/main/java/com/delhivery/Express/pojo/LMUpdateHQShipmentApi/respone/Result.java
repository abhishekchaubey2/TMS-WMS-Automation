
package com.delhivery.Express.pojo.LMUpdateHQShipmentApi.respone;

import java.util.List;
import lombok.*;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "success",
    "error"
})
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Result {

    @JsonProperty("success")
    public List<Success> success = null;
    @JsonProperty("error")
    public List<Object> error = null;

}

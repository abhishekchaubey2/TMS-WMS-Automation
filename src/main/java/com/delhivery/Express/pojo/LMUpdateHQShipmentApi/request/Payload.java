
package com.delhivery.Express.pojo.LMUpdateHQShipmentApi.request;

import lombok.*;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "source",
    "data",
    "process_insync"
})
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Payload {

    @JsonProperty("source")
    public String source;
    @JsonProperty("data")
    public Data data;
    @JsonProperty("process_insync")
    public boolean process_insync;

}

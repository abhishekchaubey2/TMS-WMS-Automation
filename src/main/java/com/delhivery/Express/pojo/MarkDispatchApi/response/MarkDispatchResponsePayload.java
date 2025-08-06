
package com.delhivery.Express.pojo.MarkDispatchApi.response;

import lombok.*;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "data",
    "meta"
})
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MarkDispatchResponsePayload {

    @JsonProperty("data")
    public Data data;
    @JsonProperty("meta")
    public Meta meta;

}


package com.delhivery.Express.pojo.MarkDispatchApi.responseMPS;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.*;

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
public class MpsMarkDispatchResponsePayload {

    @JsonProperty("data")
    public Data data;
    @JsonProperty("meta")
    public Meta meta;

}

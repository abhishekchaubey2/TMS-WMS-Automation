
package com.delhivery.Express.pojo.FetchClientUuidDetailsResponsePayloadApi.response;

import lombok.*;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "gst_tin",
    "state"
})
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class HR {

    @JsonProperty("gst_tin")
    public String gst_tin;
    @JsonProperty("state")
    public String state;

}

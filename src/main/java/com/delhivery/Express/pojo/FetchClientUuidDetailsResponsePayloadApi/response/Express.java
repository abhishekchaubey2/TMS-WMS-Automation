
package com.delhivery.Express.pojo.FetchClientUuidDetailsResponsePayloadApi.response;

import lombok.*;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "divisor"
})
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Express {

    @JsonProperty("divisor")
    public float divisor;

}

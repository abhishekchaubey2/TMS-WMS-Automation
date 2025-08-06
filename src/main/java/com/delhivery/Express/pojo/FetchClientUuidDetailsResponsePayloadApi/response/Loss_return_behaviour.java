
package com.delhivery.Express.pojo.FetchClientUuidDetailsResponsePayloadApi.response;

import lombok.*;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "Return_remaining"
})
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Loss_return_behaviour {

    @JsonProperty("Return_remaining")
    public boolean return_remaining;

}

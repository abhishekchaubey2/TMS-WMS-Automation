
package com.delhivery.Express.pojo.FetchClientUuidDetailsResponsePayloadApi.response;

import lombok.*;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "authorised",
    "verified"
})
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Weight_verification {

    @JsonProperty("authorised")
    public Authorised authorised;
    @JsonProperty("verified")
    public Verified verified;

}

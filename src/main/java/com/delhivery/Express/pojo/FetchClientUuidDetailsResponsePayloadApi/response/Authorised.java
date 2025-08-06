
package com.delhivery.Express.pojo.FetchClientUuidDetailsResponsePayloadApi.response;

import java.util.List;
import lombok.*;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "pdt"
})
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Authorised {

    @JsonProperty("pdt")
    public List<Object> pdt;

}

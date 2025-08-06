
package com.delhivery.Express.pojo.ManifestCmuCreateWithCwhApi.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.*;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "name"
})
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Pickup_location {

    @JsonProperty("name")
    public String name;

}

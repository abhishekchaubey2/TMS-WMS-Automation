
package com.delhivery.Express.pojo.BagV3Api.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "sd",
    "u"
})

public class Bag {

    @JsonProperty("sd")
    public String sd;
    @JsonProperty("u")
    public String u;

}

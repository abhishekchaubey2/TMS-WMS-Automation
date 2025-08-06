
package com.delhivery.Express.pojo.BagV3Api.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.*;

import java.util.List;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonPropertyOrder({
    "bs",
    "wbn",
    "t"
})

public class Datum {

    @JsonProperty("bs")
    public String bs;
    @JsonProperty("wbn")
    public List<String> wbn = null;
    @JsonProperty("t")
    public String t;

}

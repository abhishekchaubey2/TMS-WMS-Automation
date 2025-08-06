
package com.delhivery.Express.pojo.InstaBaggingSealApi.response;

import java.util.List;
import lombok.*;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "bd",
    "wbns",
    "nsc",
    "bi",
    "bt",
    "bs"
})
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Data {

    @JsonProperty("bd")
    public String bd;
    @JsonProperty("wbns")
    public List<String> wbns;
    @JsonProperty("nsc")
    public int nsc;
    @JsonProperty("bi")
    public String bi;
    @JsonProperty("bt")
    public String bt;
    @JsonProperty("bs")
    public String bs;

}

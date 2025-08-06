
package com.delhivery.Express.pojo.InstaBaggingCreateApi.response;

import java.util.List;
import lombok.*;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "bd",
    "wbns",
    "bi",
    "bt",
    "bs",
    "msg",
    "rm"
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
    @JsonProperty("bi")
    public String bi;
    @JsonProperty("bt")
    public String bt;
    @JsonProperty("bs")
    public Object bs;
    @JsonProperty("msg")
    public String msg;
    @JsonProperty("rm")
    public boolean rm;

}

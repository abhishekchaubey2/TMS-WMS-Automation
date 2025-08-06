
package com.delhivery.Express.pojo.GetManifestUplApi.responseFailure;

import java.util.List;
import lombok.*;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "cod_cnt",
    "paid",
    "cash",
    "tot",
    "cod",
    "w",
    "pick",
    "replacement"
})
@Builder@Getter@Setter@NoArgsConstructor@AllArgsConstructor
public class C {

    @JsonProperty("cod_cnt")
    public long cod_cnt;
    @JsonProperty("paid")
    public long paid;
    @JsonProperty("cash")
    public long cash;
    @JsonProperty("tot")
    public long tot;
    @JsonProperty("cod")
    public long cod;
    @JsonProperty("w")
    public List<Object> w;
    @JsonProperty("pick")
    public long pick;
    @JsonProperty("replacement")
    public long replacement;

}

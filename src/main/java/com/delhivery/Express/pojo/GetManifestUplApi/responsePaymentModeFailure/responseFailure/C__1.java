
package com.delhivery.Express.pojo.GetManifestUplApi.responsePaymentModeFailure.responseFailure;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.*;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "cod",
    "cash",
    "tot",
    "paid",
    "pick",
    "replacement"
})
@Builder@Getter@Setter@NoArgsConstructor@AllArgsConstructor
public class C__1 {

    @JsonProperty("cod")
    public long cod;
    @JsonProperty("cash")
    public double cash;
    @JsonProperty("tot")
    public long tot;
    @JsonProperty("paid")
    public long paid;
    @JsonProperty("pick")
    public long pick;
    @JsonProperty("replacement")
    public long replacement;

}

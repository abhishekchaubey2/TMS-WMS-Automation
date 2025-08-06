
package com.delhivery.Express.pojo.BagDetailsFetchRestInfoApi.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "wbn",
    "user",
    "bs",
    "otp",
    "rmk",
    "dt"
})
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown=true)
public class Inc {

    @JsonProperty("wbn")
    public Wbn wbn;
    @JsonProperty("user")
    public Object user;
    @JsonProperty("bs")
    public Bs bs;
    @JsonProperty("otp")
    public Object otp;
    @JsonProperty("rmk")
    public Object rmk;
    @JsonProperty("dt")
    public Object dt;

}

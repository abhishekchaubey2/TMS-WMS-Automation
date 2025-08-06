
package com.delhivery.Express.pojo.GetManifestUplApi.response;

import lombok.*;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "wbn",
    "rmk",
    "oid"
})
@Builder@Getter@Setter@NoArgsConstructor@AllArgsConstructor
public class Suc {

    @JsonProperty("wbn")
    public String wbn;
    @JsonProperty("rmk")
    public Object rmk;
    @JsonProperty("oid")
    public String oid;

}

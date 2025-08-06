
package com.delhivery.Express.pojo.PkgRemoveFromBagApi.request;

import java.util.HashMap;
import java.util.Map;
import lombok.*;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "bs",
    "wbn",
    "cn"
})
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PkgRemoveFromBagRequestPayload {

    @JsonProperty("bs")
    public String bs;
    @JsonProperty("wbn")
    public String wbn;
    @JsonProperty("cn")
    public String cn;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}

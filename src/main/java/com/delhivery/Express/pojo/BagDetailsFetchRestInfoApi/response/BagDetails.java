
package com.delhivery.Express.pojo.BagDetailsFetchRestInfoApi.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.*;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "bags",
    "packages",
    "invalid",
    "ist"
})
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown=true)
public class BagDetails {

    @JsonProperty("bags")
    public List<Bag> bags = null;
    @JsonProperty("packages")
    public List<Object> packages = null;
    @JsonProperty("invalid")
    public List<Object> invalid = null;
    @JsonProperty("ist")
    public List<Object> ist = null;

}

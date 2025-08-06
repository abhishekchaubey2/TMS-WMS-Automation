
package com.delhivery.Express.pojo.BagDetailsFetchRestInfoApi.response;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "s",
    "u",
    "f"
})
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown=true)
public class Bs {

    @JsonProperty("s")
    public List<Object> s = null;
    @JsonProperty("u")
    public List<Object> u = null;
    @JsonProperty("f")
    public List<Object> f = null;

}

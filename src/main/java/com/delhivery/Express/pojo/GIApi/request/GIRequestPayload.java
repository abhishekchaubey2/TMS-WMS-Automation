
package com.delhivery.Express.pojo.GIApi.request;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class GIRequestPayload {

    @JsonProperty("center")
    public String center;
    @JsonProperty("ref_ids")
    public List<String> ref_ids = null;

}

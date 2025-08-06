
package com.delhivery.Express.pojo.CMS.FetchB2bClaimsDetailsApi.requestParamFilter;

import lombok.*;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "limit",
    "offset",
    "created_at"
})
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FetchB2bClaimsDetailsRequestParam {

    @JsonProperty("limit")
    public int limit;
    @JsonProperty("offset")
    public int offset;
    @JsonProperty("created_at")
    public String created_at;

}

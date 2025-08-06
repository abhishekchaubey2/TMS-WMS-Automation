
package com.delhivery.Express.pojo.CMS.FetchUcpLossClaimsApi.requestParamFilter;

import lombok.*;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "wbn"
})
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FetchUcpLossClaimsRequestParam {

    @JsonProperty("wbn")
    public String wbn;

}

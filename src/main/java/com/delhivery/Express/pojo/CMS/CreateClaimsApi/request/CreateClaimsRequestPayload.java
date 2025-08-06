
package com.delhivery.Express.pojo.CMS.CreateClaimsApi.request;

import java.util.List;
import lombok.*;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "client_name",
    "waybills"
})
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateClaimsRequestPayload {

    @JsonProperty("client_name")
    public String client_name;
    @JsonProperty("waybills")
    public List<CreateClaimWaybill> waybills;

}


package com.delhivery.Express.pojo.CMS.UpdateClaimSettlementAmountApi.response;

import lombok.*;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "status_code",
    "msg"
})
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateClaimSettlementAmountResponsePayload {

    @JsonProperty("status_code")
    public int status_code;
    @JsonProperty("msg")
    public String msg;

}

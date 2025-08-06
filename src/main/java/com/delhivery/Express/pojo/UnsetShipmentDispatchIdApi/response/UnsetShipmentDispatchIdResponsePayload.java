
package com.delhivery.Express.pojo.UnsetShipmentDispatchIdApi.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.*;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "success",
    "remark",
    "failed_wbns"
})
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UnsetShipmentDispatchIdResponsePayload {

    @JsonProperty("success")
    public boolean success;
    @JsonProperty("remark")
    public String remark;
    @JsonProperty("failed_wbns")
    public List<Object> failed_wbns = null;

}

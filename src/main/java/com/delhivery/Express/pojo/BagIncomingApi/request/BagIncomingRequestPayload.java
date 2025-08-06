
package com.delhivery.Express.pojo.BagIncomingApi.request;

import lombok.*;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "slid",
    "ref_id",
    "unexpected"
})
@Builder
@Getter
@Setter
public class BagIncomingRequestPayload {

    @JsonProperty("slid")
    public String slid;
    @JsonProperty("ref_id")
    public String refId;
    @JsonProperty("unexpected")
    public Integer unexpected;

}

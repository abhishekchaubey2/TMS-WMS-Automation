package com.delhivery.Express.pojo.UnsetReturnDispatchId.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UnsetReturnDispatchIdReqPayload {
    @JsonProperty("center_code")
    private String centerCode;

    @JsonProperty("ref_ids")
    private List<String> refIds;

    @JsonProperty("dispatch_id")
    private String dispatchId;

    @JsonProperty("username")
    private String username;

    @JsonProperty("scan_time")
    private String scanTime;
}

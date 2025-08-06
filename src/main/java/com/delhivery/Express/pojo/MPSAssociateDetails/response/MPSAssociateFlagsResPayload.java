package com.delhivery.Express.pojo.MPSAssociateDetails.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class MPSAssociateFlagsResPayload {
    @JsonProperty("wtvr")
    private boolean wtvr;

    @JsonProperty("is_ewbn_req")
    private boolean isEwbnReq;

    @JsonProperty("is_rts")
    private boolean isRts;

    @JsonProperty("esntl")
    private boolean esntl;
}

package com.delhivery.Express.pojo.MPSAssociateDetails.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class MPSAssociateUCIDResPayload {
    @JsonProperty("is_valid")
    private boolean isValid;

    @JsonProperty("is_mobile")
    private boolean isMobile;

    @JsonProperty("uci")
    private String uci;
}

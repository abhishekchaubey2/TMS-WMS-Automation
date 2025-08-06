package com.delhivery.Express.pojo.BagInfo.Request;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "bag"
})
@Builder
@Getter
@Setter
public class BagInfoRequestPayload {

    @JsonProperty("bag")
    public String bag;

}

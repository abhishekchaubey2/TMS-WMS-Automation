package com.delhivery.Express.pojo.ClientCreateUpdate.Request;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Builder
@Getter
@Setter
public class UpdateRegressionClientRequestPayload {

    @JsonProperty("billing_method")
    public String billingMethod;
    @JsonProperty("product_type")
    public String productType;
    @JsonProperty("client_uuid")
    public String clientUuid;

}

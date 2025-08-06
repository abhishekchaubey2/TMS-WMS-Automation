
package com.delhivery.Express.pojo.TestPayloadApi.response;

import lombok.*;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "otp_generation",
    "max_pick_up_tat",
    "b2b_serviceability_type",
    "use_bird_service",
    "cq_phone",
    "restrict_weight_srv"
})
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Data {

    @JsonProperty("otp_generation")
    public String otp_generation;
    @JsonProperty("max_pick_up_tat")
    public int max_pick_up_tat;
    @JsonProperty("b2b_serviceability_type")
    public Object b2b_serviceability_type;
    @JsonProperty("use_bird_service")
    public boolean use_bird_service;
    @JsonProperty("cq_phone")
    public String cq_phone;
    @JsonProperty("restrict_weight_srv")
    public boolean restrict_weight_srv;

}

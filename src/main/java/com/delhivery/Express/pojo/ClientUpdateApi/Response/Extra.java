package com.delhivery.Express.pojo.ClientUpdateApi.Response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "pod_cell",
    "otp_pkup_verification"
})

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Extra {

    @JsonProperty("pod_cell")
    public boolean podCell;
    @JsonProperty("otp_pkup_verification")
    public String otpPkupVerification;

}

package com.delhivery.Express.pojo.UpdateBagStatus.Response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UpdateBagStatusResponsePayload {

    @JsonProperty("data")
    private Data data;
    @JsonProperty("success")
    private Boolean success;
    @JsonProperty("error")
    private String error;

}

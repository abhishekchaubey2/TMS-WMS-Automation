package com.delhivery.Express.pojo.UpdateBagStatus.Request;

import java.util.List;
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
public class UpdateBagStatusRequestPayload {

    @JsonProperty("hold")
    private List<String> hold;
    @JsonProperty("unhold")
    private List<String> unhold;

}

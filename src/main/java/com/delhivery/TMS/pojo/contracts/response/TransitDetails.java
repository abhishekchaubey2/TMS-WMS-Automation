package com.delhivery.TMS.pojo.contracts.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Builder @Getter @Setter @NoArgsConstructor @AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TransitDetails {
    @JsonProperty("tat") private Integer tat;
    @JsonProperty("tatDisplayUnit") private String tatDisplayUnit;
} 
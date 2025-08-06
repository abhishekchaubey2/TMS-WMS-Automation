package com.delhivery.TMS.pojo.contracts.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Builder @Getter @Setter @NoArgsConstructor @AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class LaneDetail {
    @JsonProperty("laneId") private String laneId;
    @JsonProperty("origin") private String origin;
    @JsonProperty("destination") private String destination;
    @JsonProperty("vehicleName") private String vehicleName;
    @JsonProperty("rateDetails") private RateDetails rateDetails;
    @JsonProperty("transitDetails") private TransitDetails transitDetails;
} 
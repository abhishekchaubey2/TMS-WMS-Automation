package com.delhivery.Express.pojo.NewFm.Request;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "pickup_time",
    "pickup_date",
    "pickup_location",
    "expected_package_count"
})
@Builder
@Getter
@Setter
public class NewFmRequestPayload {

    @JsonProperty("pickup_time")
    public String pickupTime;
    @JsonProperty("pickup_date")
    public String pickupDate;
    @JsonProperty("pickup_location")
    public String pickupLocation;
    @JsonProperty("expected_package_count")
    public long expectedPackageCount;

}

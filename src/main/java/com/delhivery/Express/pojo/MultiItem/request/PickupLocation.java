package com.delhivery.Express.pojo.MultiItem.request;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Builder
@Getter
@Setter
//pojo for pickup location
public class PickupLocation {
    @JsonProperty("city")
    public Object city;
    @JsonProperty("name")
    public Object name;
}

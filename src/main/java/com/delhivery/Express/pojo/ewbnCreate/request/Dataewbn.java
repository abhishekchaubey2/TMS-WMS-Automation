package com.delhivery.Express.pojo.ewbnCreate.request;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

import java.util.List;
@Builder
public class Dataewbn {
    @JsonProperty("consolidate")
    public Boolean consolidate;
    @JsonProperty("tripItems")
    public List<TripItem> tripItems;
    @JsonProperty("modeOfTransport")
    public String modeOfTransport;
    @JsonProperty("transhipmentLocation")
    public String transhipmentLocation;
    @JsonProperty("state")
    public String state;
    @JsonProperty("vehicleNo")
    public String vehicleNo;
}

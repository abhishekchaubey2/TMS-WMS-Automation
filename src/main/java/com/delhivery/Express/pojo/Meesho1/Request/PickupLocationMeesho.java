package com.delhivery.Express.pojo.Meesho1.Request;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
@Builder
@Getter
@Setter

public class PickupLocationMeesho {
    @JsonProperty("shp_name")
    public Object shpName;
    @JsonProperty("name")
    public Object name;
}

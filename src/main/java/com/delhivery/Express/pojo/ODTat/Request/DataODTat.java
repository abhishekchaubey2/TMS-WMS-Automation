package com.delhivery.Express.pojo.ODTat.Request;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "ltl_air_tat",
    "rvp_air_tat",
    "rvp_air_cutoff",
    "forward_fast_b2b_cutoff",
    "ltl_regular_tat",
    "forward_fast_b2c_cutoff",
    "forward_surface_cutoff",
    "heavy_cutoff",
    "id",
    "origin_city",
    "forward_express_cutoff",
    "destination_city",
    "flash_air_tat",
    "heavy_tat",
    "forward_fast_b2b_tat",
    "return_cutoff",
    "next_b2c_surface_cutoff",
    "ltl_air_cutoff",
    "dto_tat",
    "ltl_regular_cutoff",
    "air_lane_exists",
    "return_tat",
    "dto_cutoff",
    "forward_fast_b2c_tat",
    "flash_air_cutoff",
    "next_b2c_surface_tat",
    "forward_express_tat",
    "forward_surface_tat"
})

@Builder
@Getter
@Setter
public class DataODTat {

    @JsonProperty("ltl_air_tat")
    public long ltlAirTat;
    @JsonProperty("rvp_air_tat")
    public long rvpAirTat;
    @JsonProperty("rvp_air_cutoff")
    public String rvpAirCutoff;
    @JsonProperty("forward_fast_b2b_cutoff")
    public String forwardFastB2bCutoff;
    @JsonProperty("ltl_regular_tat")
    public long ltlRegularTat;
    @JsonProperty("forward_fast_b2c_cutoff")
    public String forwardFastB2cCutoff;
    @JsonProperty("forward_surface_cutoff")
    public String forwardSurfaceCutoff;
    @JsonProperty("heavy_cutoff")
    public String heavyCutoff;
    @JsonProperty("id")
    public long id;
    @JsonProperty("origin_city")
    public String originCity;
    @JsonProperty("forward_express_cutoff")
    public String forwardExpressCutoff;
    @JsonProperty("destination_city")
    public String destinationCity;
    @JsonProperty("flash_air_tat")
    public long flashAirTat;
    @JsonProperty("heavy_tat")
    public long heavyTat;
    @JsonProperty("forward_fast_b2b_tat")
    public long forwardFastB2bTat;
    @JsonProperty("return_cutoff")
    public String returnCutoff;
    @JsonProperty("next_b2c_surface_cutoff")
    public String nextB2cSurfaceCutoff;
    @JsonProperty("ltl_air_cutoff")
    public String ltlAirCutoff;
    @JsonProperty("dto_tat")
    public long dtoTat;
    @JsonProperty("ltl_regular_cutoff")
    public String ltlRegularCutoff;
    @JsonProperty("air_lane_exists")
    public boolean airLaneExists;
    @JsonProperty("return_tat")
    public long returnTat;
    @JsonProperty("dto_cutoff")
    public String dtoCutoff;
    @JsonProperty("forward_fast_b2c_tat")
    public long forwardFastB2cTat;
    @JsonProperty("flash_air_cutoff")
    public String flashAirCutoff;
    @JsonProperty("next_b2c_surface_tat")
    public long nextB2cSurfaceTat;
    @JsonProperty("forward_express_tat")
    public long forwardExpressTat;
    @JsonProperty("forward_surface_tat")
    public long forwardSurfaceTat;

}

package com.delhivery.Express.pojo.FetchDcCenter.Response;

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
    "code",
    "name",
    "city",
    "state",
    "country",
    "region",
    "p_center",
    "latitude",
    "longitude",
    "unicode_name",
    "facilities",
    "active",
    "facility_type",
    "enable_odx_fm",
    "enable_lmap"
})
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FetchDcCenterResponsePayload {

    @JsonProperty("code")
    public String code;
    @JsonProperty("name")
    public String name;
    @JsonProperty("city")
    public String city;
    @JsonProperty("state")
    public String state;
    @JsonProperty("country")
    public String country;
    @JsonProperty("region")
    public String region;
    @JsonProperty("p_center")
    public Object pCenter;
    @JsonProperty("latitude")
    public long latitude;
    @JsonProperty("longitude")
    public long longitude;
    @JsonProperty("unicode_name")
    public String unicodeName;
    @JsonProperty("facilities")
    public String facilities;
    @JsonProperty("active")
    public boolean active;
    @JsonProperty("facility_type")
    public FacilityType facilityType;
    @JsonProperty("enable_odx_fm")
    public boolean enableOdxFm;
    @JsonProperty("enable_lmap")
    public boolean enableLmap;

}

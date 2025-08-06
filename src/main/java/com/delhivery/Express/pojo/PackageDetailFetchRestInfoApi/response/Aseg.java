
package com.delhivery.Express.pojo.PackageDetailFetchRestInfoApi.response;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.Getter;

@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "pin_loc_matched",
    "loc",
    "name_hierarchy",
    "pin",
    "invalid_add",
    "rec_id",
    "lon",
    "inv_add_scr",
    "lat",
    "score",
    "city_identified",
    "ud",
    "locality_type",
    "city_supported",
    "pinV3",
    "invalid_add_code"
})
@JsonIgnoreProperties(ignoreUnknown=true)
public class Aseg {

    @JsonProperty("pin_loc_matched")
    public boolean pinLocMatched;
    @JsonProperty("loc")
    public String loc;
    @JsonProperty("name_hierarchy")
    public String nameHierarchy;
    @JsonProperty("pin")
    public String pin;
    @JsonProperty("invalid_add")
    public boolean invalidAdd;
    @JsonProperty("rec_id")
    public String recId;
    @JsonProperty("lon")
    public double lon;
    @JsonProperty("inv_add_scr")
    public long invAddScr;
    @JsonProperty("lat")
    public double lat;
    @JsonProperty("score")
    public double score;
    @JsonProperty("city_identified")
    public String cityIdentified;
    @JsonProperty("ud")
    public String ud;
    @JsonProperty("locality_type")
    public String localityType;
    @JsonProperty("city_supported")
    public boolean citySupported;
    @JsonProperty("pinV3")
    public List<Long> pinV3 = null;
    @JsonProperty("invalid_add_code")
    public String invalidAddCode;

}

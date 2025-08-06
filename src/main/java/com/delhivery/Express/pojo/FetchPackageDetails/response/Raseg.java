package com.delhivery.Express.pojo.FetchPackageDetails.response;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown=true)
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
"ud",
"city_identified",
"city_mismatch",
"locality_type",
"city_supported",
"pinV3",
"invalid_add_code"
})

public class Raseg {

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
public float lon;
@JsonProperty("inv_add_scr")
public long invAddScr;
@JsonProperty("lat")
public float lat;
@JsonProperty("ud")
public String ud;
@JsonProperty("city_identified")
public String cityIdentified;
@JsonProperty("city_mismatch")
public Object cityMismatch;
@JsonProperty("locality_type")
public String localityType;
@JsonProperty("city_supported")
public boolean citySupported;
@JsonProperty("pinV3")
public List<Long> pinV3;
@JsonProperty("invalid_add_code")
public String invalidAddCode;

}

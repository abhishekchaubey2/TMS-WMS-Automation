package com.delhivery.Express.pojo.FetchPackageDetails2.response;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

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
public Object pinLocMatched;
@JsonProperty("loc")
public Object loc;
@JsonProperty("name_hierarchy")
public Object nameHierarchy;
@JsonProperty("pin")
public Object pin;
@JsonProperty("invalid_add")
public Object invalidAdd;
@JsonProperty("rec_id")
public Object recId;
@JsonProperty("lon")
public Object lon;
@JsonProperty("inv_add_scr")
public Object invAddScr;
@JsonProperty("lat")
public Object lat;
@JsonProperty("ud")
public Object ud;
@JsonProperty("city_identified")
public Object cityIdentified;
@JsonProperty("city_mismatch")
public Object cityMismatch;
@JsonProperty("locality_type")
public Object localityType;
@JsonProperty("city_supported")
public Object citySupported;
@JsonProperty("pinV3")
public List<Object> pinV3;
@JsonProperty("invalid_add_code")
public Object invalidAddCode;

}

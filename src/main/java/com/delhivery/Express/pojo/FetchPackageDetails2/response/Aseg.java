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
"score",
"city_identified",
"ud",
"locality_type",
"city_supported",
"pinV3",
"invalid_add_code"
})

public class Aseg {
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
	@JsonProperty("score")
	public Object score;
	@JsonProperty("city_identified")
	public Object cityIdentified;
	@JsonProperty("ud")
	public Object ud;
	@JsonProperty("locality_type")
	public Object localityType;
	@JsonProperty("city_supported")
	public Object citySupported;
	@JsonProperty("pinV3")
	public List<Integer> pinV3;
	@JsonProperty("invalid_add_code")
	public Object invalidAddCode;
}

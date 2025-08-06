package com.delhivery.Express.pojo.FetchPackageDetailsSecond.response;
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
	public Boolean pinLocMatched;
	@JsonProperty("loc")
	public String loc;
	@JsonProperty("name_hierarchy")
	public String nameHierarchy;
	@JsonProperty("pin")
	public String pin;
	@JsonProperty("invalid_add")
	public Boolean invalidAdd;
	@JsonProperty("rec_id")
	public String recId;
	@JsonProperty("lon")
	public Float lon;
	@JsonProperty("inv_add_scr")
	public Integer invAddScr;
	@JsonProperty("lat")
	public Float lat;
	@JsonProperty("score")
	public Float score;
	@JsonProperty("city_identified")
	public String cityIdentified;
	@JsonProperty("ud")
	public String ud;
	@JsonProperty("locality_type")
	public String localityType;
	@JsonProperty("city_supported")
	public Boolean citySupported;
	@JsonProperty("pinV3")
	public List<Integer> pinV3;
	@JsonProperty("invalid_add_code")
	public String invalidAddCode;
}

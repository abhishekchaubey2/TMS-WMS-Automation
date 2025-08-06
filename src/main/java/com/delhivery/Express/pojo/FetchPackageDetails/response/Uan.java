package com.delhivery.Express.pojo.FetchPackageDetails.response;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown=true)
@JsonPropertyOrder({
"slot",
"add_styp",
"add_typ",
"long",
"error_radius",
"uai",
"geo_source",
"visited_before",
"apt_req",
"lat",
"slot_src",
"last_visited_date"
})

public class Uan {

@JsonProperty("slot")
public List<Object> slot;
@JsonProperty("add_styp")
public Object addStyp;
@JsonProperty("add_typ")
public Object addTyp;
@JsonProperty("long")
public Float _long;
@JsonProperty("error_radius")
public Integer errorRadius;
@JsonProperty("uai")
public String uai;
@JsonProperty("geo_source")
public String geoSource;
@JsonProperty("visited_before")
public Boolean visitedBefore;
@JsonProperty("apt_req")
public Boolean aptReq;
@JsonProperty("lat")
public Float lat;
@JsonProperty("slot_src")
public Object slotSrc;
@JsonProperty("last_visited_date")
public Object lastVisitedDate;

}
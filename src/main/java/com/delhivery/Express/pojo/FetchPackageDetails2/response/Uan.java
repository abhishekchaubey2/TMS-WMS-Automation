package com.delhivery.Express.pojo.FetchPackageDetails2.response;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;


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
public Object _long;
@JsonProperty("error_radius")
public Object errorRadius;
@JsonProperty("uai")
public Object uai;
@JsonProperty("geo_source")
public Object geoSource;
@JsonProperty("visited_before")
public Object visitedBefore;
@JsonProperty("apt_req")
public Object aptReq;
@JsonProperty("lat")
public Object lat;
@JsonProperty("slot_src")
public Object slotSrc;
@JsonProperty("last_visited_date")
public Object lastVisitedDate;

}
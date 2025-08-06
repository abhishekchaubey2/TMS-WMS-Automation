
package com.delhivery.Express.pojo.PackageDetailFetchRestInfoApi.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;

@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
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
@JsonIgnoreProperties(ignoreUnknown=true)
public class Ruan {

    @JsonProperty("slot")
    public Object slot;
    @JsonProperty("add_styp")
    public String addStyp;
    @JsonProperty("add_typ")
    public String addTyp;
    @JsonProperty("long")
    public double _long;
    @JsonProperty("error_radius")
    public double errorRadius;
    @JsonProperty("uai")
    public String uai;
    @JsonProperty("geo_source")
    public String geoSource;
    @JsonProperty("visited_before")
    public boolean visitedBefore;
    @JsonProperty("apt_req")
    public boolean aptReq;
    @JsonProperty("lat")
    public double lat;
    @JsonProperty("slot_src")
    public Object slotSrc;
    @JsonProperty("last_visited_date")
    public Object lastVisitedDate;

}

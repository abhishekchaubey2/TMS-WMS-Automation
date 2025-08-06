package pojo.FetchPackgeDetails.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class PackageInfoUanResPayload {
    @JsonProperty("slot")
    private Object slot;

    @JsonProperty("add_styp")
    private String addSTyp;

    @JsonProperty("add_typ")
    private String addTyp;

    @JsonProperty("long")
    private double _long;

    @JsonProperty("error_radius")
    private double errorRadius;

    @JsonProperty("uai")
    private String uai;

    @JsonProperty("geo_source")
    private String geoSource;

    @JsonProperty("visited_before")
    private boolean visitedBefore;

    @JsonProperty("apt_req")
    private boolean aptReq;

    @JsonProperty("lat")
    private double lat;

    @JsonProperty("slot_src")
    private Object slotSrc;

    @JsonProperty("last_visited_date")
    private Object lastVisitedDate;
}

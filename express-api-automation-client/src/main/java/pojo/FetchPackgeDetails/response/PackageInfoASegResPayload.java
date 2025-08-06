package pojo.FetchPackgeDetails.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

@Getter
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class PackageInfoASegResPayload {
    @JsonProperty("pin_loc_matched")
    private boolean pinLocMatched;

    @JsonProperty("loc")
    private String loc;

    @JsonProperty("name_hierarchy")
    private String nameHierarchy;

    @JsonProperty("pin")
    private String pin;

    @JsonProperty("invalid_add")
    private boolean invalidAdd;

    @JsonProperty("rec_id")
    private String recId;

    @JsonProperty("lon")
    private double lon;

    @JsonProperty("inv_add_scr")
    private long invAddScr;

    @JsonProperty("lat")
    private double lat;

    @JsonProperty("score")
    private double score;

    @JsonProperty("city_identified")
    private String cityIdentified;

    @JsonProperty("ud")
    private String ud;

    @JsonProperty("locality_type")
    private String localityType;

    @JsonProperty("city_supported")
    private boolean citySupported;

    @JsonProperty("pinV3")
    private List<Long> pinV3;

    @JsonProperty("invalid_add_code")
    private String invalidAddCode;
}

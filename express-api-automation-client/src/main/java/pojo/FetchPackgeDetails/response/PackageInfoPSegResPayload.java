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
public class PackageInfoPSegResPayload {
    @JsonProperty("dg")
    private boolean dg;

    @JsonProperty("cat")
    private String cat;

    @JsonProperty("scat")
    private String scat;

    @JsonProperty("prohibited")
    private boolean prohibited;

    @JsonProperty("cat_confidence")
    private double catConfidence;

    @JsonProperty("dg_score")
    private long dgScore;

    @JsonProperty("ud")
    private String ud;

    @JsonProperty("cft")
    private long cft;

    @JsonProperty("frgl")
    private boolean frGl;
}

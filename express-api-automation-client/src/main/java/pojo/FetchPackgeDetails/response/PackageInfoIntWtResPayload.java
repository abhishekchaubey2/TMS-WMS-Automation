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
public class PackageInfoIntWtResPayload {
    @JsonProperty("wtr")
    private String wtr;

    @JsonProperty("b")
    private double b;

    @JsonProperty("wt")
    private double wt;

    @JsonProperty("h")
    private double h;

    @JsonProperty("l")
    private double l;

    @JsonProperty("iwt")
    private long iwt;

    @JsonProperty("v")
    private double v;

    @JsonProperty("div")
    private long div;
}

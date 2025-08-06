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
public class PackageInfoChWtResPayload {
    @JsonProperty("rv")
    private long rv;

    @JsonProperty("b")
    private long b;

    @JsonProperty("dwt")
    private long dwt;

    @JsonProperty("h")
    private long h;

    @JsonProperty("wtr")
    private String wtr;

    @JsonProperty("l")
    private long l;

    @JsonProperty("wt")
    private long wt;

    @JsonProperty("div")
    private long div;
}

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
public class PackageInfoTargetResPayload {
    @JsonProperty("cedd")
    private String cEdd;

    @JsonProperty("edd")
    private String edd;

    @JsonProperty("dst")
    private String dst;

    @JsonProperty("cpdd")
    private String cPdd;

    @JsonProperty("ipdd")
    private String iPdd;

    @JsonProperty("nofly")
    private Boolean noFly;

    @JsonProperty("pdd")
    private String pdd;
}

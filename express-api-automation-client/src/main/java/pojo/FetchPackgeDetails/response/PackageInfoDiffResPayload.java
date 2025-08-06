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
public class PackageInfoDiffResPayload {
    @JsonProperty("dpf")
    private long dpf;

    @JsonProperty("dfpd")
    private long dFpd;

    @JsonProperty("dipd")
    private double dIpd;

    @JsonProperty("def")
    private long def;

    @JsonProperty("dfbi")
    private double dFbi;
}

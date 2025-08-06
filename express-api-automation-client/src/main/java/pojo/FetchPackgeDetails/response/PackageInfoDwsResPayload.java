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
public class PackageInfoDwsResPayload {
    @JsonProperty("is_box")
    private boolean isBox;

    @JsonProperty("rv")
    private double rv;

    @JsonProperty("b")
    private double b;

    @JsonProperty("h")
    private double h;

    @JsonProperty("image")
    private PackageInfoImageResPayload image;

    @JsonProperty("l")
    private double l;

    @JsonProperty("min_th")
    private boolean minTh;

    @JsonProperty("swt")
    private double swt;
}

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
public class PackageInfoSlotLmFwdResPayload {
    @JsonProperty("en_utc")
    private long enUtc;

    @JsonProperty("src")
    private String src;

    @JsonProperty("d_src")
    private String dSrc;

    @JsonProperty("st_utc")
    private long stUtc;

    @JsonProperty("t_src_app")
    private String tSrcApp;

    @JsonProperty("t_src")
    private String tSrc;

    @JsonProperty("time")
    private List<PackageInfoTimeResPayload> time;

    @JsonProperty("date")
    private String date;

    @JsonProperty("src_app")
    private String srcApp;

    @JsonProperty("d_src_app")
    private String dSrcApp;
}

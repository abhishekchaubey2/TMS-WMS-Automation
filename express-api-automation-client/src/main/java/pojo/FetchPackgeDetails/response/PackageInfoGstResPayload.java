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
public class PackageInfoGstResPayload {
    @JsonProperty("dcn")
    private List<String> dcn;

    @JsonProperty("cngstin")
    private String cnGstIn;

    @JsonProperty("hsncode")
    private List<String> hsnCode;

    @JsonProperty("mvmtgstin")
    private String mvMtGstIn;

    @JsonProperty("irn")
    private String irn;

    @JsonProperty("slgstin")
    private String slgGtin;

    @JsonProperty("clgstin")
    private String clGstIn;
}

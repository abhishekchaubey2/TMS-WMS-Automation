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
public class PackageInfoNslResPayload {
    @JsonProperty("date")
    private String date;

    @JsonProperty("dt")
    private List<Long> dt;

    @JsonProperty("code")
    public String code;

    @JsonProperty("slid")
    public String slid;
}

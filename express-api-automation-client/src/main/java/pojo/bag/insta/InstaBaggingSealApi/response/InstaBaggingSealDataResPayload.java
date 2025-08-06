package pojo.bag.insta.InstaBaggingSealApi.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

@Getter
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class InstaBaggingSealDataResPayload {
    @JsonProperty("bd")
    private String bd;

    @JsonProperty("wbns")
    private List<String> wbns;

    @JsonProperty("nsc")
    private int nsc;

    @JsonProperty("bi")
    private String bi;

    @JsonProperty("bt")
    private String bt;

    @JsonProperty("bs")
    private String bs;
}

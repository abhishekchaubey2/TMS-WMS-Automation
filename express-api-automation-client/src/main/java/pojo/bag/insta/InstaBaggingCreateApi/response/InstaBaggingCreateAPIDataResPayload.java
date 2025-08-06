package pojo.bag.insta.InstaBaggingCreateApi.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

@Getter
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class InstaBaggingCreateAPIDataResPayload {
    @JsonProperty("bd")
    private String bd;

    @JsonProperty("wbns")
    private List<String> wbns;

    @JsonProperty("bi")
    private String bi;

    @JsonProperty("bt")
    private String bt;

    @JsonProperty("bs")
    private Object bs;

    @JsonProperty("msg")
    private String msg;

    @JsonProperty("rm")
    private boolean rm;
}

package pojo.bag.BagV3Api.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

@Getter
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BagV3DataResponsePayload {
    @JsonProperty("bs")
    private String bs;

    @JsonProperty("wbn")
    private List<String> wbn;

    @JsonProperty("t")
    private String t;
}

package pojo.bag.CreateBagV2Api.Response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

@Getter
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CreateBagV2DataResPayload {
    @JsonProperty("wbn")
    private List<String> wbn;

    @JsonProperty("t")
    private String t;

    @JsonProperty("bs")
    private String bs;
}

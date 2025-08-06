package pojo.bag.BagV3Api.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

@Getter
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BagV3ResponsePayload {
    @JsonProperty("data")
    private List<BagV3DataResponsePayload> data;

    @JsonProperty("success")
    private boolean success;

    @JsonProperty("error")
    private List<Object> error = null;

}

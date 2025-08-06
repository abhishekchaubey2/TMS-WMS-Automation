package pojo.bag.CreateBagV2Api.Response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

@Getter
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CreateBagV2ApiResponsePayload {
    @JsonProperty("data")
    private List<CreateBagV2DataResPayload> data;

    @JsonProperty("success")
    private boolean success;

    @JsonProperty("error")
    private List<Object> error;
}

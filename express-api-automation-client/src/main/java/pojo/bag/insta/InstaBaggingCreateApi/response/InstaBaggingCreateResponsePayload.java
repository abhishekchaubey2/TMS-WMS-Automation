package pojo.bag.insta.InstaBaggingCreateApi.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class InstaBaggingCreateResponsePayload {
    @JsonProperty("data")
    private InstaBaggingCreateAPIDataResPayload data;

    @JsonProperty("success")
    private boolean success;

    @JsonProperty("error")
    private String error;
}

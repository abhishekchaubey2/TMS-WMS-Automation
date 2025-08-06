package pojo.BagIncomingApi.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BagIncomingResponsePayload {
    @JsonProperty("data")
    private BagIncomingDataResPayload data;

    @JsonProperty("bi")
    private String bi;

    @JsonProperty("success")
    private Boolean success;

    @JsonProperty("error")
    private String error;
}
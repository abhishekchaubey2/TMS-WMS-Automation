package pojo.ApplyNslApi.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApplyNslResponsePayload {
    @JsonProperty("message")
    private String message;

    @JsonProperty("data")
    private String data;

    @JsonProperty("success")
    private boolean success;

    @JsonProperty("msg")
    private String msg;
}

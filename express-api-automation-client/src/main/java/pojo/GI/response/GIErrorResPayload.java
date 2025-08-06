package pojo.GI.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import lombok.ToString;

@Getter
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class GIErrorResPayload {
    @JsonProperty("msg")
    private String msg;

    @JsonProperty("message")
    private String message;

    @JsonProperty("code")
    private Integer code;
}
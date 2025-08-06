package pojo.FmApiIncoming.Response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import lombok.ToString;

@Getter
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class FmApiIncomingResponsePayload {
    @JsonProperty("wbn")
    private String wbn;

    @JsonProperty("success")
    private boolean success;

    @JsonProperty("error")
    private String error;
}

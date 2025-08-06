package pojo.GI.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import lombok.ToString;

import java.util.List;

@Getter
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class GIResponsePayload {
    @JsonProperty("status")
    private int status;

    @JsonProperty("data")
    private List<GIDataResPayload> data;

    @JsonProperty("error")
    private List<GIErrorResPayload> error;
}

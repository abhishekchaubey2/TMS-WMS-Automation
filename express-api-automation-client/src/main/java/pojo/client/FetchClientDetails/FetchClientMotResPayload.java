package pojo.client.FetchClientDetails;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class FetchClientMotResPayload {
    @JsonProperty("express")
    private FetchClientMotExpressResPayload express;

    @JsonProperty("surface")
    private FetchClientMotSurfaceResPayload surface;
}
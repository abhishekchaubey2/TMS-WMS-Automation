package pojo.client.FetchClientDetails;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class FetchClientMotSurfaceResPayload {
    @JsonProperty("divisor")
    private float divisor;
}
package pojo.BagIncomingApi.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BagIncomingDataResPayload {
    @JsonProperty("hold")
    private Boolean hold;
}
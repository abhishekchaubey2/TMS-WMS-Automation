package pojo.client.FetchClientDetails;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class FetchClientDetailsResponsePayload {
    @JsonProperty("data")
    private FetchClientDataResPayload data;

    @JsonProperty("success")
    private Boolean success;
}
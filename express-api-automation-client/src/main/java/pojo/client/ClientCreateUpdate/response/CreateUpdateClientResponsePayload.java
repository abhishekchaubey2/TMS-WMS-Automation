package pojo.client.ClientCreateUpdate.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CreateUpdateClientResponsePayload {
    @JsonProperty("data")
    private ClientCreateUpdateDataResPayload data;

    @JsonProperty("success")
    private boolean success;

    @JsonProperty("Error")
    private ClientCreateUpdateErrorResPayload error;

}

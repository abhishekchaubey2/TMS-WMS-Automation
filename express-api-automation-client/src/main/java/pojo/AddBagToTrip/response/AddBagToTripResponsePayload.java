package pojo.AddBagToTrip.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AddBagToTripResponsePayload {
    @JsonProperty("data")
    private AddBagToTripResponseDataPayload data;

    @JsonProperty("success")
    private Boolean success;

    @JsonProperty("error")
    private String error;
}

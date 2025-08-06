package pojo.AddBagToTrip.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AddBagToTripRequestPayload {
    @JsonProperty("ref_id")
    private String refId;

    @JsonProperty("trid")
    private String trid;

    @JsonProperty("slid")
    private String slid;

    @JsonProperty("vid")
    private String vid;

    @JsonProperty("ntcid")
    private String ntcid;

    @JsonProperty("mawb")
    private String mawb;

    @JsonProperty("action")
    private String action;

    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<>();
}

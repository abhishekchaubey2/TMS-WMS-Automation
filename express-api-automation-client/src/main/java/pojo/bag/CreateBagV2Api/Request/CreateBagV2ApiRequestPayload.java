package pojo.bag.CreateBagV2Api.Request;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Map;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CreateBagV2ApiRequestPayload {
    @JsonProperty("bs")
    private String bs;

    @JsonProperty("destination")
    private String destination;

    @JsonProperty("device_id")
    private String deviceId;

    @JsonProperty("ed")
    private String ed;

    @JsonProperty("origin")
    private String origin;

    @JsonProperty("sd")
    private String sd;

    @JsonProperty("u")
    private String u;

    @JsonProperty("bt")
    private String bt;

    @JsonProperty("primary")
    private CreateBagV2PrimaryReqPayload primary;

    @JsonProperty("wbns")
    private Map<String, CreateBagV2WayBillReqPayload> wbns;
}

package pojo.bag.BagV3Api.request;

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
public class BagV3RequestPayload {
    @JsonProperty("bs")
    private String bs;

    @JsonProperty("destination")
    private String destination;

    @JsonProperty("device_id")
    private String device_id;

    @JsonProperty("ed")
    private String ed;

    @JsonProperty("origin")
    private String origin;

    @JsonProperty("sd")
    private String sd;

    @JsonProperty("u")
    private String u;

    @JsonProperty("dws")
    private CreateBagV3DWSReqPayload dws;

    @JsonProperty("bt")
    private String bt;

    @JsonProperty("wbns")
    private Map<String, CreateBagV3WBNSReqPayload> wbns;
}

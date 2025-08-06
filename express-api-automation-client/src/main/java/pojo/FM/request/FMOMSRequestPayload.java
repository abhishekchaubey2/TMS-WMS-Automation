package pojo.FM.request;

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
public class FMOMSRequestPayload {
    @JsonProperty("wbns_dict")
    private Map<String, WBNSDictReqPayload> waybillsDict;

    @JsonProperty("client_id")
    private String clientId;

    @JsonProperty("warehouse_id")
    private String warehouseId;

    @JsonProperty("request_id")
    private String requestId;

    @JsonProperty("dispatch_id")
    private String dispatchId;

    @JsonProperty("cb")
    private CBReqPayload cb;

    @JsonProperty("location_id")
    private String location_id;

    @JsonProperty("event_code")
    private String event_code;

    @JsonProperty("sync")
    private Boolean sync;

    @JsonProperty("source")
    private String source;
}


package com.delhivery.Express.pojo.FMOMSApi.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class FMOMSRequestPayload {

    @JsonProperty("wbns_dict")
    public Wbns_dict wbns_dict;
    @JsonProperty("client_id")
    public String client_id;
    @JsonProperty("warehouse_id")
    public String warehouse_id;
    @JsonProperty("request_id")
    public String request_id;
    @JsonProperty("dispatch_id")
    public String dispatch_id;
    @JsonProperty("cb")
    public Cb cb;
    @JsonProperty("location_id")
    public String location_id;
    @JsonProperty("event_code")
    public String event_code;
    @JsonProperty("sync")
    public boolean sync;
    @JsonProperty("source")
    public String source;

}

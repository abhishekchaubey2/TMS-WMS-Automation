
package com.delhivery.Express.pojo.GetNoDataUplShipmentApi.response;

import java.util.List;
import lombok.*;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "upl",
    "package_count",
    "master_waybill",
    "waybills",
    "success",
    "failed",
    "error",
    "status",
    "dimensions"
})
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GetNoDataUplShipmentApiResponsePayload {

    @JsonProperty("upl")
    public String upl;
    @JsonProperty("package_count")
    public int package_count;
    @JsonProperty("master_waybill")
    public String master_waybill;
    @JsonProperty("waybills")
    public List<String> waybills = null;
    @JsonProperty("success")
    public List<Object> success = null;
    @JsonProperty("failed")
    public List<Object> failed = null;
    @JsonProperty("error")
    public String error;
    @JsonProperty("status")
    public String status;
    @JsonProperty("dimensions")
    public Dimensions dimensions;

}


package com.delhivery.Express.pojo.CmuV2ManifestNoDataShipmentApi.response;

import lombok.*;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "upl",
    "status"
})
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CmuV2ManifestNoDataShipmentApiResponsePayload {

    @JsonProperty("upl")
    public String upl;
    @JsonProperty("status")
    public String status;

}

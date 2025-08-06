
package com.delhivery.Express.pojo.LMUpdateHQShipmentApi.respone;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.*;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "Waybill"
})
@Builder
@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown=true)
public class Success {

//    @JsonProperty("Waybill")
//    public com.delhivery.Express.pojo.LMUpdateHQShipmentApi.respone.Waybill Waybill;

}

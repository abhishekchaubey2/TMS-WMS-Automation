
package com.delhivery.Express.pojo.LMUpdateHQShipmentApi.request;

import lombok.*;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "address_type",
    "cad",
    "call_attempted",
    "device",
    "device_id",
    "device_type",
    "fe_location_state",
    "fe_phone",
    "security_check"
})
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Add_info {

    @JsonProperty("address_type")
    public String address_type;
    @JsonProperty("cad")
    public Cad cad;
    @JsonProperty("call_attempted")
    public boolean call_attempted;
    @JsonProperty("device")
    public String device;
    @JsonProperty("device_id")
    public String device_id;
    @JsonProperty("device_type")
    public String device_type;
    @JsonProperty("fe_location_state")
    public int fe_location_state;
    @JsonProperty("fe_phone")
    public String fe_phone;
    @JsonProperty("security_check")
    public boolean security_check;

}

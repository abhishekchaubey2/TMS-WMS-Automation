
package com.delhivery.Express.pojo.EditPhone.Request;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "waybill",
    "act",
    "action_data"
})
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DatumEditPhone {

    @JsonProperty("waybill")
    public String waybill;
    @JsonProperty("act")
    public String act;
    @JsonProperty("action_data")
    public ActionDataEditPhone actionData;

}

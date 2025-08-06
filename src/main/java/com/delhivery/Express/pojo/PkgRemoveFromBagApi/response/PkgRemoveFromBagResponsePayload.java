
package com.delhivery.Express.pojo.PkgRemoveFromBagApi.response;

import lombok.*;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "status",
    "fail",
    "success"
})
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PkgRemoveFromBagResponsePayload {

    @JsonProperty("status")
    public boolean status;
    @JsonProperty("fail")
    public List<String> fail;
    @JsonProperty("success")
    public List<Datum> success;

}

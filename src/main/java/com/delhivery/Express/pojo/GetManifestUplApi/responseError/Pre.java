
package com.delhivery.Express.pojo.GetManifestUplApi.responseError;

import lombok.*;

import com.delhivery.Express.pojo.GetManifestUplApi.responseFailure.C;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "c_data",
    "status"
})
@Builder@Getter@Setter@NoArgsConstructor@AllArgsConstructor
public class Pre {

    @JsonProperty("c_data")
    public String c_data;
    @JsonProperty("status")
    public String status;
    @JsonProperty("c")
    public C c;

}

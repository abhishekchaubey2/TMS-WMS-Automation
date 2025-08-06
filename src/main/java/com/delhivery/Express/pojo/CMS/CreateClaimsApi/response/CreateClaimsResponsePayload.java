
package com.delhivery.Express.pojo.CMS.CreateClaimsApi.response;

import lombok.*;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "status_code",
    "data",
    "msg"
})
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateClaimsResponsePayload {

    @JsonProperty("status_code")
    public int status_code;
    @JsonProperty("data")
    public Data data;
    @JsonProperty("msg")
    public String msg;

}

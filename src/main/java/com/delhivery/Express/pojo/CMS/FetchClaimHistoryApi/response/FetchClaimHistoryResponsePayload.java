
package com.delhivery.Express.pojo.CMS.FetchClaimHistoryApi.response;

import java.util.List;
import lombok.*;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

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
public class FetchClaimHistoryResponsePayload {

    @JsonProperty("status_code")
    public int status_code;
    @JsonProperty("data")
    public List<Object> data;
    @JsonProperty("msg")
    public String msg;

}

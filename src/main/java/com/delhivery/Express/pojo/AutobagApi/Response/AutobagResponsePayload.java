package com.delhivery.Express.pojo.AutobagApi.Response;

import java.util.List;
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
    "status",
    "data",
    "error"
})
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AutobagResponsePayload {

    @JsonProperty("status")
    public long status;
    @JsonProperty("data")
    public List<Datum> data;
    @JsonProperty("error")
    public List<Object> error;

}

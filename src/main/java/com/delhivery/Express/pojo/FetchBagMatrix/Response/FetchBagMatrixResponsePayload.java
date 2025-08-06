
package com.delhivery.Express.pojo.FetchBagMatrix.Response;

import java.util.List;

import com.delhivery.Express.pojo.BagV3Api.response.Datum;
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
    "msg",
    "data",
    "success"
})

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FetchBagMatrixResponsePayload {

    @JsonProperty("msg")
    public String msg;
    @JsonProperty("data")
    public List<Datum> data;
    @JsonProperty("success")
    public boolean success;

}

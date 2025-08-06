package com.delhivery.Express.pojo.BagCalc.Response;

import java.util.List;
import java.util.Map;

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
    "error_bag",
    "data",
    "success",
    "error"
})
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BagCalcResponsePayload {

    @JsonProperty("error_bag")
    public ErrorBag errorBag;
    @JsonProperty("data")
    public List<Datum> data;
    @JsonProperty("success")
    public boolean success;
    @JsonProperty("error")
    public String error;

}

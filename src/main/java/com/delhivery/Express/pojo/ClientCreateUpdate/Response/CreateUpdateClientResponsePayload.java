package com.delhivery.Express.pojo.ClientCreateUpdate.Response;

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
    "data",
    "success"
})
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateUpdateClientResponsePayload {

    @JsonProperty("data")
    public Data data;
    @JsonProperty("success")
    public boolean success;

}

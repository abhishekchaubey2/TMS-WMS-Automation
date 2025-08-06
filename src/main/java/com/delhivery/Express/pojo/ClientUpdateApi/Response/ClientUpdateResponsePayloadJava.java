package com.delhivery.Express.pojo.ClientUpdateApi.Response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "updated_data",
    "name",
    "success",
    "error"
})

public class ClientUpdateResponsePayloadJava {

    @JsonProperty("updated_data")
    public UpdatedData updatedData;
    @JsonProperty("name")
    public String name;
    @JsonProperty("success")
    public boolean success;
    @JsonProperty("error")
    public String error;

}

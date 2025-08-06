package com.delhivery.Express.pojo.ClientUpdateNew.Response;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

public class ClientUpdateResponse {
    @JsonProperty("updated_data")
    public UpdatedData updatedData;
    @JsonProperty("name")
    public String name;
    @JsonProperty("success")
    public Boolean success;
}

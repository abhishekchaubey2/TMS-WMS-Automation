package com.delhivery.Express.pojo.ClientUpdateNew.Response;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

public class Mot {
    @JsonProperty("surface")
    public Surface surface;
}

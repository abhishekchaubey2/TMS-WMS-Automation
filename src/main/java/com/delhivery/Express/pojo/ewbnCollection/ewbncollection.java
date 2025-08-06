package com.delhivery.Express.pojo.ewbnCollection;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

public class ewbncollection {
    @JsonProperty("data")
    public dataewbn data;
    @JsonProperty("success")
    public Object success;
    @JsonProperty("error")
    public Object error;
}

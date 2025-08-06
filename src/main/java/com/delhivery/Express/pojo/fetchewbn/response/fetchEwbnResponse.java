package com.delhivery.Express.pojo.fetchewbn.response;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.List;
//POJO of fetch ewbn API response
public class fetchEwbnResponse {

    @JsonProperty("success")
    public Boolean success;
    @JsonProperty("data")
    public List<Datum> data;
    @JsonProperty("message")
    public String message;
}

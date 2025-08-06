package com.delhivery.Express.pojo.pUpdate;

import java.util.LinkedHashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "waybill",
        "act",
        "source",
        "action_data"
})
@Builder
@Getter
@Setter
public class Datum {

    @JsonProperty("waybill")
    public String waybill;
    @JsonProperty("act")
    public String act;
    @JsonProperty("source")
    public String source;
    @JsonProperty("action_data")
    public com.delhivery.Express.pojo.pUpdate.ActionDataPUpdate actionDataPUpdate;
    @JsonIgnore
    public Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

}

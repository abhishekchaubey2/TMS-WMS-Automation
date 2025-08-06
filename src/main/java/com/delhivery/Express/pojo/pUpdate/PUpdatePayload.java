package com.delhivery.Express.pojo.pUpdate;

import java.util.LinkedHashMap;
import java.util.List;
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
        "data"
})
@Builder
@Getter
@Setter
public class PUpdatePayload {

    @JsonProperty("data")
    public List<com.delhivery.Express.pojo.pUpdate.Datum> data;
    @JsonIgnore
    public Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

}

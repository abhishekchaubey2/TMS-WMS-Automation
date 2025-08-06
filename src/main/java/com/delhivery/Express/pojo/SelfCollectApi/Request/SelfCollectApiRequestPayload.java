package com.delhivery.Express.pojo.SelfCollectApi.Request;

import java.util.List;
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
    "source",
    "dwbn",
    "process_insync",
    "required_fields",
    "data",
    "md"
})
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SelfCollectApiRequestPayload {

    @JsonProperty("source")
    public String source;
    @JsonProperty("dwbn")
    public String dwbn;
    @JsonProperty("process_insync")
    public boolean processInsync;
    @JsonProperty("required_fields")
    public List<String> requiredFields;
    @JsonProperty("data")
    public SelfCollectData data;
    @JsonProperty("md")
    public String md;

}

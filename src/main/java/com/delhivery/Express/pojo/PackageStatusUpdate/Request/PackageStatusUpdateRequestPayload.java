
package com.delhivery.Express.pojo.PackageStatusUpdate.Request;

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
    "process_insync",
    "required_fields",
    "data",
    "source"
})
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PackageStatusUpdateRequestPayload {

    @JsonProperty("process_insync")
    public boolean processInsync;
    @JsonProperty("required_fields")
    public List<String> requiredFields;
    @JsonProperty("data")
    public Data data;
    @JsonProperty("source")
    public String source;

}

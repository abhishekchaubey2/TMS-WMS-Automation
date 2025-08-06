
package com.delhivery.Express.pojo.trackingApi.response;

import java.util.LinkedHashMap;
import java.util.Map;

import com.delhivery.Express.pojo.hqTrackingError.response.HQTrackError;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.*;
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "Instructions",
    "Scan",
    "ScanDateTime",
    "ScanType",
    "ScannedLocation",
    "StatusCode",
    "StatusDateTime"
})
public class ScanDetail {

    @JsonProperty("Instructions")
    public String instructions;
    @JsonProperty("Scan")
    public String scan;
    @JsonProperty("ScanDateTime")
    public String scanDateTime;
    @JsonProperty("ScanType")
    public String scanType;
    @JsonProperty("ScannedLocation")
    public String scannedLocation;
    @JsonProperty("StatusCode")
    public String statusCode;
    @JsonProperty("StatusDateTime")
    public String statusDateTime;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}

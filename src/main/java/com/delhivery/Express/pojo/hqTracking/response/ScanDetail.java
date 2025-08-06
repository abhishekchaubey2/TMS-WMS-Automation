
package com.delhivery.Express.pojo.hqTracking.response;


import com.delhivery.Express.pojo.hqTrackingError.response.HQTrackError;
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
    "ScanDateTime",
    "ScanType",
    "Scan",
    "StatusDateTime",
    "ScannedLocation",
    "Instructions",
    "StatusCode",
    "geo_location"
})
public class ScanDetail {

    @JsonProperty("ScanDateTime")
    public String scanDateTime;
    @JsonProperty("ScanType")
    public String scanType;
    @JsonProperty("Scan")
    public String scan;
    @JsonProperty("StatusDateTime")
    public String statusDateTime;
    @JsonProperty("ScannedLocation")
    public String scannedLocation;
    @JsonProperty("Instructions")
    public String instructions;
    @JsonProperty("StatusCode")
    public String statusCode;
    @JsonProperty("geo_location")
    public GeoLocation geoLocation;

}

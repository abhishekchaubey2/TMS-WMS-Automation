package com.delhivery.Express.pojo.PackageStatusApi.Response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "ScanDateTime",
    "AdditionalScanRemark",
    "ScanType",
    "CityLocation",
    "Scan",
    "Destination",
    "ScannedLocation",
    "StatusDateTime",
    "Instructions",
    "StatusCode"
})

public class ScanDetail {

    @JsonProperty("ScanDateTime")
    public String scanDateTime;
    @JsonProperty("AdditionalScanRemark")
    public Object additionalScanRemark;
    @JsonProperty("ScanType")
    public Object scanType;
    @JsonProperty("CityLocation")
    public String cityLocation;
    @JsonProperty("Scan")
    public String scan;
    @JsonProperty("Destination")
    public Object destination;
    @JsonProperty("ScannedLocation")
    public String scannedLocation;
    @JsonProperty("StatusDateTime")
    public String statusDateTime;
    @JsonProperty("Instructions")
    public Object instructions;
    @JsonProperty("StatusCode")
    public Object statusCode;

}

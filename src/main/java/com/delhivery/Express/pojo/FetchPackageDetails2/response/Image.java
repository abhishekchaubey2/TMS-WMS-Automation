package com.delhivery.Express.pojo.FetchPackageDetails2.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;

@Getter
@JsonPropertyOrder({
    "path",
    "bucket"
})
@JsonIgnoreProperties(ignoreUnknown=true)
public class Image {

    @JsonProperty("path")
    public Object path;
    @JsonProperty("bucket")
    public Object bucket;

}

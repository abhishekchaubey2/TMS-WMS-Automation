package com.delhivery.Express.pojo.FetchPackageDetailsSecond.response;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class PackageDetails {
@JsonProperty("add_info")
public List<PackageDetail2> packageDetails;
}

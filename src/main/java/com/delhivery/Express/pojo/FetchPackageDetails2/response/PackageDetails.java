package com.delhivery.Express.pojo.FetchPackageDetails2.response;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonInclude;


public class PackageDetails {
@JsonProperty("add_info")
public List<PackageDetailNew> packageDetails;
}

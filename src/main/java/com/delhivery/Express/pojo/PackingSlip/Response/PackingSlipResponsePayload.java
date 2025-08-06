package com.delhivery.Express.pojo.PackingSlip.Response;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;

@Getter
public class PackingSlipResponsePayload {
    @JsonProperty("packages")
    private List<Package> packages;

    @JsonProperty("packages_found")
    private long packagesFound;
}

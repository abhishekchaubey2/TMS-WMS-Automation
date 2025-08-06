
package com.delhivery.Express.pojo.ManifestCmuCreateApiNew.response;

import java.util.List;

import com.delhivery.Express.pojo.ManifestCmuCreateApi.request.CmuManifestApi;
import com.delhivery.Express.pojo.ManifestCmuCreateApi.request.Shipment;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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

@JsonIgnoreProperties(ignoreUnknown = true)
public class CmuResponseApiNew {

    @JsonProperty("cash_pickups_count")
    public float cashPickupsCount;
    @JsonProperty("package_count")
    public long packageCount;
    @JsonProperty("upload_wbn")
    public String uploadWbn;
    @JsonProperty("replacement_count")
    public long replacementCount;
    @JsonProperty("pickups_count")
    public long pickupsCount;
    @JsonProperty("packages")
    public List<Package> packages = null;
    @JsonProperty("cash_pickups")
    public float cashPickups;
    @JsonProperty("cod_count")
    public long codCount;
    @JsonProperty("success")
    public boolean success;
    @JsonProperty("prepaid_count")
    public long prepaidCount;
    @JsonProperty("cod_amount")
    public float codAmount;

}

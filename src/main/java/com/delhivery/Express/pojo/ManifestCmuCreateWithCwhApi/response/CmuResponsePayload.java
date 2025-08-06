
package com.delhivery.Express.pojo.ManifestCmuCreateWithCwhApi.response;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

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
public class CmuResponsePayload {

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

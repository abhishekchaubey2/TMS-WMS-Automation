
package com.delhivery.Express.pojo.MeeshoManifestApi.response;

import java.util.List;
import lombok.*;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "cash_pickups_count",
    "package_count",
    "upload_wbn",
    "replacement_count",
    "pickups_count",
    "packages",
    "cash_pickups",
    "cod_count",
    "success",
    "prepaid_count",
    "cod_amount"
})
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MeeshoManifestResponsePayload {

    @JsonProperty("cash_pickups_count")
    public float cash_pickups_count;
    @JsonProperty("package_count")
    public int package_count;
    @JsonProperty("upload_wbn")
    public String upload_wbn;
    @JsonProperty("replacement_count")
    public int replacement_count;
    @JsonProperty("pickups_count")
    public int pickups_count;
    @JsonProperty("packages")
    public List<Package> packages;
    @JsonProperty("cash_pickups")
    public int cash_pickups;
    @JsonProperty("cod_count")
    public int cod_count;
    @JsonProperty("success")
    public boolean success;
    @JsonProperty("prepaid_count")
    public int prepaid_count;
    @JsonProperty("cod_amount")
    public float cod_amount;

}

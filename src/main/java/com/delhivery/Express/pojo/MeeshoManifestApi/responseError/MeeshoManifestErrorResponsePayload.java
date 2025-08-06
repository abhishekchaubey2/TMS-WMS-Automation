
package com.delhivery.Express.pojo.MeeshoManifestApi.responseError;

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
    "rmk",
    "pickups_count",
    "packages",
    "cash_pickups",
    "cod_count",
    "success",
    "prepaid_count",
    "error",
    "cod_amount"
})
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MeeshoManifestErrorResponsePayload {

    @JsonProperty("cash_pickups_count")
    public int cash_pickups_count;
    @JsonProperty("package_count")
    public int package_count;
    @JsonProperty("upload_wbn")
    public String upload_wbn;
    @JsonProperty("replacement_count")
    public int replacement_count;
    @JsonProperty("rmk")
    public String rmk;
    @JsonProperty("pickups_count")
    public int pickups_count;
    @JsonProperty("packages")
    public List<Object> packages;
    @JsonProperty("cash_pickups")
    public int cash_pickups;
    @JsonProperty("cod_count")
    public int cod_count;
    @JsonProperty("success")
    public boolean success;
    @JsonProperty("prepaid_count")
    public int prepaid_count;
    @JsonProperty("error")
    public boolean error;
    @JsonProperty("cod_amount")
    public int cod_amount;

}

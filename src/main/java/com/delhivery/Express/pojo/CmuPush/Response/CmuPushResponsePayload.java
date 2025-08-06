package com.delhivery.Express.pojo.CmuPush.Response;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@JsonInclude(JsonInclude.Include.NON_NULL)
@NoArgsConstructor
@AllArgsConstructor
@JsonPropertyOrder({
    "cash_pickups_count",
    "cod_count",
    "success",
    "package_count",
    "upload_wbn",
    "replacement_count",
    "cod_amount",
    "prepaid_count",
    "pickups_count",
    "packages",
    "cash_pickups"
})
@Builder
@Getter
@Setter
public class CmuPushResponsePayload {

    @JsonProperty("cash_pickups_count")
    public float cashPickupsCount;
    @JsonProperty("cod_count")
    public long codCount;
    @JsonProperty("success")
    public boolean success;
    @JsonProperty("package_count")
    public long packageCount;
    @JsonProperty("upload_wbn")
    public String uploadWbn;
    @JsonProperty("replacement_count")
    public long replacementCount;
    @JsonProperty("cod_amount")
    public float codAmount;
    @JsonProperty("prepaid_count")
    public long prepaidCount;
    @JsonProperty("pickups_count")
    public long pickupsCount;
    @JsonProperty("packages")
    public List<Package> packages;
    @JsonProperty("cash_pickups")
    public float cashPickups;

}

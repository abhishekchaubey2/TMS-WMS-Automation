package pojo.manifest.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import lombok.ToString;

import java.util.List;

@Getter
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class CMUResponsePayload {
    @JsonProperty("cash_pickups_count")
    private float cashPickupsCount;

    @JsonProperty("package_count")
    private long packageCount;

    @JsonProperty("upload_wbn")
    private String uploadWbn;

    @JsonProperty("replacement_count")
    private long replacementCount;

    @JsonProperty("pickups_count")
    private long pickupsCount;

    @JsonProperty("packages")
    private List<PackageResponsePayload> packages;

    @JsonProperty("cash_pickups")
    private float cashPickups;

    @JsonProperty("cod_count")
    private long codCount;

    @JsonProperty("success")
    private boolean success;

    @JsonProperty("prepaid_count")
    private long prepaidCount;

    @JsonProperty("cod_amount")
    private float codAmount;

    @JsonProperty("rmk")
    private String rmk;

    @JsonProperty("error")
    private String error;
}

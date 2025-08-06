package pojo.FMNewPickup.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import lombok.ToString;

@Getter
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class CreateNewFMPickupResPayload {
    @JsonProperty("pickup_location_name")
    private String pickupLocation;

    @JsonProperty("client_name")
    private String clientName;

    @JsonProperty("pickup_time")
    private String pickupTime;

    @JsonProperty("pickup_id")
    private String pickupId;

    @JsonProperty("incoming_center_name")
    private String incomingCenterName;


    @JsonProperty("expected_package_count")
    private Integer expectedPackageCount;

    @JsonProperty("pickup_date")
    private String pickupDate;

    @JsonProperty("status")
    private boolean status;

    @JsonProperty("data")
    private CreateNewFMPickupDataResPayload data;

    @JsonProperty("success")
    private boolean success;

    @JsonProperty("error")
    private CreateNewFMPickupErrorResPayload errorResponsePayload;

    @JsonProperty("pr_exist")
    private Boolean prExist;
}

package pojo.FMNewPickup.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CreateNewFmRequestPayload {
    @JsonProperty("pickup_time")
    private String pickupTime;

    @JsonProperty("pickup_date")
    private String pickupDate;

    @JsonProperty("pickup_location")
    private String pickupLocation;

    @JsonProperty("expected_package_count")
    private Integer expectedPackageCount;
}

package pojo.manifest.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CMUManifestRequestPayload {
    @JsonProperty("pickup_location")
    private PickupLocationRequestPayload pickupLocation;

    @JsonProperty("shipments")
    private List<ShipmentRequestPayload> shipments;

    @JsonProperty("dispatch_date")
    private String dispatchDate;
}

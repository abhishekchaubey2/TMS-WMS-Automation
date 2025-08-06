package com.delhivery.Express.pojo.UpdatedBagmatrix.Request;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "last_updated",
    "scan_location"
})
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UpdatedBagmatrixRequestPayload {

    @JsonProperty("last_updated")
    public long lastUpdated;
    @JsonProperty("scan_location")
    public String scanLocation;

}

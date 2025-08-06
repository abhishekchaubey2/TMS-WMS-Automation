package com.delhivery.Express.pojo.LocationAssociate.Request;

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
    "user",
    "sd",
    "ref_id",
    "slid",
    "location_id"
})
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LocationAssociateRequestPayload {

    @JsonProperty("user")
    public String user;
    @JsonProperty("sd")
    public String sd;
    @JsonProperty("ref_id")
    public String refId;
    @JsonProperty("slid")
    public String slid;
    @JsonProperty("location_id")
    public String locationId;

}

package com.delhivery.Express.pojo.ManifestCmuCreateApi.request;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CmuManifestApi {
	@JsonProperty("pickup_location")
	public pickup_location pickup_location;
    @JsonProperty("shipments")
    public List<Shipment> shipments = null;
    @JsonProperty("dispatch_date")
    public String dispatchDate;
}

package com.delhivery.Express.pojo.NewManifestService3.request;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class Manifest3 {
	@JsonProperty("shipments")
	public List<Shipment3> shipments;

}

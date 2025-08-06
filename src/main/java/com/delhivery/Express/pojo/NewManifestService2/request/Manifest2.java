package com.delhivery.Express.pojo.NewManifestService2.request;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class Manifest2 {
	@JsonProperty("shipments")
	public List<Shipment2> shipments;

}

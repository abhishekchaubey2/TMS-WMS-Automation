package com.delhivery.Express.pojo.ManifestCmuCreateApi.request;
import lombok.*;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class pickup_location {
	@JsonProperty("name")
	public String name;
}

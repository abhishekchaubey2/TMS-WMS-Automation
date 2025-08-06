
package com.delhivery.Express.pojo.CenterUpdateRT.Request;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.Builder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "rcn",
    "wbn"
})
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateDatumRT {

	@JsonProperty("rcn")
	public String rcn;
	@JsonProperty("wbn")
	public String wbn;

}

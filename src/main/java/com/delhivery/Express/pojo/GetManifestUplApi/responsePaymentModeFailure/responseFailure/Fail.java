
package com.delhivery.Express.pojo.GetManifestUplApi.responsePaymentModeFailure.responseFailure;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.*;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "wbn",
    "rmk",
    "oid"
})
@Builder@Getter@Setter@NoArgsConstructor@AllArgsConstructor
public class Fail {

	@JsonProperty("wbn")
	public String wbn;
	@JsonProperty("rmk")
	public Object rmk;
	@JsonProperty("oid")
	public String oid;
	@JsonProperty("err_code")
	public String errCode;

}

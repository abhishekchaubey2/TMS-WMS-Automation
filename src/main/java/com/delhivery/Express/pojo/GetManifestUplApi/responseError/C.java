
package com.delhivery.Express.pojo.GetManifestUplApi.responseError;

import lombok.*;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "paid",
    "cod",
    "tot",
    "pick"
})
@Builder@Getter@Setter@NoArgsConstructor@AllArgsConstructor
public class C {

	@JsonProperty("cod_cnt")
	public Integer codCnt;
	@JsonProperty("paid")
	public Integer paid;
	@JsonProperty("cash")
	public Integer cash;
	@JsonProperty("tot")
	public Integer tot;
	@JsonProperty("cod")
	public Integer cod;
	@JsonProperty("w")
	public List<Object> w;
	@JsonProperty("pick")
	public Integer pick;
	@JsonProperty("replacement")
	public Integer replacement;

}

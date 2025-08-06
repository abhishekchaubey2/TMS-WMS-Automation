
package com.delhivery.Express.pojo.CenterUpdate.Request;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@JsonPropertyOrder({
    "update_data",
    "interface",
    "action_type"
})
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CenterUpdateRequest {

	@JsonProperty("update_data")
	public List<UpdateDatum> updateData;
	@JsonProperty("interface")
	public String _interface;
	@JsonProperty("action_type")
	public String actionType;

}

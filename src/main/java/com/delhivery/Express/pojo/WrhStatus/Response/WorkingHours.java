
package com.delhivery.Express.pojo.WrhStatus.Response;

import java.util.LinkedHashMap;
import java.util.Map;

import com.delhivery.Express.pojo.AgWt.Request.MaxWt;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@JsonPropertyOrder({

})
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class WorkingHours {

	@JsonIgnore
	private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

}

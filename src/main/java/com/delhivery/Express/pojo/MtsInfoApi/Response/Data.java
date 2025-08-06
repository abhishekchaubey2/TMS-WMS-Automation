package com.delhivery.Express.pojo.MtsInfoApi.Response;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@JsonPropertyOrder({ "reverse_data", "forward_data" })
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Data implements Serializable {

	@JsonProperty("reverse_data")
	public ReverseData reverseData;
	@JsonProperty("forward_data")
	public ForwardData forwardData;
	// public final static long serialVersionUID = -2271492738355625920L;

}
package com.delhivery.Express.pojo.MtsInfoApi.Response;

import java.io.Serializable;

import com.delhivery.Express.pojo.AgWt.Request.MaxWt;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "data", "success", "error" })
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class MtsResponse implements Serializable {

	@JsonProperty("data")
	public Data data;
	@JsonProperty("success")
	public Boolean success;
	@JsonProperty("error")
	public Object error;
	public final static long serialVersionUID = 1975913947935467133L;

}
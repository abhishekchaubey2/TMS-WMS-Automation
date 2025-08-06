
package com.delhivery.Express.pojo.Psearch.Response;

import java.util.List;

import com.delhivery.Express.pojo.SelfCollectApi.Response.Data;
import com.delhivery.Express.pojo.SelfCollectApi.Response.Meta;
import com.delhivery.Express.pojo.SelfCollectApi.Response.SelfCollectApiResponsePayload;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ApiResponse {

	@JsonProperty("status")
	public Integer status;
	@JsonProperty("count")
	public Integer count;
	@JsonProperty("data")
	public List<Object> data;
	@JsonProperty("result")
	public List<Result> result;
	@JsonProperty("error")
	public List<Object> error;

}

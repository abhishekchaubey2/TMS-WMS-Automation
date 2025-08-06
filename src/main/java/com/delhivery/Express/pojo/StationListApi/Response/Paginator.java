package com.delhivery.Express.pojo.StationListApi.Response;

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

@JsonPropertyOrder({ "per_page", "page", "total_count" })
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Paginator {

	@JsonProperty("per_page")
	public Integer perPage;
	@JsonProperty("page")
	public Integer page;
	@JsonProperty("total_count")
	public Integer totalCount;
	@JsonIgnore
	public Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

}

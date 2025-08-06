package com.delhivery.Express.pojo.StationListApi.Response;

import java.util.LinkedHashMap;
import java.util.List;
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

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "paginator", "records" })
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class Data {

	@JsonProperty("paginator")
	public Paginator paginator;
	@JsonProperty("records")
	public List<Object> records;
	@JsonIgnore
	public Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

}

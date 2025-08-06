package com.delhivery.Express.pojo.QC.Response;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
"description",
"image",
"quantity",
"question",
"type",
"brand",
"options",
"err_code",
"valid_options",
"question_id",
"required",
"max_count",
"min_count"
})


public class Item1 {
	
	@JsonProperty("description")
	public String description;
	@JsonProperty("image")
	public List<String> image;
	@JsonProperty("quantity")
	public Integer quantity;
	@JsonProperty("question")
	public String question;
	@JsonProperty("type")
	public String type;
	@JsonProperty("brand")
	public String brand;
	@JsonProperty("options")
	public List<String> options;
	@JsonProperty("err_code")
	public String errCode;
	@JsonProperty("valid_options")
	public List<String> validOptions;
	@JsonProperty("question_id")
	public String questionId;
	@JsonProperty("required")
	public Boolean required;
	@JsonProperty("max_count")
	public Integer maxCount;
	@JsonProperty("min_count")
	public Integer minCount;

}

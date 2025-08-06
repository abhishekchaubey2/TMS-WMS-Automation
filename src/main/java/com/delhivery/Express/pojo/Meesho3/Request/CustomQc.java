package com.delhivery.Express.pojo.Meesho3.Request;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Builder
@Getter
@Setter

public class CustomQc {
    @JsonProperty("item")
    public Object item;
    @JsonProperty("description")
    public Object description;
    @JsonProperty("images")
    public List<Object> images;
    @JsonProperty("return_reason")
    public Object returnReason;
    @JsonProperty("quantity")
    public Object quantity;
    @JsonProperty("brand")
    public Object brand;
    @JsonProperty("product_category")
    public Object productCategory;
    @JsonProperty("questions")
    public List<Question> questions;
}

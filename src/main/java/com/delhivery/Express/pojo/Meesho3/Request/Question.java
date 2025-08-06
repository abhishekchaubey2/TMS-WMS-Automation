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
public class Question {
    @JsonProperty("questions_id")
    public Object questionsId;
    @JsonProperty("options")
    public List<Object> options;
    @JsonProperty("value")
    public List<Object> value;
    @JsonProperty("required")
    public Object required;
    @JsonProperty("type")
    public Object type;
}

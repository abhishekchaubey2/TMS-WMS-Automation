package com.delhivery.Express.pojo.SelfCollectApi.Response;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "status",
    "failure",
    "remark",
    "id",
    "success"
})
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Meta {

    @JsonProperty("status")
    public boolean status;
    @JsonProperty("failure")
    public List<Object> failure;
    @JsonProperty("remark")
    public String remark;
    @JsonProperty("id")
    public String id;
    @JsonProperty("success")
    public List<String> success;

}

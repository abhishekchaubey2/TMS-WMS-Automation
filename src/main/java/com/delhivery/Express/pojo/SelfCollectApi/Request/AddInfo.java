package com.delhivery.Express.pojo.SelfCollectApi.Request;

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
    "additional_remark"
})
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AddInfo {

    @JsonProperty("additional_remark")
    public String additionalRemark;

}

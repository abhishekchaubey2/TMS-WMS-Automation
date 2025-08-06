package com.delhivery.Express.pojo.CustomEditApi.Request;

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
    "land_mark"
})
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CustomEditApiRequestPayload {

    @JsonProperty("land_mark")
    public String landMark;

}

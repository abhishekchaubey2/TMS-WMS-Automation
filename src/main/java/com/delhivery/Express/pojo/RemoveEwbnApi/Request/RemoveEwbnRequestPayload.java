package com.delhivery.Express.pojo.RemoveEwbnApi.Request;

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
    "ewbn",
    "user"
})
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RemoveEwbnRequestPayload {

    @JsonProperty("ewbn")
    public String ewbn;
    @JsonProperty("user")
    public String user;

}

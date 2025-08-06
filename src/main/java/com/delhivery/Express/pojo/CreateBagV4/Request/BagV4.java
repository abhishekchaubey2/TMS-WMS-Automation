package com.delhivery.Express.pojo.CreateBagV4.Request;

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
    "u",
    "sd"
})
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BagV4 {

    @JsonProperty("u")
    public String u;
    @JsonProperty("sd")
    public String sd;

}

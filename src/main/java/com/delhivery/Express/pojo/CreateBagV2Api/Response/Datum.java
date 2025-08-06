package com.delhivery.Express.pojo.CreateBagV2Api.Response;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "wbn",
    "t",
    "bs"
})

public class Datum {

    @JsonProperty("wbn")
    public List<String> wbn;
    @JsonProperty("t")
    public String t;
    @JsonProperty("bs")
    public String bs;

}

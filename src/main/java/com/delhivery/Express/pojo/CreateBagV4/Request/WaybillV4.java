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
    "dws",
    "bag",
    "primary",
    "secondary"
})
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class WaybillV4 {

    @JsonProperty("dws")
    public Dws dws;
    @JsonProperty("bag")
    public BagV4 bag;
    @JsonProperty("primary")
    public PrimaryV4 primary;
    @JsonProperty("secondary")
    public Secondary secondary;

}

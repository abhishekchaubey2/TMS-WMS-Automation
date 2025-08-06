
package com.delhivery.Express.pojo.UpdateFinalWt.Request;

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
    "wbn",
    "wt",
    "l",
    "h",
    "b",
    "int_wt_iwt",
    "div",
    "wt_rule",
    "wt_type"
})
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateFinalWtRequestPayload {

    @JsonProperty("wbn")
    public String wbn;
    @JsonProperty("wt")
    public long wt;
    @JsonProperty("l")
    public long l;
    @JsonProperty("h")
    public long h;
    @JsonProperty("b")
    public long b;
    @JsonProperty("int_wt_iwt")
    public String intWtIwt;
    @JsonProperty("div")
    public long div;
    @JsonProperty("wt_rule")
    public String wtRule;
    @JsonProperty("wt_type")
    public String wtType;

}

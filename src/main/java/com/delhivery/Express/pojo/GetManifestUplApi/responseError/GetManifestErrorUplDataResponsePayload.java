
package com.delhivery.Express.pojo.GetManifestUplApi.responseError;

import java.util.List;
import lombok.*;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "pre",
    "upl",
    "suc",
    "cl",
    "c",
    "cd",
    "fail",
    "s",
    "cod",
    "rmk",
    "wbn",
    "ud",
    "api_version",
    "dup"
})
@Builder@Getter@Setter@NoArgsConstructor@AllArgsConstructor
public class GetManifestErrorUplDataResponsePayload {

    @JsonProperty("pre")
    public Pre pre;
    @JsonProperty("upl")
    public Upl upl;
    @JsonProperty("suc")
    public List<Object> suc;
    @JsonProperty("cl")
    public Object cl;
    @JsonProperty("c")
    public C c;
    @JsonProperty("cd")
    public String cd;
    @JsonProperty("fail")
    public List<Object> fail;
    @JsonProperty("s")
    public String s;
    @JsonProperty("cod")
    public Object cod;
    @JsonProperty("rmk")
    public String rmk;
    @JsonProperty("wbn")
    public String wbn;
    @JsonProperty("ud")
    public String ud;
    @JsonProperty("api_version")
    public double api_version;
    @JsonProperty("dup")
    public List<Object> dup;
    @JsonProperty("c_data")
    public String c_data;
    @JsonProperty("sync")
    public Boolean sync;
    @JsonProperty("cash")
    public Float cash;
    @JsonProperty("w")
    public List<Object> w;

}

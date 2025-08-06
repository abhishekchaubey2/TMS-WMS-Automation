
package com.delhivery.Express.pojo.GetManifestUplApi.response;

import java.util.List;
import lombok.*;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "pre",
    "c_data",
    "upl",
    "suc",
    "cl",
    "c",
    "cash",
    "cd",
    "sync",
    "fail",
    "s",
    "cod",
    "w",
    "rmk",
    "wbn",
    "ud",
    "api_version",
    "dup"
})
@Builder@Getter@Setter@NoArgsConstructor@AllArgsConstructor
public class GetManifestUplDataResponsePayload {

    @JsonProperty("pre")
    public Pre pre;
    @JsonProperty("c_data")
    public String c_data;
    @JsonProperty("upl")
    public Upl upl;
    @JsonProperty("suc")
    public List<Suc> suc;
    @JsonProperty("cl")
    public Object cl;
    @JsonProperty("c")
    public C__1 c;
    @JsonProperty("cash")
    public double cash;
    @JsonProperty("cd")
    public String cd;
    @JsonProperty("sync")
    public boolean sync;
    @JsonProperty("fail")
    public List<Object> fail;
    @JsonProperty("s")
    public String s;
    @JsonProperty("cod")
    public double cod;
    @JsonProperty("w")
    public List<Object> w;
    @JsonProperty("rmk")
    public Object rmk;
    @JsonProperty("wbn")
    public String wbn;
    @JsonProperty("ud")
    public String ud;
    @JsonProperty("api_version")
    public double api_version;
    @JsonProperty("dup")
    public List<Object> dup;

}

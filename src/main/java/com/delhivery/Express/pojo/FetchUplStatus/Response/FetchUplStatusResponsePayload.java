
package com.delhivery.Express.pojo.FetchUplStatus.Response;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FetchUplStatusResponsePayload {

    @JsonProperty("upl")
    private Upl upl;
    @JsonProperty("cburl")
    private Cburl cburl;
    @JsonProperty("success")
    private List<Object> success;
    @JsonProperty("cl")
    private Object cl;
    @JsonProperty("cd")
    private String cd;
    @JsonProperty("wbns_dict")
    private WbnsDict wbnsDict;
    @JsonProperty("action")
    private String action;
    @JsonProperty("s")
    private String s;
    @JsonProperty("rmk")
    private Object rmk;
    @JsonProperty("wbn")
    private String wbn;
    @JsonProperty("ud")
    private String ud;
    @JsonProperty("fail")
    private Fail fail;

}

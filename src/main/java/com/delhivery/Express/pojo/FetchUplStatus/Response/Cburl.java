
package com.delhivery.Express.pojo.FetchUplStatus.Response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Cburl {

    @JsonProperty("url")
    private String url;
    @JsonProperty("method")
    private String method;

}

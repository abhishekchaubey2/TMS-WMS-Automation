
package com.delhivery.Express.pojo.FetchUplStatus.Response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Upl {

    @JsonProperty("url")
    private Object url;
    @JsonProperty("u")
    private String u;
    @JsonProperty("act")
    private Object act;

}

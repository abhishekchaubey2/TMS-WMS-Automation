
package com.delhivery.Express.pojo.FetchUplStatus.Response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties
public class WbnsDict {

    @JsonProperty("waybill")
    private com.delhivery.Express.pojo.FetchUplStatus.Response.Waybill waybill;
}

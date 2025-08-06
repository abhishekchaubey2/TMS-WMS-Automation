package com.delhivery.Express.pojo.QC.Response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
"wbn"
})
public class QcData {

@JsonProperty("wbn")
public Wbn wbn;

}
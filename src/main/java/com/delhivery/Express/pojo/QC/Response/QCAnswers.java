package com.delhivery.Express.pojo.QC.Response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
"qc_data",
"success"
})


public class QCAnswers {
	
	@JsonProperty("qc_data")
	public QcData qcData;
	@JsonProperty("success")
	public Boolean success;

}

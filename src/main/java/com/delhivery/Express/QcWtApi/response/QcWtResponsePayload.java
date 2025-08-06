package com.delhivery.Express.QcWtApi.response;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class QcWtResponsePayload {
	@JsonProperty("msg")
	public String msg;
	@JsonProperty("success")
	public boolean success;
}

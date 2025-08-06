package com.delhivery.Express.pojo.MtsInfoApi.Response;

import java.io.Serializable;

import com.delhivery.Express.pojo.AgWt.Request.MaxWt;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@JsonPropertyOrder({

})
@Builder
@Getter
@Setter
@NoArgsConstructor

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ReverseData implements Serializable {

	public final static long serialVersionUID = -8568744297811393904L;

}
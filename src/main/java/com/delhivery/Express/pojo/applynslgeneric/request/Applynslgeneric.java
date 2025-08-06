package com.delhivery.Express.pojo.applynslgeneric.request;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;


import lombok.*;


@Getter
@Setter
@Builder

public class Applynslgeneric {
	@JsonProperty("wbn")
	public Wbn wbn;
	@JsonProperty("user_name")
	public String userName;
	@JsonProperty("src")
	public String src;
	@JsonProperty("status_type")
	public String statusType;

}

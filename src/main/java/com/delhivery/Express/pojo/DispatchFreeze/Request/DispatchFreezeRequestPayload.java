package com.delhivery.Express.pojo.DispatchFreeze.Request;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
"center_code",
"ref_ids",
"dispatch_id",
"username",
"nsl_code",
"vh_number",
"scan_time"
})

@Builder
@Getter
@Setter
public class DispatchFreezeRequestPayload {

@JsonProperty("center_code")
public String centerCode;
@JsonProperty("ref_ids")
public List<String> refIds;
@JsonProperty("dispatch_id")
public String dispatchId;
@JsonProperty("username")
public String username;
@JsonProperty("nsl_code")
public String nslCode;
@JsonProperty("vh_number")
public String vhNumber;
@JsonProperty("scan_time")
public String scanTime;

}
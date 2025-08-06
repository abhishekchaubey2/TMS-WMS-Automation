package com.delhivery.Express.pojo.MarkPendingApi.Request;

import java.util.List;
import com.delhivery.Express.pojo.ClientDetailsFetch.Request.FetchClientDetailsRequestPayload;
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
"scan_remarks",
"scan_time"
})

@Builder
@Getter
@Setter
public class MarkPendingRequestPayload {

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
@JsonProperty("scan_remarks")
public String scanRemarks;
@JsonProperty("scan_time")
public String scanTime;

}
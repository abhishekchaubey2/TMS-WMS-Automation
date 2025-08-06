package pojo.ApplyNslApi.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApplyNslRequestPayload {
	@JsonProperty("nsl_id")
	private String nslId;

	@JsonProperty("scope")
	private String scope;

	@JsonProperty("status_type")
	private String statusType;

	@JsonProperty("status")
	private String status;

	@JsonProperty("wbns")
	private List<String> wbns;

	@JsonProperty("user")
	private String user;
}

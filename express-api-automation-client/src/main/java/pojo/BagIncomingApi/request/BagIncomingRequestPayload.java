package pojo.BagIncomingApi.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BagIncomingRequestPayload {
    @JsonProperty("slid")
    private String slid;

    @JsonProperty("ref_id")
    private String refId;

    @JsonProperty("unexpected")
    private Integer unexpected;
}

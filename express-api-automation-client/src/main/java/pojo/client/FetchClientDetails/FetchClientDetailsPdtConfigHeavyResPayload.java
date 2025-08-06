package pojo.client.FetchClientDetails;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class FetchClientDetailsPdtConfigHeavyResPayload {
    @JsonProperty("min_wt")
    private Long minWt;

    @JsonProperty("max_wt")
    private Long maxWt;
}
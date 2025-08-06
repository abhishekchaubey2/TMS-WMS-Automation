package pojo.FetchPackgeDetails.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class PackageInfoUcIdResPayload {
    @JsonProperty("is_cc_invalid")
    private boolean isCcInvalid;

    @JsonProperty("is_masked")
    private boolean isMasked;

    @JsonProperty("is_valid")
    private boolean isValid;

    @JsonProperty("is_mobile")
    private boolean isMobile;

    @JsonProperty("uci")
    private String uci;
}

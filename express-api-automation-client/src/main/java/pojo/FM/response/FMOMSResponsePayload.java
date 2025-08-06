package pojo.FM.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import lombok.ToString;

@Getter
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class FMOMSResponsePayload {
    @JsonProperty("data")
    private FMOMSDataResPayload data;

    @JsonProperty("success")
    private boolean success;

    @JsonProperty("error")
    private FMOMSErrorResPayload error;
}

package pojo.manifest.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import lombok.ToString;

import java.util.List;

@Getter
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class PackageResponsePayload {
    @JsonProperty("status")
    private String status;

    @JsonProperty("client")
    private String client;

    @JsonProperty("sort_code")
    private String sortCode;

    @JsonProperty("remarks")
    private List<String> remarks = null;

    @JsonProperty("waybill")
    private String waybill;

    @JsonProperty("cod_amount")
    private float codAmount;

    @JsonProperty("payment")
    private String payment;

    @JsonProperty("serviceable")
    private boolean serviceable;

    @JsonProperty("refnum")
    private String refnum;
}

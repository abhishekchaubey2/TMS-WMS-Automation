package pojo.bag.insta.InstaBaggingCreateApi.request;

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
public class InstaBaggingCreateRequestPayload {
    @JsonProperty("bd")
    private String bd;

    @JsonProperty("bi")
    private String bi;

    @JsonProperty("bs")
    private Object bs;

    @JsonProperty("bt")
    private String bt;

    @JsonProperty("dpc")
    private Boolean dpc;

    @JsonProperty("rm")
    private Boolean rm;

    @JsonProperty("capture_weight")
    private Boolean capture_weight;

    @JsonProperty("st")
    private String st;

    @JsonProperty("wbn")
    private String wbn;
}

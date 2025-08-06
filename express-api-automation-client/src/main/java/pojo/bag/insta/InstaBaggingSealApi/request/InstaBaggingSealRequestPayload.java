package pojo.bag.insta.InstaBaggingSealApi.request;

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
public class InstaBaggingSealRequestPayload {
    @JsonProperty("bd")
    private String bd;

    @JsonProperty("bi")
    private String bi;

    @JsonProperty("bs")
    private String bs;

    @JsonProperty("bt")
    private String bt;

    @JsonProperty("wbn")
    private String wbn;

    @JsonProperty("bar")
    private String bar;

    @JsonProperty("st")
    private String st;

    @JsonProperty("rm")
    private Boolean rm;

    @JsonProperty("dpc")
    private String dpc;
}

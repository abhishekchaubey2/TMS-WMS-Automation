package pojo.bag.BagV3Api.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CreateBagV3DWSReqPayload {
    @JsonProperty("sd")
    private String sd;

    @JsonProperty("u")
    private String u;

    @JsonProperty("rv")
    private String rv;

    @JsonProperty("h")
    private String h;

    @JsonProperty("b")
    private String b;

    @JsonProperty("l")
    private String l;

    @JsonProperty("wt")
    private String wt;

    @JsonProperty("is_vol")
    private Boolean isVol;

    @JsonProperty("is_box")
    private Boolean isBox;

    @JsonProperty("min_th")
    private Integer minTh;
}

package pojo.manifest.request;

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
public class ItemRequestPayload {
    @JsonProperty("descr")
    private String descr;

    @JsonProperty("ean")
    private String ean;

    @JsonProperty("brand")
    private String brand;

    @JsonProperty("si")
    private String si;

    @JsonProperty("imei")
    private String imei;

    @JsonProperty("reason")
    private String reason;

    @JsonProperty("images")
    private String images;

    @JsonProperty("serial")
    private String serial;

    @JsonProperty("pcat")
    private String pcat;

    @JsonProperty("item_quantity")
    private String itemQuantity;
}

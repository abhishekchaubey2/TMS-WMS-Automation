package pojo.client.FetchClientDetails;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class FetchClientDetailsPdtConfigResPayload {
    @JsonProperty("heavy")
    private FetchClientDetailsPdtConfigHeavyResPayload heavy;

    @JsonProperty("doc_flash")
    private FetchClientDetailsPdtConfigDocFlashResPayload docFlash;

    @JsonProperty("kyc")
    private FetchClientDetailsPdtConfigKYCResPayload kyc;

    @JsonProperty("flash_b2c_surface")
    private FetchClientDetailsPdtConfigFlashB2CSurfaceResPayload flashB2cSurface;

    @JsonProperty("flash_heavy")
    private FetchClientDetailsPdtConfigFlashHeavyResPayload flashHeavy;

    @JsonProperty("c2c_lite")
    private FetchClientDetailsPdtConfigC2CLiteResPayload c2cLite;

    @JsonProperty("flash_b2c_air")
    private FetchClientPDTConfigFlashB2CAirResPayload flashB2cAir;

    @JsonProperty("doc")
    private FetchClientDetailsPdtConfigDocResPayload doc;

    @JsonProperty("b2c")
    private FetchClientDetailsPdtConfigB2CPayload b2c;

    @JsonProperty("b2b")
    private FetchClientDetailsPdtConfigB2BResPayload b2b;
}
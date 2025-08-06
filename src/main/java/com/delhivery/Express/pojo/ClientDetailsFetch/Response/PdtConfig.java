package com.delhivery.Express.pojo.ClientDetailsFetch.Response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

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
@JsonPropertyOrder({
    "heavy",
    "doc_flash",
    "kyc",
    "flash_b2c_surface",
    "flash_heavy",
    "c2c_lite",
    "flash_b2c_air",
    "doc",
    "b2c",
    "b2b"
})
public class PdtConfig {

    @JsonProperty("heavy")
    public Heavy heavy;
    @JsonProperty("doc_flash")
    public DocFlash docFlash;
    @JsonProperty("kyc")
    public Kyc kyc;
    @JsonProperty("flash_b2c_surface")
    public FlashB2cSurface flashB2cSurface;
    @JsonProperty("flash_heavy")
    public FlashHeavy flashHeavy;
    @JsonProperty("c2c_lite")
    public C2cLite c2cLite;
    @JsonProperty("flash_b2c_air")
    public FlashB2cAir flashB2cAir;
    @JsonProperty("doc")
    public Doc doc;
    @JsonProperty("b2c")
    public B2c b2c;
    @JsonProperty("b2b")
    public B2b b2b;

}
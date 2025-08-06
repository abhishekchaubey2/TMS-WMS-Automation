package com.delhivery.Express.pojo.ManifestCmuCreateApi.request;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Item {
	
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

package com.delhivery.Express.pojo.Meesho1.Request;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import com.fasterxml.jackson.annotation.JsonProperty;

@Builder
@Getter
@Setter

public class ItemMeesho {
    @JsonProperty("descr")
    public Object descr;
    @JsonProperty("ean")
    public Object ean;
    @JsonProperty("brand")
    public Object brand;
    @JsonProperty("si")
    public Object si;
    @JsonProperty("imei")
    public Object imei;
    @JsonProperty("reason")
    public Object reason;
    @JsonProperty("images")
    public Object images;
    @JsonProperty("serial")
    public Object serial;
    @JsonProperty("pcat")
    public Object pcat;
    @JsonProperty("item_quantity")
    public Object itemQuantity;
}

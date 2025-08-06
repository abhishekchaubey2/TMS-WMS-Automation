package com.delhivery.Express.pojo.rebag.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ReBagDataReqPayload {
    @JsonProperty("action")
    private String action;

    @JsonProperty("bag_barcode")
    private String bagBarcode;

    @JsonProperty("sl")
    private String sl;

    @JsonProperty("slid")
    private String slid;
}

package com.delhivery.Express.pojo.FetchDcCenter.Response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "CC",
    "IPC",
    "GW",
    "HUB",
    "FC",
    "INTERNAL",
    "KIOSK",
    "DC",
    "PC",
    "RPC",
    "STATION",
    "DPC",
    "RDC",
    "IMPLANT",
    "SC",
    "DCT",
    "CP",
    "KK",
    "OCT",
    "STORE"
})
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FacilityType {

    @JsonProperty("CC")
    public boolean cc;
    @JsonProperty("IPC")
    public boolean ipc;
    @JsonProperty("GW")
    public boolean gw;
    @JsonProperty("HUB")
    public boolean hub;
    @JsonProperty("FC")
    public boolean fc;
    @JsonProperty("INTERNAL")
    public boolean internal;
    @JsonProperty("KIOSK")
    public boolean kiosk;
    @JsonProperty("DC")
    public boolean dc;
    @JsonProperty("PC")
    public boolean pc;
    @JsonProperty("RPC")
    public boolean rpc;
    @JsonProperty("STATION")
    public boolean station;
    @JsonProperty("DPC")
    public boolean dpc;
    @JsonProperty("RDC")
    public boolean rdc;
    @JsonProperty("IMPLANT")
    public boolean implant;
    @JsonProperty("SC")
    public boolean sc;
    @JsonProperty("DCT")
    public boolean dct;
    @JsonProperty("CP")
    public boolean cp;
    @JsonProperty("KK")
    public boolean kk;
    @JsonProperty("OCT")
    public boolean oct;
    @JsonProperty("STORE")
    public boolean store;

}

package com.delhivery.Express.pojo.Psearch.Response;

import com.delhivery.Express.pojo.SelfCollectApi.Response.Data;
import com.delhivery.Express.pojo.SelfCollectApi.Response.Meta;
import com.delhivery.Express.pojo.SelfCollectApi.Response.SelfCollectApiResponsePayload;
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

public class Result {
	@JsonProperty("zn")
	public String zn;
	@JsonProperty("adf_pin")
	public String adfPin;
	@JsonProperty("cn")
	public String cn;
	@JsonProperty("pin")
	public Integer pin;
	@JsonProperty("cl")
	public String cl;
	@JsonProperty("xraycs")
	public Boolean xraycs;
	@JsonProperty("adf_rcity")
	public String adfRcity;
	@JsonProperty("adf_rloc")
	public String adfRloc;
	@JsonProperty("oid")
	public String oid;
	@JsonProperty("pri")
	public Boolean pri;
	@JsonProperty("mot")
	public String mot;
	@JsonProperty("ar")
	public Boolean ar;
	@JsonProperty("adf_loc")
	public String adfLoc;
	@JsonProperty("offload")
	public Boolean offload;
	@JsonProperty("city")
	public String city;
	@JsonProperty("rpdd")
	public Object rpdd;
	@JsonProperty("rpin")
	public Integer rpin;
	@JsonProperty("incoming_trip")
	public Boolean incomingTrip;
	@JsonProperty("pt")
	public String pt;
	@JsonProperty("ss")
	public String ss;
	@JsonProperty("chute_id")
	public Object chuteId;
	@JsonProperty("ndc")
	public String ndc;
	@JsonProperty("pdt")
	public String pdt;
	@JsonProperty("adf_rpin")
	public String adfRpin;
	@JsonProperty("st")
	public String st;
	@JsonProperty("xray")
	public Boolean xray;
	@JsonProperty("cwh")
	public Object cwh;
	@JsonProperty("rcn")
	public Object rcn;
	@JsonProperty("rcnid")
	public Object rcnid;
	@JsonProperty("sl")
	public String sl;
	@JsonProperty("wbn")
	public String wbn;
	@JsonProperty("pdd")
	public Object pdd;
	@JsonProperty("rcity")
	public Object rcity;
	@JsonProperty("adf_city")
	public String adfCity;
}

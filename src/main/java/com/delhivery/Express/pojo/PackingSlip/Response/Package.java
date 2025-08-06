package com.delhivery.Express.pojo.PackingSlip.Response;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;

@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class Package {
    @JsonProperty("origin")
    private Object origin;

    @JsonProperty("invoice_reference")
    private Object invoiceReference;

    @JsonProperty("shipment_width")
    private float shipmentWidth;

    @JsonProperty("pin")
    private long pin;

    @JsonProperty("cl")
    private String cl;

    @JsonProperty("intl")
    private Object intl;

    @JsonProperty("origin_state_code")
    private Object originStateCode;

    @JsonProperty("cd")
    private String cd;

    @JsonProperty("ewbn")
    private List<Object> ewbn;

    @JsonProperty("rph")
    private String rph;

    @JsonProperty("shipment_length")
    private float shipmentLength;

    @JsonProperty("snm")
    private String snm;

    @JsonProperty("barcode")
    private String barcode;

    @JsonProperty("origin_city")
    private Object originCity;

    @JsonProperty("weight")
    private float weight;

    @JsonProperty("pt")
    private String pt;

    @JsonProperty("rs")
    private float rs;

    @JsonProperty("destination")
    private String destination;

    @JsonProperty("si")
    private String si;

    @JsonProperty("destination_city")
    private String destinationCity;

    @JsonProperty("hsn_code")
    private String hsnCode;

    @JsonProperty("tin")
    private String tin;

    @JsonProperty("contact")
    private String contact;

    @JsonProperty("origin_state")
    private String originState;

    @JsonProperty("fm_ucid")
    private String fmUcid;

    @JsonProperty("sid")
    private String sid;

    @JsonProperty("cst")
    private String cst;

    @JsonProperty("prd")
    private String prd;

    @JsonProperty("rcty")
    private Object rcty;

    @JsonProperty("consignee_gst_tin")
    private Object consigneeGstTin;

    @JsonProperty("cnph")
    private String cnph;

    @JsonProperty("sadd")
    private String sadd;

    @JsonProperty("oid_barcode")
    private String oidBarcode;

    @JsonProperty("oid")
    private String oid;

    @JsonProperty("customer_state")
    private String customerState;

    @JsonProperty("mot")
    private String mot;

    @JsonProperty("radd")
    private String radd;

    @JsonProperty("customer_state_code")
    private Object customerStateCode;

    @JsonProperty("address")
    private String address;

    @JsonProperty("rst")
    private Object rst;

    @JsonProperty("seller_gst_tin")
    private Object sellerGstTin;

    @JsonProperty("shipment_height")
    private float shipmentHeight;

    @JsonProperty("pdd")
    private Object pdd;

    @JsonProperty("product_type")
    private String productType;

    @JsonProperty("name")
    private String name;

    @JsonProperty("is_sdc")
    private Object isSdc;

    @JsonProperty("st_code")
    private Object stCode;

    @JsonProperty("cl_logo")
    private String clLogo;

    @JsonProperty("st")
    private String st;

    @JsonProperty("client_gst_tin")
    private Object clientGstTin;

    @JsonProperty("etc")
    private String etc;

    @JsonProperty("delhivery_logo")
    private String delhiveryLogo;

    @JsonProperty("client_type")
    private String clientType;

    @JsonProperty("cod")
    private float cod;

    @JsonProperty("wbn")
    private String wbn;

    @JsonProperty("sort_code")
    private String sortCode;

    @JsonProperty("rpin")
    private long rpin;

    @JsonProperty("einv_qr")
    private Boolean einvQr;
}

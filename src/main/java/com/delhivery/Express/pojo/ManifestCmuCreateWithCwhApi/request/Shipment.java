package com.delhivery.Express.pojo.ManifestCmuCreateWithCwhApi.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Shipment {

    @JsonProperty("lrn")
    public String lrn;
    @JsonProperty("name")
    public String name;
    @JsonProperty("client")
    public String client;
    @JsonProperty("waybill")
    public String waybill;
    @JsonProperty("order")
    public String order;
    @JsonProperty("products_desc")
    public String productsDesc;
    @JsonProperty("order_date")
    public String orderDate;
    @JsonProperty("payment_mode")
    public String paymentMode;
    @JsonProperty("cod_amount")
    public long codAmount;
    @JsonProperty("mps_amount")
    public long mpsAmount;
    @JsonProperty("product_type")
    public String productType;
    @JsonProperty("package_count")
    public long packageCount;
    @JsonProperty("total_amount")
    public long totalAmount;
    @JsonProperty("add")
    public String add;
    @JsonProperty("city")
    public String city;
    @JsonProperty("state")
    public String state;
    @JsonProperty("country")
    public String country;
    @JsonProperty("phone")
    public String phone;
    @JsonProperty("pin")
    public String pin;
    @JsonProperty("dangerous_good")
    public boolean dangerousGood;
    @JsonProperty("return_add")
    public String returnAdd;
    @JsonProperty("return_city")
    public String returnCity;
    @JsonProperty("return_country")
    public String returnCountry;
    @JsonProperty("return_name")
    public String returnName;
    @JsonProperty("return_phone")
    public String returnPhone;
    @JsonProperty("return_pin")
    public String returnPin;
    @JsonProperty("return_state")
    public String returnState;
    @JsonProperty("supplier")
    public String supplier;
    @JsonProperty("billable_weight")
    public long billableWeight;
    @JsonProperty("dimensions")
    public String dimensions;
    @JsonProperty("volumetric")
    public long volumetric;
    @JsonProperty("client_gst_tin")
    public String clientGstTin;
    @JsonProperty("consignee_gst_tin")
    public String consigneeGstTin;
    @JsonProperty("hsn_code")
    public String hsnCode;
    @JsonProperty("invoice_reference")
    public String invoiceReference;
    @JsonProperty("seller_gst_tin")
    public String sellerGstTin;
    @JsonProperty("weight")
    public String weight;
    @JsonProperty("shipping_mode")
    public String shippingMode;
    @JsonProperty("shipping_method")
    public String shippingMethod;
    @JsonProperty("weight_verification")
    public boolean weightVerification;
    @JsonProperty("essential_good")
    public boolean essentialGood;
    @JsonProperty("master_id")
    public String masterId;
    @JsonProperty("mps_children")
    public Long mpsChildren;
    @JsonProperty("shipment_type")
    public String shipmentType;
    @JsonProperty("internal")
    public Boolean internal;
    @JsonProperty("international")
    public String international;
    

}

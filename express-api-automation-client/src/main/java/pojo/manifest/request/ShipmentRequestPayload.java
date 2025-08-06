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
public class ShipmentRequestPayload {
    @JsonProperty("lrn")
    private String lrn;

    @JsonProperty("quantity")
    private Integer qty;

    @JsonProperty("qc")
    private QCRequestPayload qc;

    @JsonProperty("name")
    private String name;

    @JsonProperty("client")
    private String client;

    @JsonProperty("waybill")
    private String waybill;

    @JsonProperty("order")
    private String order;

    @JsonProperty("products_desc")
    private String productsDesc;

    @JsonProperty("order_date")
    private String orderDate;

    @JsonProperty("payment_mode")
    private String paymentMode;

    @JsonProperty("cod_amount")
    private Long codAmount;

    @JsonProperty("mps_amount")
    private Long mpsAmount;

    @JsonProperty("product_type")
    private String productType;

    @JsonProperty("package_count")
    private Long packageCount;

    @JsonProperty("total_amount")
    private Long totalAmount;

    @JsonProperty("add")
    private String add;

    @JsonProperty("city")
    private String city;

    @JsonProperty("state")
    private String state;

    @JsonProperty("country")
    private String country;

    @JsonProperty("phone")
    private String phone;

    @JsonProperty("pin")
    private String pin;

    @JsonProperty("dangerous_good")
    private Boolean dangerousGood;

    @JsonProperty("return_add")
    private String returnAdd;

    @JsonProperty("return_city")
    private String returnCity;

    @JsonProperty("return_country")
    private String returnCountry;

    @JsonProperty("return_name")
    private String returnName;

    @JsonProperty("return_phone")
    private String returnPhone;

    @JsonProperty("return_pin")
    private String returnPin;

    @JsonProperty("return_state")
    private String returnState;

    @JsonProperty("supplier")
    private String supplier;

    @JsonProperty("billable_weight")
    private Double billableWeight;

    @JsonProperty("dimensions")
    private String dimensions;

    @JsonProperty("volumetric")
    private Double volumetric;

    @JsonProperty("client_gst_tin")
    private String clientGstTin;

    @JsonProperty("consignee_gst_tin")
    private String consigneeGstTin;

    @JsonProperty("hsn_code")
    private String hsnCode;

    @JsonProperty("invoice_reference")
    private String invoiceReference;

    @JsonProperty("seller_gst_tin")
    private String sellerGstTin;

    @JsonProperty("weight")
    private String weight;

    @JsonProperty("shipping_mode")
    private String shippingMode;

    @JsonProperty("shipping_method")
    private String shippingMethod;

    @JsonProperty("weight_verification")
    private Boolean weightVerification;

    @JsonProperty("essential_good")
    private String essentialGood;

    @JsonProperty("fragile_shipment")
    private String fragileShipment;

    @JsonProperty("master_id")
    private String masterId;

    @JsonProperty("mps_children")
    private Long mpsChildren;

    @JsonProperty("shipment_type")
    private String shipmentType;

    @JsonProperty("internal")
    private Boolean internal;

    @JsonProperty("international")
    private String international;

    @JsonProperty("quality_check")
    private Boolean qualityCheck;

    @JsonProperty("einv_qr")
    private Object einvQr;

    @JsonProperty("is_otp_verified")
    private String isOtpVerified;
}

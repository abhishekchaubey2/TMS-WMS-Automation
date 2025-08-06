package com.delhivery.Express.pojo.ClientDetailsFetch.Response;

import java.util.List;

import com.delhivery.Express.pojo.ClientDetailsFetch.Response.PdtConfig;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonPropertyOrder({
    "enable_self_collect_otp",
    "min_rvp_img_count",
    "lock_invalid_ewbn",
    "extra",
    "bird_migration_date",
    "cl_call_masking",
    "active",
    "allow_no_data",
    "registered_email",
    "user_uuid_key",
    "risk_appetite_bucket",
    "rvp_img_count",
    "wallet_credit_limit",
    "waybill_prefix",
    "accepted_ids",
    "is_prepaid",
    "registered_name",
    "payment_link",
    "b2b_serviceability_type",
    "client_name",
    "registered_phone",
    "revenue_mapping",
    "billing_mode",
    "gen_ewbn_rto",
    "gen_ewbn_fwd",
    "wallet_id",
    "use_bird_service",
    "rvp_img_item",
    "mps_service",
    "persons_allowed",
    "expires",
    "mot",
    "alias",
    "rvp_img",
    "qr_payment",
    "wallet_provider",
    "pdt_config",
    "services",
    "product_type",
    "freight_collection",
    "enable_ivr",
    "payment",
    "client_uuid_key",
    "billing_method",
    "allow_large_mps_lot",
    "last_password_changed",
    "divisor",
    "auto_pickup",
    "ewbn_vh_update",
    "client_type",
    "billing_on",
    "registered_address",
    "nodes_tenant"
})
public class Data {

    @JsonProperty("enable_self_collect_otp")
    public boolean enableSelfCollectOtp;
    @JsonProperty("min_rvp_img_count")
    public long minRvpImgCount;
    @JsonProperty("lock_invalid_ewbn")
    public boolean lockInvalidEwbn;
    @JsonProperty("extra")
    public Extra extra;
    @JsonProperty("bird_migration_date")
    public String birdMigrationDate;
    @JsonProperty("cl_call_masking")
    public boolean clCallMasking;
    @JsonProperty("active")
    public boolean active;
    @JsonProperty("allow_no_data")
    public boolean allowNoData;
    @JsonProperty("registered_email")
    public String registeredEmail;
    @JsonProperty("user_uuid_key")
    public String userUuidKey;
    @JsonProperty("risk_appetite_bucket")
    public String riskAppetiteBucket;
    @JsonProperty("rvp_img_count")
    public long rvpImgCount;
    @JsonProperty("wallet_credit_limit")
    public String walletCreditLimit;
    @JsonProperty("waybill_prefix")
    public long waybillPrefix;
    @JsonProperty("accepted_ids")
    public List<AcceptedId> acceptedIds;
    @JsonProperty("is_prepaid")
    public boolean isPrepaid;
    @JsonProperty("registered_name")
    public String registeredName;
    @JsonProperty("payment_link")
    public boolean paymentLink;
    @JsonProperty("b2b_serviceability_type")
    public Object b2bServiceabilityType;
    @JsonProperty("client_name")
    public String clientName;
    @JsonProperty("registered_phone")
    public String registeredPhone;
    @JsonProperty("revenue_mapping")
    public String revenueMapping;
    @JsonProperty("billing_mode")
    public Object billingMode;
    @JsonProperty("gen_ewbn_rto")
    public boolean genEwbnRto;
    @JsonProperty("gen_ewbn_fwd")
    public boolean genEwbnFwd;
    @JsonProperty("wallet_id")
    public Object walletId;
    @JsonProperty("use_bird_service")
    public boolean useBirdService;
    @JsonProperty("rvp_img_item")
    public boolean rvpImgItem;
    @JsonProperty("mps_service")
    public boolean mpsService;
    @JsonProperty("persons_allowed")
    public List<String> personsAllowed;
    @JsonProperty("expires")
    public String expires;
    @JsonProperty("mot")
    public Mot mot;
    @JsonProperty("alias")
    public String alias;
    @JsonProperty("rvp_img")
    public boolean rvpImg;
    @JsonProperty("qr_payment")
    public boolean qrPayment;
    @JsonProperty("wallet_provider")
    public Object walletProvider;
    @JsonProperty("pdt_config")
    public PdtConfig pdtConfig;
    @JsonProperty("services")
    public List<List<String>> services;
    @JsonProperty("product_type")
    public Object productType;
    @JsonProperty("freight_collection")
    public boolean freightCollection;
    @JsonProperty("enable_ivr")
    public boolean enableIvr;
    @JsonProperty("payment")
    public List<List<String>> payment;
    @JsonProperty("client_uuid_key")
    public String clientUuidKey;
    @JsonProperty("billing_method")
    public String billingMethod;
    @JsonProperty("allow_large_mps_lot")
    public boolean allowLargeMpsLot;
    @JsonProperty("last_password_changed")
    public Object lastPasswordChanged;
    @JsonProperty("divisor")
    public float divisor;
    @JsonProperty("auto_pickup")
    public boolean autoPickup;
    @JsonProperty("ewbn_vh_update")
    public boolean ewbnVhUpdate;
    @JsonProperty("client_type")
    public String clientType;
    @JsonProperty("billing_on")
    public String billingOn;
    @JsonProperty("registered_address")
    public String registeredAddress;
    @JsonProperty("nodes_tenant")
    public String nodesTenant;

}
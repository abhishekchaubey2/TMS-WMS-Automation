
package com.delhivery.Express.pojo.FetchClientUuidDetailsResponsePayloadApi.response;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "otp_generation",
    "max_pick_up_tat",
    "bird_migration_date",
    "instant_return",
    "pod_description",
    "cheque_on_delivery",
    "cod_instructions",
    "b2b_serviceability_type",
    "weight_status",
    "gen_ewbn_rto",
    "use_cmu_service",
    "valid_for_return",
    "wallet_id",
    "use_bird_service",
    "gst_rate",
    "send_sms_prepaid",
    "is_active",
    "mot",
    "return_behaviour",
    "dp",
    "use_waybill_service",
    "name",
    "notify_pkg_type",
    "cut_off_time",
    "ewbn_vh_update",
    "client_type",
    "billing_gst_config",
    "mps_service",
    "enable_self_collect_otp",
    "cl_call_masking",
    "allow_no_data",
    "card_on_delivery",
    "ewbn_edit_enable",
    "waybill_prefix",
    "warehouse_state_gstin",
    "rp",
    "billing_mode",
    "rvp_img_item",
    "send_sms_cash",
    "ewbn_rt_lock",
    "qr_payment",
    "dad_nsl_codes",
    "freight_collection",
    "parent_account_name",
    "client_ho_state_gstin",
    "b2c_serviceability_type",
    "allow_large_mps_lot",
    "divisor",
    "billing_on",
    "ewbn_fwd_block",
    "nodes_tenant",
    "weight_verification",
    "lock_invalid_ewbn",
    "extra",
    "od_tat_temp",
    "xp",
    "send_pickup_sms",
    "pt",
    "loss_return_behaviour",
    "pf",
    "gen_ewbn_fwd",
    "pk",
    "pm",
    "end_date",
    "ho_state",
    "send_sms_reverse",
    "wallet_provider",
    "product_type",
    "is_prepaid",
    "dad_attempts",
    "billing_method",
    "esntl",
    "ewbn_mfst_enable",
    "gst_states",
    "send_sms_ndr",
    "mps_enabled",
    "min_rvp_img_count",
    "lock",
    "wallet_on_delivery",
    "alternate_pod",
    "uuid_key",
    "rvp_img_count",
    "alias",
    "send_sms_cod",
    "push_url",
    "payment_link",
    "pkg_invoice",
    "gst_billing_type",
    "seller_details_field",
    "delivery_behaviour",
    "enable_ivr",
    "default_wallet_balance",
    "person_specific_delivery",
    "dispatch_after_cancellation",
    "return_ship_time_delay",
    "return_add",
    "rvp_img",
    "clcd",
    "auto_pickup",
    "fm_cut_off_time",
    "return_serviceability_type",
    "claims",
    "allow_cancellation_via_otp"
})
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class Data {

    @JsonProperty("otp_generation")
    public String otp_generation;
    @JsonProperty("max_pick_up_tat")
    public int max_pick_up_tat;
    @JsonProperty("bird_migration_date")
    public String bird_migration_date;
    @JsonProperty("instant_return")
    public boolean instant_return;
    @JsonProperty("pod_description")
    public String pod_description;
    @JsonProperty("cheque_on_delivery")
    public boolean cheque_on_delivery;
    @JsonProperty("cod_instructions")
    public String cod_instructions;
    @JsonProperty("b2b_serviceability_type")
    public Object b2b_serviceability_type;
    @JsonProperty("weight_status")
    public String weight_status;
    @JsonProperty("gen_ewbn_rto")
    public boolean gen_ewbn_rto;
    @JsonProperty("use_cmu_service")
    public boolean use_cmu_service;
    @JsonProperty("valid_for_return")
    public boolean valid_for_return;
    @JsonProperty("wallet_id")
    public String wallet_id;
    @JsonProperty("use_bird_service")
    public boolean use_bird_service;
    @JsonProperty("gst_rate")
    public String gst_rate;
    @JsonProperty("send_sms_prepaid")
    public boolean send_sms_prepaid;
    @JsonProperty("is_active")
    public boolean is_active;
    @JsonProperty("mot")
    public Mot mot;
    @JsonProperty("return_behaviour")
    public Return_behaviour return_behaviour;
    @JsonProperty("dp")
    public float dp;
    @JsonProperty("use_waybill_service")
    public boolean use_waybill_service;
    @JsonProperty("name")
    public String name;
    @JsonProperty("notify_pkg_type")
    public List<Object> notify_pkg_type;
    @JsonProperty("cut_off_time")
    public String cut_off_time;
    @JsonProperty("ewbn_vh_update")
    public boolean ewbn_vh_update;
    @JsonProperty("client_type")
    public String client_type;
    @JsonProperty("billing_gst_config")
    public String billing_gst_config;
    @JsonProperty("mps_service")
    public boolean mps_service;
    @JsonProperty("enable_self_collect_otp")
    public boolean enable_self_collect_otp;
    @JsonProperty("cl_call_masking")
    public boolean cl_call_masking;
    @JsonProperty("allow_no_data")
    public boolean allow_no_data;
    @JsonProperty("card_on_delivery")
    public boolean card_on_delivery;
    @JsonProperty("ewbn_edit_enable")
    public boolean ewbn_edit_enable;
    @JsonProperty("waybill_prefix")
    public int waybill_prefix;
    @JsonProperty("warehouse_state_gstin")
    public boolean warehouse_state_gstin;
    @JsonProperty("rp")
    public float rp;
    @JsonProperty("billing_mode")
    public String billing_mode;
    @JsonProperty("rvp_img_item")
    public boolean rvp_img_item;
    @JsonProperty("send_sms_cash")
    public boolean send_sms_cash;
    @JsonProperty("ewbn_rt_lock")
    public boolean ewbn_rt_lock;
    @JsonProperty("qr_payment")
    public boolean qr_payment;
    @JsonProperty("dad_nsl_codes")
    public List<String> dad_nsl_codes;
    @JsonProperty("freight_collection")
    public boolean freight_collection;
    @JsonProperty("parent_account_name")
    public String parent_account_name;
    @JsonProperty("client_ho_state_gstin")
    public boolean client_ho_state_gstin;
    @JsonProperty("b2c_serviceability_type")
    public Object b2c_serviceability_type;
    @JsonProperty("allow_large_mps_lot")
    public boolean allow_large_mps_lot;
    @JsonProperty("divisor")
    public float divisor;
    @JsonProperty("billing_on")
    public String billing_on;
    @JsonProperty("ewbn_fwd_block")
    public boolean ewbn_fwd_block;
    @JsonProperty("nodes_tenant")
    public String nodes_tenant;
    @JsonProperty("weight_verification")
    public Weight_verification weight_verification;
    @JsonProperty("lock_invalid_ewbn")
    public boolean lock_invalid_ewbn;
    @JsonProperty("extra")
    public Extra extra;
    @JsonProperty("od_tat_temp")
    public String od_tat_temp;
    @JsonProperty("xp")
    public float xp;
    @JsonProperty("send_pickup_sms")
    public boolean send_pickup_sms;
    @JsonProperty("pt")
    public List<String> pt;
    @JsonProperty("loss_return_behaviour")
    public Loss_return_behaviour loss_return_behaviour;
    @JsonProperty("pf")
    public int pf;
    @JsonProperty("gen_ewbn_fwd")
    public boolean gen_ewbn_fwd;
    @JsonProperty("pk")
    public int pk;
    @JsonProperty("pm")
    public List<Object> pm;
    @JsonProperty("end_date")
    public String end_date;
    @JsonProperty("ho_state")
    public String ho_state;
    @JsonProperty("send_sms_reverse")
    public boolean send_sms_reverse;
    @JsonProperty("wallet_provider")
    public String wallet_provider;
    @JsonProperty("product_type")
    public String product_type;
    @JsonProperty("is_prepaid")
    public boolean is_prepaid;
    @JsonProperty("dad_attempts")
    public int dad_attempts;
    @JsonProperty("billing_method")
    public String billing_method;
    @JsonProperty("esntl")
    public boolean esntl;
    @JsonProperty("ewbn_mfst_enable")
    public boolean ewbn_mfst_enable;
    @JsonProperty("gst_states")
    public Gst_states gst_states;
    @JsonProperty("send_sms_ndr")
    public boolean send_sms_ndr;
    @JsonProperty("mps_enabled")
    public boolean mps_enabled;
    @JsonProperty("min_rvp_img_count")
    public int min_rvp_img_count;
    @JsonProperty("lock")
    public boolean lock;
    @JsonProperty("wallet_on_delivery")
    public boolean wallet_on_delivery;
    @JsonProperty("alternate_pod")
    public boolean alternate_pod;
    @JsonProperty("uuid_key")
    public String uuid_key;
    @JsonProperty("rvp_img_count")
    public int rvp_img_count;
    @JsonProperty("alias")
    public String alias;
    @JsonProperty("send_sms_cod")
    public boolean send_sms_cod;
    @JsonProperty("push_url")
    public String push_url;
    @JsonProperty("payment_link")
    public boolean payment_link;
    @JsonProperty("pkg_invoice")
    public boolean pkg_invoice;
    @JsonProperty("gst_billing_type")
    public String gst_billing_type;
    @JsonProperty("seller_details_field")
    public Seller_details_field seller_details_field;
    @JsonProperty("delivery_behaviour")
    public Delivery_behaviour delivery_behaviour;
    @JsonProperty("enable_ivr")
    public boolean enable_ivr;
    @JsonProperty("default_wallet_balance")
    public String default_wallet_balance;
    @JsonProperty("person_specific_delivery")
    public boolean person_specific_delivery;
    @JsonProperty("dispatch_after_cancellation")
    public boolean dispatch_after_cancellation;
    @JsonProperty("return_ship_time_delay")
    public int return_ship_time_delay;
    @JsonProperty("return_add")
    public String return_add;
    @JsonProperty("rvp_img")
    public boolean rvp_img;
    @JsonProperty("clcd")
    public String clcd;
    @JsonProperty("auto_pickup")
    public boolean auto_pickup;
    @JsonProperty("fm_cut_off_time")
    public Object fm_cut_off_time;
    @JsonProperty("return_serviceability_type")
    public Object return_serviceability_type;
    @JsonProperty("claims")
    public Claims claims;
    @JsonProperty("allow_cancellation_via_otp")
    public boolean allow_cancellation_via_otp;

}

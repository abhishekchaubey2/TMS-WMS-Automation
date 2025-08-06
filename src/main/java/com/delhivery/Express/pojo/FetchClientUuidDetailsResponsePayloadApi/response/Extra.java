
package com.delhivery.Express.pojo.FetchClientUuidDetailsResponsePayloadApi.response;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;


@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "pod_city",
    "brand_name",
    "pur_pick",
    "contactless_del",
    "label_type",
    "enable_dos",
    "brand_logo",
    "enable_srv",
    "otp_hard_check",
    "enable_b2b_rvp_sms",
    "excl_lockdown",
    "is_pri_enabled",
    "client_category",
    "pod_cell",
    "retail_partner",
    "pod_cn",
    "disable_fod_payment",
    "rto_ageing",
    "sop_type",
    "lr_limitation",
    "fail_rvp_manifest",
    "flat_max",
    "mandatory_weight_capture",
    "enable_fm_pickup_sms_service",
    "flat_min",
    "weight_capture_enable",
    "fragile_shipment",
    "on_demand_fail",
    "e_pod_req",
    "flat_rate",
    "disable_cod",
    "alt_prepaid_ver",
    "fail_manifest",
    "exempt_express_delivery",
    "capture_client_otp",
    "enable_self_collect_pod",
    "cq_fix_add",
    "is_so",
    "email_subscription",
    "allowed_brands",
    "oda_config",
    "region",
    "cq_phone",
    "restrict_weight_srv",
    "pod_add",
    "otp_pkup_verification",
    "cq_pin",
    "delivery_verification",
    "exception_auto_clear",
    "verified_add",
    "pod_stamp",
    "rvp_img_on_qc_failure",
    "pod_pin",
    "brandname",
    "disable_document_waybill",
    "inv_verif",
    "customs_declaration",
    "is_tat_sensitive",
    "inv_restrict",
    "pod_fix_add",
    "capture_non_qc_images",
    "return_rec_id_serv_template",
    "cq_pickup_loc",
    "billed_by",
    "sunday_dlv",
    "pod_phone",
    "enable_manifest_failure",
    "restrict_mapper",
    "cq_st",
    "tat_sensitivity",
    "enable_sms_service",
    "pod_st",
    "enable_static_waybill",
    "is_bfsi_branch",
    "cq_add",
    "depot",
    "enable_manifestation_service",
    "plastic_packaging",
    "mandate_manifest_doc_upload",
    "secure_pickup",
    "pod_status_inv",
    "cq_city",
    "cq_cn",
    "alt_cod_ver",
    "pod_pickup_add",
    "custom_oda",
    "ondc_manifestation",
    "rvp_qc_serv",
    "validate_phone"
})
@Getter
@Setter
@NoArgsConstructor
public class Extra {

    @JsonProperty("pod_city")
    public String pod_city;
    @JsonProperty("brand_name")
    public boolean brand_name;
    @JsonProperty("pur_pick")
    public boolean pur_pick;
    @JsonProperty("contactless_del")
    public String contactless_del;
    @JsonProperty("label_type")
    public String label_type;
    @JsonProperty("enable_dos")
    public boolean enable_dos;
    @JsonProperty("brand_logo")
    public boolean brand_logo;
    @JsonProperty("enable_srv")
    public boolean enable_srv;
    @JsonProperty("otp_hard_check")
    public List<Object> otp_hard_check;
    @JsonProperty("enable_b2b_rvp_sms")
    public boolean enable_b2b_rvp_sms;
    @JsonProperty("excl_lockdown")
    public boolean excl_lockdown;
    @JsonProperty("is_pri_enabled")
    public boolean is_pri_enabled;
    @JsonProperty("client_category")
    public String client_category;
    @JsonProperty("pod_cell")
    public boolean pod_cell;
    @JsonProperty("retail_partner")
    public boolean retail_partner;
    @JsonProperty("pod_cn")
    public String pod_cn;
    @JsonProperty("disable_fod_payment")
    public boolean disable_fod_payment;
    @JsonProperty("rto_ageing")
    public boolean rto_ageing;
    @JsonProperty("sop_type")
    public String sop_type;
    @JsonProperty("lr_limitation")
    public int lr_limitation;
    @JsonProperty("fail_rvp_manifest")
    public boolean fail_rvp_manifest;
    @JsonProperty("flat_max")
    public String flat_max;
    @JsonProperty("mandatory_weight_capture")
    public boolean mandatory_weight_capture;
    @JsonProperty("enable_fm_pickup_sms_service")
    public boolean enable_fm_pickup_sms_service;
    @JsonProperty("flat_min")
    public String flat_min;
    @JsonProperty("weight_capture_enable")
    public boolean weight_capture_enable;
    @JsonProperty("fragile_shipment")
    public boolean fragile_shipment;
    @JsonProperty("on_demand_fail")
    public boolean on_demand_fail;
    @JsonProperty("e_pod_req")
    public boolean e_pod_req;
    @JsonProperty("flat_rate")
    public boolean flat_rate;
    @JsonProperty("disable_cod")
    public boolean disable_cod;
    @JsonProperty("alt_prepaid_ver")
    public String alt_prepaid_ver;
    @JsonProperty("fail_manifest")
    public boolean fail_manifest;
    @JsonProperty("exempt_express_delivery")
    public boolean exempt_express_delivery;
    @JsonProperty("capture_client_otp")
    public boolean capture_client_otp;
    @JsonProperty("enable_self_collect_pod")
    public boolean enable_self_collect_pod;
    @JsonProperty("cq_fix_add")
    public boolean cq_fix_add;
    @JsonProperty("is_so")
    public boolean is_so;
    @JsonProperty("email_subscription")
    public boolean email_subscription;
    @JsonProperty("allowed_brands")
    public String allowed_brands;
    @JsonProperty("oda_config")
    public String oda_config;
    @JsonProperty("region")
    public String region;
    @JsonProperty("cq_phone")
    public String cq_phone;
    @JsonProperty("restrict_weight_srv")
    public boolean restrict_weight_srv;
    @JsonProperty("pod_add")
    public String pod_add;
    @JsonProperty("otp_pkup_verification")
    public String otp_pkup_verification;
    @JsonProperty("cq_pin")
    public String cq_pin;
    @JsonProperty("delivery_verification")
    public boolean delivery_verification;
    @JsonProperty("exception_auto_clear")
    public boolean exception_auto_clear;
    @JsonProperty("verified_add")
    public boolean verified_add;
    @JsonProperty("pod_stamp")
    public boolean pod_stamp;
    @JsonProperty("rvp_img_on_qc_failure")
    public boolean rvp_img_on_qc_failure;
    @JsonProperty("pod_pin")
    public String pod_pin;
    @JsonProperty("brandname")
    public String brandname;
    @JsonProperty("disable_document_waybill")
    public boolean disable_document_waybill;
    @JsonProperty("inv_verif")
    public boolean inv_verif;
    @JsonProperty("customs_declaration")
    public String customs_declaration;
    @JsonProperty("is_tat_sensitive")
    public boolean is_tat_sensitive;
    @JsonProperty("inv_restrict")
    public boolean inv_restrict;
    @JsonProperty("pod_fix_add")
    public boolean pod_fix_add;
    @JsonProperty("capture_non_qc_images")
    public boolean capture_non_qc_images;
    @JsonProperty("return_rec_id_serv_template")
    public String return_rec_id_serv_template;
    @JsonProperty("cq_pickup_loc")
    public boolean cq_pickup_loc;
    @JsonProperty("billed_by")
    public String billed_by;
    @JsonProperty("sunday_dlv")
    public boolean sunday_dlv;
    @JsonProperty("pod_phone")
    public String pod_phone;
    @JsonProperty("enable_manifest_failure")
    public boolean enable_manifest_failure;
    @JsonProperty("restrict_mapper")
    public boolean restrict_mapper;
    @JsonProperty("cq_st")
    public String cq_st;
    @JsonProperty("tat_sensitivity")
    public float tat_sensitivity;
    @JsonProperty("enable_sms_service")
    public boolean enable_sms_service;
    @JsonProperty("pod_st")
    public String pod_st;
    @JsonProperty("enable_static_waybill")
    public boolean enable_static_waybill;
    @JsonProperty("is_bfsi_branch")
    public boolean is_bfsi_branch;
    @JsonProperty("cq_add")
    public String cq_add;
    @JsonProperty("depot")
    public String depot;
    @JsonProperty("enable_manifestation_service")
    public boolean enable_manifestation_service;
    @JsonProperty("plastic_packaging")
    public boolean plastic_packaging;
    @JsonProperty("mandate_manifest_doc_upload")
    public boolean mandate_manifest_doc_upload;
    @JsonProperty("secure_pickup")
    public boolean secure_pickup;
    @JsonProperty("pod_status_inv")
    public boolean pod_status_inv;
    @JsonProperty("cq_city")
    public String cq_city;
    @JsonProperty("cq_cn")
    public String cq_cn;
    @JsonProperty("alt_cod_ver")
    public String alt_cod_ver;
    @JsonProperty("pod_pickup_add")
    public boolean pod_pickup_add;
    @JsonProperty("custom_oda")
    public boolean custom_oda;
    @JsonProperty("ondc_manifestation")
    public boolean ondc_manifestation;
    @JsonProperty("rvp_qc_serv")
    public boolean rvp_qc_serv;
    @JsonProperty("validate_phone")
    public boolean validate_phone;

}

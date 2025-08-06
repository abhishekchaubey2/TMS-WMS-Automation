package com.delhivery.Express.pojo.ClientDetailsFetch.Response;

import java.util.List;

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
    "pod_phone",
    "sunday_dlv",
    "pod_city",
    "cq_pin",
    "enable_manifest_failure",
    "brand_name",
    "restrict_mapper",
    "exception_auto_clear",
    "flat_max",
    "mandatory_weight_capture",
    "cq_phone",
    "contactless_del",
    "allowed_brands",
    "enable_fm_pickup_sms_service",
    "flat_min",
    "enable_sms_service",
    "weight_capture_enable",
    "rvp_img_on_qc_failure",
    "on_demand_fail",
    "cq_st",
    "disable_document_waybill",
    "e_pod_req",
    "rvp_qc_serv",
    "disable_fod_payment",
    "tat_sensitivity",
    "enable_static_waybill",
    "flat_rate",
    "plastic_packaging",
    "verified_add",
    "sop_type",
    "is_bfsi_branch",
    "cq_add",
    "depot",
    "disable_cod",
    "cq_pickup_loc",
    "alt_prepaid_ver",
    "ondc_manifestation",
    "fragile_shipment",
    "exempt_express_delivery",
    "brand_logo",
    "customs_declaration",
    "pod_pin",
    "enable_srv",
    "delivery_verification",
    "oda_config",
    "enable_self_collect_pod",
    "brandname",
    "pod_st",
    "cq_fix_add",
    "billed_by",
    "is_so",
    "cq_cn",
    "otp_hard_check",
    "enable_b2b_rvp_sms",
    "pod_add",
    "email_subscription",
    "alt_cod_ver",
    "pod_stamp",
    "excl_lockdown",
    "inv_verif",
    "is_tat_sensitive",
    "cq_city",
    "inv_restrict",
    "pod_pickup_add",
    "client_category",
    "label_type",
    "pod_fix_add",
    "otp_pkup_verification",
    "pod_cell",
    "capture_non_qc_images",
    "return_rec_id_serv_template",
    "pod_cn",
    "restrict_weight_srv",
    "fail_manifest",
    "pur_pick",
    "rto_ageing",
    "enable_dos",
    "region",
    "retail_partner",
    "validate_phone"
})
public class Extra {

    @JsonProperty("pod_phone")
    public String podPhone;
    @JsonProperty("sunday_dlv")
    public boolean sundayDlv;
    @JsonProperty("pod_city")
    public String podCity;
    @JsonProperty("cq_pin")
    public String cqPin;
    @JsonProperty("enable_manifest_failure")
    public boolean enableManifestFailure;
    @JsonProperty("brand_name")
    public boolean brandName;
    @JsonProperty("restrict_mapper")
    public boolean restrictMapper;
    @JsonProperty("exception_auto_clear")
    public boolean exceptionAutoClear;
    @JsonProperty("flat_max")
    public String flatMax;
    @JsonProperty("mandatory_weight_capture")
    public boolean mandatoryWeightCapture;
    @JsonProperty("cq_phone")
    public String cqPhone;
    @JsonProperty("contactless_del")
    public String contactlessDel;
    @JsonProperty("allowed_brands")
    public String allowedBrands;
    @JsonProperty("enable_fm_pickup_sms_service")
    public boolean enableFmPickupSmsService;
    @JsonProperty("flat_min")
    public String flatMin;
    @JsonProperty("enable_sms_service")
    public boolean enableSmsService;
    @JsonProperty("weight_capture_enable")
    public boolean weightCaptureEnable;
    @JsonProperty("rvp_img_on_qc_failure")
    public boolean rvpImgOnQcFailure;
    @JsonProperty("on_demand_fail")
    public boolean onDemandFail;
    @JsonProperty("cq_st")
    public String cqSt;
    @JsonProperty("disable_document_waybill")
    public boolean disableDocumentWaybill;
    @JsonProperty("e_pod_req")
    public boolean ePodReq;
    @JsonProperty("rvp_qc_serv")
    public boolean rvpQcServ;
    @JsonProperty("disable_fod_payment")
    public boolean disableFodPayment;
    @JsonProperty("tat_sensitivity")
    public float tatSensitivity;
    @JsonProperty("enable_static_waybill")
    public boolean enableStaticWaybill;
    @JsonProperty("flat_rate")
    public boolean flatRate;
    @JsonProperty("plastic_packaging")
    public boolean plasticPackaging;
    @JsonProperty("verified_add")
    public boolean verifiedAdd;
    @JsonProperty("sop_type")
    public String sopType;
    @JsonProperty("is_bfsi_branch")
    public boolean isBfsiBranch;
    @JsonProperty("cq_add")
    public String cqAdd;
    @JsonProperty("depot")
    public String depot;
    @JsonProperty("disable_cod")
    public boolean disableCod;
    @JsonProperty("cq_pickup_loc")
    public boolean cqPickupLoc;
    @JsonProperty("alt_prepaid_ver")
    public String altPrepaidVer;
    @JsonProperty("ondc_manifestation")
    public boolean ondcManifestation;
    @JsonProperty("fragile_shipment")
    public boolean fragileShipment;
    @JsonProperty("exempt_express_delivery")
    public boolean exemptExpressDelivery;
    @JsonProperty("brand_logo")
    public boolean brandLogo;
    @JsonProperty("customs_declaration")
    public String customsDeclaration;
    @JsonProperty("pod_pin")
    public String podPin;
    @JsonProperty("enable_srv")
    public boolean enableSrv;
    @JsonProperty("delivery_verification")
    public boolean deliveryVerification;
    @JsonProperty("oda_config")
    public String odaConfig;
    @JsonProperty("enable_self_collect_pod")
    public boolean enableSelfCollectPod;
    @JsonProperty("brandname")
    public String brandname;
    @JsonProperty("pod_st")
    public String podSt;
    @JsonProperty("cq_fix_add")
    public boolean cqFixAdd;
    @JsonProperty("billed_by")
    public String billedBy;
    @JsonProperty("is_so")
    public boolean isSo;
    @JsonProperty("cq_cn")
    public String cqCn;
    @JsonProperty("otp_hard_check")
    public List<Object> otpHardCheck;
    @JsonProperty("enable_b2b_rvp_sms")
    public boolean enableB2bRvpSms;
    @JsonProperty("pod_add")
    public String podAdd;
    @JsonProperty("email_subscription")
    public boolean emailSubscription;
    @JsonProperty("alt_cod_ver")
    public String altCodVer;
    @JsonProperty("pod_stamp")
    public boolean podStamp;
    @JsonProperty("excl_lockdown")
    public boolean exclLockdown;
    @JsonProperty("inv_verif")
    public boolean invVerif;
    @JsonProperty("is_tat_sensitive")
    public boolean isTatSensitive;
    @JsonProperty("cq_city")
    public String cqCity;
    @JsonProperty("inv_restrict")
    public boolean invRestrict;
    @JsonProperty("pod_pickup_add")
    public boolean podPickupAdd;
    @JsonProperty("client_category")
    public String clientCategory;
    @JsonProperty("label_type")
    public String labelType;
    @JsonProperty("pod_fix_add")
    public boolean podFixAdd;
    @JsonProperty("otp_pkup_verification")
    public String otpPkupVerification;
    @JsonProperty("pod_cell")
    public boolean podCell;
    @JsonProperty("capture_non_qc_images")
    public boolean captureNonQcImages;
    @JsonProperty("return_rec_id_serv_template")
    public String returnRecIdServTemplate;
    @JsonProperty("pod_cn")
    public String podCn;
    @JsonProperty("restrict_weight_srv")
    public boolean restrictWeightSrv;
    @JsonProperty("fail_manifest")
    public boolean failManifest;
    @JsonProperty("pur_pick")
    public boolean purPick;
    @JsonProperty("rto_ageing")
    public boolean rtoAgeing;
    @JsonProperty("enable_dos")
    public boolean enableDos;
    @JsonProperty("region")
    public String region;
    @JsonProperty("retail_partner")
    public boolean retailPartner;
    @JsonProperty("validate_phone")
    public boolean validatePhone;

}
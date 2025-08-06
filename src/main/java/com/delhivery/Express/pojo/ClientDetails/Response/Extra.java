package com.delhivery.Express.pojo.ClientDetails.Response;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "pod_phone",
    "sop_type",
    "cq_pin",
    "enable_manifest_failure",
    "brand_name",
    "restrict_mapper",
    "flat_max",
    "mandatory_weight_capture",
    "otp_pkup_verification",
    "enable_fm_pickup_sms_service",
    "flat_min",
    "enable_sms_service",
    "weight_capture_enable",
    "pod_st",
    "on_demand_fail",
    "validate_phone",
    "label_type",
    "e_pod_req",
    "enable_static_waybill",
    "flat_rate",
    "fragile_shipment",
    "plastic_packaging",
    "is_bfsi_branch",
    "pod_city",
    "verified_add",
    "cq_add",
    "depot",
    "disable_cod",
    "capture_non_qc_images",
    "pod_cn",
    "alt_prepaid_ver",
    "restrict_weight_srv",
    "ondc_manifestation",
    "rvp_img_on_qc_failure",
    "pod_cell",
    "exempt_express_delivery",
    "brand_logo",
    "enable_dos",
    "capture_client_otp",
    "pod_pin",
    "enable_srv",
    "oda_config",
    "enable_self_collect_pod",
    "fail_manifest",
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
    "customs_declaration",
    "pod_fix_add",
    "cq_city",
    "inv_restrict",
    "pod_pickup_add",
    "client_category",
    "contactless_del",
    "sunday_dlv",
    "rvp_qc_serv",
    "cq_st",
    "cq_pickup_loc",
    "disable_fod_payment",
    "pur_pick",
    "rto_ageing",
    "disable_document_waybill",
    "region",
    "retail_partner",
    "cq_phone"
})
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Extra {

    @JsonProperty("pod_phone")
    public String podPhone;
    @JsonProperty("sop_type")
    public String sopType;
    @JsonProperty("cq_pin")
    public String cqPin;
    @JsonProperty("enable_manifest_failure")
    public boolean enableManifestFailure;
    @JsonProperty("brand_name")
    public boolean brandName;
    @JsonProperty("restrict_mapper")
    public boolean restrictMapper;
    @JsonProperty("flat_max")
    public String flatMax;
    @JsonProperty("mandatory_weight_capture")
    public boolean mandatoryWeightCapture;
    @JsonProperty("otp_pkup_verification")
    public String otpPkupVerification;
    @JsonProperty("enable_fm_pickup_sms_service")
    public boolean enableFmPickupSmsService;
    @JsonProperty("flat_min")
    public String flatMin;
    @JsonProperty("enable_sms_service")
    public boolean enableSmsService;
    @JsonProperty("weight_capture_enable")
    public boolean weightCaptureEnable;
    @JsonProperty("pod_st")
    public String podSt;
    @JsonProperty("on_demand_fail")
    public boolean onDemandFail;
    @JsonProperty("validate_phone")
    public boolean validatePhone;
    @JsonProperty("label_type")
    public String labelType;
    @JsonProperty("e_pod_req")
    public boolean ePodReq;
    @JsonProperty("enable_static_waybill")
    public boolean enableStaticWaybill;
    @JsonProperty("flat_rate")
    public boolean flatRate;
    @JsonProperty("fragile_shipment")
    public boolean fragileShipment;
    @JsonProperty("plastic_packaging")
    public boolean plasticPackaging;
    @JsonProperty("is_bfsi_branch")
    public boolean isBfsiBranch;
    @JsonProperty("pod_city")
    public String podCity;
    @JsonProperty("verified_add")
    public boolean verifiedAdd;
    @JsonProperty("cq_add")
    public String cqAdd;
    @JsonProperty("depot")
    public String depot;
    @JsonProperty("disable_cod")
    public boolean disableCod;
    @JsonProperty("capture_non_qc_images")
    public boolean captureNonQcImages;
    @JsonProperty("pod_cn")
    public String podCn;
    @JsonProperty("alt_prepaid_ver")
    public String altPrepaidVer;
    @JsonProperty("restrict_weight_srv")
    public boolean restrictWeightSrv;
    @JsonProperty("ondc_manifestation")
    public boolean ondcManifestation;
    @JsonProperty("rvp_img_on_qc_failure")
    public boolean rvpImgOnQcFailure;
    @JsonProperty("pod_cell")
    public boolean podCell;
    @JsonProperty("exempt_express_delivery")
    public boolean exemptExpressDelivery;
    @JsonProperty("brand_logo")
    public boolean brandLogo;
    @JsonProperty("enable_dos")
    public boolean enableDos;
    @JsonProperty("capture_client_otp")
    public boolean captureClientOtp;
    @JsonProperty("pod_pin")
    public String podPin;
    @JsonProperty("enable_srv")
    public boolean enableSrv;
    @JsonProperty("oda_config")
    public String odaConfig;
    @JsonProperty("enable_self_collect_pod")
    public boolean enableSelfCollectPod;
    @JsonProperty("fail_manifest")
    public boolean failManifest;
    @JsonProperty("cq_fix_add")
    public boolean cqFixAdd;
    @JsonProperty("billed_by")
    public String billedBy;
    @JsonProperty("is_so")
    public boolean isSo;
    @JsonProperty("cq_cn")
    public String cqCn;
    @JsonProperty("otp_hard_check")
    public List<String> otpHardCheck;
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
    @JsonProperty("customs_declaration")
    public String customsDeclaration;
    @JsonProperty("pod_fix_add")
    public boolean podFixAdd;
    @JsonProperty("cq_city")
    public String cqCity;
    @JsonProperty("inv_restrict")
    public boolean invRestrict;
    @JsonProperty("pod_pickup_add")
    public boolean podPickupAdd;
    @JsonProperty("client_category")
    public String clientCategory;
    @JsonProperty("contactless_del")
    public String contactlessDel;
    @JsonProperty("sunday_dlv")
    public boolean sundayDlv;
    @JsonProperty("rvp_qc_serv")
    public boolean rvpQcServ;
    @JsonProperty("cq_st")
    public String cqSt;
    @JsonProperty("cq_pickup_loc")
    public boolean cqPickupLoc;
    @JsonProperty("disable_fod_payment")
    public boolean disableFodPayment;
    @JsonProperty("pur_pick")
    public boolean purPick;
    @JsonProperty("rto_ageing")
    public boolean rtoAgeing;
    @JsonProperty("disable_document_waybill")
    public boolean disableDocumentWaybill;
    @JsonProperty("region")
    public String region;
    @JsonProperty("retail_partner")
    public boolean retailPartner;
    @JsonProperty("cq_phone")
    public String cqPhone;

}

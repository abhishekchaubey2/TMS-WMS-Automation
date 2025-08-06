package com.delhivery.Express.pojo.ClientUpdateNew.Response;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.List;

public class Extra {
    @JsonProperty("credit_period")
    public Integer creditPeriod;
    @JsonProperty("pod_phone")
    public String podPhone;
    @JsonProperty("cq_city")
    public String cqCity;
    @JsonProperty("flat_max")
    public String flatMax;
    @JsonProperty("sunday_dlv")
    public Boolean sundayDlv;
    @JsonProperty("sop_type")
    public String sopType;
    @JsonProperty("cq_pin")
    public String cqPin;
    @JsonProperty("enable_manifest_failure")
    public Boolean enableManifestFailure;
    @JsonProperty("lr_limitation")
    public Integer lrLimitation;
    @JsonProperty("brand_name")
    public Boolean brandName;
    @JsonProperty("fail_rvp_manifest")
    public Boolean failRvpManifest;
    @JsonProperty("restrict_mapper")
    public Boolean restrictMapper;
    @JsonProperty("contactless_del")
    public String contactlessDel;
    @JsonProperty("mandatory_weight_capture")
    public Boolean mandatoryWeightCapture;
    @JsonProperty("cq_phone")
    public String cqPhone;
    @JsonProperty("enable_fm_pickup_sms_service")
    public Boolean enableFmPickupSmsService;
    @JsonProperty("pod_pickup_add")
    public Boolean podPickupAdd;
    @JsonProperty("flat_min")
    public String flatMin;
    @JsonProperty("enable_sms_service")
    public Boolean enableSmsService;
    @JsonProperty("weight_capture_enable")
    public Boolean weightCaptureEnable;
    @JsonProperty("pod_st")
    public String podSt;
    @JsonProperty("alt_prepaid_ver")
    public String altPrepaidVer;
    @JsonProperty("label_type")
    public String labelType;
    @JsonProperty("e_pod_req")
    public Boolean ePodReq;
    @JsonProperty("rvp_img_on_qc_failure")
    public Boolean rvpImgOnQcFailure;
    @JsonProperty("rvp_qc_serv")
    public Boolean rvpQcServ;
    @JsonProperty("disable_fod_payment")
    public Boolean disableFodPayment;
    @JsonProperty("enable_static_waybill")
    public Boolean enableStaticWaybill;
    @JsonProperty("flat_rate")
    public Boolean flatRate;
    @JsonProperty("cq_st")
    public String cqSt;
    @JsonProperty("is_bfsi_branch")
    public Boolean isBfsiBranch;
    @JsonProperty("pod_city")
    public String podCity;
    @JsonProperty("enable_srv")
    public Boolean enableSrv;
    @JsonProperty("cq_add")
    public String cqAdd;
    @JsonProperty("depot")
    public String depot;
    @JsonProperty("disable_cod")
    public Boolean disableCod;
    @JsonProperty("cq_pickup_loc")
    public Boolean cqPickupLoc;
    @JsonProperty("pod_stamp")
    public Boolean podStamp;
    @JsonProperty("on_demand_fail")
    public Boolean onDemandFail;
    @JsonProperty("enable_b2b_rvp_sms")
    public Boolean enableB2bRvpSms;
    @JsonProperty("ondc_manifestation")
    public Boolean ondcManifestation;
    @JsonProperty("fragile_shipment")
    public Boolean fragileShipment;
    @JsonProperty("exempt_express_delivery")
    public Boolean exemptExpressDelivery;
    @JsonProperty("brand_logo")
    public Boolean brandLogo;
    @JsonProperty("capture_client_otp")
    public Boolean captureClientOtp;
    @JsonProperty("customs_declaration")
    public String customsDeclaration;
    @JsonProperty("mandate_manifest_doc_upload")
    public Boolean mandateManifestDocUpload;
    @JsonProperty("secure_pickup")
    public Boolean securePickup;
    @JsonProperty("pod_status_inv")
    public Boolean podStatusInv;
    @JsonProperty("oda_config")
    public String odaConfig;
    @JsonProperty("enable_self_collect_pod")
    public Boolean enableSelfCollectPod;
    @JsonProperty("fail_manifest")
    public Boolean failManifest;
    @JsonProperty("pod_pin")
    public String podPin;
    @JsonProperty("cq_fix_add")
    public Boolean cqFixAdd;
    @JsonProperty("rto_ageing")
    public Boolean rtoAgeing;
    @JsonProperty("is_so")
    public Boolean isSo;
    @JsonProperty("cq_cn")
    public String cqCn;
    @JsonProperty("otp_hard_check")
    public List<Object> otpHardCheck;
    @JsonProperty("disable_document_waybill")
    public Boolean disableDocumentWaybill;
    @JsonProperty("pod_add")
    public String podAdd;
    @JsonProperty("email_subscription")
    public Boolean emailSubscription;
    @JsonProperty("alt_cod_ver")
    public String altCodVer;
    @JsonProperty("pur_pick")
    public Boolean purPick;
    @JsonProperty("excl_lockdown")
    public Boolean exclLockdown;
    @JsonProperty("is_pri_enabled")
    public Boolean isPriEnabled;
    @JsonProperty("verified_add")
    public Boolean verifiedAdd;
    @JsonProperty("inv_restrict")
    public Boolean invRestrict;
    @JsonProperty("plastic_packaging")
    public Boolean plasticPackaging;
    @JsonProperty("client_category")
    public String clientCategory;
    @JsonProperty("custom_oda")
    public Boolean customOda;
    @JsonProperty("pod_fix_add")
    public Boolean podFixAdd;
    @JsonProperty("otp_pkup_verification")
    public String otpPkupVerification;
    @JsonProperty("pod_cell")
    public Boolean podCell;
    @JsonProperty("capture_non_qc_images")
    public Boolean captureNonQcImages;
    @JsonProperty("pod_cn")
    public String podCn;
    @JsonProperty("restrict_weight_srv")
    public Boolean restrictWeightSrv;
    @JsonProperty("billed_by")
    public String billedBy;
    @JsonProperty("enable_dos")
    public Boolean enableDos;
    @JsonProperty("region")
    public String region;
    @JsonProperty("retail_partner")
    public Boolean retailPartner;
    @JsonProperty("validate_phone")
    public Boolean validatePhone;
}

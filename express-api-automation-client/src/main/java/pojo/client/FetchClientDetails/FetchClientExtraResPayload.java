package pojo.client.FetchClientDetails;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

@Getter
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class FetchClientExtraResPayload {
    @JsonProperty("pod_phone")
    private String podPhone;

    @JsonProperty("sunday_dlv")
    private Boolean sundayDlv;

    @JsonProperty("pod_city")
    private String podCity;

    @JsonProperty("cq_pin")
    private String cqPin;

    @JsonProperty("enable_manifest_failure")
    private Boolean enableManifestFailure;

    @JsonProperty("brand_name")
    private Boolean brandName;

    @JsonProperty("restrict_mapper")
    private Boolean restrictMapper;

    @JsonProperty("exception_auto_clear")
    private Boolean exceptionAutoClear;

    @JsonProperty("flat_max")
    private String flatMax;

    @JsonProperty("mandatory_weight_capture")
    private Boolean mandatoryWeightCapture;

    @JsonProperty("cq_phone")
    private String cqPhone;

    @JsonProperty("contactless_del")
    private String contactlessDel;

    @JsonProperty("allowed_brands")
    private String allowedBrands;

    @JsonProperty("enable_fm_pickup_sms_service")
    private Boolean enableFmPickupSmsService;

    @JsonProperty("flat_min")
    private String flatMin;

    @JsonProperty("enable_sms_service")
    private Boolean enableSmsService;

    @JsonProperty("weight_capture_enable")
    private Boolean weightCaptureEnable;

    @JsonProperty("rvp_img_on_qc_failure")
    private Boolean rvpImgOnQcFailure;

    @JsonProperty("on_demand_fail")
    private Boolean onDemandFail;

    @JsonProperty("cq_st")
    private String cqSt;

    @JsonProperty("disable_document_waybill")
    private Boolean disableDocumentWaybill;

    @JsonProperty("e_pod_req")
    private Boolean ePodReq;

    @JsonProperty("rvp_qc_serv")
    private Boolean rvpQcServ;

    @JsonProperty("disable_fod_payment")
    private Boolean disableFodPayment;

    @JsonProperty("tat_sensitivity")
    private Long tatSensitivity;

    @JsonProperty("enable_static_waybill")
    private Boolean enableStaticWaybill;

    @JsonProperty("flat_rate")
    private Boolean flatRate;

    @JsonProperty("plastic_packaging")
    private Boolean plasticPackaging;

    @JsonProperty("verified_add")
    private Boolean verifiedAdd;

    @JsonProperty("sop_type")
    private String sopType;

    @JsonProperty("is_bfsi_branch")
    private Boolean isBfsiBranch;

    @JsonProperty("cq_add")
    private String cqAdd;

    @JsonProperty("depot")
    private String depot;

    @JsonProperty("disable_cod")
    private Boolean disableCod;

    @JsonProperty("cq_pickup_loc")
    private Boolean cqPickupLoc;

    @JsonProperty("alt_prepaid_ver")
    private String altPrepaidVer;

    @JsonProperty("ondc_manifestation")
    private Boolean ondcManifestation;

    @JsonProperty("fragile_shipment")
    private Boolean fragileShipment;

    @JsonProperty("exempt_express_delivery")
    private Boolean exemptExpressDelivery;

    @JsonProperty("brand_logo")
    private Boolean brandLogo;

    @JsonProperty("customs_declaration")
    private String customsDeclaration;

    @JsonProperty("pod_pin")
    private String podPin;

    @JsonProperty("enable_srv")
    private Boolean enableSrv;

    @JsonProperty("delivery_verification")
    private Boolean deliveryVerification;

    @JsonProperty("oda_config")
    private String odaConfig;

    @JsonProperty("enable_self_collect_pod")
    private Boolean enableSelfCollectPod;

    @JsonProperty("brandname")
    private String brandname;

    @JsonProperty("pod_st")
    private String podSt;

    @JsonProperty("cq_fix_add")
    private Boolean cqFixAdd;

    @JsonProperty("billed_by")
    private String billedBy;

    @JsonProperty("is_so")
    private Boolean isSo;

    @JsonProperty("cq_cn")
    private String cqCn;

    @JsonProperty("otp_hard_check")
    private List<Object> otpHardCheck;

    @JsonProperty("enable_b2b_rvp_sms")
    private Boolean enableB2bRvpSms;

    @JsonProperty("pod_add")
    private String podAdd;

    @JsonProperty("email_subscription")
    private Boolean emailSubscription;

    @JsonProperty("alt_cod_ver")
    private String altCodVer;

    @JsonProperty("pod_stamp")
    private Boolean podStamp;

    @JsonProperty("excl_lockdown")
    private Boolean exclLockdown;

    @JsonProperty("inv_verif")
    private Boolean invVerif;

    @JsonProperty("is_tat_sensitive")
    private Boolean isTatSensitive;

    @JsonProperty("cq_city")
    private String cqCity;

    @JsonProperty("inv_restrict")
    private Boolean invRestrict;

    @JsonProperty("pod_pickup_add")
    private Boolean podPickupAdd;

    @JsonProperty("client_category")
    private String clientCategory;

    @JsonProperty("label_type")
    private String labelType;

    @JsonProperty("pod_fix_add")
    private Boolean podFixAdd;

    @JsonProperty("otp_pkup_verification")
    private String otpPkupVerification;

    @JsonProperty("pod_cell")
    private Boolean podCell;

    @JsonProperty("capture_non_qc_images")
    private Boolean captureNonQcImages;

    @JsonProperty("return_rec_id_serv_template")
    private String returnRecIdServTemplate;

    @JsonProperty("pod_cn")
    private String podCn;

    @JsonProperty("restrict_weight_srv")
    private Boolean restrictWeightSrv;

    @JsonProperty("fail_manifest")
    private Boolean failManifest;

    @JsonProperty("pur_pick")
    private Boolean purPick;

    @JsonProperty("rto_ageing")
    private Boolean rtoAgeing;

    @JsonProperty("enable_dos")
    private Boolean enableDos;

    @JsonProperty("region")
    private String region;

    @JsonProperty("retail_partner")
    private Boolean retailPartner;

    @JsonProperty("validate_phone")
    private Boolean validatePhone;
}
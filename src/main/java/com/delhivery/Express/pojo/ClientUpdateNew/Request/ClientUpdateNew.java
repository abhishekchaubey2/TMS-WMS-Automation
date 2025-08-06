package com.delhivery.Express.pojo.ClientUpdateNew.Request;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter

public class ClientUpdateNew {

    @JsonProperty("client_name")
    public Object clientName;
    @JsonProperty("client_last_name")
    public Object clientLastName;
    @JsonProperty("client_first_name")
    public Object clientFirstName;
    @JsonProperty("salesforce_id")
    public Object salesforceId;
    @JsonProperty("billing_mode")
    public Object billingMode;
    @JsonProperty("xray_amount_limit")
    public Object xrayAmountLimit;
    @JsonProperty("client_type")
    public Object clientType;
    @JsonProperty("billing_method")
    public Object billingMethod;
    @JsonProperty("wallet_provider")
    public Object walletProvider;
    @JsonProperty("phone")
    public Object phone;
    @JsonProperty("wallet_notification_mobile")
    public Object walletNotificationMobile;
    @JsonProperty("wallet_notification_email")
    public Object walletNotificationEmail;
    @JsonProperty("payment_link")
    public Object paymentLink;
    @JsonProperty("services")
    public Services services;
    @JsonProperty("qr_payment")
    public Object qrPayment;
    @JsonProperty("is_prepaid_service")
    public Object isPrepaidService;
    @JsonProperty("product_type")
    public Object productType;
    @JsonProperty("divisor")
    public Object divisor;
    @JsonProperty("weight_status")
    public Object weightStatus;
    @JsonProperty("client_uuid")
    public Object clientUuid;
    @JsonProperty("miscellaneous_data")
    public MiscellaneousData miscellaneousData;
    @JsonProperty("ho_state")
    public Object hoState;
    @JsonProperty("gst_states")
    public GstStates gstStates;
    @JsonProperty("billing_client_config")
    public Object billingClientConfig;
    @JsonProperty("movement_config")
    public Object movementConfig;
    @JsonProperty("billing_dlv_config")
    public Object billingDlvConfig;
    @JsonProperty("line_of_business")
    public Object lineOfBusiness;
    @JsonProperty("gst_rate")
    public Object gstRate;
    @JsonProperty("gst_billing_type")
    public Object gstBillingType;
    //pojo for following checks
    /* "fail_manifest": false
    "enable_manifest_failure": true
    "enable_sms_service": false
    "weight_capture_enable": false
     "on_demand_fail": false,
     "fragile_shipment": false,
      "secure_pickup": false,
       "verified_add": false
        "capture_client_otp": false,
        "fail_manifest": false
        "lock": false
         "is_prepaid": true,
         "mps_service": false,
         "enable_ivr": false,
         "qr_payment": false,

     */
    @JsonProperty("fail_manifest")
    public Object failManifest;
    @JsonProperty("enable_manifest_failure")
    public Object enableManifestFailure;
    @JsonProperty("enable_sms_service")
    public Object enableSmsService;
    @JsonProperty("weight_capture_enable")
    public Object weightCaptureEnable;
    @JsonProperty("on_demand_fail")
    public Object onDemandFail;
    @JsonProperty("fragile_shipment")
    public Object fragileShipment;
    @JsonProperty("secure_pickup")
    public Object securePickup;
    @JsonProperty("verified_add")
    public Object verifiedAdd;
    @JsonProperty("capture_client_otp")
    public Object captureClientOtp;
    @JsonProperty("lock")
    public Object lock;
    @JsonProperty("is_prepaid")
    public Object isPrepaid;
    @JsonProperty("mps_service")
    public Object mpsService;
    @JsonProperty("enable_ivr")
    public Object enableIvr;





}

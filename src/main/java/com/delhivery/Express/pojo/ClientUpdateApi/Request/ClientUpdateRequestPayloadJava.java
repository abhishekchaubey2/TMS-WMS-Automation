package com.delhivery.Express.pojo.ClientUpdateApi.Request;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "ndr_report_recipients",
    "report_recipients",
    "rto_email_recipients",
    "dto_email_recipients",
    "rem_email",
    "upload_recipients",
    "vat_number",
    "cst_number",
    "eco_code",
    "frs_code",
    "return_add",
    "address",
    "reg_name",
    "push_url",
    "product_type",
    "is_prepaid",
    "wallet_provider",
    "wallet_notification_mobile",
    "wallet_notification_email",
    "mps_service",
    "send_mail_pickup",
    "send_sms_prepaid",
    "send_sms_cash",
    "send_sms_cod",
    "send_sms_reverse",
    "send_sms_ndr",
    "auto_pickup",
    "allow_no_data",
    "billing_mode",
    "services",
    "postal_cat",
    "phone",
    "ho_state",
    "gst_states"
})


@Builder
@Getter
@Setter
public class ClientUpdateRequestPayloadJava {

    @JsonProperty("ndr_report_recipients")
    public String ndrReportRecipients;
    @JsonProperty("report_recipients")
    public String reportRecipients;
    @JsonProperty("rto_email_recipients")
    public String rtoEmailRecipients;
    @JsonProperty("dto_email_recipients")
    public String dtoEmailRecipients;
    @JsonProperty("rem_email")
    public String remEmail;
    @JsonProperty("upload_recipients")
    public String uploadRecipients;
    @JsonProperty("vat_number")
    public long vatNumber;
    @JsonProperty("cst_number")
    public long cstNumber;
    @JsonProperty("eco_code")
    public long ecoCode;
    @JsonProperty("frs_code")
    public long frsCode;
    @JsonProperty("return_add")
    public String returnAdd;
    @JsonProperty("address")
    public String address;
    @JsonProperty("reg_name")
    public String regName;
    @JsonProperty("push_url")
    public String pushUrl;
    @JsonProperty("product_type")
    public String productType;
    @JsonProperty("is_prepaid")
    public boolean isPrepaid;
    @JsonProperty("wallet_provider")
    public String walletProvider;
    @JsonProperty("wallet_notification_mobile")
    public String walletNotificationMobile;
    @JsonProperty("wallet_notification_email")
    public String walletNotificationEmail;
    @JsonProperty("mps_service")
    public boolean mpsService;
    @JsonProperty("send_mail_pickup")
    public boolean sendMailPickup;
    @JsonProperty("send_sms_prepaid")
    public boolean sendSmsPrepaid;
    @JsonProperty("send_sms_cash")
    public boolean sendSmsCash;
    @JsonProperty("send_sms_cod")
    public boolean sendSmsCod;
    @JsonProperty("send_sms_reverse")
    public boolean sendSmsReverse;
    @JsonProperty("send_sms_ndr")
    public boolean sendSmsNdr;
    @JsonProperty("auto_pickup")
    public boolean autoPickup;
    @JsonProperty("allow_no_data")
    public boolean allowNoData;
    @JsonProperty("billing_mode")
    public String billingMode;
    @JsonProperty("services")
    public Services services;
    @JsonProperty("postal_cat")
    public String postalCat;
    @JsonProperty("phone")
    public String phone;
    @JsonProperty("ho_state")
    public String hoState;
    @JsonProperty("gst_states")
    public GstStatesReq gstStates;
    

}

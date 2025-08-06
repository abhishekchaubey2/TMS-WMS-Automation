package com.delhivery.Express.pojo.ClientUpdateApi.Response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "extra",
    "reg_name",
    "postal_cat",
    "rem_email",
    "allow_no_data",
    "vat_number",
    "cst_number",
    "dto_email_recipients",
    "send_sms_cod",
    "push_url",
    "report_recipients",
    "billing_mode",
    "mps_service",
    "eco_code",
    "send_mail_pickup",
    "send_sms_prepaid",
    "ho_state",
    "rto_email_recipients",
    "phone",
    "address",
    "frs_code",
    "is_prepaid",
    "return_add",
    "upload_recipients",
    "product_type",
    "ndr_report_recipients",
    "auto_pickup",
    "send_sms_reverse",
    "gst_states",
    "send_sms_cash",
    "send_sms_ndr"
})

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UpdatedData {

    @JsonProperty("extra")
    public Extra extra;
    @JsonProperty("reg_name")
    public String regName;
    @JsonProperty("postal_cat")
    public String postalCat;
    @JsonProperty("rem_email")
    public String remEmail;
    @JsonProperty("allow_no_data")
    public boolean allowNoData;
    @JsonProperty("vat_number")
    public long vatNumber;
    @JsonProperty("cst_number")
    public long cstNumber;
    @JsonProperty("dto_email_recipients")
    public String dtoEmailRecipients;
    @JsonProperty("send_sms_cod")
    public boolean sendSmsCod;
    @JsonProperty("push_url")
    public String pushUrl;
    @JsonProperty("report_recipients")
    public String reportRecipients;
    @JsonProperty("billing_mode")
    public String billingMode;
    @JsonProperty("mps_service")
    public boolean mpsService;
    @JsonProperty("eco_code")
    public long ecoCode;
    @JsonProperty("send_mail_pickup")
    public boolean sendMailPickup;
    @JsonProperty("send_sms_prepaid")
    public boolean sendSmsPrepaid;
    @JsonProperty("ho_state")
    public String hoState;
    @JsonProperty("rto_email_recipients")
    public String rtoEmailRecipients;
    @JsonProperty("phone")
    public long phone;
    @JsonProperty("address")
    public String address;
    @JsonProperty("frs_code")
    public long frsCode;
    @JsonProperty("is_prepaid")
    public boolean isPrepaid;
    @JsonProperty("return_add")
    public String returnAdd;
    @JsonProperty("upload_recipients")
    public String uploadRecipients;
    @JsonProperty("product_type")
    public String productType;
    @JsonProperty("ndr_report_recipients")
    public String ndrReportRecipients;
    @JsonProperty("auto_pickup")
    public boolean autoPickup;
    @JsonProperty("send_sms_reverse")
    public boolean sendSmsReverse;
    @JsonProperty("gst_states")
    public GstStates gstStates;
    @JsonProperty("send_sms_cash")
    public boolean sendSmsCash;
    @JsonProperty("send_sms_ndr")
    public boolean sendSmsNdr;

}

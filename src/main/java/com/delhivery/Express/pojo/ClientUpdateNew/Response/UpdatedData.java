package com.delhivery.Express.pojo.ClientUpdateNew.Response;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

public class UpdatedData {
    @JsonProperty("weight_status")
    public String weightStatus;
    @JsonProperty("billing_mode")
    public String billingMode;
    @JsonProperty("divisor")
    public Integer divisor;
    @JsonProperty("gst_rate")
    public Integer gstRate;
    @JsonProperty("gst_billing_client_config")
    public String gstBillingClientConfig;
    @JsonProperty("ho_state")
    public String hoState;
    @JsonProperty("extra")
    public Extra extra;
    @JsonProperty("wallet_not_email")
    public String walletNotEmail;
    @JsonProperty("phone")
    public String phone;
    @JsonProperty("parent_account_id")
    public String parentAccountId;
    @JsonProperty("report_recipients")
    public String reportRecipients;
    @JsonProperty("gst_states")
    public GstStates gstStates;
    @JsonProperty("wallet_not_mobile")
    public String walletNotMobile;
    @JsonProperty("gst_movement_config")
    public String gstMovementConfig;
    @JsonProperty("wallet_provider")
    public String walletProvider;
    @JsonProperty("od_tat_temp")
    public String odTatTemp;
    @JsonProperty("gst_billing_type")
    public String gstBillingType;
    @JsonProperty("mot")
    public Mot mot;
    @JsonProperty("parent_account_name")
    public String parentAccountName;
    @JsonProperty("xray_amount_limit")
    public Integer xrayAmountLimit;

}

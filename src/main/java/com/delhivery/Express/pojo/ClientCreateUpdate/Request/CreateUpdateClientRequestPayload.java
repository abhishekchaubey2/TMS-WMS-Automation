package com.delhivery.Express.pojo.ClientCreateUpdate.Request;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "billing_method",
    "salesforce_id",
    "phone",
    "client_name",
    "report_recipients",
    "billing_mode",
    "client_type",
    "wallet_provider",
    "wallet_notification_email",
    "client_first_name",
    "client_last_name",
    "password",
    "wallet_notification_mobile",
    "xray_amount_limit",
    "is_prepaid_service",
    "is_mps",
    "is_no_data"
})
@Builder
@Getter
@Setter
public class CreateUpdateClientRequestPayload{

    @JsonProperty("billing_method")
    public String billingMethod;
    @JsonProperty("salesforce_id")
    public String salesforceId;
    @JsonProperty("phone")
    public String phone;
    @JsonProperty("client_name")
    public String clientName;
    @JsonProperty("report_recipients")
    public String reportRecipients;
    @JsonProperty("billing_mode")
    public String billingMode;
    @JsonProperty("client_type")
    public String clientType;
    @JsonProperty("wallet_provider")
    public String walletProvider;
    @JsonProperty("wallet_notification_email")
    public String walletNotificationEmail;
    @JsonProperty("client_first_name")
    public String clientFirstName;
    @JsonProperty("client_last_name")
    public String clientLastName;
    @JsonProperty("password")
    public String password;
    @JsonProperty("wallet_notification_mobile")
    public String walletNotificationMobile;
    @JsonProperty("xray_amount_limit")
    public long xrayAmountLimit;
    @JsonProperty("is_prepaid_service")
    public boolean isPrepaidService;
    @JsonProperty("is_mps")
    public boolean isMps;
    @JsonProperty("is_no_data")
    public boolean isNoData;
    @JsonProperty("product_type")
    private String productType;

}

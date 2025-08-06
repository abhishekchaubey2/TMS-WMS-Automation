package pojo.client.ClientCreateUpdate.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Builder
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ClientCreateUpdateReqPayload {
    @JsonProperty("billing_method")
    private String billingMethod;

    @JsonProperty("salesforce_id")
    private String salesforceId;

    @JsonProperty("phone")
    private String phone;

    @JsonProperty("client_name")
    private String clientName;

    @JsonProperty("report_recipients")
    private String reportRecipients;

    @JsonProperty("billing_mode")
    private String billingMode;

    @JsonProperty("client_type")
    private String clientType;

    @JsonProperty("wallet_provider")
    private String walletProvider;

    @JsonProperty("wallet_notification_email")
    private String walletNotificationEmail;

    @JsonProperty("client_first_name")
    private String clientFirstName;

    @JsonProperty("client_last_name")
    private String clientLastName;

    @JsonProperty("password")
    private String password;

    @JsonProperty("wallet_notification_mobile")
    private String walletNotificationMobile;

    @JsonProperty("xray_amount_limit")
    private Long xrayAmountLimit;

    @JsonProperty("is_prepaid_service")
    private Boolean isPrepaidService;

    @JsonProperty("is_mps")
    private Boolean isMps;

    @JsonProperty("is_no_data")
    private Boolean isNoData;

    @JsonProperty("product_type")
    private String productType;
}

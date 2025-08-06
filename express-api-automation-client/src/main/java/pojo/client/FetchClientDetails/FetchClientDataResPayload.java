package pojo.client.FetchClientDetails;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

@Getter
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class FetchClientDataResPayload {
    @JsonProperty("enable_self_collect_otp")
    private boolean enableSelfCollectOtp;

    @JsonProperty("min_rvp_img_count")
    private long minRvpImgCount;

    @JsonProperty("lock_invalid_ewbn")
    private boolean lockInvalidEwbn;

    @JsonProperty("extra")
    private FetchClientExtraResPayload extra;

    @JsonProperty("bird_migration_date")
    private String birdMigrationDate;

    @JsonProperty("cl_call_masking")
    private boolean clCallMasking;

    @JsonProperty("active")
    private boolean active;

    @JsonProperty("allow_no_data")
    private boolean allowNoData;

    @JsonProperty("registered_email")
    private String registeredEmail;

    @JsonProperty("user_uuid_key")
    private String userUuidKey;

    @JsonProperty("risk_appetite_bucket")
    private String riskAppetiteBucket;

    @JsonProperty("rvp_img_count")
    private long rvpImgCount;

    @JsonProperty("wallet_credit_limit")
    private String walletCreditLimit;

    @JsonProperty("waybill_prefix")
    private long waybillPrefix;

    @JsonProperty("accepted_ids")
    private List<FetchClientDataAcceptedIdResPayload> acceptedIds;

    @JsonProperty("is_prepaid")
    private boolean isPrepaid;

    @JsonProperty("registered_name")
    private String registeredName;

    @JsonProperty("payment_link")
    private boolean paymentLink;

    @JsonProperty("b2b_serviceability_type")
    private Object b2bServiceabilityType;

    @JsonProperty("client_name")
    private String clientName;

    @JsonProperty("registered_phone")
    private String registeredPhone;

    @JsonProperty("revenue_mapping")
    private String revenueMapping;

    @JsonProperty("billing_mode")
    private Object billingMode;

    @JsonProperty("gen_ewbn_rto")
    private boolean genEwbnRto;

    @JsonProperty("gen_ewbn_fwd")
    private boolean genEwbnFwd;

    @JsonProperty("wallet_id")
    private Object walletId;

    @JsonProperty("use_bird_service")
    private boolean useBirdService;

    @JsonProperty("rvp_img_item")
    private boolean rvpImgItem;

    @JsonProperty("mps_service")
    private boolean mpsService;

    @JsonProperty("persons_allowed")
    private List<String> personsAllowed;

    @JsonProperty("expires")
    private String expires;

    @JsonProperty("mot")
    private FetchClientMotResPayload mot;

    @JsonProperty("alias")
    private String alias;

    @JsonProperty("rvp_img")
    private boolean rvpImg;

    @JsonProperty("qr_payment")
    private boolean qrPayment;

    @JsonProperty("wallet_provider")
    private Object walletProvider;

    @JsonProperty("pdt_config")
    private FetchClientDetailsPdtConfigResPayload pdtConfig;

    @JsonProperty("services")
    private List<List<String>> services;

    @JsonProperty("product_type")
    private Object productType;

    @JsonProperty("freight_collection")
    private boolean freightCollection;

    @JsonProperty("enable_ivr")
    private boolean enableIvr;

    @JsonProperty("payment")
    private List<List<String>> payment;

    @JsonProperty("client_uuid_key")
    private String clientUuidKey;

    @JsonProperty("billing_method")
    private String billingMethod;

    @JsonProperty("allow_large_mps_lot")
    private boolean allowLargeMpsLot;

    @JsonProperty("last_password_changed")
    private Object lastPasswordChanged;

    @JsonProperty("divisor")
    private float divisor;

    @JsonProperty("auto_pickup")
    private boolean autoPickup;

    @JsonProperty("ewbn_vh_update")
    private boolean ewbnVhUpdate;

    @JsonProperty("client_type")
    private String clientType;

    @JsonProperty("billing_on")
    private String billingOn;

    @JsonProperty("registered_address")
    private String registeredAddress;

    @JsonProperty("nodes_tenant")
    private String nodesTenant;
}
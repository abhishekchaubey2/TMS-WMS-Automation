package com.delhivery.core.api;

public class Routes {
    /*** Auth ***/
    public static final String AUTH = "/auth/user/authenticate/";

    /**
     * Module
     **/

    public static final String CONTAINER_CREATE = "/fulfillment-inventory-management/container/create";
    public static final String REQUEST_TRACKER = "/request-tracker/logs";
    public static final String UPDATE_CONTAINER = "/pick/picklist/update/container/item";

    /**
     * UMS APIs
     **/
    public static final String FETCH_USER_JWT_TOKEN = "/login/";

    /**
     * HQ APIs
     **/
    public static final String FETCH_PACKAGE_REST_INFO = "/api/rest/info";
    public static final String FETCH_PACKAGE_INFO = "/api/p/info/";
    public static final String CREATE_JSON_API_MANIFEST_SHIPMENT = "/api/cmu/create.json";
    public static final String FM_OMS_SHIPMENT = "/api/oms/fm/scan/";
    public static final String GI_SHIPMENT = "/api/mob-loc/mi/";
    public static final String BAG_V3_SHIPMENT = "/api/bag/create/v3";
    public static final String BAG_ADD_TO_TRIP = "/api/lh/trip/add/";
    public static final String BAG_REMOVE_FROM_TRIP = "/api/lh/trip/remove/";
    public static final String PKG_REMOVE_FROM_BAG = "/api/bag/remove/package";
    public static final String TRIP_INCOMING = "/api/lh/trip/inscan/";
    public static final String BAG_INCOMING = "/api/bag/inscan/";
    public static final String MARK_DISPATCH = "/api/p/mark_dispatched/v1/";
    public static final String LM_UPDATE_HQ_SHIPMENT = "/api/p/lm/package/sync/v1/";
    public static final String UPDATE_QC_WT = "/api/p/update_qc_weight";
    public static final String EDIT_API = "/api/p/edit";
    public static final String APPLY_NSL_API = "/nsl/api/apply/nsl";
    public static final String FETCH_WAYBILL = "/api/wbn/bulk.json";
    public static final String ASYNC_OMS_CREATE_NODATA_SHIPMENT = "/api/oms/create/nodata/async/";
    public static final String OMS_FETCH_NODATA_SHIPMENT = "/api/oms/fetch/nodata/uplId/";
    public static final String CMU_V2_MANIFEST_SHIPMENT = "/api/cmu/v2/manifest";
    public static final String FETCH_CLIENT_DETAILS = "/api/backend/fetch/client";
    public static final String DISPATCH_FREEZE = "/api/mob-loc/dispatch/return/freeze/";
    public static final String MARK_PENDING = "/api/mob-loc/dispatch/return/eod/mark-pending/";
    public static final String MARK_DELIVERED = "/api/mob-loc/dispatch/return/eod/mark-delivered/";
    public static final String FETCH_BAGMATRIX = "/api/bag/fetch-bagmatrix/";
    public static final String BULK_NDR = "/api/p/bulk/ndr/";
    public static final String CMU_PUSH_API_MANIFEST = "/cmu/push/json/";
    public static final String BAG_V4_SHIPMENT = "/api/bag/create/v4";
    public static final String KINKO_INVOICE_CHARGES = "/kinko/api/invoice/charges/json/";
    public static final String CLIENT_UPDATE = "/api/backend/client/update/35587";
    public static final String FETCH_CLIENT_WBN = "/api/wbn/fetch.json";
    public static final String REMOVE_EWBN = "/api/rest/ewbn_rem/";
    public static final String PACKAGE_STATUS = "/api/status/packages/json/";
    public static final String ACTION_API = "/api/p/actions";
    public static final String SELF_COLLECT_V1 = "/api/p/update/self_collect/v1";
    public static final String BAG_CUSTODDY_SCAN = "/api/bag/custody/scan/v1";
    public static final String BAG_V2_SHIPMENT = "/api/bag/create/v2";
    public static final String AUTOBAG = "/api/p/autobag";
    public static final String FM_API_INCOMING = "/fm/api/incoming/";
    public static final String CUSTOMEDIT = "/api/p/customedit/";
    public static final String CLIENT_CREATE = "/api/p/customedit/";
    public static final String CLIENT_CREATE_UPDATE = "/api/backend/client/create-update/";
    public static final String OD_TAT = "/api/dc/fetch/od";
    public static final String PKG_COUNT = "/api/p/count/";
    public static final String FETCH_DC = "/api/dc/fetch/dc/list";
    public static final String FETCH_CITY = "/c/api/cities/";
    public static final String PINCODE_INFO = "/c/api/pin-codes/json";
    public static final String PACKING_SLIP = "/api/p/packing_slip/";
    public static final String PACKAGE_CANCEL = "/api/p/cancel/";
    public static final String FETCH_DC_CENTER = "/api/dc/fetch/center/IND147301AAA";
    public static final String PACKAGE_UPDATE = "/api/p/update/";
    public static final String LOCATION_ASSOCIATE = "/api/lh/location/associate/";
    public static final String KINKO_FETCH = "/api/kinko/fetch/";
    public static final String CLIENT_DETAILS = "/api/backend/clients/";
    public static final String STATE_INFO = "/c/api/states/";
    public static final String BAG_INFO = "/api/bag/baginfo";
    public static final String NEW_FM = "/fm/request/new/";
    public static final String LOCATION_DISSOCIATE = "/api/lh/location/dissociate/";
    public static final String FETCH_PACKAGE_DETAILS = "/api/p/package/detail";
    public static final String BAG_CALC = "/api/bag/calc/dimensions/";
    public static final String UPDATED_BAGMATRIX = "/api/bag/get-updated-bagmatrix/";
    public static final String EDIT_PHONE = "/api/p/update";
    public static final String PACKAGE_STATUS_UPDATE = "/api/p/update/status/delay/v1";
    public static final String CUSTODY_CONNECTION = "/api/lh/connections";
    public static final String UPDATE_FINAL_WT = "/api/p/update_qc_final_weight/";
    public static final String MEESHO_MANIFEST_SHIPMENT = "/api/cmu/create/v2.json";
    public static final String FETCH_MANIFESTATION_UPL_DATA = "/api/cmu/get_upl_detail/uplId";
    public static final String SEARCH_PACKAGE = "/api/p/search";
    public static final String FETCH_MTS_INFO = "/api/rest/mts/info";
    public static final String Fetch_Serviceability = "/api/dc/fetch/serviceability/pincode";
    public static final String LH_Connections = "/api/lh/connections";
    public static final String Generate_qr_code = "/api/p/qrcode/generate/";
    public static final String FETCH_CLIENTWAREHOUSE_STATUS = "/api/backend/clientwarehouse/status/";
    public static final String EDIT_CLIENTWAREHOUSE = "/api/backend/clientwarehouse/edit/";
    public static final String CREATE_CLIENTWAREHOUSE = "/api/backend/clientwarehouse/create/";
    public static final String REST_PHONELOG = "/api/rest/phone/log/";
    public static final String UPDATE_AGGRIGATE_wt = "/v1/package/update/aggweight";
    public static final String UPDATE_Fetch_List = "/api/p/fetch/list/";
    public static final String Fetch_Station_List = "/api/dc/station/list/";
    public static final String INSTA_BAGGING = "/api/bag/create/";
    public static final String FETCH_CLIENT_UUID_DETAILS = "api/backend/fetch/Client/uuid/";
    public static final String CENTER_UPDATE = "/api/p/bulk/center_update";
    public static final String CLIENT_FETCH_INTERNAL = "/internal/api/fetch/client/detail/";
    public static final String CLIENT_DETAILS_BY_NAME = "/api/backend/fetch/client.json";
    public static final String APPLY_NSL_GENERIC = "/nsl/api/apply/nsl/generic/";
    public static final String APPLY_Nsl = "/api/p/apply_nsl/";
    public static final String API_REST_E_WAYBILL = "/api/rest/ewaybill/";
    public static final String INTERNAL_API_E_WAYBILL = "/internal/api/ewaybill/update/";
    public static final String QC_fetch = "/dev/rvp_qc/answers/get";
    public static final String UNSET_RETURN_DISPATCH_ID = "/api/mob-loc/dispatch/return/eod/unset-ddid/";
    public static final String UPDATE_TRANSACTION_ID_BULK = "/api/p/bulk/packages/update_trans_id";
    public static final String UPDATE_TRANSACTION_ID_DEX = "/api/async/upd_txn";
    public static final String FETCH_UPL_RESPONSE = "/api/cmu/get_bulk_upl/";
    public static final String P_UPDATE_STATUS = "/p/update-status/";
    public static final String SORTER_ADMIN_PUSH_WAYBILL_DATA_UI = "/sorter/admin/push_waybill_data_ui/";
    public static final String API_LM_DASHBOARD_MISSING_SHIPMENTS = "/api/lm-dashboard/missing-shipments";
    public static final String API_REST_P_COUNTS = "/api/rest/p/counts/";
    public static final String API_P_MPS = "/api/p/mps/";
    public static final String API_BAG_REBAG = "/api/bag/rebag";
    public static final String SMS_FETCH = "/v1/fetch/notification/";
    public static final String FETCH_SCORE = "/api/bag/fetch/scores";
    public static final String BAG_STATUS_UPDATE = "/api/bag/bulk/update/status/";
    public static final String NEW_MANIFEST_SERVICE = "/api/cmu/create.json";
    public static final String FETCH_EWBN_COLLECTION = "/api/p/get/ewbndata";
    public static final String FETCH_EWBN = "/dev/ewaybill/get_ewaybill";
    public static final String CREATE_EWBN = "/ewaybill/create";

    /**
     * TRACKING APIs
     **/
    public static final String TRACKING_SERVICE = "/v1/package/track";
    public static final String HQ_TRACKING = "/api/v1/packages/json/";


    /**
     * CMS APIs
     **/
    public static final String FETCH_B2C_LISTING = "/loss_claims/b2c";
    public static final String FETCH_B2B_LISTING = "/loss_claims/b2b";
    public static final String FETCH_UCP_LOSS_CLAIMS = "/loss_claims/ucp";
    public static final String CREATE_LOSS_CLAIM = "/loss_claims";
    public static final String FETCH_CLAIM_ID_DATA = "/loss_claims/";
    public static final String CREATE_CLAIM_UPLOAD_FILE = "/loss_claims/upload/create";
    public static final String CLAIM_HISTORY = "/history";
    public static final String FETCH_B2B_VIEW_DATA = "/loss_claims/b2b_view/";
    public static final String FETCH_B2B_CLAIM_REPORT = "/reports/b2b";
    public static final String FETCH_B2C_CLAIM_REPORT = "/reports/b2c";
    public static final String UPDATE_CLAIM_SETTLEMENT_AMOUNT = "/loss_claims/";
    public static final String UPDATE_CLAIM_SETTLEMENT_AMOUNT_UPLOAD_FILE = "/requests/update/1/settle";
    public static final String PACKAGE_UPDATE_2 = "/api/p/update";

}

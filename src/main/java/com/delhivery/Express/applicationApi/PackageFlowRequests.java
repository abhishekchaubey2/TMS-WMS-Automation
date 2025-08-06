package com.delhivery.Express.applicationApi;

import com.delhivery.Express.pojo.ewaybill.APIRestEWayBill.request.APIRestEWayBillRequestPayload;
import com.delhivery.core.api.RestResource;
import com.delhivery.core.api.Routes;
import com.fasterxml.jackson.core.JsonProcessingException;
import io.restassured.response.Response;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import static com.delhivery.core.api.Routes.*;
import static com.delhivery.core.api.Routes.CREATE_EWBN;

public class PackageFlowRequests {
    // fixing rest_info_issue
    public static Response getPackageDetails(String paramsKey, String paramsValue) {
        return RestResource.get(FETCH_PACKAGE_INFO, paramsKey, paramsValue);
    }

    public static Response getPackageDetails(String paramsKey, String paramsValue, HashMap<String, String> data) {
        return RestResource.get(FETCH_PACKAGE_INFO, paramsValue, data);
    }

    public static Response getBagDetails(String paramsKey, String paramsValue) {
        return RestResource.get(FETCH_PACKAGE_REST_INFO, paramsKey, paramsValue);
    }

    public static Response trackingDetails(Map<String, String> data) {

        return RestResource.getTrack(TRACKING_SERVICE, data);
    }

    public static Response hqTrackingDetails(Map<String, String> data) {

        return RestResource.getHqTrack(HQ_TRACKING, data);
    }

    public static Response getPackageDetails(String enviorment, String paramsKey, String paramsValue) {
        return RestResource.get(FETCH_PACKAGE_REST_INFO, enviorment, paramsKey, paramsValue);

        // return RestResource.get(FETCH_PACKAGE_INFO, paramsValue, data);
    }

    public static Response getPackageDetails(String enviorment, String paramsKey, String paramsValue, String Client) {
        return RestResource.Get(FETCH_PACKAGE_INFO, enviorment, paramsValue, Client);

    }

    public static Response getPackageTrackingDetails(String enviorment, Map<String, String> data) {
        return RestResource.getdiffenv(TRACKING_SERVICE, enviorment, data);
    }

    public static Response getPackageHqTrackingDetails(String enviorment, Map<String, String> data) {
        return RestResource.getdiffenv(HQ_TRACKING, enviorment, data);
    }

    public static Response cmuManifestShipment(Object reqPayload, HashMap<String, String> data) {
        return RestResource.post(CREATE_JSON_API_MANIFEST_SHIPMENT, reqPayload, data);
    }

    public static Response cmuManifestShipment(Object reqPayload, String clientName) {
        return RestResource.post(CREATE_JSON_API_MANIFEST_SHIPMENT, reqPayload, clientName);
    }

    public static Response cmuManifestShipmentDiffEnv(String enviorment, Object reqPayload, String clientName) {
        return RestResource.post(CREATE_JSON_API_MANIFEST_SHIPMENT, enviorment, reqPayload, clientName);
    }

    public static Response fmOmsShipment(Object reqPayload, HashMap<String, String> data) {
        return RestResource.post(FM_OMS_SHIPMENT, reqPayload, data);
    }

    public static Response fmOmsShipment(String enviorment, Object reqPayload, String Client) {
        return RestResource.post(FM_OMS_SHIPMENT, enviorment, reqPayload, Client);
    }

    public static Response giShipment(Object reqPayload, HashMap<String, String> data) {
        return RestResource.post(GI_SHIPMENT, reqPayload, data);
    }

    public static Response giShipment(String enviorment, Object reqPayload, String Client) {
        return RestResource.post(GI_SHIPMENT, enviorment, reqPayload, Client);
    }

    public static Response bagV3Shipment(Object reqPayload) {
        return RestResource.post(BAG_V3_SHIPMENT, reqPayload);
    }

    public static Response bagV4Shipment(Object reqPayload, HashMap<String, String> data) {
        return RestResource.post(BAG_V4_SHIPMENT, reqPayload, data);
    }

    public static Response bagV4Shipment(Object reqPayload) {
        return RestResource.post(BAG_V4_SHIPMENT, reqPayload);
    }

    public static Response bagAddToTrip(Object reqPayload) {
        return RestResource.put(BAG_ADD_TO_TRIP, reqPayload);
    }

    public static Response bagRemoveFromTrip(Object reqPayload) {
        return RestResource.put(BAG_REMOVE_FROM_TRIP, reqPayload);
    }

    public static Response pkgRemoveFromBag(Object reqPayload, HashMap<String, String> data) {
        return RestResource.post(PKG_REMOVE_FROM_BAG, reqPayload);
    }

    public static Response tripIncoming(Object reqPayload) {
        return RestResource.put(TRIP_INCOMING, reqPayload);
    }

    public static Response bagIncoming(Object reqPayload) {
        return RestResource.put(BAG_INCOMING, reqPayload);
    }

    public static Response markShipmentDispatch(Object reqPayload, HashMap<String, String> data) {
        return RestResource.put(MARK_DISPATCH, reqPayload, data);
    }

    public static Response lmUpdateHqShipment(Object reqPayload, HashMap<String, String> data) {
        return RestResource.post(LM_UPDATE_HQ_SHIPMENT, reqPayload, data);
    }

    public static Response unsetShipmentDispatchId(Object reqPayload, HashMap<String, String> data) {
        return RestResource.post(LM_UPDATE_HQ_SHIPMENT, reqPayload, data);
    }

    public static Response updateQcWt(Object reqPayload, HashMap<String, String> data) {
        return RestResource.post(UPDATE_QC_WT, reqPayload, data);
    }

    public static Response updateQcWt(String enviorment, Object reqPayload, String Client) {
        return RestResource.post(UPDATE_QC_WT, enviorment, reqPayload, Client);
    }

    public static Response EditApi(Object reqPayload, HashMap<String, String> data) {
        return RestResource.post(EDIT_API, reqPayload, data);
    }

    public static Response EditApi(String enviorment, Object reqPayload) {
        return RestResource.post(EDIT_API, enviorment, reqPayload);
    }

    public static Response ApplyNslApi(Object reqPayload, HashMap<String, String> data) {
        return RestResource.post(APPLY_NSL_API, reqPayload, data);
    }

    public static Response FetchWaybills(HashMap<String, String> paramsMap, HashMap<String, String> data) {
        return RestResource.get(FETCH_WAYBILL, paramsMap, data);
    }

    public static Response fetchUserJwtToken(Object reqPayload) {
        return RestResource.post(FETCH_USER_JWT_TOKEN, reqPayload, "UMS");
    }

    public static Response createNoDataUpl(Object reqPayload, String token) {
        return RestResource.post(ASYNC_OMS_CREATE_NODATA_SHIPMENT, reqPayload, token);
    }

    public static Response getNoDataUplShipment(HashMap<String, String> requestData) {
        return RestResource.get(OMS_FETCH_NODATA_SHIPMENT, requestData);
    }

    public static Response cmuV2ManifestNoDataShipment(Object reqPayload, String token) {
        return RestResource.post(CMU_V2_MANIFEST_SHIPMENT, reqPayload, token);
    }

    public static Response ApplyNslApi(String enviorment, Object reqPayload, HashMap<String, String> data) {
        return RestResource.post(APPLY_NSL_API, enviorment, reqPayload, data.get("client"));
    }

    public static Response getClientDetails(String paramsKey, String paramsValue) {
        return RestResource.get(FETCH_CLIENT_DETAILS, paramsKey, paramsValue);
    }

    public static Response dispatchFreeze(Object reqPayload, String paramsKey, String paramsValue) {
        return RestResource.put(DISPATCH_FREEZE, reqPayload, paramsKey, paramsValue, "Odx");
    }

    public static Response markPending(Object reqPayload, String paramsKey, String paramsValue) {
        return RestResource.put(MARK_PENDING, reqPayload, paramsKey, paramsValue, "Odx");
    }

    public static Response markDelivered(Object reqPayload, String paramsKey, String paramsValue) {
        return RestResource.put(MARK_DELIVERED, reqPayload, paramsKey, paramsValue, "Odx");
    }

    public static Response fetchBagMatrix(Object reqPayload) {
        return RestResource.post(FETCH_BAGMATRIX, reqPayload);
    }

    public static Response bulkNdrEdit(Object reqPayload, HashMap<String, String> data) {
        return RestResource.post(BULK_NDR, reqPayload, data);
    }

    public static Response getBagDetails(String paramsKey, String paramsValue, HashMap<String, String> data) {
        return RestResource.get(FETCH_PACKAGE_REST_INFO, paramsKey, paramsValue, data);
    }

    public static Response bagV3Shipment(Object reqPayload, HashMap<String, String> data) {
        return RestResource.post(BAG_V3_SHIPMENT, reqPayload, data);
    }

    public static Response bagAddToTrip(Object reqPayload, HashMap<String, String> data) {
        return RestResource.put(BAG_ADD_TO_TRIP, reqPayload, data);
    }

    public static Response bagRemoveFromTrip(Object reqPayload, HashMap<String, String> data) {
        return RestResource.put(BAG_REMOVE_FROM_TRIP, reqPayload, data);
    }

    public static Response tripIncoming(Object reqPayload, HashMap<String, String> data) {
        return RestResource.put(TRIP_INCOMING, reqPayload, data);
    }

    public static Response bagIncoming(Object reqPayload, HashMap<String, String> data) {
        return RestResource.put(BAG_INCOMING, reqPayload, data);
    }

    public static Response kinkoInvoiceCharges(HashMap<String, String> data) {
        return RestResource.getMultipleParams(KINKO_INVOICE_CHARGES, data);
    }

    public static Response clientUpdate(Object reqPayload) {
        return RestResource.post(CLIENT_UPDATE, reqPayload);
    }

    public static Response fetchClientWbn(HashMap<String, String> data) {
        return RestResource.getMultipleParams(FETCH_CLIENT_WBN, data);
    }

    public static Response removeEwbn(Object reqPayload) {
        return RestResource.post(REMOVE_EWBN, reqPayload);
    }

    public static Response packageStatus(String paramsKey, String paramsValue) {
        return RestResource.get(PACKAGE_STATUS, paramsKey, paramsValue);
    }

    public static Response packageAction(String paramsKey, String paramsValue) {
        return RestResource.get(ACTION_API, paramsKey, paramsValue);
    }

    public static Response bagCustodyScan(Object reqPayload) {
        return RestResource.post(BAG_CUSTODDY_SCAN, reqPayload);
    }

    public static Response bagV2Shipment(Object reqPayload, HashMap<String, String> data) {
        return RestResource.post(BAG_V2_SHIPMENT, reqPayload, data);
    }

    public static Response bagV2Shipment(Object reqPayload) {
        return RestResource.post(BAG_V2_SHIPMENT, reqPayload);
    }

    public static Response autobag(Object reqPayload) {
        return RestResource.post(AUTOBAG, reqPayload);
    }

    public static Response fmApiIncoming(Object reqPayload) {
        return RestResource.post(FM_API_INCOMING, reqPayload);
    }

    public static Response customEdit(Object reqPayload, String waybill) {
        return RestResource.put(CUSTOMEDIT + waybill + "/", reqPayload);
    }

    public static Response clientCreateUpdate(Object reqPayload) {
        return RestResource.put(CLIENT_CREATE_UPDATE, reqPayload);
    }

    public static Response pkgCount() {
        return RestResource.get(PKG_COUNT);
    }

    public static Response fetchDC(String paramsKey, String paramsValue) {
        return RestResource.get(FETCH_DC, paramsKey, paramsValue);
    }

    public static Response fetchCity(String paramsKey, String paramsValue) {
        return RestResource.get(FETCH_CITY, paramsKey, paramsValue);
    }

    public static Response fetchPincodeInfo(String paramsKey, String paramsValue) {
        return RestResource.get(PINCODE_INFO, paramsKey, paramsValue);
    }

    public static Response fetchPackingSlip(String paramsKey, String paramsValue, HashMap<String, String> data) {
        return RestResource.get(PACKING_SLIP, paramsKey, paramsValue, data);
    }

    public static Response fetchPackingSlip(String paramsKey, String paramsValue) {
        return RestResource.get(PACKING_SLIP, paramsKey, paramsValue);
    }

    public static Response packageCancel(Object reqPayload) {
        return RestResource.put(PACKAGE_CANCEL, reqPayload);
    }

    public static Response fetchDcCenter() {
        return RestResource.get(FETCH_DC_CENTER);
    }

    public static Response updatePackage(Object reqPayload) {
        return RestResource.post(PACKAGE_UPDATE, reqPayload);
    }

    public static Response locationAssociate(Object reqPayload) {
        return RestResource.put(LOCATION_ASSOCIATE, reqPayload);
    }

    public static Response kinkoFetch(HashMap<String, String> data) {
        return RestResource.getMultipleParams(KINKO_FETCH, data);
    }

    public static Response clientDetails(String token) {
        return RestResource.get(CLIENT_DETAILS + token);
    }

    public static Response clientDetailsByClientName(String clientName) {
        return RestResource.get(CLIENT_DETAILS_BY_NAME, "client", clientName);
    }

    public static Response stateInfo() {
        return RestResource.get(STATE_INFO);
    }

    public static Response bagInfo(Object reqPayload) {
        return RestResource.get(BAG_INFO, reqPayload);
    }

    public static Response newFmRequest(Object reqPayload) {
        return RestResource.post(NEW_FM, reqPayload);
    }

    public static Response cmuPush(Object reqPayload, String clientName) {
        return RestResource.post(CMU_PUSH_API_MANIFEST, reqPayload, clientName);
    }

    public static Response selfCollect(Object reqPayload) {
        return RestResource.put(SELF_COLLECT_V1, reqPayload);
    }

    public static Response locationDissociate(Object reqPayload) {
        return RestResource.put(LOCATION_DISSOCIATE, reqPayload);
    }

    public static Response fetchPackageDetails(HashMap<String, String> data, String token) {
        return RestResource.get(FETCH_PACKAGE_DETAILS, data, token);
    }

    public static Response fetchBagCalc(String paramsKey, String paramsValue, HashMap<String, String> data) {
        return RestResource.get(BAG_CALC, paramsKey, paramsValue, data);
    }

    public static Response fetchBagCalc(String paramsKey, String paramsValue) {
        return RestResource.get(BAG_CALC, paramsKey, paramsValue);
    }

    public static Response updatedBagmatrix(Object reqPayload) {
        return RestResource.post(UPDATED_BAGMATRIX, reqPayload);
    }

    public static Response editPhone(Object reqPayload) {
        return RestResource.post(EDIT_PHONE, reqPayload);
    }

    public static Response updatePackageStatus(Object reqPayload, HashMap<String, String> data) {
        return RestResource.post(PACKAGE_STATUS_UPDATE, reqPayload, data);
    }

    public static Response custodyConnection(String paramsKey, String paramsValue) {
        return RestResource.get(CUSTODY_CONNECTION, paramsKey, paramsValue);
    }

    public static Response updateFinalQcWt(Object reqPayload) {
        return RestResource.post(UPDATE_FINAL_WT, reqPayload);
    }

    public static Response meeshoManifestShipment(Object reqPayload, HashMap<String, String> data) {
        return RestResource.post(MEESHO_MANIFEST_SHIPMENT, reqPayload, data);
    }

    public static Response getManifestationUplData(HashMap<String, String> requestData) {
        return RestResource.get(FETCH_MANIFESTATION_UPL_DATA, requestData);
    }

    public static Response getSearchDetails(String paramsKey, String paramsValue) {
        return RestResource.get(SEARCH_PACKAGE, paramsKey, paramsValue);
    }

    public static Response getMtsDetails(String paramsKey, String paramsValue) {
        return RestResource.get(FETCH_MTS_INFO, paramsKey, paramsValue);
    }

    public static Response flShipment(Object reqPayload, HashMap<String, String> data) {
        return RestResource.post(UPDATE_Fetch_List, reqPayload, data);
    }

    public static Response Postphonelog(Object reqPayload, String token) {
        return RestResource.post(REST_PHONELOG, reqPayload, token);
    }

    public static Response whDetails(Object requestBody) {

        return RestResource.post(FETCH_CLIENTWAREHOUSE_STATUS, requestBody);
    }

    public static Response whsresp(Object responseBody) {

        return RestResource.post(FETCH_CLIENTWAREHOUSE_STATUS, responseBody);
    }

    public static Response whscrt(Object responseBody) {

        return RestResource.post(CREATE_CLIENTWAREHOUSE, responseBody);
    }

    public static Response editwhDetails(Object requestBody) {

        return RestResource.post(EDIT_CLIENTWAREHOUSE, requestBody);
    }

    public static Response editwhresp(Object responseBody) {

        return RestResource.post(EDIT_CLIENTWAREHOUSE, responseBody);
    }

    public static Response updateDetails(Object requestBody) {

        return RestResource.post(UPDATE_AGGRIGATE_wt, requestBody);
    }

    public static Response updateresp(Object responseBody) {

        return RestResource.post(UPDATE_AGGRIGATE_wt, responseBody);
    }

    public static Response getQrcode(Object responseBody) {

        return RestResource.post(Generate_qr_code, responseBody);
    }

    public static Response dcPinDetails(String paramsValue) {
        return RestResource.get(Fetch_Serviceability, "pincode", paramsValue);
    }

    public static Response dcStationDetails(String paramsValue) {
        return RestResource.get(Fetch_Station_List, "page", paramsValue);
    }

    public static Response lhDetails(String paramsValue) {
        return RestResource.get(LH_Connections, "origin", paramsValue);
    }

    public static Response ApplyNslGeneric(Object reqPayload, HashMap<String, String> data) {
        return RestResource.put(APPLY_NSL_GENERIC, reqPayload, data);
    }

    public static Response ApplyNsl(Object reqPayload, HashMap<String, String> data) {
        return RestResource.put(APPLY_Nsl, reqPayload, data);
    }

    public static Response instaBagging(Object reqPayload, HashMap<String, String> requestData) {
        return RestResource.post(INSTA_BAGGING, reqPayload, requestData);
    }

    public static Response getClientsDetails(String paramsKey, String paramsValue, HashMap<String, String> data) {
        return RestResource.get(FETCH_CLIENT_DETAILS, paramsKey, paramsValue, data);
    }

    public static Response getClientsUuidDetails(String clientUuid) {
        return RestResource.get(FETCH_CLIENT_UUID_DETAILS + clientUuid);
    }

    public static Response getClaimsB2cListing(String paramsKey, Object paramsValue, HashMap<String, String> data) throws JsonProcessingException {
        return RestResource.get(FETCH_B2C_LISTING, paramsKey, paramsValue, data);
    }

    public static Response getClaimsB2bListing(String paramsKey, Object paramsValue, HashMap<String, String> data) throws JsonProcessingException {
        return RestResource.get(FETCH_B2B_LISTING, paramsKey, paramsValue, data);
    }

    public static Response getUcpLossClaims(String paramsKey, Object paramsValue, HashMap<String, String> data) throws JsonProcessingException {
        return RestResource.get(FETCH_UCP_LOSS_CLAIMS, paramsKey, paramsValue, data);
    }

    public static Response createLossClaims(Object reqPayload, HashMap<String, String> data) {
        return RestResource.post(CREATE_LOSS_CLAIM, reqPayload, data);
    }

    public static Response getClaimIdData(HashMap<String, String> apiData, HashMap<String, String> data) {
        return RestResource.get(FETCH_CLAIM_ID_DATA + apiData.get("ClaimId"), data);
    }

    public static Response createClaimUploadFile(String filePath, HashMap<String, String> data) {
        return RestResource.post(CREATE_CLAIM_UPLOAD_FILE, filePath, data);
    }

    public static Response getClaimHistory(HashMap<String, String> data) {
        return RestResource.get(CLAIM_HISTORY, data);
    }

    public static Response getB2bViewData(HashMap<String, String> apiData, String paramsKey, Object paramsValue, HashMap<String, String> data) throws JsonProcessingException {
        return RestResource.get(FETCH_B2B_VIEW_DATA + apiData.get("ClaimId"), paramsKey, paramsValue, data);
    }

    public static Response getClaimsB2bReport(String paramsKey, Object paramsValue, HashMap<String, String> data) throws JsonProcessingException {
        return RestResource.get(FETCH_B2B_CLAIM_REPORT, paramsKey, paramsValue, data);
    }

    public static Response getClaimsB2cReport(String paramsKey, Object paramsValue, HashMap<String, String> data) throws JsonProcessingException {
        return RestResource.get(FETCH_B2C_CLAIM_REPORT, paramsKey, paramsValue, data);
    }

    public static Response updateClaimSettlementAmount(Object reqPayload, HashMap<String, String> apiData, HashMap<String, String> data) {
        return RestResource.patch(UPDATE_CLAIM_SETTLEMENT_AMOUNT + apiData.get("claimId"), reqPayload, data);
    }

    public static Response updateClaimSettlementAmountUploadFile(String filePath, HashMap<String, String> data) {
        return RestResource.patch(UPDATE_CLAIM_SETTLEMENT_AMOUNT_UPLOAD_FILE, filePath, data);
    }

    public static Response centerUpdate(Object reqPayload) {
        return RestResource.put(CENTER_UPDATE, reqPayload);
    }

    public static Response clientDetailsFetchInternal(HashMap<String, String> params, String bearer) {
        return RestResource.get(CLIENT_FETCH_INTERNAL, params, bearer);
    }

    public static Response updateRSEWBnDCN(String waybill, APIRestEWayBillRequestPayload apiRestEWayBillRequestPayload) {
        return RestResource.put(API_REST_E_WAYBILL + waybill + "/", apiRestEWayBillRequestPayload);
    }

    public static Response updateRSEWBnDCNByInternal(String waybill, APIRestEWayBillRequestPayload apiRestEWayBillRequestPayload, String token) {
        return RestResource.put(INTERNAL_API_E_WAYBILL + waybill + "/", apiRestEWayBillRequestPayload, token);
    }

    public static Response getQcAnswers(String paramsKey, String paramsValue) {
        return RestResource.getQCAnswers(QC_fetch, paramsKey, paramsValue);
    }


    public static Response FetchodTat(Map<String, String> params) {
        return RestResource.get(OD_TAT, params);
    }

    public static Response unsetReturnDispatchId(Object requestPayload, String dwnId) {
        return RestResource.put(Routes.UNSET_RETURN_DISPATCH_ID, requestPayload, "dwbn", dwnId, "RT-ODX");
    }

    public static Response pUpdate(Object reqPayload) {
        return RestResource.post(PACKAGE_UPDATE_2, reqPayload);
    }

    public static Response updateTransIdByBulkUpdate(Object requestPayload) {
        return RestResource.put(UPDATE_TRANSACTION_ID_BULK, requestPayload);
    }

    public static Response updateTransIdByDexApi(Object requestPayload) {
        return RestResource.post(UPDATE_TRANSACTION_ID_DEX, requestPayload);
    }

    public static Response uplResponse(String upl) {
        return RestResource.get(FETCH_UPL_RESPONSE + upl);
    }

    public static Response getPackageDetails(HashMap<String, String> paramsMap, HashMap<String, String> data) {
        return RestResource.get(FETCH_PACKAGE_REST_INFO, paramsMap, data);
    }

    public static Response fetchclientDetails(HashMap<String, String> data) {
        return RestResource.get(CLIENT_DETAILS, data);
    }

    public static Response updatePackage(Object reqPayload, HashMap<String, String> data) {
        return RestResource.post(PACKAGE_UPDATE, reqPayload, data);
    }

    public static Response getSMS(String environment, String phone, String paramsKey, String paramsValue) {
        return RestResource.get(environment, SMS_FETCH + phone, paramsKey, paramsValue);
    }

    public static Response getBagScore(String paramsKey, String paramsValue) {
        return RestResource.get(FETCH_SCORE, paramsKey, paramsValue);
    }

    public static Response updateBagStatus(Object requestPayload) {
        return RestResource.put(BAG_STATUS_UPDATE, requestPayload);
    }

    public static Response NewManifestShipment(String environment, Object reqPayload, HashMap<String, Object> data, String client) {
        if (environment == "manifest_regression_client") {
            return RestResource.post2(environment, NEW_MANIFEST_SERVICE, reqPayload, data, client);
        } else {
            return RestResource.post2(environment, CREATE_JSON_API_MANIFEST_SHIPMENT, reqPayload, data, client);
        }
    }

    public static Response getManifestationUplDataNew(String environment, HashMap<String, String> requestData) {
        return RestResource.get2(environment, FETCH_MANIFESTATION_UPL_DATA, requestData);
    }

    public static Response fetchEwbnCollection(String environment, String paramsKey, String paramsValue) {
        return RestResource.get(environment, FETCH_EWBN_COLLECTION, paramsKey, paramsValue);
    }

    public static Response fetchEwbn(String environment, String paramsKey, String paramsValue) {
        return RestResource.get(environment, FETCH_EWBN, paramsKey, paramsValue);
    }

    public static Response createEwbn(String environment, Object requestBody) {
        return RestResource.post(environment, CREATE_EWBN, requestBody);
    }

    public static Response fetchClientWbn2(HashMap<String, Object> data) {
        return RestResource.getMultipleParams(FETCH_CLIENT_WBN, data);
    }

    public static Response clientCreateUpdateNew(String environment, Object requestBody) {
        return RestResource.post(environment, CLIENT_CREATE_UPDATE, requestBody);
    }

    public static Response updateStatus(Map<String, String> data) {
        return RestResource.postFormData(P_UPDATE_STATUS, data);
    }

    public static Response pushDataToSorter(String pathParam, Map<String, String> data) {
        return RestResource.postFormData(SORTER_ADMIN_PUSH_WAYBILL_DATA_UI + pathParam, data);
    }

    public static Response getLMDashboardMissingShipments(String param, String paramValue, HashMap<String, String> data) {
        return RestResource.get(API_LM_DASHBOARD_MISSING_SHIPMENTS, param, paramValue, data);
    }

    public static Response getPackageCount(String env) {
        if (env == null) {
            return RestResource.get(API_REST_P_COUNTS);
        }
        return RestResource.getdiffenv(API_REST_P_COUNTS, env);
    }

    public static Response getMPSAssociateDetails(String env, LinkedHashMap<String, String> params) {
        if (env == null) {
            return RestResource.get(API_P_MPS, params);
        }
        return RestResource.getdiffenv(API_P_MPS, env, params);
    }

    public static Response reBag(String env, Object body) {
        if (env == null) {
            return RestResource.post(API_BAG_REBAG, body);
        }
        return RestResource.post(API_BAG_REBAG, env, body);
    }
}

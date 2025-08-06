package com.delhivery.Express.controllers.api;

import com.delhivery.Express.QcWtApi.response.QcWtResponsePayload;
import com.delhivery.Express.applicationApi.PackageFlowRequests;
import com.delhivery.Express.controllers.helper.ControllerHelper;
import com.delhivery.Express.pojo.ApiLhConnection.LhconnectionResponse;
import com.delhivery.Express.pojo.ApplyNslApi.request.ApplyNslRequestPayload;
import com.delhivery.Express.pojo.ApplyNslApi.response.ApplyNslResponsePayload;
import com.delhivery.Express.pojo.AutobagApi.Request.AutobagRequestPayload;
import com.delhivery.Express.pojo.AutobagApi.Response.AutobagResponsePayload;
import com.delhivery.Express.pojo.BagAddToTripApi.request.BagAddToTripRequestPayload;
import com.delhivery.Express.pojo.BagAddToTripApi.response.BagAddToTripResponsePayload;
import com.delhivery.Express.pojo.BagCalc.Response.BagCalcResponsePayload;
import com.delhivery.Express.pojo.BagCustodyScanApi.Request.BagCustodyScanRequestPayload;
import com.delhivery.Express.pojo.BagCustodyScanApi.Response.BagCustodyScanResponsePayload;
import com.delhivery.Express.pojo.BagDetailsFetchRestInfoApi.response.BagDetails;
import com.delhivery.Express.pojo.BagIncomingApi.request.BagIncomingRequestPayload;
import com.delhivery.Express.pojo.BagIncomingApi.response.BagIncomingResponsePayload;
import com.delhivery.Express.pojo.BagInfo.Request.BagInfoRequestPayload;
import com.delhivery.Express.pojo.BagRemoveTripApi.request.BagRemoveTripRequestPayload;
import com.delhivery.Express.pojo.BagRemoveTripApi.response.BagRemoveTripResponsePayload;
import com.delhivery.Express.pojo.BagV3Api.request.BagV3RequestPayload;
import com.delhivery.Express.pojo.BagV3Api.response.BagV3ResponsePayload;
import com.delhivery.Express.pojo.BulkNdrEditApi.Request.BulkNdrRequestPayload;
import com.delhivery.Express.pojo.BulkNdrEditApi.Response.BulkNdrResponsePayload;
import com.delhivery.Express.pojo.CMS.CreateClaimsApi.request.CreateClaimsRequestPayload;
import com.delhivery.Express.pojo.CMS.CreateClaimsApi.response.CreateClaimsResponsePayload;
import com.delhivery.Express.pojo.CMS.FetchB2bClaimsDetailsApi.requestParamFilter.FetchB2bClaimsDetailsRequestParam;
import com.delhivery.Express.pojo.CMS.FetchB2bClaimsListingApi.requestParamFilter.FetchB2bClaimsListingRequestParam;
import com.delhivery.Express.pojo.CMS.FetchB2bClaimsListingApi.response.FetchB2bClaimsListingResponsePayload;
import com.delhivery.Express.pojo.CMS.FetchB2cClaimsListingApi.requestParamFilter.FetchB2cClaimsListingRequestParam;
import com.delhivery.Express.pojo.CMS.FetchClaimHistoryApi.response.FetchClaimHistoryResponsePayload;
import com.delhivery.Express.pojo.CMS.FetchClaimIdDataApi.response.FetchClaimIdDataResponsePayload;
import com.delhivery.Express.pojo.CMS.FetchClaimsReportApi.requestParamFilter.FetchClaimsReportRequestParam;
import com.delhivery.Express.pojo.CMS.FetchClaimsReportApi.response.FetchClaimsReportResponsePayload;
import com.delhivery.Express.pojo.CMS.FetchUcpLossClaimsApi.requestParamFilter.FetchUcpLossClaimsRequestParam;
import com.delhivery.Express.pojo.CMS.FetchUcpLossClaimsApi.response.FetchUcpLossClaimsResponsePayload;
import com.delhivery.Express.pojo.CMS.UpdateClaimSettlementAmountApi.request.UpdateClaimSettlementAmountRequestPayload;
import com.delhivery.Express.pojo.CMS.UpdateClaimSettlementAmountApi.response.UpdateClaimSettlementAmountResponsePayload;
import com.delhivery.Express.pojo.CMS.UploadFileCreateBulkClaimsApi.response.UploadFileCreateBulkClaimsResponsePayload;
import com.delhivery.Express.pojo.CenterUpdate.Request.CenterUpdateRequest;
import com.delhivery.Express.pojo.CenterUpdate.Response.CenterUpdateResponse;
import com.delhivery.Express.pojo.CenterUpdateRT.Request.CenterUpdateRequestRT;
import com.delhivery.Express.pojo.ClientCreateUpdate.Request.CreateUpdateClientRequestPayload;
import com.delhivery.Express.pojo.ClientCreateUpdate.Request.UpdateRegressionClientRequestPayload;
import com.delhivery.Express.pojo.ClientCreateUpdate.Response.CreateUpdateClientResponsePayload;
import com.delhivery.Express.pojo.ClientDetails.Response.ClientDetailsResponsePayload;
import com.delhivery.Express.pojo.ClientDetailsFetch.Response.FetchClientDetailsResponsePayload;
import com.delhivery.Express.pojo.ClientFetchInternal.Response.ClientFetchInternalResponse;
import com.delhivery.Express.pojo.ClientUpdateApi.Request.ClientUpdateRequestPayloadJava;
import com.delhivery.Express.pojo.ClientUpdateApi.Response.ClientUpdateResponsePayloadJava;
import com.delhivery.Express.pojo.ClientUpdateNew.Request.ClientUpdateNew;
import com.delhivery.Express.pojo.ClientUpdateNew.Response.ClientUpdateResponse;
import com.delhivery.Express.pojo.CmuPush.Request.CmuPushRequestPayload;
import com.delhivery.Express.pojo.CmuPush.Response.CmuPushResponsePayload;
import com.delhivery.Express.pojo.CmuV2ManifestNoDataShipmentApi.request.CmuV2ManifestNoDataShipmentApiRequestPayload;
import com.delhivery.Express.pojo.CmuV2ManifestNoDataShipmentApi.response.CmuV2ManifestNoDataShipmentApiResponsePayload;
import com.delhivery.Express.pojo.CreateBagV2Api.Request.CreateBagV2ApiRequestPayload;
import com.delhivery.Express.pojo.CreateBagV2Api.Response.CreateBagV2ApiResponsePayload;
import com.delhivery.Express.pojo.CreateBagV4.Request.CreateBagV4RequestPayload;
import com.delhivery.Express.pojo.CreateBagV4.Response.CreateBagV4ResponsePayload;
import com.delhivery.Express.pojo.CreateNoDataUplApi.request.CreateNoDataUplApiRequestPayload;
import com.delhivery.Express.pojo.CreateNoDataUplApi.response.CreateNoDataUplApiResponsePayload;
import com.delhivery.Express.pojo.CustodyConnection.Response.CustodyConnectionResponsePayload;
import com.delhivery.Express.pojo.DispatchFreeze.Request.DispatchFreezeRequestPayload;
import com.delhivery.Express.pojo.DispatchFreeze.Response.DispatchFreezeResponsePayload;
import com.delhivery.Express.pojo.EditApi.request.EditApiRequestPayload;
import com.delhivery.Express.pojo.EditApi.response.EditApiCancellationScanResponsePayload;
import com.delhivery.Express.pojo.EditApi.response.EditApiResponsePayload;
import com.delhivery.Express.pojo.EditPhone.Request.EditPhoneRequestPayload;
import com.delhivery.Express.pojo.EditPhone.Response.EditPhoneResponsePayload;
import com.delhivery.Express.pojo.FMOMSApi.request.FMOMSRequestPayload;
import com.delhivery.Express.pojo.FMOMSApi.response.FMOMSResponsePayload;
import com.delhivery.Express.pojo.CMS.FetchB2cClaimsListingApi.response.FetchB2cClaimsListingResponsePayload;
import com.delhivery.Express.pojo.FetchBagMatrix.Request.FetchBagMatrixRequestPayload;
import com.delhivery.Express.pojo.FetchBagMatrix.Response.FetchBagMatrixResponsePayload;
import com.delhivery.Express.pojo.FetchBagScore.Response.FetchBagScoreResponsePayload;
import com.delhivery.Express.pojo.FetchClientUuidDetailsResponsePayloadApi.response.FetchClientUuidDetailsResponsePayload;
import com.delhivery.Express.pojo.FetchClientWbn.Response.FetchClientWbnJava;
import com.delhivery.Express.pojo.FetchDcCenter.Response.FetchDcCenterResponsePayload;
import com.delhivery.Express.pojo.FetchList.Requist.FetchListRequest;
import com.delhivery.Express.pojo.FetchList.Response.FetchListResponse;
import com.delhivery.Express.pojo.FetchPackageDetails.response.PackageDetail;
import com.delhivery.Express.pojo.FetchPackageDetails2.response.PackageDetailNew;
import com.delhivery.Express.pojo.FetchPackageDetailsSecond.response.PackageDetail2;
import com.delhivery.Express.pojo.FetchWaybillsApi.response.FetchWaybillsResponse;
import com.delhivery.Express.pojo.FmApiIncoming.Request.FmApiIncomingRequestPayload;
import com.delhivery.Express.pojo.GIApi.request.GIRequestPayload;
import com.delhivery.Express.pojo.GIApi.response.GIResponsePayload;
import com.delhivery.Express.pojo.GetManifestUplApi.response.GetManifestUplDataResponsePayload;
import com.delhivery.Express.pojo.GetManifestUplApi.responseError.GetManifestErrorUplDataResponsePayload;
import com.delhivery.Express.pojo.GetManifestUplApi.responseFailure.GetManifestFailureUplDataResponsePayload;
import com.delhivery.Express.pojo.GetManifestUplApi.responsePaymentModeFailure.responseFailure.GetManifestPaymentModeFailureUplDataResponsePayload;
import com.delhivery.Express.pojo.GetNoDataUplShipmentApi.response.GetNoDataUplShipmentApiResponsePayload;
import com.delhivery.Express.pojo.InstaBaggingCreateApi.request.InstaBaggingCreateRequestPayload;
import com.delhivery.Express.pojo.InstaBaggingCreateApi.response.InstaBaggingCreateResponsePayload;
import com.delhivery.Express.pojo.InstaBaggingSealApi.request.InstaBaggingSealRequestPayload;
import com.delhivery.Express.pojo.InstaBaggingSealApi.response.InstaBaggingSealResponsePayload;
import com.delhivery.Express.pojo.InstaBaggingSealApi.response.bar.InstaBaggingSealWithBarResponsePayload;
import com.delhivery.Express.pojo.KinkoInvoiceChargesApi.Response.KinkoInvoiceChargesResponsePayloadJava;
import com.delhivery.Express.pojo.LMUpdateHQShipmentApi.request.LMUpdateHQShipmentRequestPayload;
import com.delhivery.Express.pojo.LMUpdateHQShipmentApi.respone.LMUpdateHQShipmentResponePayload;
import com.delhivery.Express.pojo.LocationAssociate.Request.LocationAssociateRequestPayload;
import com.delhivery.Express.pojo.LocationAssociate.Response.LocationAssociateResponsePayload;
import com.delhivery.Express.pojo.LocationDissociate.Request.LocationDissociateRequestPayload;
import com.delhivery.Express.pojo.LocationDissociate.Response.LocationDissociateResponsePayload;
import com.delhivery.Express.pojo.MPSAssociateDetails.response.MPSAssociateDetailsResPayload;
import com.delhivery.Express.pojo.ManifestCmuCreateApi.request.CmuManifestApi;
import com.delhivery.Express.pojo.ManifestCmuCreateApi.response.CmuResponseApi;
import com.delhivery.Express.pojo.ManifestCmuCreateApiNew.response.CmuResponseApiNew;
import com.delhivery.Express.pojo.ManifestCmuCreateWithCwhApi.request.CmuManifestRequestPayload;
import com.delhivery.Express.pojo.ManifestCmuCreateWithCwhApi.response.CmuResponsePayload;
import com.delhivery.Express.pojo.ManifestMpsMasterChild.Request.CreateMPS;
import com.delhivery.Express.pojo.MarkDelivered.Request.MarkDeliveredRequestPayload;
import com.delhivery.Express.pojo.MarkDelivered.Response.MarkDeliveredResponsePayload;
import com.delhivery.Express.pojo.MarkDispatchApi.request.MarkDispatchRequestPayload;
import com.delhivery.Express.pojo.MarkDispatchApi.response.MarkDispatchResponsePayload;
import com.delhivery.Express.pojo.MarkDispatchApi.responseMPS.MpsMarkDispatchResponsePayload;
import com.delhivery.Express.pojo.MarkDispatchApi.responseRvpShipment.MarkRvpShipmentDispatchResponsePayload;
import com.delhivery.Express.pojo.MarkPendingApi.Request.MarkPendingRequestPayload;
import com.delhivery.Express.pojo.MarkPendingApi.Response.MarkPendingResponsePayload;
import com.delhivery.Express.pojo.MeeshoManifestApi.response.MeeshoManifestResponsePayload;
import com.delhivery.Express.pojo.MeeshoManifestApi.responseError.MeeshoManifestErrorResponsePayload;
import com.delhivery.Express.pojo.MeeshoManifestApi.responseFailure.MeeshoManifestFailureResponsePayload;
import com.delhivery.Express.pojo.MtsInfoApi.Response.MtsResponse;
import com.delhivery.Express.pojo.NewFm.Request.NewFmRequestPayload;
import com.delhivery.Express.pojo.NewFm.Response.NewFmResponsePayload;
import com.delhivery.Express.pojo.NewManifestMadatoryKeys.request.MandatoryKeys;
import com.delhivery.Express.pojo.NewManifestService.request.Manifest;
import com.delhivery.Express.pojo.NewManifestService2.request.Manifest2;
import com.delhivery.Express.pojo.NewManifestService3.request.Manifest3;
import com.delhivery.Express.pojo.ODTat.Response.ODTatResponsePayload;
import com.delhivery.Express.pojo.PackageActionApi.Response.PackageActionApiResponsePayloadJava;
import com.delhivery.Express.pojo.PackageCancel.Request.PackageCancelRequestPayload;
import com.delhivery.Express.pojo.PackageCancel.Response.PackageCancelResponsePayload;
import com.delhivery.Express.pojo.PackageCount.Response.PackageCountResponsePayload;
import com.delhivery.Express.pojo.PackageDetailFetchRestInfoApi.response.PackageDetails;
import com.delhivery.Express.pojo.PackageStatusApi.Response.PackageStatusApiResponsePayloadJava;
import com.delhivery.Express.pojo.PackageStatusUpdate.Request.PackageStatusUpdateRequestPayload;
import com.delhivery.Express.pojo.PackageStatusUpdate.Response.PackageStatusUpdateResponsePayload;
import com.delhivery.Express.pojo.PackageUpdate.Request.PackageUpdateRequestPayload;
import com.delhivery.Express.pojo.PackageUpdate.Response.PackageUpdateResponsePayload;
import com.delhivery.Express.pojo.PackingSlip.Response.PackingSlipResponsePayload;
import com.delhivery.Express.pojo.PincodeInfo.Response.PincodeInfoResponsePayload;
import com.delhivery.Express.pojo.PkgRemoveFromBagApi.request.PkgRemoveFromBagRequestPayload;
import com.delhivery.Express.pojo.PkgRemoveFromBagApi.response.PkgRemoveFromBagResponsePayload;
import com.delhivery.Express.pojo.Psearch.Response.ApiResponse;
import com.delhivery.Express.pojo.PushWBNToSorter.response.PushWBNToSorterResPayload;
import com.delhivery.Express.pojo.QcWtApi.request.QcWtPayload;
import com.delhivery.Express.pojo.Qrcode.Request.QrcodeapiPayload;
import com.delhivery.Express.pojo.Qrcode.Response.QrcodeapiResponse;
import com.delhivery.Express.pojo.RemoveEwbnApi.Request.RemoveEwbnRequestPayload;
import com.delhivery.Express.pojo.SMS.SmsResponse;
import com.delhivery.Express.pojo.SelfCollectApi.Request.SelfCollectApiRequestPayload;
import com.delhivery.Express.pojo.SelfCollectApi.Response.SelfCollectApiResponsePayload;
import com.delhivery.Express.pojo.StationListApi.Response.StListResponse;
import com.delhivery.Express.pojo.TripIncomingApi.request.TripIncomingRequestPayload;
import com.delhivery.Express.pojo.TripIncomingApi.response.TripIncomingResponsePayload;
import com.delhivery.Express.pojo.UnsetReturnDispatchId.request.UnsetReturnDispatchIdReqPayload;
import com.delhivery.Express.pojo.UnsetReturnDispatchId.response.UnsetReturnDispatchIdResPayload;
import com.delhivery.Express.pojo.UnsetShipmentDispatchIdApi.request.UnsetShipmentDispatchIdRequestPayload;
import com.delhivery.Express.pojo.UnsetShipmentDispatchIdApi.response.UnsetShipmentDispatchIdResponsePayload;
import com.delhivery.Express.pojo.UpdateBagStatus.Request.UpdateBagStatusRequestPayload;
import com.delhivery.Express.pojo.UpdateBagStatus.Response.UpdateBagStatusResponsePayload;
import com.delhivery.Express.pojo.UpdateFinalWt.Request.UpdateFinalWtRequestPayload;
import com.delhivery.Express.pojo.UpdateFinalWt.Response.UpdateFinalWtResponsePayload;
import com.delhivery.Express.pojo.UpdateTransId.Request.UpdateTransIdRequestPayload;
import com.delhivery.Express.pojo.UpdateTransId.Response.UpdateTransIdResponsePayload;
import com.delhivery.Express.pojo.UpdateTransId1.Request.UpdateTransId1RequestPayload;
import com.delhivery.Express.pojo.UpdateTransId1.Response.UpdateTransId1ResponsePayload;
import com.delhivery.Express.pojo.UpdatedBagmatrix.Request.UpdatedBagmatrixRequestPayload;
import com.delhivery.Express.pojo.UpdatedBagmatrix.Response.UpdatedBagmatrixResponsePayload;
import com.delhivery.Express.pojo.WrhCreate.Request.CreateRequest;
import com.delhivery.Express.pojo.WrhCreate.Response.CreateResponse;
import com.delhivery.Express.pojo.WrhEdit.Request.EditRequest;
import com.delhivery.Express.pojo.WrhEdit.Response.EditResponse;
import com.delhivery.Express.pojo.WrhStatus.Request.StatusRequest;
import com.delhivery.Express.pojo.WrhStatus.Response.StatusResponse;
import com.delhivery.Express.pojo.es.PackageCount.response.PackageCountResPayload;
import com.delhivery.Express.pojo.ewaybill.APIRestEWayBill.request.APIRestEWayBillRequestPayload;
import com.delhivery.Express.pojo.ewaybill.APIRestEWayBill.response.APIRestEWayBillResponsePayload;
import com.delhivery.Express.pojo.applynsl.request.applynsl;
import com.delhivery.Express.pojo.applynsl.response.applynslresponse;
import com.delhivery.Express.pojo.applynslgeneric.request.Applynslgeneric;
import com.delhivery.Express.pojo.applynslgeneric.response.ApplynslgenericResponse;
import com.delhivery.Express.pojo.ewbnCollection.ewbncollection;
import com.delhivery.Express.pojo.ewbnCreate.request.ewbncreation;
import com.delhivery.Express.pojo.ewbnCreate.response.ewbncreationresponse;
import com.delhivery.Express.pojo.fetchewbn.response.fetchEwbnResponse;
import com.delhivery.Express.pojo.hqTracking.response.HQTrack;
import com.delhivery.Express.pojo.hqTrackingError.response.HQTrackError;
import com.delhivery.Express.pojo.ms.response.LMDashboardMissingShipmentsRes;
import com.delhivery.Express.pojo.rebag.request.ReBagReqPayload;
import com.delhivery.Express.pojo.rebag.response.ReBagResPayload;
import com.delhivery.Express.pojo.pUpdate.PUpdatePayload;
import com.delhivery.Express.pojo.pUpdate.PUpdateResponse;
import com.delhivery.Express.pojo.trackingApi.response.Example;
import com.delhivery.Express.pojo.trackingApiError.response.TrackError;
import com.delhivery.Express.testModules.ManifestationService.RequestBuilder2;
import com.delhivery.Express.RequestBuilder.RequestBuilder;
import com.delhivery.core.BaseTest;
import com.delhivery.core.api.TokenManager;
import com.delhivery.core.utils.Assertions;
import com.delhivery.core.utils.Utilities;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.mongodb.util.JSON;
import io.restassured.response.Response;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static com.delhivery.core.utils.Assertions.*;
import static com.delhivery.core.utils.Utilities.logInfo;

public class ApiController extends BaseTest {
    private Object requestPayload;

    public PackageDetail verifyPackageFetchInfoApi(String waybill, String statusType, String scanStatus, String scanNsl,
                                                   HashMap<String, String> data) {
        Response response = PackageFlowRequests.getPackageDetails("ref_ids", waybill, data);
        assertStatusCode(200, response);

        PackageDetail[] pkg_Details = response.as(PackageDetail[].class);
        PackageDetail pkgDetails = pkg_Details[0];
        if (statusType != null) {
            assertKeyValue("Status type", statusType, pkgDetails.cs.st);
            assertKeyValue("Scan status", scanStatus, pkgDetails.cs.ss);
            assertKeyValue("Scan nsl", scanNsl, pkgDetails.cs.nsl);
            logInfo("Scan REMARK : " + pkgDetails.cs.sr);
        }
        return pkgDetails;
    }

    public Example trackingApi(Map<String, String> data) {
        Response response = PackageFlowRequests.trackingDetails(data);
        assertStatusCode(200, response);
        Example dataDetails = response.as(Example.class);

        return dataDetails;

    }

    public HQTrack hqTrackingApi(Map<String, String> data) {
        Response response = PackageFlowRequests.hqTrackingDetails(data);
        assertStatusCode(200, response);
        HQTrack dataDetails = response.as(HQTrack.class);

        return dataDetails;

    }

    public PackageDetail fetchPackageInfo(String waybill, HashMap<String, String> data) {
        Response response = PackageFlowRequests.getPackageDetails("ref_ids", waybill, data);
        assertStatusCode(200, response);
        PackageDetail[] pkg_Details = response.as(PackageDetail[].class);
        PackageDetail pkgDetails = pkg_Details[0];
        return pkgDetails;
    }

    //    public String cmuManifestApi(String product_type, String payment_mode) {
//      String json=null;
//        CmuManifestApi body = RequestBuilder.cmuManifestApiReqGen();
//        body.shipments.get(0).setProductType(product_type);
//        body.shipments.get(0).setPaymentMode(payment_mode);
//      try {
//          json = "data="+Utilities.jsonObjectToString(body)+"&format=json";
//      } catch (JsonProcessingException e) {
//          // TODO Auto-generated catch block
//          e.printStackTrace();
//      }
//        Response response = PackageFlowRequests.cmuManifestShipment(json);
//        assertStatusCode(200, response);
////        response.then().body("success", is(true));
////        response.then().body("packages.status", hasItem("Success"));
//
//        CmuResponseApi apiResponse = response.as(CmuResponseApi.class);
//        assertKeyValue("status", "Success", apiResponse.packages.get(0).status);
//        assertKeyValue("success", true, apiResponse.success);
//
//        return apiResponse.packages.get(0).waybill;
//
//    }
    public void rvpManifestApi(List<HashMap<String, String>> data) {
        String json = null;
        CmuManifestApi body = RequestBuilder.rvpReqBuildRequestPayload(data);

        // setting up the body provided in data
        try {
            json = "data=" + Utilities.jsonObjectToString(body) + "&format=json";
        } catch (JsonProcessingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        Response response = PackageFlowRequests.cmuManifestShipment(json, data.get(0));
        assertStatusCode(200, response);
        CmuResponseApi apiResponse = response.as(CmuResponseApi.class);
        if (data.get(0).get("payment_mode") != "pickup") {
            assertKeyValue("status", "Fail", apiResponse.packages.get(0).status);
            assertKeyValue("success", false, apiResponse.success);
            assertKeyValue("remarks", "Time bound and Quality checks are valid only for Pickup packages.", apiResponse.packages.get(0).remarks.get(0));
        } else if (data.get(0).get("payment_mode") == "pickup" && Integer.valueOf(data.get(0).get("quantity")) > 2 && data.get(0).get("product_type") != "Heavy") {
            assertKeyValue("status", "Fail", apiResponse.packages.get(0).status);
            assertKeyValue("success", false, apiResponse.success);
            assertKeyValue("remarks", "Crashing while saving package due to exception - number of items in qc cannot be greater than 2. Package might have been partially saved.", apiResponse.packages.get(0).remarks.get(0));
        } else {
            assertKeyValue("status", "Success", apiResponse.packages.get(0).status);
            assertKeyValue("success", true, apiResponse.success);
        }
    }


    public ArrayList<String> cmuManifestApi(HashMap<String, String> data) {
        String json = null;
        CmuManifestApi body = RequestBuilder.cmuManifestApiReqGen(data);

        //Prod script
//      if (data.get("jobType").equalsIgnoreCase("prodSanity")) {
//          body.getShipments().get(0).client = data.get("prodClient");
//      }

        //if (data.get("jobType").equalsIgnoreCase("prodSanity")) {
        //body.getShipments().get(0).client = data.get("prodClient");
        //}

        // setting up the body provided in data
        try {
            json = "data=" + Utilities.jsonObjectToString(body) + "&format=json";
        } catch (JsonProcessingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        Response response = PackageFlowRequests.cmuManifestShipment(json, data);
        assertStatusCode(200, response);

        CmuResponseApi apiResponse = response.as(CmuResponseApi.class);
        assertKeyValue("status", "Success", apiResponse.packages.get(0).status);
        assertKeyValue("success", true, apiResponse.success);

        ArrayList<String> waybills = new ArrayList<>();

        for (int i = 0; i < apiResponse.packages.size(); i++) {
            waybills.add(apiResponse.packages.get(i).waybill);
        }


        return waybills;
    }

    public void fmOMSApi(String waybill, String eventCode, HashMap<String, String> data) {
        String json = null;
        FMOMSRequestPayload body = RequestBuilder.fmOmsApiReqGen(eventCode, data);
        try {
            json = Utilities.jsonObjectToString(body).replace("Waybill", waybill);
        } catch (JsonProcessingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        Response response = PackageFlowRequests.fmOmsShipment(JSON.parse(json), data);
        assertStatusCode(200, response);

        FMOMSResponsePayload apiResponse = response.as(FMOMSResponsePayload.class);
        assertKeyValue("success", true, apiResponse.success);

    }

    public void fmOMSApi(String waybill, String eventCode, String locationID, HashMap<String, String> data) {
        String json = null;
        FMOMSRequestPayload body = RequestBuilder.fmOmsApiReqGen(eventCode, data);
        body.setLocation_id(locationID);
        if (data.containsKey("clientWarehouse")) {
            body.setWarehouse_id(data.get("clientWarehouse"));
        }
        try {
            json = Utilities.jsonObjectToString(body).replace("Waybill", waybill);
        } catch (JsonProcessingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        Response response = PackageFlowRequests.fmOmsShipment(JSON.parse(json), data);
        assertStatusCode(200, response);

        FMOMSResponsePayload apiResponse = response.as(FMOMSResponsePayload.class);
        assertKeyValue("success", true, apiResponse.success);

    }

    public void giApi(String waybill, String centre, HashMap<String, String> data) {
        GIRequestPayload body = RequestBuilder.giApiReqGen(Collections.singletonList(waybill));
        body.setCenter(centre);
        ControllerHelper.doGIWithRetry(body, data);
    }

    public String bagv3Api(String waybill) {
        BagV3RequestPayload body = RequestBuilder.bagV3ApiReqGen(waybill);
        try {
            requestPayload = JSON.parse(Utilities.jsonObjectToString(body).replace("waybill", waybill));
        } catch (JsonProcessingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        Response response = PackageFlowRequests.bagV3Shipment(requestPayload);
        assertStatusCode(200, response);

        BagV3ResponsePayload apiResponse = response.as(BagV3ResponsePayload.class);
        assertKeyValue("success", true, apiResponse.success);

        return apiResponse.data.get(0).bs;

    }

    public String bagv4Api(String waybill, HashMap<String, String> data) {
        CreateBagV4RequestPayload body = RequestBuilder.bagV4ApiReqGen(data);
        try {
            requestPayload = JSON.parse(Utilities.jsonObjectToString(body).replace("Waybill", waybill));
        } catch (JsonProcessingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        Response response = PackageFlowRequests.bagV4Shipment(requestPayload, data);
        assertStatusCode(200, response);

        CreateBagV4ResponsePayload apiResponse = response.as(CreateBagV4ResponsePayload.class);
        assertKeyValue("success", true, apiResponse.success);

        return apiResponse.data.success.get(0);
    }

    public String bagv4Api(String waybill) {
        CreateBagV4RequestPayload body = RequestBuilder.bagV4ApiReqGen();
        try {
            requestPayload = JSON.parse(Utilities.jsonObjectToString(body).replace("waybill", waybill));
        } catch (JsonProcessingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        Response response = PackageFlowRequests.bagV4Shipment(requestPayload);
        assertStatusCode(200, response);

        CreateBagV4ResponsePayload apiResponse = response.as(CreateBagV4ResponsePayload.class);
        assertKeyValue("success", true, apiResponse.success);

        return apiResponse.data.success.get(0);

    }

    public void verifyBagFetchInfoApi(String bagId, String scanStatus, String scanAct, String pid) {
        Response response = PackageFlowRequests.getBagDetails("ref_ids", bagId);
        assertStatusCode(200, response);

        BagDetails bagDetails = response.as(BagDetails.class);
        assertKeyValue("Bag scan status", scanStatus, bagDetails.bags.get(0).cs.ss);
        assertKeyValue("Bag scan act", scanAct, bagDetails.bags.get(0).cs.act);
        assertKeyValue("Bag pid", pid, (String) bagDetails.bags.get(0).pid);
        logInfo("Bag Scan REMARK : " + bagDetails.bags.get(0).cs.sr);

    }

    public String bagAddToTripApi(String bagId) {
        BagAddToTripRequestPayload body = RequestBuilder.bagAddToTripApiReqGen(bagId);
        Response response = PackageFlowRequests.bagAddToTrip(body);
        assertStatusCode(200, response);

        BagAddToTripResponsePayload apiResponse = response.as(BagAddToTripResponsePayload.class);
        assertKeyValue("success", true, apiResponse.success);

        return body.trid;
    }

    public void bagRemoveTripApi(ArrayList<String> bagIds, HashMap<String, String> data, String tripIds) {
        for (String bagId : bagIds) {
            BagRemoveTripRequestPayload body = RequestBuilder.bagRemoveFromTripApiReqGen(bagId, tripIds);
            Response response = PackageFlowRequests.bagRemoveFromTrip(body, data);
            assertStatusCode(200, response);

            BagRemoveTripResponsePayload apiResponse = response.as(BagRemoveTripResponsePayload.class);
            assertKeyValue("success", true, apiResponse.success);
        }
    }

    public void pkgRemoveBagApi(String waybill, HashMap<String, String> data, String bagId) {
        PkgRemoveFromBagRequestPayload body = RequestBuilder.pkgRemoveFromBagApiReqGen(waybill, bagId);
        Response response = PackageFlowRequests.pkgRemoveFromBag(body, data);
        assertStatusCode(200, response);

        PkgRemoveFromBagResponsePayload apiResponse = response.as(PkgRemoveFromBagResponsePayload.class);
        assertKeyValue("status", true, apiResponse.status);
    }

    public void tripIncomingApi(String tripId) {
        TripIncomingRequestPayload body = RequestBuilder.tripIncomingApiReqGen(tripId);
        Response response = PackageFlowRequests.tripIncoming(body);
        assertStatusCode(200, response);

        TripIncomingResponsePayload apiResponse = response.as(TripIncomingResponsePayload.class);
        assertKeyValue("success", true, apiResponse.success);

    }

    public void bagIncomingApi(String bagId) {
        BagIncomingRequestPayload body = RequestBuilder.bagIncomingApiReqGen(bagId);
        Response response = PackageFlowRequests.bagIncoming(body);
        assertStatusCode(200, response);

        BagIncomingResponsePayload apiResponse = response.as(BagIncomingResponsePayload.class);
        assertKeyValue("success", true, apiResponse.getSuccess());

    }

    public String markShipmentDispatchApi(String waybill, HashMap<String, String> data) {
        MarkDispatchRequestPayload body = RequestBuilder.markDispatchApiReqGen(Collections.singletonList(waybill));
        if (data.containsKey("shipment_destination_center")) {
            body.setSl(data.get("shipment_destination_center"));
        }
        Response response = PackageFlowRequests.markShipmentDispatch(body, data);
        assertStatusCode(200, response);

        MarkDispatchResponsePayload apiResponse = response.as(MarkDispatchResponsePayload.class);
        assertKeyValue("status", true, apiResponse.meta.status);

        return body.dwbn;
    }

    public void lmUpdateHQShipmentApi(String waybill, String action, HashMap<String, String> data) {
        LMUpdateHQShipmentRequestPayload body = RequestBuilder.lmUpdateHQShipmentApiReqGen();
        if (data.containsKey("shipment_destination_center")) {
            body.payload.data.waybill.setSl(data.get("shipment_destination_center"));
        }
        if (action.equalsIgnoreCase("Delivered")) {
            body.payload.data.waybill.setSt("DL");
            body.payload.data.waybill.setSs("Delivered");
            body.payload.data.waybill.setNsl_code("EOD-38");
        } else if (action.equalsIgnoreCase("PickedUp")) {
            body.payload.data.waybill.setSt("PU");
            body.payload.data.waybill.setSs("In Transit");
            body.payload.data.waybill.setNsl_code("EOD-77");
        } else if (action.equalsIgnoreCase("Cancelled")) {
            body.payload.data.waybill.setSt("CN");
            body.payload.data.waybill.setSs("Cancelled");
            body.payload.data.waybill.setNsl_code("EOD-108");
        } else if (action.equalsIgnoreCase("REPL RT")) {
            body.payload.data.waybill.setSt("RT");
            body.payload.data.waybill.setSs("In Transit");
            body.payload.data.waybill.setNsl_code("EOD-50");
        } else if (action.equalsIgnoreCase("REPL PU")) {
            body.payload.data.waybill.setSt("PU");
            body.payload.data.waybill.setSs("In Transit");
            body.payload.data.waybill.setNsl_code("EOD-77");
        }
        try {
            requestPayload = JSON.parse(Utilities.jsonObjectToString(body).replace("Waybill", waybill));
        } catch (JsonProcessingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        Response response = PackageFlowRequests.lmUpdateHqShipment(requestPayload, data);
        assertStatusCode(200, response);

        LMUpdateHQShipmentResponePayload apiResponse = response.as(LMUpdateHQShipmentResponePayload.class);
        assertKeyValue("status", "Completed", apiResponse.status);

    }

    public void unsetShipmentDispatchIdApi(String waybill, String dispatchId, HashMap<String, String> data) {
        UnsetShipmentDispatchIdRequestPayload body = RequestBuilder.unsetShipmentDispatchIdApiReqGen();
        body.payload.setWbns(Collections.singletonList(waybill));
        body.payload.setDispatch_id(dispatchId);
        Response response = PackageFlowRequests.unsetShipmentDispatchId(body, data);
        assertStatusCode(200, response);

        UnsetShipmentDispatchIdResponsePayload apiResponse = response.as(UnsetShipmentDispatchIdResponsePayload.class);
        assertKeyValue("success", true, apiResponse.success);

    }

    public void QcWtApi(String waybill, double wt, String wtr, HashMap<String, String> data) {
        QcWtPayload body = RequestBuilder.QcWtApiReqGen(waybill, wt, wtr);

        Response response = PackageFlowRequests.updateQcWt(body, data);
        assertStatusCode(200, response);

        QcWtResponsePayload apiResponse = response.as(QcWtResponsePayload.class);
        assertKeyValue("success", true, apiResponse.success);

    }

    public EditApiResponsePayload EditApi(String waybill, HashMap<String, String> data) {
        EditApiRequestPayload body = RequestBuilder.EditApiReqGen(waybill, data);

        Response response = PackageFlowRequests.EditApi(body, data);
        assertStatusCode(200, response);

        EditApiResponsePayload apiResponse = response.as(EditApiResponsePayload.class);
        if (data.containsKey("e_way_bill")) {
            return apiResponse;
        }
        assertKeyValue("status", "true", apiResponse.status);
        //Todo add raseg paseg check in controller helper
        return apiResponse;
    }

    public EditApiCancellationScanResponsePayload EditApiApplyCancellationScan(String waybill, HashMap<String, String> data) {
        EditApiRequestPayload body = RequestBuilder.EditApiReqGen(waybill, data);

        Response response = PackageFlowRequests.EditApi(body, data);
        assertStatusCode(200, response);

        EditApiCancellationScanResponsePayload apiResponse = response.as(EditApiCancellationScanResponsePayload.class);

        assertKeyValue("status", "true", apiResponse.status);
        return apiResponse;
    }


    public ApplyNslResponsePayload ApplyNsl(List<String> waybills, String status_code, String nsl_id, HashMap<String, String> data) {
        ApplyNslRequestPayload body = RequestBuilder.ApplyNsl(waybills, status_code, nsl_id, data);

        Response response = PackageFlowRequests.ApplyNslApi(body, data);
        assertStatusCode(200, response);

        ApplyNslResponsePayload apiResponse = response.as(ApplyNslResponsePayload.class);
        ControllerHelper.checkPackageCurrentScanWithWait(waybills, data, nsl_id);
        return apiResponse;
    }


    public ArrayList<String> FetchWaybills(String Client_name, int count, HashMap<String, String> data1) {
        HashMap<String, String> data = new HashMap<>();
        data.put("cl", Client_name);
        data.put("count", Integer.toString(count));

        Response response = PackageFlowRequests.FetchWaybills(data, data1);
        assertStatusCode(200, response);
        FetchWaybillsResponse waybills_generated = response.as(FetchWaybillsResponse.class);

        ArrayList<String> waybills = new ArrayList<>();

        for (int i = 0; i < waybills_generated.wbns.size(); i++) {
            waybills.add(waybills_generated.wbns.get(i));
        }

        return waybills;
    }

    public ArrayList<String> cmuManifestApiB2BWithInternalChild(HashMap<String, String> data) {
        String json = null;
        CmuManifestApi body = RequestBuilder.ManifestB2BWithInternalChild(data);

        // setting up the body provided in data

//        body.shipments.get(0).setProductType(product_type);
//        body.shipments.get(0).setPaymentMode(payment_mode);

        String client = "regression_client";
        // if client name is also sent in data
        if (data.containsKey("client")) {
            client = data.get("client");
        }

        try {
            json = "data=" + Utilities.jsonObjectToString(body) + "&format=json";
        } catch (JsonProcessingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        Response response = PackageFlowRequests.cmuManifestShipment(json, client);
        assertStatusCode(200, response);
//        response.then().body("success", is(true));
//        response.then().body("packages.status", hasItem("Success"));

        CmuResponseApi apiResponse = response.as(CmuResponseApi.class);
        assertKeyValue("status", "Success", apiResponse.packages.get(0).status);
        assertKeyValue("success", true, apiResponse.success);

        ArrayList<String> waybills = new ArrayList<>();

        for (int i = 0; i < apiResponse.packages.size(); i++) {
            waybills.add(apiResponse.packages.get(i).waybill);
        }

        return waybills;
    }

    public String markRerveseShipmentDispatchApi(List<String> waybills, HashMap<String, String> data) {
        MarkDispatchRequestPayload body = RequestBuilder.markDispatchApiReqGen(waybills);
        if (data.containsKey("shipment_destination_center")) {
            body.setSl(data.get("shipment_destination_center"));
        }
        Response response = PackageFlowRequests.markShipmentDispatch(body, data);
        assertStatusCode(200, response);

        MarkRvpShipmentDispatchResponsePayload apiResponse = response.as(MarkRvpShipmentDispatchResponsePayload.class);
        assertKeyValue("status", true, apiResponse.meta.status);

        return body.dwbn;
    }

    public String markRerveseShipmentDispatchApi(String waybill, HashMap<String, String> data) {
        MarkDispatchRequestPayload body = RequestBuilder.markDispatchApiReqGen(Collections.singletonList(waybill));
        if (data.containsKey("shipment_destination_center")) {
            body.setSl(data.get("shipment_destination_center"));
        }
        Response response = PackageFlowRequests.markShipmentDispatch(body, data);
        assertStatusCode(200, response);

        MarkRvpShipmentDispatchResponsePayload apiResponse = response.as(MarkRvpShipmentDispatchResponsePayload.class);
        assertKeyValue("status", true, apiResponse.meta.status);

        return body.dwbn;
    }

    //Get Internal user token to call internal apis
    public String fetchInternalUserJwtTokenApi(String client) {
        client = client != null ? client : "internal";
        System.out.println("Internal clinet user " + client);

        return TokenManager.getToken(client);
    }

    // get authorization jwt token from apicontroller class jwtToken variable
    public String createNoDataUplApi(String token) {
        CreateNoDataUplApiRequestPayload body = RequestBuilder.createNoDataUplReqGen();
        Response response = PackageFlowRequests.createNoDataUpl(body, token);
        assertStatusCode(201, response);

        CreateNoDataUplApiResponsePayload apiResponse = response.as(CreateNoDataUplApiResponsePayload.class);
        assertKeyValue("error", "", apiResponse.error);

        return apiResponse.upl;
    }

    // get authorization jwt token from apicontroller class jwtToken variable
    public List<String> getNoDataUplShipment(String upl, String token) {
        HashMap<String, String> requestData = new HashMap<>();
        requestData.put("auth", token);
        requestData.put("upl", upl);
        return ControllerHelper.checkUPLStatusWithRetry(requestData);
    }

    // get authorization jwt token from apicontroller class jwtToken variable
    public void cmuV2ManifestNoDataShipment(List<String> waybills, HashMap<String, String> data, String token) throws IOException {
        CmuV2ManifestNoDataShipmentApiRequestPayload body = RequestBuilder.cmuV2ManifestNoDataShipmentReqGen(data);
        int index = 0;
        for (String waybill : waybills) {
            body.shipments.get(0).order_info.get(index).setWaybill(waybill);
            index++;
        }

        Response response = PackageFlowRequests.cmuV2ManifestNoDataShipment(body, token);
        assertStatusCode(200, response);

        CmuV2ManifestNoDataShipmentApiResponsePayload apiResponse = response
                .as(CmuV2ManifestNoDataShipmentApiResponsePayload.class);
        assertKeyValue("status", "Preload", apiResponse.status);
    }

    // <----------Different Enviorment api methods------->

    public void verifyPackageFetchInfoApiDiffEnv(String enviorment, String waybill, String statusType,
                                                 String scanStatus, String scanNsl, HashMap<String, String> data) {

        String client = "regression_client";
        // if client name is also sent in data
        if (data.containsKey("client")) {
            client = data.get("client");
        }
        Response response = PackageFlowRequests.getPackageDetails(enviorment, "ref_ids", waybill, client);
        assertStatusCode(200, response);

        PackageDetail[] pkg_Details = response.as(PackageDetail[].class);
        PackageDetail pkgDetails = pkg_Details[0];
        assertKeyValue("Status type", statusType, pkgDetails.cs.st);
        assertKeyValue("Scan status", scanStatus, pkgDetails.cs.ss);
        assertKeyValue("Scan nsl", scanNsl, pkgDetails.cs.nsl);
        logInfo("Scan REMARK : " + pkgDetails.cs.sr);
    }

    public PackageDetail fetchPackageInfoDiffEnv(String enviorment, String waybill, HashMap<String, String> data) {
        String client = "regression_client";
        // if client name is also sent in data
        if (data.containsKey("client")) {
            client = data.get("client");
        }

        Response response = PackageFlowRequests.getPackageDetails(enviorment, "ref_ids", waybill, client);
        assertStatusCode(200, response);
        PackageDetail[] pkg_Details = response.as(PackageDetail[].class);
        PackageDetail pkgDetails = pkg_Details[0];

        return pkgDetails;
    }

    public Example fetchPackageInfoTrackingEnv(String enviorment, Map<String, String> data) {
        Response response = PackageFlowRequests.getPackageTrackingDetails(enviorment, data);
        assertStatusCode(200, response);
        Example pkgDetails = response.as(Example.class);

        return pkgDetails;
    }

    public TrackError fetchPackageInfoNegativeCaseTrackingEnv(String enviorment, Map<String, String> data,
                                                              String Scenario) {
        Response response = PackageFlowRequests.getPackageTrackingDetails(enviorment, data);
        if (Scenario == "Scenario:: hit_service_with_100_wbns "
                || Scenario == "Scenario:: hit_service_with_empty_wbns ") {
            assertStatusCode(400, response);
        } else
            assertStatusCode(200, response);

        TrackError pkgDetails = response.as(TrackError.class);

        return pkgDetails;
    }

    public HQTrackError fetchPackageInfoNegativeCaseHqEnv(String enviorment, Map<String, String> data) {
        Response response = PackageFlowRequests.getPackageHqTrackingDetails(enviorment, data);
        assertStatusCode(200, response);
        HQTrackError pkgDetails = response.as(HQTrackError.class);

        return pkgDetails;
    }

    public HQTrack fetchPackageInfoHqEnv(String enviorment, Map<String, String> data) {
        Response response = PackageFlowRequests.getPackageHqTrackingDetails(enviorment, data);
        assertStatusCode(200, response);
        HQTrack pkgDetails = response.as(HQTrack.class);

        return pkgDetails;
    }

    public ArrayList<String> cmuManifestApiDiffEnv(String enviorment, HashMap<String, String> data) {
        String json = null;
        CmuManifestApi body = RequestBuilder.cmuManifestApiReqGen(data);

        // setting up the body provided in data

//        body.shipments.get(0).setProductType(product_type);
//        body.shipments.get(0).setPaymentMode(payment_mode);
        try {
            json = "data=" + Utilities.jsonObjectToString(body) + "&format=json";
        } catch (JsonProcessingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        String client = "regression_client";
        // if client name is also sent in data
        if (data.containsKey("client")) {
            client = data.get("client");
        }
        Response response = PackageFlowRequests.cmuManifestShipmentDiffEnv(enviorment, json, client);
        assertStatusCode(200, response);
//        response.then().body("success", is(true));
//        response.then().body("packages.status", hasItem("Success"));

        CmuResponseApi apiResponse = response.as(CmuResponseApi.class);
        assertKeyValue("status", "Success", apiResponse.packages.get(0).status);
        assertKeyValue("success", true, apiResponse.success);

        ArrayList<String> waybills = new ArrayList<>();

        for (int i = 0; i < apiResponse.packages.size(); i++) {
            waybills.add(apiResponse.packages.get(i).waybill);
        }

        return waybills;
    }

    public void fmOMSApiDiffEnv(String enviorment, String waybill, String eventCode, HashMap<String, String> data) {
        String json = null;
        HashMap<String, String> clData = new HashMap<>();
        clData = data;
        FMOMSRequestPayload body = RequestBuilder.fmOmsApiReqGen(eventCode, clData);
        try {
            json = Utilities.jsonObjectToString(body).replace("Waybill", waybill);
        } catch (JsonProcessingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        String client = "regression_client";
        if (clData.containsKey("client")) {
            client = clData.get("client");
        }

        Response response = PackageFlowRequests.fmOmsShipment(enviorment, JSON.parse(json), client);
        assertStatusCode(200, response);

        FMOMSResponsePayload apiResponse = response.as(FMOMSResponsePayload.class);
        assertKeyValue("success", true, apiResponse.success);
    }

    public void fmOMSApiDiffEnv(String enviorment, String waybill, String eventCode, String locationID,
                                HashMap<String, String> data) {
        String json = null;
        HashMap<String, String> clData = new HashMap<>();
        clData = data;
        FMOMSRequestPayload body = RequestBuilder.fmOmsApiReqGen(eventCode, clData);
        body.setLocation_id(locationID);
        try {
            json = Utilities.jsonObjectToString(body).replace("Waybill", waybill);
        } catch (JsonProcessingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        String client = "regression_client";
        if (clData.containsKey("client")) {
            client = clData.get("client");
        }

        Response response = PackageFlowRequests.fmOmsShipment(enviorment, JSON.parse(json), client);
        assertStatusCode(200, response);

        FMOMSResponsePayload apiResponse = response.as(FMOMSResponsePayload.class);
        assertKeyValue("success", true, apiResponse.success);
    }

    public void giApiDiffEnv(String enviorment, String waybill, String centre, HashMap<String, String> data) {
        GIRequestPayload body = RequestBuilder.giApiReqGen(Collections.singletonList(waybill));
        body.setCenter(centre);
        String client = "regression_client";
        if (data.containsKey("client")) {
            client = data.get("client");
        }
        ControllerHelper.doGIWithRetry(enviorment, body, client);
    }


    public void EditApiDiffEnv(String enviorment, String waybill, HashMap<String, String> data) {

        EditApiRequestPayload body = RequestBuilder.EditApiReqGen(waybill, data);

        Response response = PackageFlowRequests.EditApi(enviorment, body);
        assertStatusCode(200, response);

        EditApiResponsePayload apiResponse = response.as(EditApiResponsePayload.class);

        assertKeyValue("status", "true", apiResponse.status);
    }

    public void QcWtApiDiffEnv(String enviorment, String waybill, double wt, String wtr, HashMap<String, String> data) {
        QcWtPayload body = RequestBuilder.QcWtApiReqGen(waybill, wt, wtr);

        String client = "regression_client";
        if (data.containsKey("client")) {
            client = data.get("client");
        }
        Response response = PackageFlowRequests.updateQcWt(enviorment, body, client);
        assertStatusCode(200, response);

        QcWtResponsePayload apiResponse = response.as(QcWtResponsePayload.class);
        assertKeyValue("success", true, apiResponse.success);
    }

    public ArrayList<String> cmuManifestMPSWithChildPayload(HashMap<String, String> data) {
        String json = null;
        CmuManifestApi body = RequestBuilder.ManifestMPSWithChildPayload(data);

        String client = "regression_client";
        // if client name is also sent in data
        if (data.containsKey("client")) {
            client = data.get("client");
        }

        // setting up the body provided in data

        // body.shipments.get(0).setProductType(product_type);
        // body.shipments.get(0).setPaymentMode(payment_mode);
        try {
            json = "data=" + Utilities.jsonObjectToString(body) + "&format=json";
        } catch (JsonProcessingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        Response response = null;
        if (data.containsKey("enviorment")) {
            response = PackageFlowRequests.cmuManifestShipmentDiffEnv(data.get("enviorment"), json, client);
        } else {
            response = PackageFlowRequests.cmuManifestShipment(json, client);
        }
        assertStatusCode(200, response);
        // response.then().body("success", is(true));
        // response.then().body("packages.status", hasItem("Success"));

        CmuResponseApi apiResponse = response.as(CmuResponseApi.class);
        assertKeyValue("status", "Success", apiResponse.packages.get(0).status);
        assertKeyValue("success", true, apiResponse.success);

        ArrayList<String> waybills = new ArrayList<>();

        for (int i = 0; i < apiResponse.packages.size(); i++) {
            waybills.add(apiResponse.packages.get(i).waybill);
        }

        return waybills;
    }

    public String createPartiallyManifestedUplApi(String token) {
        CreateNoDataUplApiRequestPayload body = RequestBuilder.createPartiallyManifestedUplReqGen();
        Response response = PackageFlowRequests.createNoDataUpl(body, token);
        assertStatusCode(201, response);

        CreateNoDataUplApiResponsePayload apiResponse = response.as(CreateNoDataUplApiResponsePayload.class);
        assertKeyValue("error", "", apiResponse.error);

        return apiResponse.upl;
    }

    public ArrayList<String> cmuManifestWithCwhApi(HashMap<String, String> data) throws IOException {
        String json = null;
        CmuManifestRequestPayload body = RequestBuilder.cmuManifestWithCwhShipmentReqGen(data);
        String client = "regression_client";
        // if client name is also sent in data
        if (data.containsKey("client")) {
            client = data.get("client");
        }

        if (data.containsKey("pickup_location")) {
            body.pickup_location.setName(data.get("pickup_location"));
        }

        try {
            json = "data=" + Utilities.jsonObjectToString(body) + "&format=json";
        } catch (JsonProcessingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        Response response = PackageFlowRequests.cmuManifestShipment(json, client);
        assertStatusCode(200, response);

        CmuResponsePayload apiResponse = response.as(CmuResponsePayload.class);
        assertKeyValue("status", "Success", apiResponse.packages.get(0).status);
        assertKeyValue("success", true, apiResponse.success);

        ArrayList<String> waybills = new ArrayList<>();
        for (int i = 0; i < apiResponse.packages.size(); i++) {
            waybills.add(apiResponse.packages.get(i).waybill);
        }

        return waybills;
    }

    public PackageDetail2 fetchPackageInfo2(String waybill, HashMap<String, String> data) {
        Response response = PackageFlowRequests.getPackageDetails("ref_ids", waybill, data);
        assertStatusCode(200, response);
        PackageDetail2[] pkg_Details = response.as(PackageDetail2[].class);
        PackageDetail2 pkgDetails = pkg_Details[0];

        return pkgDetails;
    }

    public void verifyPackageFetchInfoApi2(String waybill, String statusType, String scanStatus, String scanNsl,
                                           HashMap<String, String> data) {
        Response response = PackageFlowRequests.getPackageDetails("ref_ids", waybill, data);
        assertStatusCode(200, response);

        PackageDetail[] pkg_Details = response.as(PackageDetail[].class);
        PackageDetail pkgDetails = pkg_Details[0];
        assertKeyValue("Status type", statusType, pkgDetails.cs.st);
        assertKeyValue("Scan status", scanStatus, pkgDetails.cs.ss);
        assertKeyValue("Scan nsl", scanNsl, pkgDetails.cs.nsl);
        logInfo("Scan REMARK : " + pkgDetails.cs.sr);
    }

    public String createNoDataUplApi(HashMap<String, String> clData, String token) {
        CreateNoDataUplApiRequestPayload body = RequestBuilder.createNoDataUplReqGen(clData);
        Response response = PackageFlowRequests.createNoDataUpl(body, token);
        assertStatusCode(201, response);

        CreateNoDataUplApiResponsePayload apiResponse = response.as(CreateNoDataUplApiResponsePayload.class);
        assertKeyValue("error", "", apiResponse.error);

        return apiResponse.upl;
    }

    public String createPartiallyManifestedUplApi(HashMap<String, String> clData, String token) {
        CreateNoDataUplApiRequestPayload body = RequestBuilder.createPartiallyManifestedUplReqGen(clData);
        Response response = PackageFlowRequests.createNoDataUpl(body, token);
        assertStatusCode(201, response);

        CreateNoDataUplApiResponsePayload apiResponse = response.as(CreateNoDataUplApiResponsePayload.class);
        assertKeyValue("error", "", apiResponse.error);

        return apiResponse.upl;
    }

    public void ApplyNslDiffEnv(String enviorment, List<String> waybills, String status_code, String nsl_id,
                                HashMap<String, String> data) {

        ApplyNslRequestPayload body = RequestBuilder.ApplyNsl(waybills, status_code, nsl_id, data);

        Response response = PackageFlowRequests.ApplyNslApi(enviorment, body, data);
        assertStatusCode(200, response);

        ApplyNslResponsePayload apiResponse = response.as(ApplyNslResponsePayload.class);
        assertKeyValue("success", true, apiResponse.isSuccess());

    }

    public void verifyFetchClientDetailsApi(String clientName) {
        Response response = PackageFlowRequests.getClientDetails("client", clientName);
        assertStatusCode(200, response);

        FetchClientDetailsResponsePayload apiResponse = response.as(FetchClientDetailsResponsePayload.class);
        assertKeyValue("success", true, apiResponse.success);
    }

    public void verifyDispatchFreezeApi(String waybill) {
        DispatchFreezeRequestPayload request = RequestBuilder
                .dispatchFreezeApiReqGen(Collections.singletonList(waybill));
        Response response = PackageFlowRequests.dispatchFreeze(request, "dwbn",
                Utilities.generateUniqueEntity("Dispatch"));
        assertStatusCode(200, response);

        DispatchFreezeResponsePayload apiResponse = response.as(DispatchFreezeResponsePayload.class);
        assertKeyValue("success", true, apiResponse.success);
    }

    public void verifyMarkPendingApi(String waybill) {
        MarkPendingRequestPayload request = RequestBuilder.markPendingApiReqGen(Collections.singletonList(waybill));
        Response response = PackageFlowRequests.markPending(request, "dwbn",
                Utilities.generateUniqueEntity("Dispatch"));
        assertStatusCode(200, response);

        MarkPendingResponsePayload apiResponse = response.as(MarkPendingResponsePayload.class);
        assertKeyValue("success", true, apiResponse.success);
    }

    public void verifyMarkDeliveredApi(String waybill) {
        MarkDeliveredRequestPayload request = RequestBuilder.markDeliveredApiReqGen(Collections.singletonList(waybill));
        Response response = PackageFlowRequests.markDelivered(request, "dwbn",
                Utilities.generateUniqueEntity("Dispatch"));
        assertStatusCode(200, response);

        MarkDeliveredResponsePayload apiResponse = response.as(MarkDeliveredResponsePayload.class);
        assertKeyValue("success", true, apiResponse.success);
    }

    public void verifyFetchBagMatrixApi(String sl) {
        FetchBagMatrixRequestPayload request = RequestBuilder.fetchMagMatrixApiReqGen(sl);
        Response response = PackageFlowRequests.fetchBagMatrix(request);
        assertStatusCode(200, response);

        FetchBagMatrixResponsePayload apiResponse = response.as(FetchBagMatrixResponsePayload.class);
        assertKeyValue("success", true, apiResponse.success);
    }

    public void verifyBulkEditNdr(String waybill, String action, HashMap<String, String> data) {
        String json = null;
        BulkNdrRequestPayload body = RequestBuilder.bulkEditNdrApiReqGen(action);
        try {
            json = Utilities.jsonObjectToString(body).replace("Waybill", waybill);
        } catch (JsonProcessingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        Response response = PackageFlowRequests.bulkNdrEdit(JSON.parse(json), data);
        assertStatusCode(200, response);

        BulkNdrResponsePayload apiResponse = response.as(BulkNdrResponsePayload.class);
        assertKeyValue("success", true, apiResponse.success);
    }

    public String bagv3Api(String waybill, HashMap<String, String> data) {
        BagV3RequestPayload body = RequestBuilder.bagV3ApiReqGen(data);
        try {
            requestPayload = JSON.parse(Utilities.jsonObjectToString(body).replace("waybill", waybill));
        } catch (JsonProcessingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        Response response = PackageFlowRequests.bagV3Shipment(requestPayload, data);
        assertStatusCode(200, response);

        BagV3ResponsePayload apiResponse = response.as(BagV3ResponsePayload.class);
        assertKeyValue("success", true, apiResponse.success);

        return apiResponse.data.get(0).bs;
    }

    public void verifyBagFetchInfoApi(String bagId, String scanStatus, String scanAct, String pid,
                                      HashMap<String, String> data) {
        Response response = PackageFlowRequests.getBagDetails("ref_ids", bagId, data);
        assertStatusCode(200, response);

        BagDetails bagDetails = response.as(BagDetails.class);
        assertKeyValue("Bag scan status", scanStatus, bagDetails.bags.get(0).cs.ss);
        assertKeyValue("Bag scan act", scanAct, bagDetails.bags.get(0).cs.act);
        assertKeyValue("Bag pid", pid, (String) bagDetails.bags.get(0).pid);
        logInfo("Bag Scan REMARK : " + bagDetails.bags.get(0).cs.sr);
    }

    public String bagAddToTripApi(String bagId, HashMap<String, String> data) {
        BagAddToTripRequestPayload body = RequestBuilder.bagAddToTripApiReqGen(bagId);
        if (data.containsKey("ocid")) {
            body.setSlid(data.get("ocid"));
        }
        if (data.containsKey("cnid")) {
            body.setNtcid(data.get("cnid"));
        }

        Response response = PackageFlowRequests.bagAddToTrip(body, data);
        assertStatusCode(200, response);

        BagAddToTripResponsePayload apiResponse = response.as(BagAddToTripResponsePayload.class);
        assertKeyValue("success", true, apiResponse.success);

        return body.trid;
    }

    public void tripIncomingApi(String tripId, HashMap<String, String> data) {
        TripIncomingRequestPayload body = RequestBuilder.tripIncomingApiReqGen(tripId);
        if (data.containsKey("cnid")) {
            body.setSlid(data.get("cnid"));
        }

        Response response = PackageFlowRequests.tripIncoming(body, data);
        assertStatusCode(200, response);

        TripIncomingResponsePayload apiResponse = response.as(TripIncomingResponsePayload.class);
        assertKeyValue("success", true, apiResponse.success);
    }

    public void bagIncomingApi(String bagId, HashMap<String, String> data) {
        BagIncomingRequestPayload body = RequestBuilder.bagIncomingApiReqGen(bagId);
        if (data.containsKey("cnid")) {
            body.setSlid(data.get("cnid"));
        }

        Response response = PackageFlowRequests.bagIncoming(body, data);
        assertStatusCode(200, response);

        BagIncomingResponsePayload apiResponse = response.as(BagIncomingResponsePayload.class);
        assertKeyValue("success", true, apiResponse.getSuccess());
    }

    public void verifyPackageFetchInfoApi(ArrayList<String> waybills, String statusType, String scanStatus,
                                          String scanNsl, HashMap<String, String> data) {
        for (String waybill : waybills) {
            Response response = PackageFlowRequests.getPackageDetails("ref_ids", waybill, data);
            assertStatusCode(200, response);

            PackageDetail[] pkg_Details = response.as(PackageDetail[].class);
            PackageDetail pkgDetails = pkg_Details[0];
            assertKeyValue("Status type", statusType, pkgDetails.cs.st);
            assertKeyValue("Scan status", scanStatus, pkgDetails.cs.ss);
            assertKeyValue("Scan nsl", scanNsl, pkgDetails.cs.nsl);
            logInfo("Scan REMARK : " + pkgDetails.cs.sr);
        }
    }

    public void verifyBagFetchInfoApi(ArrayList<String> bagIds, String scanStatus, String scanAct,
                                      HashMap<String, String> data) {
        for (String bagId : bagIds) {
            Response response = PackageFlowRequests.getBagDetails("ref_ids", bagId, data);
            assertStatusCode(200, response);

            BagDetails bagDetails = response.as(BagDetails.class);
            assertKeyValue("Bag scan status", scanStatus, bagDetails.bags.get(0).cs.ss);
            try {
                if (!bagDetails.bags.get(0).cs.act.isEmpty()) {
                    assertKeyValue("Bag scan act", scanAct, bagDetails.bags.get(0).cs.act);
                }
            } catch (Exception e) {
                logInfo("cs.act is not present on bag");
            }
            logInfo("Bag Scan REMARK : " + bagDetails.bags.get(0).cs.sr);
        }
    }

    public void fmOMSApi(ArrayList<String> waybills, String eventCode, HashMap<String, String> data) {
        String json = null;
        FMOMSRequestPayload body = RequestBuilder.fmOmsApiReqGen(eventCode, data);
        for (String waybill : waybills) {
            try {
                json = Utilities.jsonObjectToString(body).replace("Waybill", waybill);
            } catch (JsonProcessingException e) {
                throw new RuntimeException("Error while parsing json");
            }
            Response response = PackageFlowRequests.fmOmsShipment(JSON.parse(json), data);
            assertStatusCode(200, response);

            FMOMSResponsePayload apiResponse = response.as(FMOMSResponsePayload.class);
            assertKeyValue("success", true, apiResponse.success);
        }
    }

    public void giApi(ArrayList<String> waybills, String centre, HashMap<String, String> data) {
        GIRequestPayload body = RequestBuilder.giApiReqGen(waybills);
        body.setCenter(centre);
        ControllerHelper.doGIWithRetry(body, data);
    }


    public ArrayList<String> bagv3Api(ArrayList<String> waybills, HashMap<String, String> data) {
        ArrayList<String> bagIds = new ArrayList<>();
        for (String waybill : waybills) {
            BagV3RequestPayload body = RequestBuilder.bagV3ApiReqGen(waybill);
            try {
                requestPayload = JSON.parse(Utilities.jsonObjectToString(body).replace("waybill", waybill));
            } catch (JsonProcessingException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            Response response = PackageFlowRequests.bagV3Shipment(requestPayload, data);
            assertStatusCode(200, response);

            BagV3ResponsePayload apiResponse = response.as(BagV3ResponsePayload.class);
            assertKeyValue("success", true, apiResponse.success);

            bagIds.add(apiResponse.data.get(0).bs);
        }

        return bagIds;
    }

    public ArrayList<String> bagAddToTripApi(ArrayList<String> bagIds, HashMap<String, String> data) {
        ArrayList<String> tripIds = new ArrayList<>();
        for (String bagId : bagIds) {
            BagAddToTripRequestPayload body = RequestBuilder.bagAddToTripApiReqGen(bagId);
            Response response = PackageFlowRequests.bagAddToTrip(body, data);
            assertStatusCode(200, response);

            BagAddToTripResponsePayload apiResponse = response.as(BagAddToTripResponsePayload.class);
            assertKeyValue("success", true, apiResponse.success);

            tripIds.add(body.trid);
        }

        return tripIds;
    }

    public void tripIncomingApi(ArrayList<String> tripIds, HashMap<String, String> data) {
        for (String tripId : tripIds) {
            TripIncomingRequestPayload body = RequestBuilder.tripIncomingApiReqGen(tripId);
            Response response = PackageFlowRequests.tripIncoming(body, data);
            assertStatusCode(200, response);

            TripIncomingResponsePayload apiResponse = response.as(TripIncomingResponsePayload.class);
            assertKeyValue("success", true, apiResponse.success);
        }
    }

    public void bagIncomingApi(ArrayList<String> bagIds, HashMap<String, String> data) {
        for (String bagId : bagIds) {
            BagIncomingRequestPayload body = RequestBuilder.bagIncomingApiReqGen(bagId);
            Response response = PackageFlowRequests.bagIncoming(body, data);
            assertStatusCode(200, response);

            BagIncomingResponsePayload apiResponse = response.as(BagIncomingResponsePayload.class);
            assertKeyValue("success", true, apiResponse.getSuccess());
        }
    }

    public String markShipmentDispatchApi(ArrayList<String> waybills, HashMap<String, String> data) {
        MarkDispatchRequestPayload body = RequestBuilder.markDispatchApiReqGen(waybills);
        if (data.containsKey("shipment_destination_center")) {
            body.setSl(data.get("shipment_destination_center"));
        }
        Response response = PackageFlowRequests.markShipmentDispatch(body, data);
        assertStatusCode(200, response);

        MpsMarkDispatchResponsePayload apiResponse = response.as(MpsMarkDispatchResponsePayload.class);
        assertKeyValue("status", true, apiResponse.meta.status);

        return body.dwbn;
    }

    public void lmUpdateHQShipmentApi(ArrayList<String> waybills, String action, HashMap<String, String> data) {
        for (String waybill : waybills) {
            LMUpdateHQShipmentRequestPayload body = RequestBuilder.lmUpdateHQShipmentApiReqGen();
            if (data.containsKey("shipment_destination_center")) {
                body.payload.data.waybill.setSl(data.get("shipment_destination_center"));
            }
            if (action.equalsIgnoreCase("Delivered")) {
                body.payload.data.waybill.setSt("DL");
                body.payload.data.waybill.setSs("Delivered");
                body.payload.data.waybill.setNsl_code("EOD-38");
            } else if (action.equalsIgnoreCase("PickedUp")) {
                body.payload.data.waybill.setSt("PU");
                body.payload.data.waybill.setSs("In Transit");
                body.payload.data.waybill.setNsl_code("EOD-77");
            } else if (action.equalsIgnoreCase("Cancelled")) {
                body.payload.data.waybill.setSt("CN");
                body.payload.data.waybill.setSs("Cancelled");
                body.payload.data.waybill.setNsl_code("EOD-108");
            } else if (action.equalsIgnoreCase("REPL RT")) {
                body.payload.data.waybill.setSt("RT");
                body.payload.data.waybill.setSs("In Transit");
                body.payload.data.waybill.setNsl_code("EOD-50");
            } else if (action.equalsIgnoreCase("REPL PU")) {
                body.payload.data.waybill.setSt("PU");
                body.payload.data.waybill.setSs("In Transit");
                body.payload.data.waybill.setNsl_code("EOD-77");
            } else if (action.equalsIgnoreCase("docu_pending")) {
                body.payload.data.waybill.setSt("UD");
                body.payload.data.waybill.setSs("pending");
                body.payload.data.waybill.setNsl_code("DLYLH-145");
            } else if (action.equalsIgnoreCase("docu_inTransit")) {
                body.payload.data.waybill.setSt("UD");
                body.payload.data.waybill.setSs("In Transit");
                body.payload.data.waybill.setNsl_code("DLYLH-145");
            } else if (action.equalsIgnoreCase("docu_returned")) {
                body.payload.data.waybill.setSt("RT");
                body.payload.data.waybill.setSs("In Transit");
                body.payload.data.waybill.setNsl_code("DLYLH-145");
            } else if (action.equalsIgnoreCase("docu_manifested")) {
                body.payload.data.waybill.setSt("UD");
                body.payload.data.waybill.setSs("Manifested");
                body.payload.data.waybill.setNsl_code("DLYLH-145");
            } else if (action.equalsIgnoreCase("docu_delivered")) {
                body.payload.data.waybill.setSt("DL");
                body.payload.data.waybill.setSs("Delivered");
                body.payload.data.waybill.setNsl_code("DLYLH-145");
            } else if (action.equalsIgnoreCase("docu_pickupPending")) {
                body.payload.data.waybill.setSt("PP");
                body.payload.data.waybill.setSs("Open");
                body.payload.data.waybill.setNsl_code("DLYLH-145");
            } else if (action.equalsIgnoreCase("docu_pickupPending_Scheduled")) {
                body.payload.data.waybill.setSt("PP");
                body.payload.data.waybill.setSs("Scheduled");
                body.payload.data.waybill.setNsl_code("DLYLH-145");
            } else if (action.equalsIgnoreCase("docu_PickedUp")) {
                body.payload.data.waybill.setSt("PU");
                body.payload.data.waybill.setSs("In transit");
                body.payload.data.waybill.setNsl_code("DLYLH-145");
            } else if (action.equalsIgnoreCase("docu_cancelled")) {
                body.payload.data.waybill.setSt("CN");
                body.payload.data.waybill.setSs("cancelled");
                body.payload.data.waybill.setNsl_code("DLYLH-145");
            } else if (action.equalsIgnoreCase("zero_liability_DL")) {
                body.payload.data.waybill.setSt("DL");
                body.payload.data.waybill.setSs("Delivered");
                body.payload.data.waybill.setNsl_code("DTUP-ZL");
            } else if (action.equalsIgnoreCase("zero_liability_UD")) {
                body.payload.data.waybill.setSt("UD");
                body.payload.data.waybill.setSs("Undelivered");
                body.payload.data.waybill.setNsl_code("DTUP-ZL");
            } else if (data.containsKey("st") && action.equalsIgnoreCase("")) {
                body.payload.data.waybill.setSt(data.get("st"));
                body.payload.data.waybill.setSs(data.get("ss"));
                body.payload.data.waybill.setNsl_code(data.get("nsl_code"));

            }
            try {
                requestPayload = JSON.parse(Utilities.jsonObjectToString(body).replace("Waybill", waybill));
            } catch (JsonProcessingException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            Response response = PackageFlowRequests.lmUpdateHqShipment(requestPayload, data);
            assertStatusCode(200, response);

            LMUpdateHQShipmentResponePayload apiResponse = response.as(LMUpdateHQShipmentResponePayload.class);
            assertKeyValue("status", "Completed", apiResponse.status);
        }
    }

    public void unsetShipmentDispatchIdApi(ArrayList<String> waybills, String dispatchId,
                                           HashMap<String, String> data) {
        UnsetShipmentDispatchIdRequestPayload body = RequestBuilder.unsetShipmentDispatchIdApiReqGen();
        body.payload.setWbns(waybills);
        body.payload.setDispatch_id(dispatchId);
        Response response = PackageFlowRequests.unsetShipmentDispatchId(body, data);
        assertStatusCode(200, response);

        UnsetShipmentDispatchIdResponsePayload apiResponse = response.as(UnsetShipmentDispatchIdResponsePayload.class);
        assertKeyValue("success", true, apiResponse.success);

    }

    public void verifyKinkoInvoiceCharge(HashMap<String, String> data) {
        Response response = PackageFlowRequests.kinkoInvoiceCharges(data);
        assertStatusCode(200, response);

        KinkoInvoiceChargesResponsePayloadJava apiResponse = response.as(KinkoInvoiceChargesResponsePayloadJava.class);
        assertIfNotNull("response", apiResponse.response);
    }

    public void verifyClientUpdate() {
        ClientUpdateRequestPayloadJava request = RequestBuilder.clientUpdateReqGen();
        Response response = PackageFlowRequests.clientUpdate(request);
        assertStatusCode(200, response);

        ClientUpdateResponsePayloadJava apiResponse = response.as(ClientUpdateResponsePayloadJava.class);
        assertKeyValue("success", true, apiResponse.success);
    }

    public void verifyFetchClienWbn(HashMap<String, String> data) {
        Response response = PackageFlowRequests.fetchClientWbn(data);
        assertStatusCode(200, response);

        FetchClientWbnJava apiResponse = response.as(FetchClientWbnJava.class);
        assertIfNotNull("response", apiResponse);
    }

    public void verifyRemoveEwbn(String ewbn, String user) {
        RemoveEwbnRequestPayload request = RequestBuilder.removeEwbnApiReqGen(ewbn, user);
        Response response = PackageFlowRequests.removeEwbn(request);
        assertStatusCode(200, response);
    }

    public void verifyPackageStatus(String waybill) {
        Response response = PackageFlowRequests.packageStatus("waybill", waybill);
        assertStatusCode(200, response);

        PackageStatusApiResponsePayloadJava apiResponse = response.as(PackageStatusApiResponsePayloadJava.class);
        assertIfNotNull("Status", apiResponse.shipmentData.get(0).shipment.status);
    }

    public void verifyPackageAction(String waybill) {
        Response response = PackageFlowRequests.packageAction("trackingNumber", waybill);
        assertStatusCode(200, response);

        PackageActionApiResponsePayloadJava apiResponse = response.as(PackageActionApiResponsePayloadJava.class);
        assertKeyValue("status", "true", apiResponse.actionsForPackageResponse.status.status);
    }

    public void verifyBagCustodyScan() {
        BagCustodyScanRequestPayload request = RequestBuilder.bagCustodyScanApiReqGen();
        Response response = PackageFlowRequests.bagCustodyScan(request);
        assertStatusCode(200, response);

        BagCustodyScanResponsePayload apiResponse = response.as(BagCustodyScanResponsePayload.class);
        assertKeyValue("success", true, apiResponse.success);
    }

    public String bagv2Api(String waybill, HashMap<String, String> data) {
        CreateBagV2ApiRequestPayload body = RequestBuilder.bagV2ApiReqGen(data);
        try {
            requestPayload = JSON.parse(Utilities.jsonObjectToString(body).replace("waybill", waybill));
        } catch (JsonProcessingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        Response response = PackageFlowRequests.bagV2Shipment(requestPayload, data);
        assertStatusCode(200, response);

        CreateBagV2ApiResponsePayload apiResponse = response.as(CreateBagV2ApiResponsePayload.class);
        assertKeyValue("success", true, apiResponse.success);

        return apiResponse.data.get(0).bs;
    }

    public String bagv2Api(String waybill) {
        CreateBagV2ApiRequestPayload body = RequestBuilder.bagV2ApiReqGen(waybill);
        try {
            requestPayload = JSON.parse(Utilities.jsonObjectToString(body).replace("waybill", waybill));
        } catch (JsonProcessingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        Response response = PackageFlowRequests.bagV2Shipment(requestPayload);
        assertStatusCode(200, response);

        CreateBagV2ApiResponsePayload apiResponse = response.as(CreateBagV2ApiResponsePayload.class);
        assertKeyValue("success", true, apiResponse.success);

        return apiResponse.data.get(0).bs;
    }

    public void verifyAutoBag(String waybill, String centre) {
        AutobagRequestPayload request = RequestBuilder.autobagApiReqGen(waybill);
        request.setCn(centre);
        Response response = PackageFlowRequests.autobag(request);
        assertStatusCode(200, response);

        AutobagResponsePayload apiResponse = response.as(AutobagResponsePayload.class);
        assertKeyValue("status", 1, apiResponse.status);
    }

    public void verifyFmIncoming(String waybill, String centre) {
        FmApiIncomingRequestPayload request = RequestBuilder.fmApiIncomingReqGen(waybill);
        request.setCenter(centre);
        Response response = PackageFlowRequests.fmApiIncoming(request);
        assertStatusCode(200, response);
    }

    public void verifyClientCreateUpdate(String clientName, String productType) {
        CreateUpdateClientRequestPayload request = RequestBuilder.clientCreateUpdateReqGen(clientName, productType);
        Response response = PackageFlowRequests.clientCreateUpdate(request);
        assertStatusCode(200, response);

        CreateUpdateClientResponsePayload apiResponse = response.as(CreateUpdateClientResponsePayload.class);
        assertKeyValue("success", true, apiResponse.success);
    }

    public void verifyPkgCount() {
        Response response = PackageFlowRequests.pkgCount();
        assertStatusCode(200, response);

        PackageCountResponsePayload apiResponse = response.as(PackageCountResponsePayload.class);
        assertIfNotNull("response", apiResponse);
    }

    public void verifyFetchDC(String city) {
        Response response = PackageFlowRequests.fetchDC("city", city);
        assertStatusCode(200, response);
    }

    public void verifyFetchCity(String state) {
        Response response = PackageFlowRequests.fetchCity("state", state);
        assertStatusCode(200, response);
    }

    public void verifyPincodeInfo(String pincode) {
        Response response = PackageFlowRequests.fetchPincodeInfo("filter_codes", pincode);
        assertStatusCode(200, response);

        PincodeInfoResponsePayload apiResponse = response.as(PincodeInfoResponsePayload.class);
        assertIfNotNull("Postal Code", apiResponse.deliveryCodes.get(0).postalCode);
    }

    public void verifyPackingSlip(String waybil, HashMap<String, String> data) {
        Response response = PackageFlowRequests.fetchPackingSlip("wbns", waybil, data);
        assertStatusCode(200, response);

        PackingSlipResponsePayload apiResponse = response.as(PackingSlipResponsePayload.class);
        assertKeyValue("packages_found", 1, apiResponse.getPackagesFound());
    }

    public void verifyPackingSlip(String waybill) {
        Response response = PackageFlowRequests.fetchPackingSlip("wbns", waybill);
        assertStatusCode(200, response);

        PackingSlipResponsePayload apiResponse = response.as(PackingSlipResponsePayload.class);
        assertKeyValue("packages_found", 1, apiResponse.getPackagesFound());
    }

    public void verifyPackageCancel(String waybill) {
        PackageCancelRequestPayload request = RequestBuilder.packageCancelReqGen(waybill);
        Response response = PackageFlowRequests.packageCancel(request);
        assertStatusCode(200, response);

        PackageCancelResponsePayload apiResponse = response.as(PackageCancelResponsePayload.class);
        assertKeyValue("status", true, apiResponse.status);
    }

    public void verifyFetchDcCenter() {
        Response response = PackageFlowRequests.fetchDcCenter();
        assertStatusCode(200, response);

        FetchDcCenterResponsePayload apiResponse = response.as(FetchDcCenterResponsePayload.class);
        assertIfNotNull("response", apiResponse);
    }

    public void verifyPackageUpdate(String waybill) {
        PackageUpdateRequestPayload request = RequestBuilder.packageUpdateReqGen(waybill);
        Response response = PackageFlowRequests.updatePackage(request);
        assertStatusCode(200, response);

        PackageUpdateResponsePayload apiResponse = response.as(PackageUpdateResponsePayload.class);
        assertKeyValue("success", true, apiResponse.success);
    }

    public void verifyLocationAssociate(String waybill, String scanTime) {
        LocationAssociateRequestPayload request = RequestBuilder.locationAssociateReqGen(waybill, scanTime);
        Response response = PackageFlowRequests.locationAssociate(request);
        assertStatusCode(200, response);

        LocationAssociateResponsePayload apiResponse = response.as(LocationAssociateResponsePayload.class);
        assertKeyValue("success", true, apiResponse.success);
    }

    public void verifyKinkoFetch(HashMap<String, String> data) {
        Response response = PackageFlowRequests.kinkoFetch(data);
        assertStatusCode(200, response);
    }

    public void verifyClientDetails(String token) {
        Response response = PackageFlowRequests.clientDetails(token);
        assertStatusCode(200, response);

        ClientDetailsResponsePayload apiResponse = response.as(ClientDetailsResponsePayload.class);
        assertKeyValue("success", true, apiResponse.success);
    }

    public ClientDetailsResponsePayload verifyClientDetailsByClientName(String clientName) {
        Response response = PackageFlowRequests.clientDetailsByClientName(clientName);
        assertStatusCode(200, response);

        ClientDetailsResponsePayload apiResponse = response.as(ClientDetailsResponsePayload.class);
        assertKeyValue("success", true, apiResponse.success);
        return apiResponse;
    }

    public void verifyStateInfo() {
        Response response = PackageFlowRequests.stateInfo();
        assertStatusCode(200, response);
    }

    public void verifyBagInfo(String BagID) {
        BagInfoRequestPayload request = RequestBuilder.bagInfoReqGen(BagID);
        Response response = PackageFlowRequests.bagInfo(request);
        assertStatusCode(200, response);
    }

    public void verifyNewFmRequest() {
        NewFmRequestPayload request = RequestBuilder.newFmReqGen();
        Response response = PackageFlowRequests.newFmRequest(request);
        assertStatusCode(201, response);

        NewFmResponsePayload apiResponse = response.as(NewFmResponsePayload.class);
        assertKeyValue("status", true, apiResponse.status);
    }

    public ArrayList<String> verifyCmuPush(HashMap<String, String> data) {
        String json = null;
        CmuPushRequestPayload request = RequestBuilder.cmuPushReqGen(data);
        String client = "regression_client";
        // if client name is also sent in data
        if (data.containsKey("client")) {
            client = data.get("client");
        }

        try {
            json = "data=" + Utilities.jsonObjectToString(request) + "&format=json";
        } catch (JsonProcessingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        Response response = PackageFlowRequests.cmuPush(json, client);

        CmuPushResponsePayload apiResponse = response.as(CmuPushResponsePayload.class);
        assertKeyValue("status", "Success", apiResponse.packages.get(0).status);

        ArrayList<String> waybills = new ArrayList<>();

        for (int i = 0; i < apiResponse.packages.size(); i++) {
            waybills.add(apiResponse.packages.get(i).waybill);
        }

        return waybills;
    }

    public void verifySelfCollect(String waybill, String sl) {
        SelfCollectApiRequestPayload request = RequestBuilder.selfCollectReqGen(waybill, sl);
        try {
            requestPayload = JSON.parse(Utilities.jsonObjectToString(request).replace("waybill", waybill));
        } catch (JsonProcessingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        Response response = PackageFlowRequests.selfCollect(requestPayload);
        assertStatusCode(200, response);

        SelfCollectApiResponsePayload apiResponse = response.as(SelfCollectApiResponsePayload.class);
        assertKeyValue("status", true, apiResponse.meta.status);
    }

    public void verifyLocationDissociate(String waybill, String scanTime) {
        LocationDissociateRequestPayload request = RequestBuilder.locationDissociateReqGen(waybill, scanTime);
        Response response = PackageFlowRequests.locationDissociate(request);
        assertStatusCode(200, response);

        LocationDissociateResponsePayload apiResponse = response.as(LocationDissociateResponsePayload.class);
        assertKeyValue("success", true, apiResponse.success);
    }

    public void verifyPackageDetails(HashMap<String, String> data) {
        String bearer = fetchUserJwtTokenApi(null);
        Response response = PackageFlowRequests.fetchPackageDetails(data, bearer);
        assertStatusCode(200, response);
    }

    public void verifyBagCalc(String bagId, HashMap<String, String> data) {
        Response response = PackageFlowRequests.fetchBagCalc("ref_ids", bagId, data);
        assertStatusCode(200, response);

        BagCalcResponsePayload apiResponse = response.as(BagCalcResponsePayload.class);
        assertKeyValue("success", true, apiResponse.success);
    }

    public void verifyBagCalc(String bagId) {
        Response response = PackageFlowRequests.fetchBagCalc("ref_ids", bagId);
        assertStatusCode(200, response);

        BagCalcResponsePayload apiResponse = response.as(BagCalcResponsePayload.class);
        assertKeyValue("success", true, apiResponse.success);
    }

    public void verifyUpdatedBagMatrix() {
        UpdatedBagmatrixRequestPayload request = RequestBuilder.updatedBagmatrixReqGen();
        Response response = PackageFlowRequests.updatedBagmatrix(request);
        assertStatusCode(200, response);

        UpdatedBagmatrixResponsePayload apiResponse = response.as(UpdatedBagmatrixResponsePayload.class);
        assertKeyValue("success", true, apiResponse.success);
    }

    public void verifyEditPhone(String wbn) {
        EditPhoneRequestPayload request = RequestBuilder.editPhoneReqGen(wbn);
        Response response = PackageFlowRequests.editPhone(request);
        assertStatusCode(201, response);

        EditPhoneResponsePayload apiResponse = response.as(EditPhoneResponsePayload.class);
        assertKeyValue("message", "Request submitted successfully!", apiResponse.message);
    }

    public void verifyPackageStatusUpdate(String waybill, String action, HashMap<String, String> data) {
        PackageStatusUpdateRequestPayload body = RequestBuilder.updatePackageStatusReqGen();
        if (data.containsKey("shipment_destination_center")) {
            body.data.waybill.setSl(data.get("shipment_destination_center"));
        }
        if (action.equalsIgnoreCase("Delivered")) {
            body.data.waybill.setSt("DL");
            body.data.waybill.setSs("Delivered");
            body.data.waybill.setNslCode(action);
        } else if (action.equalsIgnoreCase("PickedUp")) {
            body.data.waybill.setSt("PU");
            body.data.waybill.setSs("In Transit");
            body.data.waybill.setNslCode("EOD-77");
        } else if (action.equalsIgnoreCase("Cancelled")) {
            body.data.waybill.setSt("CN");
            body.data.waybill.setSs("Cancelled");
            body.data.waybill.setNslCode("EOD-108");
        } else if (action.equalsIgnoreCase("REPL RT")) {
            body.data.waybill.setSt("RT");
            body.data.waybill.setSs("In Transit");
            body.data.waybill.setNslCode("EOD-77");
        } else if (action.equalsIgnoreCase("REPL PU")) {
            body.data.waybill.setSt("PU");
            body.data.waybill.setSs("In Transit");
            body.data.waybill.setNslCode("EOD-77");
        }
        try {
            requestPayload = JSON.parse(Utilities.jsonObjectToString(body).replace("Waybill", waybill));
        } catch (JsonProcessingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        Response response = PackageFlowRequests.updatePackageStatus(requestPayload, data);
        assertStatusCode(200, response);

        PackageStatusUpdateResponsePayload apiResponse = response.as(PackageStatusUpdateResponsePayload.class);
        assertKeyValue("status", "Completed", apiResponse.status);
    }

    public void verifyCustodyConnection() {
        Response response = PackageFlowRequests.custodyConnection("origin", "IND122001AAB");
        assertStatusCode(200, response);

        CustodyConnectionResponsePayload apiResponse = response.as(CustodyConnectionResponsePayload.class);
        assertKeyValue("success", true, apiResponse.success);
    }

    public void updateFinalQcWt(String wbn) {
        UpdateFinalWtRequestPayload request = RequestBuilder.updateFinalQcWtReqGen(wbn);
        Response response = PackageFlowRequests.updateFinalQcWt(request);
        assertStatusCode(200, response);

        UpdateFinalWtResponsePayload apiResponse = response.as(UpdateFinalWtResponsePayload.class);
        assertKeyValue("success", true, apiResponse.success);
    }

    public String meeshoSuccessManifestApi(HashMap<String, String> data) throws IOException {
        String json = null;
        CmuManifestRequestPayload body = RequestBuilder.cmuManifestWithCwhShipmentReqGen(data);

        try {
            json = "data=" + Utilities.jsonObjectToString(body) + "&format=json";
        } catch (JsonProcessingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        Response response = PackageFlowRequests.meeshoManifestShipment(json, data);
        assertStatusCode(200, response);

        MeeshoManifestResponsePayload apiResponse = response.as(MeeshoManifestResponsePayload.class);

        assertKeyValue("success", true, apiResponse.success);
        assertKeyValue("status", "Success", apiResponse.packages.get(0).status);
        assertKeyValue("remarks", "", apiResponse.packages.get(0).remarks.get(0));

        return apiResponse.upload_wbn;
    }

    public String meeshoFailureManifestApi(HashMap<String, String> data, String failureRemark) throws IOException {
        String json = null;
        CmuManifestRequestPayload body = RequestBuilder.cmuManifestWithCwhShipmentReqGen(data);

        try {
            json = "data=" + Utilities.jsonObjectToString(body) + "&format=json";
        } catch (JsonProcessingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        Response response = PackageFlowRequests.meeshoManifestShipment(json, data);
        assertStatusCode(200, response);

        MeeshoManifestFailureResponsePayload apiResponse = response.as(MeeshoManifestFailureResponsePayload.class);

        assertKeyValue("success", false, apiResponse.success);
        assertKeyValue("status", "Fail", apiResponse.packages.get(0).status);
        assertKeyValue("remarks", failureRemark, apiResponse.packages.get(0).remarks.get(0));

        return apiResponse.upload_wbn;
    }

    public String meeshoErrorManifestApi(HashMap<String, String> data, String errorRemark) throws IOException {
        String json = null;
        CmuManifestRequestPayload body = RequestBuilder.cmuManifestWithCwhShipmentReqGen(data);

        try {
            json = "data=" + Utilities.jsonObjectToString(body) + "&format=json";
        } catch (JsonProcessingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        Response response = PackageFlowRequests.meeshoManifestShipment(json, data);
        assertStatusCode(200, response);

        MeeshoManifestErrorResponsePayload apiResponse = response.as(MeeshoManifestErrorResponsePayload.class);

        assertKeyValue("success", false, apiResponse.success);
        assertKeyValue("error", true, apiResponse.error);
        assertKeyValue("rmk", errorRemark, apiResponse.rmk);

        return apiResponse.upload_wbn;
    }

    public void getManifestationUplData(String upl) {
        HashMap<String, String> requestData = new HashMap<>();
        requestData.put("upl", upl);
        Response response = PackageFlowRequests.getManifestationUplData(requestData);
        assertStatusCode(200, response);

        GetManifestUplDataResponsePayload apiResponse = response.as(GetManifestUplDataResponsePayload.class);
        assertKeyValue("status", "Success", apiResponse.pre.status);
        assertIfNotNull("suc.wbn", apiResponse.suc.get(0).wbn, "Manifestation successful");
    }

    public void getManifestationFailureUplData(String upl) {
        HashMap<String, String> requestData = new HashMap<>();
        requestData.put("upl", upl);
        Response response = PackageFlowRequests.getManifestationUplData(requestData);
        assertStatusCode(200, response);

        GetManifestFailureUplDataResponsePayload apiResponse = response
                .as(GetManifestFailureUplDataResponsePayload.class);
        assertKeyValue("status", "Failed", apiResponse.pre.status);
    }

    public void getManifestationFailureUplData(HashMap<String, String> requestData) {
        Response response = PackageFlowRequests.getManifestationUplData(requestData);
        assertStatusCode(200, response);

        if (requestData.get("scenario") == null) {
            GetManifestFailureUplDataResponsePayload apiResponse = response
                    .as(GetManifestFailureUplDataResponsePayload.class);
            assertKeyValue("status", "Failed", apiResponse.pre.status);

        } else if (requestData.get("scenario").contains("Payment mode")) {
            GetManifestPaymentModeFailureUplDataResponsePayload apiResponse = response
                    .as(GetManifestPaymentModeFailureUplDataResponsePayload.class);
            assertKeyValue("status", "Failed", apiResponse.pre.status);
        }
    }

    public void getManifestationErrorUplData(String upl) {
        HashMap<String, String> requestData = new HashMap<>();
        requestData.put("upl", upl);
        Response response = PackageFlowRequests.getManifestationUplData(requestData);
        assertStatusCode(200, response);

        GetManifestErrorUplDataResponsePayload apiResponse = response.as(GetManifestErrorUplDataResponsePayload.class);
        assertKeyValue("status", "Failure", apiResponse.pre.status);
    }

    public void getManifestationErrorUplData(HashMap<String, String> requestData) {
        Response response = PackageFlowRequests.getManifestationUplData(requestData);

        if (requestData.get("scenario") == null) {
            assertStatusCode(200, response);

            GetManifestErrorUplDataResponsePayload apiResponse = response
                    .as(GetManifestErrorUplDataResponsePayload.class);
            assertKeyValue("status", "Failure", apiResponse.pre.status);
            assertKeyValue("rmk", requestData.get("remark"), apiResponse.rmk);

        } else if (requestData.get("scenario").contains("Invalid client")) {
            assertStatusCode(404, response);
        }
    }

    public void getManifestationSuccessUplData(HashMap<String, String> requestData) {
        Response response = PackageFlowRequests.getManifestationUplData(requestData);

        if (requestData.get("scenario") == null) {
            assertStatusCode(200, response);

            GetManifestErrorUplDataResponsePayload apiResponse = response
                    .as(GetManifestErrorUplDataResponsePayload.class);
            assertKeyValue("status", "Success", apiResponse.pre.status);
            assertKeyValue("rmk", requestData.get("remark"), apiResponse.rmk);

        } else if (requestData.get("scenario").contains("Invalid client")) {
            assertStatusCode(404, response);
        }
    }

    public ApiResponse searchPackageInfo(String waybill) {
        Response response = PackageFlowRequests.getSearchDetails("wbns", waybill);
        assertStatusCode(200, response);

        ApiResponse apiResponse = response.as(ApiResponse.class);

        // Now, you can access and assert values within apiResponse
        assertKeyValue("status", 1, apiResponse.getStatus());

        return apiResponse;
    }

    public MtsResponse fetchMts(String waybill) {
        Response response = PackageFlowRequests.getMtsDetails("wbn", waybill);
        assertStatusCode(200, response);
        MtsResponse mtsResponse = response.as(MtsResponse.class);
        assertKeyValue("success", true, mtsResponse.success);
        return mtsResponse;
    }

    public QrcodeapiResponse fetchQRcode(HashMap<String, Object> dmap2) {
        QrcodeapiPayload body = RequestBuilder.QrcodeapiReqGen(dmap2);
        Response response = PackageFlowRequests.getQrcode(body);
        assertStatusCode(200, response);
        QrcodeapiResponse QR_Details = response.as(QrcodeapiResponse.class);
        assertKeyValue("success", true, QR_Details.success);
        return QR_Details;
    }

    public void flApi(String waybill, String centre, HashMap<String, String> data) {
        FetchListRequest body = RequestBuilder.flApiReqGen(Collections.singletonList(waybill));
        body.setCenter(centre);
        Response response = PackageFlowRequests.flShipment(body, data);
        assertStatusCode(200, response);

        FetchListResponse apiResponse = response.as(FetchListResponse.class);
        assertKeyValue("success", true, apiResponse.success);
    }

//this api is prnding due to empety response
    /*
     * public PhonelogRequest fetchPhonelog(HashMap<String, Object> dmap3) {
     *
     * PhonelogRequest body = RequestBuilder.PhoneLogApiReqGen(dmap3);
     *
     * Response response = PackageFlowRequests.Postphonelog(body, jwtToken);
     * assertStatusCode(200, response);
     *
     * return null; }
     */

    public FetchListResponse dcServApi(String pincode) {
        Response response = PackageFlowRequests.dcPinDetails(pincode);

        assertStatusCode(200, response);
        FetchListResponse dataDetails = response.as(FetchListResponse.class);
        assertKeyValue("success", true, dataDetails.success);

        return dataDetails;
    }

    public StListResponse dcStationApi(String page) {
        Response response = PackageFlowRequests.dcStationDetails(page);

        assertStatusCode(200, response);
        StListResponse dataDetails = response.as(StListResponse.class);
        assertKeyValue("success", true, dataDetails.success);
        return dataDetails;
    }

    public LhconnectionResponse lhConnApi(String origin) {
        Response response = PackageFlowRequests.lhDetails(origin);

        assertStatusCode(200, response);
        LhconnectionResponse dataDetails = response.as(LhconnectionResponse.class);
        assertKeyValue("success", true, dataDetails.success);
        return dataDetails;
    }

    public StatusResponse whStatusApi(String name) {
        StatusRequest body = RequestBuilder.whsRequest(name);
        Response response = PackageFlowRequests.whDetails(body);
        assertStatusCode(200, response);
        StatusResponse dataDetails = response.as(StatusResponse.class);
        assertKeyValue("success", true, dataDetails.success);

        return dataDetails;
    }

    public EditResponse whEditApi(String name) {
        EditRequest body = RequestBuilder.editwhsRequest(name);
        Response response = PackageFlowRequests.editwhDetails(body);
        assertStatusCode(200, response);
        EditResponse dataDetails = response.as(EditResponse.class);
        assertKeyValue("success", true, dataDetails.success);

        return dataDetails;
    }

    public CreateResponse whCreateApi(String name) {
        CreateRequest body = RequestBuilder.createwhsRequest(name);
        Response response = PackageFlowRequests.whscrt(body);
        assertStatusCode(201, response);

        CreateResponse dataDetails = response.as(CreateResponse.class);
        assertKeyValue("success", true, dataDetails.success);

        return dataDetails;
    }

    public ApplynslgenericResponse ApplyNslGeneric(List<String> waybills, String status_type, String nsl_code, String nsl_remark, HashMap<String, String> data) {
        Applynslgeneric body = RequestBuilder.ApplyNslGeneric(waybills, status_type, nsl_code, nsl_remark);
        try {
            requestPayload = JSON.parse(Utilities.jsonObjectToString(body).replace("Waybill", waybills.get(0)));
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Failed to parse !!!" + e);
        }
        Response response = PackageFlowRequests.ApplyNslGeneric(requestPayload, data);
        ControllerHelper.checkPackageCurrentScanWithWait(waybills, data, nsl_code);
        return response.as(ApplynslgenericResponse.class);
    }

    public applynslresponse Applynsl(List<String> waybills, String nsl_code, HashMap<String, String> data) {

        applynsl body = RequestBuilder.Applynsl(waybills, nsl_code);

        Response response = PackageFlowRequests.ApplyNsl(body, data);
        assertStatusCode(200, response);

        applynslresponse apiResponse = response.as(applynslresponse.class);
        return apiResponse;
    }

    public ArrayList<Object> cmuManifestApiWithResponse(HashMap<String, String> data) {
        String json = null;
        CmuManifestApi body = RequestBuilder.cmuManifestApiReqGen(data);
        String client = "regression_client";
        //if client name is also sent in data
        if (data.containsKey("client")) {
            client = data.get("client");
        }

        // setting up the body provided in data
        try {
            json = "data=" + Utilities.jsonObjectToString(body) + "&format=json";
        } catch (JsonProcessingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        ArrayList<Object> waybills = new ArrayList<>();
        Response response = PackageFlowRequests.cmuManifestShipment(json, data);
        assertStatusCode(200, response);

        CmuResponseApi apiResponse = response.as(CmuResponseApi.class);
        for (int i = 0; i < apiResponse.packages.size(); i++) {
            waybills.add(apiResponse.packages.get(i).waybill);
        }
        waybills.add(apiResponse.uploadWbn.toString());
        //String status = apiResponse.packages.get(0).status;
        if (apiResponse.packages.get(0).remarks.get(0) != null) {
            waybills.add(apiResponse.packages.get(0).status);
            waybills.add(apiResponse.packages.get(0).remarks.get(0));
        } else {
            waybills.add(apiResponse.packages.get(0).status);
        }

        return waybills;
    }

    public InstaBaggingCreateResponsePayload instaBaggingCreateApi(HashMap<String, String> data) {
        InstaBaggingCreateRequestPayload body = RequestBuilder.instaBaggingCreateReqGen(data);

        Response response = PackageFlowRequests.instaBagging(body, data);
        assertStatusCode(200, response);
        return response.as(InstaBaggingCreateResponsePayload.class);
    }

    public Object instaBaggingSealApi(HashMap<String, String> data) {
        InstaBaggingSealRequestPayload body = RequestBuilder.instaBaggingSealReqGen(data);

        Response response = PackageFlowRequests.instaBagging(body, data);
        assertStatusCode(200, response);
        if (data.containsKey("bar")) {
            InstaBaggingSealWithBarResponsePayload instaBaggingSealWithBarResponsePayload = response.as(InstaBaggingSealWithBarResponsePayload.class);
            assertKeyValue("success", true, instaBaggingSealWithBarResponsePayload.isSuccess());
            return instaBaggingSealWithBarResponsePayload;
        }
        InstaBaggingSealResponsePayload apiResponse = response.as(InstaBaggingSealResponsePayload.class);
        assertKeyValue("success", true, apiResponse.success);
        return apiResponse;
    }

    public FetchClientDetailsResponsePayload verifyFetchClientDetailsApi(String clientName, HashMap<String, String> data) {
        Response response = PackageFlowRequests.getClientsDetails("client", clientName, data);
        assertStatusCode(200, response);

        FetchClientDetailsResponsePayload apiResponse = response.as(FetchClientDetailsResponsePayload.class);
        assertKeyValue("success", true, apiResponse.success);

        return apiResponse;
    }

    public void createUpdateClientData(HashMap<String, String> data) {
        UpdateRegressionClientRequestPayload request = RequestBuilder.clientUpdateReqGen(data);

        Response response = PackageFlowRequests.clientCreateUpdate(request);
        assertStatusCode(200, response);

        CreateUpdateClientResponsePayload apiResponse = response.as(CreateUpdateClientResponsePayload.class);
        assertKeyValue("success", true, apiResponse.success);
    }

    public FetchClientUuidDetailsResponsePayload verifyFetchClientUuidDetailsApi(String clientUuid) {
        Response response = PackageFlowRequests.getClientsUuidDetails(clientUuid);
        assertStatusCode(200, response);

        FetchClientUuidDetailsResponsePayload apiResponse = response.as(FetchClientUuidDetailsResponsePayload.class);
        return apiResponse;
    }

    //fetch User JWT token from Token Manager, and token manager handle the token generation
    public String fetchUserJwtTokenApi(HashMap<String, String> data) {
        String client = Optional.ofNullable(data)
                .map(m -> m.get("client"))
                .orElse("HQAPIREGRESSION");
        System.out.println("client " + client);
        return TokenManager.getToken(client);
    }

    //add function to request and verify claims b2c listing api
    public void verifyClaimsB2cListingApi(HashMap<String, String> paramObjectData, HashMap<String, String> data) throws JsonProcessingException {
        FetchB2cClaimsListingRequestParam requestParamObject = RequestBuilder.fetchB2cClaimParamReqGen(paramObjectData);

        Response response = PackageFlowRequests.getClaimsB2cListing("filter", requestParamObject, data);
        assertStatusCode(200, response);

        FetchB2cClaimsListingResponsePayload apiResponse = response.as(FetchB2cClaimsListingResponsePayload.class);
        assertKeyValue("msg", "Successful", apiResponse.msg);
    }

    //add function to request and verify claims b2c listing api
    public void verifyClaimsB2bListingApi(HashMap<String, String> paramObjectData, HashMap<String, String> data) throws JsonProcessingException {
        FetchB2bClaimsListingRequestParam requestParamObject = RequestBuilder.fetchB2bClaimParamReqGen(paramObjectData);

        Response response = PackageFlowRequests.getClaimsB2bListing("filter", requestParamObject, data);
        assertStatusCode(200, response);

        FetchB2bClaimsListingResponsePayload apiResponse = response.as(FetchB2bClaimsListingResponsePayload.class);
        assertKeyValue("msg", "Successful", apiResponse.msg);
    }

    //add function to request and verify claims b2c listing api
    public void verifyUcpLossClaimsApi(HashMap<String, String> paramObjectData, HashMap<String, String> data) throws JsonProcessingException {
        FetchUcpLossClaimsRequestParam requestParamObject = RequestBuilder.fetchUcpLossClaimsParamReqGen(paramObjectData);

        Response response = PackageFlowRequests.getUcpLossClaims("filter", requestParamObject, data);
        assertStatusCode(200, response);

        FetchUcpLossClaimsResponsePayload apiResponse = response.as(FetchUcpLossClaimsResponsePayload.class);
        assertKeyValue("msg", "Successful", apiResponse.msg);
    }


    public void createLossClaimAPi(HashMap<String, String> requestBodyData, HashMap<String, String> data) {
        CreateClaimsRequestPayload body = RequestBuilder.createClaimsReqGen(requestBodyData);

        Response response = PackageFlowRequests.createLossClaims(body, data);
        assertStatusCode(201, response);

        CreateClaimsResponsePayload apiResponse = response.as(CreateClaimsResponsePayload.class);
        assertKeyValue("msg", "Successful", apiResponse.msg);
        assertKeyValue("data message",
                "Your request is queued successfully. Please check after some time",
                apiResponse.data.message);
    }

    public void verifyFetchClaimDataApi(HashMap<String, String> apiData, HashMap<String, String> data) {
        Response response = PackageFlowRequests.getClaimIdData(apiData, data);
        assertStatusCode(200, response);

        FetchClaimIdDataResponsePayload apiResponse = response.as(FetchClaimIdDataResponsePayload.class);
        assertKeyValue("msg", "Successful", apiResponse.msg);
        assertKeyValue("client_name", apiData.get("client_name"), apiResponse.data.client_name);
        assertKeyValue("wbn", apiData.get("wbn"), apiResponse.data.wbn);
        assertKeyValue("dispute_type", apiData.get("dispute_type"), apiResponse.data.dispute_type);
        assertKeyValue("status", apiData.get("status"), apiResponse.data.status);


    }

    public void createClaimUploadFileAPi(String filepath, HashMap<String, String> data) {
        Response response = PackageFlowRequests.createClaimUploadFile(filepath, data);
        assertStatusCode(201, response);

        UploadFileCreateBulkClaimsResponsePayload apiResponse = response.as(UploadFileCreateBulkClaimsResponsePayload.class);
        assertKeyValue("msg", "Successful", apiResponse.msg);
        assertKeyValue("data message",
                "Your request is queued successfully. Please check History.",
                apiResponse.data.message);
    }

    public void verifyFetchClaimHistoryApi(HashMap<String, String> data) {
        Response response = PackageFlowRequests.getClaimHistory(data);
        assertStatusCode(200, response);

        FetchClaimHistoryResponsePayload apiResponse = response.as(FetchClaimHistoryResponsePayload.class);
        assertKeyValue("msg", "Successful", apiResponse.msg);


    }

    //add function to request and verify claims b2c listing api
    public void verifyClaimsB2bViewApi(HashMap<String, String> paramObjectData, HashMap<String, String> data) throws JsonProcessingException {
        FetchB2bClaimsDetailsRequestParam requestParamObject = RequestBuilder.fetchB2bViewClaimParamReqGen(paramObjectData);

        Response response = PackageFlowRequests.getB2bViewData(paramObjectData, "filter", requestParamObject, data);
        assertStatusCode(200, response);

        FetchB2cClaimsListingResponsePayload apiResponse = response.as(FetchB2cClaimsListingResponsePayload.class);
        assertKeyValue("msg", "Successful", apiResponse.msg);
    }

    //add function to request and verify claims b2b report api
    public void verifyClaimsB2bReportApi(HashMap<String, String> paramObjectData, HashMap<String, String> data) throws JsonProcessingException {
        FetchClaimsReportRequestParam requestParamObject = RequestBuilder.fetchClaimReportParamReqGen(paramObjectData);

        Response response = PackageFlowRequests.getClaimsB2bReport("filter", requestParamObject, data);
        assertStatusCode(200, response);

        FetchClaimsReportResponsePayload apiResponse = response.as(FetchClaimsReportResponsePayload.class);
        assertKeyValue("msg", "Successful", apiResponse.msg);
    }

    //add function to request and verify claims b2b report api
    public void verifyClaimsB2cReportApi(HashMap<String, String> paramObjectData, HashMap<String, String> data) throws JsonProcessingException {
        FetchClaimsReportRequestParam requestParamObject = RequestBuilder.fetchClaimReportParamReqGen(paramObjectData);

        Response response = PackageFlowRequests.getClaimsB2cReport("filter", requestParamObject, data);
        assertStatusCode(200, response);

        FetchClaimsReportResponsePayload apiResponse = response.as(FetchClaimsReportResponsePayload.class);
        assertKeyValue("msg", "Successful", apiResponse.msg);
    }

    public void updateClaimSettlementAmountAPi(HashMap<String, String> requestBodyData, HashMap<String, String> data) {
        UpdateClaimSettlementAmountRequestPayload body = RequestBuilder.updateClaimSettlementAmountReqGen(requestBodyData);

        Response response = PackageFlowRequests.updateClaimSettlementAmount(body, requestBodyData, data);
        assertStatusCode(200, response);

        UpdateClaimSettlementAmountResponsePayload apiResponse = response.as(UpdateClaimSettlementAmountResponsePayload.class);
        assertKeyValue("msg", "Successful", apiResponse.msg);
    }

    public void updateClaimSettlementAmountUploadFileAPi(String filepath, HashMap<String, String> data) {
        Response response = PackageFlowRequests.updateClaimSettlementAmountUploadFile(filepath, data);
        assertStatusCode(200, response);

        UploadFileCreateBulkClaimsResponsePayload apiResponse = response.as(UploadFileCreateBulkClaimsResponsePayload.class);
        assertKeyValue("msg", "Successful", apiResponse.msg);

    }

    public GetManifestUplDataResponsePayload getUplData(HashMap<String, String> UPLdata) {
        HashMap<String, String> requestData = new HashMap<>();
        Response response = PackageFlowRequests.getManifestationUplData(UPLdata);
        assertStatusCode(200, response);

        GetManifestUplDataResponsePayload apiResponse = response.as(GetManifestUplDataResponsePayload.class);

        return apiResponse;
    }

    public void centerUpdateApi(String waybill, String updatedCn) {
        CenterUpdateRequest body = RequestBuilder.CenterUpdateApiReqGen(waybill, updatedCn);

        Response response = PackageFlowRequests.centerUpdate(body);
        assertStatusCode(200, response);

        CenterUpdateResponse apiResponse = response.as(CenterUpdateResponse.class);
        assertKeyValue("status", "Success", apiResponse.getStatus());

    }

    public void centerUpdateApi(String waybill, String updatedCn, Boolean enable) {
        CenterUpdateRequest body = RequestBuilder.CenterUpdateApiReqGen(waybill, updatedCn);
        Response response = PackageFlowRequests.centerUpdate(body);
        assertStatusCode(200, response);

        if (enable) {
            CenterUpdateResponse apiResponse = response.as(CenterUpdateResponse.class);
            assertKeyValue("status", "Success", apiResponse.getStatus());
        } else {
            CenterUpdateResponse apiResponse = response.as(CenterUpdateResponse.class);
            assertKeyValue("status", "Failed", apiResponse.getStatus());
        }

    }

    public void centerUpdateRTApi(String waybill, String updatedRCn) {
        CenterUpdateRequestRT body = RequestBuilder.CenterUpdateRTApiReqGen(waybill, updatedRCn);

        Response response = PackageFlowRequests.centerUpdate(body);
        assertStatusCode(200, response);

        CenterUpdateResponse apiResponse = response.as(CenterUpdateResponse.class);
        assertKeyValue("status", "Success", apiResponse.getStatus());

    }

    public void centerUpdateRTApi(String waybill, String updatedRCn, Boolean enable) {
        CenterUpdateRequestRT body = RequestBuilder.CenterUpdateRTApiReqGen(waybill, updatedRCn);
        Response response = PackageFlowRequests.centerUpdate(body);
        assertStatusCode(200, response);

        if (enable) {
            CenterUpdateResponse apiResponse = response.as(CenterUpdateResponse.class);
            assertKeyValue("status", "Success", apiResponse.getStatus());
        } else {
            CenterUpdateResponse apiResponse = response.as(CenterUpdateResponse.class);
            assertKeyValue("status", "Failed", apiResponse.getStatus());
        }

    }

    public void fetchClientDetailsInternal(String completion_type, String client_name, String client_type, HashMap<String, Object> expected_values) {
        HashMap<String, String> data = new HashMap<>();
        String bearer = fetchUserJwtTokenApi(data);
        data.clear();
        data.put("completion_type", completion_type);
        data.put("client_name", client_name);
        data.put("client_type", client_type);
        Response response = PackageFlowRequests.clientDetailsFetchInternal(data, bearer);

        ClientFetchInternalResponse apiResponse = response.as(ClientFetchInternalResponse.class);

        if (client_type.equals("B2C")) {
            assertStatusCode(400, response);
            assertKeyValue("success", expected_values.get("success").toString(), apiResponse.success.toString());
        } else if (client_type.equals("B2B")) {
            assertStatusCode(200, response);

            expected_values.forEach((key, value) -> {
                switch (key) {
                    case "success":
                        assertKeyValue(key, value.toString(), apiResponse.success.toString());
                        break;

                    case "billing_method":
                        assertKeyValue(key, value.toString(), apiResponse.message.get(0).billingMethod); //on edt for verification it should be cn1 key
                        break;

                    case "product_type":
                        assertKeyValue(key, value.toString(), apiResponse.message.get(0).productType);
                        break;

                    case "name":
                        assertKeyValue(key, value.toString(), apiResponse.message.get(0).name);
                        break;

                    case "client_type":
                        assertKeyValue(key, value.toString(), apiResponse.message.get(0).clientType);
                        break;

                    case "wallet_id":
                        assertKeyValue(key, value.toString(), apiResponse.message.get(0).walletId);
                        break;

                    case "uuid_key":
                        assertKeyValue(key, value.toString(), apiResponse.message.get(0).uuidKey);
                        break;

                    case "frs_code":
                        assertKeyValue(key, value.toString(), apiResponse.message.get(0).frsCode);
                        break;

                    case "is_prepaid":
                        assertKeyValue(key, value.toString(), apiResponse.message.get(0).isPrepaid.toString());
                        break;

                    case "freight_collection":
                        assertKeyValue(key, value.toString(), apiResponse.message.get(0).freightCollection.toString());
                        break;

                    case "id":
                        assertKeyValue(key, value.hashCode(), apiResponse.message.get(0).id);
                        break;

                    case "waybill_prefix":
                        assertKeyValue(key, value.hashCode(), apiResponse.message.get(0).waybillPrefix);
                        break;

                }
            });
        }


    }

    public Response verifyQCAnswerResponse(String wbn) throws InterruptedException {
        Thread.sleep(5000);
        Response response = PackageFlowRequests.getQcAnswers("wbn", wbn);
        assertStatusCode(200, response);
        String responseBody = response.getBody().asString();
        assertKeyValue("success", true, response.jsonPath().get("success"));
        return response;
    }


    public APIRestEWayBillResponsePayload updateRSDCNEWBNOfWayBill(Map<String, String> updateRSDCNEWBNOfWayBillMap) {
        APIRestEWayBillRequestPayload apiRestEWayBillRequestPayload = RequestBuilder.apiRestEWayBillRequestPayload(updateRSDCNEWBNOfWayBillMap);
        if (updateRSDCNEWBNOfWayBillMap.get("api").equalsIgnoreCase("Internal")) {
            return PackageFlowRequests.updateRSEWBnDCNByInternal(updateRSDCNEWBNOfWayBillMap.get("waybill"), apiRestEWayBillRequestPayload, updateRSDCNEWBNOfWayBillMap.get("token"))
                    .as(APIRestEWayBillResponsePayload.class);
        }
        return PackageFlowRequests.updateRSEWBnDCN(updateRSDCNEWBNOfWayBillMap.get("waybill"), apiRestEWayBillRequestPayload).as(APIRestEWayBillResponsePayload.class);
    }

    public ODTatResponsePayload FetchODTatValues(HashMap<String, String> params) {
        Response response = PackageFlowRequests.FetchodTat(params);
        assertStatusCode(200, response);

        ODTatResponsePayload apiResponse = response.as(ODTatResponsePayload.class);
        assertKeyValue("success", true, apiResponse.success);

        return apiResponse;
    }

    public UnsetReturnDispatchIdResPayload unsetReturnDispatchId(Map<String, Object> returnDispatchData, List<String> waybillList) {
        UnsetReturnDispatchIdReqPayload unsetReturnDispatchIdReqPayload = RequestBuilder.unsetReturnDispatchIdReqGen(returnDispatchData, waybillList);
        String unsetReturnDispatchIdReqPayloadJson;
        try {
            unsetReturnDispatchIdReqPayloadJson = Utilities.jsonObjectToString(unsetReturnDispatchIdReqPayload);

        } catch (JsonProcessingException e) {
            throw new RuntimeException("Error in converting object to json");
        }
        Response response = PackageFlowRequests.unsetReturnDispatchId(unsetReturnDispatchIdReqPayloadJson, returnDispatchData.get("dispatch_id").toString());
        assertStatusCode(200, response);
        UnsetReturnDispatchIdResPayload unsetReturnDispatchIdResPayload = response.as(UnsetReturnDispatchIdResPayload.class);
        assertKeyValue("Success", true, unsetReturnDispatchIdResPayload.isSuccess());
        assertKeyValue("rmk", "Request submitted successfully", unsetReturnDispatchIdResPayload.getRemark());
        return unsetReturnDispatchIdResPayload;
    }

    public void transIdUpdate(String waybill) {
        String json;
        UpdateTransIdRequestPayload body = RequestBuilder.updateTransId();

        try {
            json = Utilities.jsonObjectToString(body).replace("Waybill", waybill);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        Response response = PackageFlowRequests.updateTransIdByBulkUpdate(JSON.parse(json));

        assertStatusCode(200, response);
        UpdateTransIdResponsePayload apiResponse = response.as(UpdateTransIdResponsePayload.class);
        assertKeyValue("success", true, apiResponse.getSuccess());
        String upl = apiResponse.getUpl();

        Utilities.hardWait(60);

        Response UplResponse = PackageFlowRequests.uplResponse(upl);

        assertStatusCode(200, UplResponse);
        String key = UplResponse.asPrettyString();

        JsonObject jsonObject = new Gson().fromJson(key, JsonObject.class);
        String sValue = jsonObject.get("s").getAsString();
        assertKeyValue("s", "Success", sValue);

        UpdateTransId1RequestPayload body1 = RequestBuilder.updateTransId1(upl);

        try {

            json = Utilities.jsonObjectToString(body1).replace("Waybill", waybill);

        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        Response response1 = PackageFlowRequests.updateTransIdByDexApi(JSON.parse(json));
        assertStatusCode(200, response1);
        UpdateTransId1ResponsePayload apiResponse1 = response1.as(UpdateTransId1ResponsePayload.class);
        assertKeyValue("success", true, apiResponse1.getSuccess());
        upl = apiResponse1.getData().getUpl();
        Utilities.hardWait(60);

        UplResponse = PackageFlowRequests.uplResponse(upl);
        assertStatusCode(200, UplResponse);
        key = UplResponse.asPrettyString();

        jsonObject = new Gson().fromJson(key, JsonObject.class);
        sValue = jsonObject.get("s").getAsString();
        assertKeyValue("s", "Success", sValue);
    }

    public PackageDetails verifyPackageFetchInfoApi(HashMap<String, String> paramsMap, HashMap<String, String> data) {
        Response response = PackageFlowRequests.getPackageDetails(paramsMap, data);
        assertStatusCode(200, response);

        PackageDetails pkg_Details = response.as(PackageDetails.class);
        return pkg_Details;

    }

    public ClientDetailsResponsePayload verifyClientDetailsApi(HashMap<String, String> data) {
        Response response = PackageFlowRequests.fetchclientDetails(data);
        assertStatusCode(200, response);

        ClientDetailsResponsePayload apiResponse = response.as(ClientDetailsResponsePayload.class);
        assertKeyValue("success", true, apiResponse.success);

        return apiResponse;

    }

    public void verifyPackageUpdate(String waybill, HashMap<String, String> data) {
        PackageUpdateRequestPayload request = RequestBuilder.packageUpdateReqGen(waybill);
        Response response = PackageFlowRequests.updatePackage(request, data);
        assertStatusCode(200, response);

        PackageUpdateResponsePayload apiResponse = response.as(PackageUpdateResponsePayload.class);
        assertKeyValue("success", true, apiResponse.success);
    }


    public PUpdateResponse PackageUpdate(HashMap<String, String> ReqData) {
        PUpdatePayload request = RequestBuilder.packageUpdateReqGen(ReqData);
        Response response = PackageFlowRequests.pUpdate(request);
        assertStatusCode(201, response);

        PUpdateResponse apiResponse = response.as(PUpdateResponse.class);
        assertKeyValue("success", "Request submitted successfully!", apiResponse.message);

        return apiResponse;

    }

    public SmsResponse verifySMS(String phone) throws InterruptedException {
        Response response = PackageFlowRequests.getSMS("SMS", phone, "count", "24");

        SmsResponse apiResponse = response.as(SmsResponse.class);

        return apiResponse;

    }

    public void FetchBagScore(String bag) {
        Response response = PackageFlowRequests.getBagScore("ref_ids", bag);
        assertStatusCode(200, response);
        FetchBagScoreResponsePayload apiResponse = response.as(FetchBagScoreResponsePayload.class);
        assertKeyValue("success", true, apiResponse.getSuccess());
    }

    public void markBagHold(String bag) {
        List<String> UnholdbagList = new ArrayList<>();
        List<String> HoldbagList = new ArrayList<>();
        HoldbagList.add(bag);
        UpdateBagStatusRequestPayload request = RequestBuilder.bagStatusUpdateHold(HoldbagList, UnholdbagList);
        Response response = PackageFlowRequests.updateBagStatus(request);
        assertStatusCode(200, response);

        UpdateBagStatusResponsePayload apiResponse = response.as(UpdateBagStatusResponsePayload.class);
        assertKeyValue("success", bag, apiResponse.getData().getSuccess().get(0));

        response = PackageFlowRequests.getBagDetails("ref_ids", bag);
        assertStatusCode(200, response);

        BagDetails bagDetails = response.as(BagDetails.class);
        assertKeyValue("Bag hold key:", true, bagDetails.bags.get(0).hold);
    }

    public void markBagUnhold(String bag) {
        List<String> UnholdbagList = new ArrayList<>();
        List<String> HoldbagList = new ArrayList<>();
        UnholdbagList.add(bag);
        UpdateBagStatusRequestPayload request = RequestBuilder.bagStatusUpdateUnhold(HoldbagList, UnholdbagList);
        Response response = PackageFlowRequests.updateBagStatus(request);
        assertStatusCode(200, response);

        UpdateBagStatusResponsePayload apiResponse = response.as(UpdateBagStatusResponsePayload.class);
        assertKeyValue("success", bag, apiResponse.getData().getSuccess().get(0));

        response = PackageFlowRequests.getBagDetails("ref_ids", bag);
        assertStatusCode(200, response);

        BagDetails bagDetails = response.as(BagDetails.class);
        assertKeyValue("Bag hold key:", false, bagDetails.bags.get(0).hold);

    }


    public ArrayList<String> NewManifestApi(String environment, HashMap<String, Object> data) {
        String json = null;
        Manifest body = RequestBuilder2.NewManifestApiReqGen(data);
        // setting up the body provided in data
        try {
            json = "data=" + Utilities.jsonObjectToString(body) + "&format=json";
        } catch (JsonProcessingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        Response response = PackageFlowRequests.NewManifestShipment(environment, json, data, "manifest_regression_client");
        long responseTime = response.getTime();
        int responseCode = response.statusCode();

        CmuResponseApiNew apiResponse = response.as(CmuResponseApiNew.class);
        String ApiResponse = response.getBody().asPrettyString();

        ArrayList<String> waybills = new ArrayList<>();
        waybills.add(ApiResponse);
        waybills.add(apiResponse.uploadWbn.toString());
        String ResponseTime = Long.toString(responseTime);
        String ResponseCode = Integer.toString(responseCode);
        waybills.add(ResponseTime);
        waybills.add(ResponseCode);

        for (int i = 0; i < apiResponse.packages.size(); i++) {
            if (apiResponse.packages.get(i).waybill == null || apiResponse.packages.get(i).waybill == "") {
                waybills.add("");
            } else {
                waybills.add(apiResponse.packages.get(i).waybill.toString());

            }
        }

        return waybills;
    }

    public ArrayList<String> NewManifestApi2(String environment, HashMap<String, Object> data) {
        String json = null;
        //request pojo
        Manifest2 body = RequestBuilder2.NewManifestApiReqGen2(data);
        // setting up the body provided in data
        try {
            json = "data=" + Utilities.jsonObjectToString(body) + "&format=json";
        } catch (JsonProcessingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        Response response = PackageFlowRequests.NewManifestShipment(environment, json, data, "manifest_regression_client");
        long responseTime = response.getTime();
        int responseCode = response.statusCode();

        CmuResponseApiNew apiResponse = response.as(CmuResponseApiNew.class);
        String ApiResponse = response.getBody().asPrettyString();

        ArrayList<String> waybills = new ArrayList<>();
        waybills.add(ApiResponse);
        waybills.add(apiResponse.uploadWbn.toString());
        String ResponseTime = Long.toString(responseTime);
        String ResponseCode = Integer.toString(responseCode);
        waybills.add(ResponseTime);
        waybills.add(ResponseCode);


        for (int i = 0; i < apiResponse.packages.size(); i++) {
            if (apiResponse.packages.get(i).waybill == null || apiResponse.packages.get(i).waybill == "") {
                waybills.add("");

            } else {
                waybills.add(apiResponse.packages.get(i).waybill.toString());


            }
        }


        return waybills;
    }

    public ArrayList<String> NewManifestApi3(String environment, HashMap<String, Object> data) {
        String json = null;
        Manifest3 body = RequestBuilder2.NewManifestApiReqGen3(data);
        // setting up the body provided in data
        try {
            json = "data=" + Utilities.jsonObjectToString(body) + "&format=json";
        } catch (JsonProcessingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        Response response = PackageFlowRequests.NewManifestShipment(environment, json, data, "manifest_regression_client");
        long responseTime = response.getTime();
        int responseCode = response.statusCode();


        CmuResponseApiNew apiResponse = response.as(CmuResponseApiNew.class);
        String ApiResponse = response.getBody().asPrettyString();


        ArrayList<String> waybills = new ArrayList<>();
        waybills.add(ApiResponse);
        System.out.println(apiResponse.uploadWbn);
        waybills.add(apiResponse.uploadWbn.toString());
        String ResponseTime = Long.toString(responseTime);
        String ResponseCode = Integer.toString(responseCode);
        waybills.add(ResponseTime);
        waybills.add(ResponseCode);


        for (int i = 0; i < apiResponse.packages.size(); i++) {
            if (apiResponse.packages.get(i).waybill == null || apiResponse.packages.get(i).waybill == "") {
                waybills.add("");
            } else {
                waybills.add(apiResponse.packages.get(i).waybill.toString());

            }
        }


        return waybills;
    }


    //Create function manifest MPS shipmnet with both master and child payload
    //request builder = createMPSApiReqGen
    public ArrayList<String> MPSManifestationUsingMasterChildPayload(String environment, HashMap<String, Object> data) {
        String json = null;
        CreateMPS body = RequestBuilder2.createMPSApiReqGen(data);
        // setting up the body provided in data
        try {
            json = "data=" + Utilities.jsonObjectToString(body) + "&format=json";
        } catch (JsonProcessingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        Response response = PackageFlowRequests.NewManifestShipment(environment, json, data, "manifest_regression_client");
        long responseTime = response.getTime();
        int responseCode = response.statusCode();


        CmuResponseApiNew apiResponse = response.as(CmuResponseApiNew.class);
        String ApiResponse = response.getBody().asPrettyString();


        ArrayList<String> waybills = new ArrayList<>();
        waybills.add(ApiResponse);
        System.out.println(apiResponse.uploadWbn);
        waybills.add(apiResponse.uploadWbn.toString());
        String ResponseTime = Long.toString(responseTime);
        String ResponseCode = Integer.toString(responseCode);
        waybills.add(ResponseTime);
        waybills.add(ResponseCode);


        for (int i = 0; i < apiResponse.packages.size(); i++) {
            if (apiResponse.packages.get(i).waybill == null || apiResponse.packages.get(i).waybill == "") {
                waybills.add("");

            } else {
                waybills.add(apiResponse.packages.get(i).waybill.toString());


            }
        }
        if (apiResponse.packages.size() == 0) {
            waybills.add("");
            waybills.add("");
        }


        return waybills;
    }


    //Create function to manifest a shipment using only mandatory keys
    //request builder = Mandatory keys


    public ArrayList<String> ManifestationUsingMandatoryKeys(String environment, HashMap<String, Object> data) {
        String json = null;
        MandatoryKeys body = RequestBuilder2.MandatoryKeys(data);
        // setting up the body provided in data
        try {
            json = "data=" + Utilities.jsonObjectToString(body) + "&format=json";
        } catch (JsonProcessingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        Response response = PackageFlowRequests.NewManifestShipment(environment, json, data, "manifest_regression_client");
        long responseTime = response.getTime();
        int responseCode = response.statusCode();


        CmuResponseApiNew apiResponse = response.as(CmuResponseApiNew.class);
        String ApiResponse = response.getBody().asPrettyString();


        ArrayList<String> waybills = new ArrayList<>();
        waybills.add(ApiResponse);

        waybills.add(apiResponse.uploadWbn.toString());
        String ResponseTime = Long.toString(responseTime);
        String ResponseCode = Integer.toString(responseCode);
        waybills.add(ResponseTime);
        waybills.add(ResponseCode);
        for (int i = 0; i < apiResponse.packages.size(); i++) {
            if (apiResponse.packages.get(i).waybill == null || apiResponse.packages.get(i).waybill == "") {
                waybills.add("");
            } else {
                waybills.add(apiResponse.packages.get(i).waybill.toString());

            }
        }
        System.out.println(waybills);


        return waybills;
    }

    public String getManifestationUplDataNewService(String upl) {
        HashMap<String, String> requestData = new HashMap<>();
        requestData.put("upl", upl);
        Response response = PackageFlowRequests.getManifestationUplDataNew("manifest_regression_client", requestData);
        GetManifestUplDataResponsePayload apiResponse = response.as(GetManifestUplDataResponsePayload.class);
        String ApiResponse = response.getBody().asPrettyString();

        return ApiResponse;
    }

    public PackageDetailNew NewServicefetchPackageInfoDiffEnv(String enviorment, String waybill, HashMap<String, String> data) {
        String client = "manifest_regression_client";
        // if client name is also sent in data
        if (data.containsKey("client")) {
            client = data.get("client");
        }

        Response response = PackageFlowRequests.getPackageDetails(enviorment, "ref_ids", waybill, client);
        assertStatusCode(200, response);
        PackageDetailNew[] pkg_Details = response.as(PackageDetailNew[].class);
        PackageDetailNew pkgDetails = pkg_Details[0];

        return pkgDetails;

    }

    public String FetchEwbnCollection(String newEnv, String wbn) {
        Response response = PackageFlowRequests.fetchEwbnCollection(newEnv, "wbn", wbn);
        ewbncollection apiResponse = response.as(ewbncollection.class);
        String ApiResponse = response.getBody().asPrettyString();

        return ApiResponse;
    }

    public String FetchEwbn(String environment, String waybill) {
        Response response = PackageFlowRequests.fetchEwbn(environment, "wbn", waybill);
        fetchEwbnResponse apiResponse = response.as(fetchEwbnResponse.class);
        String ewbn;
        ewbn = apiResponse.data.get(0).ewbn.toString();
        return ewbn;

    }

    public String CreateEwbn(String environment, String waybill) {
        //call request builder for creating ewbn
        ewbncreation ewbnrequest = RequestBuilder.createEwbn(waybill);
        Response response = PackageFlowRequests.createEwbn(environment, ewbnrequest);
        ewbncreationresponse apiResponse = response.as(ewbncreationresponse.class);

        return waybill;

    }

    public String verifyFetchWbn(HashMap<String, Object> data) {
        Response response = PackageFlowRequests.fetchClientWbn2(data);
        assertStatusCode(200, response);

        FetchClientWbnJava apiResponse = response.as(FetchClientWbnJava.class);
        assertIfNotNull("response", apiResponse);
        return apiResponse.wbn.toString();
    }

    public String ClientUpdateNew(String environment, HashMap<String, Object> data) {
        ClientUpdateNew body = RequestBuilder2.ClientUpdateNew(data);
        Response response = PackageFlowRequests.clientCreateUpdateNew(environment, body);
        ClientUpdateResponse apiResponse = response.as(ClientUpdateResponse.class);
        String ApiResponse = response.getBody().asPrettyString();
        assertStatusCode(200, response);
        assertKeyValue("success", true, apiResponse.success);
        return ApiResponse;
    }

    public byte[] updateStatusByFile(Map<String, String> data) {
        Response response = PackageFlowRequests.updateStatus(data);
        assertStatusCode(200, response);
        return response.asByteArray();
    }

    public PushWBNToSorterResPayload pushWBNToSorter(Map<String, String> data) {
        Response response = PackageFlowRequests.pushDataToSorter(data.get("param_value"), data);
        return response.as(PushWBNToSorterResPayload.class);
    }

    public LMDashboardMissingShipmentsRes getLMDashboardMissingShipments(HashMap<String, String> data, String param, String paramValue) {
        Response response = PackageFlowRequests.getLMDashboardMissingShipments(param, paramValue, data);
        LMDashboardMissingShipmentsRes lmDashboardMissingShipmentsRes = response.as(LMDashboardMissingShipmentsRes.class);
        Assertions.assertStatusCode(lmDashboardMissingShipmentsRes.getMessage() != null ? 206 : 200, response);
        return lmDashboardMissingShipmentsRes;
    }

    public PackageCountResPayload getPackageCount(String env) {
        Response response = PackageFlowRequests.getPackageCount(env);
        return response.as(PackageCountResPayload.class);
    }

    public MPSAssociateDetailsResPayload getMPSAssociateDetails(String env, LinkedHashMap<String, String> params) {
        Response response = PackageFlowRequests.getMPSAssociateDetails(env, params);
        return response.as(MPSAssociateDetailsResPayload.class);
    }

    public ReBagResPayload reBag(List<Map<String, Object>> data, String env) {
        ReBagReqPayload reBagReqPayload = RequestBuilder.reBagReqPayload(data);
        Response response = PackageFlowRequests.reBag(env, reBagReqPayload);
        return response.as(ReBagResPayload.class);
    }

    public void partialManifestImprovements(String scenario, String wbn, HashMap<String, String> data) {
        Response response = PackageFlowRequests.getPackageDetails("ref_ids", wbn, data);
        assertStatusCode(200, response);

        if(scenario.contains("NO DATA WITHOUT MANIFESTATION")){
            verifyPackageFetchInfoApi(wbn, "UD", "In Transit", "X-PPONM", data);
        } else if(scenario.contains("NO DATA") || scenario.contains("PARTIALLY MANIFESTED")){
            verifyPackageFetchInfoApi(wbn, "UD", "Manifested", "X-UCI", data);
        }
        PackageDetail[] pkg_Details = response.as(PackageDetail[].class);
        PackageDetail pkgDetails = pkg_Details[0];

        assertKeyValue("Phone", pkgDetails.ucid.uci, pkgDetails.ucid.uci);
        assertKeyValue("Name", pkgDetails.nm, pkgDetails.nm);
        assertKeyValue("Address", pkgDetails.add_pii, pkgDetails.add_pii);
    }
}
package controller;

import application.ExpressAPI;
import builder.DTOBuilder;
import builder.ExpressRequestBuilder;
import dto.WaybillDTO;
import ewaybill.factory.EWaybillFactory;
import io.restassured.response.Response;
import pojo.AddBagToTrip.request.AddBagToTripRequestPayload;
import pojo.AddBagToTrip.response.AddBagToTripResponsePayload;
import pojo.ApplyNslApi.request.ApplyNslRequestPayload;
import pojo.ApplyNslApi.response.ApplyNslResponsePayload;
import pojo.BagIncomingApi.request.BagIncomingRequestPayload;
import pojo.BagIncomingApi.response.BagIncomingResponsePayload;
import pojo.FMNewPickup.request.CreateNewFmRequestPayload;
import pojo.FMNewPickup.response.CreateNewFMPickupResPayload;
import pojo.FM.request.FMOMSRequestPayload;
import pojo.FM.response.FMOMSResponsePayload;
import pojo.FetchPackgeDetails.response.PackageInfoResPayload;
import pojo.FmApiIncoming.Request.FmApiIncomingRequestPayload;
import pojo.FmApiIncoming.Response.FmApiIncomingResponsePayload;
import pojo.GI.request.GIRequestPayload;
import pojo.GI.response.GIResponsePayload;
import pojo.TripIncomingApi.request.TripIncomingRequestPayload;
import pojo.TripIncomingApi.response.TripIncomingResponsePayload;
import pojo.bag.BagV3Api.request.BagV3RequestPayload;
import pojo.bag.BagV3Api.response.BagV3ResponsePayload;
import pojo.bag.CreateBagV2Api.Request.CreateBagV2ApiRequestPayload;
import pojo.bag.CreateBagV2Api.Response.CreateBagV2ApiResponsePayload;
import pojo.bag.insta.InstaBaggingCreateApi.request.InstaBaggingCreateRequestPayload;
import pojo.bag.insta.InstaBaggingCreateApi.response.InstaBaggingCreateResponsePayload;
import pojo.bag.insta.InstaBaggingSealApi.request.InstaBaggingSealRequestPayload;
import pojo.bag.insta.InstaBaggingSealApi.response.InstaBaggingSealResponsePayload;
import pojo.client.ClientCreateUpdate.request.ClientCreateUpdateReqPayload;
import pojo.client.ClientCreateUpdate.response.CreateUpdateClientResponsePayload;
import pojo.client.FetchClientDetails.FetchClientDetailsResponsePayload;
import pojo.manifest.request.CMUManifestRequestPayload;
import pojo.manifest.response.CMUResponsePayload;
import common.template.ExpressHttpReqObject;
import common.utils.ExpressUtilities;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class ExpressAPIController {
    public CMUResponsePayload manifestWBN(List<Map<String, Object>> cmuShipmentPayloadList, List<Map<String, Object>> qcDataList, Map<String, Object> pickupLocationData, String url, String token) {
        CMUManifestRequestPayload cmuManifestRequestPayload = ExpressRequestBuilder.cmuManifestRequestPayload(cmuShipmentPayloadList, qcDataList, pickupLocationData);
        String json = "data=" + ExpressUtilities.jsonObjectToString(cmuManifestRequestPayload) + "&format=json";
        ExpressHttpReqObject expressHttpReqObject = ExpressHttpReqObject.builder()
                .requestBody(json)
                .url(url)
                .token(token)
                .build();
        Response response = ExpressAPI.executeCmuManifest(expressHttpReqObject);
        return response.as(CMUResponsePayload.class);
    }

    public CreateUpdateClientResponsePayload createUpdateClient(Map<String, Object> clientCreateUpdate, String url, String token) {
        ClientCreateUpdateReqPayload clientCreateUpdateReqPayload = ExpressRequestBuilder.clientCreateUpdateReqGen(clientCreateUpdate);
        String json = ExpressUtilities.jsonObjectToString(clientCreateUpdateReqPayload);
        ExpressHttpReqObject expressHttpReqObject = ExpressHttpReqObject.builder()
                .requestBody(json)
                .url(url)
                .token(token)
                .build();
        Response response = ExpressAPI.executeCreateUpdateClient(expressHttpReqObject);
        return response.as(CreateUpdateClientResponsePayload.class);
    }

    public FetchClientDetailsResponsePayload fetchClientDetails(String clientName, String url, String token) {
        LinkedHashMap<String, String> param = new LinkedHashMap<>();
        param.put("client", clientName);
        ExpressHttpReqObject expressHttpReqObject = ExpressHttpReqObject.builder()
                .url(url)
                .queryParam(param)
                .token(token)
                .build();
        Response response = ExpressAPI.executeFetchClientDetails(expressHttpReqObject);
        return response.as(FetchClientDetailsResponsePayload.class);
    }

    public PackageInfoResPayload fetchPackageInfo(Map<String, Object> data) {
        ExpressHttpReqObject expressHttpReqObject = ExpressHttpReqObject.builder()
                .url(data.get("url").toString())
                .token(data.get("token").toString())
                .pathParam(data.get("waybill").toString())
                .build();
        Response response = ExpressAPI.getPackageDetails(expressHttpReqObject);
        PackageInfoResPayload[] packageInfoResPayloads = response.as(PackageInfoResPayload[].class);
        return packageInfoResPayloads[0];
    }

    public static CreateNewFMPickupResPayload createNewFMPickup(Map<String, Object> data) {
        CreateNewFmRequestPayload payload = ExpressRequestBuilder.createNewFmRequestPayload(data);
        String json = ExpressUtilities.jsonObjectToString(payload);

        ExpressHttpReqObject expressHttpReqObject = ExpressHttpReqObject.builder()
                .requestBody(json)
                .url(data.get("url").toString())
                .token(data.get("token").toString())
                .build();
        Response response = ExpressAPI.createNewFMPickup(expressHttpReqObject);
        return response.as(CreateNewFMPickupResPayload.class);
    }

    public FMOMSResponsePayload fMIncomingByOMSAPI(Map<String, Object> data, List<String> waybills) {
        FMOMSRequestPayload body = ExpressRequestBuilder.fmOmsReqPayload(data, waybills);
        String json = ExpressUtilities.jsonObjectToString(body);

        ExpressHttpReqObject expressHttpReqObject = ExpressHttpReqObject.builder()
                .requestBody(json)
                .url(data.get("url").toString())
                .token(data.get("token").toString())
                .build();

        Response response = ExpressAPI.doFMIncomingByOMSAPI(expressHttpReqObject);
        return response.as(FMOMSResponsePayload.class);
    }

    public List<FmApiIncomingResponsePayload> fmIncomingByFMIncomingAPI(String waybill, Map<String, Object> data) {
        FmApiIncomingRequestPayload request = ExpressRequestBuilder.fmApiIncomingReqGen(waybill, data);
        String json = ExpressUtilities.jsonObjectToString(request);

        ExpressHttpReqObject expressHttpReqObject = ExpressHttpReqObject.builder()
                .requestBody(json)
                .url(data.get("url").toString())
                .token(data.get("token").toString())
                .build();

        Response response = ExpressAPI.doFMIncomingByFMIncomingAPI(expressHttpReqObject);
        FmApiIncomingResponsePayload[] responseArray = response.as(FmApiIncomingResponsePayload[].class);
        return Arrays.asList(responseArray);
    }

    public GIResponsePayload performGIOnPackages(List<String> waybills, Map<String, Object> data) {
        GIRequestPayload body = ExpressRequestBuilder.giRequestPayload(waybills, data);
        ExpressHttpReqObject expressHttpReqObject = ExpressHttpReqObject.builder()
                .requestBody(body)
                .url(data.get("url").toString())
                .token(data.get("token").toString())
                .build();

        Response response = ExpressAPI.doGI(expressHttpReqObject);
        return response.as(GIResponsePayload.class);
    }

    public InstaBaggingCreateResponsePayload createInstaBag(Map<String, Object> data) {
        InstaBaggingCreateRequestPayload body = ExpressRequestBuilder.instaBaggingCreateRequestPayload(data);
        String json = ExpressUtilities.jsonObjectToString(body);

        ExpressHttpReqObject expressHttpReqObject = ExpressHttpReqObject.builder()
                .requestBody(json)
                .url(data.get("url").toString())
                .token(data.get("token").toString())
                .build();

        Response response = ExpressAPI.createIntaBag(expressHttpReqObject);
        return response.as(InstaBaggingCreateResponsePayload.class);
    }

    public InstaBaggingSealResponsePayload sealInstaBag(Map<String, Object> data) {
        InstaBaggingSealRequestPayload body = ExpressRequestBuilder.instaBaggingSealReqPayload(data);
        String json = ExpressUtilities.jsonObjectToString(body);

        ExpressHttpReqObject expressHttpReqObject = ExpressHttpReqObject.builder()
                .requestBody(json)
                .url(data.get("url").toString())
                .token(data.get("token").toString())
                .build();

        Response response = ExpressAPI.createIntaBag(expressHttpReqObject);
        return response.as(InstaBaggingSealResponsePayload.class);
    }

    public CreateBagV2ApiResponsePayload createBagV2(Map<String, Object> data, List<Map<String, Object>> waybillDataMap) {
        CreateBagV2ApiRequestPayload body = ExpressRequestBuilder.bagV2ApiReqGen(data, waybillDataMap);
        String json = ExpressUtilities.jsonObjectToString(body);

        ExpressHttpReqObject expressHttpReqObject = ExpressHttpReqObject.builder()
                .requestBody(json)
                .url(data.get("url").toString())
                .token(data.get("token").toString())
                .build();

        Response response = ExpressAPI.createBagV2(expressHttpReqObject);
        return response.as(CreateBagV2ApiResponsePayload.class);
    }

    public BagV3ResponsePayload createBagV3(Map<String, Object> data, List<Map<String, Object>> wayDataList) {
        BagV3RequestPayload body = ExpressRequestBuilder.bagV3ApiReqGen(data, wayDataList);
        String json = ExpressUtilities.jsonObjectToString(body);

        ExpressHttpReqObject requestPayload = ExpressHttpReqObject.builder()
                .requestBody(json)
                .url(data.get("url").toString())
                .token(data.get("token").toString())
                .build();

        Response response = ExpressAPI.createBagV3(requestPayload);
        return response.as(BagV3ResponsePayload.class);
    }

    public Map<String, AddBagToTripResponsePayload> addBagToTrip(Map<String, Object> data) {
        AddBagToTripRequestPayload body = ExpressRequestBuilder.bagAddToTripApiReqGen(data);
        String json = ExpressUtilities.jsonObjectToString(body);

        ExpressHttpReqObject expressHttpReqObject = ExpressHttpReqObject.builder()
                .requestBody(json)
                .url(data.get("url").toString())
                .token(data.get("token").toString())
                .build();

        Response response = ExpressAPI.addBagToTrip(expressHttpReqObject);
        AddBagToTripResponsePayload addBagToTripResponsePayload = response.as(AddBagToTripResponsePayload.class);
        Map<String, AddBagToTripResponsePayload> responseMap = new LinkedHashMap<>();
        responseMap.put(body.getTrid(), addBagToTripResponsePayload);
        return responseMap;
    }

    public TripIncomingResponsePayload doTripIncoming(Map<String, Object> data) {
        TripIncomingRequestPayload body = ExpressRequestBuilder.tripIncomingApiReqGen(data);
        String json = ExpressUtilities.jsonObjectToString(body);

        ExpressHttpReqObject expressHttpReqObject = ExpressHttpReqObject.builder()
                .requestBody(json)
                .url(data.get("url").toString())
                .token(data.get("token").toString())
                .build();

        Response response = ExpressAPI.tripIncoming(expressHttpReqObject);
        return response.as(TripIncomingResponsePayload.class);
    }

    public BagIncomingResponsePayload doBagIncoming(Map<String, Object> data) {
        BagIncomingRequestPayload body = ExpressRequestBuilder.bagIncomingApiReqGen(data);
        String json = ExpressUtilities.jsonObjectToString(body);

        ExpressHttpReqObject expressHttpReqObject = ExpressHttpReqObject.builder()
                .requestBody(json)
                .url(data.get("url").toString())
                .token(data.get("token").toString())
                .build();

        Response response = ExpressAPI.bagIncoming(expressHttpReqObject);
        return response.as(BagIncomingResponsePayload.class);
    }

    public ApplyNslResponsePayload applyNsl(Map<String, Object> data, List<String> waybills) {
        ApplyNslRequestPayload applyNslRequestPayload = ExpressRequestBuilder.applyNslRequestPayload(data, waybills);
        String json = ExpressUtilities.jsonObjectToString(applyNslRequestPayload);

        ExpressHttpReqObject expressHttpReqObject = ExpressHttpReqObject.builder()
                .requestBody(json)
                .url(data.get("url").toString())
                .token(data.get("token").toString())
                .build();

        Response response = ExpressAPI.applyNSL(expressHttpReqObject);
        return response.as(ApplyNslResponsePayload.class);
    }

    public List<WaybillDTO> getWaybillsDTOList(Map<String, Object> data, List<String> waybills) {
        Map<String, List<String>> masterToChildMap = new HashMap<>();
        Map<String, String> masterToPaymentModeMap = new HashMap<>();

        //Determine master and child waybills
        waybills.forEach(wbn -> {
            data.put("waybill", wbn);
            PackageInfoResPayload packageInfoResPayload = fetchPackageInfo(data);

            String master;
            if (packageInfoResPayload.getMwn() == null || packageInfoResPayload.getMwn().isEmpty()) {
                master = wbn;
            } else {
                master = packageInfoResPayload.getMwn();
            }

            masterToChildMap.putIfAbsent(master, new ArrayList<>());
            masterToChildMap.computeIfAbsent(master, k -> new ArrayList<>()).add(wbn);
            masterToPaymentModeMap.putIfAbsent(master, packageInfoResPayload.getPt());
        });

        return DTOBuilder.buildWaybillDTOList(masterToChildMap, masterToPaymentModeMap);
    }

    //Generate e-waybill as per the number of required e-waybills
    public List<String> getEWaybill(int noOFRequiredEWaybill){
        return EWaybillFactory.getEWaybill(noOFRequiredEWaybill);
    }

}

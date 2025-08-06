package application;

import rest.ExpressRestResource;
import io.restassured.response.Response;
import common.template.ExpressHttpReqObject;
import common.route.ExpressRoutes;

public class ExpressAPI {
    public static Response executeCmuManifest(ExpressHttpReqObject expressHttpReqObject) {
        return ExpressRestResource.post(ExpressRoutes.API_CMU_CREATE_JSON, expressHttpReqObject);
    }

    public static Response executeCreateUpdateClient(ExpressHttpReqObject expressHttpReqObject) {
        return ExpressRestResource.put(ExpressRoutes.CLIENT_CREATE_UPDATE, expressHttpReqObject);
    }

    public static Response executeFetchClientDetails(ExpressHttpReqObject expressHttpReqObject) {
        return ExpressRestResource.get(ExpressRoutes.FETCH_CLIENT_DETAILS, expressHttpReqObject);
    }

    public static Response getPackageDetails(ExpressHttpReqObject expressHttpReqObject) {
        return ExpressRestResource.get(ExpressRoutes.API_P_INFO + expressHttpReqObject.getPathParam() + "/.json", expressHttpReqObject);
    }

    public static Response createNewFMPickup(ExpressHttpReqObject expressHttpReqObject) {
        return ExpressRestResource.post(ExpressRoutes.FM_REQUEST_NEW, expressHttpReqObject);
    }

    public static Response doFMIncomingByOMSAPI(ExpressHttpReqObject expressHttpReqObject) {
        return ExpressRestResource.post(ExpressRoutes.FM_OMS_SHIPMENT, expressHttpReqObject);
    }

    public static Response doFMIncomingByFMIncomingAPI(ExpressHttpReqObject expressHttpReqObject) {
        return ExpressRestResource.post(ExpressRoutes.FM_API_INCOMING, expressHttpReqObject);
    }

    public static Response doGI(ExpressHttpReqObject expressHttpReqObject) {
        return ExpressRestResource.post(ExpressRoutes.GI_SHIPMENT, expressHttpReqObject);
    }

    public static Response createIntaBag(ExpressHttpReqObject httpReqObject) {
        return ExpressRestResource.post(ExpressRoutes.INSTA_BAGGING, httpReqObject);
    }

    public static Response createBagV2(ExpressHttpReqObject expressHttpReqObject) {
        return ExpressRestResource.post(ExpressRoutes.BAG_V2_SHIPMENT, expressHttpReqObject);
    }

    public static Response createBagV3(ExpressHttpReqObject expressHttpReqObject) {
        return ExpressRestResource.post(ExpressRoutes.BAG_V3_SHIPMENT, expressHttpReqObject);
    }

    public static Response bagIncoming(ExpressHttpReqObject expressHttpReqObject) {
        return ExpressRestResource.put(ExpressRoutes.BAG_INCOMING, expressHttpReqObject);
    }

    public static Response addBagToTrip(ExpressHttpReqObject expressHttpReqObject) {
        return ExpressRestResource.put(ExpressRoutes.BAG_ADD_TO_TRIP, expressHttpReqObject);
    }

    public static Response tripIncoming(ExpressHttpReqObject expressHttpReqObject) {
        return ExpressRestResource.put(ExpressRoutes.TRIP_INCOMING, expressHttpReqObject);
    }

    public static Response applyNSL(ExpressHttpReqObject expressHttpReqObject) {
        return ExpressRestResource.post(ExpressRoutes.APPLY_NSL_API,expressHttpReqObject);
    }
}

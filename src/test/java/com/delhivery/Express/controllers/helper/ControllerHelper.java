package com.delhivery.Express.controllers.helper;

import com.delhivery.Express.applicationApi.PackageFlowRequests;
import com.delhivery.Express.controllers.api.ApiController;
import com.delhivery.Express.pojo.FetchPackageDetails.response.PackageDetail;
import com.delhivery.Express.pojo.GIApi.response.GIResponsePayload;
import com.delhivery.Express.pojo.GetNoDataUplShipmentApi.response.GetNoDataUplShipmentApiResponsePayload;
import com.delhivery.core.utils.CoreConstants;
import com.delhivery.core.utils.Utilities;
import io.restassured.response.Response;

import java.util.HashMap;
import java.util.List;

import static com.delhivery.core.utils.Assertions.assertKeyValue;
import static com.delhivery.core.utils.Assertions.assertStatusCode;

public class ControllerHelper {
    private static final ThreadLocal<PackageDetail> packageDetails = new ThreadLocal<>();
    public static final ThreadLocal<List<String>> waybillList = new ThreadLocal<>();
    private static final ThreadLocal<GIResponsePayload> giResponsePayload = new ThreadLocal<>();
    private static final ThreadLocal<Response> response = new ThreadLocal<>();

    public static void waitForNextTry(String msg){
        Utilities.logInfo(msg+" "+CoreConstants.WAIT_THRESHOLD +" seconds");
        System.out.println("Apply wait");
        Utilities.hardWait(CoreConstants.WAIT_THRESHOLD);
    }

    private static Boolean shouldWaitForGI(){
        if(giResponsePayload.get().getData().get(0).getError()==null){
            Utilities.logInfo("GI NSL Applied Successfully !!!!");
            return false;
        } else if (giResponsePayload.get().getData().get(0).getError().contains("Invalid status for package")) {
            System.out.println(giResponsePayload.get().getData().get(0).getError() + " Terminating the wait !!!");
            return false;
        }
        return true;
    }

    public static void doGIWithRetry(String enviorment, Object body, String client ){
        response.set(PackageFlowRequests.giShipment(enviorment, body, client));
        assertStatusCode(200, response.get());

        giResponsePayload.set(response.get().as(GIResponsePayload.class));

        for(int doRetry = 0; doRetry< CoreConstants.MAX_RETRY; doRetry++){
            if(!shouldWaitForGI()){
                break;
            }
            waitForNextTry("GI NSL does not applied on package waiting for next ");
            response.set(PackageFlowRequests.giShipment(enviorment, body, client));
            giResponsePayload.set(response.get().as(GIResponsePayload.class));
        }
        assertKeyValue("status", 1, giResponsePayload.get().getStatus());
    }

    public static void doGIWithRetry(Object body, HashMap<String,String> data){
        response.set(PackageFlowRequests.giShipment(body, data));
        assertStatusCode(200, response.get());
        giResponsePayload.set(response.get().as(GIResponsePayload.class));
        for(int doRetry =0; doRetry< CoreConstants.MAX_RETRY;doRetry++){
            if(!shouldWaitForGI()){
                break;
            }
            waitForNextTry("GI NSL does not applied on package waiting for next ");
            response.set(PackageFlowRequests.giShipment(body, data));
            giResponsePayload.set(response.get().as(GIResponsePayload.class));
        }
        assertKeyValue("status", 1, giResponsePayload.get().getStatus());
    }

    public static void checkPackageCurrentScanWithWait(List<String> waybills, HashMap<String,String>data, String nsl_id ){
        if(data.containsKey("can_apply_nsl")){
            if(!data.get("can_apply_nsl").equalsIgnoreCase("true")){
                Utilities.logInfo("As NSL can not be applied hence terminating the wait !!!");
                return;
            }
        }

        waybillList.set(waybills);
        for(int doRetry =0; doRetry< CoreConstants.MAX_RETRY;doRetry++){
            packageDetails.set(new ApiController().fetchPackageInfo(waybillList.get().get(0),data));
            if(packageDetails.get().getCs().getNsl().equalsIgnoreCase(nsl_id)){
                Utilities.logInfo("NSL : "+nsl_id+" successfully applied on package now terminating further wait...");
                break;
            }
            waitForNextTry("NSL does not applied on package waiting for next");
        }
    }

    public static List<String> checkUPLStatusWithRetry(HashMap<String,String >requestData){
        Response response = PackageFlowRequests.getNoDataUplShipment(requestData);
        assertStatusCode(200, response);
        GetNoDataUplShipmentApiResponsePayload apiResponse = response.as(GetNoDataUplShipmentApiResponsePayload.class);

        for(int doRetry =0; doRetry< CoreConstants.MAX_RETRY;doRetry++){
            if(apiResponse.getStatus().equalsIgnoreCase("Success")){
                break;
            }
            waitForNextTry("UPL does not applied on package waiting for next");
            response = PackageFlowRequests.getNoDataUplShipment(requestData);
            assertStatusCode(200, response);
            apiResponse = response.as(GetNoDataUplShipmentApiResponsePayload.class);
        }
        return apiResponse.waybills;
    }
}

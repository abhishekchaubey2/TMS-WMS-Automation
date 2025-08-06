package com.delhivery.Express.testModules.PackageFlow;

import com.delhivery.Express.RequestBuilder.RequestBuilder;
import com.delhivery.Express.controllers.api.ApiController;
import com.delhivery.Express.controllers.api.DifferentStateShipments;
import com.delhivery.Express.dataprovider.manifestationData;
import com.delhivery.Express.pojo.FetchPackageDetails.response.PackageDetail;
import com.delhivery.core.BaseTest;
import com.delhivery.core.db.DataProviderClass;
import com.delhivery.core.utils.DateTimeUtility;
import com.delhivery.core.utils.Utilities;
import com.delhivery.core.utils.YamlReader;

import org.apache.commons.lang3.RandomStringUtils;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static com.delhivery.core.utils.Assertions.assertKeyValue;
import static com.delhivery.core.utils.Utilities.logInfo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TestScript extends BaseTest {
    DifferentStateShipments differentStateShipments = new DifferentStateShipments();

    private String waybill;
    private String bagId;
    private String tripId;
    private String dispatchId;

    private final String product_type, payment_mode;
    public String scenario;
    public HashMap<String, String> clData = new HashMap<>();
    ApiController apiCtrl = new ApiController();

    public TestScript() {
        DataProviderClass.fileName = "CmuRegressionData";
        DataProviderClass.sheetName = "Pkg_flows";
        product_type = "B2C";
        payment_mode = "COD";
    }

    public void fillHashMap(String product_type, String payment_mode) {
        clData.put("client", "regression_client");
        clData.put("product_type", product_type);
        clData.put("payment_mode", payment_mode);
        clData.put("pin", "400059");
    }

//    @Factory(dataProviderClass = com.delhivery.core.db.DataProviderClass.class, dataProvider = "dataprovider")
//    public TestScript(String scenario, String product_type, String payment_mode) {
//        this.scenario = scenario;
//        this.product_type = product_type;
//        this.payment_mode = payment_mode;
//        client = YamlReader.getYamlValues("Client_Details.client_HQAPIREGRESSION").get("name").toString();
//        //	oc_center = YamlReader.getYamlValues("Centers.Gurgaon");
//        //	cn_center = YamlReader.getYamlValues("Centers.Mumbai_MIDC");
////        cn_center = YamlReader.getYamlValues("Centers." + "Mumbai_MIDC");
////        oc_center = YamlReader.getYamlValues("Centers." + "Gurgaon");
//        ocid = YamlReader.getYamlValues("Centers.Gurgaon").get("SortCode").toString();
//        cnid = YamlReader.getYamlValues("Centers.Mumbai_MIDC").get("SortCode").toString();
//
//    }

    @Test(priority = 1)
    public void manifest() {
        clData.put("client", "regression_client");
        HashMap<String, String> data = new HashMap<>();
        data.put("product_type", product_type);
        data.put("payment_mode", payment_mode);
        data.put("pin", "400059");

        ArrayList<String> waybills = apiCtrl.cmuManifestApi(data);
        waybill = waybills.get(0);
        logInfo("Waybill generated " + waybill);
        apiCtrl.verifyPackageFetchInfoApi(waybill, "UD", "Manifested", "X-UCI", clData);
        if (product_type.equals("B2B")) {
            Utilities.hardWait(70);
        }
    }

    @Test(dependsOnMethods = "manifest")
    public void fmOmsFmPick() {
        clData.put("client", "regression_client");
        apiCtrl.fmOMSApi(waybill, "FMPICK", clData);
        apiCtrl.verifyPackageFetchInfoApi(waybill, "UD", "In Transit", "X-PPOM", clData);
    }

    @Test(dependsOnMethods = "fmOmsFmPick")
    public void fmOmsFmDepart() {
        clData.put("client", "regression_client");
        apiCtrl.fmOMSApi(waybill, "FMDEPART", clData);
        apiCtrl.verifyPackageFetchInfoApi(waybill, "UD", "In Transit", "FMDEPART-101", clData);
    }

    @Test(dependsOnMethods = "fmOmsFmDepart")
    public void genericIncomingAtOrigin() {
        clData.put("client", "regression_client");
        apiCtrl.giApi(waybill, RequestBuilder.origin_center.get("Name").toString(), clData);
        apiCtrl.verifyPackageFetchInfoApi(waybill, "UD", "In Transit", "X-PIOM", clData);
    }

    @Test(dependsOnMethods = "genericIncomingAtOrigin")
    public void createV3Bag() {
        clData.put("client", "regression_client");
        bagId = apiCtrl.bagv3Api(waybill);
        logInfo("Bag created " + bagId);
        Utilities.hardWait(60);
        apiCtrl.verifyPackageFetchInfoApi(waybill, "UD", "In Transit", "X-DBL1F", clData);
        apiCtrl.verifyBagFetchInfoApi(bagId, "In Transit", "+S", null);
    }

    @Test
    public void createV4Bag() {
        clData.put("client", "regression_client");
        HashMap<String, String> data = new HashMap<>();
        data.put("product_type", product_type);
        data.put("payment_mode", payment_mode);
        data.put("pin", "400059");

        List<String> waybills = apiCtrl.cmuManifestApi(data);
        String wbn = waybills.get(0);
        apiCtrl.fmOMSApi(wbn, "FMPICK", clData);
        apiCtrl.fmOMSApi(wbn, "FMDEPART", clData);
        apiCtrl.giApi(wbn, RequestBuilder.origin_center.get("Name").toString(), clData);
        String bagId = apiCtrl.bagv4Api(wbn);
        logInfo("Bag created " + bagId);
    }

    @Test(dependsOnMethods = "createV3Bag")
    public void bagAddToTrip() {
        clData.put("client", "regression_client");
        tripId = apiCtrl.bagAddToTripApi(bagId);
        logInfo("Trip created " + tripId);
        apiCtrl.verifyPackageFetchInfoApi(waybill, "UD", "In Transit", "X-DLL2F", clData);
        apiCtrl.verifyBagFetchInfoApi(bagId, "In Transit", "+L", tripId);
    }

    @Test(dependsOnMethods = "bagAddToTrip")
    public void tripIncoming() {
        clData.put("client", "regression_client");
        apiCtrl.tripIncomingApi(tripId);
        apiCtrl.verifyPackageFetchInfoApi(waybill, "UD", "In Transit", "X-ILL2F", clData);
        apiCtrl.verifyBagFetchInfoApi(bagId, "In Transit", "<L", tripId);
    }

    @Test(dependsOnMethods = "tripIncoming")
    public void bagIncoming() {
        clData.put("client", "regression_client");
        apiCtrl.bagIncomingApi(bagId);
        apiCtrl.verifyPackageFetchInfoApi(waybill, "UD", "In Transit", "X-ILL1F", clData);
        apiCtrl.verifyBagFetchInfoApi(bagId, "Pending", "<B", null);
    }

    @Test(dependsOnMethods = "bagIncoming")
    public void genericIncomingAtDestination() {
        clData.put("client", "regression_client");
        apiCtrl.giApi(waybill, RequestBuilder.destination_center.get("Name").toString(), clData);
        apiCtrl.verifyPackageFetchInfoApi(waybill, "UD", "Pending", "X-IBD3F", clData);
        apiCtrl.verifyBagFetchInfoApi(bagId, "Complete", null, null);
    }

    @Test(dependsOnMethods = "genericIncomingAtDestination")
    public void markShipmentDispatched() {
        clData.put("client", "regression_client");
        dispatchId = apiCtrl.markShipmentDispatchApi(waybill, clData);
        logInfo("Dispatch created " + dispatchId);
        apiCtrl.verifyPackageFetchInfoApi(waybill, "UD", "Dispatched", "X-DDD3FD", clData);
        PackageDetail pkgDetails = apiCtrl.fetchPackageInfo(waybill, clData);
        assertKeyValue("Shipment dispatch Id", dispatchId, pkgDetails.dd.id.toString());
    }

    @Test(dependsOnMethods = "markShipmentDispatched")
    public void lmUpdateHQShipment() {
        apiCtrl.lmUpdateHQShipmentApi(waybill, "Delivered", clData);
        apiCtrl.verifyPackageFetchInfoApi(waybill, "DL", "Delivered", "EOD-38", clData);
    }

    @Test(dependsOnMethods = "lmUpdateHQShipment")
    public void unsetShipmentDispatchId() {
        apiCtrl.unsetShipmentDispatchIdApi(waybill, dispatchId, clData);
        apiCtrl.verifyPackageFetchInfoApi(waybill, "DL", "Delivered", "EOD-38", clData);
        PackageDetail pkgDetails = apiCtrl.fetchPackageInfo(waybill, clData);
        if (pkgDetails.dd.id == null) {
            assertKeyValue("Shipment dispatch Id", "null", "null");
        } else {
            assertThat(0, equalTo(1));
        }
    }

    @Test
    public void fetchClientDetails() {
        String client = YamlReader.getYamlValues("Client_Details.client_HQAPIREGRESSION").get("name").toString();
        apiCtrl.verifyFetchClientDetailsApi(client);
    }

    @Test
    public void dispatchFreeze() {
        clData.put("client", "regression_client");
        HashMap<String, String> data = new HashMap<>();
        data.put("product_type", product_type);
        data.put("payment_mode", payment_mode);
        data.put("pin", "400059");

        List<String> waybills = apiCtrl.cmuManifestApi(data);
        String wbn = waybills.get(0);
        apiCtrl.verifyDispatchFreezeApi(wbn);
    }

    @Test
    public void markPending() {
        clData.put("client", "regression_client");
        HashMap<String, String> data = new HashMap<>();
        data.put("product_type", product_type);
        data.put("payment_mode", payment_mode);
        data.put("pin", "400059");

        List<String> waybills = apiCtrl.cmuManifestApi(data);
        String wbn = waybills.get(0);
        apiCtrl.verifyMarkPendingApi(wbn);
    }

    @Test
    public void markDelivered() {
        clData.put("client", "regression_client");
        HashMap<String, String> data = new HashMap<>();
        data.put("product_type", product_type);
        data.put("payment_mode", payment_mode);
        data.put("pin", "400059");

        List<String> waybills = apiCtrl.cmuManifestApi(data);
        String wbn = waybills.get(0);
        apiCtrl.verifyMarkDeliveredApi(wbn);
    }

    @Test
    public void fetchBagMatrix() {
        apiCtrl.verifyFetchBagMatrixApi("IND122001AAB");
    }

//    @Test
//    public void bulkNdrEdit() {
//    	clData.put("client", "regression_client");
//    	HashMap<String,String> data = new HashMap<String,String>();
//		data.put("product_type",product_type);
//		data.put("payment_mode", payment_mode);
//		data.put("pin", "400059");
//
//		waybills = apiCtrl.cmuManifestApi(data);
//		wbn = waybills.get(0);
//        apiCtrl.verifyBulkEditNdr(wbn, "EDIT_DETAILS", clData);
//    }
//

//    @Test
//    public void kinkoInvoiceCharge() {
//    	HashMap<String,String> data = new HashMap<String,String>();
//    	data.put("token", "7f28215509848801c2944426043509d8f4f852d2");
//    	data.put("pt", "prepaid");
//    	data.put("gm", "500");
//    	data.put("o_pin", "282001");
//    	data.put("d_pin", "282003");
//        apiCtrl.verifyKinkoInvoiceCharge(data);
//    }

    @Test
    public void clientUpdate() {
        apiCtrl.verifyClientUpdate();
    }

//    @Test
//    public void fetchClientWbn() {
//    	HashMap<String,String> data = new HashMap<String,String>();
//    	data.put("cl", "HQAPIREGRESSION");
//    	data.put("consume", "False");
//    	apiCtrl.verifyFetchClienWbn(data);
//    }
//
//    @Test
//    public void removeEwbn() {
//    	apiCtrl.verifyRemoveEwbn("131001803694", "test");
//    }

//    @Test
//    public void packageStatus() {
//        clData.put("client", "regression_client");
//        HashMap<String, String> data = new HashMap<String, String>();
//        data.put("product_type", product_type);
//        data.put("payment_mode", payment_mode);
//        data.put("pin", "400059");
//
//        waybills = apiCtrl.cmuManifestApi(data);
//        wbn = waybills.get(0);
//        apiCtrl.verifyPackageStatus(wbn);
//    }

    @Test
    public void packageAction() {
        clData.put("client", "regression_client");
        HashMap<String, String> data = new HashMap<>();
        data.put("product_type", product_type);
        data.put("payment_mode", payment_mode);
        data.put("pin", "400059");

        List<String> waybills = apiCtrl.cmuManifestApi(data);
        String wbn = waybills.get(0);
        apiCtrl.verifyPackageAction(wbn);
    }

//    @Test
//    public void bagCustodyScan() {
//    	apiCtrl.verifyBagCustodyScan();
//    }

    @Test
    public void createV2Bag() {
        clData.put("client", "regression_client");
        HashMap<String, String> data = new HashMap<>();
        data.put("product_type", product_type);
        data.put("payment_mode", payment_mode);
        data.put("pin", "400059");

        List<String> waybills = apiCtrl.cmuManifestApi(data);
        String wbn = waybills.get(0);
        apiCtrl.fmOMSApi(wbn, "FMPICK", clData);
        apiCtrl.fmOMSApi(wbn, "FMDEPART", clData);
        apiCtrl.giApi(wbn, RequestBuilder.origin_center.get("Name").toString(), clData);
        String bagId1 = apiCtrl.bagv2Api(wbn);
        Utilities.hardWait(30);
        logInfo("Bag created " + bagId1);
    }

    @Test
    public void autobag() {
        clData.put("client", "regression_client");
        HashMap<String, String> data = new HashMap<>();
        data.put("product_type", product_type);
        data.put("payment_mode", payment_mode);
        data.put("pin", "400059");

        List<String> waybills = apiCtrl.cmuManifestApi(data);
        String wbn = waybills.get(0);
        apiCtrl.fmOMSApi(wbn, "FMPICK", clData);
        apiCtrl.fmOMSApi(wbn, "FMDEPART", clData);
        apiCtrl.giApi(wbn, RequestBuilder.origin_center.get("Name").toString(), clData);
        apiCtrl.verifyAutoBag(wbn, RequestBuilder.origin_center.get("Name").toString());

    }

    @Test
    public void fmApiIncoming() {
        clData.put("client", "regression_client");
        HashMap<String, String> data = new HashMap<>();
        data.put("product_type", product_type);
        data.put("payment_mode", payment_mode);
        data.put("pin", "400059");

        List<String> waybills = apiCtrl.cmuManifestApi(data);
        String wbn = waybills.get(0);
        apiCtrl.verifyFmIncoming(wbn, RequestBuilder.origin_center.get("Name").toString());
    }

    @Test
    public synchronized void clientCreateUpdate() {
        String cl_name = "Client" + RandomStringUtils.randomNumeric(8);
        apiCtrl.verifyClientCreateUpdate(cl_name, null);
    }


//    @Test
//    public void pkgCount() {
//    	apiCtrl.verifyPkgCount();
//    }
//
//    @Test
//    public void fetchDC() {
//    	apiCtrl.verifyFetchDC(RequestBuilder.destination_center.get("Name").toString());
//    }

//    @Test
//    public void fetchCity() {
//    	apiCtrl.verifyFetchCity("Bihar");
//    }

//    @Test
//    public void fetchPincodeInfo() {
//    	apiCtrl.verifyPincodeInfo("122003");
//    }

    @Test
    public void packingSlip() {
        clData.put("client", "regression_client");
        HashMap<String, String> data = new HashMap<>();
        data.put("product_type", product_type);
        data.put("payment_mode", payment_mode);
        data.put("pin", "400059");

        List<String> waybills = apiCtrl.cmuManifestApi(data);
        String wbn = waybills.get(0);
        apiCtrl.verifyPackingSlip(wbn);
    }

    @Test
    public void packageCancel() {
        clData.put("client", "regression_client");
        HashMap<String, String> data = new HashMap<>();
        data.put("product_type", product_type);
        data.put("payment_mode", payment_mode);
        data.put("pin", "400059");

        List<String> waybills = apiCtrl.cmuManifestApi(data);
        String wbn = waybills.get(0);
        apiCtrl.verifyPackageCancel(wbn);
    }

//    @Test
//    public void fetchDCCenter() {
//    	apiCtrl.verifyFetchDcCenter();
//    }

    @Test
    public void packageUpdate() {
        clData.put("client", "regression_client");
        HashMap<String, String> data = new HashMap<>();
        data.put("product_type", product_type);
        data.put("payment_mode", payment_mode);
        data.put("pin", "400059");

        List<String> waybills = apiCtrl.cmuManifestApi(data);
        String wbn = waybills.get(0);
        apiCtrl.verifyPackageUpdate(wbn);
    }

    @Test
    public void locationAssociate() {
        clData.put("client", "regression_client");
        HashMap<String, String> data = new HashMap<>();
        data.put("product_type", product_type);
        data.put("payment_mode", payment_mode);
        data.put("pin", "400059");

        List<String> waybills = apiCtrl.cmuManifestApi(data);
        String wbn = waybills.get(0);
        apiCtrl.fmOMSApi(wbn, "FMPICK", clData);
        apiCtrl.fmOMSApi(wbn, "FMDEPART", clData);
        apiCtrl.giApi(wbn, RequestBuilder.origin_center.get("Name").toString(), clData);
        apiCtrl.verifyLocationAssociate(wbn, DateTimeUtility.getISTDateTimeWithDayHourMinuteAndSecond(0L, 0L, 0L, 0L));
    }

//    @Test
//    public void kinkoFetch() {
//    	HashMap<String,String> data = new HashMap<String,String>();
//    	data.put("date_filter", "date");
//    	data.put("date_range", "2018-03-20 - 2019-03-27");
//    	data.put("typ", "rem");
//    	apiCtrl.verifyKinkoFetch(data);
//    }

    @Test
    public void clientDetails() {
        String clientToken = "cms::client::c35a5960-dea0-11ed-ac9a-025dba7e1714";
        apiCtrl.verifyClientDetails(clientToken);
    }

//    @Test
//    public void fetchStateInfo() {
//    	apiCtrl.verifyStateInfo();
//    }

//    @Test
//    public void fetchBagInfo() {
//    	clData.put("client", "regression_client");
//    	HashMap<String,String> data = new HashMap<String,String>();
//		data.put("product_type",product_type);
//		data.put("payment_mode", payment_mode);
//		data.put("pin", "400059");
//
//		waybills = apiCtrl.cmuManifestApi(data);
//		wbn = waybills.get(0);
//        apiCtrl.fmOMSApi(wbn, "FMPICK", clData);
//        apiCtrl.fmOMSApi(wbn, "FMDEPART", clData);
//        apiCtrl.giApi(wbn, RequestBuilder.origin_center.get("Name").toString(),clData);
//    	bagId1 = apiCtrl.bagv3Api(wbn);
//    	Utilities.hardWait(30);
//    	apiCtrl.verifyBagInfo(bagId1);
//    }

//    @Test
//    public void newFM() {
//    	apiCtrl.verifyNewFmRequest();
//    }

    @Test
    public void cmuPushManifest() {
        clData.put("client", "regression_client");
        HashMap<String, String> data = new HashMap<>();
        data.put("product_type", product_type);
        data.put("payment_mode", payment_mode);
        data.put("pin", "400059");
        ArrayList<String> cmuWaybills = apiCtrl.verifyCmuPush(data);
        System.out.println("CMU Waybills " + cmuWaybills);
    }

    @Test
    public void selfCollect() {
        clData.put("client", "regression_client");
        HashMap<String, String> data = new HashMap<>();
        data.put("product_type", product_type);
        data.put("payment_mode", payment_mode);
        data.put("pin", "400059");

        List<String> waybills = apiCtrl.cmuManifestApi(data);
        String wbn = waybills.get(0);
        apiCtrl.fmOMSApi(wbn, "FMPICK", clData);
        apiCtrl.fmOMSApi(wbn, "FMDEPART", clData);
        apiCtrl.giApi(wbn, RequestBuilder.origin_center.get("Name").toString(), clData);
        String bagId1 = apiCtrl.bagv3Api(wbn);
        Utilities.hardWait(30);
        String tripId1 = apiCtrl.bagAddToTripApi(bagId1);
        apiCtrl.tripIncomingApi(tripId1);
        apiCtrl.bagIncomingApi(bagId1);
        apiCtrl.giApi(wbn, RequestBuilder.destination_center.get("Name").toString(), clData);
        apiCtrl.verifySelfCollect(wbn, RequestBuilder.destination_center.get("Name").toString());
    }

    @Test
    public void locationDissociate() {
        clData.put("client", "regression_client");
        HashMap<String, String> data = new HashMap<>();
        data.put("product_type", product_type);
        data.put("payment_mode", payment_mode);
        data.put("pin", "400059");

        List<String> waybills = apiCtrl.cmuManifestApi(data);
        String wbn = waybills.get(0);
        apiCtrl.fmOMSApi(wbn, "FMPICK", clData);
        apiCtrl.fmOMSApi(wbn, "FMDEPART", clData);
        apiCtrl.giApi(wbn, RequestBuilder.origin_center.get("Name").toString(), clData);
        apiCtrl.verifyLocationDissociate(wbn,DateTimeUtility.getISTDateTimeWithDayHourMinuteAndSecond(0L, 0L, 0L, 0L));
    }

//    @Test
//    public void fetchPackageDetails() {
//    	clData.put("client", "regression_client");
//    	HashMap<String,String> data = new HashMap<String,String>();
//		data.put("product_type",product_type);
//		data.put("payment_mode", payment_mode);
//		data.put("pin", "400059");
//
//		waybills = apiCtrl.cmuManifestApi(data);
//		wbn = waybills.get(0);
//    	HashMap<String,String> data1 = new HashMap<String,String>();
//    	data1.put("center", "IND360311AAA");
//    	data1.put("scans", "true");
//    	data1.put("format", "json");
//    	data1.put("details", "true");
//    	data1.put("wbn", wbn);
//    	apiCtrl.verifyPackageDetails(data1);
//    }

    @Test
    public void bagCalc() {
        clData.put("client", "regression_client");
        HashMap<String, String> data = new HashMap<>();
        data.put("product_type", product_type);
        data.put("payment_mode", payment_mode);
        data.put("pin", "400059");

        List<String> waybills = apiCtrl.cmuManifestApi(data);
        String wbn = waybills.get(0);
        apiCtrl.fmOMSApi(wbn, "FMPICK", clData);
        apiCtrl.fmOMSApi(wbn, "FMDEPART", clData);
        apiCtrl.giApi(wbn, RequestBuilder.origin_center.get("Name").toString(), clData);
        String bagId1 = apiCtrl.bagv3Api(wbn);
        Utilities.hardWait(30);
        apiCtrl.verifyBagCalc(bagId1);
    }

    @Test
    public void updatedBagMatrix() {
        apiCtrl.verifyUpdatedBagMatrix();
    }

    @Test
    public void editPhone() {
        clData.put("client", "regression_client");
        HashMap<String, String> data = new HashMap<>();
        data.put("product_type", product_type);
        data.put("payment_mode", payment_mode);
        data.put("pin", "400059");

        List<String> waybills = apiCtrl.cmuManifestApi(data);
        String wbn = waybills.get(0);
        apiCtrl.verifyEditPhone(wbn);
    }

    @Test
    public void packageStatusUpdate() {
        clData.put("client", "regression_client");
        HashMap<String, String> data = new HashMap<>();
        data.put("product_type", product_type);
        data.put("payment_mode", payment_mode);
        data.put("pin", "400059");

        List<String> waybills = apiCtrl.cmuManifestApi(data);
        String wbn = waybills.get(0);
        apiCtrl.fmOMSApi(wbn, "FMPICK", clData);
        apiCtrl.fmOMSApi(wbn, "FMDEPART", clData);
        apiCtrl.giApi(wbn, RequestBuilder.origin_center.get("Name").toString(), clData);
        String bagId1 = apiCtrl.bagv3Api(wbn);
        Utilities.hardWait(30);
        String tripId1 = apiCtrl.bagAddToTripApi(bagId1);
        apiCtrl.tripIncomingApi(tripId1);
        apiCtrl.bagIncomingApi(bagId1);
        apiCtrl.giApi(wbn, RequestBuilder.destination_center.get("Name").toString(), clData);
        String dispatchId = apiCtrl.markShipmentDispatchApi(wbn, clData);
        Utilities.logInfo("Dispatch Id " + dispatchId);
        System.out.println();
        apiCtrl.verifyPackageStatusUpdate(wbn, "Delivered", clData);
    }

    @Test
    public void custodyConnection() {
        apiCtrl.verifyCustodyConnection();
    }

    @Test
    public void updateFinalQcWt() {
        clData.put("client", "regression_client");
        HashMap<String, String> data = new HashMap<>();
        data.put("product_type", product_type);
        data.put("payment_mode", payment_mode);
        data.put("pin", "400059");

        List<String> waybills = apiCtrl.cmuManifestApi(data);
        String wbn = waybills.get(0);
        apiCtrl.updateFinalQcWt(wbn);
    }


    @Test()
    public void tripRemoveCheck() {
        fillHashMap(product_type, payment_mode);
        ArrayList<String> waybills = apiCtrl.cmuManifestApi(clData);
        String wbn = waybills.get(0);

        apiCtrl.fmOMSApi(wbn, "FMPICK", clData);
        apiCtrl.fmOMSApi(wbn, "FMDEPART", clData);
        apiCtrl.giApi(wbn, RequestBuilder.origin_center.get("Name").toString(), clData);

//      clData.put("client", "regression_client");
        ArrayList<String> bagId = apiCtrl.bagv3Api(waybills, clData);
        Utilities.hardWait(30);
        ArrayList<String> tripId = apiCtrl.bagAddToTripApi(bagId, clData);
        Utilities.hardWait(7);
        apiCtrl.bagRemoveTripApi(bagId, clData, tripId.get(0));
        Utilities.hardWait(7);
        logInfo("Bag: " + bagId + " removed from trip: " + tripId);
        Map<String, Object> pkgFlowData = YamlReader.getYamlValues("packageFlowScans.removeBagFromTrip");
        apiCtrl.verifyPackageFetchInfoApi(waybills, pkgFlowData.get("statusType").toString(), pkgFlowData.get("scanStatus").toString(), pkgFlowData.get("scanNsl").toString(), clData);
        Map<String, Object> bagFlowData = YamlReader.getYamlValues("bagFlowScans.removeBagFromTrip");
        apiCtrl.verifyBagFetchInfoApi(bagId, bagFlowData.get("scanStatus").toString(), bagFlowData.get("scanAct").toString(), clData);
    }

    @Test()
    public void pkgRemoveFromBagCheck() {
        fillHashMap(product_type, payment_mode);
        ArrayList<String> waybills = apiCtrl.cmuManifestApi(clData);
        String wbn = waybills.get(0);

        apiCtrl.fmOMSApi(wbn, "FMPICK", clData);
        apiCtrl.fmOMSApi(wbn, "FMDEPART", clData);
        apiCtrl.giApi(wbn, RequestBuilder.origin_center.get("Name").toString(), clData);
        clData.put("client", "regression_client");
        ArrayList<String> bagId = apiCtrl.bagv3Api(waybills, clData);
        Utilities.hardWait(30);
        apiCtrl.pkgRemoveBagApi(wbn, clData, bagId.get(0));
        Map<String, Object> pkgFlowData = YamlReader.getYamlValues("packageFlowScans.removePkgFromBag");
        apiCtrl.verifyPackageFetchInfoApi(waybills, pkgFlowData.get("statusType").toString(), pkgFlowData.get("scanStatus").toString(), pkgFlowData.get("scanNsl").toString(), clData);
        Map<String, Object> bagFlowData = YamlReader.getYamlValues("bagFlowScans.removePkgFromBag");
        apiCtrl.verifyBagFetchInfoApi(bagId, bagFlowData.get("scanStatus").toString(), bagFlowData.get("scanAct").toString(), clData);
    }

    @Test
    public void search_pkg() {
        clData.put("client", "regression_client");
        HashMap<String, String> data = new HashMap<>();
        data.put("product_type", product_type);
        data.put("payment_mode", payment_mode);
        data.put("pin", "400059");

        List<String> waybills = apiCtrl.cmuManifestApi(data);
        String wbn = waybills.get(0);
//		waybill = apiCtrl.cmuManifestApi(data);
        logInfo("Waybill generated " + waybills);
        apiCtrl.verifyPackageFetchInfoApi(wbn, "UD", "Manifested", "X-UCI", clData);
        if (product_type.equals("B2B")) {
            Utilities.hardWait(70);
        }
        apiCtrl.searchPackageInfo(wbn);
    }

    @Test
    public void Fetch_mts() {
        clData.put("client", "regression_client");
        HashMap<String, String> data = new HashMap<>();
        data.put("product_type", product_type);
        data.put("payment_mode", payment_mode);
        data.put("pin", "400059");

        List<String> waybills = apiCtrl.cmuManifestApi(data);
        String wbn = waybills.get(0);
        logInfo("Waybill generated " + waybills);
        apiCtrl.verifyPackageFetchInfoApi(wbn, "UD", "Manifested", "X-UCI", clData);
        if (product_type.equals("B2B")) {
            Utilities.hardWait(70);
        }
        apiCtrl.fetchMts(wbn);
    }

    @Test
    public void WHS_STATUS() {
        apiCtrl.whStatusApi("name");
    }

    // @Test
    // public void WHS_EDIT() {
    // apiCtrl.whEditApi("name");
    // }

    @Test
    public void WHS_CREATE() {
        apiCtrl.whCreateApi("name");
    }

//need to fix this api
//	@Test
//	public void Fetch_serv() {
//		apiCtrl.dcServApi("400059");
//	}

    @Test
    public void Fetch_station_list() {
        apiCtrl.dcStationApi("1");

    }

    @Test
    public void Lh_Connections() {
        apiCtrl.lhConnApi("IND122003AAM");
    }

    @DataProvider(name = "PACKAGE_KEYS_COMPARISON", parallel = true)
    public Object[][] package_keys_comparison() {
        return new Object[][]{{"Scenario:: State_having_MANIFEST ", "MANIFEST"},
                {"Scenario:: State_having_PENDING ", "PENDING"},
                {"Scenario:: State_having_DISPATCHED ", "DISPATCHED"},
                {"Scenario:: State_having_RETURNED ", "RETURNED"}, {"Scenario:: State_having_RTO ", "RTO"},
                {"Scenario:: State_having_LOST ", "LOST"},
                {"Scenario:: State_having_PICKUPPENDING ", "PICKUPPENDING"},
                {"Scenario:: State_having_PICKEDUP ", "PICKEDUP"}, {"Scenario:: State_having_DTO ", "DTO"},
                {"Scenario:: State_having_CANCELLED ", "CANCELLED"},
                {"Scenario:: State_having_DELIVERED ", "DELIVERED"},
        };
    }

    @Test(dataProvider = "PACKAGE_KEYS_COMPARISON")
    public void qrcodegenerate1(String Scenario, String State) {
        ThreadLocal<String> wbn = new ThreadLocal<>();
        wbn.set(differentStateShipments.DifferentStateShipments(State));
        ThreadLocal<HashMap<String, Object>> dmap2 = new ThreadLocal<>();

        dmap2.set(getQRCodeGenerate1(wbn.get()).get());
        apiCtrl.fetchQRcode(dmap2.get());
    }

    private ThreadLocal<HashMap<String, Object>> getQRCodeGenerate1(String wbn) {
        return ThreadLocal.withInitial(() -> {
            // Adding elements to the Map
            // using standard put() method
            HashMap<String, Object> dmap2 = new HashMap<>();
            dmap2.put("waybill", wbn);
            dmap2.put("amount", 10);
            dmap2.put("gst", 23.0);
            dmap2.put("sgst", 23.0);
            dmap2.put("cgst", 23.0);
            dmap2.put("igst", 23.0);
            dmap2.put("cess", 23.0);
            dmap2.put("invoice_no", "123456789");
            dmap2.put("invoice_date", "2021-03-25 12:20:16");
            dmap2.put("gst_in", "37AADCB2230M2ZR");
            dmap2.put("gst_incentive", 23.0);
            dmap2.put("invoice_name", "abc");
            dmap2.put("gstpct", 23.0);

            return dmap2;
        });
    }

    @DataProvider(name = "Different_completion_types", parallel = true)
    public Object[][] Different_completion_types() {
        return new Object[][]{
                {"Scenario:: Completion type: Autocomplete and client type is B2B", "autocomplete", "99labels", "B2B", true, "postpaid", "B2B", "99LabelsTest", "e_tail", false, null, "0fc86054-b1ea-11ee-9b9b-02d52690c6a5", "cms::client::e9d16a06-b435-11ed-9fdf-025dba7e1714", false, 34571, "12345", 61984},
                {"Scenario:: Completion type: Complete and client type is B2B", "complete", "99LabelsTest", "B2B", true, "postpaid", "B2B", "99LabelsTest", "e_tail", false, null, "0fc86054-b1ea-11ee-9b9b-02d52690c6a5", "cms::client::e9d16a06-b435-11ed-9fdf-025dba7e1714", false, 34571, "12345", 61984},
                {"Scenario:: Completion type: Complete and client type is B2C", "complete", "99labels", "B2C", false, "postpaid", "B2B", "99LabelsTest", "e_tail", false, null, "0fc86054-b1ea-11ee-9b9b-02d52690c6a5", "cms::client::e9d16a06-b435-11ed-9fdf-025dba7e1714", false, 34571, "12345", 61984},
                {"Scenario:: Completion type: Autocomplete and client type is B2C", "autocomplete", "99labels", "B2C", false, "postpaid", "B2B", "99LabelsTest", "e_tail", false, null, "0fc86054-b1ea-11ee-9b9b-02d52690c6a5", "cms::client::e9d16a06-b435-11ed-9fdf-025dba7e1714", false, 34571, "12345", 61984}
        };
    }

    //NFT_223597 cases
    @Test(dataProvider = "Different_completion_types", enabled = true)
    public void client_fetch_internal(String scenario, String completion_type, String client_name, String client_type, Boolean expectedSuccess,
                                      String expectedBillingMethod, String expectedPdt, String expectedName, String expectedClientType, Boolean expectedIsPrepaid,
                                      Object expectedWalletProvider, String expectedWalletId, String expectedUuid, Boolean expectedFreightCollection, Integer expectedId,
                                      String expectedFrsCode, Integer expectedWaybillPrefix) {

        ThreadLocal<HashMap<String, Object>> expected_values = new ThreadLocal<>();
        expected_values.set(buildExpectedValuesMap(expectedSuccess, expectedBillingMethod, expectedPdt, expectedName, expectedClientType, expectedIsPrepaid, expectedWalletProvider, expectedWalletId, expectedUuid, expectedFreightCollection, expectedId, expectedFrsCode, expectedWaybillPrefix).get());

        apiCtrl.fetchClientDetailsInternal(completion_type, client_name, client_type, expected_values.get());
    }

    private ThreadLocal<HashMap<String, Object>> buildExpectedValuesMap(Boolean expectedSuccess, String expectedBillingMethod, String expectedPdt, String expectedName, String expectedClientType, Boolean expectedIsPrepaid, Object expectedWalletProvider, String expectedWalletId, String expectedUuid, Boolean expectedFreightCollection, Integer expectedId, String expectedFrsCode, Integer expectedWaybillPrefix) {
        return ThreadLocal.withInitial(() -> {
            HashMap<String, Object> expectedValues = new HashMap<>();
            expectedValues.put("product_type", expectedPdt);
            expectedValues.put("success", expectedSuccess);
            expectedValues.put("billing_method", expectedBillingMethod);
            expectedValues.put("name", expectedName);
            expectedValues.put("client_type", expectedClientType);
            expectedValues.put("wallet_id", expectedWalletId);
            expectedValues.put("uuid_key", expectedUuid);
            expectedValues.put("frs_code", expectedFrsCode);
            expectedValues.put("is_prepaid", expectedIsPrepaid);
            expectedValues.put("wallet_provider", expectedWalletProvider);
            expectedValues.put("freight_collection", expectedFreightCollection);
            expectedValues.put("id", expectedId);
            expectedValues.put("waybill_prefix", expectedWaybillPrefix);
            return expectedValues;
        });
    }
    
    //FUNCTIONAL SUB TASK 233224
    @Test(dataProvider = "Different_type_pkg", dataProviderClass = manifestationData.class, enabled = true)
    public void update_trans_id_for_diff_type_pkgs(String Scenario, String type) {
        List<String> waybills = diffTypeShipment.DifferentTypeShipments(type, clData);
        String wbn = waybills.get(0);
        
        logInfo("Waybill generated " + wbn);
        
        String child_waybill = waybill;
        if (waybills.size() > 1 && Scenario.contains("MPS")) {
            child_waybill = waybills.get(1);
            logInfo("Child Waybill generated " + child_waybill);
        }
        
        if(Scenario.contains("B2B") || Scenario.contains("Heavy") || Scenario.contains("No data")){
            Utilities.hardWait(40);
        }
        
        apiCtrl.transIdUpdate(wbn);
    }
    
    @Test(dataProvider = "Different_state_type", dataProviderClass = manifestationData.class, enabled = true)
    public void update_trans_id_for_diff_states(String Scenario, String type, String state) {
    	HashMap<String,String> data = new HashMap<String,String>();
		data.put("product_type", type);
        String wbn = differentStateShipments.DifferentStateShipments(state, data);
        
        logInfo("Waybill generated " + wbn);
        
        if(Scenario.contains("B2B") || Scenario.contains("Heavy") || Scenario.contains("No data")){
            Utilities.hardWait(40);
        }
     
        apiCtrl.transIdUpdate(wbn);
    }

    @Test(dataProvider = "Partial_Manifest_Improvements", dataProviderClass = manifestationData.class, enabled = true)
    public void partialManifestationImprovementsB2B(String Scenario, String type) {
        clData.put("client", "regression_client");
        List<String> waybills = diffTypeShipment.DifferentTypeShipments(type, clData);
        String wbn = waybills.get(0);

        logInfo("Waybill generated " + wbn);

        String child_waybill = waybill;
        if (waybills.size() > 1 && Scenario.contains("MPS")) {
            child_waybill = waybills.get(1);
            logInfo("Child Waybill generated " + child_waybill);
        }
        apiCtrl.partialManifestImprovements(Scenario, wbn, clData);
    }
}
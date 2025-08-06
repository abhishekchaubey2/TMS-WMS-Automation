package com.delhivery.Express.testModules.PackageFlow;

import com.delhivery.Express.controllers.api.ApiController;
import com.delhivery.Express.pojo.ClientDetailsFetch.Response.FetchClientDetailsResponsePayload;
import com.delhivery.Express.pojo.FetchPackageDetails.response.PackageDetail;
import com.delhivery.Express.pojo.InstaBaggingCreateApi.response.InstaBaggingCreateResponsePayload;
import com.delhivery.Express.pojo.PackageDetailFetchRestInfoApi.response.PackageDetails;
import com.delhivery.core.BaseTest;
import com.delhivery.core.utils.ProdConfigLoader;
import com.delhivery.core.utils.Utilities;
import com.delhivery.core.utils.YamlReader;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static com.delhivery.core.utils.Assertions.assertKeyValue;
import static com.delhivery.core.utils.Utilities.logInfo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class ProductionSanity extends BaseTest {

	private String waybill, bagId, tripId, dispatchId;
	private ArrayList<String> waybills;

    private String product_type, payment_mode, packageStatus, bagSeal, client, clientWarehouse, client_uuid, cwh_uuid, pin, origin_center, destination_center, ocid, oc, cnid, instaBagStId, instaBagStName;
    public String scenario;
    public HashMap<String, String> clData = new HashMap<>();
    public HashMap<String, String> manifestData = new HashMap<>();
    public HashMap<String, String> instaBaggingData = new HashMap<>();
    public Map<String, Object> pkgFlowData;
    public Map<String, Object> bagFlowData;
    ApiController apiCtrl = new ApiController();

    public ProductionSanity() {
        scenario = "Package Flow B2C Prepaid type shipment";
        product_type = ProdConfigLoader.getInstance().getProductType();
        payment_mode = ProdConfigLoader.getInstance().getPaymentType();
        pin = ProdConfigLoader.getInstance().getPin();
        client = YamlReader.getYamlValues("productionData.client_"+ProdConfigLoader.getInstance().getClient()).get("name").toString();
        clientWarehouse = YamlReader.getYamlValues("productionData.client_"+ProdConfigLoader.getInstance().getClient()).get("client_warehouse").toString();
        client_uuid = YamlReader.getYamlValues("productionData.client_"+ProdConfigLoader.getInstance().getClient()).get("client_uuid").toString();
        cwh_uuid = YamlReader.getYamlValues("productionData.client_"+ProdConfigLoader.getInstance().getClient()).get("cwh_uuid").toString();
        origin_center = YamlReader.getYamlValues("productionData."+ProdConfigLoader.getInstance().getOriginCenter()).get("Name").toString();
        destination_center = YamlReader.getYamlValues("productionData."+ProdConfigLoader.getInstance().getDestinationCenter()).get("Name").toString();
        oc = YamlReader.getYamlValues("productionData."+ProdConfigLoader.getInstance().getOriginCenter()).get("Name").toString();
        ocid = YamlReader.getYamlValues("productionData."+ProdConfigLoader.getInstance().getOriginCenter()).get("SortCode").toString();
        cnid = YamlReader.getYamlValues("productionData."+ProdConfigLoader.getInstance().getDestinationCenter()).get("SortCode").toString();
        instaBagStId = ProdConfigLoader.getInstance().getInstaBagStationId();
        instaBagStName = ProdConfigLoader.getInstance().getInstaBagStationName();

        manifestData.put("jobType","ProdSanity");
        manifestData.put("prodClient", client);
        clData.put("jobType","ProdSanity");
        clData.put("prodClient", client);
        clData.put("ocid", ocid);
        clData.put("cnid", cnid);
        clData.put("shipment_destination_center", destination_center);

    }

    @Test()
    public void checkClientConfig() throws JsonProcessingException {
        FetchClientDetailsResponsePayload apiResponse = apiCtrl.verifyFetchClientDetailsApi(client, clData);
        assertKeyValue("Client product type", "null", Utilities.jsonObjectToString(apiResponse.data.productType));

    }

    @Test(dependsOnMethods = "checkClientConfig")
    public void manifest() {
        manifestData.put("product_type",product_type);
        manifestData.put("payment_mode", payment_mode);
        manifestData.put("pin", pin);

		waybills = apiCtrl.cmuManifestApi(manifestData);
		waybill = waybills.get(0);
		logInfo("Waybill generated " + waybill);
        pkgFlowData = YamlReader.getYamlValues("packageFlowScans.manifestation");
		apiCtrl.verifyPackageFetchInfoApi(waybill, pkgFlowData.get("statusType").toString(), pkgFlowData.get("scanStatus").toString(), pkgFlowData.get("scanNsl").toString(), clData);
        Utilities.hardWait(30);

	}

    @Test(dependsOnMethods = "manifest")
    public void fmOmsFmPick() {
        apiCtrl.fmOMSApi(waybill, "FMPICK", clData);
        pkgFlowData = YamlReader.getYamlValues("packageFlowScans.fmPick");
        apiCtrl.verifyPackageFetchInfoApi(waybill, pkgFlowData.get("statusType").toString(), pkgFlowData.get("scanStatus").toString(), pkgFlowData.get("scanNsl").toString(), clData);
    }

    @Test(dependsOnMethods = "fmOmsFmPick")
    public void fmOmsFmDepart() {
        apiCtrl.fmOMSApi(waybill, "FMDEPART", clData);
        pkgFlowData = YamlReader.getYamlValues("packageFlowScans.fmDepart");
        apiCtrl.verifyPackageFetchInfoApi(waybill, pkgFlowData.get("statusType").toString(), pkgFlowData.get("scanStatus").toString(), pkgFlowData.get("scanNsl").toString(), clData);
    }

    @Test(dependsOnMethods = "fmOmsFmDepart")
    public void genericIncomingAtOrigin() {
        apiCtrl.giApi(waybill, origin_center, clData);
        pkgFlowData = YamlReader.getYamlValues("packageFlowScans.genericIncomingAtOrigin");
        apiCtrl.verifyPackageFetchInfoApi(waybill, pkgFlowData.get("statusType").toString(), pkgFlowData.get("scanStatus").toString(), pkgFlowData.get("scanNsl").toString(), clData);
    }

    @Test(dependsOnMethods = "genericIncomingAtOrigin")
    public void instaBaggingCreate() throws IOException {
        instaBaggingData = clData;
        instaBaggingData.put("bd", destination_center);
        instaBaggingData.put("bi", instaBagStId);
        instaBaggingData.put("bt", "Surface");
        instaBaggingData.put("st", instaBagStName);
        instaBaggingData.put("wbn", waybill);
        instaBaggingData.put("center_name", oc);
        InstaBaggingCreateResponsePayload apiResponse = apiCtrl.instaBaggingCreateApi(instaBaggingData);
        assertKeyValue("success", true, apiResponse.success);
        Utilities.hardWait(2);
    }

    @Test(dependsOnMethods = "instaBaggingCreate")
    public void instaBaggingSeal() throws IOException {
        bagId = Utilities.generateUniqueEntity("BAG");
        instaBaggingData.put("bs", bagId);
        apiCtrl.instaBaggingSealApi(instaBaggingData);
        logInfo("Bag created " + bagId);
        Utilities.hardWait(2);
        pkgFlowData = YamlReader.getYamlValues("packageFlowScans.createBag");
        apiCtrl.verifyPackageFetchInfoApi(waybill, pkgFlowData.get("statusType").toString(), pkgFlowData.get("scanStatus").toString(), pkgFlowData.get("scanNsl").toString(), clData);
        bagFlowData = YamlReader.getYamlValues("bagFlowScans.createBag");
        apiCtrl.verifyBagFetchInfoApi(bagId, bagFlowData.get("scanStatus").toString(), bagFlowData.get("scanAct").toString(), null, clData);
    }

    @Test(dependsOnMethods = "instaBaggingSeal")
    public void bagAddToTrip() {
        tripId = apiCtrl.bagAddToTripApi(bagId, clData);
        logInfo("Trip created " + tripId);
        pkgFlowData = YamlReader.getYamlValues("packageFlowScans.addBagToTrip");
        apiCtrl.verifyPackageFetchInfoApi(waybill, pkgFlowData.get("statusType").toString(), pkgFlowData.get("scanStatus").toString(), pkgFlowData.get("scanNsl").toString(), clData);
        bagFlowData = YamlReader.getYamlValues("bagFlowScans.addBagToTrip");
        apiCtrl.verifyBagFetchInfoApi(bagId, bagFlowData.get("scanStatus").toString(), bagFlowData.get("scanAct").toString(), tripId, clData);
    }

    @Test(dependsOnMethods = "bagAddToTrip")
    public void tripIncoming() {
        apiCtrl.tripIncomingApi(tripId, clData);
        pkgFlowData = YamlReader.getYamlValues("packageFlowScans.tripIncoming");
        apiCtrl.verifyPackageFetchInfoApi(waybill, pkgFlowData.get("statusType").toString(), pkgFlowData.get("scanStatus").toString(), pkgFlowData.get("scanNsl").toString(), clData);
        bagFlowData = YamlReader.getYamlValues("bagFlowScans.tripIncoming");
        apiCtrl.verifyBagFetchInfoApi(bagId, bagFlowData.get("scanStatus").toString(), bagFlowData.get("scanAct").toString(), tripId, clData);
    }

    @Test(dependsOnMethods = "tripIncoming")
    public void bagIncoming() {
        apiCtrl.bagIncomingApi(bagId, clData);
        pkgFlowData = YamlReader.getYamlValues("packageFlowScans.bagIncoming");
        apiCtrl.verifyPackageFetchInfoApi(waybill, pkgFlowData.get("statusType").toString(), pkgFlowData.get("scanStatus").toString(), pkgFlowData.get("scanNsl").toString(), clData);
        bagFlowData = YamlReader.getYamlValues("bagFlowScans.bagIncoming");
        apiCtrl.verifyBagFetchInfoApi(bagId, bagFlowData.get("scanStatus").toString(), bagFlowData.get("scanAct").toString(), null, clData);
    }

    @Test(dependsOnMethods = "bagIncoming")
    public void genericIncomingAtDestination() {
        apiCtrl.giApi(waybill, destination_center, clData);
        pkgFlowData = YamlReader.getYamlValues("packageFlowScans.genericIncomingAtDestination");
        apiCtrl.verifyPackageFetchInfoApi(waybill, pkgFlowData.get("statusType").toString(), pkgFlowData.get("scanStatus").toString(), pkgFlowData.get("scanNsl").toString(), clData);
        bagFlowData = YamlReader.getYamlValues("bagFlowScans.genericIncomingAtDestination");
        apiCtrl.verifyBagFetchInfoApi(bagId, bagFlowData.get("scanStatus").toString(), null, null, clData);
    }

    @Test(dependsOnMethods = "genericIncomingAtDestination")
    public void markShipmentDispatched() {
        dispatchId = apiCtrl.markShipmentDispatchApi(waybill,clData);
        logInfo("Dispatch created " + dispatchId);
        pkgFlowData = YamlReader.getYamlValues("packageFlowScans.shipmentDispatched");
        apiCtrl.verifyPackageFetchInfoApi(waybill, pkgFlowData.get("statusType").toString(), pkgFlowData.get("scanStatus").toString(), pkgFlowData.get("scanNsl").toString(), clData);
        PackageDetail pkgDetails = apiCtrl.fetchPackageInfo(waybill,clData);
        assertKeyValue("Shipment dispatch Id", dispatchId, pkgDetails.dd.id.toString());
    }

    @Test(dependsOnMethods = "markShipmentDispatched")
    public void lmUpdateHQShipment() {
        apiCtrl.lmUpdateHQShipmentApi(waybill, "Delivered",clData);
        pkgFlowData = YamlReader.getYamlValues("packageFlowScans.shipmentDelivered");
        apiCtrl.verifyPackageFetchInfoApi(waybill, pkgFlowData.get("statusType").toString(), pkgFlowData.get("scanStatus").toString(), pkgFlowData.get("scanNsl").toString(), clData);
    }

    @Test(dependsOnMethods = "lmUpdateHQShipment")
    public void unsetShipmentDispatchId() {
        apiCtrl.unsetShipmentDispatchIdApi(waybill, dispatchId,clData);
        PackageDetail pkgDetails = apiCtrl.fetchPackageInfo(waybill,clData);
        if(pkgDetails.dd.id == null) {
        	assertKeyValue("Shipment dispatch Id", "null", "null");
        }else{
        	assertThat(0, equalTo(1));
        }
        pkgFlowData = YamlReader.getYamlValues("packageFlowScans.shipmentDelivered");
        apiCtrl.verifyPackageFetchInfoApi(waybill, pkgFlowData.get("statusType").toString(), pkgFlowData.get("scanStatus").toString(), pkgFlowData.get("scanNsl").toString(), clData);
    }

    @Test(dependsOnMethods = "unsetShipmentDispatchId")
    public void fetchPackageDetailRestInfo() {
        pkgFlowData = YamlReader.getYamlValues("packageFlowScans.shipmentDelivered");
        HashMap<String, String> paramsMap = new HashMap<>();
        paramsMap.put("ref_ids", waybill);
        PackageDetails pkgDetails = apiCtrl.verifyPackageFetchInfoApi(paramsMap, clData);
        assertKeyValue("Status type", pkgFlowData.get("statusType").toString(), pkgDetails.packages.get(0).cs.st);
        assertKeyValue("Scan status", pkgFlowData.get("scanStatus").toString(), pkgDetails.packages.get(0).cs.ss);
        assertKeyValue("Scan nsl", pkgFlowData.get("scanNsl").toString(), pkgDetails.packages.get(0).cs.nsl);
        logInfo("Scan REMARK : " + pkgDetails.packages.get(0).cs.sr);
    }

}
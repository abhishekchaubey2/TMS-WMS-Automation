package com.delhivery.Express.testModules.PackageFlow;

import com.delhivery.Express.RequestBuilder.RequestBuilder;
import com.delhivery.Express.controllers.api.ApiController;
import com.delhivery.Express.pojo.FetchPackageDetails.response.PackageDetail;
import com.delhivery.core.BaseTest;
import com.delhivery.core.utils.Utilities;
import com.delhivery.core.utils.YamlReader;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static com.delhivery.core.utils.Assertions.assertKeyValue;
import static com.delhivery.core.utils.Utilities.logInfo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class PackageFlowSanity extends BaseTest {

	private String waybill, bagId, tripId, dispatchId;
	private ArrayList<String> waybills;

    private String product_type, payment_mode, packageStatus, bagSeal, ocid, cnid, client, pin;
    public String scenario;
    public HashMap<String, String> clData = new HashMap<>();
    public HashMap<String,String> manifestData = new HashMap<String,String>();
    public Map<String, Object> pkgFlowData;
    public Map<String, Object> bagFlowData;
    ApiController apiCtrl = new ApiController();

    public PackageFlowSanity() {
        clData.put("jobType","ApiSanity");
        manifestData.put("jobType","ApiSanity");
        this.scenario = "Package Flow B2C Prepaid type shipment";
        this.product_type = "B2C";
        this.payment_mode = "Prepaid";
        this.pin = "400059";
        client = YamlReader.getYamlValues("Client_Details.client_HQAPIREGRESSION").get("name").toString();
        ocid = YamlReader.getYamlValues("Centers.Gurgaon").get("SortCode").toString();
        cnid = YamlReader.getYamlValues("Centers.Mumbai_MIDC").get("SortCode").toString();

    }

    @Test()
    public void manifest() {
    	clData.put("client", "regression_client");
        manifestData.put("product_type",product_type);
        manifestData.put("payment_mode", payment_mode);
        manifestData.put("pin", pin);
		
		waybills = apiCtrl.cmuManifestApi(manifestData);
		waybill = waybills.get(0);
		logInfo("Waybill generated " + waybill);
        pkgFlowData = YamlReader.getYamlValues("packageFlowScans.manifestation");
		apiCtrl.verifyPackageFetchInfoApi(waybill, pkgFlowData.get("statusType").toString(), pkgFlowData.get("scanStatus").toString(), pkgFlowData.get("scanNsl").toString(), clData);
		if (product_type.equals("B2B")) {
			Utilities.hardWait(70);
		}
	}

    @Test(dependsOnMethods = "manifest")
    public void fmOmsFmPick() {
    	clData.put("client", "regression_client");
        apiCtrl.fmOMSApi(waybill, "FMPICK", clData);
        pkgFlowData = YamlReader.getYamlValues("packageFlowScans.fmPick");
        apiCtrl.verifyPackageFetchInfoApi(waybill, pkgFlowData.get("statusType").toString(), pkgFlowData.get("scanStatus").toString(), pkgFlowData.get("scanNsl").toString(), clData);
    }

    @Test(dependsOnMethods = "fmOmsFmPick")
    public void fmOmsFmDepart() {
    	clData.put("client", "regression_client");
        apiCtrl.fmOMSApi(waybill, "FMDEPART", clData);
        pkgFlowData = YamlReader.getYamlValues("packageFlowScans.fmDepart");
        apiCtrl.verifyPackageFetchInfoApi(waybill, pkgFlowData.get("statusType").toString(), pkgFlowData.get("scanStatus").toString(), pkgFlowData.get("scanNsl").toString(), clData);
    }
 
    @Test(dependsOnMethods = "fmOmsFmDepart")
    public void genericIncomingAtOrigin() {
    	clData.put("client", "regression_client");
        apiCtrl.giApi(waybill, RequestBuilder.origin_center.get("Name").toString(),clData);
        pkgFlowData = YamlReader.getYamlValues("packageFlowScans.genericIncomingAtOrigin");
        apiCtrl.verifyPackageFetchInfoApi(waybill, pkgFlowData.get("statusType").toString(), pkgFlowData.get("scanStatus").toString(), pkgFlowData.get("scanNsl").toString(), clData);
    }

    @Test(dependsOnMethods = "genericIncomingAtOrigin")
    public void createV3Bag() {
    	clData.put("client", "regression_client");
        bagId = apiCtrl.bagv3Api(waybill, clData);
        logInfo("Bag created " + bagId);
        Utilities.hardWait(60);
        pkgFlowData = YamlReader.getYamlValues("packageFlowScans.createBag");
        apiCtrl.verifyPackageFetchInfoApi(waybill, pkgFlowData.get("statusType").toString(), pkgFlowData.get("scanStatus").toString(), pkgFlowData.get("scanNsl").toString(), clData);
        bagFlowData = YamlReader.getYamlValues("bagFlowScans.createBag");
        apiCtrl.verifyBagFetchInfoApi(bagId, bagFlowData.get("scanStatus").toString(), bagFlowData.get("scanAct").toString(), null, clData);
    }

    @Test(dependsOnMethods = "createV3Bag")
    public void bagAddToTrip() {
    	clData.put("client", "regression_client");
        tripId = apiCtrl.bagAddToTripApi(bagId, clData);
        logInfo("Trip created " + tripId);
        pkgFlowData = YamlReader.getYamlValues("packageFlowScans.addBagToTrip");
        apiCtrl.verifyPackageFetchInfoApi(waybill, pkgFlowData.get("statusType").toString(), pkgFlowData.get("scanStatus").toString(), pkgFlowData.get("scanNsl").toString(), clData);
        bagFlowData = YamlReader.getYamlValues("bagFlowScans.addBagToTrip");
        apiCtrl.verifyBagFetchInfoApi(bagId, bagFlowData.get("scanStatus").toString(), bagFlowData.get("scanAct").toString(), tripId, clData);
    }

    @Test(dependsOnMethods = "bagAddToTrip")
    public void tripIncoming() {
    	clData.put("client", "regression_client");
        apiCtrl.tripIncomingApi(tripId, clData);
        pkgFlowData = YamlReader.getYamlValues("packageFlowScans.tripIncoming");
        apiCtrl.verifyPackageFetchInfoApi(waybill, pkgFlowData.get("statusType").toString(), pkgFlowData.get("scanStatus").toString(), pkgFlowData.get("scanNsl").toString(), clData);
        bagFlowData = YamlReader.getYamlValues("bagFlowScans.tripIncoming");
        apiCtrl.verifyBagFetchInfoApi(bagId, bagFlowData.get("scanStatus").toString(), bagFlowData.get("scanAct").toString(), tripId, clData);
    }

    @Test(dependsOnMethods = "tripIncoming")
    public void bagIncoming() {
    	clData.put("client", "regression_client");
        apiCtrl.bagIncomingApi(bagId, clData);
        pkgFlowData = YamlReader.getYamlValues("packageFlowScans.bagIncoming");
        apiCtrl.verifyPackageFetchInfoApi(waybill, pkgFlowData.get("statusType").toString(), pkgFlowData.get("scanStatus").toString(), pkgFlowData.get("scanNsl").toString(), clData);
        bagFlowData = YamlReader.getYamlValues("bagFlowScans.bagIncoming");
        apiCtrl.verifyBagFetchInfoApi(bagId, bagFlowData.get("scanStatus").toString(), bagFlowData.get("scanAct").toString(), null, clData);
    }

    @Test(dependsOnMethods = "bagIncoming")
    public void genericIncomingAtDestination() {
    	clData.put("client", "regression_client");
        apiCtrl.giApi(waybill, RequestBuilder.destination_center.get("Name").toString(),clData);
        pkgFlowData = YamlReader.getYamlValues("packageFlowScans.genericIncomingAtDestination");
        apiCtrl.verifyPackageFetchInfoApi(waybill, pkgFlowData.get("statusType").toString(), pkgFlowData.get("scanStatus").toString(), pkgFlowData.get("scanNsl").toString(), clData);
        bagFlowData = YamlReader.getYamlValues("bagFlowScans.genericIncomingAtDestination");
        apiCtrl.verifyBagFetchInfoApi(bagId, bagFlowData.get("scanStatus").toString(), null, null, clData);
    }

    @Test(dependsOnMethods = "genericIncomingAtDestination")
    public void markShipmentDispatched() {
    	clData.put("client", "regression_client");
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

}
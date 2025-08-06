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

public class MultiPackageFlowSanity extends BaseTest {

	private String dispatchId;
	private ArrayList<String> waybills, bagIds, tripIds;

    private String product_type, payment_mode, packageStatus, bagSeal, ocid, cnid, client, pin, package_count;
    public String scenario;
    public HashMap<String, String> clData = new HashMap<>();
    public HashMap<String,String> manifestData = new HashMap<String,String>();
    public Map<String, Object> pkgFlowData;
    public Map<String, Object> bagFlowData;
    ApiController apiCtrl = new ApiController();

    public MultiPackageFlowSanity() {
        clData.put("jobType","ApiSanity");
        manifestData.put("jobType","ApiSanity");
        this.scenario = "Package Flow B2C Prepaid type shipment";
        this.product_type = "B2C";
        this.payment_mode = "Prepaid";
        this.pin = "400059";
        this.package_count = "3";
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
        manifestData.put("package_count", package_count);
		
		waybills = apiCtrl.cmuManifestApi(manifestData);
		logInfo("Waybill generated " + waybills);
        pkgFlowData = YamlReader.getYamlValues("packageFlowScans.manifestation");
		apiCtrl.verifyPackageFetchInfoApi(waybills, pkgFlowData.get("statusType").toString(), pkgFlowData.get("scanStatus").toString(), pkgFlowData.get("scanNsl").toString(), clData);
		if (product_type.equals("B2B")) {
			Utilities.hardWait(70);
		}
	}

    @Test(dependsOnMethods = "manifest")
    public void fmOmsFmPick() {
    	clData.put("client", "regression_client");
        apiCtrl.fmOMSApi(waybills, "FMPICK", clData);
        pkgFlowData = YamlReader.getYamlValues("packageFlowScans.fmPick");
        apiCtrl.verifyPackageFetchInfoApi(waybills, pkgFlowData.get("statusType").toString(), pkgFlowData.get("scanStatus").toString(), pkgFlowData.get("scanNsl").toString(), clData);
    }

    @Test(dependsOnMethods = "fmOmsFmPick")
    public void fmOmsFmDepart() {
    	clData.put("client", "regression_client");
        apiCtrl.fmOMSApi(waybills, "FMDEPART", clData);
        pkgFlowData = YamlReader.getYamlValues("packageFlowScans.fmDepart");
        apiCtrl.verifyPackageFetchInfoApi(waybills, pkgFlowData.get("statusType").toString(), pkgFlowData.get("scanStatus").toString(), pkgFlowData.get("scanNsl").toString(), clData);
    }

    @Test(dependsOnMethods = "fmOmsFmDepart")
    public void genericIncomingAtOrigin() {
    	clData.put("client", "regression_client");
        apiCtrl.giApi(waybills, RequestBuilder.origin_center.get("Name").toString(),clData);
        pkgFlowData = YamlReader.getYamlValues("packageFlowScans.genericIncomingAtOrigin");
        apiCtrl.verifyPackageFetchInfoApi(waybills, pkgFlowData.get("statusType").toString(), pkgFlowData.get("scanStatus").toString(), pkgFlowData.get("scanNsl").toString(), clData);
    }

    @Test(dependsOnMethods = "genericIncomingAtOrigin")
    public void createV3Bag() {
    	clData.put("client", "regression_client");
        bagIds = apiCtrl.bagv3Api(waybills, clData);
        logInfo("Bag created " + bagIds);
        Utilities.hardWait(60);
        pkgFlowData = YamlReader.getYamlValues("packageFlowScans.createBag");
        apiCtrl.verifyPackageFetchInfoApi(waybills, pkgFlowData.get("statusType").toString(), pkgFlowData.get("scanStatus").toString(), pkgFlowData.get("scanNsl").toString(), clData);
        bagFlowData = YamlReader.getYamlValues("bagFlowScans.createBag");
        apiCtrl.verifyBagFetchInfoApi(bagIds, bagFlowData.get("scanStatus").toString(), bagFlowData.get("scanAct").toString(), clData);
    }

    @Test(dependsOnMethods = "createV3Bag")
    public void bagAddToTrip() {
    	clData.put("client", "regression_client");
        tripIds = apiCtrl.bagAddToTripApi(bagIds, clData);
        logInfo("Trip created " + tripIds);
        pkgFlowData = YamlReader.getYamlValues("packageFlowScans.addBagToTrip");
        apiCtrl.verifyPackageFetchInfoApi(waybills, pkgFlowData.get("statusType").toString(), pkgFlowData.get("scanStatus").toString(), pkgFlowData.get("scanNsl").toString(), clData);
        bagFlowData = YamlReader.getYamlValues("bagFlowScans.addBagToTrip");
        apiCtrl.verifyBagFetchInfoApi(bagIds, bagFlowData.get("scanStatus").toString(), bagFlowData.get("scanAct").toString(), clData);
    }

    @Test(dependsOnMethods = "bagAddToTrip")
    public void tripIncoming() {
    	clData.put("client", "regression_client");
        apiCtrl.tripIncomingApi(tripIds, clData);
        pkgFlowData = YamlReader.getYamlValues("packageFlowScans.tripIncoming");
        apiCtrl.verifyPackageFetchInfoApi(waybills, pkgFlowData.get("statusType").toString(), pkgFlowData.get("scanStatus").toString(), pkgFlowData.get("scanNsl").toString(), clData);
        bagFlowData = YamlReader.getYamlValues("bagFlowScans.tripIncoming");
        apiCtrl.verifyBagFetchInfoApi(bagIds, bagFlowData.get("scanStatus").toString(), bagFlowData.get("scanAct").toString(), clData);
    }

    @Test(dependsOnMethods = "tripIncoming")
    public void bagIncoming() {
    	clData.put("client", "regression_client");
        apiCtrl.bagIncomingApi(bagIds, clData);
        pkgFlowData = YamlReader.getYamlValues("packageFlowScans.bagIncoming");
        apiCtrl.verifyPackageFetchInfoApi(waybills, pkgFlowData.get("statusType").toString(), pkgFlowData.get("scanStatus").toString(), pkgFlowData.get("scanNsl").toString(), clData);
        bagFlowData = YamlReader.getYamlValues("bagFlowScans.bagIncoming");
        apiCtrl.verifyBagFetchInfoApi(bagIds, bagFlowData.get("scanStatus").toString(), bagFlowData.get("scanAct").toString(), clData);
    }

    @Test(dependsOnMethods = "bagIncoming")
    public void genericIncomingAtDestination() {
    	clData.put("client", "regression_client");
        apiCtrl.giApi(waybills, RequestBuilder.destination_center.get("Name").toString(),clData);
        pkgFlowData = YamlReader.getYamlValues("packageFlowScans.genericIncomingAtDestination");
        apiCtrl.verifyPackageFetchInfoApi(waybills, pkgFlowData.get("statusType").toString(), pkgFlowData.get("scanStatus").toString(), pkgFlowData.get("scanNsl").toString(), clData);
        bagFlowData = YamlReader.getYamlValues("bagFlowScans.genericIncomingAtDestination");
        apiCtrl.verifyBagFetchInfoApi(bagIds, bagFlowData.get("scanStatus").toString(), null, clData);
    }

    @Test(dependsOnMethods = "genericIncomingAtDestination")
    public void markShipmentDispatched() {
    	clData.put("client", "regression_client");
        dispatchId = apiCtrl.markShipmentDispatchApi(waybills,clData);
        logInfo("Dispatch created " + dispatchId);
        Utilities.hardWait(5);
        pkgFlowData = YamlReader.getYamlValues("packageFlowScans.shipmentDispatched");
        apiCtrl.verifyPackageFetchInfoApi(waybills, pkgFlowData.get("statusType").toString(), pkgFlowData.get("scanStatus").toString(), pkgFlowData.get("scanNsl").toString(), clData);
        PackageDetail pkgDetails = apiCtrl.fetchPackageInfo(waybills.get(0),clData);
        assertKeyValue("Shipment dispatch Id", dispatchId, pkgDetails.dd.id.toString());
    }

    @Test(dependsOnMethods = "markShipmentDispatched")
    public void lmUpdateHQShipment() {
        apiCtrl.lmUpdateHQShipmentApi(waybills, "Delivered",clData);
        pkgFlowData = YamlReader.getYamlValues("packageFlowScans.shipmentDelivered");
        apiCtrl.verifyPackageFetchInfoApi(waybills, pkgFlowData.get("statusType").toString(), pkgFlowData.get("scanStatus").toString(), pkgFlowData.get("scanNsl").toString(), clData);
    }

    @Test(dependsOnMethods = "lmUpdateHQShipment")
    public void unsetShipmentDispatchId() {
        apiCtrl.unsetShipmentDispatchIdApi(waybills, dispatchId,clData);
        PackageDetail pkgDetails = apiCtrl.fetchPackageInfo(waybills.get(0),clData);
        if(pkgDetails.dd.id == null) {
        	assertKeyValue("Shipment dispatch Id", "null", "null");
        }else{
        	assertThat(0, equalTo(1));
        }
        pkgFlowData = YamlReader.getYamlValues("packageFlowScans.shipmentDelivered");
        apiCtrl.verifyPackageFetchInfoApi(waybills, pkgFlowData.get("statusType").toString(), pkgFlowData.get("scanStatus").toString(), pkgFlowData.get("scanNsl").toString(), clData);
    }
    
}
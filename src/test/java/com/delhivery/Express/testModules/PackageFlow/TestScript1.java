package com.delhivery.Express.testModules.PackageFlow;

import static com.delhivery.core.utils.Assertions.assertKeyValue;
import static com.delhivery.core.utils.Utilities.logInfo;

import java.util.HashMap;
import java.util.List;

import org.testng.annotations.Test;

import com.delhivery.Express.controllers.api.ApiController;
import com.delhivery.core.BaseTest;
import com.delhivery.core.db.DataProviderClass;

public class TestScript1 extends BaseTest {
	private String waybill, bagId, tripId, dispatchId;

	private String product_type, payment_mode, packageStatus, bagSeal, ocid, cnid, client;
	public String scenario;
	ApiController apiCtrl = new ApiController();
	public static HashMap<String,String> clData = new HashMap<>();

	public TestScript1() {
		DataProviderClass.fileName = "CmuRegressionData";
		DataProviderClass.sheetName = "Pkg_flows";

	}

//    @DataProvider(name = "package_flow")
//    public Object[][] package_flow() {
//        return new Object[][] {
//                { "Scenario :: Package flow of B2C COD type shipment", ""B2C", "COD" },
//                { "Scenario :: Package flow of B2C COD type shipment", "B2B", "Pre-paid" }
//        };
//    }
//    Manifest B2C COD type package
//    Manifest B2B, prepaid type package

//    @Factory(dataProviderClass = com.delhivery.core.db.DataProviderClass.class, dataProvider = "dataprovider")
//	@Factory(dataProviderClass = com.delhivery.core.db.DataProviderClass.class, dataProvider = "dataprovider")
//	public TestScript1(String scenario, String product_type, String payment_mode) {
//		this.scenario = scenario;
//		this.product_type = product_type;
//		this.payment_mode = payment_mode;
//		client = YamlReader.getYamlValues("Client_Details.client_HQAPIREGRESSION").get("name").toString();
//		// oc_center = YamlReader.getYamlValues("Centers.Gurgaon");
//		// cn_center = YamlReader.getYamlValues("Centers.Mumbai_MIDC");
////        cn_center = YamlReader.getYamlValues("Centers." + "Mumbai_MIDC");
////        oc_center = YamlReader.getYamlValues("Centers." + "Gurgaon");
//		ocid = YamlReader.getYamlValues("Centers.Gurgaon").get("SortCode").toString();
//		cnid = YamlReader.getYamlValues("Centers.Mumbai_MIDC").get("SortCode").toString();
//
//	}

//	@Test()
//	public void manifest() {
//		HashMap<String,String> data = new HashMap<String,String>();
//		data.put("product_type",product_type);
//		data.put("payment_mode", payment_mode);
//		
//		List<String> waybills = apiCtrl.cmuManifestApi(data);
//		waybill = waybills.get(0);
//		logInfo("Waybill generated " + waybill);
//		apiCtrl.verifyPackageFetchInfoApi(waybill, "UD", "Manifested", "X-UCI");
//		if (product_type.equals("B2B")) {
//			Utilities.hardWait(70);
//		}
//	}
//
//	@Test(dependsOnMethods = "manifest")
//	public void fmOmsFmPick() {
//		apiCtrl.fmOMSApi(waybill, "FMPICK");
//		apiCtrl.verifyPackageFetchInfoApi(waybill, "UD", "In Transit", "X-PPOM");
//	}

//    @Test(dependsOnMethods = "fmOmsFmPick")
//    public void fmOmsFmDepart() {
//        apiCtrl.fmOMSApi(waybill, "FMDEPART");
//        apiCtrl.verifyPackageFetchInfoApi(waybill, "UD", "In Transit", "FMDEPART-101");
//    }
//
//    @Test(dependsOnMethods = "fmOmsFmDepart")
//    public void genericIncomingAtOrigin() {
//        apiCtrl.giApi(waybill, RequestBuilder.origin_center.get("Name").toString());
//        apiCtrl.verifyPackageFetchInfoApi(waybill, "UD", "In Transit", "X-PIOM");
//    }
//
//    @Test(dependsOnMethods = "genericIncomingAtOrigin")
//    public void createV3Bag() {
//        bagId = apiCtrl.bagv3Api(waybill);
//        logInfo("Bag created " + bagId);
//        Utilities.hardWait(30);
//        apiCtrl.verifyPackageFetchInfoApi(waybill, "UD", "In Transit", "X-DBL1F");
//        apiCtrl.verifyBagFetchInfoApi(bagId, "In Transit", "+S", null);
//    }
//
//    @Test(dependsOnMethods = "createV3Bag")
//    public void bagAddToTrip() {
//        tripId = apiCtrl.bagAddToTripApi(bagId);
//        logInfo("Trip created " + tripId);
//        apiCtrl.verifyPackageFetchInfoApi(waybill, "UD", "In Transit", "X-DLL2F");
//        apiCtrl.verifyBagFetchInfoApi(bagId, "In Transit", "+L", tripId);
//    }
//
//    @Test(dependsOnMethods = "bagAddToTrip")
//    public void tripIncoming() {
//        apiCtrl.tripIncomingApi(tripId);
//        apiCtrl.verifyPackageFetchInfoApi(waybill, "UD", "In Transit", "X-ILL2F");
//        apiCtrl.verifyBagFetchInfoApi(bagId, "In Transit", "<L", tripId);
//    }
//
//    @Test(dependsOnMethods = "tripIncoming")
//    public void bagIncoming() {
//        apiCtrl.bagIncomingApi(bagId);
//        apiCtrl.verifyPackageFetchInfoApi(waybill, "UD", "In Transit", "X-ILL1F");
//        apiCtrl.verifyBagFetchInfoApi(bagId, "Pending", "<B", null);
//    }
//
//    @Test(dependsOnMethods = "bagIncoming")
//    public void genericIncomingAtDestination() {
//        apiCtrl.giApi(waybill, RequestBuilder.destination_center.get("Name").toString());
//        apiCtrl.verifyPackageFetchInfoApi(waybill, "UD", "Pending", "X-IBD3F");
//        apiCtrl.verifyBagFetchInfoApi(bagId, "Complete", null, null);
//    }
//
//    @Test(dependsOnMethods = "genericIncomingAtDestination")
//    public void markShipmentDispatched() {
//        dispatchId = apiCtrl.markShipmentDispatchApi(waybill);
//        logInfo("Dispatch created " + dispatchId);
//        apiCtrl.verifyPackageFetchInfoApi(waybill, "UD", "Dispatched", "X-DDD3FD");
//        PackageDetails pkgDetails = apiCtrl.fetchPackageInfo(waybill);
//        assertKeyValue("Shipment dispatch Id", dispatchId, pkgDetails.packages.get(0).dd.id);
//    }
//
//    @Test(dependsOnMethods = "markShipmentDispatched")
//    public void lmUpdateHQShipment() {
//        apiCtrl.lmUpdateHQShipmentApi(waybill, "Delivered");
//        apiCtrl.verifyPackageFetchInfoApi(waybill, "DL", "Delivered", "EOD-38");
//    }
//
//    @Test(dependsOnMethods = "lmUpdateHQShipment")
//    public void unsetShipmentDispatchId() {
//        apiCtrl.unsetShipmentDispatchIdApi(waybill, dispatchId);
//        apiCtrl.verifyPackageFetchInfoApi(waybill, "DL", "Delivered", "EOD-38");
//        PackageDetails pkgDetails = apiCtrl.fetchPackageInfo(waybill);
//        assertKeyValue("Shipment dispatch Id", null, pkgDetails.packages.get(0).dd.id);
//    }

//	@Test(dependsOnMethods = "fmOmsFmPick")
//	public void UpdateQcWt() {
//		for (int i = 0; i < 2; i++) {
//			if (i == 0) {
//				// sending light weight
//				apiCtrl.QcWtApi(waybill, 9999.12, "sorter");
////				apiCtrl.verifyPackageFetchInfoApi(waybill, "UD", "In Transit", "X-PPOM");
//				PackageDetails pkgdetails = apiCtrl.fetchPackageInfo(waybill);
//				double iwt = pkgdetails.packages.get(0).intWt.wt;
//
//				assertKeyValue("int_wt", 9999.12, iwt);
//
//			} else {
//				// sending heavy weight
//				apiCtrl.QcWtApi(waybill, 10000.12, "sorter");
////				apiCtrl.verifyPackageFetchInfoApi(waybill, "UD", "In Transit", "X-PPOM");
//				PackageDetails pkgdetails = apiCtrl.fetchPackageInfo(waybill);
//				double iwt = pkgdetails.packages.get(0).intWt.wt;
//				assertKeyValue("int_wt", 10000.12, iwt);
//			}
//
//		}
//
//	}
//
//	@Test(dependsOnMethods = "fmOmsFmPick")
//	public void ApplyNsl() {
//		List<String> wbns = new ArrayList<>();
//		wbns.add(waybill);
//		apiCtrl.ApplyNsl(wbns, "UD", "RT-114");
//		apiCtrl.verifyPackageFetchInfoApi(waybill, "UD", "In Transit", "RT-114");
//
//	}
//
//	@Test(dependsOnMethods = "fmOmsFmPick")
//	public void EditPackage() {
//		HashMap<String, String> data = new HashMap<>();
//		data.put("add", "Postmaster, Post Office HANUMAN ROAD (SUB OFFICE), MUMBAI, MAHARASHTRA (MH), India (IN)");
//		data.put("phone", "9999012078");
//		data.put("product_details", "Whey Protein");
//
//		apiCtrl.EditApi(waybill, data);
//		Utilities.hardWait(180);
//		PackageDetails pkgdetails = apiCtrl.fetchPackageInfo(waybill);
//		assertKeyValue("prd", "Whey Protein", pkgdetails.packages.get(0).prd);
//		assertKeyValue("add", "Postmaster, Post Office HANUMAN ROAD (SUB OFFICE), MUMBAI, MAHARASHTRA (MH), India (IN)",
//				pkgdetails.packages.get(0).add.get(0));
//	}
	
	@Test
	public void FetchWaybills() {
		List<String> waybills = apiCtrl.FetchWaybills("HQAPIREGRESSION", 1,clData);
		logInfo("Waybills generated for " + "HQAPIREGRESSION");
		
		for(String w : waybills) {
			logInfo("Waybills -> " + w);
		}
		
	}

}

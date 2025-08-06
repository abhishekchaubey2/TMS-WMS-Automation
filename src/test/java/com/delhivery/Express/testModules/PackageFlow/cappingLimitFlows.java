package com.delhivery.Express.testModules.PackageFlow;

import static com.delhivery.core.utils.Utilities.logInfo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.testng.annotations.Test;

import com.delhivery.Express.controllers.api.ApiController;
import com.delhivery.core.BaseTest;
import com.delhivery.core.utils.Utilities;
import com.delhivery.core.utils.YamlReader;

public class cappingLimitFlows extends BaseTest {
	private ArrayList<String> waybills;

	private final String product_type, payment_mode, pin, package_count;
	public String scenario;
	public HashMap<String, String> clData = new HashMap<>();
	public HashMap<String,String> manifestData = new HashMap<String,String>();
	public Map<String, Object> pkgFlowData;
	public Map<String, Object> bagFlowData;
	ApiController apiCtrl = new ApiController();

	public cappingLimitFlows() {
		clData.put("jobType","ApiSanity");
		manifestData.put("jobType","ApiSanity");
		this.scenario = "Package Flow B2C Prepaid type shipment";
		this.product_type = "B2C";
		this.payment_mode = "Prepaid";
		this.pin = "400059";
		this.package_count = "3";
	}

	@Test
	public void manifest() {
		clData.put("client", "regression_client");
		manifestData.put("product_type",product_type);
		manifestData.put("payment_mode", payment_mode);
		manifestData.put("pin", pin);
		manifestData.put("package_count", package_count);

		waybills = apiCtrl.cmuManifestApi(manifestData);
		logInfo("Waybill generated " + waybills);
		pkgFlowData = YamlReader.getYamlValues("packageFlowScans.manifestation");
		if (product_type.equals("B2B")) {
			Utilities.hardWait(70);
		}
		apiCtrl.verifyPackageFetchInfoApi(waybills, pkgFlowData.get("statusType").toString(), pkgFlowData.get("scanStatus").toString(), pkgFlowData.get("scanNsl").toString(), clData);
	}

}

package com.delhivery.Express.testModules.PackageFlow.ApplyNslTesting;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.testng.annotations.Factory;
import org.testng.annotations.Test;

import static com.delhivery.core.utils.Assertions.assertKeyValue;
import static com.delhivery.core.utils.Utilities.logInfo;

import com.delhivery.Express.controllers.api.ApiController;
import com.delhivery.Express.controllers.api.DifferentStateShipments;
import com.delhivery.Express.dataprovider.manifestationData;
import com.delhivery.Express.pojo.ApplyNslApi.response.ApplyNslResponsePayload;
import com.delhivery.Express.pojo.FetchPackageDetails.response.PackageDetail;
import com.delhivery.Express.pojo.applynslgeneric.response.ApplynslgenericResponse;
import com.delhivery.core.BaseTest;
import com.delhivery.core.utils.Utilities;

public class applynslDTUP2 extends BaseTest {
	private final String product_type;
	private final ThreadLocal<List<String>> waybillList = new ThreadLocal<>();
	ThreadLocal<String> waybillToBeProcess = new ThreadLocal<>();
	private final ThreadLocal<PackageDetail> pkgs = new ThreadLocal<>();
	private final ThreadLocal<ApplyNslResponsePayload> apiResponse = new ThreadLocal<>();
	private final ThreadLocal<HashMap<String, String>> clData = new ThreadLocal<>();
	private final ThreadLocal<HashMap<String, String>> data = new ThreadLocal<>();
	private final ThreadLocal<Map<String, Object>> pkgFlowData = new ThreadLocal<>();
	ThreadLocal<String> last_scan = new ThreadLocal<>();
	DifferentStateShipments diffStShpt = new DifferentStateShipments();

	ApiController apiCtrl = new ApiController();

	@Factory(dataProvider = "Different_pdt_types", dataProviderClass = manifestationData.class)
	public Object[] createInstances(String pdt) {
		return new Object[]{new applynslDTUP2(pdt)};
	}

	public applynslDTUP2(String pdt) {
		this.product_type = pdt;
	}

	@Test(dataProvider = "Different_state_type", dataProviderClass = manifestationData.class, enabled = true)
	public void NSL_DTUP_ZL_Cases_Apply_NSL2(String scenario, String pdt, String state) {
		clData.set(ApplyNSLHelper.getClData("regression_client").get());
		HashMap<String, String> data = new HashMap<String, String>();
		data.put("product_type", product_type);
		data.put("pin", "400059");
		waybillToBeProcess.set(diffStShpt.DifferentStateShipments(state, data));
		logInfo("Waybill " + waybillToBeProcess.get());

		//Fetch Pkg Info
		pkgs.set(apiCtrl.fetchPackageInfo(waybillToBeProcess.get(), clData.get()));
		List<String> list = Arrays.asList(waybillToBeProcess.get());


		//Apply NSL
		if (state == "DELIVERED") {
			ApplyNslResponsePayload applyNslResponsePayload = apiCtrl.ApplyNsl(Collections.singletonList(waybillToBeProcess.get()), "DL", "DTUP-ZL", clData.get());
			assertKeyValue("Success", true, applyNslResponsePayload.isSuccess());
			assertKeyValue("data", "NSL successfully applied on packages " + waybillToBeProcess.get(), applyNslResponsePayload.getData());

			PackageDetail pkg2 = apiCtrl.fetchPackageInfo(waybillToBeProcess.get(), clData.get());
			assertKeyValue("nsl", "DTUP-ZL", pkg2.cs.nsl);

		} else if (state == "PICKEDUP") {
			ApplyNslResponsePayload applyNslResponsePayload = apiCtrl.ApplyNsl(Collections.singletonList(waybillToBeProcess.get()), "PU", "DTUP-ZL", clData.get());
			assertKeyValue("Success", true, applyNslResponsePayload.isSuccess());
			assertKeyValue("data", "NSL successfully applied on packages " + waybillToBeProcess.get(), applyNslResponsePayload.getData());

			PackageDetail pkg2 = apiCtrl.fetchPackageInfo(waybillToBeProcess.get(), clData.get());
			assertKeyValue("nsl", "DTUP-ZL", pkg2.cs.nsl);


		} else if (state == "MANIFEST") {
			ApplyNslResponsePayload applyNslResponsePayload = apiCtrl.ApplyNsl(Collections.singletonList(waybillToBeProcess.get()), "UD", "DTUP-ZL", clData.get());
			assertKeyValue("Success", false, applyNslResponsePayload.isSuccess());
			assertKeyValue("data", "NSL cannot apply on these packages ", applyNslResponsePayload.getMessage());


		} else if (state == "PICKUPPENDING") {
			ApplyNslResponsePayload applyNslResponsePayload = apiCtrl.ApplyNsl(Collections.singletonList(waybillToBeProcess.get()), "PP", "DTUP-ZL", clData.get());
			assertKeyValue("Success", true, applyNslResponsePayload.isSuccess());
			assertKeyValue("data", "NSL successfully applied on packages " + waybillToBeProcess.get(), applyNslResponsePayload.getData());

			PackageDetail pkg2 = apiCtrl.fetchPackageInfo(waybillToBeProcess.get(), clData.get());
			assertKeyValue("nsl", "DTUP-ZL", pkg2.cs.nsl);


		} else if (state == "CANCELLED") {
			ApplyNslResponsePayload applyNslResponsePayload = apiCtrl.ApplyNsl(Collections.singletonList(waybillToBeProcess.get()), "CN", "DTUP-ZL", clData.get());
			assertKeyValue("Success", false, applyNslResponsePayload.isSuccess());
			assertKeyValue("data", "NSL cannot apply on these packages ", applyNslResponsePayload.getMessage());


		} else if (state == "RETURNED") {
			ApplyNslResponsePayload applyNslResponsePayload = apiCtrl.ApplyNsl(Collections.singletonList(waybillToBeProcess.get()), "RT", "DTUP-ZL", clData.get());
			assertKeyValue("Success", true, applyNslResponsePayload.isSuccess());
			assertKeyValue("data", "NSL successfully applied on packages " + waybillToBeProcess.get(), applyNslResponsePayload.getData());

			PackageDetail pkg2 = apiCtrl.fetchPackageInfo(waybillToBeProcess.get(), clData.get());
			assertKeyValue("nsl", "DTUP-ZL", pkg2.cs.nsl);

		} else {
			ApplyNslResponsePayload applyNslResponsePayload = apiCtrl.ApplyNsl(Collections.singletonList(waybillToBeProcess.get()), "UD", "DTUP-ZL", clData.get());
			assertKeyValue("Success", true, applyNslResponsePayload.isSuccess());
			assertKeyValue("data", "NSL successfully applied on packages " + waybillToBeProcess.get(), applyNslResponsePayload.getData());

			PackageDetail pkg2 = apiCtrl.fetchPackageInfo(waybillToBeProcess.get(), clData.get());
			assertKeyValue("nsl", "DTUP-ZL", pkg2.cs.nsl);
		}
		postProcess();
	}

	@Test(dataProvider = "Different_state_type", dataProviderClass = manifestationData.class, enabled = true)
	public void NSL_DTUP_ZL_Cases_Apply_NSL_Generic2(String scenario, String pdt, String state) {
		clData.set(ApplyNSLHelper.getClData("regression_client").get());
		HashMap<String, String> data = new HashMap<String, String>();
		data.put("product_type", product_type);
		data.put("pin", "400059");
		waybillToBeProcess.set(diffStShpt.DifferentStateShipments(state, data));
		logInfo("Waybill " + waybillToBeProcess.get());

		//Fetch Pkg Info
		pkgs.set(apiCtrl.fetchPackageInfo(waybillToBeProcess.get(), clData.get()));
		List<String> list = Arrays.asList(waybillToBeProcess.get());

		//unset dd.id
		if (pkgs.get().dd.id != null) {
			apiCtrl.unsetShipmentDispatchIdApi(waybillToBeProcess.get(), pkgs.get().dd.id.toString(), clData.get());

		}

		//Apply NSL
		if (state == "DELIVERED") {
			ApplynslgenericResponse apiResponse = apiCtrl.ApplyNslGeneric(list, "DL", "DTUP-ZL", "Zero liability shipment", clData.get());
			Utilities.hardWait(20);
			assertKeyValue("success", true, apiResponse.success);
			assertKeyValue("msg", "Task initiated for applying nsl on packages", apiResponse.msg);
			PackageDetail pkg = apiCtrl.fetchPackageInfo(waybillToBeProcess.get(), clData.get());
			assertKeyValue("nsl", "DTUP-ZL", pkg.cs.nsl);


		} else if (state == "PICKEDUP") {
			ApplynslgenericResponse apiResponse = apiCtrl.ApplyNslGeneric(list, "PU", "DTUP-ZL", "Zero liability shipment", clData.get());
			Utilities.hardWait(20);
			assertKeyValue("success", true, apiResponse.success);
			assertKeyValue("msg", "Task initiated for applying nsl on packages", apiResponse.msg);
			PackageDetail pkg = apiCtrl.fetchPackageInfo(waybillToBeProcess.get(), clData.get());
			assertKeyValue("nsl", "DTUP-ZL", pkg.cs.nsl);


		} else if (state == "MANIFEST") {
			ApplynslgenericResponse apiResponse = apiCtrl.ApplyNslGeneric(list, "UD", "DTUP-ZL", "Zero liability shipment", clData.get());
			Utilities.hardWait(20);
			assertKeyValue("success", true, apiResponse.success);
			assertKeyValue("msg", "Task initiated for applying nsl on packages", apiResponse.msg);
			PackageDetail pkg = apiCtrl.fetchPackageInfo(waybillToBeProcess.get(), clData.get());
			assertKeyValue("nsl", "X-UCI", pkg.cs.nsl);


		} else if (state == "PICKUPPENDING") {
			ApplynslgenericResponse apiResponse = apiCtrl.ApplyNslGeneric(list, "PP", "DTUP-ZL", "Zero liability shipment", clData.get());
			Utilities.hardWait(20);
			assertKeyValue("success", true, apiResponse.success);
			assertKeyValue("msg", "Task initiated for applying nsl on packages", apiResponse.msg);
			PackageDetail pkg = apiCtrl.fetchPackageInfo(waybillToBeProcess.get(), clData.get());
			assertKeyValue("nsl", "DTUP-ZL", pkg.cs.nsl);

		} else if (state == "CANCELLED") {
			ApplynslgenericResponse apiResponse = apiCtrl.ApplyNslGeneric(list, "CN", "DTUP-ZL", "Zero liability shipment", clData.get());
			Utilities.hardWait(20);
			assertKeyValue("success", true, apiResponse.success);
			assertKeyValue("msg", "Task initiated for applying nsl on packages", apiResponse.msg);
			PackageDetail pkg = apiCtrl.fetchPackageInfo(waybillToBeProcess.get(), clData.get());
			assertKeyValue("nsl", "EOD-108", pkg.cs.nsl);


		} else if (state == "RETURNED") {
			ApplynslgenericResponse apiResponse = apiCtrl.ApplyNslGeneric(list, "RT", "DTUP-ZL", "Zero liability shipment", clData.get());
			Utilities.hardWait(20);
			assertKeyValue("success", true, apiResponse.success);
			assertKeyValue("msg", "Task initiated for applying nsl on packages", apiResponse.msg);
			PackageDetail pkg = apiCtrl.fetchPackageInfo(waybillToBeProcess.get(), clData.get());
			assertKeyValue("nsl", "DTUP-ZL", pkg.cs.nsl);

		} else {
			ApplynslgenericResponse apiResponse = apiCtrl.ApplyNslGeneric(list, "UD", "DTUP-ZL", "Zero liability shipment", clData.get());
			Utilities.hardWait(20);
			assertKeyValue("success", true, apiResponse.success);
			assertKeyValue("msg", "Task initiated for applying nsl on packages", apiResponse.msg);
			PackageDetail pkg = apiCtrl.fetchPackageInfo(waybillToBeProcess.get(), clData.get());
			assertKeyValue("nsl", "DTUP-ZL", pkg.cs.nsl);
		}
		postProcess();
	}

	@Test(dataProvider = "Different_state_type", dataProviderClass = manifestationData.class, enabled = true)
	public void NSL_DTUP_ZL_Cases_Apply_NSL_LM_Sync_V1_2(String scenario, String pdt, String state) {
		HashMap<String, String> clData = new HashMap<>();
		clData.put("client", "regression_client");
		clData.put("product_type", product_type);
		HashMap<String, String> data = new HashMap<String, String>();
		data.put("product_type", product_type);
		data.put("pin", "400059");
		waybillToBeProcess.set(diffStShpt.DifferentStateShipments(state, data));
		logInfo("Waybill " + waybillToBeProcess.get());

		//Fetch Pkg Info
		pkgs.set(apiCtrl.fetchPackageInfo(waybillToBeProcess.get(), clData));
		ArrayList<String> list = new ArrayList<String>();
		list.add(waybillToBeProcess.get());

		//Apply NSL
		//apiCtrl.lmUpdateHQShipmentApi(list, "zero_liability_DL" , clData);
		Utilities.hardWait(10);
		if (state == "DELIVERED") {
			clData.put("ss", "Delivered");
			clData.put("st", "DL");
			clData.put("nsl_code", "DTUP-ZL");
			apiCtrl.lmUpdateHQShipmentApi(list, "", clData);
			PackageDetail pkg = apiCtrl.fetchPackageInfo(waybillToBeProcess.get(), clData);
			assertKeyValue("nsl", "DTUP-ZL", pkg.cs.nsl);

		} else if (state == "PICKEDUP") {
			clData.put("ss", "Picked Up");
			clData.put("st", "PU");
			clData.put("nsl_code", "DTUP-ZL");
			apiCtrl.lmUpdateHQShipmentApi(list, "", clData);
			PackageDetail pkg = apiCtrl.fetchPackageInfo(waybillToBeProcess.get(), clData);
			assertKeyValue("nsl", "DTUP-ZL", pkg.cs.nsl);

		} else if (state == "PICKUPPENDING") {
			clData.put("ss", "Picked Pending");
			clData.put("st", "PP");
			clData.put("nsl_code", "DTUP-ZL");
			apiCtrl.lmUpdateHQShipmentApi(list, "", clData);
			PackageDetail pkg = apiCtrl.fetchPackageInfo(waybillToBeProcess.get(), clData);
			assertKeyValue("nsl", "DTUP-ZL", pkg.cs.nsl);

		} else if (state == "CANCELLED") {
			clData.put("ss", "Cancelled");
			clData.put("st", "CN");
			clData.put("nsl_code", "DTUP-ZL");
			apiCtrl.lmUpdateHQShipmentApi(list, "", clData);
			PackageDetail pkg = apiCtrl.fetchPackageInfo(waybillToBeProcess.get(), clData);
			assertKeyValue("nsl", "EOD-108", pkg.cs.nsl);


		} else if (state == "RETURNED") {
			clData.put("ss", "Returned");
			clData.put("st", "RT");
			clData.put("nsl_code", "DTUP-ZL");
			apiCtrl.lmUpdateHQShipmentApi(list, "", clData);
			PackageDetail pkg = apiCtrl.fetchPackageInfo(waybillToBeProcess.get(), clData);
			assertKeyValue("nsl", "DTUP-ZL", pkg.cs.nsl);


		} else if (state == "MANIFESTED") {
			clData.put("ss", "Manifested");
			clData.put("st", "UD");
			clData.put("nsl_code", "DTUP-ZL");
			apiCtrl.lmUpdateHQShipmentApi(list, "", clData);
			PackageDetail pkg = apiCtrl.fetchPackageInfo(waybillToBeProcess.get(), clData);
			assertKeyValue("nsl", "DTUP-ZL", pkg.cs.nsl);


		} else if (state == "INTRANSIT") {
			clData.put("ss", "In Transit");
			clData.put("st", "UD");
			clData.put("nsl_code", "DTUP-ZL");
			apiCtrl.lmUpdateHQShipmentApi(list, "", clData);
			PackageDetail pkg = apiCtrl.fetchPackageInfo(waybillToBeProcess.get(), clData);
			assertKeyValue("nsl", "DTUP-ZL", pkg.cs.nsl);


		} else if (state == "PENDING") {
			clData.put("ss", "PENDING");
			clData.put("st", "UD");
			clData.put("nsl_code", "DTUP-ZL");
			apiCtrl.lmUpdateHQShipmentApi(list, "", clData);
			PackageDetail pkg = apiCtrl.fetchPackageInfo(waybillToBeProcess.get(), clData);
			assertKeyValue("nsl", "DTUP-ZL", pkg.cs.nsl);

		}
		postProcess();
	}

	private void postProcess() {
		ThreadLocal<?>[] variables = {data, clData, waybillList, waybillToBeProcess, pkgFlowData, apiResponse, last_scan, pkgs};
		for (ThreadLocal<?> variable : variables) {
			if (variable != null) {
				variable.remove();
			}
		}
	}
}
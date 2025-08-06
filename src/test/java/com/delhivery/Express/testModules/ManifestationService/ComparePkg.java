package com.delhivery.Express.testModules.ManifestationService;

import static com.delhivery.core.utils.Utilities.logFailure;
import static com.delhivery.core.utils.Utilities.logInfo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.delhivery.Express.controllers.api.ApiController;
import com.delhivery.Express.pojo.FetchPackageDetails2.response.PackageDetailNew;
import com.delhivery.Express.pojo.GetManifestUplApi.response.GetManifestUplDataResponsePayload;
import org.json.JSONObject;

import com.aventstack.extentreports.gherkin.model.Scenario;
import com.delhivery.Express.pojo.FetchPackageDetails.response.PackageDetail;
import com.delhivery.core.BaseTest;
import com.delhivery.core.utils.ManifestationKeysAssertions;
import com.delhivery.core.utils.Utilities;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ComparePkg  extends BaseTest{
	static String NewWbn,StageWbn, child_waybill = "", NewUPL, StageUPL;
	static ApiController apiCtrl = new ApiController();
	static ObjectMapper mapper = new ObjectMapper();
	static JSONObject js=new JSONObject();
	
	public static String NewEnv= "NewManifest";
	public static String StagingEnv = "staging";

	//compare function to compare waybill details of edt and staging
	public static void compare(List<String> edtApiResponse, List<String> stagingApiResponse, String Scenario) throws JsonProcessingException {
		//API response Compare
		try {
			logInfo("API response for edt:" + edtApiResponse.get(0));
			logInfo("API response for staging:" + stagingApiResponse.get(0));
			ManifestationKeysAssertions.compareNewServiceStagingAPIResponse(stagingApiResponse.get(0).toString(), edtApiResponse.get(0).toString());
		}catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//API response code compare
		int NewServiceResponseCode = Integer.parseInt  (edtApiResponse.get(3).toString());
		int StagingResponseCode = Integer.parseInt  (edtApiResponse.get(3).toString());
        if(StagingResponseCode ==NewServiceResponseCode){
			logInfo("Response code is same for Staging and New Service");
			logInfo("Response code of Staging : " + StagingResponseCode);
			logInfo("Response code of New Service : " + NewServiceResponseCode);
		}else{
			logFailure("Response code is different for staging and New service");
			logInfo("Response code of Staging : " + StagingResponseCode);
			logInfo("Response code of New Service : " + NewServiceResponseCode);
		}

		//API response time compare
		int NewServiceResponseTime= Integer.parseInt (edtApiResponse.get(2).toString());
		int StagingResponseTime= Integer.parseInt(stagingApiResponse.get(2).toString());
		int Timediffernece= NewServiceResponseTime-StagingResponseTime;
		if(Timediffernece>500){
			logInfo("Response time of new service : " + NewServiceResponseTime);
			logInfo("Response time of staging : " + StagingResponseTime);
			logFailure("Response time is greater than 500ms :" + Timediffernece );
		}else{
			logInfo("Response time of new service : " + NewServiceResponseTime);
			logInfo("Response time of staging : " + StagingResponseTime);
			logInfo("Response time difference is :" + Timediffernece);
		}

		//UPL Response
		if(edtApiResponse.get(2).toString()=="" && stagingApiResponse.get(2).toString()=="" ) {
			logInfo("Manifestation failed for both edt and staging");
		}else {
		String New_Service_UPL = apiCtrl.getManifestationUplDataNewService(edtApiResponse.get(1).toString());
			Utilities.hardWait(1);
			if(Scenario.contains("address") || Scenario.contains("product description")  || Scenario.contains("number")){
				Utilities.hardWait(60);
			}
		String Staging_UPL = apiCtrl.getManifestationUplDataNewService(stagingApiResponse.get(1).toString());
			Utilities.hardWait(1);
			if(Scenario.contains("address") || Scenario.contains("product description")  || Scenario.contains("number")){
				Utilities.hardWait(60);
			}
		try {
			logInfo("UPL response for New Service:" + New_Service_UPL);
			logInfo("UPL response for staging:" + Staging_UPL);
			ManifestationKeysAssertions.compareNewServiceStagingAPIResponse(Staging_UPL, New_Service_UPL);
		}catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}

		//Json Compare for generated waybills
		if(Scenario.contains("MPS")){
			if(edtApiResponse.get(5).toString()=="" && stagingApiResponse.get(5).toString()=="") {
				logInfo("Manifestation failed for both edt and staging");
			}else{
				if(edtApiResponse.get(4).toString()=="" && stagingApiResponse.get(4).toString()=="" ) {
					logInfo("Manifestation failed for both edt and staging");
				}else {
					HashMap<String, String> requestPayload = new HashMap();
					//call assertion function here
					PackageDetailNew New_Service_Wbn_Details = apiCtrl.NewServicefetchPackageInfoDiffEnv(NewEnv, edtApiResponse.get(4).toString(), requestPayload);
					PackageDetailNew Staging_Wbn_Details = apiCtrl.NewServicefetchPackageInfoDiffEnv(StagingEnv, stagingApiResponse.get(4).toString(), requestPayload);

					try {
						logInfo("Waybill generated from new env :: " + edtApiResponse.get(4).toString());
						logInfo("Waybill generated from staging env :: " + stagingApiResponse.get(4).toString());
						ManifestationKeysAssertions.compareNewServiceStagingShipment(Staging_Wbn_Details, New_Service_Wbn_Details);
					} catch (JsonProcessingException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

					if (edtApiResponse.get(1).toString().contains("MPS")) {
						PackageDetailNew  New_Service_Child_Shipment_Details = apiCtrl.NewServicefetchPackageInfoDiffEnv(NewEnv, edtApiResponse.get(5).toString(), requestPayload);

						PackageDetailNew  Staging_Child_Shipment_Details = apiCtrl.NewServicefetchPackageInfoDiffEnv(StagingEnv, stagingApiResponse.get(5).toString(), requestPayload);

						try {
							logInfo("Child waybill generated from new env :: " + edtApiResponse.get(3).toString());
							logInfo("Child waybill generated from staging env :: " + stagingApiResponse.get(3).toString());
							ManifestationKeysAssertions.compareNewServiceStagingShipment(New_Service_Child_Shipment_Details, Staging_Child_Shipment_Details);
						} catch (JsonProcessingException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}

			}
		}else{
			if(edtApiResponse.get(4).toString()=="" && stagingApiResponse.get(4).toString()=="" ) {
				logInfo("Manifestation failed for both edt and staging");
			}else {
				HashMap<String, String> requestPayload = new HashMap();
				//call assertion function here
				PackageDetailNew New_Service_Wbn_Details = apiCtrl.NewServicefetchPackageInfoDiffEnv(NewEnv, edtApiResponse.get(4).toString(), requestPayload);
				PackageDetailNew Staging_Wbn_Details = apiCtrl.NewServicefetchPackageInfoDiffEnv(StagingEnv, stagingApiResponse.get(4).toString(), requestPayload);

				try {
					logInfo("Waybill generated from new env :: " + edtApiResponse.get(4).toString());
					logInfo("Waybill generated from staging env :: " + stagingApiResponse.get(4).toString());
					ManifestationKeysAssertions.compareNewServiceStagingShipment(Staging_Wbn_Details, New_Service_Wbn_Details);
				} catch (JsonProcessingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				if (edtApiResponse.get(1).toString().contains("MPS")) {
					PackageDetailNew New_Service_Child_Shipment_Details = apiCtrl.NewServicefetchPackageInfoDiffEnv(NewEnv, edtApiResponse.get(5).toString(), requestPayload);

					PackageDetailNew Staging_Child_Shipment_Details = apiCtrl.NewServicefetchPackageInfoDiffEnv(StagingEnv, stagingApiResponse.get(5).toString(), requestPayload);

					try {
						logInfo("Child waybill generated from new env :: " + edtApiResponse.get(3).toString());
						logInfo("Child waybill generated from staging env :: " + stagingApiResponse.get(3).toString());
						ManifestationKeysAssertions.compareNewServiceStagingShipment(New_Service_Child_Shipment_Details, Staging_Child_Shipment_Details);
					} catch (JsonProcessingException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}

		}

		//ewbn collection compare
		//condition to check if scenario contains "ewbn"
		if(Scenario.contains("ewbn")) {
			//call FetchEwbnCollection for both new service and staging waybills
			String newServiceEwbnDetails = apiCtrl.FetchEwbnCollection(NewEnv,edtApiResponse.get(4).toString());
			String stagingEwbnDetails = apiCtrl.FetchEwbnCollection(NewEnv,stagingApiResponse.get(4).toString());
			//log both responses
			logInfo("Ewbn collection response for new service :: " + newServiceEwbnDetails);
			logInfo("Ewbn collection response for staging :: " + stagingEwbnDetails);
			//compare both responses
			ManifestationKeysAssertions.compareNewServiceStagingAPIResponse(newServiceEwbnDetails, stagingEwbnDetails);
		}

	}




}

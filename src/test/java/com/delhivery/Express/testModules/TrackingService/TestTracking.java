package com.delhivery.Express.testModules.TrackingService;


import com.delhivery.Express.controllers.api.ApiController;
import com.delhivery.Express.controllers.api.DifferentStateShipments;
import com.delhivery.Express.controllers.api.DifferentTypeShipments;
import com.delhivery.Express.pojo.hqTracking.response.HQTrack;
import com.delhivery.Express.pojo.hqTrackingError.response.HQTrackError;
import com.delhivery.Express.pojo.trackingApi.response.Example;
import com.delhivery.Express.pojo.trackingApiError.response.TrackError;
import com.delhivery.core.BaseTest;
import com.delhivery.core.utils.Assertions;
import com.delhivery.core.utils.Utilities;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.delhivery.core.utils.Utilities.logInfo;

public class TestTracking extends BaseTest {
    DifferentStateShipments diffStShpt = new DifferentStateShipments();
    DifferentTypeShipments difftypeShpt = new DifferentTypeShipments();
    ApiController apiCtrl = new ApiController();
    Map<String, String> clData = new HashMap<String, String>();
    Map<String, String> clData2 = new HashMap<String, String>();
    Map<String, String> parameter = new HashMap<String, String>();
    Map<String, String> trackParameter = new HashMap<String, String>();
    Map<String, String> trackParameter1 = new HashMap<String, String>();
    Map<String, String> trackParameter2 = new HashMap<String, String>();
    Map<String, String> trackParameter3 = new HashMap<String, String>();
    Map<String, String> trackParameter4 = new HashMap<String, String>();
    Map<String, String> trackParameter5 = new HashMap<String, String>();
    Map<String, String> trackParameter6 = new HashMap<String, String>();
    Map<String, String> hqParameter = new HashMap<String, String>();
    List<String> WAYBILL = new ArrayList<String>();
    List<String> STATE = new ArrayList<String>();
    private String waybill;
    private String waybill2;
    Example trackingShipmentDetails = new Example();

    public void hitingWithWbnOnly() {

        try {
            waybill = diffStShpt.DifferentStateShipments("MANIFEST");
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }


        parameter.put("wbns", waybill);


        apiCtrl.trackingApi(parameter);

    }


    public void hitingHqApiWithWbnOnly() {

        try {
            waybill = diffStShpt.DifferentStateShipments("MANIFEST");
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        parameter.put("waybill", waybill);
        apiCtrl.hqTrackingApi(parameter);

    }

    @DataProvider(name = "HITTING_WITH_VERBOSE", parallel = false)
    public Object[][] hitting_with_verbose() {
        return new Object[][]{
                {"Scenario:: VERBOSE_HAVING_VALUE_ZERO_MANIFEST ", "0", "MANIFEST"},
                {"Scenario:: VERBOSE_HAVING_VALUE_ZERO_PENDING ", "0", "PENDING"},
                {"Scenario:: VERBOSE_HAVING_VALUE_ZERO_DISPATCHED ", "0", "DISPATCHED"},
                {"Scenario:: VERBOSE_HAVING_VALUE_ZERO_DELIVERED ", "0", "DELIVERED"},
                {"Scenario:: VERBOSE_HAVING_VALUE_ZERO_RETURNED ", "0", "RETURNED"},
                {"Scenario:: VERBOSE_HAVING_VALUE_ZERO_RTO ", "0", "RTO"},
                {"Scenario:: VERBOSE_HAVING_VALUE_ZERO_LOST ", "0", "LOST"},
                {"Scenario:: VERBOSE_HAVING_VALUE_ZERO_PICKUPPENDING ", "0", "PICKUPPENDING"},
                {"Scenario:: VERBOSE_HAVING_VALUE_ZERO_PICKEDUP ", "0", "PICKEDUP"},
                {"Scenario:: VERBOSE_HAVING_VALUE_ZERO_DTO ", "0", "DTO"},
                {"Scenario:: VERBOSE_HAVING_VALUE_ZERO_CANCELLED ", "0", "CANCELLED"},

                {"Scenario:: VERBOSE_HAVING_VALUE_ONE_MANIFEST ", "1", "MANIFEST"},
                {"Scenario:: VERBOSE_HAVING_VALUE_ONE_PENDING ", "1", "PENDING"},
                {"Scenario:: VERBOSE_HAVING_VALUE_ONE_DISPATCHED ", "1", "DISPATCHED"},
                {"Scenario:: VERBOSE_HAVING_VALUE_ONE_DELIVERED ", "1", "DELIVERED"},
                {"Scenario:: VERBOSE_HAVING_VALUE_ONE_RETURNED ", "1", "RETURNED"},
                {"Scenario:: VERBOSE_HAVING_VALUE_ONE_RTO ", "1", "RTO"},
                {"Scenario:: VERBOSE_HAVING_VALUE_ONE_LOST ", "1", "LOST"},
                {"Scenario:: VERBOSE_HAVING_VALUE_ONE_PICKUPPENDING ", "1", "PICKUPPENDING"},
                {"Scenario:: VERBOSE_HAVING_VALUE_ONE_PICKEDUP ", "1", "PICKEDUP"},
                {"Scenario:: VERBOSE_HAVING_VALUE_ONE_DTO ", "1", "DTO"},
                {"Scenario:: VERBOSE_HAVING_VALUE_ONE_CANCELLED ", "1", "CANCELLED"},

                {"Scenario:: VERBOSE_HAVING_VALUE_TWO_MANIFEST ", "2", "MANIFEST"},
                {"Scenario:: VERBOSE_HAVING_VALUE_TWO_PENDING ", "2", "PENDING"},
                {"Scenario:: VERBOSE_HAVING_VALUE_TWO_DISPATCHED ", "2", "DISPATCHED"},
                {"Scenario:: VERBOSE_HAVING_VALUE_TWO_DELIVERED ", "2", "DELIVERED"},
                {"Scenario:: VERBOSE_HAVING_VALUE_TWO_RETURNED ", "2", "RETURNED"},
                {"Scenario:: VERBOSE_HAVING_VALUE_TWO_RTO ", "2", "RTO"},
                {"Scenario:: VERBOSE_HAVING_VALUE_TWO_LOST ", "2", "LOST"},
                {"Scenario:: VERBOSE_HAVING_VALUE_TWO_PICKUPPENDING ", "2", "PICKUPPENDING"},
                {"Scenario:: VERBOSE_HAVING_VALUE_TWO_PICKEDUP ", "2", "PICKEDUP"},
                {"Scenario:: VERBOSE_HAVING_VALUE_TWO_DTO ", "2", "DTO"},
                {"Scenario:: VERBOSE_HAVING_VALUE_TWO_CANCELLED ", "2", "CANCELLED"}


        };
    }

    @Test(dataProvider = "HITTING_WITH_VERBOSE", enabled = true)
    public void hitingWithverbose(String Scenario, String value, String State) {

        try {
            waybill = diffStShpt.DifferentStateShipments(State);

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        hqParameter.put("waybill", waybill);
        hqParameter.put("verbose", value);
        trackParameter.put("wbns", waybill);
        trackParameter.put("verbose", value);


        Example trackingShipmentDetails = apiCtrl.fetchPackageInfoTrackingEnv("Trackin", trackParameter);

        //HQTrack edtMasterShipmentDetails = apiCtrl.fetchPackageInfoHqEnv("edt", hqParameter);

        HQTrack stagingMasterShipmentDetails = apiCtrl.fetchPackageInfoHqEnv("staging", hqParameter);


        try {
            logInfo("Waybill generated from Trackin env :: " + trackParameter.get("Wbns"));
            logInfo("Waybill generated from staging env :: " + hqParameter.get("waybill"));
            Assertions.comparePkgResponses(stagingMasterShipmentDetails, trackingShipmentDetails);
        } catch (JsonProcessingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @DataProvider(name = "TEST_FOR_NEGATIVE_CASES", parallel = false)
    public Object[][] test_for_negative_cases() {
        return new Object[][]{

                //{ "Scenario:: hit_service_with_100_wbns ", "0","352028516341,352028516352,352028516326,352028516330,352028516400,352028516385,352028516363,352028516411,352028516374,352028516396,352028516422,352028516433,352028516444,352028516481,352028516455,352028516562,352028516466,352028516536,352028516503,352028516514,352028516492,352028516470,352028516676,352028516540,352028516525,352028516643,352028516713,352028516573,352028516606,352028516610,352028516702,352028516691,352028516551,352028516772,352028516724,352028516746,352028516680,352028516750,352028516665,352028516584,352028516783,352028516654,352028516595,352028516632,352028516735,352028516794,352028516761,352028516621,352028516805,352028516820,352028516816,352028516831,352028516842,352028516875,352028516853,352028516864,352028516890,352028516886,352028516901,352028516934,352028516945,352028516971,352028516960,352028516982,352028516956,352028517015,352028516912,352028516923,352028516993,352028517004,352028517041,352028517026,352028517052,352028517030,352028517074,352028517085,352028517155,352028517144,352028517096,352028517063,352028517166,352028517192,352028517111,352028517133,352028517181,352028517170,352028517100,352028517122,352028517203,352028517225,352028517214,352028517236,352028517240,352028517273,352028517251,352028517262,352028517284,352028517295,352028517306,352028517310"},
                //{ "Scenario:: hit_service_with_incorrect_wbns ", "0","35202851634"},

                //{ "Scenario:: hit_service_with_100_wbns ", "1","352028516341,352028516352,352028516326,352028516330,352028516400,352028516385,352028516363,352028516411,352028516374,352028516396,352028516422,352028516433,352028516444,352028516481,352028516455,352028516562,352028516466,352028516536,352028516503,352028516514,352028516492,352028516470,352028516676,352028516540,352028516525,352028516643,352028516713,352028516573,352028516606,352028516610,352028516702,352028516691,352028516551,352028516772,352028516724,352028516746,352028516680,352028516750,352028516665,352028516584,352028516783,352028516654,352028516595,352028516632,352028516735,352028516794,352028516761,352028516621,352028516805,352028516820,352028516816,352028516831,352028516842,352028516875,352028516853,352028516864,352028516890,352028516886,352028516901,352028516934,352028516945,352028516971,352028516960,352028516982,352028516956,352028517015,352028516912,352028516923,352028516993,352028517004,352028517041,352028517026,352028517052,352028517030,352028517074,352028517085,352028517155,352028517144,352028517096,352028517063,352028517166,352028517192,352028517111,352028517133,352028517181,352028517170,352028517100,352028517122,352028517203,352028517225,352028517214,352028517236,352028517240,352028517273,352028517251,352028517262,352028517284,352028517295,352028517306,352028517310"},
                //{ "Scenario:: hit_service_with_incorrect_wbns ", "1","35202851634"},

                //{ "Scenario:: hit_service_with_100_wbns ", "2","352028516341,352028516352,352028516326,352028516330,352028516400,352028516385,352028516363,352028516411,352028516374,352028516396,352028516422,352028516433,352028516444,352028516481,352028516455,352028516562,352028516466,352028516536,352028516503,352028516514,352028516492,352028516470,352028516676,352028516540,352028516525,352028516643,352028516713,352028516573,352028516606,352028516610,352028516702,352028516691,352028516551,352028516772,352028516724,352028516746,352028516680,352028516750,352028516665,352028516584,352028516783,352028516654,352028516595,352028516632,352028516735,352028516794,352028516761,352028516621,352028516805,352028516820,352028516816,352028516831,352028516842,352028516875,352028516853,352028516864,352028516890,352028516886,352028516901,352028516934,352028516945,352028516971,352028516960,352028516982,352028516956,352028517015,352028516912,352028516923,352028516993,352028517004,352028517041,352028517026,352028517052,352028517030,352028517074,352028517085,352028517155,352028517144,352028517096,352028517063,352028517166,352028517192,352028517111,352028517133,352028517181,352028517170,352028517100,352028517122,352028517203,352028517225,352028517214,352028517236,352028517240,352028517273,352028517251,352028517262,352028517284,352028517295,352028517306,352028517310"},
                //{ "Scenario:: hit_service_with_incorrect_wbns ", "2","35202851634"},

                //{ "Scenario:: hit_service_with_empty_wbns ", "",""},
                //{ "Scenario:: hit_service_with_ref_id ", "2","352028516341"},
                {"Scenario:: hit_service_with_Less_Than_75_wbns ", "2", "352028516341,352028516352,352028516326,352028516330,352028516400,352028516385,352028516363,352028516411,352028516374,352028516396,352028516422,352028516433,352028516444,352028516481,352028516455,352028516562,352028516466,352028516536,352028516503,352028516514,352028516492,352028516470,352028516676,352028516540,352028516525,352028516643,352028516713,352028516573,352028516606,352028516610,352028516702,352028516691,352028516551,352028516772,352028516724,352028516746,352028516680,352028516750,352028516665,352028516584,352028516783,352028516654,352028516595,352028516632,352028516735,352028516794,352028516761,352028516621,352028516805,352028516820,352028516816"}

        };
    }

    //(dataProvider = "TEST_FOR_NEGATIVE_CASES", enabled = true)
    public void testForNegativeCases(String Scenario, String value, String wbn) {
        String msg = null, msg2;
        if (Scenario == "Scenario:: hit_service_with_Less_Than_75_wbns ") {
            trackParameter.put("wbns", wbn);
            hqParameter.put("waybill", wbn);
            hqParameter.put("verbose", value);
        } else if (Scenario == "Scenario:: hit_service_with_ref_id ") {
            hqParameter.put("ref_id", wbn);
            hqParameter.put("verbose", value);


        } else {
            hqParameter.put("waybill", wbn);
            hqParameter.put("verbose", value);
            trackParameter.put("wbns", wbn);
            trackParameter.put("verbose", value);
        }
        if (Scenario == "Scenario:: hit_service_with_incorrect_wbns ") {
            msg = "Data does not exists for provided Waybill(s)";
            msg2 = "No such waybill or Order Id found";
        } else if (Scenario == "Scenario:: hit_service_with_empty_wbns ") {
            msg = "Package handler limit exceeded";
            msg2 = "ref_ids/ref_nos or waybill cannot be greater than 75";
        } else {
            msg = "Parameter refIDs/refNos or waybill or dateRange is required";
            msg2 = "parameter ref_ids/ref_nos or waybill is required";
        }
        if (Scenario == "Scenario:: hit_service_with_ref_id ") {

            TrackError trackingShipmentDetails = apiCtrl.fetchPackageInfoNegativeCaseTrackingEnv("Trackin", trackParameter, Scenario);
            Assertions.assertKeyValue("message", msg, trackingShipmentDetails.error.message);
        } else if (Scenario == "Scenario:: hit_service_with_Less_Than_75_wbns ") {
            //Example trackingShipmentDetails = apiCtrl.fetchPackageInfoTrackingEnv("Trackin", trackParameter);


            HQTrack edtMasterShipmentDetails = apiCtrl.fetchPackageInfoHqEnv("edt", hqParameter);
            HQTrack stagingMasterShipmentDetails = apiCtrl.fetchPackageInfoHqEnv("staging", hqParameter);

            try {
                logInfo("Waybill generated from Trackin env :: " + trackParameter.get("Wbns"));
                logInfo("Waybill generated from staging env :: " + hqParameter.get("waybill"));
                Assertions.comparePkgResponses(stagingMasterShipmentDetails, edtMasterShipmentDetails);
            } catch (JsonProcessingException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        } else {


            //TrackError trackingShipmentDetails =apiCtrl.fetchPackageInfoNegativeCaseTrackingEnv("Trackin",trackParameter,Scenario);
            //Assertions.assertKeyValue("message", msg,trackingShipmentDetails.error.message);
            //HQTrackError edtMasterShipmentDetails = apiCtrl.fetchPackageInfoNegativeCaseHqEnv("edt", hqParameter);
            //Assertions.assertKeyValue("Error", "ref_ids/ref_nos or waybill cannot be greater than 75", edtMasterShipmentDetails.error);
            //HQTrackError stagingMasterShipmentDetails = apiCtrl.fetchPackageInfoNegativeCaseHqEnv("staging", hqParameter);
            //Assertions.assertKeyValue("Error", "ref_ids/ref_nos or waybill cannot be greater than 75", stagingMasterShipmentDetails.error);

            HQTrackError edtMasterShipmentDetails = apiCtrl.fetchPackageInfoNegativeCaseHqEnv("edt", hqParameter);
            HQTrackError stagingMasterShipmentDetails = apiCtrl.fetchPackageInfoNegativeCaseHqEnv("staging", hqParameter);
            //Assertions.assertKeyValue("Error", msg2, edtMasterShipmentDetails.error);
            try {
                logInfo("Waybill generated from Trackin env :: " + trackParameter.get("Wbns"));
                logInfo("Waybill generated from staging env :: " + hqParameter.get("waybill"));
                Assertions.comparePkgResponses(stagingMasterShipmentDetails, edtMasterShipmentDetails);
            } catch (JsonProcessingException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }


    }

    @DataProvider(name = "DEFAULT_KEY_TEST_CASES", parallel = false)
    public Object[][] default_key_test_cases() {
        return new Object[][]{


                {"Scenario:: default_key_test_cases_is_staff_key_not_present", "5265161092874", "true", "true", "CL", "Client", "true", "2"},
                {"Scenario:: default_key_test_cases_is_agent_key_not_present", "5265161092874", "true", "true", "CL", "Client", "true", "2"},
                {"Scenario:: default_key_test_cases_is_agent_and_user_type_keys_not_present", "5265161092874", "false", "true", "CL", "Client", "true", "2"},
                {"Scenario:: default_key_test_cases_is_agent_is_staff_and_nsl_user_type_keys_not_present", "5265161092874", "true", "true", "CL", "Client", "true", "2"},
                {"Scenario:: default_key_test_cases_is_agent_is_staff_verbose_and_nsl_user_type_keys_not_present", "5265161092874", "true", "true", "CL", "Client", "true", "2"},
                {"Scenario:: default_key_test_cases_is_agent_is_staff_verbose_user_type_and_nsl_user_type_keys_not_present", "5265161092874", "true", "true", "CL", "Client", "true", "2"},
                {"Scenario:: default_key_test_cases_when_only_wbn_is_passed", "5265161092874", "true", "true", "CL", "Client", "true", "2"}

        };
    }

    @Test(dataProvider = "DEFAULT_KEY_TEST_CASES", enabled = true)
    public void DefaultKeyTestCases(String Scenario, String wbn, String is_staf, String is_agent, String user_type, String nsl_user_type, String fetch_pkg_info, String value) {
//https://express-tracking-service.pntrzz.com/v1/package/track?wbns=5265161439945&is_staff=true&is_agent=true&user_type=CL&nsl_user_type=Internal&fetch_pkg_info=true&verbose=2


        if (Scenario == "Scenario:: default_key_test_cases_is_staff_key_not_present") {
            trackParameter.put("wbns", wbn);
            trackParameter.put("verbose", value);
            trackParameter.put("is_agent", is_agent);
            trackParameter.put("user_type", user_type);
            trackParameter.put("nsl_user_type", nsl_user_type);
            trackParameter.put("fetch_pkg_info", fetch_pkg_info);
            trackingShipmentDetails = apiCtrl.fetchPackageInfoTrackingEnv("Trackin", trackParameter);
        } else if (Scenario == "Scenario:: default_key_test_cases_is_agent_key_not_present") {
            trackParameter1.put("wbns", wbn);
            trackParameter1.put("verbose", value);
            trackParameter1.put("is_staff", is_agent);
            trackParameter1.put("user_type", user_type);
            trackParameter1.put("nsl_user_type", nsl_user_type);
            trackParameter1.put("fetch_pkg_info", fetch_pkg_info);
            trackingShipmentDetails = apiCtrl.fetchPackageInfoTrackingEnv("Trackin", trackParameter1);
        }
        //wbns=5265161092874&is_staff=false&nsl_user_type=Client&fetch_pkg_info=true&verbose=2
        else if (Scenario == "Scenario:: default_key_test_cases_is_agent_and_user_type_keys_not_present") {
            trackParameter2.put("wbns", wbn);
            trackParameter2.put("verbose", value);
            trackParameter2.put("is_staff", is_agent);
            trackParameter2.put("nsl_user_type", nsl_user_type);
            trackParameter2.put("fetch_pkg_info", fetch_pkg_info);
            trackingShipmentDetails = apiCtrl.fetchPackageInfoTrackingEnv("Trackin", trackParameter2);

        }
        //wbns=5265161092874&user_type=CL&fetch_pkg_info=true&verbose=2
        else if (Scenario == "Scenario:: default_key_test_cases_is_agent_is_staff_and_nsl_user_type_keys_not_present") {
            trackParameter3.put("wbns", wbn);
            trackParameter3.put("verbose", value);
            trackParameter3.put("user_type", user_type);
            trackParameter3.put("fetch_pkg_info", fetch_pkg_info);
            trackingShipmentDetails = apiCtrl.fetchPackageInfoTrackingEnv("Trackin", trackParameter3);
        } else if (Scenario == "Scenario:: default_key_test_cases_is_agent_is_staff_verbose_and_nsl_user_type_keys_not_present") {
            trackParameter4.put("wbns", wbn);
            trackParameter4.put("user_type", user_type);
            trackParameter4.put("fetch_pkg_info", fetch_pkg_info);
            trackingShipmentDetails = apiCtrl.fetchPackageInfoTrackingEnv("Trackin", trackParameter4);

        } else if (Scenario == "Scenario:: default_key_test_cases_is_agent_is_staff_verbose_user_type_and_nsl_user_type_keys_not_present") {
            trackParameter5.put("wbns", wbn);
            trackParameter5.put("fetch_pkg_info", fetch_pkg_info);
            trackingShipmentDetails = apiCtrl.fetchPackageInfoTrackingEnv("Trackin", trackParameter5);
        } else {
            trackParameter6.put("wbns", wbn);
            trackingShipmentDetails = apiCtrl.fetchPackageInfoTrackingEnv("Trackin", trackParameter6);
        }


        if (Scenario == "Scenario:: default_key_test_cases_is_staff_key_not_present") {
            Assertions.assertKeyValue("Instructions", "Shipment Recieved at Origin Center", trackingShipmentDetails.data.shipmentData.get(0).shipment.status.instructions);
        } else if (Scenario == "Scenario:: default_key_test_cases_is_agent_key_not_present") {
            Assertions.assertKeyValue("Instructions", "Package Missing in Audit", trackingShipmentDetails.data.shipmentData.get(0).shipment.status.instructions);
        } else if (Scenario == "Scenario:: default_key_test_cases_is_agent_key_not_present") {
            Assertions.assertKeyValue("Instructions", "Package Missing in Audit", trackingShipmentDetails.data.shipmentData.get(0).shipment.status.instructions);
        } else if (Scenario == "Scenario:: default_key_test_cases_is_agent_is_staff_and_nsl_user_type_keys_not_present") {
            Assertions.assertKeyValue("Instructions", "Shipment Recieved at Origin Center", trackingShipmentDetails.data.shipmentData.get(0).shipment.status.instructions);
        } else if (Scenario == "Scenario:: default_key_test_cases_is_agent_is_staff_verbose_and_nsl_user_type_keys_not_present") {
            Assertions.assertKeyValue("Instructions", "Shipment Recieved at Origin Center", trackingShipmentDetails.data.shipmentData.get(0).shipment.status.instructions);
        } else if (Scenario == "Scenario:: default_key_test_cases_is_agent_is_staff_verbose_user_type_and_nsl_user_type_keys_not_present") {
            Assertions.assertKeyValue("Instructions", "Package Missing in Audit", trackingShipmentDetails.data.shipmentData.get(0).shipment.status.instructions);
        } else {
            Assertions.assertKeyValue("Instructions", "Package Missing in Audit", trackingShipmentDetails.data.shipmentData.get(0).shipment.status.instructions);
        }

    }


    @DataProvider(name = "TEST_FOR_NSL_AND_OTHER_QUERY_PARAMS_CASES", parallel = false)
    public Object[][] test_for_nsl_and_other_query_params_cases() {
        return new Object[][]{


                {"Scenario:: nsl_belong_to_L-PMA_or_U-PMA_with_staff_true", "5265161092874", "true", "true", "CL", "Client", "true", "2"},
                {"Scenario:: nsl_belong_to_L-PMA_or_U-PMA_with_staff_false", "5265161092874", "false", "true", "CL", "Client", "true", "2"},
                {"Scenario:: nsl_not_belong_to_L-PMA_or_U-PMA_with_staff_false", "5265161439923", "false", "true", "CL", "Client", "true", "2"},
                {"Scenario:: verify_unique_scans", "5265161439945", "true", "true", "CL", "Client", "true", "2"}

        };
    }

    //(dataProvider = "TEST_FOR_NSL_AND_OTHER_QUERY_PARAMS_CASES", enabled = true)
    public void testForNslAndOtherQueryParamsCases(String Scenario, String wbn, String is_staf, String is_agent, String user_type, String nsl_user_type, String fetch_pkg_info, String value) {

        trackParameter.put("wbn", wbn);
        trackParameter.put("verbose", value);
        trackParameter.put("is_staf", is_staf);
        trackParameter.put("is_agent", is_agent);
        trackParameter.put("user_type", user_type);
        trackParameter.put("nsl_user_type", nsl_user_type);
        trackParameter.put("fetch_pkg_info", fetch_pkg_info);
        Example trackingShipmentDetails = apiCtrl.fetchPackageInfoTrackingEnv("Trackin", trackParameter);
        //Assertions.assertSuccessMessageInJson(trackingShipmentDetails.data.shipmentData.get(0).shipment.scans.get(5).scanDetail.statusCode,"L-PMA");

        if (Scenario == "Scenario:: nsl_belong_to_L-PMA_or_U-PMA_with_staff_true") {
            Assertions.assertKeyValue("statusCode", "L-PMA", trackingShipmentDetails.data.shipmentData.get(0).shipment.scans.get(5).scanDetail.statusCode);
        } else if (Scenario == "Scenario:: nsl_belong_to_L-PMA_or_U-PMA_with_staff_false") {
            Assertions.assertKeyValue("statusCode", "X-PIOM", trackingShipmentDetails.data.shipmentData.get(0).shipment.scans.get(4).scanDetail.statusCode);
        } else if (Scenario == "Scenario:: nsl_not_belong_to_L-PMA_or_U-PMA_with_staff_false") {
            Assertions.assertKeyValue("statusCode", "DLYLH-101", trackingShipmentDetails.data.shipmentData.get(0).shipment.status.statusCode);
        } else {
            Assertions.assertKeyValue("statusCode", "DTUP-205", trackingShipmentDetails.data.shipmentData.get(0).shipment.scans.get(4).scanDetail.statusCode);
            Assertions.assertKeyValue("statusCode", "DTUP-205", trackingShipmentDetails.data.shipmentData.get(0).shipment.scans.get(5).scanDetail.statusCode);
        }


    }


    @DataProvider(name = "TEST_FOR_SCAN_SS_CASES", parallel = false)
    public Object[][] test_for_scan_ss_cases() {
        return new Object[][]{
//	if nsl not present and is_staf = false, if scan.ss in ['Delivered', 'RTO', 'DTO', 'Collected', 'Closed', 'LOST'] 
                {"Scenario:: hit_service_with_four_wbns ", "5265161439783", "false", "true", "CL", "Client", "true", "2", "Delivered"},
                {"Scenario:: hit_service_with_four_wbns ", "5265161439783", "false", "true", "CL", "Client", "true", "2", "RTO"},
                {"Scenario:: hit_service_with_four_wbns ", "5265161439783", "false", "true", "CL", "Client", "true", "2", "DTO"},
                {"Scenario:: hit_service_with_four_wbns ", "5265161439783", "false", "true", "CL", "Client", "true", "2", "Collected"},
                {"Scenario:: hit_service_with_four_wbns ", "5265161439783", "false", "true", "CL", "Client", "true", "2", "Closed"},
                {"Scenario:: hit_service_with_four_wbns ", "5265161439783", "false", "true", "CL", "Client", "true", "2", "LOST"},
//	if nsl not present and is_staf = false, if scan.ss not in ['Delivered', 'RTO', 'DTO', 'Collected', 'Closed', 'LOST'] and is_staff is false
                {"Scenario:: hit_service_with_four_wbns ", "5265161439783", "false", "true", "CL", "Client", "true", "2", "PENDING"},
                {"Scenario:: hit_service_with_four_wbns ", "5265161439783", "false", "true", "CL", "Client", "true", "2", "DISPATCHED"},
                {"Scenario:: hit_service_with_four_wbns ", "5265161439783", "false", "true", "CL", "Client", "true", "2", "RETURNED"},
//if nsl not present and is_staf = true, if scan.ss not  in ['Delivered', 'RTO', 'DTO', 'Collected', 'Closed', 'LOST'] 
                {"Scenario:: hit_service_with_four_wbns ", "5265161439783", "true", "true", "CL", "Client", "true", "2", "PENDING"},
                {"Scenario:: hit_service_with_four_wbns ", "5265161439783", "true", "true", "CL", "Client", "true", "2", "DISPATCHED"},
                {"Scenario:: hit_service_with_four_wbns ", "5265161439783", "true", "true", "CL", "Client", "true", "2", "RETURNED"}

        };
    }

    //(dataProvider = "TEST_FOR_SCAN_SS_CASES", enabled = true)
    public void testForScanSsCases(String Scenario, String wbn, String is_staf, String is_agent, String user_type, String nsl_user_type, String fetch_pkg_info, String value, String state) {
        try {
            waybill = diffStShpt.DifferentStateShipments(state);

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        trackParameter.put("wbns", waybill);
        trackParameter.put("verbose", value);
        trackParameter.put("is_staf", is_staf);
        trackParameter.put("is_agent", is_agent);
        trackParameter.put("user_type", user_type);
        trackParameter.put("nsl_user_type", nsl_user_type);
        trackParameter.put("nsl_user_type", fetch_pkg_info);


        Example trackingShipmentDetails = apiCtrl.fetchPackageInfoTrackingEnv("Trackin", trackParameter);


    }


    @DataProvider(name = "TEST_FOR_MULTIPLE_WBNS", parallel = false)
    public Object[][] test_for_multiple_wbns() {
        return new Object[][]{
                {"Scenario:: hit_service_with_four_wbns ", "0", "5265161416893,21604011575,21604011590,21604011590"},
                {"Scenario:: hit_service_for_2_same_wbn", "0", "352028516352, 352028516352"},
                {"Scenario:: hit_service_for_one_correct_and_one_incorrect_wbn", "0", "5265161416893, 526516141689"},

                {"Scenario:: hit_service_with_four_wbns ", "1", "5265161416893,21604011575,21604011590,21604011590"},
                {"Scenario:: hit_service_for_2_same_wbn", "1", "352028516352, 352028516352"},
                {"Scenario:: hit_service_for_one_correct_and_one_incorrect_wbn", "1", "5265161416893, 526516141689"},

                {"Scenario:: hit_service_with_four_wbns ", "2", "5265161416893,21604011575,21604011590,21604011590"},
                {"Scenario:: hit_service_for_2_same_wbn", "2", "352028516352, 352028516352"},
                {"Scenario:: hit_service_for_one_correct_and_one_incorrect_wbn", "2", "5265161416893, 526516141689"}
        };
    }

    @Test(dataProvider = "TEST_FOR_MULTIPLE_WBNS", enabled = true)
    public void testForMultipleWbns(String Scenario, String value, String wbn) {


        hqParameter.put("waybill", wbn);
        hqParameter.put("verbose", value);
        trackParameter.put("wbns", wbn);
        trackParameter.put("verbose", value);


        Example trackingShipmentDetails = apiCtrl.fetchPackageInfoTrackingEnv("Trackin", trackParameter);


        HQTrack stagingMasterShipmentDetails = apiCtrl.fetchPackageInfoHqEnv("staging", hqParameter);


        try {
            logInfo("Waybill generated from Trackin env :: " + trackParameter.get("Wbns"));
            logInfo("Waybill generated from staging env :: " + hqParameter.get("waybill"));
            Assertions.comparePkgResponses(stagingMasterShipmentDetails, trackingShipmentDetails);
        } catch (JsonProcessingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }


    @DataProvider(name = "ASR_CASE", parallel = false)
    public Object[][] asr_case() {
        return new Object[][]{
                {"testScenario:: asr_case_when_asr_present_in_scan_and_nsl_EOD3_staff_false ", "21604011844", "false", "false", "CL", "Client", "true", "2"},
                {"Scenario:: asr_case_when_asr_present_in_scan_and_nsl_EOD3_staff_true ", "21604011844", "true", "false", "CL", "Client", "true", "2"},
                {"Scenario:: asr_case_when_asr_present_in_scan_and_nsl_not_in_EOD3_staff_false ", "21604011855", "false", "false", "CL", "Client", "true", "2"},
                {"Scenario:: asr_case_when_asr_present_in_scan_and_nsl_EOD3_staff_false_and_agent_true ", "21604011844", "false", "true", "CL", "Client", "true", "2"},
                {"Scenario:: asr_case_when_asr_present_in_scan_and_nsl_EOD3_staff_false_and_user_type=EXT", "21604011844", "false", "false", "EXT", "Client", "true", "2"},
                {"Scenario:: asr_case_when_asr_present_in_scan_and_nsl_EOD3_staff_false_and_user_type=LMAP", "21604011844", "false", "false", "LMAP", "Client", "true", "2"},

                //{ "testScenario:: asr_case_when_asr_present_in_scan_and_nsl_EOD29_staff_false ", " ","false","false","CL", "Client","true","2"},
                //{ "Scenario:: asr_case_when_asr_present_in_scan_and_nsl_EOD29_staff_true ", " ","true","false","CL", "Client","true","2"},
                //{ "Scenario:: asr_case_when_asr_present_in_scan_and_nsl_EOD29_staff_false_and_agent_true ", " ","false","true","CL", "Client","true","2"},
                //{ "Scenario:: asr_case_when_asr_present_in_scan_and_nsl_EOD29_staff_false_and_user_type=EXT", " ","false","false","EXT", "Client","true","2"},
                //{ "Scenario:: asr_case_when_asr_present_in_scan_and_nsl_EOD29_staff_false_and_user_type=LMAP", " ","false","false","LMAP", "Client","true","2"}


        };
    }

    @Test(dataProvider = "ASR_CASE", enabled = true)
    public void ASRCase(String Scenario, String wbn, String is_staf, String is_agent, String user_type, String nsl_user_type, String fetch_pkg_info, String value) {
//https://express-tracking-service.pntrzz.com/v1/package/track?wbns=5265161439945&is_staff=true&is_agent=true&user_type=CL&nsl_user_type=Internal&fetch_pkg_info=true&verbose=2
        trackParameter.put("wbns", wbn);
        trackParameter.put("verbose", value);
        trackParameter.put("is_staf", is_staf);
        trackParameter.put("is_agent", is_agent);
        trackParameter.put("user_type", user_type);
        trackParameter.put("nsl_user_type", nsl_user_type);
        trackParameter.put("nsl_user_type", fetch_pkg_info);


        Example trackingShipmentDetails = apiCtrl.fetchPackageInfoTrackingEnv("Trackin", trackParameter);

        if (Scenario == "testScenario:: asr_case_when_asr_present_in_scan_and_nsl_EOD3_staff_false ") {
            Assertions.assertKeyValue("Instructions", "Delivery date rescheduled test 1", trackingShipmentDetails.data.shipmentData.get(0).shipment.status.instructions);
        } else if (Scenario == "testScenario:: asr_case_when_asr_present_in_scan_and_nsl_EOD29_staff_false ") {    //Assertions.assertKeyValue("Instructions", "Shipment details manifested", trackingShipmentDetails.data.shipmentData.get(0).shipment.scans.get(0).scanDetail.instructions);
            //	nsl not active
        } else {
            Assertions.assertKeyValue("Instructions", "Delivery date rescheduled", trackingShipmentDetails.data.shipmentData.get(0).shipment.status.instructions);
        }


    }


    @DataProvider(name = "INSTRUCTION_CHANGE_CASE", parallel = false)
    public Object[][] instruction_change_case() {
        return new Object[][]{
                {"Scenario:: instruction_change_case_Client ", "5265161440870", "false", "true", "CL", "Client", "true", "2"},
                {"Scenario:: instruction_change_case_Internal ", "5265161440870", "false", "true", "CL", "Internal", "true", "2"},
                {"Scenario:: instruction_change_case ", "5265161440870", "false", "true", "CL", "", "true", "2"}

        };
    }

    @Test(dataProvider = "INSTRUCTION_CHANGE_CASE", enabled = true)
    public void instructionChangeCase(String Scenario, String wbn, String is_staf, String is_agent, String user_type, String nsl_user_type, String fetch_pkg_info, String value) {
//https://express-tracking-service.pntrzz.com/v1/package/track?wbns=5265161439945&is_staff=true&is_agent=true&user_type=CL&nsl_user_type=Internal&fetch_pkg_info=true&verbose=2
        trackParameter.put("wbns", wbn);
        trackParameter.put("verbose", value);
        trackParameter.put("is_staf", is_staf);
        trackParameter.put("is_agent", is_agent);
        trackParameter.put("user_type", user_type);
        trackParameter.put("nsl_user_type", nsl_user_type);
        trackParameter.put("nsl_user_type", fetch_pkg_info);


        Example trackingShipmentDetails = apiCtrl.fetchPackageInfoTrackingEnv("Trackin", trackParameter);

        if (Scenario == "Scenario:: instruction_change_case_Client ") {
            Assertions.assertKeyValue("Instructions", "Consignment Manifested", trackingShipmentDetails.data.shipmentData.get(0).shipment.scans.get(0).scanDetail.instructions);
        } else if (Scenario == "Scenario:: instruction_change_case_Internal ") {
            Assertions.assertKeyValue("Instructions", "Shipment details manifested", trackingShipmentDetails.data.shipmentData.get(0).shipment.scans.get(0).scanDetail.instructions);
        } else {
            Assertions.assertKeyValue("Instructions", "Shipment details manifested", trackingShipmentDetails.data.shipmentData.get(0).shipment.scans.get(0).scanDetail.instructions);
        }


    }


    @DataProvider(name = "PACKAGE_KEYS_COMPARISON", parallel = false)
    public Object[][] package_keys_comparison() {
        return new Object[][]{
                {"Scenario:: B2C package ", "B2C"},
                {"Scenario:: B2B package ", "B2B"},
                {"Scenario:: HEAVY package ", "HEAVY"},
                {"Scenario:: B2C MPS package ", "B2C MPS"}

        };
    }

    @Test(dataProvider = "PACKAGE_KEYS_COMPARISON", enabled = true)
    public void PkgKeysComparison(String Scenario, String type) {


        List<String> waybills = new ArrayList<String>();
        HashMap<String, String> requestPayload = new HashMap();


        requestPayload.put("enviorment", "staging");
        System.out.println(requestPayload);


        /*
         * requestPayload.put("client","HQAPIREGRESSION SRV");
         * requestPayload.put("international", "true"); if (Scenario.contains("MPS")) {
         * requestPayload.put("master_id", Utilities.getUniqueInt(15)); } if
         * (!Scenario.contains("ONLY MASTER")) { requestPayload.put("waybill",
         * Utilities.getUniqueInt(15)); } requestPayload.put("pin", "122001");
         */


        waybills = difftypeShpt.DifferentTypeShipments(type, requestPayload);


        waybill = waybills.get(0);
        logInfo("Waybill to be verified :: " + waybill);


        Utilities.hardWait(10);

        //call assertion function here

        clData.put("wbns", waybills.get(0));

        Example trackingShipmentDetails = apiCtrl.fetchPackageInfoTrackingEnv("Trackin", clData);


        clData2.put("waybill", waybills.get(0));
        HQTrack stagingMasterShipmentDetails = apiCtrl.fetchPackageInfoHqEnv("staging", clData2);


        try {

            Assertions.comparePkgResponses(stagingMasterShipmentDetails, trackingShipmentDetails);
        } catch (JsonProcessingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        if (Scenario.contains("MPS")) {
            clData.put("wbns", waybills.get(1));

            Example trackingChildShipmentDetails = apiCtrl.fetchPackageInfoTrackingEnv("Trackin", clData);


            clData2.put("waybill", waybills.get(1));
            HQTrack stagingChildShipmentDetails = apiCtrl.fetchPackageInfoHqEnv("staging", clData2);

            try {
                Assertions.comparePkgResponses(stagingChildShipmentDetails, trackingChildShipmentDetails);
            } catch (JsonProcessingException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

}

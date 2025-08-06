package com.delhivery.Express.testModules.FMOMS;
import com.aventstack.extentreports.service.ExtentTestManager;
import com.delhivery.Express.controllers.api.ApiController;
import com.delhivery.Express.controllers.api.DifferentStateShipments;
import com.delhivery.Express.pojo.FetchPackageDetails.response.PackageDetail;
import com.delhivery.Express.pojo.FetchPackageDetailsSecond.response.PackageDetail2;
//import com.delhivery.Express.pojo.GetPackageDetail.response.GetPackageDetailResponse;
import com.delhivery.Express.pojo.PackageDetailFetchRestInfoApi.response.PackageDetails;
import com.delhivery.Express.pojo.PackageDetailFetchRestInfoApi.response.S;
import com.delhivery.Express.pojo.PackingSlip.Response.PackingSlipResponsePayload;
import com.delhivery.core.BaseTest;
import com.delhivery.core.db.DataProviderClass;
import com.delhivery.core.utils.Utilities;
import com.delhivery.core.utils.YamlReader;
import com.fasterxml.jackson.core.JsonProcessingException;

import org.apache.commons.lang3.StringUtils;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;
import com.delhivery.core.utils.Assertions;
import com.delhivery.core.utils.ServiceabilityKeysAssertions;

import static com.delhivery.core.utils.Assertions.assertKeyValue;
import static com.delhivery.core.utils.ServiceabilityKeysAssertions.assertNodeValue;
import static com.delhivery.core.utils.Utilities.logInfo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

public class p_update extends BaseTest {
    private String waybill, bagId, tripId, dispatchId;
    public String scenario;
    ApiController apiCtrl = new ApiController();
    public LinkedHashMap<String, String> Expected_values;
    public HashMap<String, String> clData = new HashMap<>();
    HashMap<String,String> paramsMap = new HashMap<>();
    public String api_p_info = "api/p/info";
    public String api_rest_info = "api/rest/info";
    DifferentStateShipments diffStShpt = new DifferentStateShipments();

    public p_update() {
        DataProviderClass.fileName = "CmuRegressionData";
        DataProviderClass.sheetName = "Pkg_flows";

    }
   

    public void FillExpectedValues(String ocid, String fpin ,String fcnt_cd , String occ , String oc ) {
        Expected_values = new LinkedHashMap<String, String>();
        Expected_values.put("ocid", ocid);
        Expected_values.put("fpin",fpin);
        Expected_values.put("fcnt_cd",fcnt_cd);
        Expected_values.put("occ",occ);
        Expected_values.put("oc",oc);
    }

    public void FillPayload(String add , String radd , String sc_add , String repl_add, String badd , String sadd , String fm_add ) {
        clData.put("add", add);
        clData.put("city", "Gurgaon");
        clData.put("state", "Haryana");
        clData.put("pin", "122001");
        clData.put("return_add", radd);
        clData.put("return_city", "Gurgaon");
        clData.put("return_state", "Haryana");
        clData.put("return_pin", "122003");
        clData.put("secondary_add", sc_add);
        clData.put("secondary_city", "Delhi");
        clData.put("secondary_state", "Delhi");
        clData.put("secondary_pin", "110011");
        clData.put("buyback_add", repl_add);
        clData.put("buyback_city", "Noida");
        clData.put("buyback_state", "Uttar Pradesh");
        clData.put("buyback_pin", "301201");
        clData.put("billing_add", badd);
        clData.put("billing_city", "Meerut");
        clData.put("billing_state", "Uttar Pradesh");
        clData.put("billing_pin", "250002");
        clData.put("seller_add", sadd);
        clData.put("seller_city", "Chandigarh");
        clData.put("seller_state", "Haryana");
        clData.put("seller_pin", "160017");
        clData.put("fm_add", fm_add);
        clData.put("fm_city", "Delhi");
        clData.put("fm_state", "Delhi");
        clData.put("fm_pin", "110085");
    }

    public void FillPayload(String add , String radd) {
        clData.put("add", add);
        clData.put("city", "Gurgaon");
        clData.put("state", "Haryana");
        clData.put("pin", "122001");
        clData.put("return_add", radd);
        clData.put("return_city", "Gurgaon");
        clData.put("return_state", "Haryana");
        clData.put("return_pin", "122003");
    }


    @DataProvider(name = "Different_type_of_packages", parallel = true)
    public Object[][] Different_type_of_packages() {
        return new Object[][] {
                { "Scenario:: B2C package with all the add related fields", "B2C" ,"Gurgaon (Haryana)" , "IND474001AAA" , "474020" , "IN", "Gwalior", "Gwalior_VstraNgr_P (Madhya Pradesh)"},
                { "Scenario:: B2B package with all the add related fields", "B2B" ,"Gurgaon (Haryana)" , "IND474001AAA" , "474020" , "IN", "Gwalior", "Gwalior_VstraNgr_P (Madhya Pradesh)"},
                { "Scenario:: Heavy package with all the add related fields", "Heavy" ,"Gurgaon (Haryana)" , "IND474001AAA" , "474020" , "IN", "Gwalior", "Gwalior_VstraNgr_P (Madhya Pradesh)"},
                { "Scenario:: B2C MPS package with all the add related fields", "B2C MPS" ,"Gurgaon (Haryana)" , "IND474001AAA" , "474020" , "IN", "Gwalior", "Gwalior_VstraNgr_P (Madhya Pradesh)"},
                { "Scenario:: B2B MPS package with all the add related fields", "B2B MPS" ,"GGN_DPC (Haryana)" , "IND474001AAA" , "474020" , "IN", "Gwalior", "Gwalior_VstraNgr_P (Madhya Pradesh)"},
                { "Scenario:: Heavy MPS package with all the add related fields", "Heavy MPS" ,"GGN_DPC (Haryana)" , "IND474001AAA" , "474020" , "IN", "Gwalior", "Gwalior_VstraNgr_P (Madhya Pradesh)"},
                { "Scenario:: B2B MPS package with mcount 1", "MPS WITH MCOUNT 1" ,"GGN_DPC (Haryana)" , "IND474001AAA" , "474020" , "IN", "Gwalior", "Gwalior_VstraNgr_P (Madhya Pradesh)"},
                { "Scenario:: B2C MPS package with mcount 1", "B2C MPS WITH MCOUNT 1" ,"Gurgaon (Haryana)" , "IND474001AAA" , "474020" , "IN", "Gwalior", "Gwalior_VstraNgr_P (Madhya Pradesh)"}

        };

    }

    @Test(dataProvider = "Different_type_of_packages" , enabled = true)
    public void Packages_Manifested_with_PL(String Scenario, String type, String cn , String ocid , String fpin , String fcnt_cd, String occ, String oc) {
        List<String> waybills = new ArrayList<String>();
        HashMap<String, String> Payload = new HashMap<>();
        clData.put("client", "regression_client");
        clData.put("pickup_location","HQAPIREGRESSION");
        clData.put("cwh","TestFaasToES");
        clData.put("cwh_sent","true");
        clData.put("cod_amount", "100");
        //pickup_location,cwh -> use this key for pickup location
        //cwh_sent -> true
        clData.put("client", "regression_client");
        FillPayload("add", "radd", "sc_add", "repl_add", "badd", "sadd", "fm_add");
        waybills = diffTypeShipment.DifferentTypeShipments(type, clData);
        waybill = waybills.get(0);
        logInfo("Waybill generated " + waybill);

        //Getting child waybill
        String child_waybill = waybill;
        if (waybills.size() > 1 && (Scenario.contains("MPS") || Scenario.contains("NO DATA") || Scenario.contains("PARTIALLY MANIFESTED"))) {
            child_waybill = waybills.get(1);
            logInfo("Child Waybill generated " + child_waybill);
        }

        
        Utilities.hardWait(60);
        PackageDetail pkgDetails = apiCtrl.fetchPackageInfo(waybill, clData);
        logInfo("ocid generated " + pkgDetails.ocid);

        Utilities.hardWait(10);
        HashMap<String,String> pUpdateData = new HashMap<>();
        pUpdateData.put("source","LTL-Selfdrop");
        pUpdateData.put("locationId","IND474001AAA"); //132103
        pUpdateData.put("waybill",waybill);
        pUpdateData.put("act","EDIT_DETAILS");
//        clData.put("cwh_uuid","delhivery::clientwarehouse::6ee4d010-20d7-4a7b-8c91-5497712683d9"); //474009
        apiCtrl.PackageUpdate(pUpdateData);
        if(!child_waybill.equalsIgnoreCase(waybill)) {
            Utilities.hardWait(2);
            pUpdateData.put("waybill",child_waybill);
            apiCtrl.PackageUpdate(pUpdateData);
        }

        Utilities.hardWait(10);
        
        
        
      //Fetch Pkg Info
        PackageDetail pkgs = apiCtrl.fetchPackageInfo(waybill, clData);
            assertKeyValue("ocid", ocid, pkgs.ocid);
            assertKeyValue("fpin", fpin, pkgs.fpin.toString());
            assertKeyValue("fcnt_cd", fcnt_cd, pkgs.fcntCd);
            assertKeyValue("occ", occ, pkgs.occ);
            assertKeyValue("oc", oc, pkgs.oc);
            
   
    }
    
    @DataProvider(name = "Different_type_of_packages_without_src", parallel = false)
    public Object[][] Different_type_of_packages_without_src() {
        return new Object[][] {
                { "Scenario:: B2C package with all the add related fields", "B2C" ,"Gurgaon (Haryana)" , "IND122001AAB" , "122002" , "IN", "Gurgaon", "Gurgaon (Haryana)"},
                { "Scenario:: B2B package with all the add related fields", "B2B" ,"GGN_DPC (Haryana)" , "IND122001AAB" , "122002" , "IN", "Gurgaon", "Gurgaon (Haryana)"},
                { "Scenario:: Heavy package with all the add related fields", "Heavy" ,"GGN_DPC (Haryana)" , "IND122001AAB" , "122002" , "IN", "Gurgaon", "Gurgaon (Haryana)"},
                { "Scenario:: B2C MPS package with all the add related fields", "B2C MPS" ,"Gurgaon (Haryana)" , "IND122001AAB" , "122002" , "IN", "Gurgaon", "Gurgaon (Haryana)"},
                { "Scenario:: B2B MPS package with all the add related fields", "B2B MPS" ,"GGN_DPC (Haryana)" , "IND122001AAB" , "122002" , "IN", "Gurgaon", "Gurgaon (Haryana)"},
                { "Scenario:: Heavy MPS package with all the add related fields", "Heavy MPS" ,"GGN_DPC (Haryana)" , "IND122001AAB" , "122002" , "IN", "Gurgaon", "Gurgaon (Haryana)"},
                { "Scenario:: B2B MPS package with mcount 1", "MPS WITH MCOUNT 1" ,"GGN_DPC (Haryana)" , "IND474001AAA" , "474009" , "IN", "Gwalior", "Gwalior_VstraNgr_P (Madhya Pradesh)"},
                { "Scenario:: B2C MPS package with mcount 1", "B2C MPS WITH MCOUNT 1" ,"Gurgaon (Haryana)" , "IND474001AAA" , "474009" , "IN", "Gwalior", "Gwalior_VstraNgr_P (Madhya Pradesh)"}

        };

    }

    @Test(dataProvider = "Different_type_of_packages_without_src" , enabled = true)
    public void Packages_Manifested_with_PL_without_src(String Scenario, String type, String cn , String ocid , String fpin , String fcnt_cd, String occ, String oc) {
        List<String> waybills = new ArrayList<String>();
        HashMap<String, String> Payload = new HashMap<>();
        clData.put("client", "regression_client");
        clData.put("pickup_location","HQAPIREGRESSION");
        clData.put("cwh","TestFaasToES");
        clData.put("cwh_sent","true");
        //pickup_location,cwh -> use this key for pickup location
        //cwh_sent -> true
        clData.put("client", "regression_client");
        FillPayload("add", "radd", "sc_add", "repl_add", "badd", "sadd", "fm_add");
        waybills = diffTypeShipment.DifferentTypeShipments(type, clData);
        waybill = waybills.get(0);
        logInfo("Waybill generated " + waybill);
        

        //Getting child waybill
        String child_waybill = waybill;
        if (waybills.size() > 1 && (Scenario.contains("MPS") || Scenario.contains("NO DATA") || Scenario.contains("PARTIALLY MANIFESTED"))) {
            child_waybill = waybills.get(1);
            logInfo("Child Waybill generated " + child_waybill);
        }

        if (Scenario.contains("B2B") || Scenario.contains("Heavy") || Scenario.contains("No data")) {
            Utilities.hardWait(40);
        }
        
        PackageDetail pkgDetails = apiCtrl.fetchPackageInfo(waybill, clData);
        logInfo("ocid generated " + pkgDetails.ocid);

        Utilities.hardWait(10);
        HashMap<String,String> pUpdateData = new HashMap<>();
        //pUpdateData.put("source","LTL-Selfdrop");
        pUpdateData.put("locationId","IND400093AAA"); //132103
        pUpdateData.put("waybill",waybill);
        pUpdateData.put("act","EDIT_DETAILS");
//        clData.put("cwh_uuid","delhivery::clientwarehouse::6ee4d010-20d7-4a7b-8c91-5497712683d9"); //474009
        apiCtrl.PackageUpdate(pUpdateData);
        if(!child_waybill.equalsIgnoreCase(waybill)) {
            Utilities.hardWait(2);
            pUpdateData.put("waybill",child_waybill);
            apiCtrl.PackageUpdate(pUpdateData);
        }

        Utilities.hardWait(10);
      //Fetch Pkg Info
        PackageDetail pkgs = apiCtrl.fetchPackageInfo(waybill, clData);
            assertKeyValue("ocid", ocid, pkgs.ocid);
            assertKeyValue("fpin", fpin, pkgs.fpin.toString());
            assertKeyValue("fcnt_cd", fcnt_cd, pkgs.fcntCd);
            assertKeyValue("occ", occ, pkgs.occ);
            assertKeyValue("oc", oc, pkgs.oc);
            
    }


    
    @DataProvider(name = "Different_state_type", parallel = false)
    public static Object[][] Different_state_type() {
        return new Object[][]{
        	{ "Scenario:: MANIFEST package with all the add related fields", "MANIFEST" ,"Gurgaon (Haryana)" , "IND122001AAB" , "122002" , "IN", "Gurgaon", "Gurgaon (Haryana)"},
            { "Scenario:: IN TRANSIT package with all the add related fields", "IN TRANSIT" ,"GGN_DPC (Haryana)" , "IND110092AAB" , "122002" , "IN", "Delhi", "East Delhi (Delhi)"},
            { "Scenario:: PENDING package with all the add related fields", "PENDING" ,"GGN_DPC (Haryana)" , "IND400093AAA" , "122002" , "IN", "Mumbai", "Mumbai MIDC (Maharashtra)"},
            { "Scenario:: DELIVERED package with all the add related fields", "DELIVERED" ,"Gurgaon (Haryana)" , "IND400093AAA" , "122002" , "IN", "Mumbai", "Mumbai MIDC (Maharashtra)"},
            { "Scenario:: RETURNED package with all the add related fields", "RETURNED" ,"GGN_DPC (Haryana)" , "IND110092AAB" , "122002" , "IN", "Delhi", "East Delhi (Delhi)"},
            { "Scenario:: PICKUPPENDING package with all the add related fields", "PICKUPPENDING" ,"GGN_DPC (Haryana)" , "IND122001AAB" , "122002" , "IN", "Gurgaon", "Gurgaon (Haryana)"},
            { "Scenario:: PICKEDUP package with internal child", "PICKEDUP" ,"GGN_DPC (Haryana)" , "IND122001AAB" , "122002" , "IN", "Gurgaon", "Gurgaon (Haryana)"},
            { "Scenario:: CANCELLED MPS package with mcount 1", "CANCELLED" ,"GGN_DPC (Haryana)" , "IND122001AAB" , "122002" , "IN", "Gurgaon", "Gurgaon (Haryana)"},
           

        };
    }

    @Test(dataProvider = "Different_state_type" , enabled = true)
    public void Packages_Manifested_with_PL_different_states(String Scenario, String state, String cn , String ocid , String fpin , String fcnt_cd, String occ, String oc) {
        List<String> waybills = new ArrayList<String>();
        HashMap<String, String> Payload = new HashMap<>();
        clData.put("client", "regression_client");
        clData.put("pickup_location","HQAPIREGRESSION");
        clData.put("cwh","TestFaasToES");
        clData.put("cwh_sent","true");
        clData.put("cod_amount", "100");
        //pickup_location,cwh -> use this key for pickup location
        //cwh_sent -> true
      //  clData.put("client", "regression_client");
        //waybills = diffStShpt.DifferentStateShipments(type, clData);
        String waybill = diffStShpt.DifferentStateShipments(state, clData);
        logInfo("Waybill generated " + waybill);

        Utilities.hardWait(10);
        HashMap<String,String> pUpdateData = new HashMap<>();
        pUpdateData.put("source","LTL-Selfdrop");
        pUpdateData.put("locationId","IND122001AAB"); //132103
        pUpdateData.put("waybill",waybill);
        pUpdateData.put("act","EDIT_DETAILS");
//        clData.put("cwh_uuid","delhivery::clientwarehouse::6ee4d010-20d7-4a7b-8c91-5497712683d9"); //474009
        apiCtrl.PackageUpdate(pUpdateData);

        Utilities.hardWait(10);
      //Fetch Pkg Info
        PackageDetail pkgs = apiCtrl.fetchPackageInfo(waybill, clData);
            assertKeyValue("ocid", ocid, pkgs.ocid);
            assertKeyValue("fpin", fpin, pkgs.fpin.toString());
            assertKeyValue("fcnt_cd", fcnt_cd, pkgs.fcntCd);
            assertKeyValue("occ", occ, pkgs.occ);
            assertKeyValue("oc", oc, pkgs.oc);
    }

}
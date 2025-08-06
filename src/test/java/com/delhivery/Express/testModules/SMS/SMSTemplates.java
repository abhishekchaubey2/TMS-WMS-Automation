package com.delhivery.Express.testModules.SMS;

import com.delhivery.Express.controllers.api.ApiController;
import com.delhivery.Express.controllers.api.DifferentStateShipments;
import com.delhivery.Express.dataprovider.manifestationData;
import com.delhivery.Express.pojo.FetchPackageDetails.response.PackageDetail;
import com.delhivery.Express.pojo.SMS.SmsResponse;
import com.delhivery.core.BaseTest;
import com.delhivery.core.utils.Assertions;
import com.delhivery.core.utils.Utilities;
import com.delhivery.core.utils.YamlReader;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.delhivery.core.utils.Assertions.assertKeyValue;
import static com.delhivery.core.utils.Utilities.logInfo;

public class SMSTemplates extends BaseTest {

    private String dispatchId;
    private ArrayList<String> waybills, bagIds, tripIds;
    DifferentStateShipments diffStShpt = new DifferentStateShipments();
    public HashMap<String,String> reqData;

    private String payment_mode, packageStatus, bagSeal, ocid, cnid, client, pin, package_count, UPL;
    public String scenario;
    public HashMap<String, String> clData = new HashMap<>();
    public HashMap<String,String> manifestData = new HashMap<String,String>();
    public Map<String, Object> pkgFlowData;
    public Map<String, Object> bagFlowData;
    String phone = YamlReader.getYamlValues("TestData.ManifestData").get("SMS_Phone_Number").toString();
    ApiController apiCtrl = new ApiController();
    private String product_type;

    @Test(dataProvider = "SMS_For_different_Pkg_type", dataProviderClass = manifestationData.class, enabled = true)
    public void SMS_Cases_For_Regression_Client(String scenario, String product_type, String payment_mode) throws InterruptedException {
        clData.put("client", "ManifestClient");
        HashMap<String,String> data = new HashMap<String,String>();
        data.put("product_type",product_type);
        data.put("payment_mode", payment_mode);
        data.put("phone", phone);
        data.put("is_otp_verified", "true");
        data.put("client", "ManifestClient");
        if( payment_mode=="cod"){
            //following values are required to trigger SMS for cod type shipments, hence should be hardcoded
            data.put("cod_amount", "1500");
            data.put("total_amount","5000");
        }else if( payment_mode=="pickup"){
            //following values are required to trigger SMS for cod type shipments, hence should be hardcoded
            data.put("quality_check", "true");
        }


        waybills = apiCtrl.cmuManifestApi(data);
        String waybill = waybills.get(0);
//		waybill = apiCtrl.cmuManifestApi(data);
        logInfo("Waybill generated " + waybill);
        Utilities.hardWait(60);

        //Fetch Pkg Info
        PackageDetail pkgs = apiCtrl.fetchPackageInfo(waybill, clData);
        Assertions.assertKeyValue("ucid.is_valid", true, pkgs.ucid.isValid);
        Assertions.assertKeyValue("flags.otp_secr", true, pkgs.flags.otpSecr);




        if(scenario.contains("Prepaid")  || scenario.contains("COD")) {
            //FM OMS Pick
            apiCtrl.fmOMSApi(waybill, "FMPICK", clData);
            pkgFlowData = YamlReader.getYamlValues("packageFlowScans.fmPick");
            apiCtrl.verifyPackageFetchInfoApi(waybill, pkgFlowData.get("statusType").toString(), pkgFlowData.get("scanStatus").toString(), pkgFlowData.get("scanNsl").toString(), clData);

            Utilities.hardWait(2);
            //FM OMS Depart
            apiCtrl.fmOMSApi(waybill, "FMDEPART", clData);
            pkgFlowData = YamlReader.getYamlValues("packageFlowScans.fmDepart");
            apiCtrl.verifyPackageFetchInfoApi(waybill, pkgFlowData.get("statusType").toString(), pkgFlowData.get("scanStatus").toString(), pkgFlowData.get("scanNsl").toString(), clData);

            Utilities.hardWait(2);
            //Gi of pkgs
            String cn_loc = apiCtrl.fetchPackageInfo(waybill, clData).cn;
            apiCtrl.giApi(waybill, cn_loc, clData);
        }
        Utilities.hardWait(10);


        //pickup schedule
        if(scenario.contains("QC") || scenario.contains("pickup")) {
            apiCtrl.ApplyNsl(waybills, "PP", "X-ASP",data);
        }
        Utilities.hardWait(2);

        //Mark out for dispatch
        if(scenario.contains("QC")){
            dispatchId = apiCtrl.markRerveseShipmentDispatchApi(waybill,clData);
            logInfo("Dispatch created " + dispatchId);
        }else{
            dispatchId = apiCtrl.markShipmentDispatchApi(waybill,clData);
            logInfo("Dispatch created " + dispatchId);

        }
        
        //pickup successful
        if(scenario.contains("QC") || scenario.contains("pickup")) {
        	 apiCtrl.lmUpdateHQShipmentApi(waybill, "PickedUp",data);
        }
        

        Utilities.hardWait(20);
        //Verify SMS Received on phone number
        SmsResponse apiResponse= apiCtrl.verifySMS(phone);
        assertKeyValue("success", true, apiResponse.success);
        String message = apiResponse.message.get(0).message.toString();

        String text = YamlReader.getYamlValues("SmsTemplates.Out_For_Pickup").get("message").toString();
        text = text.replace("wbn", waybill);


        if(scenario.contains("Prepaid")|| scenario.contains("COD")){
            //Asserting SMS
        	String text2 = YamlReader.getYamlValues("SmsTemplates.Out_For_Delivery").get("message").toString();
            text = text2.replace("wbn", waybill);
            assertKeyValue("message", text2, apiResponse.message.get(0).message.toString() );

        }


    }

    @Test(dataProvider = "SMS_For_different_Pkg_type", dataProviderClass = manifestationData.class, enabled = true)
    public void SMS_Cases_for_Bank_Client(String scenario, String product_type, String payment_mode) throws InterruptedException {
        clData.put("client", "BankClient");
        HashMap<String,String> data = new HashMap<String,String>();
        data.put("client", "BankClient");
        data.put("product_type",product_type);
        data.put("payment_mode", payment_mode);
        data.put("phone", phone);
        if( payment_mode=="cod"){
            //following values are required to trigger SMS for cod type shipments, hence should be hardcoded
            data.put("cod_amount", "1500");
            data.put("total_amount","5000");
        }else if( payment_mode=="pickup"){
            //following values are required to trigger SMS for cod type shipments, hence should be hardcoded
            data.put("quality_check", "true");
        }


        waybills = apiCtrl.cmuManifestApi(data);
        String waybill = waybills.get(0);
//		waybill = apiCtrl.cmuManifestApi(data);
        logInfo("Waybill generated " + waybill);
        Utilities.hardWait(30);

        //Fetch Pkg Info
        PackageDetail pkgs = apiCtrl.fetchPackageInfo(waybill, clData);
        Assertions.assertKeyValue("ucid.is_valid", true, pkgs.ucid.isValid);



        if(scenario.contains("Prepaid") || scenario.contains("COD") ) {
            //FM OMS Pick
            apiCtrl.fmOMSApi(waybill, "FMPICK", clData);
            pkgFlowData = YamlReader.getYamlValues("packageFlowScans.fmPick");
            apiCtrl.verifyPackageFetchInfoApi(waybill, pkgFlowData.get("statusType").toString(), pkgFlowData.get("scanStatus").toString(), pkgFlowData.get("scanNsl").toString(), clData);

            //FM OMS Depart
            apiCtrl.fmOMSApi(waybill, "FMDEPART", clData);
            pkgFlowData = YamlReader.getYamlValues("packageFlowScans.fmDepart");
            apiCtrl.verifyPackageFetchInfoApi(waybill, pkgFlowData.get("statusType").toString(), pkgFlowData.get("scanStatus").toString(), pkgFlowData.get("scanNsl").toString(), clData);
            Utilities.hardWait(2);

            //GI of pkgs
            String cn_loc = apiCtrl.fetchPackageInfo(waybill, clData).cn;
            apiCtrl.giApi(waybill, cn_loc, clData);
        }

        //pickup schedule
        if(scenario.contains("QC") || scenario.contains("pickup")) {
            apiCtrl.ApplyNsl(waybills, "PP", "X-ASP",data);
        }
        Utilities.hardWait(2);

        //Mark out for dispatch
        if(scenario.contains("QC")){
            dispatchId = apiCtrl.markRerveseShipmentDispatchApi(waybill,clData);
            logInfo("Dispatch created " + dispatchId);
        }else{
            dispatchId = apiCtrl.markShipmentDispatchApi(waybill,clData);
            logInfo("Dispatch created " + dispatchId);

        }

        Utilities.hardWait(10);
        //Verify SMS Received on phone number
        SmsResponse apiResponse= apiCtrl.verifySMS(phone);
        assertKeyValue("success", true, apiResponse.success);
        String message = apiResponse.message.get(0).message.toString();

        //String value
        String text =  apiResponse.message.get(0).message.toString();
        // Extracting "YCY1SXzE3nc8iaPP"
        Pattern referencePattern = Pattern.compile("Reference No (\\w+)");
        Matcher referenceMatcher = referencePattern.matcher(text);
        String referenceNo = "";
        if (referenceMatcher.find()) {
            referenceNo = referenceMatcher.group(1);
            text = text.replace(referenceNo, "number");
        }

        // Extracting "2838"
        Pattern authCodePattern = Pattern.compile("Delivery Authentication Code (\\d+)");
        Matcher authCodeMatcher = authCodePattern.matcher(text);
        String authCode = "";
        if (authCodeMatcher.find()) {
            authCode = authCodeMatcher.group(1);
            text = text.replace(authCode, "otp");
        }

        System.out.println("Modified Text: " + text);

        //Asserting SMS
        assertKeyValue("message", YamlReader.getYamlValues("SmsTemplates.Bank_Dispatch_SMS").get("message").toString(), text );
    }
}

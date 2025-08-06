package com.delhivery.Express.testModules.RegressionScripts;

import com.delhivery.Express.controllers.api.ApiController;
import com.delhivery.core.BaseTest;
import com.delhivery.core.utils.CsvFileUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;


public class CMS_TestScripts extends BaseTest {
    private String client, jwtToken, b2cClaimListingStartDate, b2cClaimListingEndDate,
            b2bClaimListingStartDate, b2bClaimListingEndDate, waybill, clientName,
            disputeType, claimAmount, clientRemarks, imageLink, ClaimId, claimIdDisputeType, status,
            fileName, filePath, createdAt, b2bViewClaimId, pdt,
            settlementAmount = null;

    ApiController apiCtrl = new ApiController();
    HashMap<String, String> requestTokenData, requestData, paramObjectData, apiData = null;

    //add a constructor
    public CMS_TestScripts() {
        client = "internal";
        b2cClaimListingStartDate = "2023-12-07";
        b2cClaimListingEndDate = "2023-12-08";
        b2bClaimListingStartDate = "2023-12-06";
        b2bClaimListingEndDate = "2023-12-07";
//        waybill = "5265168066856";
        waybill = "882712496067";
        clientName = "DLVCLAIMTESTINGClone002";
        disputeType = "Content Short";
        claimAmount = "100";
        clientRemarks = "Lost package";
        imageLink = "test.png";
        ClaimId = "398";
        claimIdDisputeType = "Damaged";
        status = "Claim Accepted";
        fileName = "ClaimUpload.csv";
        filePath = System.getProperty("user.dir") + "/src/test/resources/testData/";
        createdAt = "2023-10-01";
        b2bViewClaimId = "91";
        pdt = "B2C";
        settlementAmount = "100";

    }

    //create test method which fetch qahq user token and can be used in other test cases
    //other test cases will depends upon toke fetch test case
    @Test
    public void fetchUserTokenForClaimsApis() {
        requestTokenData = new HashMap<>();
        requestTokenData.put("client", client);
        jwtToken = apiCtrl.fetchUserJwtTokenApi(requestTokenData);
        requestData = new HashMap<>();
        requestData.put("domain", "CMS");
        requestData.put("auth", jwtToken);
    }

    //write test case to fetch jwt token and then hit the Claims b2c listing api
    @Test(dependsOnMethods = "fetchUserTokenForClaimsApis")
    public void fetchB2cListing() throws JsonProcessingException {
        paramObjectData = new HashMap<>();
        //set the start date and end date of param object Data hashmap from global vairable
        paramObjectData.put("fromDate", b2cClaimListingStartDate);
        paramObjectData.put("toDate", b2cClaimListingEndDate);

        apiCtrl.verifyClaimsB2cListingApi(paramObjectData, requestData);

    }

    //write test case to fetch jwt token and then hit the Claims b2b listing api
    @Test(dependsOnMethods = "fetchUserTokenForClaimsApis")
    public void fetchB2bListing() throws JsonProcessingException {
        paramObjectData = new HashMap<>();
        //set the start date and end date of param object Data hashmap from global vairable
        paramObjectData.put("fromDate", b2bClaimListingStartDate);
        paramObjectData.put("toDate", b2bClaimListingEndDate);

        apiCtrl.verifyClaimsB2bListingApi(paramObjectData, requestData);

    }

    //write test case to fetch jwt token and then hit the Claims b2b listing api
    @Test(dependsOnMethods = "fetchUserTokenForClaimsApis")
    public void fetchUcpLossClaims() throws JsonProcessingException {
        paramObjectData = new HashMap<>();
        //set the wbn of param object Data hashmap from global vairable
        paramObjectData.put("wbn", waybill);


        apiCtrl.verifyUcpLossClaimsApi(paramObjectData, requestData);

    }

    //write test case for createLossClaimAPi with
    //pass hashmap in api function
    @Test(dependsOnMethods = "fetchUserTokenForClaimsApis")
    public void createLossClaim() {
        paramObjectData = new HashMap<>();
        //set the param object Data hashmap from global vairable
        paramObjectData.put("wbn", waybill);
        //clientName, disputeType, claimAmount, clientRemarks
        paramObjectData.put("clientName", clientName);
        paramObjectData.put("disputeType", disputeType);
        paramObjectData.put("claimAmount", claimAmount);
        paramObjectData.put("clientRemarks", clientRemarks);

        apiCtrl.createLossClaimAPi(paramObjectData, requestData);

    }

    //write test case for getCLaimIdData with
    //pass hashmap in api function
    @Test(dependsOnMethods = "fetchUserTokenForClaimsApis")
    public void getCLaimIdData() {
        apiData = new HashMap<>();
        //set the param object Data hashmap from global vairable
        apiData.put("ClaimId", ClaimId);
        apiData.put("wbn", waybill);
        apiData.put("client_name", clientName);
        apiData.put("dispute_type", claimIdDisputeType);
        apiData.put("status", status);
        apiCtrl.verifyFetchClaimDataApi(apiData, requestData);
    }


    @Test(dependsOnMethods = "fetchUserTokenForClaimsApis")
    public void createClaimsUploadFile() {
        Map<String, ArrayList<String>> data = new LinkedHashMap<>();
        data.put("Headers", new ArrayList<>(Arrays.asList("wbn", "dispute_type", "claim_amount", "client_remarks", "client", "image_link")));
        data.put("Key1", new ArrayList<>(Arrays.asList(waybill, disputeType, claimAmount, clientRemarks, clientName, imageLink)));
        data.put("Key2", new ArrayList<>(Arrays.asList(waybill, claimIdDisputeType, claimAmount, clientRemarks, clientName, imageLink)));
        CsvFileUtils.writeDataToCSV(filePath , fileName, data);
        apiCtrl.createClaimUploadFileAPi(filePath + fileName, requestData);
    }

    @Test(dependsOnMethods = "fetchUserTokenForClaimsApis")
    public void getClaimHistory() {
        apiCtrl.verifyFetchClaimHistoryApi(requestData);

    }

    @Test(dependsOnMethods = "fetchUserTokenForClaimsApis")
    public void getClaimB2bView() throws JsonProcessingException {
        paramObjectData = new HashMap<>();
        //set the start date and end date of param object Data hashmap from global vairable
        paramObjectData.put("createdAt", createdAt);
        paramObjectData.put("ClaimId", b2bViewClaimId);
        apiCtrl.verifyClaimsB2bViewApi(paramObjectData, requestData);
    }

    //write test case to fetch jwt token and then hit the Claims b2b listing api
    @Test(dependsOnMethods = "fetchUserTokenForClaimsApis")
    public void fetchClaimsB2bReport() throws JsonProcessingException {
        paramObjectData = new HashMap<>();
        //set the start date and end date of param object Data hashmap from global vairable
        paramObjectData.put("fromDate", b2bClaimListingStartDate);
        paramObjectData.put("toDate", b2bClaimListingEndDate);
        apiCtrl.verifyClaimsB2bReportApi(paramObjectData, requestData);

    }

    //write test case to fetch jwt token and then hit the Claims b2b listing api
    @Test(dependsOnMethods = "fetchUserTokenForClaimsApis")
    public void fetchClaimsB2cReport() throws JsonProcessingException {
        paramObjectData = new HashMap<>();
        //set the start date and end date of param object Data hashmap from global vairable
//        paramObjectData.put("fromDate", b2cClaimListingStartDate);
//        paramObjectData.put("toDate", b2cClaimListingEndDate);

        apiCtrl.verifyClaimsB2cReportApi(paramObjectData, requestData);
    }

    @Test(dependsOnMethods = "fetchUserTokenForClaimsApis")
    public void updateClaimSettlementAmount() {
        paramObjectData = new HashMap<>();
        //set the param object Data hashmap from global vairable
        //pdt, createdAt, actionType, settlementAmount, claimId
//        paramObjectData.put("pdt", pdt);
//        paramObjectData.put("createdAt", createdAt);
//        paramObjectData.put("actionType", actionType);
//        paramObjectData.put("settlementAmount", settlementAmount);
//        paramObjectData.put("claimId", ClaimId);

        apiCtrl.updateClaimSettlementAmountAPi(paramObjectData, requestData);
    }

    @Test(dependsOnMethods = "fetchUserTokenForClaimsApis")
    public void updateClaimSettlementAmountUploadFile() {
        Map<String, ArrayList<String>> data = new LinkedHashMap<>();
        data.put("Headers", new ArrayList<>(Arrays.asList("claimid", "pdt", "settlement_amount", "closing remarks")));
        data.put("Key1", new ArrayList<>(Arrays.asList(ClaimId, pdt, settlementAmount, clientRemarks)));
        data.put("Key2", new ArrayList<>(Arrays.asList(b2bViewClaimId, pdt, settlementAmount, clientRemarks)));

        CsvFileUtils.writeDataToCSV(filePath, fileName, data);
        apiCtrl.updateClaimSettlementAmountUploadFileAPi(filePath + fileName, requestData);

    }
}

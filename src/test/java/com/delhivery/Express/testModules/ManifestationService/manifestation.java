package com.delhivery.Express.testModules.ManifestationService;

import com.delhivery.Express.controllers.api.ApiController;
import com.delhivery.Express.pojo.FetchPackageDetails.response.PackageDetail;
import com.delhivery.core.utils.ManifestationKeysAssertions;
import com.delhivery.core.utils.Utilities;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONObject;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static com.delhivery.Express.testModules.ManifestationService.ComparePkg.compare;
import static com.delhivery.core.utils.Utilities.logFailure;
import static com.delhivery.core.utils.Utilities.logInfo;

public class manifestation {
    static String NewWbn,StageWbn, child_waybill = "", NewUPL, StageUPL;
    static ApiController apiCtrl = new ApiController();
    static ObjectMapper mapper = new ObjectMapper();
    static JSONObject js=new JSONObject();

    public static String NewEnv= "NewManifest";
    public static String StagingEnv = "staging";


    //create function for manifestation of edt and staging waybills for first builder and compare them
    //apiCtrl = NewManifestApi for first builder
    //EDT manifestation, Staging manifestation
    public static void manifestForFirstBuilder(HashMap<String,Object> manifestData, String scenario) throws JsonProcessingException {
        List<String> New_Service_waybills = new ArrayList<String>();
        List<String> staging_waybills = new ArrayList<String>();
        //EDT manifestation
        New_Service_waybills = apiCtrl.NewManifestApi(NewEnv, manifestData);

        //Staging manifestation
        staging_waybills = apiCtrl.NewManifestApi(StagingEnv, manifestData);

        //Compare edt vs staging waybills
        compare(New_Service_waybills, staging_waybills, scenario);

    }

    //create function for manifestation of edt and staging waybills for second builder and compare them
    //apiCtrl = NewManifestApi2 for second builder
    //EDT manifestation, Staging manifestation
    public static void manifestForSecondBuilder(HashMap<String,Object> manifestData, String scenario) throws JsonProcessingException {
        List<String> New_Service_waybills = new ArrayList<String>();
        List<String> staging_waybills = new ArrayList<String>();
        //EDT manifestation
        New_Service_waybills = apiCtrl.NewManifestApi2(NewEnv, manifestData);

        //Staging manifestation
        staging_waybills = apiCtrl.NewManifestApi2(StagingEnv, manifestData);

        //Compare edt vs staging waybills
        compare(New_Service_waybills, staging_waybills, scenario);

    }

    //create function for manifestation of edt and staging waybills for third builder and compare them
    //apiCtrl = NewManifestApi3 for third builder
    //EDT manifestation, Staging manifestation
    public static void manifestForThirdBuilder(HashMap<String,Object> manifestData, String scenario) throws JsonProcessingException {
        List<String> New_Service_waybills = new ArrayList<String>();
        List<String> staging_waybills = new ArrayList<String>();
        //EDT manifestation
        New_Service_waybills = apiCtrl.NewManifestApi3(NewEnv, manifestData);

        //Staging manifestation
        staging_waybills = apiCtrl.NewManifestApi3(StagingEnv, manifestData);

        //Compare edt vs staging waybills
        compare(New_Service_waybills, staging_waybills, scenario);

    }

    //create function for manifestation of edt and staging waybills for mandatorykeys and compare them
    //apiCtrl =  ManifestationUsingMandatoryKeys for mandatorykeys
    //EDT manifestation, Staging manifestation
    public static void manifestUsingMandatoryKeys(HashMap<String,Object> manifestData, String scenario) throws JsonProcessingException {
        List<String> New_Service_waybills = new ArrayList<String>();
        List<String> staging_waybills = new ArrayList<String>();
        //EDT manifestation
        New_Service_waybills = apiCtrl.ManifestationUsingMandatoryKeys(NewEnv, manifestData);

        //Staging manifestation
        staging_waybills = apiCtrl.ManifestationUsingMandatoryKeys(StagingEnv, manifestData);

        //Compare edt vs staging waybills
        compare(New_Service_waybills, staging_waybills, scenario);

    }
    //create function for manifestation of edt and staging waybills for MPS and compare them
    //apiCtrl =  MPSManifestationUsingMasterChildPayload for MPS
    //EDT manifestation, Staging manifestation
    public static void MPSManifestationMasterChildPayload(HashMap<String,Object> manifestData, String scenario) throws JsonProcessingException {
        List<String> New_Service_waybills = new ArrayList<String>();
        List<String> staging_waybills = new ArrayList<String>();
        //EDT manifestation
        New_Service_waybills = apiCtrl.MPSManifestationUsingMasterChildPayload(NewEnv, manifestData);

        //Staging manifestation
        staging_waybills = apiCtrl.MPSManifestationUsingMasterChildPayload(StagingEnv, manifestData);

        //Compare edt vs staging waybills
        compare(New_Service_waybills, staging_waybills, scenario);

    }


    //create function for manifestation of edt and staging waybills for first builder and compare them for ewbn cases
    //apiCtrl = NewManifestApi for first builder
    //EDT manifestation, Staging manifestation
    public static void manifestForEwbn(HashMap<String,Object> manifestDataNewService,HashMap<String,Object> manifestDataStaging, String scenario) throws JsonProcessingException {
        List<String> New_Service_waybills = new ArrayList<String>();
        List<String> staging_waybills = new ArrayList<String>();
        //EDT manifestation
        New_Service_waybills = apiCtrl.NewManifestApi(NewEnv, manifestDataNewService);

        //Staging manifestation
        staging_waybills = apiCtrl.NewManifestApi(StagingEnv, manifestDataStaging);

        //Compare edt vs staging waybills
        compare(New_Service_waybills, staging_waybills, scenario);

    }
}

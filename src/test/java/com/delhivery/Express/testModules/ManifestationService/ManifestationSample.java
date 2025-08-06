package com.delhivery.Express.testModules.ManifestationService;

import com.delhivery.Express.controllers.api.ApiController;
import com.delhivery.core.BaseTest;
import com.delhivery.core.utils.YamlReader;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.testng.annotations.Test;

import static com.delhivery.Express.testModules.ManifestationService.manifestation.manifestForFirstBuilder;
import static com.delhivery.core.utils.Utilities.logInfo;

import java.util.*;

public class ManifestationSample extends BaseTest {
    protected static Map<String, Object> ValidPayloadData = YamlReader.getYamlValues("ManifestationData");
    List<String> keys = Arrays.asList(
            "name", "prodDesc"
    );
    private static String UPL;
    static ApiController apiCtrl = new ApiController();
    private String waybill, bagId, tripId, dispatchId;
    private ArrayList<String> waybills;

    private String product_type, payment_mode, packageStatus, bagSeal, ocid, cnid, client, pin;
    public String scenario;
    public HashMap<String, String> clData = new HashMap<>();
    public HashMap<String, String> manifestData = new HashMap<String, String>();
    public Map<String, Object> pkgFlowData;

    @Test(dataProvider = "Key_different_data_types", dataProviderClass = DataProviderClass.class, enabled = true)
    public void isCodKeyValidations( String Scenario, Object value) throws JsonProcessingException {
        HashMap<String,Object> manifestData = new HashMap<String,Object>();
        //Payload
        if (Scenario.contains("valid value")){
            manifestData.put("isCod", ValidPayloadData.get("isCod").toString());
        }else {
            manifestData.put("isCod", value);
        }

        manifestForFirstBuilder(manifestData, Scenario);


    }
}
package com.delhivery.Express.testModules.ManifestationService;

import com.delhivery.Express.controllers.api.ApiController;
import com.delhivery.core.BaseTest;
import com.delhivery.core.utils.Utilities;
import com.delhivery.core.utils.YamlReader;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static com.delhivery.Express.testModules.ManifestationService.manifestation.*;
import static com.delhivery.Express.testModules.PackageFlow.TestScript1.clData;
import static com.delhivery.core.utils.Utilities.getUniqueInt;
import static com.delhivery.core.utils.Utilities.logInfo;

public class BuisnessFlows3 extends BaseTest {
    private static String UPL;
    static ApiController apiCtrl = new ApiController();
    private String waybill, bagId, tripId, dispatchId;
    private ArrayList<String> waybills;

    private String product_type, payment_mode, packageStatus, bagSeal, ocid, cnid, client, pin;
    public String scenario;
    public HashMap<String, String> clData = new HashMap<>();
    public HashMap<String, String> manifestData = new HashMap<String, String>();

    public Map<String, Object> pkgFlowData;

    //Test case to manifest international shipment
    @Test(dataProvider = "Different_pdt_payment_types", dataProviderClass = DataProviderClass.class, enabled = true)
    public void International_DelphiB2C(String Scenario, Object pdt, Object paymentType) throws JsonProcessingException {
        HashMap<String, Object> manifestData = new HashMap<String, Object>();
        HashMap<String, Object> Data = new HashMap<String, Object>();
        Data.put("cl", "DelphiB2C-B2C");
        Data.put("count", 1);

        //Payload
        String wbn = apiCtrl.verifyFetchWbn(Data);


        manifestData.put("pdt", pdt);
        manifestData.put("paymentMode", paymentType);
        manifestData.put("international", true);
        manifestData.put("waybill", getUniqueInt(13));


        manifestForFirstBuilder(manifestData, Scenario);

    }

    @Test(dataProvider = "Different_pdt_payment_types", dataProviderClass = DataProviderClass.class, enabled = true)
    public void International_ARAMEX(String Scenario, Object pdt, Object paymentType) throws JsonProcessingException {
        HashMap<String, Object> manifestData = new HashMap<String, Object>();
        HashMap<String, Object> Data = new HashMap<String, Object>();
        Data.put("cl", "ARAMEX");
        Data.put("count", 1);

        //Payload
        String wbn = apiCtrl.verifyFetchWbn(Data);


        manifestData.put("pdt", pdt);
        manifestData.put("paymentMode", paymentType);
        manifestData.put("international", true);
        manifestData.put("waybill", getUniqueInt(15));


        manifestForFirstBuilder(manifestData, Scenario);

    }
}

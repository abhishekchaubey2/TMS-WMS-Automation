package com.delhivery.Express.testModules.PackageFlow;

import com.delhivery.Express.controllers.api.ApiController;
import com.delhivery.core.BaseTest;
import com.delhivery.core.utils.ProdConfigLoader;
import com.delhivery.core.utils.Utilities;
import com.delhivery.core.utils.YamlReader;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.delhivery.core.utils.Utilities.logInfo;

public class ProductionHighThroughputApisSanity extends BaseTest {

    private final ThreadLocal<List<String>> waybills = new ThreadLocal<>();
    private final ThreadLocal<String> waybill = new ThreadLocal<>();

    private String bagId, tripId, dispatchId;

    private String product_type, payment_mode, packageStatus, bagSeal, client, clientWarehouse, client_uuid, cwh_uuid, pin, origin_center, destination_center, ocid, cnid, instaBagStId, instaBagStName;
    public String scenario;
    public HashMap<String, String> clData = new HashMap<>();
    public HashMap<String, String> manifestData = new HashMap<>();
    public HashMap<String, String> bagData = new HashMap<>();
    public Map<String, Object> pkgFlowData;
    public Map<String, Object> bagFlowData;
    ApiController apiCtrl = new ApiController();

    public ProductionHighThroughputApisSanity() {
        scenario = "Package Flow B2C Prepaid type shipment";
        product_type = ProdConfigLoader.getInstance().getProductType();
        payment_mode = ProdConfigLoader.getInstance().getPaymentType();
        pin = ProdConfigLoader.getInstance().getPin();
        client = YamlReader.getYamlValues("productionData.client_" + ProdConfigLoader.getInstance().getClient()).get("name").toString();
        clientWarehouse = YamlReader.getYamlValues("productionData.client_" + ProdConfigLoader.getInstance().getClient()).get("client_warehouse").toString();
        client_uuid = YamlReader.getYamlValues("productionData.client_" + ProdConfigLoader.getInstance().getClient()).get("client_uuid").toString();
        cwh_uuid = YamlReader.getYamlValues("productionData.client_" + ProdConfigLoader.getInstance().getClient()).get("cwh_uuid").toString();
        origin_center = YamlReader.getYamlValues("productionData." + ProdConfigLoader.getInstance().getOriginCenter()).get("Name").toString();
        destination_center = YamlReader.getYamlValues("productionData." + ProdConfigLoader.getInstance().getDestinationCenter()).get("Name").toString();
        ocid = YamlReader.getYamlValues("productionData." + ProdConfigLoader.getInstance().getOriginCenter()).get("SortCode").toString();
        cnid = YamlReader.getYamlValues("productionData." + ProdConfigLoader.getInstance().getDestinationCenter()).get("SortCode").toString();
        instaBagStId = ProdConfigLoader.getInstance().getInstaBagStationId();
        instaBagStName = ProdConfigLoader.getInstance().getInstaBagStationName();

        manifestData.put("jobType", "ProdSanity");
        manifestData.put("prodClient", client);
        manifestData.put("product_type", product_type);
        manifestData.put("payment_mode", payment_mode);
        manifestData.put("pin", pin);

        clData.put("jobType", "ProdSanity");
        clData.put("prodClient", client);
        clData.put("clientUuid", client_uuid);
        clData.put("ocid", ocid);
        clData.put("cnid", cnid);
        clData.put("shipment_destination_center", destination_center);

        bagData.put("jobType", "ProdSanity");
        bagData.put("prodClient", client);
        bagData.put("origin", origin_center);
        bagData.put("destination", destination_center);

    }

    @Test()
    public void checkClientUuidApi() throws JsonProcessingException {
        apiCtrl.verifyClientDetailsApi(clData);
    }

    @Test
    public void packageUpdate() {
        waybills.set(apiCtrl.cmuManifestApi(manifestData));
        waybill.set(waybills.get().get(0));
        logInfo("Waybill generated " + waybill.get());

        apiCtrl.verifyPackageUpdate(waybill.get(), clData);
    }


    @Test
    public void checkApplyNsl() {
        waybills.set(apiCtrl.cmuManifestApi(manifestData));
        waybill.set(waybills.get().get(0));
        logInfo("Waybill generated " + waybill.get());

        apiCtrl.fmOMSApi(waybill.get(), "FMPICK", clData);
        apiCtrl.fmOMSApi(waybill.get(), "FMDEPART", clData);
        apiCtrl.giApi(waybill.get(), origin_center, clData);

        apiCtrl.ApplyNsl(waybills.get(), "LT", "LT-100", clData);
    }

    @Test
    public void checkFetchListApi() {
        waybills.set(apiCtrl.cmuManifestApi(manifestData));
        waybill.set(waybills.get().get(0));
        logInfo("Waybill generated " + waybill.get());

        apiCtrl.flApi(waybill.get(), origin_center, clData);

    }

    @Test
    public void checkCreateV2Bag() {
        waybills.set(apiCtrl.cmuManifestApi(manifestData));
        waybill.set(waybills.get().get(0));
        logInfo("Waybill generated " + waybill.get());

        apiCtrl.fmOMSApi(waybill.get(), "FMPICK", clData);
        apiCtrl.fmOMSApi(waybill.get(), "FMDEPART", clData);
        apiCtrl.giApi(waybill.get(), origin_center, clData);

        String bagId = apiCtrl.bagv2Api(waybill.get(), bagData);
//        Utilities.hardWait(60);
        logInfo("Bag created " + bagId);
    }

    @Test
    public void checkCreateV3Bag() {
        waybills.set(apiCtrl.cmuManifestApi(manifestData));
        waybill.set(waybills.get().get(0));
        logInfo("Waybill generated " + waybill.get());

        apiCtrl.fmOMSApi(waybill.get(), "FMPICK", clData);
        apiCtrl.fmOMSApi(waybill.get(), "FMDEPART", clData);
        apiCtrl.giApi(waybill.get(), origin_center, clData);

        String bagId = apiCtrl.bagv3Api(waybill.get(), bagData);
//        Utilities.hardWait(60);
        logInfo("Bag created " + bagId);
    }

    @Test
    public void checkCreateV4Bag() {
        waybills.set(apiCtrl.cmuManifestApi(manifestData));
        waybill.set(waybills.get().get(0));
        logInfo("Waybill generated " + waybill.get());

        apiCtrl.fmOMSApi(waybill.get(), "FMPICK", clData);
        apiCtrl.fmOMSApi(waybill.get(), "FMDEPART", clData);
        apiCtrl.giApi(waybill.get(), origin_center, clData);

        String bagId = apiCtrl.bagv4Api(waybill.get(), bagData);
//        Utilities.hardWait(60);
        logInfo("Bag created " + bagId);
    }

    @Test
    public void checkEditApiApplyCancellationScan() {
        waybills.set(apiCtrl.cmuManifestApi(manifestData));
        waybill.set(waybills.get().get(0));
        logInfo("Waybill generated " + waybill.get());

        apiCtrl.fmOMSApi(waybill.get(), "FMPICK", clData);
        apiCtrl.fmOMSApi(waybill.get(), "FMDEPART", clData);
        apiCtrl.giApi(waybill.get(), origin_center, clData);

        HashMap<String, String> requestData = clData;
        requestData.put("cancellation", "true");
        apiCtrl.EditApiApplyCancellationScan(waybill.get(), requestData);
    }

    @Test
    public void checkEditApiUpdateConsigneeInfo() {
        waybills.set(apiCtrl.cmuManifestApi(manifestData));
        waybill.set(waybills.get().get(0));
        logInfo("Waybill generated " + waybill.get());

        apiCtrl.fmOMSApi(waybill.get(), "FMPICK", clData);
        apiCtrl.fmOMSApi(waybill.get(), "FMDEPART", clData);
        apiCtrl.giApi(waybill.get(), origin_center, clData);

        HashMap<String, String> requestData = clData;
        requestData.put("add", "testAdd");
        apiCtrl.EditApi(waybill.get(), requestData);
    }

    @Test
    public void checkPackingSlip() {
        waybills.set(apiCtrl.cmuManifestApi(manifestData));
        waybill.set(waybills.get().get(0));
        logInfo("Waybill generated " + waybill.get());
        apiCtrl.verifyPackingSlip(waybill.get(), clData);
    }

    @Test
    public void checkBagDimensionsCalculate() {
        waybills.set(apiCtrl.cmuManifestApi(manifestData));
        waybill.set(waybills.get().get(0));
        logInfo("Waybill generated " + waybill.get());

        apiCtrl.fmOMSApi(waybill.get(), "FMPICK", clData);
        apiCtrl.fmOMSApi(waybill.get(), "FMDEPART", clData);
        apiCtrl.giApi(waybill.get(), origin_center, clData);

        String bagId = apiCtrl.bagv3Api(waybill.get(), bagData);
        logInfo("Bag created " + bagId);
        Utilities.hardWait(60);

        apiCtrl.verifyBagCalc(bagId, clData);
    }
}
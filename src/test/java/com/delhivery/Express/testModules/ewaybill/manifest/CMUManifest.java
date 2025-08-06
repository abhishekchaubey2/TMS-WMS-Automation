package com.delhivery.Express.testModules.ewaybill.manifest;

import com.delhivery.Express.dataprovider.manifestationData;
import com.delhivery.core.BaseTest;
import org.testng.annotations.Test;

public class CMUManifest extends BaseTest {

    @Test(dataProvider = "Different_pdt_payment_types",dataProviderClass = manifestationData.class)
    private void testCMUManifestWithOrWithoutEWayBill(String scenario, String productType, String paymentType){
    //Todo will added rabit mq to kafka task cases here
    }
}

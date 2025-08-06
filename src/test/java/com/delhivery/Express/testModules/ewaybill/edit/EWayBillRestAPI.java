package com.delhivery.Express.testModules.ewaybill.edit;

import com.delhivery.Express.controllers.api.ApiController;
import com.delhivery.Express.dataprovider.EWaybillDataProvider;
import com.delhivery.Express.dataprovider.manifestationData;
import com.delhivery.Express.pojo.FetchPackageDetails.response.PackageDetail;
import com.delhivery.Express.pojo.ewaybill.APIRestEWayBill.response.APIRestEWayBillResponsePayload;
import com.delhivery.Express.testModules.ewaybill.util.EWayBillTestHelper;
import com.delhivery.core.BaseTest;
import com.delhivery.core.utils.Utilities;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;

import java.util.HashMap;

import static com.delhivery.core.utils.Assertions.assertKeyValue;

public class EWayBillRestAPI extends BaseTest {
    private final String productType;
    private final String paymentType;
    private static String internalUserJwtToken;
    ApiController apiController = new ApiController();
    private static boolean tokenFetched = false;


    public EWayBillRestAPI(String productType, String paymentType) {
        this.productType = productType;
        this.paymentType = paymentType;
    }

    @Factory(dataProvider = "Different_pdt_payment_types", dataProviderClass = manifestationData.class)
    public static Object[] getDifferentPDTType(String scenario, String productType, String state) {
        return new Object[]{new EWayBillRestAPI(productType, state)};
    }

    @Test(dataProvider = "eWaybillDataProvider", dataProviderClass = EWaybillDataProvider.class)
    public void testAPIEWayBillUpdateByExternalAPI(String scenario, String documentNumber, String eWayBillNumber, String rs, String api) {
        // Fetch token only if not already fetched
        if (!tokenFetched) {
            System.out.println("Token is not fetched");
            internalUserJwtToken = apiController.fetchInternalUserJwtTokenApi(null);
            // Update flag to indicate token is fetched
            tokenFetched = true;
        } else {
            System.out.printf("Token is fetched");
        }
        HashMap<String, String> manifestData = new HashMap<>();
        manifestData.put("product_type", productType);
        manifestData.put("paymentType", paymentType);
        manifestData.put("total_amount", scenario.contains("Prev amt < 50K") ? "300" : "70000");
        manifestData.put("documentNumber", documentNumber);
        manifestData.put("pin", EWayBillTestHelper.pinCodeHandler(productType));
        manifestData.put("token", internalUserJwtToken);

        String waybill = apiController.cmuManifestApi(manifestData).get(0);

        manifestData.put("waybill", waybill);
        manifestData.put("documentNumber", documentNumber);
        manifestData.put("eWayBillNumber", eWayBillNumber);
        manifestData.put("rs", rs);
        manifestData.put("api", api);

        PackageDetail packageDetail = apiController.verifyPackageFetchInfoApi(waybill, null, null, null, manifestData);
        assertKeyValue("Before Update Is EWB REQ : ", !(scenario.contains("Prev amt < 50K")), packageDetail.flags.isEwbnReq);
        APIRestEWayBillResponsePayload apiRestEWayBillResponsePayload = apiController.updateRSDCNEWBNOfWayBill(manifestData);
        assertKeyValue("Success", true, apiRestEWayBillResponsePayload.success);

        Utilities.hardWait(10);

        packageDetail = apiController.verifyPackageFetchInfoApi(waybill, null, null, null, manifestData);
        assertKeyValue("Is EWB REQ: ", Integer.parseInt(rs) > EWayBillTestHelper.eWaybillThreshold, packageDetail.flags.isEwbnReq);
    }

}

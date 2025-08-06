package com.delhivery.Express.testModules.ewaybill.edit;

import com.delhivery.Express.controllers.api.ApiController;
import com.delhivery.Express.controllers.api.DifferentStateShipments;
import com.delhivery.Express.dataprovider.EWaybillDataProvider;
import com.delhivery.Express.dataprovider.manifestationData;
import com.delhivery.Express.pojo.EditApi.response.EditApiResponsePayload;
import com.delhivery.Express.pojo.FetchPackageDetails.response.PackageDetail;
import com.delhivery.Express.testModules.ewaybill.util.EWayBillTestHelper;
import com.delhivery.core.BaseTest;
import com.delhivery.core.utils.Utilities;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;

import java.util.HashMap;

import static com.delhivery.core.utils.Assertions.assertKeyValue;

public class EWayBIllEditAPI extends BaseTest {
    private final String productType;
    private final String state;

    public EWayBIllEditAPI(String productType, String state) {
        this.productType = productType;
        this.state = state;
    }

    @Factory(dataProvider = "Different_state_type", dataProviderClass = manifestationData.class)
    public static Object[] getDifferentPDTType(String scenario, String productType, String state) {
        return new Object[]{new EWayBIllEditAPI(productType, state)};
    }

    @Test(dataProvider = "eWaybillEditAPIDataProvider", dataProviderClass = EWaybillDataProvider.class)
    public void testUpdateWayBillRSBYEditPAPI(String scenario, String documentNumber, String eWayBillNumber, String rs) {
        ApiController apiController = new ApiController();
        DifferentStateShipments differentStateShipments = new DifferentStateShipments();
        HashMap<String, String> manifestData = prepareManifestData(scenario, documentNumber);

        if (shouldProcessTest(scenario)) {
            String waybill = differentStateShipments.DifferentStateShipments(state, manifestData);
            manifestData.clear();
            manifestData.put("rs", rs);
            manifestData.put("cancellation", "false");
            PackageDetail packageDetail = apiController.verifyPackageFetchInfoApi(waybill, null, null, null, manifestData);
            assertKeyValue("Before Update Is EWB REQ : ", !(scenario.contains("Prev amt < 50K")), packageDetail.flags.isEwbnReq);
            manifestData.put("e_way_bill", "true");
            EditApiResponsePayload editApiResponsePayload = apiController.EditApi(waybill, manifestData);

            boolean isEWBReq = updateEWBRequirementBasedOnState(scenario, state, rs);
            String successMessage = updateSuccessMessageBasedOnState(scenario, state, rs);

            assertKeyValue("status", successMessage, editApiResponsePayload.status);
            Utilities.hardWait(10);

            packageDetail = apiController.verifyPackageFetchInfoApi(waybill, null, null, null, manifestData);
            assertKeyValue("After Update Is EWB REQ : ", isEWBReq, packageDetail.flags.isEwbnReq);
        }
    }

    protected HashMap<String, String> prepareManifestData(String scenario, String documentNumber) {
        HashMap<String, String> manifestData = new HashMap<>();
        manifestData.put("product_type", productType);
        manifestData.put("total_amount", scenario.contains("Prev amt < 50K") ? "300" : "70000");
        manifestData.put("documentNumber", documentNumber);
        manifestData.put("pin", EWayBillTestHelper.pinCodeHandler(productType));
        manifestData.put("state", state);
        return manifestData;
    }

    private boolean updateEWBRequirementBasedOnState(String scenario, String state, String rs) {
        boolean isEWBReq = false;

        switch (state) {
            case "MANIFEST":
                isEWBReq = rs.equalsIgnoreCase("80000");
                break;
            case "IN TRANSIT":
            case "pending":
            case "PickupPending":
            case "Returned":
            case "PickedUp":
                if (!scenario.contains("Prev amt < 50K")) {
                    // Flag is true if prev amount less than 50K, false otherwise
                    isEWBReq = true;
                }
                if (scenario.contains("Prev amt < 50K") && rs.equalsIgnoreCase("80000")) {
                    // Flag is true if prev amount less than 50K, false otherwise
                    isEWBReq = true;
                }
                break;
            case "Cancelled":
                if (!scenario.contains("Prev amt < 50K") || (scenario.contains("Prev amt < 50K") && rs.equalsIgnoreCase("80000"))) {
                    // Flag is true if prev amount less than 50K, false otherwise
                    isEWBReq = true;
                }
                break;
            case "delivered":
                if (!scenario.contains("Prev amt < 50K")) {
                    // Flag is true if prev amount less than 50K, false otherwise
                    isEWBReq = true;
                }
                break;
            default:
                break;
        }
        return isEWBReq;
    }

    private String updateSuccessMessageBasedOnState(String scenario, String state, String rs) {
        String successMessage = "true";

        switch (state) {
            case "IN TRANSIT":
            case "pending":
            case "PickupPending":
            case "Returned":
            case "PickedUp":
                if (!scenario.contains("Prev amt < 50K")) {
                    // message is false if prev amount less than 50K, true otherwise
                    successMessage = "false";
                }
                if (scenario.contains("Prev amt < 50K") && rs.equalsIgnoreCase("80000")) {
                    // message is true if prev amount less than 50K, false otherwise
                    successMessage = "true";
                }
                break;
            case "Cancelled":
                successMessage = "false";
                break;
            case "delivered":
                successMessage = "Failure";
                break;
            default:
                break;
        }
        return successMessage;
    }

    private boolean shouldProcessTest(String scenario) {
        if (!state.equalsIgnoreCase("Returned") && !scenario.contains("Prev amt > 50K")) {
            return true;
        }
        return false;
    }

}

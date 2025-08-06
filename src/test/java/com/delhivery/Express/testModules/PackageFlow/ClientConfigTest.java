package com.delhivery.Express.testModules.PackageFlow;

import com.delhivery.Express.controllers.api.ApiController;
import com.delhivery.Express.dataprovider.ClientConfigTestDataProvider;
import com.delhivery.Express.pojo.ClientDetails.Response.ClientDetailsResponsePayload;
import com.delhivery.core.BaseTest;
import org.apache.commons.lang3.RandomStringUtils;
import org.testng.annotations.Test;


import static com.delhivery.core.utils.Assertions.assertKeyValue;

/**
 * Create-update client and validation
 */
public class ClientConfigTest extends BaseTest {
    ApiController apiController = new ApiController();

    //Added synchronized mechanism to avoid client creation error in parallel execution, since client creation is atomic
    @Test(dataProvider = "createUpdateClientData", dataProviderClass = ClientConfigTestDataProvider.class)
    public synchronized void testCreateUpdateClient(String scenario, String productType) {
        String clientName = "PickupTestClient" + RandomStringUtils.randomNumeric(8);
        apiController.verifyClientCreateUpdate(clientName, productType);
        ClientDetailsResponsePayload clientDetailsResponsePayload = apiController.verifyClientDetailsByClientName(clientName);
        assertKeyValue("otp_pkup_verification", scenario.contains("B2B") ? "2" : "1", clientDetailsResponsePayload.getData().getExtra().getOtpPkupVerification());
    }
}

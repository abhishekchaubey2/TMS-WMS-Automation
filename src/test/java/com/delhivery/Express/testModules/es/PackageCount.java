package com.delhivery.Express.testModules.es;

import com.delhivery.Express.controllers.api.ApiController;
import com.delhivery.Express.pojo.es.PackageCount.response.PackageCountResPayload;
import com.delhivery.core.BaseTest;
import com.delhivery.core.utils.Assertions;
import org.testng.annotations.Test;

public class PackageCount extends BaseTest {
    @Test
    private void testPackageCountWithDiffEnv(){
        ApiController apiController = new ApiController();
        PackageCountResPayload edtResponse= apiController.getPackageCount("edt");
        PackageCountResPayload stageResponse = apiController.getPackageCount("Staging");
        Assertions.assertKeyValue("Counts : ",edtResponse.getCounts(),stageResponse.getCounts());
        Assertions.assertKeyValue("msg : ",edtResponse.getMessage(),stageResponse.getMessage());
    }
}

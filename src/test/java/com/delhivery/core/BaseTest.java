package com.delhivery.core;

import com.delhivery.Express.controllers.api.DifferentTypeShipments;
import com.delhivery.Express.pojo.BagDetailsFetchRestInfoApi.response.S;
import com.delhivery.core.utils.ExcelFileUtil;
import com.delhivery.core.utils.ExtentITestListenerAdapter;
import com.delhivery.core.utils.MasterCheckListExporter;
import org.awaitility.Awaitility;
import org.testng.ITestContext;
import org.testng.annotations.*;

import java.io.IOException;
import java.lang.reflect.Method;

import static java.util.concurrent.TimeUnit.MILLISECONDS;
import static java.util.concurrent.TimeUnit.SECONDS;

@Listeners(ExtentITestListenerAdapter.class)
public class BaseTest {
    protected DifferentTypeShipments diffTypeShipment;
    protected final long addfixCallbackDelayTime = 80;
    protected final long returnAddfixCallbackDelayTime = 80;
    protected final long keyassertPkgInfoFetchDelayTime = 20;
    public BaseTest() {
        diffTypeShipment = new DifferentTypeShipments();
    }

    @BeforeSuite
    public void setUp() {
        Awaitility.reset();
        Awaitility.setDefaultPollDelay(1, SECONDS);
        Awaitility.setDefaultPollInterval(1500, MILLISECONDS);
        Awaitility.setDefaultTimeout(7, SECONDS);
    }


    @BeforeClass
    public void beforeClass(ITestContext context) throws IOException {
        //This will handle the thread count and order of execution of test cases
        context.getCurrentXmlTest().getSuite().setDataProviderThreadCount(500);
        context.getCurrentXmlTest().getSuite().setPreserveOrder(true);
    }

    @BeforeMethod
    public void beforeMethod(Method m) {
        System.out.println("STARTING TEST: " + m.getName());
        System.out.println("THREAD ID: " + Thread.currentThread().getId());
    }

    @AfterSuite
    public void shutDown(){
        System.out.println("<-------------------------================================--------------------------------->");
        ExcelFileUtil.writeExcelFile("test-output/SparkReport","Regression_checklist","Scenario", MasterCheckListExporter.decorators , MasterCheckListExporter.scenarioListMap);
        System.out.println("Suite Execution Completed");
        System.out.println("<-------------------------================================--------------------------------->");
    }
}
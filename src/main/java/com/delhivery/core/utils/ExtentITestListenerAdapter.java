package com.delhivery.core.utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import lombok.Getter;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * ExtentITestListenerAdapter class is used to create test class and test method nodes in extent report.
 * It also creates sub node for test method, if it has parameters.
 * It also handles test success, failure and skipped scenarios.
 * It also creates extent report instance and attach spark reporter.
 * It also handles concurrency issues, while creating test class and test method nodes.
 */

@Getter
public class ExtentITestListenerAdapter implements ITestListener {

    private static final ExtentReports extentReports;

    private static final Properties properties;

    private static final ThreadLocal<ExtentTest> testClass = new ThreadLocal<>();
    private static final ThreadLocal<ExtentTest> testMethod = new ThreadLocal<>();
    private static final ThreadLocal<ExtentTest> dataProviderTest = new ThreadLocal<>();
    private static final ConcurrentMap<String, ExtentTest> testMethodRegistry = new ConcurrentHashMap<>();
    private static final ConcurrentMap<String, ExtentTest> testClassRegistry = new ConcurrentHashMap<>();
    private static final Lock lock = new ReentrantLock();
    private static final String propPath = "src/test/resources/extent.properties";
    public static final Map<String,List<String>> scenarioListMap = new HashMap<>();
    private static int keyCount = 0;
    private static boolean isAlreadyPopulated = false;

    static {
        try {
            properties = PropertyUtils.propertyLoader(propPath);
            extentReports = createInstance(properties.getProperty("extent.reporter.spark.config"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public synchronized void onFinish(ITestContext context) {
        String suiteName = context.getSuite().getName();
        if(!isAlreadyPopulated){
            MasterCheckListExporter.populateMasterCheckListScenario(CoreConstants.SUITE_IDENTIFIER,suiteName);
            isAlreadyPopulated=true;
            MasterCheckListExporter.populateHeaders();
        }
        extentReports.flush();
    }

    // This method is called when the test class is instantiated
    @Override
    public synchronized void onTestStart(ITestResult result) {
        String className = result.getMethod().getRealClass().getSimpleName();
        String methodName = result.getMethod().getMethodName();

        //Taking lock to avoid concurrency issues, while creating test class and test method nodes
        lock.lock();
        try {
            handleTestClassCreation(className, result);
            handleTestMethodCreation(methodName, className);
        } finally {
            lock.unlock();
        }

        //Creating sub node for test method, if it has parameters
        if (result.getParameters().length > 0) {
            String paramName = constructTestName(result);
            MasterCheckListExporter.populateMasterCheckListScenario("test",methodName+"_"+paramName);
            System.out.println("Test method sub node params name : " + paramName);
            ExtentTest paramTest = testMethodRegistry.get(methodName).createNode(paramName);
            dataProviderTest.set(paramTest);
        } else {
            MasterCheckListExporter.populateMasterCheckListScenario("test",methodName);
            System.out.println("No need to create sub node for test method for " + methodName);
        }
    }

    //Handle test class parent node creation
    private void handleTestClassCreation(String className, ITestResult result) {
        if (testClass.get() == null && !testClassRegistry.containsKey(className)) {
            createTestClass(result, className);
            testClassRegistry.put(className, testClass.get());
        } else {
            System.out.println("Test class node already created for " + className);
        }
    }

    //Handle test method child node creation
    private void handleTestMethodCreation(String methodName, String className) {
        if (!testMethodRegistry.containsKey(methodName)) {
            ExtentTest testClassNode = testClassRegistry.get(className);
            if (testClassNode != null) {
                ExtentTest methodExtTest = testClassNode.createNode(methodName);
                testMethodRegistry.put(methodName, methodExtTest);
                testMethod.set(methodExtTest);
            } else {
                System.out.println("Warning: Test class node not found for " + className + ", skipping test method node creation for " + methodName);
            }
        } else {
            System.out.println("Test method node already created for " + methodName);
        }
    }

    //Create test class node
    private synchronized void createTestClass(ITestResult result, String className) {
        ExtentTest testClass = extentReports.createTest(className);
        ExtentITestListenerAdapter.testClass.set(testClass);

        String[] groups = result.getMethod().getGroups();
        if (groups.length > 0) {
            Arrays.asList(groups).forEach(x -> ExtentITestListenerAdapter.testClass.get().assignCategory(x));
        }
    }

    //This method is called when the test method is passed
    //It logs the test method as passed
    @Override
    public synchronized void onTestSuccess(ITestResult result) {
        ExtentTest test = getTest(result);
        if (test != null) {
            test.pass("Test Passed");
        }
    }

    //Get test method node
    //If test method has parameters, then return data provider test node
    private synchronized ExtentTest getTest(ITestResult result) {
        ExtentTest t =
                result.getParameters() != null && result.getParameters().length > 0
                        ? dataProviderTest.get()
                        : testMethodRegistry.get(result.getMethod().getMethodName());
        return t;
    }

    //This method is called when the test method is failed
    //It logs the test method as failed
    @Override
    public synchronized void onTestFailure(ITestResult result) {
        ExtentTest test = getTest(result);
        if (test != null) {
            test.fail(result.getThrowable());
        }
    }

    //This method is called when the test method is skipped
    //It logs the test method as skipped
    @Override
    public synchronized void onTestSkipped(ITestResult result) {
        ExtentTest test = getTest(result);
        if (test != null) {
            test.skip(result.getThrowable());
        }
    }

    @Override
    public synchronized void onTestFailedButWithinSuccessPercentage(ITestResult result) {
    }

    //Construct test name for test method node
    //If test method has parameters, then construct test name with factory and test level parameters
    private String constructTestName(ITestResult result) {
        Object[] factoryParameters = result.getFactoryParameters();
        String factoryScenario = findScenario(factoryParameters);

        Object[] testLevelParameters = result.getParameters();
        String testScenario = findScenario(testLevelParameters);

        if (factoryScenario == null && testScenario == null) {
            return result.getMethod().getMethodName();
        }

        StringBuilder testNameBuilder = new StringBuilder();
        if (factoryScenario != null) {
            testNameBuilder.append("Factory: ").append(factoryScenario);
        }
        if (testScenario != null) {
            if (factoryScenario != null) {
                testNameBuilder.append(" || ");
            }
            testNameBuilder.append("Test: ").append(testScenario);
        }

        return testNameBuilder.toString();
    }

    //Find scenario from parameters
    //Return scenario
    private String findScenario(Object[] parameters) {
        String scenario = null;
        for (Object param : parameters) {
            String paramStr = param.toString();
            if (paramStr.startsWith("Scenario:")) {
                scenario = paramStr;
                break;
            }
        }
        return scenario;
    }

    //Create extent report instance
    //Load extent config file and attach spark reporter
    //Return extent report instance
    private static ExtentReports createInstance(String filePath) throws IOException {
        ExtentSparkReporter spark =
                new ExtentSparkReporter(properties.getProperty("extent.reporter.spark.out"));
        spark.loadXMLConfig(filePath);
        ExtentReports extent = new ExtentReports();
        extent.attachReporter(spark);
        return extent;
    }

    //Get test method node
    //If test method has parameters, then return data provider test node
    public static ExtentTest getTest() {
        if (dataProviderTest.get() != null) {
            return dataProviderTest.get();
        }
        return testMethod.get();
    }
}
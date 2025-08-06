package com.delhivery.core.utils;

import com.aventstack.extentreports.ExtentTest;
import com.delhivery.Express.pojo.hqTracking.response.HQTrack;
import com.delhivery.Express.pojo.hqTrackingError.response.HQTrackError;
import com.delhivery.Express.pojo.trackingApi.response.Example;
import com.delhivery.Express.pojo.trackingApiError.response.TrackError;
import com.delhivery.project1.pojo.module.response.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import static com.delhivery.core.utils.Utilities.hasMatchingSubstring;
import static com.delhivery.core.utils.Utilities.logFailure;
import static com.delhivery.core.utils.Utilities.logInfo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import java.util.Arrays;
import java.util.List;

import org.skyscreamer.jsonassert.FieldComparisonFailure;
import org.skyscreamer.jsonassert.JSONCompare;
import org.skyscreamer.jsonassert.JSONCompareMode;
import org.skyscreamer.jsonassert.JSONCompareResult;

public class Assertions {
	static JSONCompareResult difference;
	static ObjectMapper mapper = new ObjectMapper();
//	 Example Exampleobj=new Example();
//	 HqTracking HqTrackingobj=new HqTracking();
	
    public static void assertStatusCode(int expectedStatusCode, Response response) {
        long responseTime = response.getTime();
        System.out.println("RESPONSE TIME (in ms): " + responseTime);
        int actualStatusCode = response.statusCode();
        System.out.println("Asserting status code, expecting : " + expectedStatusCode);
        if (actualStatusCode == expectedStatusCode) {
            System.out.println("PASS: Actual status code " + actualStatusCode);
        } else {
            System.out.println("INFO: Actual status code " + actualStatusCode);
        }
        assertThat(response.statusCode(), equalTo(expectedStatusCode));
    }

    public static void assertStatusCode(ExtentTest test, int expectedStatusCode, Response response) {
        long responseTime = response.getTime();
        logInfo(test, "RESPONSE TIME (in ms): " + responseTime);
        System.out.println("RESPONSE TIME (in ms): " + responseTime);
        int actualStatusCode = response.statusCode();
        test.info("Asserting status code, expecting : " + expectedStatusCode);
        if (actualStatusCode == expectedStatusCode) {
            test.pass("Actual status code " + actualStatusCode);
        } else {
            test.info("Actual status code " + actualStatusCode);
        }
        assertThat(response.statusCode(), equalTo(expectedStatusCode));
    }

    public static void assertSuccessMessage(ApiResponse apiResponse, String expectedMessage) {
        logInfo("Asserting Success = true");
        if (apiResponse.getSuccess().equals(true)) {
            ExtentITestListenerAdapter.getTest().pass("Success = " + apiResponse.getSuccess());
        } else {
            ExtentITestListenerAdapter.getTest().fail("Success = " + apiResponse.getSuccess());
        }
        assertThat(apiResponse.getSuccess(), equalTo(true));
        logInfo("Asserting Response Message = " + expectedMessage);
        if (apiResponse.getMessage().equalsIgnoreCase(expectedMessage)) {
            ExtentITestListenerAdapter.getTest().pass("Message = " + apiResponse.getMessage());
        } else {
            ExtentITestListenerAdapter.getTest().fail("Message = " + apiResponse.getMessage());
        }
        assertThat(apiResponse.getMessage(), equalTo(expectedMessage));
    }
    
    public static void assertSuccessMessage(ExtentTest test, ApiResponse apiResponse, String expectedMessage) {
        test.info("Asserting Success = true");
        if (apiResponse.getSuccess().equals(true)) {
            test.pass("Success = " + apiResponse.getSuccess());
        } else {
            test.fail("Success = " + apiResponse.getSuccess());
        }
        assertThat(apiResponse.getSuccess(), equalTo(true));
        test.info("Asserting Response Message = " + expectedMessage);
        if (apiResponse.getMessage().equalsIgnoreCase(expectedMessage)) {
            test.pass("Message = " + apiResponse.getMessage());
        } else {
            test.fail("Message = " + apiResponse.getMessage());
        }
        assertThat(apiResponse.getMessage(), equalTo(expectedMessage));
    }


    public static void assertSuccessMessageInJson(JsonPath jsonPath, String expectedMessage) {
        logInfo("Asserting Success");
        boolean success = jsonPath.get("success");
        String message = jsonPath.get("message");
        if (success) {
            ExtentITestListenerAdapter.getTest().pass("Success = " + true);
        } else {
            ExtentITestListenerAdapter.getTest().fail("Success = " + false);
        }
        assertThat(success, equalTo(true));
        logInfo("Asserting Response Message = " + expectedMessage);
        if (message.trim().equalsIgnoreCase(expectedMessage.trim())) {
            ExtentITestListenerAdapter.getTest().pass("Message = " + message);
        } else {
            ExtentITestListenerAdapter.getTest().fail("Message = " + message);
        }
        assertThat(message, equalTo(expectedMessage));
    }
  

    public static void assertSuccessInJson(JsonPath jsonPath) {
        logInfo("Asserting Success");
        boolean success = jsonPath.get("success");
        if (success) {
            ExtentITestListenerAdapter.getTest().pass("Success = " + true);
        } else {
            ExtentITestListenerAdapter.getTest().fail("Success = " + false);
        }
        assertThat(success, equalTo(true));
    }

    public static void assertKeyValueInJson(String keyName, JsonPath jsonPath, String expectedVal,
                                            String actualJsonPath) {
        logInfo("Asserting success");
        boolean success = jsonPath.get("success");
        String jsonVal = jsonPath.get(actualJsonPath);
        if (success) {
            ExtentITestListenerAdapter.getTest().pass("Success = " + true);
        } else {
            ExtentITestListenerAdapter.getTest().fail("Success = " + false);
        }
        assertThat(success, equalTo(true));
        logInfo("Asserting Response " + keyName + " Value = " + expectedVal);
        if (jsonVal.trim().equalsIgnoreCase(expectedVal.trim())) {
            ExtentITestListenerAdapter.getTest().pass(keyName + " = " + jsonVal);
        } else {
            ExtentITestListenerAdapter.getTest().fail(keyName + " = " + jsonVal);
        }
        assertThat(jsonVal, equalTo(expectedVal));
    }

    /**
     * overloaded method
     **/
    public static void assertKeyValueInJson(String keyName, JsonPath jsonPath, boolean expectedFlag,
                                            String actualJsonPath) {
        logInfo("Asserting success");
        boolean success = jsonPath.get("success");
        boolean jsonVal = jsonPath.get(actualJsonPath);
        if (success) {
            ExtentITestListenerAdapter.getTest().pass("Success = " + true);
        } else {
            ExtentITestListenerAdapter.getTest().fail("Success = " + false);
        }
        assertThat(success, equalTo(true));
        logInfo("Asserting Response " + keyName + " Value = " + expectedFlag);
        if (jsonVal == expectedFlag) {
            ExtentITestListenerAdapter.getTest().pass(keyName + " = " + jsonVal);
        } else {
            ExtentITestListenerAdapter.getTest().fail(keyName + " = " + jsonVal);
        }
        assertThat(jsonVal, equalTo(expectedFlag));
    }
    
    public static void assertSuccess(ApiResponse apiResponse) {
        logInfo("Asserting Success = true");
        if (apiResponse.getSuccess().equals(true)) {
            ExtentITestListenerAdapter.getTest().pass("Success = " + apiResponse.getSuccess());
        } else {
            ExtentITestListenerAdapter.getTest().fail("Success = " + apiResponse.getSuccess());
        }
        assertThat(apiResponse.getSuccess(), equalTo(true));
    }



    public static void assertKeyValue(String keyName, String expectedVal, String actualVal) {
        logInfo("Asserting " + keyName);
        if (actualVal != null) {
            if (actualVal.trim().equalsIgnoreCase(expectedVal.trim())) {
                ExtentITestListenerAdapter.getTest().pass(keyName + " expected value = " + expectedVal +
                        " & actual value = " + actualVal);
            } else {
                ExtentITestListenerAdapter.getTest().fail(keyName + " expected value = " + expectedVal +
                        " & actual value =   " + actualVal);
                assertThat(0, equalTo(1));
            }
            assertThat(actualVal.toLowerCase().trim(), equalTo(expectedVal.toLowerCase().trim()));
        } else {
            if (actualVal == expectedVal) {
                ExtentITestListenerAdapter.getTest().pass(keyName + " expected value = " + expectedVal +
                        " & actual value = " + actualVal);
            } else {
                ExtentITestListenerAdapter.getTest().fail(keyName + " expected value = " + expectedVal +
                        " & actual value =   " + actualVal);
                assertThat(0, equalTo(1));
            }
        }
    }

    public static void assertKeyValue(ExtentTest test, String keyName, String expectedVal, String actualVal) {
        logInfo(test, "Asserting " + keyName);
        if (actualVal != null && expectedVal != null) {
            if (actualVal.trim().equalsIgnoreCase(expectedVal.trim())) {
                test.pass(keyName + " expected value = " + expectedVal + " & actual value = " + actualVal);
            } else {
                test.fail(keyName + " expected value = " + expectedVal + " & actual value =   " + actualVal);
            }
            assertThat(actualVal.toLowerCase().trim(), equalTo(expectedVal.toLowerCase().trim()));
        } else
            test.fail("Either or both actual & expected values are null - Actual: " + actualVal +
                    " , Expected val: " + expectedVal);
    }

    public static void assertKeyValue(String keyName, Boolean expectedVal, Boolean actualVal) {
        logInfo("Asserting " + keyName);
        if (actualVal != null && expectedVal != null) {
            if (actualVal.equals(expectedVal)) {
                ExtentITestListenerAdapter.getTest().pass(keyName + " expected value = " + expectedVal +
                        " & actual value = " + actualVal);
            } else {
                ExtentITestListenerAdapter.getTest().fail(keyName + " expected value = " + expectedVal +
                        " & actual value =   " + actualVal);
                assertThat(0, equalTo(1));
            }
        } else {
            ExtentITestListenerAdapter.getTest().fail("Either or both actual & expected values are null - Actual: " + actualVal +
                    " , Expected val: " + expectedVal);
            assertThat(0, equalTo(1));
        }
    }

    public static void assertKeyValue(String keyName, int expectedVal, int actualVal) {
        logInfo("Asserting " + keyName);
        if (actualVal == expectedVal) {
            ExtentITestListenerAdapter.getTest().pass(keyName + " expected value = " + expectedVal +
                    " & actual value = " + actualVal);
        } else {
            ExtentITestListenerAdapter.getTest().fail(keyName + " expected value = " + expectedVal +
                    " & actual value =   " + actualVal);
            assertThat(0, equalTo(1));
        }
    }
    
    public static void assertKeyValue(String keyName, double expectedVal, double actualVal) {
        logInfo("Asserting " + keyName);
        if (actualVal == expectedVal) {
            ExtentITestListenerAdapter.getTest().pass(keyName + " expected value = " + expectedVal +
                    " & actual value = " + actualVal);
        } else {
            ExtentITestListenerAdapter.getTest().fail(keyName + " expected value = " + expectedVal +
                    " & actual value =   " + actualVal);
            assertThat(0, equalTo(1));
        }
    }
    
    public static void assertNull(String keyName, Object actualVal) {
        logInfo("Asserting if" + keyName + "is null");
        if (actualVal == null) {
            ExtentITestListenerAdapter.getTest().fail(keyName + " is null on the package, exiting test");
            assertThat(0, equalTo(1));
            
        }else {
        	logInfo( keyName + " is not null");
        	
        }
    }
    
    public static void assertIfUpdated(String keyName, String expectedVal ,String  actualVal) {
        logInfo("Asserting if " + keyName + " is updated");
        if (actualVal.equalsIgnoreCase(expectedVal)) {
            ExtentITestListenerAdapter.getTest().fail(keyName + " is not updated");
            assertThat(0, equalTo(1));
        }else {
        	logInfo( keyName + " is updated");
        }
    }

    public static void assertIfNull(String keyName, Object actualVal) {
       logInfo("Asserting if " + keyName + " is null");
        if (actualVal == null || actualVal.equals("null")) {
            ExtentITestListenerAdapter.getTest().pass(keyName + " is null");
        }else {
            ExtentITestListenerAdapter.getTest().fail(keyName + " is not null");
            logInfo(keyName + " :: " + actualVal);
        }
    }

    public static void assertIfNotNull(String keyName, Object actualVal) {
        logInfo("Asserting if " + keyName + " is not null");
        if (actualVal != null) {
            ExtentITestListenerAdapter.getTest().pass(keyName + " is not null");
        }else {
            ExtentITestListenerAdapter.getTest().fail(keyName + " is null");
        }
    }

    public static void assertIfNotEmpty(String keyName, Object actualVal) {
        logInfo("Asserting if " + keyName + " is not empty");
        if (!actualVal.equals("")) {
            ExtentITestListenerAdapter.getTest().pass(keyName + " is not empty");
        }else {
            ExtentITestListenerAdapter.getTest().fail(keyName + " is empty");
        }
    }
    public static void comparePkgResponses( HQTrack stagingMasterShipmentDetails,
    		Example trackingShipmentDetails) throws JsonProcessingException {
		List<String> nonMandatorylist = Arrays.asList(
//				"cs.sd", "cs.ud", "aseg.ud", "packages[0].cd", "cs.uid", "date.cd", "date.lu", "date.mnd", "date.pd", "packages[0].od",
//				"packages[0].oid", "packages[0].pd", "pseg.ud", "packages[0].ud", "packages[0].upl", "packages[0].wbn", "packages[0]._id", "date.cit", "packages[0].ivd", "nsl[0].date",
//				"nsl[1].date", "nsl[2].date", "packages[0].pupid", "s[0].sd", "s[0].ud", "s[0].uid", "s[1].pupid", "s[1].sd", "s[1].ud", "s[1].uid",
//				"s[2].sd", "s[2].ud", "s[2].uid", "date.cpd", "diff.dipd"
//				, "", "", "", "", "",
//				"", "", "", "", "", "", "", "", "", ""
		);
		String DataOne = mapper.writeValueAsString(stagingMasterShipmentDetails);
		String DataTwo = mapper.writeValueAsString(trackingShipmentDetails);

		difference = JSONCompare.compareJSON(DataOne, DataTwo, JSONCompareMode.STRICT);

		int failureCount = 0;
		if (difference.getFieldFailures().size()>0) {
			logInfo("----------------FAILURE FIELDS----------------");
			for (FieldComparisonFailure fieldFailure : difference.getFieldFailures()) {
				if (!(hasMatchingSubstring(fieldFailure.getField(), nonMandatorylist))) {
					logFailure("Node : \"" + fieldFailure.getField() + "\" | " +
							"Expected value : \"" + fieldFailure.getExpected() + "\" | " +
							"Actual value : \"" + fieldFailure.getActual() + "\"");
					failureCount++;
				} else {
					logInfo("Node : \"" + fieldFailure.getField() + "\" | " +
							"Expected value : \"" + fieldFailure.getExpected() + "\" | " +
							"Actual value : \"" + fieldFailure.getActual() + "\"");
				}

			}
		}
		logInfo("FAILURE COUNT :: "+failureCount);

		if (difference.getFieldUnexpected().size()>0) {
			logInfo("----------------UNEXPECTED FIELDS----------------");
			for (FieldComparisonFailure fieldUnexpected : difference.getFieldUnexpected()) {
				logFailure("Node : \"" + fieldUnexpected.getField() + "\" | " +
						"Expected value : \"" + fieldUnexpected.getExpected() + "\" | " +
						"Actual value : \"" + fieldUnexpected.getActual() + "\"");
			}
		}
		logInfo("UNEXPECTED COUNT :: "+difference.getFieldUnexpected().size());

		if (difference.getFieldMissing().size()>0) {
			logInfo("----------------MISSING FIELDS----------------");
			int missingCount = 0;
			for (FieldComparisonFailure fieldMissing : difference.getFieldMissing()) {
				logFailure("Node : \"" + fieldMissing.getField() + "\" | " +
						"Expected value : \"" + fieldMissing.getExpected() + "\" | " +
						"Actual value : \"" + fieldMissing.getActual() + "\"");
			}
		}
		logInfo("MISSING COUNT :: "+difference.getFieldMissing().size());

	}
    public static void comparePkgResponses( HQTrackError stagingMasterShipmentDetails,
    		HQTrackError edtMasterShipmentDetails) throws JsonProcessingException {
		List<String> nonMandatorylist = Arrays.asList(
//				"cs.sd", "cs.ud", "aseg.ud", "packages[0].cd", "cs.uid", "date.cd", "date.lu", "date.mnd", "date.pd", "packages[0].od",
//				"packages[0].oid", "packages[0].pd", "pseg.ud", "packages[0].ud", "packages[0].upl", "packages[0].wbn", "packages[0]._id", "date.cit", "packages[0].ivd", "nsl[0].date",
//				"nsl[1].date", "nsl[2].date", "packages[0].pupid", "s[0].sd", "s[0].ud", "s[0].uid", "s[1].pupid", "s[1].sd", "s[1].ud", "s[1].uid",
//				"s[2].sd", "s[2].ud", "s[2].uid", "date.cpd", "diff.dipd"
//				, "", "", "", "", "",
//				"", "", "", "", "", "", "", "", "", ""
		);
		String DataOne = mapper.writeValueAsString(stagingMasterShipmentDetails);
		String DataTwo = mapper.writeValueAsString(edtMasterShipmentDetails);

		difference = JSONCompare.compareJSON(DataOne, DataTwo, JSONCompareMode.STRICT);

		int failureCount = 0;
		if (difference.getFieldFailures().size()>0) {
			logInfo("----------------FAILURE FIELDS----------------");
			for (FieldComparisonFailure fieldFailure : difference.getFieldFailures()) {
				if (!(hasMatchingSubstring(fieldFailure.getField(), nonMandatorylist))) {
					logFailure("Node : \"" + fieldFailure.getField() + "\" | " +
							"Expected value : \"" + fieldFailure.getExpected() + "\" | " +
							"Actual value : \"" + fieldFailure.getActual() + "\"");
					failureCount++;
				} else {
					logInfo("Node : \"" + fieldFailure.getField() + "\" | " +
							"Expected value : \"" + fieldFailure.getExpected() + "\" | " +
							"Actual value : \"" + fieldFailure.getActual() + "\"");
				}

			}
		}
		logInfo("FAILURE COUNT :: "+failureCount);

		if (difference.getFieldUnexpected().size()>0) {
			logInfo("----------------UNEXPECTED FIELDS----------------");
			for (FieldComparisonFailure fieldUnexpected : difference.getFieldUnexpected()) {
				logFailure("Node : \"" + fieldUnexpected.getField() + "\" | " +
						"Expected value : \"" + fieldUnexpected.getExpected() + "\" | " +
						"Actual value : \"" + fieldUnexpected.getActual() + "\"");
			}
		}
		logInfo("UNEXPECTED COUNT :: "+difference.getFieldUnexpected().size());

		if (difference.getFieldMissing().size()>0) {
			logInfo("----------------MISSING FIELDS----------------");
			int missingCount = 0;
			for (FieldComparisonFailure fieldMissing : difference.getFieldMissing()) {
				logFailure("Node : \"" + fieldMissing.getField() + "\" | " +
						"Expected value : \"" + fieldMissing.getExpected() + "\" | " +
						"Actual value : \"" + fieldMissing.getActual() + "\"");
			}
		}
		logInfo("MISSING COUNT :: "+difference.getFieldMissing().size());

	}
    public static void comparePkgResponses( HQTrack stagingMasterShipmentDetails,
    		HQTrack edtMasterShipmentDetails) throws JsonProcessingException {
		List<String> nonMandatorylist = Arrays.asList(
//				"cs.sd", "cs.ud", "aseg.ud", "packages[0].cd", "cs.uid", "date.cd", "date.lu", "date.mnd", "date.pd", "packages[0].od",
//				"packages[0].oid", "packages[0].pd", "pseg.ud", "packages[0].ud", "packages[0].upl", "packages[0].wbn", "packages[0]._id", "date.cit", "packages[0].ivd", "nsl[0].date",
//				"nsl[1].date", "nsl[2].date", "packages[0].pupid", "s[0].sd", "s[0].ud", "s[0].uid", "s[1].pupid", "s[1].sd", "s[1].ud", "s[1].uid",
//				"s[2].sd", "s[2].ud", "s[2].uid", "date.cpd", "diff.dipd"
//				, "", "", "", "", "",
//				"", "", "", "", "", "", "", "", "", ""
		);
		String DataOne = mapper.writeValueAsString(stagingMasterShipmentDetails);
		String DataTwo = mapper.writeValueAsString(edtMasterShipmentDetails);

		difference = JSONCompare.compareJSON(DataOne, DataTwo, JSONCompareMode.STRICT);

		int failureCount = 0;
		if (difference.getFieldFailures().size()>0) {
			logInfo("----------------FAILURE FIELDS----------------");
			for (FieldComparisonFailure fieldFailure : difference.getFieldFailures()) {
				if (!(hasMatchingSubstring(fieldFailure.getField(), nonMandatorylist))) {
					logFailure("Node : \"" + fieldFailure.getField() + "\" | " +
							"Expected value : \"" + fieldFailure.getExpected() + "\" | " +
							"Actual value : \"" + fieldFailure.getActual() + "\"");
					failureCount++;
				} else {
					logInfo("Node : \"" + fieldFailure.getField() + "\" | " +
							"Expected value : \"" + fieldFailure.getExpected() + "\" | " +
							"Actual value : \"" + fieldFailure.getActual() + "\"");
				}

			}
		}
		logInfo("FAILURE COUNT :: "+failureCount);

		if (difference.getFieldUnexpected().size()>0) {
			logInfo("----------------UNEXPECTED FIELDS----------------");
			for (FieldComparisonFailure fieldUnexpected : difference.getFieldUnexpected()) {
				logFailure("Node : \"" + fieldUnexpected.getField() + "\" | " +
						"Expected value : \"" + fieldUnexpected.getExpected() + "\" | " +
						"Actual value : \"" + fieldUnexpected.getActual() + "\"");
			}
		}
		logInfo("UNEXPECTED COUNT :: "+difference.getFieldUnexpected().size());

		if (difference.getFieldMissing().size()>0) {
			logInfo("----------------MISSING FIELDS----------------");
			int missingCount = 0;
			for (FieldComparisonFailure fieldMissing : difference.getFieldMissing()) {
				logFailure("Node : \"" + fieldMissing.getField() + "\" | " +
						"Expected value : \"" + fieldMissing.getExpected() + "\" | " +
						"Actual value : \"" + fieldMissing.getActual() + "\"");
			}
		}
		logInfo("MISSING COUNT :: "+difference.getFieldMissing().size());

	}
    public static void comparePkgResponsesError( HQTrackError stagingMasterShipmentDetails,
    		TrackError trackingShipmentDetails) throws JsonProcessingException {
		List<String> nonMandatorylist = Arrays.asList(
//				"cs.sd", "cs.ud", "aseg.ud", "packages[0].cd", "cs.uid", "date.cd", "date.lu", "date.mnd", "date.pd", "packages[0].od",
//				"packages[0].oid", "packages[0].pd", "pseg.ud", "packages[0].ud", "packages[0].upl", "packages[0].wbn", "packages[0]._id", "date.cit", "packages[0].ivd", "nsl[0].date",
//				"nsl[1].date", "nsl[2].date", "packages[0].pupid", "s[0].sd", "s[0].ud", "s[0].uid", "s[1].pupid", "s[1].sd", "s[1].ud", "s[1].uid",
//				"s[2].sd", "s[2].ud", "s[2].uid", "date.cpd", "diff.dipd"
//				, "", "", "", "", "",
//				"", "", "", "", "", "", "", "", "", ""
		);
		String DataOne = mapper.writeValueAsString(stagingMasterShipmentDetails);
		String DataTwo = mapper.writeValueAsString(trackingShipmentDetails);

		difference = JSONCompare.compareJSON(DataOne, DataTwo, JSONCompareMode.STRICT);

		int failureCount = 0;
		if (difference.getFieldFailures().size()>0) {
			logInfo("----------------FAILURE FIELDS----------------");
			for (FieldComparisonFailure fieldFailure : difference.getFieldFailures()) {
				if (!(hasMatchingSubstring(fieldFailure.getField(), nonMandatorylist))) {
					logFailure("Node : \"" + fieldFailure.getField() + "\" | " +
							"Expected value : \"" + fieldFailure.getExpected() + "\" | " +
							"Actual value : \"" + fieldFailure.getActual() + "\"");
					failureCount++;
				} else {
					logInfo("Node : \"" + fieldFailure.getField() + "\" | " +
							"Expected value : \"" + fieldFailure.getExpected() + "\" | " +
							"Actual value : \"" + fieldFailure.getActual() + "\"");
				}

			}
		}
		logInfo("FAILURE COUNT :: "+failureCount);

		if (difference.getFieldUnexpected().size()>0) {
			logInfo("----------------UNEXPECTED FIELDS----------------");
			for (FieldComparisonFailure fieldUnexpected : difference.getFieldUnexpected()) {
				logFailure("Node : \"" + fieldUnexpected.getField() + "\" | " +
						"Expected value : \"" + fieldUnexpected.getExpected() + "\" | " +
						"Actual value : \"" + fieldUnexpected.getActual() + "\"");
			}
		}
		logInfo("UNEXPECTED COUNT :: "+difference.getFieldUnexpected().size());

		if (difference.getFieldMissing().size()>0) {
			logInfo("----------------MISSING FIELDS----------------");
			int missingCount = 0;
			for (FieldComparisonFailure fieldMissing : difference.getFieldMissing()) {
				logFailure("Node : \"" + fieldMissing.getField() + "\" | " +
						"Expected value : \"" + fieldMissing.getExpected() + "\" | " +
						"Actual value : \"" + fieldMissing.getActual() + "\"");
			}
		}
		logInfo("MISSING COUNT :: "+difference.getFieldMissing().size());

	}

    public static void assertIfNotNull(String keyName, Object actualVal, String message) {
        logInfo("Asserting if " + keyName + " is not null");
        if (actualVal != null) {
            ExtentITestListenerAdapter.getTest().pass(keyName + " is not null");
            ExtentITestListenerAdapter.getTest().info(message + " | "+keyName + ": "+actualVal);
        }else {
            ExtentITestListenerAdapter.getTest().fail(keyName + " is null");
        }
    }


    public static void compareJSON(String expectedJson, String actualJson) throws JsonProcessingException {
        List<String> nonMandatorylist = Arrays.asList(
//				"", "", "", "", "", "", "", "", "", ""
        );

        logInfo("Expected api response: "+expectedJson);
        logInfo("Actual api response: "+actualJson);
        difference = JSONCompare.compareJSON(expectedJson, actualJson, JSONCompareMode.STRICT);

        int failureCount = 0;
        if (difference.getFieldFailures().size()>0) {
            logInfo("----------------FAILURE FIELDS----------------");
            for (FieldComparisonFailure fieldFailure : difference.getFieldFailures()) {
                if (!nonMandatorylist.contains(fieldFailure.getField())) {
                    logFailure("Node : \"" + fieldFailure.getField() + "\" | " +
                            "Expected value : \"" + fieldFailure.getExpected() + "\" | " +
                            "Actual value : \"" + fieldFailure.getActual() + "\"");
                    failureCount++;
                } else {
                    logInfo("Node : \"" + fieldFailure.getField() + "\" | " +
                            "Expected value : \"" + fieldFailure.getExpected() + "\" | " +
                            "Actual value : \"" + fieldFailure.getActual() + "\"");
                }

            }
        }
        logInfo("FAILURE COUNT :: "+failureCount);

    }

	

	
}

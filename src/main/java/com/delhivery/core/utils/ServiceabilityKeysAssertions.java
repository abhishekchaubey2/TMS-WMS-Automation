package com.delhivery.core.utils;

import static com.delhivery.core.utils.Assertions.assertKeyValue;
import static com.delhivery.core.utils.Utilities.*;
import static org.testng.Assert.assertEquals;

import java.util.*;

import com.delhivery.Express.pojo.FetchPackageDetails.response.PackageDetail;
import com.delhivery.Express.pojo.FetchPackageDetailsSecond.response.PackageDetail2;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.StringUtils;
import org.skyscreamer.jsonassert.*;

public class ServiceabilityKeysAssertions {

	static JSONCompareResult difference;
	static ObjectMapper mapper = new ObjectMapper();

	//only for printing scenarios dataprovider value of stage shipments
	static String dataProviderValues = "";

	public static void AssertProhibhitedCategoryServiceabilityKeys(PackageDetail pkgdetails,
			HashMap<String, String> expected_val) {

		
		assertKeyValue("cn", expected_val.get("cn"), pkgdetails.cn);

		assertKeyValue("cnid", expected_val.get("cnid"), pkgdetails.cnid);

		if (expected_val.get("rgn") == null) {
			Assertions.assertIfNull("rgn", pkgdetails.rgn);
		} else {
			assertKeyValue("rgn", expected_val.get("rgn"), pkgdetails.rgn);
		}

		assertKeyValue("sc", expected_val.get("sc"), pkgdetails.sc);
		assertKeyValue("ocid", expected_val.get("ocid"), pkgdetails.ocid);

		if (expected_val.get("srv").equalsIgnoreCase("not present")) {
			// do nothing
		} else {
			assertKeyValue("srv", Boolean.valueOf(expected_val.get("srv")), 
					pkgdetails.flags.srv);
		}
		
		
		if (expected_val.get("wvcid") == null) {
			//if null
			Assertions.assertIfNull("wvcid", pkgdetails.wvcid);
			
		} else if (!expected_val.get("wvcid").equalsIgnoreCase("not present")) {
			
		} else {
			assertKeyValue("wvcid", expected_val.get("ocid"), pkgdetails.wvcid);
		}

		if (expected_val.get("cnc") == null) {
			Assertions.assertIfNull("cnc", pkgdetails.cnc);
		} else {
			assertKeyValue("cnc", expected_val.get("cnc"), pkgdetails.cnc);
		}
		
		if (expected_val.get("cpdt") == null) {
			Assertions.assertIfNull("cpdt", pkgdetails.cpdt);
		} else {
			assertKeyValue("cpdt", expected_val.get("cpdt"), pkgdetails.cpdt);
		}

		

		// need to add assertions for wvcid, pdd , edd, flag.is_ewbn_req

	}

	public static void assertProhibhitedCategoryServiceabilityKeys(PackageDetail pkgdetails,
																   HashMap<String, String> expected_val) {
		assertKeyValue("cn", expected_val.get("cn"), pkgdetails.cn);
//		assertKeyValue("cn1", expected_val.get("cn"), pkgdetails.packages.get(0).cn1);
		assertKeyValue("cnid", expected_val.get("cnid"), pkgdetails.cnid);
		if (expected_val.get("dpc") == null) {
			Assertions.assertIfNull("dpc", pkgdetails.dpc);
		} else {
			assertKeyValue("dpc", expected_val.get("dpc"), pkgdetails.dpc.toString());
		}
//		assertKeyValue("dpc", expected_val.get("dpc"), pkgdetails.packages.get(0).dpc.toString());

		if (expected_val.get("dpcid") == null) {
			Assertions.assertIfNull("dpcid", pkgdetails.dpcid);
		} else {
			assertKeyValue("dpcid", expected_val.get("dpcid"), pkgdetails.dpcid.toString());
		}
//		assertKeyValue("dpcid", expected_val.get("dpcid"), pkgdetails.packages.get(0).dpcid.toString());

		if (expected_val.get("cnc") == null) {
			Assertions.assertIfNull("cnc", pkgdetails.cnc);
		} else {
			assertKeyValue("cnc", expected_val.get("cnc"), pkgdetails.cnc);
		}
//		assertKeyValue("cnc", expected_val.get("cnc"), pkgdetails.packages.get(0).cnc);

		if (expected_val.get("cns") == null) {
			Assertions.assertIfNull("cns", pkgdetails.cns);
		} else {
			assertKeyValue("cns", expected_val.get("cns"), pkgdetails.cns);
		}
//		assertKeyValue("cns", expected_val.get("cnid"), pkgdetails.packages.get(0).cns);

		assertKeyValue("cs.nsl", expected_val.get("cs.nsl"), pkgdetails.cs.nsl);
		assertKeyValue("cs.st", expected_val.get("cs.st"), pkgdetails.cs.st);
		assertKeyValue("cs.sr", expected_val.get("cs.sr"), pkgdetails.cs.sr);


	}


	public static void compareEdtStagingShipment(PackageDetail stagePkgDetails, PackageDetail edtPkgDetails) throws JsonProcessingException {
		List<String> nonMandatorylist = Arrays.asList(
				"cs.sd", "cs.ud", "aseg.ud", "cd", "cs.uid", "date.cd", "date.lu", "date.mnd", "date.pd", "od",
				"oid", "pd", "pseg.ud", "ud", "upl", "wbn", "_id", "date.cit", "ivd", "nsl[0].date",
				"nsl[1].date", "nsl[2].date", "pupid", "s[0].sd", "s[0].ud", "s[0].uid", "s[1].pupid", "s[1].sd", "s[1].ud", "s[1].uid",
				"s[2].sd", "s[2].ud", "s[2].uid", "date.cpd", "diff.dipd", "uan.error_radius", "uan.lat", "uan.long"
//				"", "", "", "", "", "", "", "", "", ""
//				"", "", "", "", "", "", "", "", "", ""
		);
		List<String> ignoreUnexpectedKeys = Arrays.asList(
				"cn1", "cns", "rcn1", "rcns"
//				"", "", "", "", "", ""
//				"", "", "", "", "", "", "", "", "", ""
		);
		String stageData = mapper.writeValueAsString(stagePkgDetails);
		String edtData = mapper.writeValueAsString(edtPkgDetails);

		difference = JSONCompare.compareJSON(stageData, edtData, JSONCompareMode.STRICT);

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

		int unexpectedCount = 0;
		if (difference.getFieldUnexpected().size()>0) {
			logInfo("----------------UNEXPECTED FIELDS----------------");
			for (FieldComparisonFailure fieldUnexpected : difference.getFieldUnexpected()) {
				if (!ignoreUnexpectedKeys.contains(fieldUnexpected.getActual())) {
					if (!fieldUnexpected.getField().equals("")){
						logFailure("Key : \"" + fieldUnexpected.getActual() + "\" was not expected on edt package response");
					} else {
						logFailure("Node : \"" + fieldUnexpected.getField() + "\" | " +
								"Expected value : \"" + fieldUnexpected.getExpected() + "\" | " +
								"Actual value : \"" + fieldUnexpected.getActual() + "\"");
					}
					unexpectedCount++;
				} else {
					if (!fieldUnexpected.getField().equals("")){
						logInfo("Key : \"" + fieldUnexpected.getActual() + "\" was not expected on edt package response");
					} else {
						logInfo("Node : \"" + fieldUnexpected.getField() + "\" | " +
								"Expected value : \"" + fieldUnexpected.getExpected() + "\" | " +
								"Actual value : \"" + fieldUnexpected.getActual() + "\"");
					}
				}
			}
		}
//		logInfo("UNEXPECTED COUNT :: "+difference.getFieldUnexpected().size());
		logInfo("UNEXPECTED COUNT :: "+unexpectedCount);

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

	public static void assertServiceabilityKeys(PackageDetail pkgdetails,
												LinkedHashMap<String, String> expected_val) {

		//only for printing scenarios dataprovider value of stage shipments
		dataProviderValues = "";

		expected_val.forEach((key, value) -> {
			switch (key){
				case "cn":
					assertNodeValue(key, value, pkgdetails.cn);
					break;

				case "cn1":
					assertNodeValue(key, pkgdetails.cn1); //on edt for verification it should be cn1 key
					break;

				case "cnid":
					assertNodeValue(key, value, pkgdetails.cnid);
					break;

				case "dpc":
					if(pkgdetails.dpc == null) {
						assertNodeValue(key, value, null);
					}else {
						try {
							assertNodeValue(key, value, Utilities.jsonObjectToString(pkgdetails.dpc).replace("\"", ""));
						} catch (JsonProcessingException e) {
							throw new RuntimeException(e);
						}
					}
					break;

				case "dpcid":
					if(pkgdetails.dpcid == null) {
						assertNodeValue(key, value, null);
					}else {
						try {
							assertNodeValue(key, value, Utilities.jsonObjectToString(pkgdetails.dpcid).replace("\"", ""));
						} catch (JsonProcessingException e) {
							throw new RuntimeException(e);
						}
					}
					break;

				case "cnc":
					assertNodeValue(key, value, pkgdetails.cnc);
					break;

				case "cns":
					assertNodeValue(key, pkgdetails.cns);
					break;

				case "cs.nsl":
					assertNodeValue(key, pkgdetails.cs.nsl);
					break;

				case "cs.st":
					assertNodeValue(key, pkgdetails.cs.st);
					break;

				case "cs.sr":
					assertNodeValue(key, pkgdetails.cs.sr);
					break;

				case "cpdt":
					assertNodeValue(key, value, pkgdetails.cpdt);
					break;

				case "rgn":
					assertNodeValue(key, value, pkgdetails.rgn);
					break;

				case "sc":
					assertNodeValue(key, value, pkgdetails.sc);
					break;

				case "srv":
					assertNodeValue(key, value, String.valueOf(pkgdetails.flags.srv));
					break;

				case "ocid":
					assertNodeValue(key, value, pkgdetails.ocid);
					break;

				case "wvcid":
					assertNodeValue(key, value, pkgdetails.wvcid);
					break;

				case "rcn":
					assertNodeValue(key, value, pkgdetails.rcn);
					break;

				case "rcn1":
					assertNodeValue(key, pkgdetails.rcn1);
					break;

				case "rcnid":
					assertNodeValue(key, pkgdetails.rcnid);
					break;

				case "rdpc":
					if(pkgdetails.rdpc == null) {
						assertNodeValue(key, null);
					}else {
						try {
							assertNodeValue(key, Utilities.jsonObjectToString(pkgdetails.rdpc).replace("\"",""));
						} catch (JsonProcessingException e) {
							throw new RuntimeException(e);
						}
					}
					break;

				case "rdpcid":
					if(pkgdetails.rdpcid == null) {
						assertNodeValue(key, null);
					}else {
						try {
							assertNodeValue(key, Utilities.jsonObjectToString(pkgdetails.rdpcid).replace("\"",""));
						} catch (JsonProcessingException e) {
							throw new RuntimeException(e);
						}
					}
					break;

				case "rcty":
					assertNodeValue(key, pkgdetails.rcty);
					break;

				case "rcns":
					assertNodeValue(key, pkgdetails.rcns);
					break;
			}
		});

		//only for printing scenarios dataprovider value of stage shipments
		logInfo(", " + StringUtils.chop(dataProviderValues.trim()));

	}
	
	public static void assertServiceabilityKeys2(PackageDetail2 pkgdetails,
			LinkedHashMap<String, String> expected_val) {

		//only for printing scenarios dataprovider value of stage shipments
//		dataProviderValues = "";

		expected_val.forEach((key, value) -> {
			switch (key) {
			case "cn":
				assertNodeValue(key, value, pkgdetails.cn);
				break;

			case "cn1":
				assertNodeValue(key, value, pkgdetails.cn1); // on edt for verification it should be cn1 key
				break;

			case "cnid":
				assertNodeValue(key, value, pkgdetails.cnid);
				break;

			case "dpc":
				if (pkgdetails.dpc == null) {
					assertNodeValue(key, value, null);
				} else {
					try {
						assertNodeValue(key, value, Utilities.jsonObjectToString(pkgdetails.dpc).replace("\"", ""));
					} catch (JsonProcessingException e) {
						throw new RuntimeException(e);
					}
				}
				break;

			case "dpcid":
				if (pkgdetails.dpcid == null) {
					assertNodeValue(key, value, null);
				} else {
					try {
						assertNodeValue(key, value, Utilities.jsonObjectToString(pkgdetails.dpcid).replace("\"", ""));
					} catch (JsonProcessingException e) {
						throw new RuntimeException(e);
					}
				}
				break;

			case "cnc":
				assertNodeValue(key, value, pkgdetails.cnc);
				break;

			case "cns":
				assertNodeValue(key, value, pkgdetails.cns);
				break;

			case "cs.nsl":
				assertNodeValue(key, value, pkgdetails.cs.nsl);
				break;

			case "cs.st":
				assertNodeValue(key, value, pkgdetails.cs.st);
				break;

			case "cs.sr":
				assertNodeValue(key, value, pkgdetails.cs.sr);
				break;

			case "cpdt":
				assertNodeValue(key, value, pkgdetails.cpdt);
				break;

			case "rgn":
				assertNodeValue(key, value, pkgdetails.rgn);
				break;

			case "sc":
				assertNodeValue(key, value, pkgdetails.sc);
				break;

			case "srv":
				assertNodeValue(key, value, String.valueOf(pkgdetails.flags.srv));
				break;

			case "ocid":
				assertNodeValue(key, value, pkgdetails.ocid);
				break;

			case "wvcid":
				assertNodeValue(key, value, pkgdetails.wvcid);
				break;

			case "rcn":
				assertNodeValue(key, value, pkgdetails.rcn);
				break;

			case "rcn1":
				assertNodeValue(key, value, pkgdetails.rcn1);
				break;

			case "rcnid":
				assertNodeValue(key, value, pkgdetails.rcnid);
				break;

			case "rdpc":
				if(pkgdetails.rdpc == null) {
					assertNodeValue(key, value, null);
				}else {
					try {
						assertNodeValue(key, value, Utilities.jsonObjectToString(pkgdetails.rdpc).replace("\"", ""));
					} catch (JsonProcessingException e) {
						throw new RuntimeException(e);
					}
				}
				break;

			case "rdpcid":
				if(pkgdetails.rdpcid == null) {
					assertNodeValue(key, value, null);
				}else {
					try {
						assertNodeValue(key, value, Utilities.jsonObjectToString(pkgdetails.rdpcid).replace("\"", ""));
					} catch (JsonProcessingException e) {
						throw new RuntimeException(e);
					}
				}
				break;

			case "rcty":
				assertNodeValue(key, value, pkgdetails.rcty);
				break;

			case "rcns":
				assertNodeValue(key, value, pkgdetails.rcns);
				break;
			}
		});

		//only for printing scenarios dataprovider value of stage shipments
//		logInfo(", " + StringUtils.chop(dataProviderValues.trim()));

	}

	public static void assertNodeValue(String keyName, String expectedVal, String actualVal){

		if (expectedVal == null || expectedVal.equals("null")|| expectedVal.equalsIgnoreCase("null")) {
			Assertions.assertIfNull(keyName, actualVal);
		} else {
			assertKeyValue(keyName, expectedVal, actualVal);
		}

//		only for printing scenarios dataprovider value of stage shipments
		logInfo(keyName + " :: " + actualVal);
		dataProviderValues = dataProviderValues + "\"" +actualVal + "\", ";

	}

	public static void assertNodeValue(String keyName, String actualVal){

//		only for printing scenarios dataprovider value of stage shipments
		logInfo(keyName + " :: " + actualVal);
		dataProviderValues = dataProviderValues + "\"" +actualVal + "\", ";

	}

}
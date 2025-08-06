package com.delhivery.core.utils;

import static com.delhivery.core.utils.Utilities.logFailure;
import static com.delhivery.core.utils.Utilities.logInfo;

import java.util.Arrays;
import java.util.List;

import com.delhivery.Express.pojo.FetchPackageDetails2.response.PackageDetailNew;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.skyscreamer.jsonassert.*;

import com.delhivery.Express.pojo.FetchPackageDetails.response.PackageDetail;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ManifestationKeysAssertions {
	static JSONCompareResult difference;
	static ObjectMapper mapper = new ObjectMapper().setSerializationInclusion(JsonInclude.Include.ALWAYS);;
	//mapper to include all null values while writaValueAsString

	public static void compareNewServiceStagingShipment(PackageDetailNew stagePkgDetails, PackageDetailNew NewServicePkgDetails) throws JsonProcessingException {
		List<String> nonMandatorylist = Arrays.asList(
				"cs.sd", "cs.ud", "aseg.ud", "cd", "cs.uid", "date.cd", "date.lu", "date.mnd", "date.pd", "od",
				"oid", "pd", "pseg.ud", "ud", "upl", "wbn", "_id", "date.cit", "ivd", "nsl[0].date",
				"nsl[1].date", "nsl[2].date", "pupid", "s[0].sd", "s[0].ud", "s[0].uid", "s[1].pupid", "s[1].sd", "s[1].ud", "s[1].uid",
				"s[2].sd", "s[2].ud", "s[2].uid", "date.cpd", "diff.dipd", "uan.error_radius", "uan.lat", "uan.long"
//				"", "", "", "", "", "", "", "", "", ""
//				"", "", "", "", "", "", "", "", "", ""
		);
		List<String> ignoreUnexpectedKeys = Arrays.asList(
				"cn1", "cns", "rcn1", "rcns", "ar", "nofly"

//				"", "", "", "", "", ""
//				"", "", "", "", "", "", "", "", "", ""
		);
		String stageData = mapper.writeValueAsString(stagePkgDetails);
		String NewServiceData = mapper.writeValueAsString(NewServicePkgDetails);
		//stageData=stageData.replaceAll("null", "NULL");
		//NewServiceData=NewServiceData.replaceAll("null", "\"NULL\"");
		//print stage and New Service data
		//logInfo("stageData : " + stageData);
		//logInfo("NewServiceData : " + NewServiceData);
		
		//Object stageData1 = mapper.readValue(stagePkgDetails., stagePkgDetails.class);
		//Object NewServiceData1 = mapper.writeValueAsString(NewServicePkgDetails);
		//JSONAssert.assertEquals(stageData, NewServiceData, JSONCompareMode.STRICT);

		difference = JSONCompare.compareJSON(stageData, NewServiceData, JSONCompareMode.STRICT);

		int failureCount = 0;
		if (difference.getFieldFailures().size()>0) {
			logInfo("----------------FAILURE FIELDS----------------");
			for (FieldComparisonFailure fieldFailure : difference.getFieldFailures()) {
				if (!nonMandatorylist.contains(fieldFailure.getField())) {
					logFailure("Node : " + fieldFailure.getField() + " | " +
							"Expected value : " + fieldFailure.getExpected() + " | " +
							"Actual value : " + fieldFailure.getActual() + "");
					failureCount++;
				} else {
					logInfo("Node : " + fieldFailure.getField() + "| " +
							"Expected value : " + fieldFailure.getExpected() + " | " +
							"Actual value : " + fieldFailure.getActual() + "");
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
						logFailure("Key : \"" + fieldUnexpected.getActual() + "\" was not expected on New Service package response");
					} else {
						logFailure("Node : \"" + fieldUnexpected.getField() + "\" | " +
								"Expected value : \"" + fieldUnexpected.getExpected() + "\" | " +
								"Actual value : \"" + fieldUnexpected.getActual() + "\"");
					}
					unexpectedCount++;
				} else {
					if (!fieldUnexpected.getField().equals("")){
						logInfo("Key : \"" + fieldUnexpected.getActual() + "\" was not expected on New Service package response");
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
	public static void compareNewServiceStagingAPIResponse(String stagePkgDetails, String NewServicePkgDetails) throws JsonProcessingException {
		List<String> nonMandatorylist = Arrays.asList(
				"packages[0].refnum", "packages[0].waybill", "upload_wbn", "cd", "suc[0].oid",  "suc[0].wbn", "ud",
				"upl.pd", "w[0]", "wbn", "c_data", "fail[0].oid", "s", "c.paid", "fail[0].rmk", "fail[0].wbn", "suc[0].rmk", "fail[0].rmk[0][0]", "suc[0].rmk"

//				"", "", "", "", "", "", "", "", "", ""
//				"", "", "", "", "", "", "", "", "", ""
		);
		List<String> ignoreUnexpectedKeys = Arrays.asList(
				
//				"", "", "", "", "", ""
//				"", "", "", "", "", "", "", "", "", ""
		);
		

		difference = JSONCompare.compareJSON(stagePkgDetails, NewServicePkgDetails, JSONCompareMode.STRICT);

		int failureCount = 0;
		if (difference.getFieldFailures().size()>0) {
			logInfo("----------------FAILURE FIELDS----------------");
			for (FieldComparisonFailure fieldFailure : difference.getFieldFailures()) {
				if (!nonMandatorylist.contains(fieldFailure.getField())) {
					logFailure("Node : \"" + fieldFailure.getField() + "\" | " +
							"Expected value : " + fieldFailure.getExpected() + " | " +
							"Actual value : " + fieldFailure.getActual() + "");
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
						logFailure("Key : \"" + fieldUnexpected.getActual() + "\" was not expected on New Service package response");
					} else {
						logFailure("Node : \"" + fieldUnexpected.getField() + "\" | " +
								"Expected value : \"" + fieldUnexpected.getExpected() + "\" | " +
								"Actual value : \"" + fieldUnexpected.getActual() + "\"");
					}
					unexpectedCount++;
				} else {
					if (!fieldUnexpected.getField().equals("")){
						logInfo("Key : \"" + fieldUnexpected.getActual() + "\" was not expected on New Service package response");
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

}

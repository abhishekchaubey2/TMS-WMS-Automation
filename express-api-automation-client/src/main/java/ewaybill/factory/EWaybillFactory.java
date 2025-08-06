package ewaybill.factory;

import common.template.ExpressHttpReqObject;
import common.utils.ExpressDateTimeUtility;
import common.utils.ExpressUtilities;
import ewaybill.auth.GovtAuthentication;
import ewaybill.common.constants.EWaybillConstant;
import ewaybill.common.enums.ErrorCode;
import ewaybill.encryption.EncryptionService;
import ewaybill.payload.EWaybillPayloadTemplate;
import io.restassured.response.Response;
import org.json.JSONObject;
import rest.ExpressRestResource;

import java.util.ArrayList;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * This class is used to generate EWaybill.
 * It uses the EWaybillPayloadTemplate to get the payload and then encrypts it using the Government's public key.
 * It then sends the encrypted payload to the Government's server to generate the EWaybill.
 * The response is then decrypted using the Government's private key.
 * @author Manmohan
 * @version 1.0
 * @since 2024-09-20
 */
public class EWaybillFactory {
    static Map<String,Object> auth = GovtAuthentication.governmentAuthenticate();

    private static Map<String, String> getGenerateEWaybillHeader() {
        Map<String, String> header = new HashMap<>();
        header.put("client-id", "AAPCS06TXPXRRGY");
        header.put("client-secret", "kYym3SGg7uA9OocHVKj0");
        header.put("gstin", "06AAPCS9575E1ZR");
        header.put("authtoken", auth.get("auth_token").toString());
        return header;
    }

    private static String generateEWaybill() {
        Map<String, Object> tempPayload = EWaybillPayloadTemplate.getStaticPayload();
        tempPayload.put("docNo", ExpressDateTimeUtility.getModularCurrentTimeStamp());

        Map<String, Object> payload = new HashMap<>();
        payload.put("action", "GENEWAYBILL");
        System.out.println("Dek before calling: " + auth.get("dec_sek").toString());
        payload.put("data", EncryptionService.aesEncrypt((auth.get("dec_sek").toString()), ExpressUtilities.jsonObjectToString(tempPayload)));
        String jsonPayload = ExpressUtilities.jsonObjectToString(payload);

        ExpressHttpReqObject httpTemplateObject = ExpressHttpReqObject.builder()
                .url(EWaybillConstant.E_WBN_BASE_URL)
                .requestBody(jsonPayload)
                .isHeaderDecorationRequired(false)
                .headers(getGenerateEWaybillHeader())
                .build();

        System.out.println("Payload before calling: " + jsonPayload);
        Response response = ExpressRestResource.post("/ewaybillapi/v1.03/ewayapi/", httpTemplateObject);
        String responseJson = response.asString();
        System.out.println("create_res is :::::::>>>>>>  " + responseJson);
        JSONObject createResJson = new JSONObject(responseJson);
        System.out.println("Resp before decode " + createResJson);

        try {
            if ("1".equals(createResJson.getString("status"))) {
                String decryptedData = EncryptionService.decodeAndDecryptAES(
                        createResJson.getString("data"),auth.get("dec_sek").toString());
                System.out.println("dec data " + decryptedData);
                JSONObject respData = new JSONObject(decryptedData);
                long eWayBillNo = respData.getLong("ewayBillNo");
                System.out.println("Eway Bill No: " + eWayBillNo);

                // If you want to return the eWayBillNo as a String
                return Long.toString(eWayBillNo);
            } else {
                String errorCodesEncoded = createResJson.optString("error", "e30=");
                String errorCodesJson = new String(Base64.getDecoder().decode(errorCodesEncoded), "UTF-8");
                String[] errorCodes = new JSONObject(errorCodesJson).optString("errorCodes", "").split(",");

                for(String errorCode : errorCodes) {
                    System.out.println("We got error message : " + ErrorCode.getMessageByCode(Integer.parseInt(errorCode))+" for error code "+errorCode +" please contact HQ QA Team");
                }
            }
        } catch (Exception e) {
            throw new RuntimeException("We Faced Error: " + e);
        }
        throw new RuntimeException("Unable to generate EWaybill Please contact HQ QA Team !!!!!!!");
    }

    // This method is used to generate EWaybills for a given number of EWaybills
    public static List<String> getEWaybill(int noOfEWaybills) {
        ThreadLocal<List<String>> eWbnList = ThreadLocal.withInitial(ArrayList::new);
        System.out.println("Generating EWaybills for Thread "+Thread.currentThread().getName());
        for (int count = 0; count < noOfEWaybills; count++) {
            eWbnList.get().add(generateEWaybill());
            ExpressUtilities.hardWait(1);
            System.out.println("\n===============================================================");
            System.out.println("EWaybills: " + eWbnList.get()+ " For Thread "+Thread.currentThread().getName());
            System.out.println("\n===============================================================");
        }
        System.out.println("EWaybills: " + eWbnList.get()+ " For Thread "+Thread.currentThread().getName());
        return eWbnList.get();
    }
}

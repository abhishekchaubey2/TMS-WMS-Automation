package com.delhivery.ewaybill.auth;

import com.delhivery.core.api.RestResource;
import com.delhivery.core.utils.HttpTemplateObject;
import com.delhivery.core.utils.Utilities;
import com.delhivery.ewaybill.common.constants.EWaybillConstant;
import com.delhivery.ewaybill.encryption.EncryptionService;
import com.delhivery.ewaybill.payload.EWaybillPayloadTemplate;
import io.restassured.response.Response;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

public class GovtAuthentication {
    public static Map<String,Object> getDefaultGovtAuthenticationPayload(){
        Map<String,Object> govtAuthMap = new HashMap<>();
        govtAuthMap.put("auth_token","uDqGZcX3seVbVJrHI9O4VHee7");
        govtAuthMap.put("app_key","kytlcmkadxzpjpcawsrnisnrieuaopjh");
        govtAuthMap.put("sek","jeA/o1iIWXQsFCCeAnkehiWxITmavyHCL4NGOm5mduLWl+aHjJdtfj+K5LFE05TS");
        govtAuthMap.put("dec_sek","bjCLAOYNjtEzkaJbPu5dTsRZLQSAzfnTxIiS1pPTc3E=");
        return govtAuthMap;
    }

    private static Map<String,Object> updateDefaultGovtAuthenticationPayload(String authToken, String sek){
        Map<String,Object> govtAuthMap = new HashMap<>();
        govtAuthMap.put("auth_token",authToken);
        govtAuthMap.put("sek",sek);
        govtAuthMap.put("dec_sek", EncryptionService.aesDecrypt(EWaybillPayloadTemplate.nonEncryptedAppKey,sek));
        govtAuthMap.put("app_key", EWaybillPayloadTemplate.samplePayload.get("App_key"));

        System.out.println("Updated Govt Auth Map: "+govtAuthMap);

        return govtAuthMap;
    }

    private static Map<String,String> getGovtAuthenticationHeader(){
        Map<String,String> header = new HashMap<>();
        header.put("client-id","AAPCS06TXPXRRGY");
        header.put("client-secret","kYym3SGg7uA9OocHVKj0");
        header.put("gstin","06AAPCS9575E1ZR");
        return header;
    }

    public static Map<String, String> process(Map<String, Object> data) {
        System.out.println("Process Request and data should be equal to sample payload : " + data);
        String dataStr = Utilities.getJsonString(data);
        String encodedData = Base64.getEncoder().encodeToString(dataStr.getBytes(StandardCharsets.UTF_8));
        String encryptedData = EncryptionService.rsaEncryptV2Changed(encodedData);
        Map<String, String> response = new HashMap<>();
        response.put("Data", encryptedData);
        System.out.println("Process Response: " + response);

        return response;
    }

    public static Map<String,Object> governmentAuthenticate(){
        Object payload = process(EWaybillPayloadTemplate.getSamplePayload());
        System.out.println("Payload: "+payload);
        payload = Utilities.getJsonString(payload);
        System.out.println("Payload: "+payload);

        HttpTemplateObject httpTemplateObject = HttpTemplateObject.builder()
                .url(EWaybillConstant.E_WBN_BASE_URL)
                .requestBody(payload)
                .isHeaderDecorationRequired(false)
                .headers(getGovtAuthenticationHeader())
                .build();

        Response response = RestResource.post("/ewaybillapi/v1.03/auth/",httpTemplateObject);
        return updateDefaultGovtAuthenticationPayload(response.jsonPath().get("authtoken"),response.jsonPath().get("sek"));
    }
}

package com.delhivery.core.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Configuration Manager for handling application properties
 */
public class ConfigLoader {
    private static ConfigLoader instance;
    private Properties properties;
    
    private ConfigLoader() {
        loadProperties();
    }
    
    public static ConfigLoader getInstance() {
        if (instance == null) {
            synchronized (ConfigLoader.class) {
                if (instance == null) {
                    instance = new ConfigLoader();
                }
            }
        }
        return instance;
    }
    
    private void loadProperties() {
        properties = new Properties();
        String env = System.getProperty("env");
        env = (env == null) ? "qa" : env; // Default to qa
        
        try (InputStream inputStream = getClass().getClassLoader()
                .getResourceAsStream(env + ".properties")) {
            if (inputStream != null) {
                properties.load(inputStream);
            } else {
                throw new RuntimeException(env + ".properties file not found in classpath");
            }
        } catch (IOException e) {
            throw new RuntimeException("Error loading " + env + ".properties", e);
        }
    }
    
    public String getProperty(String key) {
        return properties.getProperty(key);
    }
    
    public String getProperty(String key, String defaultValue) {
        return properties.getProperty(key, defaultValue);
    }
    
    public int getIntProperty(String key) {
        return Integer.parseInt(properties.getProperty(key));
    }
    
    public int getIntProperty(String key, int defaultValue) {
        String value = properties.getProperty(key);
        return value != null ? Integer.parseInt(value) : defaultValue;
    }
    
    public boolean getBooleanProperty(String key) {
        return Boolean.parseBoolean(properties.getProperty(key));
    }
    
    public boolean getBooleanProperty(String key, boolean defaultValue) {
        String value = properties.getProperty(key);
        return value != null ? Boolean.parseBoolean(value) : defaultValue;
    }
    
    // Method to update a property dynamically
    public void setProperty(String key, String value) {
        properties.setProperty(key, value);
    }
    
    // Specific getters for common properties
    public String getBaseUrl() {
        return getProperty("base.url", getProperty("baseUrl"));
    }
    
    public String getApiVersion() {
        return getProperty("api.version");
    }
    
    public int getRequestTimeout() {
        return getIntProperty("timeout.request", 30000);
    }
    
    public int getResponseTimeout() {
        return getIntProperty("timeout.response", 30000);
    }
    
    // Authentication token getters
    public String getXCoreosAccess() {
        return getProperty("auth.x-coreos-access");
    }
    
    public String getXCoreosAuth() {
        return getProperty("auth.x-coreos-auth");
    }
    
    public String getXTmsDataAccess() {
        return getProperty("auth.x-tms-data-access");
    }
    
    public String getXCoreosTid() {
        return getProperty("auth.x-coreos-tid");
    }
    
    public String getXCoreosUserinfo() {
        return getProperty("auth.x-coreos-userinfo");
    }
    
    public String getFixedRequestId() {
        return getProperty("fixed.request.id");
    }
    
    // Header getters
    public String getAcceptHeader() {
        return getProperty("header.accept");
    }
    
    public String getContentTypeHeader() {
        return getProperty("header.content-type");
    }
    
    public String getUserAgentHeader() {
        return getProperty("header.user-agent");
    }
    
    public String getOriginHeader() {
        return getProperty("header.origin");
    }
    
    public String getAcceptLanguageHeader() {
        return getProperty("header.accept-language");
    }
    
    public String getSecChUaHeader() {
        return getProperty("header.sec-ch-ua");
    }
    
    public String getSecChUaMobileHeader() {
        return getProperty("header.sec-ch-ua-mobile");
    }
    
    public String getSecChUaPlatformHeader() {
        return getProperty("header.sec-ch-ua-platform");
    }
    
    public String getSecFetchDestHeader() {
        return getProperty("header.sec-fetch-dest");
    }
    
    public String getSecFetchModeHeader() {
        return getProperty("header.sec-fetch-mode");
    }
    
    public String getSecFetchSiteHeader() {
        return getProperty("header.sec-fetch-site");
    }
    
    public String getPriorityHeader() {
        return getProperty("header.priority");
    }
    
    public String getCallbackHeader() {
        return getProperty("header.callback");
    }
    
    // TMS-specific getters
    public String getTmsBaseUrl() {
        return getProperty("tms.base.url", getBaseUrl());
    }
    
    public String getTmsAccept() {
        return getProperty("tms.header.accept", "application/json, text/plain, */*");
    }
    
    public String getTmsAcceptLanguage() {
        return getProperty("tms.header.accept-language", "en-US,en;q=0.9");
    }
    
    public String getTmsCallback() {
        return getProperty("tms.header.callback", "");
    }
    
    public String getTmsContentType() {
        return getProperty("tms.header.content-type", "application/json");
    }
    
    public String getTmsOrigin() {
        return getProperty("tms.header.origin", "https://tms.delhivery.com");
    }
    
    public String getTmsPriority() {
        return getProperty("tms.header.priority", "u=1, i");
    }
    
    public String getTmsSecChUa() {
        return getProperty("tms.header.sec-ch-ua", "\"Not_A Brand\";v=\"8\", \"Chromium\";v=\"120\", \"Google Chrome\";v=\"120\"");
    }
    
    public String getTmsSecChUaMobile() {
        return getProperty("tms.header.sec-ch-ua-mobile", "?0");
    }
    
    public String getTmsSecChUaPlatform() {
        return getProperty("tms.header.sec-ch-ua-platform", "\"Windows\"");
    }
    
    public String getTmsSecFetchDest() {
        return getProperty("tms.header.sec-fetch-dest", "empty");
    }
    
    public String getTmsSecFetchMode() {
        return getProperty("tms.header.sec-fetch-mode", "cors");
    }
    
    public String getTmsSecFetchSite() {
        return getProperty("tms.header.sec-fetch-site", "same-origin");
    }
    
    public String getTmsUserAgent() {
        return getProperty("tms.header.user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/120.0.0.0 Safari/537.36");
    }
    
    public String getTmsXCoreosTid() {
        return getProperty("tms.header.x-coreos-tid", getXCoreosTid());
    }
    
    // Legacy methods for backward compatibility
    public String getRegressionClient() {
        return getProperty("regression_client");
    }
    
    public String getQCBearer() {
        return getProperty("bearer_token");
    }
    
    public String getManifestRegressionClient() {
        return getProperty("manifest_regression_client");
    }
    
    public String getBearerToken() {
        return getProperty("bearer_token");
    }
    
    public String getTrackingUrl() {
        return getProperty("trackingUrl");
    }
    
    public String getEdtBaseUrl() {
        return getProperty("baseUrlEdt");
    }
    
    public String getStagingBaseUrl() {
        return getProperty("baseUrlStaging");
    }
    
    public String getSMSUrl() {
        return getProperty("SMSUrl");
    }
    
    public String getEwbnCreateUrl() {
        return getProperty("ewbnCreateUrl");
    }
    
    public String getEwbnFetchUrl() {
        return getProperty("ewbnFetchUrl");
    }
    
    public String getClient(String client) {
        return getProperty(client);
    }
    
    public String getUmsUrl() {
        return getProperty("umsUrl");
    }
    
    public String getApiSanityUrl() {
        return getProperty("apiSanityUrl");
    }
    
    public String getClaimPanelUrl() {
        return getProperty("claimUrl");
    }
    
    public String getQCAnswerUrl() {
        return getProperty("qc_fetch");
    }
    
    public String getNewManifestUrl() {
        return getProperty("new.manifest.url");
    }
    
    // Backward compatibility methods
    public String getFcUuid() {
        return getProperty("fc.uuid", "");
    }
    
    public String getB2BPinCode() {
        return getProperty("b2b.pin.code", "");
    }
    
    public String getB2CPinCode() {
        return getProperty("b2c.pin.code", "");
    }
    
    public String getHeavyPinCode() {
        return getProperty("heavy.pin.code", "");
    }
    
    // WMS-specific getters
    public String getWmsBaseUrl() {
        return getProperty("wms.baseUrl");
    }
    
    public String getWmsAuthEndpoint() {
        return getProperty("wms.auth.endpoint");
    }
    
    public String getWmsUsername() {
        return getProperty("wms.username");
    }
    
    public String getWmsPassword() {
        return getProperty("wms.password");
    }
    
    public String getWmsOrderEndpoint() {
        return getProperty("wms.order.endpoint");
    }
    
    public String getWmsFcUuid() {
        return getProperty("wms.fc.uuid");
    }
    
    public String getWmsUserUuid() {
        return getProperty("wms.user.uuid");
    }
    
    // TMS Authentication getters
    public String getTmsAuthBaseUrl() {
        return getProperty("tms.auth.baseUrl");
    }
    
    public String getTmsAuthEndpoint() {
        return getProperty("tms.auth.endpoint");
    }
    
    public String getTmsAuthClientId() {
        return getProperty("tms.auth.clientId");
    }
    
    public String getTmsAuthClientSecret() {
        return getProperty("tms.auth.clientSecret");
    }
    
    public String getTmsAuthAudience() {
        return getProperty("tms.auth.audience");
    }
    
    public String getTmsAuthTid() {
        return getProperty("tms.auth.tid");
    }
}


// import java.util.Properties;

// public class ConfigLoader {
//     private final Properties properties;
//     private static ConfigLoader configLoader;
//     private static String testEnv;

//     private ConfigLoader() {
//         testEnv = System.getProperty("env");
//         testEnv = (testEnv == null) ? "qa" : testEnv;
//         properties = PropertyUtils.propertyLoader("src/main/resources/" + testEnv + ".properties");
//     }

//     public static ConfigLoader getInstance() {
//         if (configLoader == null) {
//             configLoader = new ConfigLoader();
//         }
//         return configLoader;
//     }

//     public String getBaseUrl() {
//         String prop = properties.getProperty("baseUrl");
//         if (prop != null) return prop;
//         else throw new RuntimeException("baseUrl property not found in properties file");
//     }

//     public String getClientUuid() {
//         String prop = properties.getProperty("client_uuid");
//         if (prop != null) return prop;
//         else throw new RuntimeException("client_uuid property not found in properties file");
//     }

//     public String getOrgUuid() {
//         String prop = properties.getProperty("organisation_uuid");
//         if (prop != null) return prop;
//         else throw new RuntimeException("organisation_uuid property not found in properties file");
//     }

//     public String getUserUuid() {
//         String prop = properties.getProperty("user_uuid");
//         if (prop != null) return prop;
//         else throw new RuntimeException("user_uuid property not found in properties file");
//     }

//     public String getUsername() {
//         String prop = properties.getProperty("username");
//         if (prop != null) return prop;
//         else throw new RuntimeException("username property not found in properties file");
//     }

//     public String getPassword() {
//         String prop = properties.getProperty("password");
//         if (prop != null) return prop;
//         else throw new RuntimeException("password property not found in properties file");
//     }

//     public String getFcUuid() {
//         String prop = properties.getProperty("fc_uuid");
//         if (prop != null) return prop;
//         else throw new RuntimeException("fulfillment_center_uuid property not found in properties file");
//     }

//     public String getRegressionClient() {
//         String prop = properties.getProperty("regression_client");
//         if (prop != null) return prop;
//         else throw new RuntimeException("regression_client property not found in properties file");
//     }
//     public String getInternalUser() {
//         String prop = properties.getProperty("internal_user");
//         if (prop != null) return prop;
//         else throw new RuntimeException("regression_client property not found in properties file");
//     }

//     public String getUmsUrl() {
//         String prop = properties.getProperty("umsUrl");
//         if (prop != null) return prop;
//         else throw new RuntimeException("umsUrl property not found in properties file");
//     }
//     public String getTrackingUrl() {
//         String prop = properties.getProperty("trackingUrl");
//         if (prop != null) return prop;
//         else throw new RuntimeException("umsUrl property not found in properties file");
//     }
    
//     public String getEdtBaseUrl() {
//         String prop = properties.getProperty("baseUrlEdt");
//         if (prop != null) return prop;
//         else throw new RuntimeException("baseUrlEdt property not found in properties file");
//     }
    
//     public String getStagingBaseUrl() {
//         String prop = properties.getProperty("baseUrlStaging");
//         if (prop != null) return prop;
//         else throw new RuntimeException("baseUrlStaging property not found in properties file");
//     }
    
//     public String getClient(String Client) {
//         String prop = properties.getProperty(Client);
//         if (prop != null) return prop;
//         else throw new RuntimeException(Client + " property not found in properties file");
//     }

//     public String getApiSanityUrl() {
//         String prop = properties.getProperty("apiSanityUrl");
//         if (prop != null) return prop;
//         else throw new RuntimeException("apiSanityUrl property not found in properties file");
//     }

//     public String getClaimPanelUrl() {
//         String prop = properties.getProperty("claimUrl");
//         if (prop != null) return prop;
//         else throw new RuntimeException("claimUrl property not found in properties file");
//     }

//     public String getB2BPinCode() {
//         String prop = properties.getProperty("B2BPinCode");
//         if (prop != null) return prop;
//         else throw new RuntimeException("B2BPinCode property not found in properties file");
//     }
//     public String getB2CPinCode() {
//         String prop = properties.getProperty("B2CPinCode");
//         if (prop != null) return prop;
//         else throw new RuntimeException("B2CPinCode property not found in properties file");
//     }
//     public String getHeavyPinCode() {
//         String prop = properties.getProperty("HeavyPinCode");
//         if (prop != null) return prop;
//         else throw new RuntimeException("HeavyPinCode property not found in properties file");
//     }
//     public String getQCAnswerUrl() {
//         String prop = properties.getProperty("qc_fetch");
//         if (prop != null) return prop;
//         else throw new RuntimeException("QCAnswerUrl property not found in properties file");
//     }
//     public String getQCBearer() {
//         String prop = properties.getProperty("bearer_token");
//         if (prop != null) return prop;
//         else throw new RuntimeException("bearer_token property not found in properties file");
//     }
//     public String getSMSUrl() {
//         String prop = properties.getProperty("SMSUrl");
//         if (prop != null) return prop;
//         else throw new RuntimeException("SMSUrl property not found in properties file");
//     }

//     public String getEwbnCreateUrl() {
//         String prop = properties.getProperty("ewbnCreateUrl");
//         if (prop != null) return prop;
//         else throw new RuntimeException("ewbnCreateUrl property not found in properties file");
//     }

//     //function to fetch ewbnFetch url
//     public String getEwbnFetchUrl() {
//         String prop = properties.getProperty("ewbnFetchUrl");
//         if (prop != null) return prop;
//         else throw new RuntimeException("ewbnFetchUrl property not found in properties file");
//     }

//     public String getNewManifestUrl() {
//         String prop = properties.getProperty("NewManifestUrl");
//         if (prop != null) return prop;
//         else throw new RuntimeException("NewManifestUrl property not found in properties file");
//     }

//     public String getManifestRegressionClient() {
//         String prop = properties.getProperty("manifest_regression_client");
//         if (prop != null) return prop;
//         else throw new RuntimeException("manifest_regression_client property not found in properties file");
//     }

//     //Get property key value by key name
//     public String getPropertyValue(String key) {
//         String prop = properties.getProperty(key);
//         if (prop != null) return prop;
//         else throw new RuntimeException(key + " property not found in properties file");
//     }
    
// }
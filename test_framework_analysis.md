# Automation Framework Analysis: TestGenBulkUpdate & TestPackageDetail

This document provides a detailed end-to-end analysis of the `TestGenBulkUpdate` and `TestPackageDetail` tests within the automation framework. It covers the flow from test execution to API request generation, authentication, and response handling.

## 1. High-Level Overview

The framework follows a layered architecture:
1.  **Test Layer (`src/test/java/...`)**: Contains TestNG test classes that define test scenarios and assertions.
2.  **Controller Layer (`ApiController.java`)**: Acts as a business logic layer that orchestrates API calls, handles data preparation, and performs assertions on responses.
3.  **Application API Layer (`PackageFlowRequests.java`)**: Acts as a wrapper for specific API endpoints, delegating the actual HTTP request construction to the Core API layer.
4.  **Core API Layer (`RestResource.java`, `SpecBuilder.java`)**: Handles the low-level details of making HTTP requests (using RestAssured), managing headers, and authentication.
5.  **POJO Layer (`src/main/java/.../pojo/...`)**: Defines the data models for request payloads and response bodies.
6.  **Utilities (`YamlReader`, `TokenManager`)**: Helper classes for reading configuration and managing tokens.

---

## 2. Test Layer Analysis

### 2.1. `TestGenBulkUpdate`
*   **Location**: `src/test/java/com/delhivery/Express/testModules/HQAPIEndpoints/GenBulkUpdate/TestGenBulkUpdate.java`
*   **Extends**: `BaseTest`
*   **Flow**:
    1.  **Setup**: Initializes `ApiController`.
    2.  **Data Loading**: Reads `ManifestationData` and `GenBulkUpdateData.validUpdate` from YAML files using `YamlReader`.
    3.  **Prerequisite**: Calls `apiCtrl.cmuManifestApi(manifestData)` to generate a waybill (`wbn`).
    4.  **Execution**: Calls `apiCtrl.verifyGenBulkUpdate(wbn, updateData)`.
    5.  **Assertion**: Validates the response (`success`, `error`, `successful updates count`) using `assertKeyValue`.

### 2.2. `TestPackageDetail`
*   **Location**: `src/test/java/com/delhivery/Express/testModules/HQAPIEndpoints/PackageDetail/TestPackageDetail.java`
*   **Extends**: `BaseTest`
*   **Flow**:
    1.  **Setup**: Initializes `ApiController`.
    2.  **Data Loading**: Reads `ManifestationData` from YAML.
    3.  **Prerequisite**: Calls `apiCtrl.cmuManifestApi(manifestData)` to generate a waybill (`wbn`).
    4.  **Execution**: Calls `apiCtrl.pkgdetails(data)`.
    5.  **Assertion**: Logs the success status from the response.

---

## 3. Controller Layer (`ApiController`)

*   **Location**: `src/test/java/com/delhivery/Express/controllers/api/ApiController.java`
*   **Role**: Bridges tests and API requests.

### 3.1. `verifyGenBulkUpdate`
*   **Method Signature**: `public GenBulkUpdateResponsePayload verifyGenBulkUpdate(String wbn, HashMap<String, String> data)`
*   **Logic**:
    1.  Constructs the payload (implied, likely passed or built internally).
    2.  Calls `PackageFlowRequests.genBulkUpdate(reqPayload, data)`.
    3.  Deserializes response to `GenBulkUpdateResponsePayload`.
    4.  Performs assertions (e.g., `assertKeyValue`).

### 3.2. `pkgdetails`
*   **Method Signature**: `public NewPackageDetails pkgdetails(HashMap<String, String> data)`
*   **Logic**:
    1.  **Authentication**: Calls `fetchUserJwtTokenApi(null)` to get the Bearer token.
    2.  Calls `PackageFlowRequests.pkgdetails(data, bearer)`.
    3.  Asserts status code 200.
    4.  Deserializes response to `NewPackageDetails`.

### 3.3. `fetchUserJwtTokenApi`
*   **Method Signature**: `public String fetchUserJwtTokenApi(HashMap<String, String> data)`
*   **Logic**:
    1.  Determines the client name (defaults to `ConfigLoader.getInstance().getRegressionClient()` if not provided).
    2.  Calls `TokenManager.getToken(client)` to retrieve the JWT token.

---

## 4. Application API Layer (`PackageFlowRequests`)

*   **Location**: `src/main/java/com/delhivery/Express/applicationApi/PackageFlowRequests.java`
*   **Role**: Defines API methods mapped to routes.

### 4.1. `genBulkUpdate`
*   **Route**: `GEN_BULK_UPDATE` (`/api/p/bulk/packages/gen_bulk_update`)
*   **Method**: `PUT`
*   **Call**: `RestResource.put(GEN_BULK_UPDATE, reqPayload, data)`

### 4.2. `pkgdetails`
*   **Route**: `FETCH_PACKAGE_DETAILS` (`/api/p/package/detail`)
*   **Method**: `GET`
*   **Call**: `RestResource.get(FETCH_PACKAGE_DETAILS, data, token)`

---

## 5. Core API Layer (`RestResource` & `SpecBuilder`)

### 5.1. `RestResource`
*   **Location**: `src/main/java/com/delhivery/core/api/RestResource.java`
*   **Role**: Wrapper around RestAssured.
*   **Key Methods**:
    *   `put(path, body, data)`: Uses `SpecBuilder.getRequestSpec()` or `getRequestSpec(data)` to build the request specification and executes a PUT request.
    *   `get(path, params, token)`: Uses `SpecBuilder.getRequestSpecBearer(token)` to build the request specification with Bearer token and executes a GET request.

### 5.2. `SpecBuilder`
*   **Location**: `src/main/java/com/delhivery/core/api/SpecBuilder.java`
*   **Role**: Builds `RequestSpecification` with headers and base URI.
*   **Header Management**:
    *   `defaultHeaders()`: Adds `Authorization: Token <token>` (fetched via `YamlReader` from `Client_Details`).
    *   `defaultHeadersBearer(bearer)`: Adds `Authorization: Bearer <bearer>`.
    *   `getRequestSpec()`: Sets Base URI, Content-Type (JSON), and default headers.

---

## 6. Data Models (POJOs)

### 6.1. `GenBulkUpdateResponsePayload`
*   **Location**: `src/main/java/com/delhivery/Express/pojo/GenBulkUpdate/response/GenBulkUpdateResponsePayload.java`
*   **Structure**:
    *   `boolean success`
    *   `String error`
    *   `GenBulkUpdateDataResponsePayload data`

### 6.2. `NewPackageDetails`
*   **Location**: `src/main/java/com/delhivery/Express/pojo/NewPackageDetails/Response/NewPackageDetails.java`
*   **Structure**:
    *   `String msg`
    *   `Data data`
    *   `boolean success`
    *   `String redirectTo`

---

## 7. Authentication Flow Summary

1.  **Token Retrieval**:
    *   `TokenManager` or `YamlReader` retrieves tokens based on the client name (e.g., "regression_client").
    *   Tokens are stored in YAML configuration files (`Client_Details`).

2.  **Header Injection**:
    *   `SpecBuilder` injects the token into the `Authorization` header.
    *   Format: `Token <token_value>` or `Bearer <token_value>`.

3.  **Usage**:
    *   `ApiController` decides which token to use (e.g., `fetchUserJwtTokenApi` for Bearer tokens).
    *   `RestResource` applies the appropriate `RequestSpecification`.

## 8. Reusability Guide

To use this flow in another framework:
1.  **Replicate `SpecBuilder`**: Ensure you have a mechanism to inject `Authorization` headers dynamically based on the environment and client.
2.  **Model the POJOs**: Copy the POJO structures for `GenBulkUpdateResponsePayload` and `NewPackageDetails`.
3.  **Implement Routes**: Define the API endpoints as constants.
4.  **Authentication**: Implement a `TokenManager` equivalent to fetch tokens from your configuration source.
5.  **Controller Logic**: Wrap the API calls in a controller/helper class to handle the business logic (e.g., generating waybills before updates).


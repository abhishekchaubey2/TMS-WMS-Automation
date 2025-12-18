## Outbound_Normal QA2 Flow – 16 Steps + Known QA2 Behaviours

This document captures the 16 functional steps from the `Outbound_Normal` TestNG flow in **QA2**, with ready-to-use `curl` commands **and notes about known QA2 error behaviours**.

- You can copy this file into another repo as a starting point for API automation.
- Replace the placeholders (tokens, IDs) with values from that environment and ensure the preconditions called out below are met.

---

## 0. Common Setup

Define these variables (bash):

```bash
BASE_URL="https://qa2-api-wms.delhivery.com/wms/qa2"
AUTH_TOKEN="REPLACE_WITH_VALID_JWT"
FC_UUID="28d521fc52bf47e08f59c162dfc883e5"
USER_UUID="UMSAU002"
CLIENT_UUID="d3ae2addecbe4d5dbb3425482a478658"
```

Common headers:

```bash
-H "Authorization: Bearer ${AUTH_TOKEN}" \
-H "fc-uuid: ${FC_UUID}" \
-H "User-Uuid: ${USER_UUID}" \
-H "Accept: */*" \
-H "Content-Type: application/json"
```

Runtime values you must capture from responses:

- `ORDER_NUMBER`, `SHIPMENT_NUMBER`, `INVOICE_NUMBER`
- `ORDER_CREATE_REQUEST_ID`
- `SHIPMENT_ID`, `ORDER_LINE_ID`
- `PICKWAVE_ID`, `PICKLIST_ID`, `PICK_CONTAINER_ID`
- `DLV_SKU`, `SCANNABLE_ID`, `PICK_LOCATION`, `ITEM_ID`, `LOT_ID`, `PICK_QTY`
- `WAYBILL_BARCODE` (e.g. `918723198773`)

---

## Known QA2 Error Patterns (When Automating / Re‑running)

These endpoints are **stateful** and will return 4xx/5xx in QA2 if preconditions are not met or the same data is reused across runs. When porting this flow to another repo, either:

- Ensure you create fresh data per run and satisfy the preconditions below, **or**
- Treat the listed error responses as expected outcomes and assert on them.

### `pick/picklist/assign/container` (Assign Pick Container / fixed container)

- **Endpoint**: `/wms/qa2/pick/picklist/assign/container`
- **Observed 400**:
  - `Cannot invoke "String.equals(Object)" because the return value of "com.delhivery.wms.pick.database.entity.PickLists.getContainerId()" is null`
- **Typical cause**:
  - The backend expects a non‑null `container_id` on the picklist record, but the picklist in QA2 has `container_id = null` (e.g. when using “fixed container” assignment or re‑using old picklists).
- **Mitigation for automation**:
  - Use freshly created pickwaves/picklists from the current test run.
  - Prefer assigning **movable containers** (as in step 7.2 below) and avoid calling any deprecated / “fixed container” assign endpoints.

### `pick/picklist/assign/user/` (Assign Picklist To User)

- **Endpoint**: `/wms/qa2/pick/picklist/assign/user/`
- **Observed 500**:
  - `Internal Server Error, path: "/api/v1/pick/user/"`
- **Typical causes**:
  - The `user` in the body or the `User-Uuid` header does not correspond to a valid, active WMS user for that FC or lacks necessary roles.
  - Using a user that does not exist in the QA2 UMS.
- **Mitigation for automation**:
  - Configure a real picking user in QA2 and set both:
    - `User-Uuid` header to a valid UMS user code.
    - `user` field in the JSON body to a login/username recognised by the pick service.
  - If you cannot guarantee a valid user in another repo, either:
    - Make user‑creation part of your test setup, or
    - Treat a 500 from this endpoint as an expected failure and assert on the error message.

### `pick/picklist/update/container/item` (Update Container Item)

- **Endpoint**: `/wms/qa2/pick/picklist/update/container/item`
- **Observed 400**:
  - `The Item could not be picked no picklist item found for dlv_sku : null , container_id : null, scannable id : null`
- **Typical causes**:
  - Not using the **current** picklist details (DLV SKU, container, scannable ID) from step 8, or using stale IDs from an old run.
  - Calling `update/container/item` after the picklist is already completed or the inventory has been moved.
- **Mitigation for automation**:
  - Always call **Get Picklist Details** (step 8) in the same run and use the values from that response to populate `DLV_SKU`, `PICK_LOCATION`, `ITEM_ID`, `SCANNABLE_ID`, `LOT_ID`, `PICK_QTY`.
  - Ensure you only invoke this once per unique picklist line, before `picklistComplete`.

### `pack/initiate/{containerId}` (Pack Initiate)

- **Endpoint**: `/wms/qa2/pack/initiate/{containerId}`
- **Observed errors**:
  - **500**: when `fulfillment_center_id` query param was missing.
  - **400**: `error_type: DataBaseException message: No Data Found in Database for given ContainerId with FulfillmentCenterId`
- **Typical causes**:
  - Missing `fulfillment_center_id` or `fc-uuid` → 500.
  - Calling with a `PICK_CONTAINER_ID` that does not exist in FIM or is no longer linked to an open shipment (e.g. re‑using an old container).
- **Mitigation for automation**:
  - Always include `?scan_type=PACK_SCAN&fulfillment_center_id=${FC_UUID}` and the `fc-uuid` header (as shown in step 12).
  - Use a container created in **this run** (step 7.1) that is still associated to the active picklist/shipment and has not been packed/cleared.

### `/pack/complete-box` (Complete Box)

- **Endpoint**: `/wms/qa2/pack/complete-box`
- **Observed 400**:
  - `error_type: ArrayIndexOutOfBoundsException message: Index 0 out of bounds for length 0`
  - In some runs also: `error_type: InsufficientProductQuantityException message: Available quantity for product <sku> is 0...`
- **Typical causes**:
  - No box template / box lines present for the given shipment/container when completing the box.
  - The inventory has already been consumed, or the `quantity` in the request exceeds available quantity.
- **Mitigation for automation**:
  - Ensure `complete-box` is called **after a successful pack‑initiate** and after a successful `updateContainerItem`, using the same `PICK_CONTAINER_ID`, `SHIPMENT_ID`, and `DLV_SKU` from earlier steps.
  - Avoid re‑running the same `complete-box` for an already boxed shipment or with stale IDs.

> **Important**: All of these APIs are not idempotent. If you replay the same payloads against the same shipment/container/waybill in QA2, the second and subsequent runs are very likely to return 4xx/5xx errors as described above.

## 1. FC Config + Order Create (`OrderCreate`)

### 1.1 Update FC Config – `/fc/update/config`

Enables outbound config flags for the FC.

```bash
curl -X PUT "${BASE_URL}/fc/update/config" \
  -H "Authorization: Bearer ${AUTH_TOKEN}" \
  -H "Accept: */*" \
  -H "Content-Type: application/json" \
  --data-raw '{
    "configType": "outbound",
    "fcUuid": "'"${FC_UUID}"'",
    "configData": {
      "pickImprovements": { "enabled": true },
      "pickImprovementsV2": { "enabled": true },
      "autoAddDispatch": false,
      "oneShipmentOneDispatch": false,
      "oneCourierOneDispatch": false,
      "autoCompleteDispatch": false,
      "dropzoneEnabled": false,
      "multiContainer": true,
      "exclusiveCaseEnabledClients": ["'"${CLIENT_UUID}"'"]
    }
  }'
```

**Expect**: HTTP 200, message like `Fulfillment Center Config updation successful.`  

---

### 1.2 Create Order – `/order-management/order/create`

Creates a FWD order with a single normal SKU in PRIME bucket.

```bash
ORDER_NUMBER="OD$(date +%s)000"
SHIPMENT_NUMBER="SH$(date +%s)000"
INVOICE_NUMBER="INV$(date +%s)000"
WORKFLOW="AUTO_WORKFLOW_NONE_CANCEL"   # align with your env
ORDER_TYPE="FWD"
PROD_SKU="sku_p1"                       # from your catalog/test data
PINCODE=122001
```

```bash
curl -X POST "${BASE_URL}/order-management/order/create" \
  -H "Authorization: Bearer ${AUTH_TOKEN}" \
  -H "fc-uuid: ${FC_UUID}" \
  -H "User-Uuid: ${USER_UUID}" \
  -H "Accept: */*" \
  -H "Content-Type: application/json" \
  --data-raw '{
    "requestId": null,
    "workflowId": null,
    "activityName": "CREATE",
    "data": {
      "order": {
        "orderNumber": "'"${ORDER_NUMBER}"'",
        "orderDate": "2025-12-10T07:30:00Z",
        "orderType": "'"${ORDER_TYPE}"'",
        "channel": "AUTOCHANNEL",
        "shipment": {
          "ewaybillExpiryDate": 0,
          "shippedBy": null,
          "number": "'"${SHIPMENT_NUMBER}"'",
          "workflow": "'"${WORKFLOW}"'",
          "childCourier": "Delhivery",
          "fc": "AUTOFC1",
          "ewaybill_Expiry_Date": 0,
          "invoice": {
            "invoiceNumber": "'"${INVOICE_NUMBER}"'",
            "paymentMode": "PREPAID",
            "netAmount": "1234.00"
          },
          "orderLine": {
            "number": "OL1",
            "productSku": "'"${PROD_SKU}"'",
            "quantity": 2,
            "clientId": "'"${CLIENT_UUID}"'",
            "bucket": "PRIME",
            "invoice": null
          },
          "extras": null
        },
        "consignee": {
          "name": "API Test User",
          "addressLine1": "Plot 5, Sector 44",
          "pinCode": '"${PINCODE}"',
          "city": "Gurgaon",
          "state": "Haryana",
          "country": "India",
          "primaryPhoneNumber": "9876543210",
          "secondaryPhoneNumber": "9876543211"
        },
        "extras": null
      }
    }
  }'
```

**Expect**: HTTP 200, `"success": true`, non-null `"requestId"` → save as `ORDER_CREATE_REQUEST_ID`.

---

## 2. Get Order Create Request Status (`getOrderCreateRequestStatus`)

Polls request tracker until shipment status is `ALC`, and extracts shipment/order-line info.

```bash
ORDER_CREATE_REQUEST_ID="<FROM_PREVIOUS_STEP>"
```

```bash
curl -X GET "${BASE_URL}/request-tracker/logs?request_id=${ORDER_CREATE_REQUEST_ID}" \
  -H "Authorization: Bearer ${AUTH_TOKEN}" \
  -H "fc-uuid: ${FC_UUID}" \
  -H "User-Uuid: ${USER_UUID}" \
  -H "Accept: */*"
```

**Expect**:

- HTTP 200
- `data[0].data.data.shipments[0].status == "ALC"`
- Save:
  - `SHIPMENT_ID`
  - `SHIPMENT_NUMBER`
  - `ORDER_LINE_ID`

---

## 3. Create Pickwave (`createPickwave`)

Creates a pickwave for the allocated shipment.

```bash
WORKFLOW_ID="31"  # use same as in your workflow config
```

```bash
curl -X POST "${BASE_URL}/pick/pickwave/create" \
  -H "Authorization: Bearer ${AUTH_TOKEN}" \
  -H "fc-uuid: ${FC_UUID}" \
  -H "User-Uuid: ${USER_UUID}" \
  -H "Accept: */*" \
  -H "Content-Type: application/json" \
  --data-raw '{
    "createBy": "shipments",
    "fc": "'"${FC_UUID}"'",
    "shipmentCount": 1,
    "workflow": "'"${WORKFLOW_ID}"'",
    "createdBy": "'"${USER_UUID}"'",
    "filters": {
      "shipmentIds": ['"${SHIPMENT_ID}"']
    }
  }'
```

**Expect**: HTTP 200, `"success": true`, `request_id` → `PICKWAVE_CREATE_REQUEST_ID`.

---

## 4. Get Pickwave Create Request Status (`getPickwaveCreateRequestStatus`)

Checks that pickwave creation request completed successfully.

```bash
PICKWAVE_CREATE_REQUEST_ID="<FROM_PREVIOUS_STEP>"
```

```bash
curl -X GET "${BASE_URL}/request-tracker/logs?request_id=${PICKWAVE_CREATE_REQUEST_ID}" \
  -H "Authorization: Bearer ${AUTH_TOKEN}" \
  -H "fc-uuid: ${FC_UUID}" \
  -H "User-Uuid: ${USER_UUID}" \
  -H "Accept: */*"
```

**Expect**: HTTP 200, rec state shows success; shipment appears under the created pickwave.

---

## 5. Get Pickwave Filters (`getPickwaveFilters`)

Fetches pickwave + picklist details using shipment number.

```bash
curl -X GET "${BASE_URL}/pick/pickwave/filters/v2?fulfillmentCenter=${FC_UUID}&multi_container=false&page=0&shipmentNumber=${SHIPMENT_NUMBER}&status=CREATED" \
  -H "Authorization: Bearer ${AUTH_TOKEN}" \
  -H "fc-uuid: ${FC_UUID}" \
  -H "User-Uuid: ${USER_UUID}" \
  -H "Accept: */*"
```

**Expect**:

- HTTP 200
- `"success": true`, message like `"pickwaves successfully fetched"`
- Save:
  - `PICKWAVE_ID` ← `data[0].id`
  - `PICKLIST_ID` ← `data[0].pick_lists[0].id`

---

## 6. Assign Picklist To User (`assignPicklistToUser`)

Assigns picklists in the pickwave to a user (mirrors `testAssignPicklistToUser`).

```bash
PICKWAVE_ID="<FROM_STEP_5>"
PICKLIST_ID="<FROM_STEP_5>"
USERNAME="${USER_UUID}"
```

```bash
curl -X PUT "${BASE_URL}/pick/picklist/assign/user/" \
  -H "Authorization: Bearer ${AUTH_TOKEN}" \
  -H "fc-uuid: ${FC_UUID}" \
  -H "User-Uuid: ${USER_UUID}" \
  -H "Accept: */*" \
  -H "Content-Type: application/json" \
  --data-raw '{
    "pickWaveId": '"${PICKWAVE_ID}"',
    "pickId": '"${PICKLIST_ID}"',
    "assign": true,
    "user": "'"${USERNAME}"'"
  }'
```

**Preconditions**

- `PICKWAVE_ID` and `PICKLIST_ID` must come from a **fresh pickwave** created in this run (steps 3–5).
- `USERNAME` and `User-Uuid` must correspond to a **real, active UMS user** with permission to pick in the target FC.

**Expected behaviour**

- HTTP 200
- `"success": true`
- `message == "User : <USERNAME>, Successfully updated"`

**Observed QA2 error when mis‑configured (from automation)**

- **Status**: `500`
- **Message** (from logs):  
  `Internal Server Error, path: "/api/v1/pick/user/"`
- **Typical reasons**:
  - `USERNAME` or `User-Uuid` is not a valid/active user in QA2.
  - User is not assigned to the FC / lacks required roles.

---

## 7. Assign Pick Container (`assignPickContainer`)

### 7.1 Create Movable Container

```bash
PICK_CONTAINER_ID="CONT$(date +%s)000"
```

```bash
curl -X POST "${BASE_URL}/fulfillment-inventory-management/container/create" \
  -H "Authorization: Bearer ${AUTH_TOKEN}" \
  -H "fc-uuid: ${FC_UUID}" \
  -H "User-Uuid: ${USER_UUID}" \
  -H "Accept: */*" \
  -H "Content-Type: application/json" \
  --data-raw '{
    "container_id": "'"${PICK_CONTAINER_ID}"'",
    "status": null,
    "movable": true,
    "fulfillment_center_uuid": "'"${FC_UUID}"'",
    "is_temp_container": false,
    "container_type": "shelf"
  }'
```

### 7.2 Assign Container to Picklist

```bash
curl -X PUT "${BASE_URL}/pick/picklist/assign/container?pick_list_id=${PICKLIST_ID}" \
  -H "Authorization: Bearer ${AUTH_TOKEN}" \
  -H "fc-uuid: ${FC_UUID}" \
  -H "User-Uuid: ${USER_UUID}" \
  -H "Accept: */*" \
  -H "Content-Type: application/json" \
  --data-raw '{
    "movableContainer": "'"${PICK_CONTAINER_ID}"'",
    "multiContainer": false
  }'
```

**Preconditions**

- `PICKLIST_ID` must reference a picklist that:
  - Belongs to the `PICKWAVE_ID` created in this run.
  - Has not already been assigned a conflicting container or completed.
- `PICK_CONTAINER_ID` must exist in FIM (created in step 7.1) and not be soft‑deleted.

**Expected behaviour**

- HTTP 200
- `"success": true`
- `message == "Container : <PICK_CONTAINER_ID>, Successfully assigned"`

**Observed QA2 error when using “fixed container” / bad state**

This mirrors `testAssignContainerToPicklist` and the old “fixed‑container assign” logic from `testVerifyPicklistDetails`:

- **Endpoint**: `/wms/qa2/pick/picklist/assign/container`
- **Status**: `400`
- **Message**:  
  `Cannot invoke "String.equals(Object)" because the return value of "com.delhivery.wms.pick.database.entity.PickLists.getContainerId()" is null`
- **Typical reasons**:
  - The picklist row in DB has `container_id = null` and the backend code expects a non‑null value when performing a fixed‑container assignment.
  - You are reusing an old picklist from a prior run or calling a different assign‑container variant than the one shown here.

When porting this flow to another repo:

- Prefer the **movable container** pattern above.
- If you must test the failing “fixed container” behaviour, call the same endpoint with the body that omits `movableContainer` / uses a fixed container ID and assert on the 400 with the `PickLists.getContainerId()` message.

---

## 8. Get Picklist Details (`getPicklistDetails`)

Retrieves picklist lines to drive picking.

```bash
curl -X GET "${BASE_URL}/pick/get/picklist?pick_id=${PICKLIST_ID}&multi_container=false" \
  -H "Authorization: Bearer ${AUTH_TOKEN}" \
  -H "fc-uuid: ${FC_UUID}" \
  -H "User-Uuid: ${USER_UUID}" \
  -H "Accept: */*"
```

**Expect**: HTTP 200, `"success": true`.  
Extract and save for the relevant line:

- `DLV_SKU`
- `SCANNABLE_ID`
- `PICK_LOCATION`
- `ITEM_ID`
- `PICK_QTY`
- `LOT_ID`

---

## 9. Update Container Item (`updateContainerItem`)

Performs the pick from the source location/container into the assigned `PICK_CONTAINER_ID` (mirrors `testUpdateContainerItem`).

```bash
curl -X PUT "${BASE_URL}/pick/picklist/update/container/item?item_id=${PICKLIST_ID}" \
  -H "Authorization: Bearer ${AUTH_TOKEN}" \
  -H "fc-uuid: ${FC_UUID}" \
  -H "User-Uuid: ${USER_UUID}" \
  -H "Accept: */*" \
  -H "Content-Type: application/json" \
  --data-raw '{
    "clientId": "'"${CLIENT_UUID}"'",
    "dlvSku": "'"${DLV_SKU}"'",
    "fulfillmentCenter": "'"${FC_UUID}"'",
    "itemContainer": "'"${PICK_LOCATION}"'",
    "itemId": '"${ITEM_ID}"',
    "itemPicked": '"${PICK_QTY}"',
    "movableContainer": "'"${PICK_CONTAINER_ID}"'",
    "scannableId": "'"${SCANNABLE_ID}"'",
    "lotId": "'"${LOT_ID}"'",
    "bucket": "PRIME",
    "multiContainer": false
  }'
```

**Preconditions**

- You must have just called **Get Picklist Details** (step 8) and populated:
  - `DLV_SKU`, `SCANNABLE_ID`, `PICK_LOCATION`, `ITEM_ID`, `PICK_QTY`, `LOT_ID` from that response.
- The item has **not yet been picked** (i.e. `picked_quantity` is 0 and the picklist is not completed).

**Expected behaviour**

- HTTP 200
- `"success": true`
- Response shows the item now associated with `PICK_CONTAINER_ID` and updated picked quantity.

**Observed QA2 error when preconditions are not met**

This mirrors `testUpdateContainerItem` in automation:

- **Status**: `400`
- **Message**:  
  `The Item could not be picked no picklist item found for dlv_sku : null , container_id : null, scannable id : null`
- **Typical reasons**:
  - `DLV_SKU`, `SCANNABLE_ID`, or `PICK_LOCATION` were not filled from the latest picklist response (left as `null`).
  - The picklist line was already picked/consumed in a previous run, so no matching open item exists.

In other repos, always:

- Drive this request from live picklist data.
- Avoid reusing the same `PICKLIST_ID` and container across multiple runs without resetting state.

---

## 10. Picklist Complete (`picklistComplete`)

Marks the picklist as fully picked.

```bash
curl -X PUT "${BASE_URL}/pick/picklist/complete?pick_list_id=${PICKLIST_ID}" \
  -H "Authorization: Bearer ${AUTH_TOKEN}" \
  -H "fc-uuid: ${FC_UUID}" \
  -H "User-Uuid: ${USER_UUID}" \
  -H "Accept: */*" \
  -H "Content-Type: application/json" \
  --data-raw '{
    "lost": false,
    "multiContainer": false
  }'
```

**Expect**: HTTP 200, `"success": true`, message `"Pick list completed"`.

---

## 11. Verify Pickwave Status (`verifyPickwaveStatus`)

Ensures all picklists in the pickwave are `PICKED`.

```bash
curl -X GET "${BASE_URL}/pick/pickwave/filters/v2?fulfillmentCenter=${FC_UUID}&multi_container=false&page=0&shipmentNumber=${SHIPMENT_NUMBER}&status=DONE" \
  -H "Authorization: Bearer ${AUTH_TOKEN}" \
  -H "fc-uuid: ${FC_UUID}" \
  -H "User-Uuid: ${USER_UUID}" \
  -H "Accept: */*"
```

**Expect**:

- HTTP 200
- Each `data[0].pick_lists[*].status == "PICKED"`
- Message `"pickwaves successfully fetched"`

---

## 12. Pack Initiate (`packInitiate`)

Initiates pack at pack station for `PICK_CONTAINER_ID` (mirrors `testPackInitiate`).

```bash
curl -X GET "${BASE_URL}/pack/initiate/${PICK_CONTAINER_ID}?scan_type=PACK_SCAN&fulfillment_center_id=${FC_UUID}" \
  -H "Authorization: Bearer ${AUTH_TOKEN}" \
  -H "fc-uuid: ${FC_UUID}" \
  -H "User-Uuid: ${USER_UUID}" \
  -H "Accept: */*" \
  -H "Content-Type: application/json"
```

**Preconditions**

- `PICK_CONTAINER_ID` must:
  - Exist in FIM (step 7.1) and be assigned to the picklist (step 7.2).
  - Contain the picked inventory (step 9) and be in a state where packing is allowed.
- `fulfillment_center_id` query param and `fc-uuid` header must both be present and consistent.

**Expected behaviour**

- HTTP 200
- `"success": true`
- Response `data.shipments[0].pack_status` is typically `"ToBePacked"` or similar.

**Observed QA2 errors**

- **500** when `fulfillment_center_id` is **missing** from the query:
  - Generic server error due to missing required param.
- **400** with:  
  `error_type: DataBaseException message: No Data Found in Database for given ContainerId with FulfillmentCenterId`
  - Usually indicates the container does not exist in FIM for the given FC, or is no longer associated with a packable shipment (e.g. reused after completion).

In a new repo, always:

- Use a container created and used **in the same test run**.
- Ensure the FC UUID and container name match what was used in the pick steps.

---

## 13. Get FIM Container Detail (`getFimContainerDetail`)

Reads inventory container metadata.

```bash
curl -X GET "${BASE_URL}/fulfillment-inventory-management/container/detail?name=${PICK_CONTAINER_ID}" \
  -H "Authorization: Bearer ${AUTH_TOKEN}" \
  -H "fc-uuid: ${FC_UUID}" \
  -H "User-Uuid: ${USER_UUID}" \
  -H "Accept: */*" \
  -H "Content-Type: application/json"
```

**Expect**: HTTP 200, `"success": true`, data shows container characteristics (zone, dimensions, movable, etc.).

---

## 14. Complete Box (`completeBox`)

Forms the physical shipping box contents (mirrors `testCompleteBox`).

```bash
DLV_SKU="74812"           # from picklist details
SHIPMENT_ID="${SHIPMENT_ID}"
```

```bash
curl -X POST "${BASE_URL}/pack/complete-box" \
  -H "Authorization: Bearer ${AUTH_TOKEN}" \
  -H "fc-uuid: ${FC_UUID}" \
  -H "User-Uuid: ${USER_UUID}" \
  -H "Accept: */*" \
  -H "Content-Type: application/json" \
  --data-raw '{
    "fulfillment_center_id": "'"${FC_UUID}"'",
    "shipment_id": "'"${SHIPMENT_ID}"'",
    "containers": [
      {
        "container_id": "'"${PICK_CONTAINER_ID}"'",
        "products": [
          {
            "product_id": "'"${DLV_SKU}"'",
            "quantity": 2,
            "siob": false,
            "client_uuid": "'"${CLIENT_UUID}"'"
          }
        ]
      }
    ]
  }'
```

**Preconditions**

- `SHIPMENT_ID` must refer to a shipment that:
  - Is in the correct state for packing (after `packInitiate` and with picked items).
  - Has not already had a box completed for the same container and SKU in this environment run.
- `PICK_CONTAINER_ID` and `DLV_SKU` must match what was used in steps 7–9.
- The requested `quantity` must be ≤ the available picked quantity for that SKU in the container.

**Expected behaviour**

- HTTP 200
- `"success": true`
- Response contains `data.boxes[...]` with `box_shipment_id`, `shipping_label_url`, `waybill_number`, etc.

**Observed QA2 errors when reusing state or mis‑aligning data**

From `testCompleteBox` and repeated manual runs:

- **Status**: `400`, message:  
  `error_type: ArrayIndexOutOfBoundsException message: Index 0 out of bounds for length 0`
  - Typically means no internal box entries were generated for the given shipment/container, so the service tries to read `boxes[0]` but the list is empty.
- **Status**: `400`, message:  
  `error_type: InsufficientProductQuantityException message: Available quantity for product 74812 is 0. The requested product quantity is greater than the available quantity`
  - Indicates there is no remaining available quantity (e.g. the same box was already completed or inventory has been consumed by a previous run).

In a fresh automation run, you should:

- Create a new order/shipment, pick, and pack sequence each time.
- Avoid calling `complete-box` twice for the same shipment/container/SKU without replenishing or resetting QA data.

---

## 15. Pack Shipment (`packShipment`)

Marks shipment as packed.

```bash
curl -X POST "${BASE_URL}/pack/shipment-pack" \
  -H "Authorization: Bearer ${AUTH_TOKEN}" \
  -H "fc-uuid: ${FC_UUID}" \
  -H "User-Uuid: ${USER_UUID}" \
  -H "Accept: */*" \
  -H "Content-Type: application/json" \
  --data-raw '{
    "shipment_id": '"${SHIPMENT_ID}"',
    "fulfillment_center_id": "'"${FC_UUID}"'",
    "retry": false
  }'
```

**Expect**: HTTP 200, `"success": true`, shipment status transitions to packed state.

---

## 16. Fetch Auto Dimensions at RTS (`fetchAutoDimensionsAtRts`)

Fetches auto-captured dimensions for the box using barcode.

Set from box/waybill data:

```bash
WAYBILL_BARCODE="918723198773"  # example value from QA2 run
```

```bash
curl -X GET "${BASE_URL}/rts-dispatch/fetch-auto-dimensions?barcode_identifier=${WAYBILL_BARCODE}" \
  -H "Authorization: Bearer ${AUTH_TOKEN}" \
  -H "fc-uuid: ${FC_UUID}" \
  -H "User-Uuid: ${USER_UUID}" \
  -H "Accept: */*" \
  -H "Content-Type: application/json"
```

**Expect** (as seen in QA2):

- HTTP 200
- `"success": true`
- Message: `"Dimensions were successfully fetched for the box"`
- `data[0]` contains `length`, `width`, `height`, `weight`, `fc_uuid`, `waybill_number`, etc.

---

### Note on `saveAutoDimensions`

The `saveAutoDimensions` step from the test currently **fails in QA2** because the success message has changed to:

> `Dim-wt captured for box successfully Paste shipping label at RTS Station`

while the assertion expects:

> `Dimensions were successfully captured`

For that reason, **`saveAutoDimensions` is intentionally not included in this “16 passing APIs” file**.



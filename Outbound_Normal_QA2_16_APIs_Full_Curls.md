## Outbound_Normal QA2 – 16 API Flow (Full cURL Script)

This file gives you a **straight, copy‑paste friendly sequence of 16 APIs** corresponding to the happy path of `Outbound_Normal` in QA2, using:

- All credentials and constants from this repo (`qa2.properties`, `qa2.json`)
- No missing/blank fields in the request bodies
- Explicit notes on **dependencies** and how to capture IDs between calls

> These APIs are **stateful**. You must run them in order, on **fresh data each time**, and derive IDs from responses as shown. If you reuse IDs from old runs, QA2 will legitimately start returning the 400/500 errors you already saw.

---

## 0. Common environment setup

Run this once in your shell before executing the sequence:

```bash
#!/usr/bin/env bash

set -euo pipefail

BASE_URL="https://qa2-api-wms.delhivery.com/wms/qa2"

# From src/main/resources/qa2.properties
FC_UUID="28d521fc52bf47e08f59c162dfc883e5"
CLIENT_UUID="d3ae2addecbe4d5dbb3425482a478658"
USER_UUID="UMSAU002"
USERNAME="autouser"
CHANNEL="AUTOCHANNEL"
CHILD_COURIER="Delhivery"
FC_NAME="AUTOFC1"

# From src/test/resources/qa2.json – first normal product
PROD_SKU="sku_p1"
DLV_SKU="74812"
SCANNABLE_ID="scan_p1"

# Provide a valid QA2 JWT here
AUTH_TOKEN="REPLACE_WITH_VALID_QA2_JWT"
```

All subsequent `curl` commands assume these variables are set.

---

## 1. Update FC Config (prereq for outbound) – `/fc/update/config`

```bash
curl -sS -X PUT "${BASE_URL}/fc/update/config" \
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

**Expected**: HTTP 200, `"Fulfillment Center Config updation successful."`

---

## 2. Create Order – `/order-management/order/create`

Generate fresh numbers for each run:

```bash
ORDER_NUMBER="OD$(date +%s)000"
SHIPMENT_NUMBER="SH$(date +%s)000"
INVOICE_NUMBER="INV$(date +%s)000"
WORKFLOW="AUTO_WORKFLOW_NONE_CANCEL"   # matches test data
PINCODE=122001
```

Call:

```bash
ORDER_CREATE_RESP=$(
  curl -sS -X POST "${BASE_URL}/order-management/order/create" \
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
          "orderType": "FWD",
          "channel": "'"${CHANNEL}"'",
          "shipment": {
            "ewaybillExpiryDate": 0,
            "shippedBy": null,
            "number": "'"${SHIPMENT_NUMBER}"'",
            "workflow": "'"${WORKFLOW}"'",
            "childCourier": "'"${CHILD_COURIER}"'",
            "fc": "'"${FC_NAME}"'",
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
)
echo "${ORDER_CREATE_RESP}"
```

Extract the request id:

```bash
ORDER_CREATE_REQUEST_ID=$(echo "${ORDER_CREATE_RESP}" | jq -r '.request_id')
echo "ORDER_CREATE_REQUEST_ID=${ORDER_CREATE_REQUEST_ID}"
```

---

## 3. Get Order Create Request Status – `/request-tracker/logs`

Poll until shipment status becomes `ALC`:

```bash
REQ_STATUS_RESP=$(
  curl -sS -X GET "${BASE_URL}/request-tracker/logs?request_id=${ORDER_CREATE_REQUEST_ID}" \
    -H "Authorization: Bearer ${AUTH_TOKEN}" \
    -H "fc-uuid: ${FC_UUID}" \
    -H "User-Uuid: ${USER_UUID}" \
    -H "Accept: */*"
)
echo "${REQ_STATUS_RESP}"
```

Extract:

```bash
SHIPMENT_ID=$(echo "${REQ_STATUS_RESP}" | jq -r '.data[0].data.data.shipments[0].shipment_id')
SHIPMENT_NUMBER=$(echo "${REQ_STATUS_RESP}" | jq -r '.data[0].data.data.shipments[0].shipment_number')
ORDER_LINE_ID=$(echo "${REQ_STATUS_RESP}" | jq -r '.data[0].data.data.shipments[0].order_lines[0].order_line_id')

echo "SHIPMENT_ID=${SHIPMENT_ID}"
echo "SHIPMENT_NUMBER=${SHIPMENT_NUMBER}"
echo "ORDER_LINE_ID=${ORDER_LINE_ID}"
```

**Expected**: shipment status `ALC`.

---

## 4. Create Pickwave – `/pick/pickwave/create`

```bash
WORKFLOW_ID="31"   # outbound workflow id used in tests

CREATE_PW_RESP=$(
  curl -sS -X POST "${BASE_URL}/pick/pickwave/create" \
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
      "createdBy": "'"${USERNAME}"'",
      "filters": {
        "shipmentIds": ['"${SHIPMENT_ID}"']
      }
    }'
)
echo "${CREATE_PW_RESP}"

PICKWAVE_CREATE_REQUEST_ID=$(echo "${CREATE_PW_RESP}" | jq -r '.request_id')
echo "PICKWAVE_CREATE_REQUEST_ID=${PICKWAVE_CREATE_REQUEST_ID}"
```

---

## 5. Get Pickwave Create Request Status – `/request-tracker/logs`

```bash
PW_STATUS_RESP=$(
  curl -sS -X GET "${BASE_URL}/request-tracker/logs?request_id=${PICKWAVE_CREATE_REQUEST_ID}" \
    -H "Authorization: Bearer ${AUTH_TOKEN}" \
    -H "fc-uuid: ${FC_UUID}" \
    -H "User-Uuid: ${USER_UUID}" \
    -H "Accept: */*"
)
echo "${PW_STATUS_RESP}"
```

Ensure the request is marked successful.

---

## 6. Get Pickwave Filters (CREATED) – `/pick/pickwave/filters/v2`

```bash
PWF_CREATED_RESP=$(
  curl -sS -X GET "${BASE_URL}/pick/pickwave/filters/v2?fulfillmentCenter=${FC_UUID}&multi_container=false&page=0&shipmentNumber=${SHIPMENT_NUMBER}&status=CREATED" \
    -H "Authorization: Bearer ${AUTH_TOKEN}" \
    -H "fc-uuid: ${FC_UUID}" \
    -H "User-Uuid: ${USER_UUID}" \
    -H "Accept: */*"
)
echo "${PWF_CREATED_RESP}"

PICKWAVE_ID=$(echo "${PWF_CREATED_RESP}" | jq -r '.data[0].id')
PICKLIST_ID=$(echo "${PWF_CREATED_RESP}" | jq -r '.data[0].pick_lists[0].id')

echo "PICKWAVE_ID=${PICKWAVE_ID}"
echo "PICKLIST_ID=${PICKLIST_ID}"
```

---

## 7. Assign Picklist to User – `/pick/picklist/assign/user/`

```bash
ASSIGN_USER_RESP=$(
  curl -sS -X PUT "${BASE_URL}/pick/picklist/assign/user/" \
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
)
echo "${ASSIGN_USER_RESP}"
```

**Expected**: 200, message `"User : autouser, Successfully updated"`.

---

## 8. Create Movable Container – `/fulfillment-inventory-management/container/create`

```bash
PICK_CONTAINER_ID="CONT$(date +%s)000"

CREATE_CONT_RESP=$(
  curl -sS -X POST "${BASE_URL}/fulfillment-inventory-management/container/create" \
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
)
echo "${CREATE_CONT_RESP}"
echo "PICK_CONTAINER_ID=${PICK_CONTAINER_ID}"
```

---

## 9. Assign Container to Picklist – `/pick/picklist/assign/container`

```bash
ASSIGN_CONT_RESP=$(
  curl -sS -X PUT "${BASE_URL}/pick/picklist/assign/container?pick_list_id=${PICKLIST_ID}" \
    -H "Authorization: Bearer ${AUTH_TOKEN}" \
    -H "fc-uuid: ${FC_UUID}" \
    -H "User-Uuid: ${USER_UUID}" \
    -H "Accept: */*" \
    -H "Content-Type: application/json" \
    --data-raw '{
      "movableContainer": "'"${PICK_CONTAINER_ID}"'",
      "multiContainer": false
    }'
)
echo "${ASSIGN_CONT_RESP}"
```

---

## 10. Get Picklist Details – `/pick/get/picklist`

```bash
PICKLIST_DETAIL_RESP=$(
  curl -sS -X GET "${BASE_URL}/pick/get/picklist?pick_id=${PICKLIST_ID}&multi_container=false" \
    -H "Authorization: Bearer ${AUTH_TOKEN}" \
    -H "fc-uuid: ${FC_UUID}" \
    -H "User-Uuid: ${USER_UUID}" \
    -H "Accept: */*"
)
echo "${PICKLIST_DETAIL_RESP}"
```

Extract fields for the first line:

```bash
PICK_LOCATION=$(echo "${PICKLIST_DETAIL_RESP}" | jq -r '.data.pick_lists[0].pick_list_items[0].pick_location')
ITEM_ID=$(echo "${PICKLIST_DETAIL_RESP}" | jq -r '.data.pick_lists[0].pick_list_items[0].item_id')
PICK_QTY=$(echo "${PICKLIST_DETAIL_RESP}" | jq -r '.data.pick_lists[0].pick_list_items[0].quantity')
LOT_ID=$(echo "${PICKLIST_DETAIL_RESP}" | jq -r '.data.pick_lists[0].pick_list_items[0].lot_id')

echo "PICK_LOCATION=${PICK_LOCATION}"
echo "ITEM_ID=${ITEM_ID}"
echo "PICK_QTY=${PICK_QTY}"
echo "LOT_ID=${LOT_ID}"
```

*(If the actual JSON shape differs slightly, adjust the `jq` paths accordingly.)*

---

## 11. Update Container Item – `/pick/picklist/update/container/item`

```bash
UPDATE_CONT_ITEM_RESP=$(
  curl -sS -X PUT "${BASE_URL}/pick/picklist/update/container/item?item_id=${PICKLIST_ID}" \
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
)
echo "${UPDATE_CONT_ITEM_RESP}"
```

---

## 12. Complete Picklist – `/pick/picklist/complete`

```bash
PICKLIST_COMPLETE_RESP=$(
  curl -sS -X PUT "${BASE_URL}/pick/picklist/complete?pick_list_id=${PICKLIST_ID}" \
    -H "Authorization: Bearer ${AUTH_TOKEN}" \
    -H "fc-uuid: ${FC_UUID}" \
    -H "User-Uuid: ${USER_UUID}" \
    -H "Accept: */*" \
    -H "Content-Type: application/json" \
    --data-raw '{
      "lost": false,
      "multiContainer": false
    }'
)
echo "${PICKLIST_COMPLETE_RESP}"
```

---

## 13. Verify Pickwave Status (DONE) – `/pick/pickwave/filters/v2`

```bash
PWF_DONE_RESP=$(
  curl -sS -X GET "${BASE_URL}/pick/pickwave/filters/v2?fulfillmentCenter=${FC_UUID}&multi_container=false&page=0&shipmentNumber=${SHIPMENT_NUMBER}&status=DONE" \
    -H "Authorization: Bearer ${AUTH_TOKEN}" \
    -H "fc-uuid: ${FC_UUID}" \
    -H "User-Uuid: ${USER_UUID}" \
    -H "Accept: */*"
)
echo "${PWF_DONE_RESP}"
```

All picklists under this pickwave should now be `"PICKED"`.

---

## 14. Pack Initiate – `/pack/initiate/{containerId}`

```bash
PACK_INIT_RESP=$(
  curl -sS -X GET "${BASE_URL}/pack/initiate/${PICK_CONTAINER_ID}?scan_type=PACK_SCAN&fulfillment_center_id=${FC_UUID}" \
    -H "Authorization: Bearer ${AUTH_TOKEN}" \
    -H "fc-uuid: ${FC_UUID}" \
    -H "User-Uuid: ${USER_UUID}" \
    -H "Accept: */*" \
    -H "Content-Type: application/json"
)
echo "${PACK_INIT_RESP}"
```

---

## 15. Complete Box – `/pack/complete-box`

```bash
COMPLETE_BOX_RESP=$(
  curl -sS -X POST "${BASE_URL}/pack/complete-box" \
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
)
echo "${COMPLETE_BOX_RESP}"
```

You can extract any `waybill_number` / box barcode from this response if present.

---

## 16. Pack Shipment – `/pack/shipment-pack`

```bash
PACK_SHIP_RESP=$(
  curl -sS -X POST "${BASE_URL}/pack/shipment-pack" \
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
)
echo "${PACK_SHIP_RESP}"
```

---

## 17. Fetch Auto Dimensions at RTS – `/rts-dispatch/fetch-auto-dimensions`

From the box/pack responses, set:

```bash
WAYBILL_BARCODE="REPLACE_WITH_WAYBILL_BARCODE_FROM_COMPLETE_BOX_OR_PACK_RESPONSE"
```

Then:

```bash
FETCH_DIM_RESP=$(
  curl -sS -X GET "${BASE_URL}/rts-dispatch/fetch-auto-dimensions?barcode_identifier=${WAYBILL_BARCODE}" \
    -H "Authorization: Bearer ${AUTH_TOKEN}" \
    -H "fc-uuid: ${FC_UUID}" \
    -H "User-Uuid: ${USER_UUID}" \
    -H "Accept: */*" \
    -H "Content-Type: application/json"
)
echo "${FETCH_DIM_RESP}"
```

**Expected**: HTTP 200, `"success": true`, message `"Dimensions were successfully fetched for the box"`.

---

This single `.md` can be pasted into any repo and run top‑to‑bottom (with a valid QA2 token and `jq` installed). If it behaves differently in the other repo, it means the **runtime IDs are being modified, not the static config**, and you can diff behaviour step‑by‑step. 



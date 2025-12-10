# Outbound Normal Flow API Collection

This document contains 18 APIs for the WMS Outbound Normal flow in the **QA2** environment.
These requests use concrete data and credentials. You can copy-paste them into your automation tool.

**Prerequisites**:
- Generate unique IDs for `order_number`, `shipment_number`, `invoice_number`, and `container_id` for each run.
- Use the Token obtained from Step 0 in the `Authorization` header for all subsequent requests (`Bearer <token>`).

## Environment Configuration
- **Base URL**: `https://qa2-api-wms.delhivery.com/wms/qa2`
- **FC UUID**: `28d521fc52bf47e08f59c162dfc883e5` (AUTOFC1)
- **Client UUID**: `d3ae2addecbe4d5dbb3425482a478658`
- **User UUID**: `UMSAU002`
- **Username**: `autouser`
- **Password**: `Delhivery@1234`
- **Channel**: `AUTOCHANNEL`
- **FC Name**: `AUTOFC1`

---

## 0. Login (Get Token)
*Use this token for all subsequent requests.*
- **Endpoint**: `POST https://qa2-api-wms.delhivery.com/wms/qa2/auth/user/authenticate/`
- **Payload**:
  ```json
  {
    "username": "autouser",
    "password": "Delhivery@1234"
  }
  ```
- **Extract**: `data.access_token` -> Use as `Bearer <token>` in headers.

---

## 1. Create Order
*Note: Use unique values for order/shipment/invoice numbers.*
- **Endpoint**: `POST https://qa2-api-wms.delhivery.com/wms/qa2/order-management/order/create`
- **Headers**:
  - `fc-uuid`: `28d521fc52bf47e08f59c162dfc883e5`
  - `User-Uuid`: `UMSAU002`
- **Payload**:
  ```json
  {
    "requestId": null,
    "workflowId": null,
    "activityName": "CREATE",
    "data": {
      "order": {
        "orderNumber": "OD_AUTO_{{TIMESTAMP}}",
        "orderDate": "2024-01-01 10:00:00",
        "orderType": "FWD",
        "channel": "AUTOCHANNEL",
        "shipment": {
          "ewaybillExpiryDate": 0,
          "shippedBy": null,
          "number": "SH_AUTO_{{TIMESTAMP}}",
          "workflow": "31",
          "childCourier": "Delhivery",
          "fc": "AUTOFC1",
          "ewaybill_Expiry_Date": 0,
          "invoice": {
            "invoiceNumber": "INV_AUTO_{{TIMESTAMP}}",
            "paymentMode": "PREPAID",
            "netAmount": "1234.00"
          },
          "orderLine": {
            "number": "OL1",
            "productSku": "sku_p1",
            "quantity": 1,
            "clientId": "d3ae2addecbe4d5dbb3425482a478658",
            "bucket": "PRIME",
            "invoice": null
          },
          "extras": null
        },
        "consignee": {
          "name": "API Test User",
          "addressLine1": "Plot 5, Sector 44",
          "pinCode": 122001,
          "city": "Gurgaon",
          "state": "Haryana",
          "country": "India",
          "primaryPhoneNumber": "9876543210",
          "secondaryPhoneNumber": "9876543211"
        },
        "extras": null
      }
    }
  }
  ```
- **Response**: Save `request_id`.

## 2. Get Request Status (Order)
- **Endpoint**: `GET https://qa2-api-wms.delhivery.com/wms/qa2/request-tracker/logs`
- **Query Params**: `request_id={{request_id}}`
- **Headers**: `fc-uuid`, `User-Uuid`
- **Wait For**: `rec_state` = "SUCCESS"
- **Extract**: `data.data.shipments[0].shipment_id` -> Save as `{{shipmentId}}`.

## 3. Create Pickwave
- **Endpoint**: `POST https://qa2-api-wms.delhivery.com/wms/qa2/pick/pickwave/create`
- **Headers**: `fc-uuid`, `User-Uuid`
- **Payload**:
  ```json
  {
    "create_by": "shipments",
    "fc": "28d521fc52bf47e08f59c162dfc883e5",
    "shipment_count": 1,
    "filters": {
      "shipment_id": [{{shipmentId}}]
    },
    "workflow": "31",
    "created_by": "autouser"
  }
  ```
- **Response**: Save `request_id`.

## 4. Get Request Status (Pickwave)
- **Endpoint**: `GET https://qa2-api-wms.delhivery.com/wms/qa2/request-tracker/logs`
- **Query Params**: `request_id={{request_id}}`
- **Wait For**: `rec_state` = "SUCCESS"
- **Extract**: `data.data.picklist_id` -> Save as `{{picklistId}}`.

## 5. Get Pickwave Filters
- **Endpoint**: `GET https://qa2-api-wms.delhivery.com/wms/qa2/pick/pickwave/filters/v2`
- **Query Params**:
  - `fulfillmentCenter`: `28d521fc52bf47e08f59c162dfc883e5`
  - `multi_container`: `false`
  - `page`: `0`
  - `shipmentNumber`: `SH_AUTO_{{TIMESTAMP}}`
  - `status`: `CREATED`

## 6. Assign Picklist to User
- **Endpoint**: `PUT https://qa2-api-wms.delhivery.com/wms/qa2/pick/picklist/assign/user/`
- **Payload**:
  ```json
  {
    "picklist_ids": ["{{picklistId}}"],
    "user_uuid": "UMSAU002",
    "fulfillment_center_uuid": "28d521fc52bf47e08f59c162dfc883e5"
  }
  ```

## 7. Create Container
*Generate unique container ID.*
- **Endpoint**: `POST https://qa2-api-wms.delhivery.com/wms/qa2/fulfillment-inventory-management/container/create`
- **Payload**:
  ```json
  {
    "container_id": "CONT_AUTO_{{TIMESTAMP}}",
    "status": null,
    "is_movable": true,
    "fulfillment_center_uuid": "28d521fc52bf47e08f59c162dfc883e5",
    "is_virtual": false,
    "container_type": "shelf"
  }
  ```
- **Save**: `{{containerId}}`

## 8. Assign Pick Container
- **Endpoint**: `PUT https://qa2-api-wms.delhivery.com/wms/qa2/pick/picklist/assign/container`
- **Payload**:
  ```json
  {
    "picklist_id": "{{picklistId}}",
    "container_id": "{{containerId}}",
    "fulfillment_center_uuid": "28d521fc52bf47e08f59c162dfc883e5"
  }
  ```

## 9. Get Picklist Details
- **Endpoint**: `GET https://qa2-api-wms.delhivery.com/wms/qa2/pick/get/picklist`
- **Query Params**:
  - `picklist_id`: `{{picklistId}}`
  - `fulfillment_center_uuid`: `28d521fc52bf47e08f59c162dfc883e5`
- **Extract**: `location_code` -> Save as `{{sourceLocation}}`.

## 10. Update Container Item (Pick Item)
- **Endpoint**: `PUT https://qa2-api-wms.delhivery.com/wms/qa2/pick/picklist/update/container/item`
- **Payload**:
  ```json
  {
    "picklist_id": "{{picklistId}}",
    "scanned_sku": "scan_p1",
    "container_id": "{{containerId}}",
    "quantity": 1,
    "fulfillment_center_uuid": "28d521fc52bf47e08f59c162dfc883e5",
    "location_code": "{{sourceLocation}}",
    "scan_type": "PICK"
  }
  ```

## 11. Picklist Complete
- **Endpoint**: `PUT https://qa2-api-wms.delhivery.com/wms/qa2/pick/picklist/complete`
- **Payload**:
  ```json
  {
    "picklist_id": "{{picklistId}}",
    "fulfillment_center_uuid": "28d521fc52bf47e08f59c162dfc883e5"
  }
  ```

## 12. Verify Pickwave Status (CMP)
- **Endpoint**: `GET https://qa2-api-wms.delhivery.com/wms/qa2/pick/pickwave/filters/v2`
- **Query Params**:
  - `fulfillmentCenter`: `28d521fc52bf47e08f59c162dfc883e5`
  - `multi_container`: `false`
  - `page`: `0`
  - `shipmentNumber`: `SH_AUTO_{{TIMESTAMP}}`
  - `status`: `CMP`

## 13. Initiate Pack
- **Endpoint**: `GET https://qa2-api-wms.delhivery.com/wms/qa2/pack/initiate/{{containerId}}`
- **Query Params**:
  - `fulfillment_center_uuid`: `28d521fc52bf47e08f59c162dfc883e5`

## 14. Get FIM Container Detail
- **Endpoint**: `GET https://qa2-api-wms.delhivery.com/wms/qa2/fulfillment-inventory-management/container/detail`
- **Query Params**:
  - `name`: `{{containerId}}`

## 15. Complete Box
*Important: Use quantity 1. '28194' is the ID for 'sku_p1' in QA environments.*
- **Endpoint**: `POST https://qa2-api-wms.delhivery.com/wms/qa2/pack/complete-box`
- **Payload**:
  ```json
  {
    "fulfillment_center_id": "28d521fc52bf47e08f59c162dfc883e5",
    "shipment_id": "{{shipmentId}}",
    "containers": [
      {
        "container_id": "{{containerId}}",
        "products": [
          {
            "product_id": "28194",
            "quantity": 1,
            "siob": false,
            "client_uuid": "d3ae2addecbe4d5dbb3425482a478658"
          }
        ]
      }
    ]
  }
  ```

## 16. Pack Shipment
- **Endpoint**: `POST https://qa2-api-wms.delhivery.com/wms/qa2/pack/shipment-pack`
- **Payload**:
  ```json
  {
    "shipment_id": {{shipmentId}},
    "fulfillment_center_id": "28d521fc52bf47e08f59c162dfc883e5",
    "retry": false
  }
  ```
- **Extract**: `data.boxes[0].way_bill_id` -> Save as `{{waybillNumber}}`.

## 17. Fetch Auto Dimensions
- **Endpoint**: `GET https://qa2-api-wms.delhivery.com/wms/qa2/rts-dispatch/fetch-auto-dimensions`
- **Query Params**:
  - `barcode_identifier`: `{{waybillNumber}}`

## 18. Save Auto Dimensions (RTS)
- **Endpoint**: `POST https://qa2-api-wms.delhivery.com/wms/qa2/rts-dispatch/save-auto-dimensions`
- **Payload**:
  ```json
  {
    "length": 1.1,
    "width": 2.2,
    "height": 3.3,
    "weight": 1.2,
    "barcode_identifier": "{{waybillNumber}}",
    "device_id": "1234",
    "vendor_name": "V Measure"
  }
  ```

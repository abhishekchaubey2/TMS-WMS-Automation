package builder;

import pojo.AddBagToTrip.request.AddBagToTripRequestPayload;
import pojo.ApplyNslApi.request.ApplyNslRequestPayload;
import pojo.BagIncomingApi.request.BagIncomingRequestPayload;
import pojo.FMNewPickup.request.CreateNewFmRequestPayload;
import pojo.FM.request.CBReqPayload;
import pojo.FM.request.FMOMSRequestPayload;
import pojo.FM.request.WBNSDictReqPayload;
import pojo.FmApiIncoming.Request.FmApiIncomingRequestPayload;
import pojo.GI.request.GIRequestPayload;
import pojo.TripIncomingApi.request.TripIncomingRequestPayload;
import pojo.bag.BagV3Api.request.CreateBagV3BagReqPayload;
import pojo.bag.BagV3Api.request.BagV3RequestPayload;
import pojo.bag.BagV3Api.request.CreateBagV3DWSReqPayload;
import pojo.bag.BagV3Api.request.CreateBagV3WBNSReqPayload;
import pojo.bag.CreateBagV2Api.Request.CreateBagV2BagReqPayload;
import pojo.bag.CreateBagV2Api.Request.CreateBagV2ApiRequestPayload;
import pojo.bag.CreateBagV2Api.Request.CreateBagV2WayBillReqPayload;
import pojo.bag.insta.InstaBaggingCreateApi.request.InstaBaggingCreateRequestPayload;
import pojo.bag.insta.InstaBaggingSealApi.request.InstaBaggingSealRequestPayload;
import pojo.client.ClientCreateUpdate.request.ClientCreateUpdateReqPayload;
import pojo.manifest.request.CMUManifestRequestPayload;
import pojo.manifest.request.ItemRequestPayload;
import pojo.manifest.request.PickupLocationRequestPayload;
import pojo.manifest.request.QCRequestPayload;
import pojo.manifest.request.ShipmentRequestPayload;
import common.utils.ExpressDateTimeUtility;
import common.utils.ExpressUtilities;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class ExpressRequestBuilder {
    public static CMUManifestRequestPayload cmuManifestRequestPayload(List<Map<String, Object>> shipmentList, List<Map<String, Object>> qcDataList, Map<String, Object> pickupLocationData) {
        CMUManifestRequestPayload cmuManifestRequestPayload = CMUManifestRequestPayload.builder()
                .shipments(shipmentList(shipmentList, qcDataList))
                .dispatchDate(ExpressDateTimeUtility.getDateTime(0L))
                .build();

        if (!pickupLocationData.isEmpty()) {
            cmuManifestRequestPayload.setPickupLocation(pickupLocationRequestPayload(pickupLocationData));
        }
        return cmuManifestRequestPayload;
    }

    private static PickupLocationRequestPayload pickupLocationRequestPayload(Map<String, Object> data) {
        return PickupLocationRequestPayload.builder()
                .name(data.get("name").toString())
                .build();
    }

    private static List<ShipmentRequestPayload> shipmentList(List<Map<String, Object>> dataList, List<Map<String, Object>> qcDataList) {
        List<ShipmentRequestPayload> shipmentsData = new ArrayList<>();
        dataList.forEach(map -> {
            ShipmentRequestPayload shipment = createShipment(map, qcDataList);
            shipmentsData.add(shipment);
        });
        return shipmentsData;
    }

    private static ShipmentRequestPayload createShipment(Map<String, Object> data, List<Map<String, Object>> qcDataList) {
        ShipmentRequestPayload.ShipmentRequestPayloadBuilder builder = ShipmentRequestPayload.builder()
                .waybill("")
                .client(Optional.ofNullable(data.get("client")).map(Object::toString).orElse(""))
                .name(Optional.ofNullable(data.get("name")).map(Object::toString).orElse("Test"))
                .order(Optional.ofNullable(data.get("order")).map(Object::toString).orElse(ExpressUtilities.generateUniqueEntity("order")))
                .packageCount(
                        Optional.ofNullable(data.get("package_count"))
                                .map(Object::toString)
                                .map(Long::parseLong)
                                .orElse(1L)
                )
                .paymentMode(Optional.ofNullable(data.get("payment_mode")).map(Object::toString).orElse("Prepaid"))
                .mpsAmount(
                        Optional.ofNullable(data.get("mps_amount"))
                                .map(Object::toString)
                                .map(Long::parseLong)
                                .orElse(0L)
                )
                .productType(Optional.ofNullable(data.get("product_type")).map(Object::toString).orElse(""))
                .add(Optional.ofNullable(data.get("address")).map(Object::toString).orElse(""))
                .qty(
                        Optional.ofNullable(data.get("quantity"))
                                .map(Object::toString)
                                .map(Integer::parseInt)
                                .orElse(1)
                )
                .productsDesc(Optional.ofNullable(data.get("products_desc")).map(Object::toString).orElse(""))
                .orderDate(
                        Optional.ofNullable(data.get("order_date"))
                                .map(Object::toString)
                                .orElse(ExpressDateTimeUtility.getDateTime(0L))
                )
                .totalAmount(
                        Optional.ofNullable(data.get("total_amount"))
                                .map(Object::toString)
                                .map(Long::parseLong)
                                .orElse(0L)
                )
                .country(Optional.ofNullable(data.get("country"))
                        .map(Object::toString).orElse(""))
                .clientGstTin(Optional.ofNullable(data.get("client_gst_tin"))
                        .map(Object::toString).orElse(""))
                .consigneeGstTin(Optional.ofNullable(data.get("consignee_gst_tin"))
                        .map(Object::toString).orElse(""))
                .invoiceReference(Optional.ofNullable(data.get("invoice_reference"))
                        .map(Object::toString).orElse(""))
                .sellerGstTin(Optional.ofNullable(data.get("seller_gst_tin"))
                        .map(Object::toString).orElse(""))
                .phone(Optional.ofNullable(data.get("phone")).map(Object::toString)
                        .orElse("6000000001"))
                .returnPhone(Optional.ofNullable(data.get("return_phone"))
                        .map(Object::toString).orElse(""))
                .returnPin(Optional.ofNullable(data.get("return_pin"))
                        .map(Object::toString).orElse(""))
                .returnAdd(Optional.ofNullable(data.get("return_address"))
                        .map(Object::toString).orElse(""))
                .returnCity(Optional.ofNullable(data.get("return_city"))
                        .map(Object::toString).orElse(""))
                .returnCountry(Optional.ofNullable(data.get("return_country"))
                        .map(Object::toString).orElse(""))
                .pin(Optional.ofNullable(data.get("pin")).map(Object::toString).
                        orElse(""))
                .supplier(Optional.ofNullable(data.get("supplier")).map(Object::toString)
                        .orElse("Kangaroo (India) Pvo Ltd"))
                .billableWeight(Double.valueOf(data.getOrDefault("billable_weight",
                        655.0).toString()))
                .dimensions(Optional.ofNullable(data.get("dimensions")).map(Object::toString).orElse("9.00CM x 9.00CM x 9.00CM"))
                .volumetric(Double.valueOf(data.getOrDefault("volumetric",650.0).toString()))
                .hsnCode(Optional.ofNullable(data.get("hsn_code")).map(Object::toString).orElse(""))
                .codAmount(
                        Optional.ofNullable(data.get("cod_amount"))
                                .map(Object::toString)
                                .map(Long::parseLong)
                                .orElse(1L)
                )
                .qualityCheck(
                        Optional.ofNullable(data.get("quality_check"))
                                .map(Object::toString)
                                .map(Boolean::parseBoolean)
                                .orElse(false)
                );

        if (!qcDataList.isEmpty()) {
            builder.qc(qcRequestPayload(qcDataList));
        }

        return builder.build();
    }


    private static QCRequestPayload qcRequestPayload(List<Map<String, Object>> data) {
        return QCRequestPayload.builder()
                .Item(itemList(data))
                .build();
    }

    private static List<ItemRequestPayload> itemList(List<Map<String, Object>> itemData) {
        List<ItemRequestPayload> itemPayloadList = new ArrayList<>();
        itemData.forEach(map -> {
            System.out.println("Map " + map);
            ItemRequestPayload item = item(map);
            itemPayloadList.add(item);
        });
        return itemPayloadList;
    }

    private static ItemRequestPayload item(Map<String, Object> itemData) {
        System.out.println("item " + itemData);
        return ItemRequestPayload.builder()
                .descr(itemData.get("description").toString())
                .ean(itemData.get("ean").toString())
                .brand(itemData.get("brand").toString())
                .si(itemData.get("si").toString())
                .imei(itemData.get("imei").toString())
                .reason(itemData.get("reason").toString())
                .images(itemData.get("images").toString())
                .serial(itemData.get("serial").toString())
                .pcat(itemData.get("p_cat").toString())
                .itemQuantity(itemData.get("item_quantity").toString())
                .build();
    }

    public static ClientCreateUpdateReqPayload clientCreateUpdateReqGen(Map<String, Object> clientCreateUpdateData) {
        ClientCreateUpdateReqPayload clientCreateUpdatereqPayload = ClientCreateUpdateReqPayload.builder()
                .build();

        clientCreateUpdateData.forEach((key, value) -> {
            switch (key) {
                case "billing_method":
                    clientCreateUpdatereqPayload.setBillingMethod(value.toString());
                    break;
                case "salesforce_id":
                    clientCreateUpdatereqPayload.setSalesforceId(value.toString());
                    break;
                case "phone":
                    clientCreateUpdatereqPayload.setPhone(value.toString());
                    break;
                case "client_name":
                    clientCreateUpdatereqPayload.setClientName(value.toString());
                    break;
                case "report_recipients":
                    clientCreateUpdatereqPayload.setReportRecipients(value.toString());
                    break;
                case "billing_mode":
                    clientCreateUpdatereqPayload.setBillingMode(value.toString());
                    break;
                case "client_type":
                    clientCreateUpdatereqPayload.setClientType(value.toString());
                    break;
                case "wallet_provider":
                    clientCreateUpdatereqPayload.setWalletProvider(value.toString());
                    break;
                case "wallet_notification_email":
                    clientCreateUpdatereqPayload.setWalletNotificationEmail(value.toString());
                    break;
                case "client_first_name":
                    clientCreateUpdatereqPayload.setClientFirstName(value.toString());
                    break;
                case "client_last_name":
                    clientCreateUpdatereqPayload.setClientLastName(value.toString());
                    break;
                case "password":
                    clientCreateUpdatereqPayload.setPassword(value.toString());
                    break;
                case "wallet_notification_mobile":
                    clientCreateUpdatereqPayload.setWalletNotificationMobile(value.toString());
                    break;
                case "xray_amount_limit":
                    clientCreateUpdatereqPayload.setXrayAmountLimit(Long.parseLong(value.toString()));
                    break;
                case "is_prepaid_service":
                    clientCreateUpdatereqPayload.setIsPrepaidService(Boolean.parseBoolean(value.toString()));
                    break;
                case "is_mps":
                    clientCreateUpdatereqPayload.setIsMps(Boolean.parseBoolean(value.toString()));
                    break;
                case "is_no_data":
                    clientCreateUpdatereqPayload.setIsNoData(Boolean.parseBoolean(value.toString()));
                    break;
                case "product_type":
                    clientCreateUpdatereqPayload.setProductType(value.toString());
                    break;
            }
        });

        return clientCreateUpdatereqPayload;
    }

    public static CreateNewFmRequestPayload createNewFmRequestPayload(Map<String, Object> data) {
        return CreateNewFmRequestPayload.builder()
                .pickupDate(ExpressDateTimeUtility.getDateTime(Long.parseLong(data.get("pickup_date").toString())))
                .pickupTime(data.get("pickup_time").toString())
                .pickupLocation(data.get("warehouse").toString())
                .expectedPackageCount(Integer.parseInt(data.get("expected_package_count").toString()))
                .build();
    }

    public static FMOMSRequestPayload fmOmsReqPayload(Map<String, Object> data, List<String> waybills) {
        return FMOMSRequestPayload.builder()
                .waybillsDict(wbnsDictReqPayloadMap(data, waybills))
                .clientId(data.get("client_uuid").toString())
                .warehouseId(data.get("warehouse_uuid").toString())
                .requestId("FGAB")
                .cb(cbReqPayload(data))
                .location_id(data.get("location_id").toString())
                .event_code(data.get("event_code").toString())
                .sync(true)
                .build();
    }

    private static Map<String, WBNSDictReqPayload> wbnsDictReqPayloadMap(Map<String, Object> data, List<String> waybills) {
        Map<String, WBNSDictReqPayload> wbnsDictReqPayloadMap = new HashMap<>();
        waybills.forEach(wbn -> {
            wbnsDictReqPayloadMap.put(wbn, wbnsDictReqPayload(data));
        });
        return wbnsDictReqPayloadMap;
    }

    private static WBNSDictReqPayload wbnsDictReqPayload(Map<String, Object> data) {
        return WBNSDictReqPayload.builder()
                .scanTime(data.getOrDefault("scan_time", ExpressDateTimeUtility.getISTDateTimeWithDayHourMinuteAndSecond(0L, 0L,
                        0L, 0L)).toString())
                .build();
    }

    private static CBReqPayload cbReqPayload(Map<String, Object> data) {
        return CBReqPayload.builder()
                .uri(data.get("fm_uri").toString())
                .method("PATCH")
                .build();
    }

    public static FmApiIncomingRequestPayload fmApiIncomingReqGen(String waybill, Map<String, Object> data) {
        return FmApiIncomingRequestPayload.builder()
                .client(data.get("client").toString())
                .waybill(waybill)
                .center(data.get("center").toString())
                .build();
    }

    public static GIRequestPayload giRequestPayload(List<String> waybills, Map<String, Object> data) {
        return GIRequestPayload.builder()
                .center(data.get("center").toString())
                .refIds(waybills)
                .build();
    }

    //bas is bag seal it required in insta bagging seal
    public static InstaBaggingCreateRequestPayload instaBaggingCreateRequestPayload(Map<String, Object> data) {
        return InstaBaggingCreateRequestPayload.builder()
                .bd(data.get("bd").toString())
                .bi(data.get("bi").toString())
                .bs(data.getOrDefault("bs", null))
                .bt(data.get("bt").toString())
                .dpc(Boolean.parseBoolean(data.getOrDefault("dpc", true).toString()))
                .rm(Boolean.parseBoolean(data.getOrDefault("rm", false).toString()))
                .st(data.get("st").toString())
                .wbn(data.get("wbn").toString())
                .capture_weight(Boolean.parseBoolean(data.getOrDefault("capture_weight", false).toString()))
                .build();
    }

    public static InstaBaggingSealRequestPayload instaBaggingSealReqPayload(Map<String, Object> data) {
        InstaBaggingSealRequestPayload payload = InstaBaggingSealRequestPayload.builder()
                .bd(data.get("bd").toString())
                .bi(data.get("bi").toString())
                .bs(data.getOrDefault("bs", ExpressUtilities.generateUniqueEntity("BAG")).toString())
                .bt(data.get("bt").toString())
                .wbn("")
                .bar("DV" + ExpressUtilities.getUniqueInt(8))
                .st(data.get("st").toString())
                .rm(Boolean.parseBoolean(data.getOrDefault("rm", false).toString()))
                .dpc("true")
                .build();

        return payload;
    }

    public static CreateBagV2ApiRequestPayload bagV2ApiReqGen(Map<String, Object> data, List<Map<String, Object>> waybillDataMapList) {
        return CreateBagV2ApiRequestPayload.builder()
                .bs(data.getOrDefault("bs", ExpressUtilities.generateUniqueEntity("BAG")).toString())
                .origin(data.get("origin_center").toString())
                .sd(data.getOrDefault("sd", ExpressDateTimeUtility.getDateTime(0)).toString())
                .destination(data.get("destination_center").toString())
                .ed(data.getOrDefault("ed", ExpressDateTimeUtility.getDateTime(2)).toString())
                .deviceId(data.getOrDefault("device_id", "86936116504").toString())
                .u(data.getOrDefault("u", "akshat.jain3").toString())
                .bt(data.getOrDefault("bt", "Surface").toString())
                .wbns(createBagV2WaybillRequestPayload(waybillDataMapList))
                .build();
    }

    private static Map<String, CreateBagV2WayBillReqPayload> createBagV2WaybillRequestPayload(List<Map<String, Object>> data) {
        Map<String, CreateBagV2WayBillReqPayload> waybillMap = new HashMap<>();
        data.forEach(map -> {
            waybillMap.put(map.get("waybill").toString(), bagV2WaybillRequestPayload(map));
        });
        return waybillMap;
    }

    private static CreateBagV2WayBillReqPayload bagV2WaybillRequestPayload(Map<String, Object> data) {
        return CreateBagV2WayBillReqPayload.builder()
                .bag(createBagV2BagRequestPayload(data))
                .build();
    }

    private static CreateBagV2BagReqPayload createBagV2BagRequestPayload(Map<String, Object> data) {
        return CreateBagV2BagReqPayload.builder()
                .sd(data.getOrDefault("sd", ExpressDateTimeUtility.getDateTime(0)).toString())
                .u(data.getOrDefault("u", "akshat.jain3").toString())
                .build();
    }

    public static BagV3RequestPayload bagV3ApiReqGen(Map<String, Object> data, List<Map<String, Object>> waybillDataList) {
        return BagV3RequestPayload.builder()
                .bs(data.getOrDefault("bs", ExpressUtilities.generateUniqueEntity("BAG")).toString())
                .origin(data.get("origin_center").toString())
                .sd(data.getOrDefault("sd", ExpressDateTimeUtility.getDateTime(0)).toString())
                .destination(data.get("destination_center").toString())
                .ed(data.getOrDefault("ed", ExpressDateTimeUtility.getDateTime(2)).toString())
                .device_id(data.getOrDefault("device_id", "86936116504").toString())
                .u(data.getOrDefault("u", "akshat").toString())
                .dws(createBagV3DWSRequestPayload(data))
                .bt(data.getOrDefault("bt", "Surface").toString())
                .wbns(createBagV3WaybillRequestPayload(waybillDataList))
                .build();
    }

    private static CreateBagV3DWSReqPayload createBagV3DWSRequestPayload(Map<String, Object> data) {
        return CreateBagV3DWSReqPayload.builder()
                .sd(data.getOrDefault("sd", ExpressDateTimeUtility.getDateTime(0)).toString())
                .u(data.getOrDefault("u", "akshat.jain3").toString())
                .rv(data.getOrDefault("rv", "0.0").toString())
                .h(data.getOrDefault("h", "10").toString())
                .b(data.getOrDefault("b", "11").toString())
                .l(data.getOrDefault("l", "12").toString())
                .wt(data.getOrDefault("wt", "220").toString())
                .isBox(Boolean.parseBoolean(data.getOrDefault("is_box", true).toString()))
                .isVol(Boolean.parseBoolean(data.getOrDefault("is_vol", true).toString()))
                .minTh(Integer.parseInt(data.getOrDefault("min_th", 0).toString()))
                .build();
    }

    private static Map<String, CreateBagV3WBNSReqPayload> createBagV3WaybillRequestPayload(List<Map<String, Object>> data) {
        Map<String, CreateBagV3WBNSReqPayload> waybillMap = new HashMap<>();
        data.forEach(map -> waybillMap.put(map.get("waybill").toString(), bagV3WaybillRequestPayload(map)));
        return waybillMap;
    }

    private static CreateBagV3WBNSReqPayload bagV3WaybillRequestPayload(Map<String, Object> data) {
        return CreateBagV3WBNSReqPayload.builder()
                .bag(createBagV3BagRequestPayload(data))
                .build();
    }

    private static CreateBagV3BagReqPayload createBagV3BagRequestPayload(Map<String, Object> data) {
        return CreateBagV3BagReqPayload.builder()
                .sd(data.getOrDefault("sd", ExpressDateTimeUtility.getDateTime(0)).toString())
                .u(data.getOrDefault("u", "akshat.jain3").toString())
                .build();
    }

    public static AddBagToTripRequestPayload bagAddToTripApiReqGen(Map<String, Object> data) {
        return AddBagToTripRequestPayload.builder()
                .refId(data.get("bag_id").toString())
                .trid(data.getOrDefault("trip_id", ExpressUtilities.generateUniqueEntity("TRIP")).toString())
                .slid(data.get("origin_center_id").toString())
                .ntcid(data.get("destination_center_id").toString())
                .vid(data.getOrDefault("vehicle_id", "UP78BY1961").toString())
                .mawb(data.getOrDefault("mawb", "1234567890").toString())
                .action("add")
                .build();
    }

    public static TripIncomingRequestPayload tripIncomingApiReqGen(Map<String, Object> data) {
        return TripIncomingRequestPayload.builder()
                .trid(data.get("trip_id").toString())
                .slid(data.get("destination_center_id").toString())
                .build();

    }

    public static BagIncomingRequestPayload bagIncomingApiReqGen(Map<String, Object> data) {
        return BagIncomingRequestPayload.builder()
                .refId(data.get("bag_id").toString())
                .slid(data.get("destination_center_id").toString())
                .unexpected(Integer.parseInt(data.getOrDefault("unexpected", 0).toString()))
                .build();
    }

    public static ApplyNslRequestPayload applyNslRequestPayload(Map<String, Object> data, List<String> waybills) {
        return ApplyNslRequestPayload.builder()
                .nslId(data.get("nsl_id").toString())
                .scope("Package")
                .statusType(data.get("status_type").toString())
                .wbns(waybills)
                .status(data.getOrDefault("status", "").toString())
                .user("akshat.jain3")
                .build();
    }
}

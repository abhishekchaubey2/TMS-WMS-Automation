package com.delhivery.Express.RequestBuilder;

import com.delhivery.Express.pojo.AgWt.Request.Agwtpayload;
import com.delhivery.Express.pojo.AgWt.Request.MaxWt;
import com.delhivery.Express.pojo.ApiPhonelog.PhonelogRequest;
import com.delhivery.Express.pojo.ApplyNslApi.request.ApplyNslRequestPayload;
import com.delhivery.Express.pojo.AutobagApi.Request.AutobagRequestPayload;
import com.delhivery.Express.pojo.BagAddToTripApi.request.BagAddToTripRequestPayload;
import com.delhivery.Express.pojo.BagRemoveTripApi.request.BagRemoveTripRequestPayload;
import com.delhivery.Express.pojo.CMS.CreateClaimsApi.request.CreateClaimWaybill;
import com.delhivery.Express.pojo.CMS.CreateClaimsApi.request.CreateClaimsRequestPayload;
import com.delhivery.Express.pojo.CMS.FetchB2bClaimsDetailsApi.requestParamFilter.FetchB2bClaimsDetailsRequestParam;
import com.delhivery.Express.pojo.CMS.FetchB2bClaimsListingApi.requestParamFilter.FetchB2bClaimsListingRequestParam;
import com.delhivery.Express.pojo.CMS.FetchB2cClaimsListingApi.requestParamFilter.FetchB2cClaimsListingRequestParam;
import com.delhivery.Express.pojo.CMS.FetchClaimsReportApi.requestParamFilter.FetchClaimsReportRequestParam;
import com.delhivery.Express.pojo.CMS.FetchUcpLossClaimsApi.requestParamFilter.FetchUcpLossClaimsRequestParam;
import com.delhivery.Express.pojo.CMS.UpdateClaimSettlementAmountApi.request.UpdateClaimSettlementAmountRequestPayload;
import com.delhivery.Express.pojo.ClientCreateUpdate.Request.UpdateRegressionClientRequestPayload;
import com.delhivery.Express.pojo.PkgRemoveFromBagApi.request.PkgRemoveFromBagRequestPayload;
import com.delhivery.Express.pojo.BagCustodyScanApi.Request.BagCustodyScanRequestPayload;
import com.delhivery.Express.pojo.BagIncomingApi.request.BagIncomingRequestPayload;
import com.delhivery.Express.pojo.BagInfo.Request.BagInfoRequestPayload;
import com.delhivery.Express.pojo.BagV3Api.request.Bag;
import com.delhivery.Express.pojo.BagV3Api.request.BagV3RequestPayload;
import com.delhivery.Express.pojo.BagV3Api.request.Wbns;
import com.delhivery.Express.pojo.BulkNdrEditApi.Request.ActionData;
import com.delhivery.Express.pojo.BulkNdrEditApi.Request.BulkNdrRequestPayload;
import com.delhivery.Express.pojo.BulkNdrEditApi.Request.NdrData;
import com.delhivery.Express.pojo.BulkNdrEditApi.Request.WaybillEdit;
import com.delhivery.Express.pojo.CenterUpdate.Request.CenterUpdateRequest;
import com.delhivery.Express.pojo.CenterUpdate.Request.UpdateDatum;
import com.delhivery.Express.pojo.CenterUpdateRT.Request.CenterUpdateRequestRT;
import com.delhivery.Express.pojo.CenterUpdateRT.Request.UpdateDatumRT;
import com.delhivery.Express.pojo.ClientCreateUpdate.Request.CreateUpdateClientRequestPayload;
import com.delhivery.Express.pojo.ClientUpdateApi.Request.ClientUpdateRequestPayloadJava;
import com.delhivery.Express.pojo.ClientUpdateApi.Request.GstStatesReq;
import com.delhivery.Express.pojo.ClientUpdateApi.Request.Services;
import com.delhivery.Express.pojo.CmuPush.Request.CmuPushRequestPayload;
import com.delhivery.Express.pojo.CmuPush.Request.ItemDesc;
import com.delhivery.Express.pojo.CmuPush.Request.PushShipment;
import com.delhivery.Express.pojo.CmuV2ManifestNoDataShipmentApi.request.CmuV2ManifestNoDataShipmentApiRequestPayload;
import com.delhivery.Express.pojo.CreateBagV2Api.Request.CreateBagV2ApiRequestPayload;
import com.delhivery.Express.pojo.CreateBagV2Api.Request.WbnsV2;
import com.delhivery.Express.pojo.CreateBagV4.Request.BagV4;
import com.delhivery.Express.pojo.CreateBagV4.Request.CreateBagV4RequestPayload;
import com.delhivery.Express.pojo.CreateBagV4.Request.Datum;
import com.delhivery.Express.pojo.CreateBagV4.Request.Dws;
import com.delhivery.Express.pojo.CreateBagV4.Request.Image;
import com.delhivery.Express.pojo.CreateBagV4.Request.PrimaryV4;
import com.delhivery.Express.pojo.CreateBagV4.Request.Secondary;
import com.delhivery.Express.pojo.CreateBagV4.Request.WaybillV4;
import com.delhivery.Express.pojo.CreateBagV4.Request.WbnsV4;
import com.delhivery.Express.pojo.CreateNoDataUplApi.request.CreateNoDataUplApiRequestPayload;
import com.delhivery.Express.pojo.CreateNoDataUplApi.request.Invoice;
import com.delhivery.Express.pojo.CustomEditApi.Request.CustomEditApiRequestPayload;
import com.delhivery.Express.pojo.DispatchFreeze.Request.DispatchFreezeRequestPayload;
import com.delhivery.Express.pojo.EditApi.request.EditApiRequestPayload;
import com.delhivery.Express.pojo.EditPhone.Request.ActionDataEditPhone;
import com.delhivery.Express.pojo.EditPhone.Request.DatumEditPhone;
import com.delhivery.Express.pojo.EditPhone.Request.EditPhoneRequestPayload;
import com.delhivery.Express.pojo.FMOMSApi.request.Cb;
import com.delhivery.Express.pojo.FMOMSApi.request.FMOMSRequestPayload;
import com.delhivery.Express.pojo.FMOMSApi.request.Waybill;
import com.delhivery.Express.pojo.FMOMSApi.request.Wbns_dict;
import com.delhivery.Express.pojo.FetchBagMatrix.Request.FetchBagMatrixRequestPayload;
import com.delhivery.Express.pojo.FetchList.Requist.FetchListRequest;
import com.delhivery.Express.pojo.FmApiIncoming.Request.FmApiIncomingRequestPayload;
import com.delhivery.Express.pojo.GIApi.request.GIRequestPayload;
import com.delhivery.Express.pojo.InstaBaggingCreateApi.request.InstaBaggingCreateRequestPayload;
import com.delhivery.Express.pojo.InstaBaggingSealApi.request.InstaBaggingSealRequestPayload;
import com.delhivery.Express.pojo.LMUpdateHQShipmentApi.request.Add_info;
import com.delhivery.Express.pojo.LMUpdateHQShipmentApi.request.Data;
import com.delhivery.Express.pojo.LMUpdateHQShipmentApi.request.LMUpdateHQShipmentRequestPayload;
import com.delhivery.Express.pojo.LMUpdateHQShipmentApi.request.Payload;
import com.delhivery.Express.pojo.LocationAssociate.Request.LocationAssociateRequestPayload;
import com.delhivery.Express.pojo.LocationDissociate.Request.LocationDissociateRequestPayload;
import com.delhivery.Express.pojo.ManifestCmuCreateApi.request.CmuManifestApi;
import com.delhivery.Express.pojo.ManifestCmuCreateApi.request.Item;
import com.delhivery.Express.pojo.ManifestCmuCreateApi.request.Shipment;
import com.delhivery.Express.pojo.ManifestCmuCreateApi.request.pickup_location;
import com.delhivery.Express.pojo.ManifestCmuCreateApi.request.Qc;
import com.delhivery.Express.pojo.ManifestCmuCreateWithCwhApi.request.CmuManifestRequestPayload;
import com.delhivery.Express.pojo.MarkDelivered.Request.MarkDeliveredRequestPayload;
import com.delhivery.Express.pojo.MarkDispatchApi.request.MarkDispatchRequestPayload;
import com.delhivery.Express.pojo.MarkPendingApi.Request.MarkPendingRequestPayload;
import com.delhivery.Express.pojo.NewFm.Request.NewFmRequestPayload;
import com.delhivery.Express.pojo.ODTat.Request.DataODTat;
import com.delhivery.Express.pojo.ODTat.Request.ODTatRequestPayload;
import com.delhivery.Express.pojo.PackageCancel.Request.PackageCancelRequestPayload;
import com.delhivery.Express.pojo.PackageStatusUpdate.Request.PackageStatusUpdateRequestPayload;
import com.delhivery.Express.pojo.PackageStatusUpdate.Request.WbnPackageStatusUpdate;
import com.delhivery.Express.pojo.PackageUpdate.Request.PackageUpdateRequestPayload;
import com.delhivery.Express.pojo.QcWtApi.request.QcWtPayload;
import com.delhivery.Express.pojo.Qrcode.Request.QrcodeapiPayload;
import com.delhivery.Express.pojo.RemoveEwbnApi.Request.RemoveEwbnRequestPayload;
import com.delhivery.Express.pojo.SelfCollectApi.Request.AddInfo;
import com.delhivery.Express.pojo.SelfCollectApi.Request.SelfCollectApiRequestPayload;
import com.delhivery.Express.pojo.SelfCollectApi.Request.SelfCollectData;
import com.delhivery.Express.pojo.SelfCollectApi.Request.SelfCollectWbn;
import com.delhivery.Express.pojo.TripIncomingApi.request.TripIncomingRequestPayload;
import com.delhivery.Express.pojo.UnsetReturnDispatchId.request.UnsetReturnDispatchIdReqPayload;
import com.delhivery.Express.pojo.UnsetShipmentDispatchIdApi.request.UnsetShipmentDispatchIdRequestPayload;
import com.delhivery.Express.pojo.UpdateBagStatus.Request.UpdateBagStatusRequestPayload;
import com.delhivery.Express.pojo.UpdateFinalWt.Request.UpdateFinalWtRequestPayload;
import com.delhivery.Express.pojo.UpdateTransId.Request.Cburl;
import com.delhivery.Express.pojo.UpdateTransId.Request.UpdateTransIdRequestPayload;
import com.delhivery.Express.pojo.UpdateTransId.Request.WbnTransIdUpdateBulk;
import com.delhivery.Express.pojo.UpdateTransId.Request.WbnsDict;
import com.delhivery.Express.pojo.UpdateTransId1.Request.UpdateTransId1RequestPayload;
import com.delhivery.Express.pojo.UpdateTransId1.Request.UpdateTransIdDexCburl;
import com.delhivery.Express.pojo.UpdateTransId1.Request.UpdateTransIdDexData;
import com.delhivery.Express.pojo.UpdateTransId1.Request.UpdateTransIdDexWaybill;
import com.delhivery.Express.pojo.UpdateTransId1.Request.UpdateTransIdDexWbnsDict;
import com.delhivery.Express.pojo.UpdatedBagmatrix.Request.UpdatedBagmatrixRequestPayload;
import com.delhivery.Express.pojo.WrhCreate.Request.CreateRequest;
import com.delhivery.Express.pojo.WrhEdit.Request.EditRequest;
import com.delhivery.Express.pojo.WrhStatus.Request.StatusRequest;
import com.delhivery.Express.pojo.ewaybill.APIRestEWayBill.request.APIRestDataPayload;
import com.delhivery.Express.pojo.ewaybill.APIRestEWayBill.request.APIRestEWayBillRequestPayload;
import com.delhivery.Express.pojo.applynsl.request.applynsl;
import com.delhivery.Express.pojo.applynslgeneric.request.Applynslgeneric;
import com.delhivery.Express.pojo.applynslgeneric.request.Wbn;
import com.delhivery.Express.pojo.rebag.request.ReBagDataReqPayload;
import com.delhivery.Express.pojo.rebag.request.ReBagReqPayload;
import com.delhivery.Express.pojo.ewbnCreate.request.Dataewbn;
import com.delhivery.Express.pojo.ewbnCreate.request.TripItem;
import com.delhivery.Express.pojo.ewbnCreate.request.ewbncreation;
import com.delhivery.Express.pojo.pUpdate.ActionDataPUpdate;
import com.delhivery.Express.pojo.pUpdate.PUpdatePayload;
import com.delhivery.core.BaseTest;


import com.delhivery.core.utils.ConfigLoader;
import com.delhivery.core.utils.DateTimeUtility;
import com.delhivery.core.utils.ProdConfigLoader;
import com.delhivery.core.utils.Utilities;
import com.delhivery.core.utils.YMLDataHandler;
import com.delhivery.core.utils.YamlReader;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;


import static com.delhivery.core.utils.DateTimeUtility.getDateTime;
import static com.delhivery.core.utils.Utilities.getUniqueInt;
import static com.delhivery.core.utils.Utilities.getUniqueString;

public class RequestBuilder extends BaseTest {
    static ObjectMapper objectMapper = new ObjectMapper();
    static File file;

    protected static Map<String, Object> clientDetails = YamlReader
            .getYamlValues("Client_Details.client_" + ConfigLoader.getInstance().getRegressionClient());


    public static Map<String, Object> origin_center = YamlReader.getYamlValues("Centers.East_Delhi");
    public static Map<String, Object> destination_center = YamlReader.getYamlValues("Centers.Mumbai_MIDC");


    public static CmuManifestApi cmuManifestApiReqGen(HashMap<String, String> data) {
        List<Shipment> listShipments = new ArrayList<>();
        listShipments.add(cmuShipments(data));
        CmuManifestApi body = CmuManifestApi.builder()
                .shipments(listShipments)
                .dispatchDate("")
                .build();

        //pickup location will be added only if cwh_sent key and pickup_location both will be sent while manifesting
        if (data.containsKey("cwh_sent") && data.containsKey("pickup_location")) {
            pickup_location pl = new pickup_location();
            pl.setName(data.get("pickup_location"));
            body.setPickup_location(pl);
        }
        return body;
    }

    public static Shipment cmuShipments(HashMap<String, String> data) {
        Shipment payload = Shipment.builder()
                .lrn("")
                .name("test")
                .client(clientDetails.get("name").toString())
                .waybill("")
                .order(getUniqueString())
                .productsDesc("")
                .orderDate("")
                .paymentMode("COD")
                .codAmount(12)
                .productType("")
                .packageCount(1)
                .totalAmount(1234)
                .add("Gurgaon Sector 45")
                .city("")
                .state("")
                .country("")
                .weight("")
                .phone("6000000001")
                .international("")
                .pin("122003")
                .dangerousGood(false)
                .fragileShipment("")
                .returnAdd("")
                .returnCity("")
                .returnCountry("")
                .returnName("")
                .returnPhone("")
                .returnPin("")
                .returnState("")
                .supplier("")
                .billableWeight(1)
                .dimensions("")
                .volumetric(12)
                .clientGstTin("")
                .consigneeGstTin("")
                .hsnCode("")
                .invoiceReference("")
                .sellerGstTin("")
                .packageCount(1)
                .shippingMethod("Surface")
                //.qualityCheck("false")
                .einvQr(true)
                .isOtpVerified("false")
                .build();

        for (Map.Entry<String, String> set : data.entrySet()) {
            String k = set.getKey();
            String v = set.getValue();

            if (k.equalsIgnoreCase("products_desc")) {
                payload.setProductsDesc(v);
            } else if (k.equalsIgnoreCase("payment_mode")) {
                payload.setPaymentMode(v);
            } else if (k.equalsIgnoreCase("product_type")) {
                payload.setProductType(v);
            } else if (k.equalsIgnoreCase("weight")) {
                payload.setWeight(v);
            } else if (k.equalsIgnoreCase("add")) {
                payload.setAdd(v);
            } else if (k.equalsIgnoreCase("city")) {
                payload.setCity(v);
            } else if (k.equalsIgnoreCase("phone")) {
                payload.setPhone(v);
            } else if (k.equalsIgnoreCase("state")) {
                payload.setState(v);
            } else if (k.equalsIgnoreCase("country")) {
                payload.setCountry(v);
            } else if (k.equalsIgnoreCase("pin")) {
                payload.setPin(v);
            } else if (k.equalsIgnoreCase("return_add")) {
                payload.setReturnAdd(v);
            } else if (k.equalsIgnoreCase("return_city")) {
                payload.setReturnCity(v);
            } else if (k.equalsIgnoreCase("return_state")) {
                payload.setReturnState(v);
            } else if (k.equalsIgnoreCase("return_country")) {
                payload.setReturnCountry(v);
            } else if (k.equalsIgnoreCase("return_pin")) {
                payload.setReturnPin(v);
            } else if (k.equalsIgnoreCase("shipping_mode")) {
                payload.setShippingMode(v);
            } else if (k.equalsIgnoreCase("shipping_method")) {
                payload.setShippingMethod(v);
            } else if (k.equalsIgnoreCase("package_count")) {
                payload.setPackageCount(Long.parseLong(v));
            } else if (k.equalsIgnoreCase("weight_verification")) {
                payload.setWeightVerification(Boolean.parseBoolean(v));
            } else if (k.equalsIgnoreCase("essential_good")) {
                payload.setEssentialGood(v);
            } else if (k.equalsIgnoreCase("fragile_shipment")) {
                payload.setEssentialGood(v);
            } else if (k.equalsIgnoreCase("mps_amount")) {
                payload.setMpsAmount(Long.parseLong(v));
            } else if (k.equalsIgnoreCase("cod_amount")) {
                payload.setCodAmount(Long.parseLong(v));
            } else if (k.equalsIgnoreCase("total_amount")) {
                payload.setTotalAmount(Long.parseLong(v));
            } else if (k.equalsIgnoreCase("mps_children")) {
                payload.setMpsChildren(Long.parseLong(v));
            } else if (k.equalsIgnoreCase("master_id")) {
                payload.setMasterId(v);
            } else if (k.equalsIgnoreCase("waybill")) {
                payload.setWaybill(v);
            } else if (k.equalsIgnoreCase("internal")) {
                payload.setInternal(Boolean.parseBoolean(v));
            } else if (k.equalsIgnoreCase("shipment_type")) {
                payload.setShipmentType(v);
            } else if (k.equalsIgnoreCase("international")) {
                payload.setInternational(v);

            } else if (k.equalsIgnoreCase("einv_qr")) {
                payload.setEinvQr(v);
            } else if (k.equalsIgnoreCase("quality_check")) {
                payload.setQualityCheck(v);

            } else if (k.equalsIgnoreCase("client")) {
                //fetching client data from staging.yml file
                System.out.println(ConfigLoader.getInstance().getClient(v));
                Map<String, Object> clientDetails1 = YamlReader.getYamlValues("Client_Details.client_" +
                        ConfigLoader.getInstance().getClient(v));
                payload.setClient(clientDetails1.get("name").toString());
            } else if (k.equalsIgnoreCase("order_id")) {
                payload.setOrder(v);
            } else if (k.equalsIgnoreCase("prodClient")) {
                payload.setClient(v);
            } else if (k.equalsIgnoreCase("buyback_address")) {
                payload.setBuybackAddress(v);
            } else if (k.equalsIgnoreCase("buyback_city")) {
                payload.setBuybackCity(v);
            } else if (k.equalsIgnoreCase("buyback_pin")) {
                payload.setBuybackPin(v);
            } else if (k.equalsIgnoreCase("buyback_state")) {
                payload.setBuybackState(v);
            }
        }
        return payload;
    }

    public static CmuManifestApi rvpReqBuildRequestPayload(List<HashMap<String, String>> dataList) {
        return CmuManifestApi.builder()
                .shipments(shipmentList(dataList))
                .build();
    }

    private static List<Shipment> shipmentList(List<HashMap<String, String>> dataList) {
        return dataList.stream()
                .map(RequestBuilder::createShipment)
                .collect(Collectors.toList());
    }

    private static Shipment createShipment(HashMap<String, String> data) {
        Shipment payload = Shipment.builder()
                .waybill("")
                .client("ManifestClient")
                .name("test single seller")
                .order(data.get("order"))
                .packageCount(1)
                .paymentMode(data.get("payment_mode"))
                .productType(data.get("product_type"))
                .add("sector 45")
                .qty(Integer.valueOf(data.get("quantity")))
                .qc(qc(data))
                .productsDesc("cover")
                .orderDate(getDateTime(0L))
                .totalAmount(500)
                .country("India")
                .clientGstTin("URP")
                .consigneeGstTin("URP")
                .invoiceReference("URP")
                .sellerGstTin("URP")
                .phone("7607388048")
                .returnPhone("8218132427")
                .pin("122003")
                .supplier("Kangaroo (India) Pvo Ltd")
                .billableWeight(650)
                .dimensions("9.00CM x 9.00CM x 9.00CM")
                .volumetric(10)
                .hsnCode("9105")
                .codAmount(1)
                .build();
        payload.setQualityCheck("true");
        for (Map.Entry<String, String> set : data.entrySet()) {
            String k = set.getKey();
            String v = set.getValue();

            if (k.equalsIgnoreCase("descr")) {
                payload.getQc().getItem().get(0).setDescr(v);
            } else if (k.equalsIgnoreCase("ean")) {
                payload.getQc().getItem().get(0).setEan(v);
            } else if (k.equalsIgnoreCase("brand")) {
                payload.getQc().getItem().get(0).setBrand(v);
            } else if (k.equalsIgnoreCase("si")) {
                payload.getQc().getItem().get(0).setSi(v);
            } else if (k.equalsIgnoreCase("imei")) {
                payload.getQc().getItem().get(0).setImei(v);
            } else if (k.equalsIgnoreCase("reason")) {
                payload.getQc().getItem().get(0).setReason(v);
            } else if (k.equalsIgnoreCase("images")) {
                payload.getQc().getItem().get(0).setImages(v);
            } else if (k.equalsIgnoreCase("serial")) {
                payload.getQc().getItem().get(0).setSerial(v);
            } else if (k.equalsIgnoreCase("pcat")) {
                payload.getQc().getItem().get(0).setPcat(v);
            } else if (k.equalsIgnoreCase("item_quantity")) {
                payload.getQc().getItem().get(0).setItemQuantity(v);
            }
        }
        return payload;
    }

    private static Qc qc(HashMap<String, String> data) {
        Map<String, Object> itemData = YamlReader.getYamlValues("Item." + (data.getOrDefault("item", "cookware_item")));
        List<Map<String, Object>> itemDataList = YMLDataHandler.fetchListOfMapByKey(itemData, "list");
        return Qc.builder()
                .Item(itemList(itemDataList))
                .build();
    }

    private static List<Item> itemList(List<Map<String, Object>> itemData) {
        return itemData.stream()
                .map(RequestBuilder::item)
                .collect(Collectors.toList());
    }

    private static Item item(Map<String, Object> itemData) {

        System.out.println("item " + itemData);
        return Item.builder()
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

    public static FMOMSRequestPayload fmOmsApiReqGen(String eventCode, HashMap<String, String> clData) {
        FMOMSRequestPayload FmPayload = FMOMSRequestPayload.builder()
                .wbns_dict(Wbns_dict.builder()
                        .waybill(Waybill.builder()
                                .scan_time(DateTimeUtility.getISTDateTimeWithDayHourMinuteAndSecond(0L, 0L, 0L, 0L))
                                .build())
                        .build())
                .client_id(clientDetails.get("client_uuid").toString())
                .warehouse_id(clientDetails.get("cwh_uuid").toString())
                .request_id("FGAB")
                .cb(Cb.builder()
                        .uri("http://odx-fm-bk-stage.delhivery.com/packages/uploads/309/")
                        .method("PATCH")
                        .build())
                .location_id(origin_center.get("SortCode").toString())
                .event_code(eventCode)
                .sync(true)
                .build();

        if (clData.containsKey("client")) {
            Map<String, Object> NonDefaultclientDetails = YamlReader
                    .getYamlValues("Client_Details.client_" + ConfigLoader.getInstance().getClient(clData.get("client")));
            FmPayload.setClient_id(NonDefaultclientDetails.get("client_uuid").toString());
            if (NonDefaultclientDetails.get("cwh_uuid") != null) {
                FmPayload.setWarehouse_id(NonDefaultclientDetails.get("cwh_uuid").toString());
            }
        }

        //if you want to a non-default cwh_uuid
        if (clData.containsKey("cwh_uuid")) {
            FmPayload.setWarehouse_id(clData.get("cwh_uuid"));
        }

        if (clData.containsKey("location_id")) {
            FmPayload.setLocation_id(clData.get("location_id"));
        }

        if (clData.containsKey("prodClient")) {
            Map<String, Object> NonDefaultclientDetails = YamlReader.getYamlValues("productionData.client_" + ProdConfigLoader.getInstance().getClient());
            FmPayload.setClient_id(NonDefaultclientDetails.get("client_uuid").toString());
            FmPayload.setLocation_id(clData.get("ocid"));
            if (NonDefaultclientDetails.get("cwh_uuid") != null) {
                FmPayload.setWarehouse_id(NonDefaultclientDetails.get("cwh_uuid").toString());
            }
        }

        return FmPayload;
    }

    public static GIRequestPayload giApiReqGen(List<String> waybills) {
        return GIRequestPayload.builder()
                .center("")
                .ref_ids(waybills)
                .build();

    }

    public static BagV3RequestPayload bagV3ApiReqGen(String waybill) {
        return BagV3RequestPayload.builder()
                .bs(Utilities.generateUniqueEntity("BAG"))
                .origin(origin_center.get("Name").toString())
                .sd(getDateTime(0))
                .destination(destination_center.get("Name").toString())
                .ed(getDateTime(2))
                .device_id("86936116504")
                .u("akshat")
                .bt("Surface")
                .wbns(Wbns.builder()
                        .waybill(com.delhivery.Express.pojo.BagV3Api.request.Waybill.builder()
                                .bag(Bag.builder()
                                        .sd(getDateTime(0))
                                        .u("akshat")
                                        .build())
                                .build())
                        .build())
                .build();

    }

    public static BagV3RequestPayload bagV3ApiReqGen(HashMap<String, String> payload) {
        return BagV3RequestPayload.builder()
                .bs(payload.containsKey("bs") ? payload.get("bs") : Utilities.generateUniqueEntity("BAG"))
                .origin(payload.containsKey("origin") ? payload.get("origin") : origin_center.get("Name").toString())
                .sd(payload.containsKey("sd") ? payload.get("sd") : getDateTime(0))
                .destination(payload.containsKey("destination") ? payload.get("destination") : destination_center.get("Name").toString())
                .ed(payload.containsKey("ed") ? payload.get("ed") : getDateTime(2))
                .device_id(payload.containsKey("device_id") ? payload.get("device_id") : "86936116504")
                .u(payload.containsKey("u") ? payload.get("u") : "akshat")
                .bt(payload.containsKey("bt") ? payload.get("bt") : "Surface")
                .wbns(Wbns.builder()
                        .waybill(com.delhivery.Express.pojo.BagV3Api.request.Waybill.builder()
                                .bag(Bag.builder()
                                        .sd(payload.containsKey("bag_sd") ? payload.get("bag_sd") : getDateTime(0))
                                        .u(payload.containsKey("bag_u") ? payload.get("bag_u") : "akshat")
                                        .build())
                                .build())
                        .build())
                .build();

    }

    public static Datum dataList() {
        Datum payload = Datum.builder()
                .origin(origin_center.get("Name").toString())
                .wbns(WbnsV4.builder()
                        .waybill(WaybillV4.builder()
                                .dws(Dws.builder()
                                        .rv("3914.0")
                                        .b("132.5")
                                        .cuboid(1)
                                        .minTh(0)
                                        .image(Image.builder()
                                                .path("")
                                                .bucket("")
                                                .build())
                                        .l("2822.1")
                                        .wt("8120.0")
                                        .u("DA1")
                                        .h("1220.8")
                                        .sd(getDateTime(0))
                                        .build())
                                .bag(BagV4.builder()
                                        .u("008")
                                        .sd(getDateTime(0))
                                        .build())
                                .primary(PrimaryV4.builder()
                                        .u("DA1")
                                        .sd(getDateTime(0))
                                        .build())
                                .secondary(Secondary.builder()
                                        .armid("8")
                                        .u("008")
                                        .sd(getDateTime(0))
                                        .build())
                                .build())
                        .build())
                .ed(getDateTime(2))
                .destination(destination_center.get("Name").toString())
                .bi("Ahmedabad_W")
                .bt("Surface")
                .u("shivaksh")
                .bs(Utilities.generateUniqueEntity("BAG"))
                .ptlid("9125")
                .sd(getDateTime(0))
                .deviceId("DAL")
                .build();

        return payload;
    }

    public static CreateBagV4RequestPayload bagV4ApiReqGen(HashMap<String, String> data) {
        List<Datum> bagData = new ArrayList<>();
        bagData.add(dataList(data));
        CreateBagV4RequestPayload payload = CreateBagV4RequestPayload.builder()
                .data(bagData)
                .build();

        return payload;
    }

    public static CreateBagV4RequestPayload bagV4ApiReqGen() {
        List<Datum> bagData = new ArrayList<>();
        bagData.add(dataListV4());
        CreateBagV4RequestPayload payload = CreateBagV4RequestPayload.builder()
                .data(bagData)
                .build();

        return payload;
    }

    public static Datum dataListV4() {
        Datum payload = Datum.builder()
                .origin(origin_center.get("Name").toString())
                .wbns(WbnsV4.builder()
                        .waybill(WaybillV4.builder()
                                .dws(Dws.builder()
                                        .rv("3914.0")
                                        .b("132.5")
                                        .cuboid(1)
                                        .minTh(0)
                                        .image(Image.builder()
                                                .path("")
                                                .bucket("")
                                                .build())
                                        .l("2822.1")
                                        .wt("8120.0")
                                        .u("DA1")
                                        .h("1220.8")
                                        .sd(getDateTime(0))
                                        .build())
                                .bag(BagV4.builder()
                                        .u("008")
                                        .sd(getDateTime(0))
                                        .build())
                                .primary(PrimaryV4.builder()
                                        .u("DA1")
                                        .sd(getDateTime(0))
                                        .build())
                                .secondary(Secondary.builder()
                                        .armid("8")
                                        .u("008")
                                        .sd(getDateTime(0))
                                        .build())
                                .build())
                        .build())
                .ed(getDateTime(2))
                .destination(destination_center.get("Name").toString())
                .bi("Ahmedabad_W")
                .bt("Surface")
                .u("shivaksh")
                .bs(Utilities.generateUniqueEntity("BAG"))
                .ptlid("9125")
                .sd(getDateTime(0))
                .deviceId("DAL")
                .build();

        return payload;
    }

    public static Datum dataList(HashMap<String, String> payload) {
        return Datum.builder()
                .origin(payload.containsKey("origin") ? payload.get("origin") : origin_center.get("Name").toString())
                .wbns(WbnsV4.builder()
                        .waybill(WaybillV4.builder()
                                .dws(Dws.builder()
                                        .rv(payload.containsKey("rv") ? payload.get("rv") : "3914.0")
                                        .b(payload.containsKey("b") ? payload.get("b") : "132.5")
                                        .cuboid(payload.containsKey("cuboid") ? Integer.parseInt(payload.get("cuboid")) : 1)
                                        .minTh(payload.containsKey("minTh") ? Integer.parseInt(payload.get("minTh")) : 0)
                                        .image(Image.builder()
                                                .path(payload.containsKey("image_path") ? payload.get("image_path") : "")
                                                .bucket(payload.containsKey("image_bucket") ? payload.get("image_bucket") : "")
                                                .build())
                                        .l(payload.containsKey("l") ? payload.get("l") : "2822.1")
                                        .wt(payload.containsKey("wt") ? payload.get("wt") : "8120.0")
                                        .u(payload.containsKey("dws_u") ? payload.get("dws_u") : "DA1")
                                        .h(payload.containsKey("h") ? payload.get("h") : "1220.8")
                                        .sd(payload.containsKey("dws_sd") ? payload.get("dws_sd") : getDateTime(0))
                                        .build())
                                .bag(BagV4.builder()
                                        .u(payload.containsKey("bag_u") ? payload.get("bag_u") : "008")
                                        .sd(payload.containsKey("bag_sd") ? payload.get("bag_sd") : getDateTime(0))
                                        .build())
                                .primary(PrimaryV4.builder()
                                        .u(payload.containsKey("primary_u") ? payload.get("primary_u") : "DA1")
                                        .sd(payload.containsKey("primary_sd") ? payload.get("primary_sd") : getDateTime(0))
                                        .build())
                                .secondary(Secondary.builder()
                                        .armid(payload.containsKey("secondary_armid") ? payload.get("secondary_armid") : "8")
                                        .u(payload.containsKey("secondary_u") ? payload.get("secondary_u") : "008")
                                        .sd(payload.containsKey("secondary_sd") ? payload.get("secondary_sd") : getDateTime(0))
                                        .build())
                                .build())
                        .build())
                .ed(payload.containsKey("ed") ? payload.get("ed") : getDateTime(2))
                .destination(payload.containsKey("destination") ? payload.get("destination") : destination_center.get("Name").toString())
                .bi(payload.containsKey("bi") ? payload.get("bi") : "Ahmedabad_W")
                .bt(payload.containsKey("bt") ? payload.get("bt") : "Surface")
                .u(payload.containsKey("u") ? payload.get("u") : "akshat")
                .bs(payload.containsKey("bs") ? payload.get("bs") : Utilities.generateUniqueEntity("BAG"))
                .ptlid(payload.containsKey("ptlid") ? payload.get("ptlid") : "9125")
                .sd(payload.containsKey("sd") ? payload.get("sd") : getDateTime(0))
                .deviceId(payload.containsKey("deviceId") ? payload.get("deviceId") : "DAL")
                .build();
    }

    public static BagAddToTripRequestPayload bagAddToTripApiReqGen(String bagID) {
        return BagAddToTripRequestPayload.builder()
                .refId(bagID)
                .trid(Utilities.generateUniqueEntity("TRIP"))
                .slid(origin_center.get("SortCode").toString())
                .ntcid(destination_center.get("SortCode").toString())
                .vid("UP78BY1961")
                .mawb("ABCDEFGHIJ")
                .action("add")
                .build();

    }

    public static BagRemoveTripRequestPayload bagRemoveFromTripApiReqGen(String bagIds, String tripId) {
        return BagRemoveTripRequestPayload.builder()
                .refId(bagIds)
                .trid(tripId)
                .slid(origin_center.get("SortCode").toString())
                .ntcid(destination_center.get("SortCode").toString())
                .vid("UP78BY1961")
                .mawb("ABCDEFGHIJ")
                .action("remove")
                .build();

    }

    public static PkgRemoveFromBagRequestPayload pkgRemoveFromBagApiReqGen(String waybill, String bagId) {
        return PkgRemoveFromBagRequestPayload.builder()
                .bs(bagId)
                .wbn(waybill)
                .cn(origin_center.get("Name").toString())
                .build();

    }

    public static TripIncomingRequestPayload tripIncomingApiReqGen(String tripId) {
        return TripIncomingRequestPayload.builder()
                .trid(tripId)
                .slid(destination_center.get("SortCode").toString())
                .build();

    }

    public static BagIncomingRequestPayload bagIncomingApiReqGen(String bagId) {
        return BagIncomingRequestPayload.builder()
                .refId(bagId)
                .slid(destination_center.get("SortCode").toString())
                .unexpected(0)
                .build();

    }

    public static MarkDispatchRequestPayload markDispatchApiReqGen(List<String> waybills) {
        return MarkDispatchRequestPayload.builder()
                .wbns(waybills)
                .user("akshat")
                .sl(destination_center.get("Name").toString())
                .source("LM")
                .dwbn(Utilities.generateUniqueEntity("Dispatch"))
                .dn("Akshat Jain")
                .vn("HR26CX4939")
                .md("regular")
                .ph("1234567890")
                .build();

    }


    public static LMUpdateHQShipmentRequestPayload lmUpdateHQShipmentApiReqGen() {
        return LMUpdateHQShipmentRequestPayload.builder()
                .retry_count(0)
                .action("package_update")
                .payload(Payload.builder()
                        .source("mobile")
                        .data(Data.builder()
                                .waybill(com.delhivery.Express.pojo.LMUpdateHQShipmentApi.request.Waybill.builder()
                                        .ss("")
                                        .sr("")
                                        .st("")
                                        .add_info(Add_info.builder()
                                                .device("Mobile")
                                                .build())
                                        .nsl_code("")
                                        .u("akshat")
                                        .sl(destination_center.get("Name").toString())
                                        .sd(getDateTime(0))
                                        .build())
                                .build())
                        .process_insync(true)
                        .build())
                .build();

    }

    public static UnsetShipmentDispatchIdRequestPayload unsetShipmentDispatchIdApiReqGen() {
        return UnsetShipmentDispatchIdRequestPayload.builder()
                .retry_count(0)
                .action("dd_id_unset")
                .payload(com.delhivery.Express.pojo.UnsetShipmentDispatchIdApi.request.Payload.builder()
                        .dispatch_id("")
                        .wbns(new ArrayList<>())
                        .build())
                .build();

    }

    public static QcWtPayload QcWtApiReqGen(String wbn, double wt, String wtr) {
        return QcWtPayload.builder().wbn(wbn).h(10d).b(11d).l(12d).wt(wt).wtRule(wtr).v(1212d).rv(1212d)
                .wtType("INTERNAL").intWtIwt(100.0d).source("BIRD").build();

    }

    public static EditApiRequestPayload EditApiReqGen(String Waybill, HashMap<String, String> data) {

        EditApiRequestPayload payload = EditApiRequestPayload.builder()
                .waybill(Waybill)
                .build();
        for (Map.Entry<String, String> set : data.entrySet()) {
            String k = set.getKey();
            String v = set.getValue();

            if (k.equalsIgnoreCase("tax_value")) {
                payload.setTaxValue(v);
            } else if (k.equalsIgnoreCase("shipment_width")) {
                payload.setShipmentWidth(Double.parseDouble(v));
            } else if (k.equalsIgnoreCase("product_category")) {
                payload.setProductCategory(v);
            } else if (k.equalsIgnoreCase("consignee_tin")) {
                payload.setConsigneeTin(v);
            } else if (k.equalsIgnoreCase("stax_ack_number")) {
                payload.setStaxAckNumber(k);
            } else if (k.equalsIgnoreCase("gm")) {
                payload.setGm(Double.parseDouble(v));
            } else if (k.equalsIgnoreCase("shipment_length")) {
                payload.setShipmentLength(Double.parseDouble(v));
            } else if (k.equalsIgnoreCase("shipment_height")) {
                payload.setShipmentHeight(Double.parseDouble(v));
            } else if (k.equalsIgnoreCase("commodity_value")) {
                payload.setCommodityValue(v);
            } else if (k.equalsIgnoreCase("name")) {
                payload.setName(v);
            } else if (k.equalsIgnoreCase("product_details")) {
                payload.setProductDetails(v);
            } else if (k.equalsIgnoreCase("add")) {
                payload.setAdd(v);
            } else if (k.equalsIgnoreCase("phone")) {
                payload.setPhone(v);
            } else if (k.equalsIgnoreCase("return_add")) {
                payload.setReturnAdd(v);
            } else if (k.equalsIgnoreCase("pincode")) {
                payload.setPincode(Integer.parseInt(v));
            } else if (k.equalsIgnoreCase("cancellation")) {
                payload.setCancellation(v);
            } else if (k.equalsIgnoreCase("rs")) {
                payload.setRs(Long.parseLong(v));
            }
        }

        return payload;

    }

    public static ApplyNslRequestPayload ApplyNsl(List<String> wbns, String status_type, String nsl_id, Map<String, String> data) {
        return ApplyNslRequestPayload.builder()
                .nslId(nsl_id)
                .scope("Package")
                .statusType(status_type)
                .wbns(wbns)
                .status(Optional.ofNullable(data.get("status")).orElse(""))
                .user("akshat.jain3")
                .build();
    }

    public static CmuManifestApi ManifestB2BWithInternalChild(HashMap<String, String> data) {

        String mwbn = "";
        String cwbn = "";
        String prd = "";
        HashMap<String, String> Master_data = new HashMap<>();
        HashMap<String, String> Child_data = new HashMap<>();

        for (Map.Entry<String, String> set : data.entrySet()) {
            String k = set.getKey();
            String v = set.getValue();
            if (k.equalsIgnoreCase("master_id")) {
                mwbn = v;
            } else if (k.equalsIgnoreCase("waybill")) {
                cwbn = v;
            } else if (k.equalsIgnoreCase("products_desc")
                    && v.equalsIgnoreCase("Wesol Hydrogen Peroxide 3% (Food Grade), 500 ML Pack")) {
                Master_data.put(k, v);
                Child_data.put(k, v);
            } else if (k.equalsIgnoreCase("international")) {
                Master_data.put(k, v);
                Child_data.put(k, v);
            } else if (k.equalsIgnoreCase("weight")) {
                Master_data.put(k, v);
                Child_data.put(k, v);
            } else if (k.equalsIgnoreCase("pin")) {
                Master_data.put(k, v);
                Child_data.put(k, v);
            } else if (k.equalsIgnoreCase("einv_qr")) {
                Master_data.put(k, v);
                Child_data.put(k, v);
            } else {
                Master_data.put(k, v);
                Child_data.put(k, v);
            }
        }

        List<Shipment> listShipments = new ArrayList<>();

        //Creating values required for Master dictionary

        Master_data.put("mps_amount", "1");
        Master_data.put("master_id", mwbn);
        Master_data.put("waybill", mwbn);
        Master_data.put("shipment_type", "MPS");
        Master_data.put("einv_qr", "false");
        if (data.containsKey("product_type") && data.get("product_type").equalsIgnoreCase("B2C")) {
            Master_data.put("product_type", "B2C");
        } else {
            Master_data.put("product_type", "B2B");
        }

        //Creating values required for Child dictionary

        Child_data.put("mps_children", "2");
        Child_data.put("mps_amount", "1");
        Child_data.put("master_id", mwbn);
        Child_data.put("waybill", cwbn);
        Child_data.put("internal", "true");
        Child_data.put("shipment_type", "MPS");
        if (data.containsKey("product_type") && data.get("product_type").equalsIgnoreCase("B2C")) {
            Child_data.put("product_type", "B2C");
        } else {
            Child_data.put("product_type", "B2B");
        }


        //if only waybill is sent in data that means we have only master_id and mwbn,
        // no need to add child dictionary in that case

        if (!data.containsKey("waybill")) {
            System.out.println("Only master waybill is sent");
            Master_data.put("mps_children", "1");
            listShipments.add(cmuShipments(Master_data));
        } else {
            Master_data.put("mps_children", "2");
            listShipments.add(cmuShipments(Master_data));
            listShipments.add(cmuShipments(Child_data));
        }


        CmuManifestApi payload1 = CmuManifestApi.builder()
                .shipments(listShipments)
                .dispatchDate("")
                .build();
        //if cwh is given
        if (data.containsKey("cwh")) {
            pickup_location pl = new pickup_location();
            pl.setName(data.get("cwh"));

            payload1.setPickup_location(pl);
        }

        return payload1;
    }


    public static CmuManifestApi ManifestMPSWithChildPayload(HashMap<String, String> data) {

        String mwbn = "";
        String cwbn = "";
        String prd = "";
        HashMap<String, String> Master_data = new HashMap<>();
        HashMap<String, String> Child_data = new HashMap<>();

        for (Map.Entry<String, String> set : data.entrySet()) {
            String k = set.getKey();
            String v = set.getValue();
            if (k.equalsIgnoreCase("master_id")) {
                mwbn = v;
            } else if (k.equalsIgnoreCase("waybill")) {
                cwbn = v;
            } else if (k.equalsIgnoreCase("products_desc")
                    && v.equalsIgnoreCase("Wesol Hydrogen Peroxide 3% (Food Grade), 500 ML Pack")) {
                Master_data.put(k, v);
                Child_data.put(k, v);
            } else if (k.equalsIgnoreCase("product_type")) {
                Master_data.put(k, v);
                Child_data.put(k, v);
            } else if (k.equalsIgnoreCase("international")) {
                Master_data.put(k, v);
                Child_data.put(k, v);
            } else if (k.equalsIgnoreCase("pin")) {
                Master_data.put(k, v);
                Child_data.put(k, v);
            } else {
                Master_data.put(k, v);
                Child_data.put(k, v);
            }
        }

        List<Shipment> listShipments = new ArrayList<>();

        //Creating values required for Master dictionary

        Master_data.put("mps_amount", "1");
        Master_data.put("master_id", mwbn);
        Master_data.put("waybill", mwbn);
        Master_data.put("shipment_type", "MPS");

        //Creating values required for Child dictionary

        Child_data.put("mps_children", "2");
        Child_data.put("mps_amount", "1");
        Child_data.put("master_id", mwbn);
        Child_data.put("waybill", cwbn);
//		Child_data.put("internal", "true");
        Child_data.put("shipment_type", "MPS");


        //if only waybill is sent in data that means we have only master_id and mwbn,
        // no need to add child dictionary in that case

        if (!data.containsKey("waybill")) {
            System.out.println("Only master waybill is sent");
            Master_data.put("mps_children", "1");
            listShipments.add(cmuShipments(Master_data));
        } else {
            Master_data.put("mps_children", "2");
            listShipments.add(cmuShipments(Master_data));
            listShipments.add(cmuShipments(Child_data));
        }


        return CmuManifestApi.builder()
                .shipments(listShipments)
                .dispatchDate("")
                .build();

    }

    public static CreateNoDataUplApiRequestPayload createNoDataUplReqGen() {
        return CreateNoDataUplApiRequestPayload.builder()
                .lrn("111111111")
                .pickup_location(clientDetails.get("client_warehouse").toString())
                .destination_pincode("400059")
                .shipment_count(2)
                .address("Plot 5 HQ Haryana")
                .phone("9413169108")
                .name("Anmol")
                .invoices(new ArrayList<Invoice>(Arrays.asList(Invoice.builder()
                        .invoice("INV632806905")
                        .amount(5001)
                        .ewaybill("")
                        .build())))
                .dispatch_id("")
                .build();

    }

    public static CmuV2ManifestNoDataShipmentApiRequestPayload cmuV2ManifestNoDataShipmentReqGen(HashMap<String, String> data) throws IOException {
        final CmuV2ManifestNoDataShipmentApiRequestPayload reqPayload;
        file = new File("src/test/resources/requestPayloads/cmuV2ManifestApi.json");
        reqPayload = objectMapper.readValue(file, CmuV2ManifestNoDataShipmentApiRequestPayload.class);
        reqPayload.pickup_location.setName(clientDetails.get("client_warehouse").toString());

        for (Map.Entry<String, String> set : data.entrySet()) {
            String k = set.getKey();
            String v = set.getValue();

            switch (k.toUpperCase()) {
                case "PRODUCTS_DESC":
                    reqPayload.shipments.get(0).order_info.get(0).setProducts_desc(v);
                    reqPayload.shipments.get(0).order_info.get(1).setProducts_desc(v);
                    break;
                case "WEIGHT":
                    reqPayload.shipments.get(0).setWeight(Double.parseDouble(v));
                    break;
                case "PIN":
                    reqPayload.shipments.get(0).setPin(v);
                    break;
                case "ADD":
                    reqPayload.shipments.get(0).setAdd(v);
                    break;
                case "CITY":
                    reqPayload.shipments.get(0).setCity(v);
                    break;
                case "PRODUCT_TYPE":
                    reqPayload.shipments.get(0).setProduct_type(v);
                    break;
                case "NAME":
                    Map<String, Object> NonDefaultclientDetails = YamlReader.getYamlValues("Client_Details.client_" + ConfigLoader.getInstance().getClient(v));
                    reqPayload.shipments.get(0).setName(NonDefaultclientDetails.get("name").toString());
                    reqPayload.pickup_location.setName(NonDefaultclientDetails.get("name").toString());
                    break;
                case "CWH":
                    reqPayload.pickup_location.setName(v);
                    break;
                case "RETURN_PIN":
                    reqPayload.shipments.get(0).setReturn_pin(v);
                    break;
                case "RETURN_ADD":
                    reqPayload.shipments.get(0).setReturn_add(v);
                    break;
            }
        }

        return reqPayload;

    }

    public static CreateNoDataUplApiRequestPayload createPartiallyManifestedUplReqGen() {
        return CreateNoDataUplApiRequestPayload.builder()
                .lrn("111111111")
                .pickup_location(clientDetails.get("client_warehouse").toString())
                .destination_pincode("122001")
                .source("LR-Partial-Bulk-Upload")
                .shipment_count(2)
                .address("Plot 5 HQ Haryana")
                .phone("9413169108")
                .name("Anmol")
                .invoices(new ArrayList<Invoice>(Arrays.asList(Invoice.builder()
                        .invoice("INV632806905")
                        .amount(5001)
                        .ewaybill("")
                        .build())))
                .dispatch_id("")
                .build();

    }

    public static CreateNoDataUplApiRequestPayload createNoDataUplReqGen(HashMap<String, String> ClientData) {
        Map<String, Object> NonDefaultclientDetails = YamlReader.getYamlValues("Client_Details.client_" + ConfigLoader.getInstance().getClient(ClientData.get("client")));
        CreateNoDataUplApiRequestPayload payload = CreateNoDataUplApiRequestPayload.builder()
                .lrn("111111111")
                .destination_pincode("122001")
                .shipment_count(2)
                .address("Plot 5 HQ Haryana")
                .phone("9413169108")
                .name("Anmol")
                .pickup_location(NonDefaultclientDetails.get("client_warehouse").toString())
                .invoices(new ArrayList<Invoice>(Arrays.asList(Invoice.builder()
                        .invoice("INV632806905")
                        .amount(5001)
                        .ewaybill("")
                        .build())))
                .dispatch_id("")
                .build();
        if (ClientData.containsKey("cwh")) {
            payload.setPickup_location(ClientData.get("cwh"));
        }

        return payload;

    }

    public static CreateNoDataUplApiRequestPayload createPartiallyManifestedUplReqGen(HashMap<String, String> ClientData) {
        Map<String, Object> NonDefaultclientDetails = YamlReader.getYamlValues("Client_Details.client_" + ConfigLoader.getInstance().getClient(ClientData.get("client")));
        CreateNoDataUplApiRequestPayload payload = CreateNoDataUplApiRequestPayload.builder()
                .lrn("111111111")
                .destination_pincode("122001")
                .source("LR-Partial-Bulk-Upload")
                .shipment_count(2)
                .pickup_location(NonDefaultclientDetails.get("client_warehouse").toString())
                .invoices(new ArrayList<Invoice>(Arrays.asList(Invoice.builder()
                        .invoice("INV632806905")
                        .amount(5001)
                        .ewaybill("")
                        .build())))
                .dispatch_id("")
                .address("Plot 5 HQ Haryana")
                .phone("9413169108")
                .name("Anmol")
                .build();


        return payload;

    }


    public static CmuManifestRequestPayload cmuManifestWithCwhShipmentReqGen(HashMap<String, String> data) throws IOException {
        final CmuManifestRequestPayload reqPayload;
        file = new File("src/test/resources/requestPayloads/cmuManifestWithCwhApi.json");
        reqPayload = objectMapper.readValue(file, CmuManifestRequestPayload.class);
        com.delhivery.Express.pojo.ManifestCmuCreateWithCwhApi.request.Shipment shipmentPayload = reqPayload.shipments.get(0);
        shipmentPayload.setOrder(getUniqueString());

        for (Map.Entry<String, String> set : data.entrySet()) {
            String k = set.getKey();
            String v = set.getValue();

            if (k.equalsIgnoreCase("pickup_location")) {
                reqPayload.pickup_location.setName(v);
            } else if (k.equalsIgnoreCase("products_desc")) {
                shipmentPayload.setProductsDesc(v);
            } else if (k.equalsIgnoreCase("payment_mode")) {
                shipmentPayload.setPaymentMode(v);
            } else if (k.equalsIgnoreCase("product_type")) {
                shipmentPayload.setProductType(v);
            } else if (k.equalsIgnoreCase("weight")) {
                shipmentPayload.setWeight(v);
            } else if (k.equalsIgnoreCase("add")) {
                shipmentPayload.setAdd(v);
            } else if (k.equalsIgnoreCase("city")) {
                shipmentPayload.setCity(v);
            } else if (k.equalsIgnoreCase("state")) {
                shipmentPayload.setState(v);
            } else if (k.equalsIgnoreCase("country")) {
                shipmentPayload.setCountry(v);
            } else if (k.equalsIgnoreCase("pin")) {
                shipmentPayload.setPin(v);
            } else if (k.equalsIgnoreCase("return_add")) {
                shipmentPayload.setReturnAdd(v);
            } else if (k.equalsIgnoreCase("return_city")) {
                shipmentPayload.setReturnCity(v);
            } else if (k.equalsIgnoreCase("return_state")) {
                shipmentPayload.setReturnState(v);
            } else if (k.equalsIgnoreCase("return_country")) {
                shipmentPayload.setReturnCountry(v);
            } else if (k.equalsIgnoreCase("return_pin")) {
                shipmentPayload.setReturnPin(v);
            } else if (k.equalsIgnoreCase("shipping_mode")) {
                shipmentPayload.setShippingMode(v);
            } else if (k.equalsIgnoreCase("shipping_method")) {
                shipmentPayload.setShippingMethod(v);
            } else if (k.equalsIgnoreCase("package_count")) {
                shipmentPayload.setPackageCount(Long.parseLong(v));
            } else if (k.equalsIgnoreCase("weight_verification")) {
                shipmentPayload.setWeightVerification(Boolean.parseBoolean(v));
            } else if (k.equalsIgnoreCase("essential_good")) {
                shipmentPayload.setEssentialGood(Boolean.parseBoolean(v));
            } else if (k.equalsIgnoreCase("mps_amount")) {
                shipmentPayload.setMpsAmount(Long.parseLong(v));
            } else if (k.equalsIgnoreCase("cod_amount")) {
                shipmentPayload.setCodAmount(Long.parseLong(v));
            } else if (k.equalsIgnoreCase("total_amount")) {
                shipmentPayload.setTotalAmount(Long.parseLong(v));
            } else if (k.equalsIgnoreCase("mps_children")) {
                shipmentPayload.setMpsChildren(Long.parseLong(v));
            } else if (k.equalsIgnoreCase("master_id")) {
                shipmentPayload.setMasterId(v);
            } else if (k.equalsIgnoreCase("waybill")) {
                shipmentPayload.setWaybill(v);
            } else if (k.equalsIgnoreCase("internal")) {
                shipmentPayload.setInternal(Boolean.parseBoolean(v));
            } else if (k.equalsIgnoreCase("shipment_type")) {
                shipmentPayload.setShipmentType(v);
            } else if (k.equalsIgnoreCase("international")) {
                shipmentPayload.setInternational(v);
            } else if (k.equalsIgnoreCase("client")) {
                //fetching client data from staging.yml file
                System.out.println(ConfigLoader.getInstance().getClient(v));
                Map<String, Object> clientDetails1 = YamlReader.getYamlValues("Client_Details.client_" +
                        ConfigLoader.getInstance().getClient(v));
                shipmentPayload.setClient(clientDetails1.get("name").toString());
                if (!data.containsKey("cwh")) {
                    //if someone wants to use a non-default cwh for a non default client
                    reqPayload.pickup_location.setName(clientDetails1.get("client_warehouse").toString());
                }
            } else if (k.equalsIgnoreCase("order")) {
                shipmentPayload.setOrder(v);
            } else if (k.equalsIgnoreCase("name")) {
                shipmentPayload.setName(v);
            } else if (k.equalsIgnoreCase("phone")) {
                shipmentPayload.setPhone(v);
            }
        }
        return reqPayload;

    }

    public static DispatchFreezeRequestPayload dispatchFreezeApiReqGen(List<String> waybills) {
        return DispatchFreezeRequestPayload.builder()
                .centerCode(origin_center.get("SortCode").toString())
                .refIds(waybills)
                .dispatchId("DSP101060676889")
                .username("test")
                .nslCode("12345")
                .vhNumber("testVh")
                .scanTime(DateTimeUtility.getDateTimeODX(0))
                .build();
    }

    public static MarkPendingRequestPayload markPendingApiReqGen(List<String> waybills) {
        return MarkPendingRequestPayload.builder()
                .centerCode(origin_center.get("SortCode").toString())
                .refIds(waybills)
                .dispatchId("567")
                .username("5789")
                .nslCode("RD-PD10")
                .scanRemarks("test")
                .scanTime(DateTimeUtility.getDateTimeODX(0))
                .build();
    }

    public static MarkDeliveredRequestPayload markDeliveredApiReqGen(List<String> waybills) {
        return MarkDeliveredRequestPayload.builder()
                .centerCode(origin_center.get("SortCode").toString())
                .refIds(waybills)
                .dispatchId("1234")
                .username("5789")
                .nslCode("RD-AP")
                .scanTime(DateTimeUtility.getDateTimeODX(0))
                .build();
    }

    public static FetchBagMatrixRequestPayload fetchMagMatrixApiReqGen(String scanLocation) {
        return FetchBagMatrixRequestPayload.builder()
                .sl(scanLocation)
                .build();
    }

    public static BulkNdrRequestPayload bulkEditNdrApiReqGen(String action) {
        return BulkNdrRequestPayload.builder()
                .ndrData(NdrData.builder()
                        .waybill(WaybillEdit.builder()
                                .action(action)
                                .actionData(ActionData.builder()
                                        .phone("1234567890")
                                        .address("Randoms")
                                        .build())
                                .build())
                        .build())
                .build();
    }


    public static ClientUpdateRequestPayloadJava clientUpdateReqGen() {
        return ClientUpdateRequestPayloadJava.builder()
                .ndrReportRecipients("net@gmail.com")
                .reportRecipients("n@gmail.com, new@gmail.com")
                .rtoEmailRecipients("a@gmail.com")
                .dtoEmailRecipients("b@h.com")
                .remEmail("r@gmail.com")
                .uploadRecipients("wer@gmail.com")
                .vatNumber(2322)
                .cstNumber(234)
                .ecoCode(3243)
                .frsCode(3234)
                .returnAdd("weq")
                .address("weq")
                .regName("we")
                .pushUrl("ew")
                .productType("B2B")
                .isPrepaid(false)
                .walletProvider("DEL-MILES")
                .walletNotificationEmail("2424")
                .walletNotificationMobile("2526")
                .mpsService(true)
                .sendMailPickup(true)
                .sendSmsPrepaid(true)
                .sendSmsCash(true)
                .sendSmsCod(true)
                .sendSmsNdr(true)
                .sendSmsReverse(true)
                .autoPickup(true)
                .allowNoData(true)
                .billingMode("Surface")
                .postalCat("CatB")
                .phone("7832142123")
                .hoState("Nagaland")
                .gstStates(GstStatesReq.builder()
                        .delhi("12-UR")
                        .nagaland("12-UR")
                        .build())
                .services(Services.builder()
                        .repl(true)
                        .prePaid(false)
                        .pickup(false)
                        .build())
                .build();
    }

    public static RemoveEwbnRequestPayload removeEwbnApiReqGen(String ewbn, String user) {
        return RemoveEwbnRequestPayload.builder()
                .ewbn(ewbn)
                .user(user)
                .build();
    }

    public static BagCustodyScanRequestPayload bagCustodyScanApiReqGen() {
        return BagCustodyScanRequestPayload.builder()
                .action("send")
                .destination("IND400093AAA")
                .location("IND122001AAB")
                .bagseals("BAG100817914")
                .sync(false)
                .build();
    }

    public static CreateBagV2ApiRequestPayload bagV2ApiReqGen(String waybill) {
        return CreateBagV2ApiRequestPayload.builder()
                .bs(Utilities.generateUniqueEntity("BAG"))
                .origin(origin_center.get("Name").toString())
                .sd(getDateTime(0))
                .destination(destination_center.get("Name").toString())
                .ed(getDateTime(2))
                .deviceId("86936116504")
                .u("akshat.jain3")
                .bt("Surface")
                .wbns(WbnsV2.builder()
                        .waybill(com.delhivery.Express.pojo.CreateBagV2Api.Request.WaybillV2.builder()
                                .bag(com.delhivery.Express.pojo.CreateBagV2Api.Request.Bag.builder()
                                        .sd(getDateTime(0))
                                        .u("akshat.jain3")
                                        .build())
                                .build())
                        .build())
                .build();

    }

    public static CreateBagV2ApiRequestPayload bagV2ApiReqGen(HashMap<String, String> payload) {
        return CreateBagV2ApiRequestPayload.builder()
                .bs(payload.containsKey("bs") ? payload.get("bs") : Utilities.generateUniqueEntity("BAG"))
                .origin(payload.containsKey("origin") ? payload.get("origin") : origin_center.get("Name").toString())
                .sd(payload.containsKey("sd") ? payload.get("sd") : getDateTime(0))
                .destination(payload.containsKey("destination") ? payload.get("destination") : destination_center.get("Name").toString())
                .ed(payload.containsKey("ed") ? payload.get("ed") : getDateTime(2))
                .deviceId(payload.containsKey("deviceId") ? payload.get("deviceId") : "86936116504")
                .u(payload.containsKey("u") ? payload.get("u") : "akshat")
                .bt(payload.containsKey("bt") ? payload.get("bt") : "Surface")
                .wbns(WbnsV2.builder()
                        .waybill(com.delhivery.Express.pojo.CreateBagV2Api.Request.WaybillV2.builder()
                                .bag(com.delhivery.Express.pojo.CreateBagV2Api.Request.Bag.builder()
                                        .sd(payload.containsKey("bag_sd") ? payload.get("bag_sd") : getDateTime(0))
                                        .u(payload.containsKey("bag_u") ? payload.get("bag_u") : "akshat")
                                        .build())
                                .build())
                        .build())
                .build();
    }

    public static AutobagRequestPayload autobagApiReqGen(String waybill) {
        return AutobagRequestPayload.builder()
                .cn("")
                .wbn(waybill)
                .build();
    }

    public static FmApiIncomingRequestPayload fmApiIncomingReqGen(String waybill) {
        return FmApiIncomingRequestPayload.builder()
                .client(clientDetails.get("name").toString())
                .waybill(waybill)
                .center("")
                .build();
    }

    public static CustomEditApiRequestPayload customEditReqGen() {
        return CustomEditApiRequestPayload.builder()
                .landMark("Times Square Building")
                .build();
    }

    public static CreateUpdateClientRequestPayload clientCreateUpdateReqGen(String client, String productType) {
        return CreateUpdateClientRequestPayload.builder()
                .billingMethod("postpaid")
                .salesforceId("0010I000020j7cjQAA")
                .phone("7661066209")
                .clientName(client)
                .reportRecipients("eshcol007@gmail.com")
                .billingMode("Surface")
                .clientType("C2C")
                .walletProvider("DEL-MILES")
                .walletNotificationEmail("eshcol007@gmail.com")
                .clientFirstName("Ravirakula")
                .clientLastName("Eshcol")
                .password("Delhivery@125")
                .walletNotificationMobile("7661066209")
                .xrayAmountLimit(7000)
                .isPrepaidService(true)
                .isMps(true)
                .isNoData(true)
                .productType(productType)
                .build();
    }

    public static UpdateRegressionClientRequestPayload clientUpdateReqGen(HashMap<String, String> data) {
        UpdateRegressionClientRequestPayload payload = UpdateRegressionClientRequestPayload.builder()
                .clientUuid("")
                .billingMethod("")
                .productType("")
                .build();

        //add iterate function which iterates argument hashmap, iterate hashmap value and update payload value as found in loop
        for (Map.Entry<String, String> set : data.entrySet()) {
            String k = set.getKey();
            String v = set.getValue();
            if (k.equalsIgnoreCase("client_uuid")) {
                payload.setClientUuid(v);
            } else if (k.equalsIgnoreCase("billing_method")) {
                payload.setBillingMethod(v);
            } else if (k.equalsIgnoreCase("product_type")) {
                payload.setProductType(v);
            }
        }

        return payload;

    }

    public static ODTatRequestPayload ODTatReqGen() {
        return ODTatRequestPayload.builder()
                .data(DataODTat.builder()
                        .ltlAirTat(12)
                        .rvpAirTat(17)
                        .rvpAirCutoff("23:00:00")
                        .forwardFastB2bCutoff("11:00:00")
                        .ltlRegularTat(12)
                        .forwardFastB2cCutoff("10:00:00")
                        .forwardSurfaceCutoff("23:24:00")
                        .heavyCutoff("23:24:00")
                        .id(258797)
                        .originCity("Gurgaon")
                        .forwardExpressCutoff("19:04:00")
                        .destinationCity("Mumbai")
                        .flashAirTat(5)
                        .heavyTat(22)
                        .forwardFastB2bTat(6)
                        .returnCutoff("23:00:00")
                        .nextB2cSurfaceCutoff("21:00:00")
                        .ltlAirCutoff("10:00:00")
                        .dtoTat(24)
                        .ltlRegularCutoff("10:00:00")
                        .airLaneExists(true)
                        .returnTat(24)
                        .dtoCutoff("13:59:59")
                        .forwardFastB2cTat(9)
                        .flashAirCutoff("10:00:00")
                        .nextB2cSurfaceTat(20)
                        .forwardExpressTat(15)
                        .forwardSurfaceTat(22)
                        .build())
                .success(true)
                .error("")
                .build();
    }

    public static PackageCancelRequestPayload packageCancelReqGen(String wbn) {
        return PackageCancelRequestPayload.builder()
                .waybill(wbn)
                .build();
    }

    public static PackageUpdateRequestPayload packageUpdateReqGen(String waybill) {
        return PackageUpdateRequestPayload.builder()
                .wbn(waybill)
                .l("24.4")
                .b("19.5")
                .h("15.7")
                .wt("21")
                .v("1.2")
                .rv("555555.2")
                .build();
    }

    public static LocationAssociateRequestPayload locationAssociateReqGen(String waybill, String scanTime) {
        return LocationAssociateRequestPayload.builder()
                .user("shivaksh")
                .sd(scanTime)
                .refId(waybill)
                .slid(origin_center.get("SortCode").toString())
                .locationId("SPACE-93847561")
                .build();
    }

    public static BagInfoRequestPayload bagInfoReqGen(String bagID) {
        return BagInfoRequestPayload.builder()
                .bag(bagID)
                .build();
    }

    public static NewFmRequestPayload newFmReqGen() {
        return NewFmRequestPayload.builder()
                .pickupDate(getDateTime(2))
                .pickupTime("23:59:59")
                .pickupLocation(clientDetails.get("client_warehouse").toString())
                .expectedPackageCount(1)
                .build();
    }

    public static CmuPushRequestPayload cmuPushReqGen(HashMap<String, String> data) {
        List<PushShipment> listShipments = new ArrayList<>();
        listShipments.add(cmuPushShipments(data));
        CmuPushRequestPayload body = CmuPushRequestPayload.builder()
                .status(true)
                .shipments(listShipments)
                .dispatchDate("2016-08-01T18:00:00.000000 05:30")
                .build();

        return body;
    }

    public static PushShipment cmuPushShipments(HashMap<String, String> data) {
        List<ItemDesc> itemDescp = new ArrayList<>();
        itemDescp.add(cmuPushItemDescp());
        PushShipment payload = PushShipment.builder()
                .name("test")
                .client(clientDetails.get("name").toString())
                .order(getUniqueString())
                .productsDesc("phone cover")
                .itemDesc(itemDescp)
                .orderDate("2016-11-05T12:30:00 00:00")
                .paymentMode("COD")
                .codAmount(12)
                .productType("B2C")
                .packageCount(1)
                .totalAmount(1234)
                .add("test")
                .city("Gurgaon")
                .state("Haryana")
                .country("India")
                .weight("650.0 gm")
                .phone("6000000001")
                .pin("122003")
                .dangerousGood(false)
                .returnAdd("Palika Bazar")
                .returnCity("Gurgaon")
                .returnCountry("India")
                .returnName("test")
                .returnPhone("6000000001")
                .returnPin("122001")
                .returnState("Haryana")
                .supplier("Kangaroo (India) Pvt Ltd")
                .billableWeight(650)
                .dimensions("9.00CM x 9.00CM x 9.00CM")
                .volumetric(0)
                .clientGstTin("djsjnvnvjjj")
                .consigneeGstTin("swijdwinedvn")
                .hsnCode("39494949, sdsjjddjjj")
                .invoiceReference("dsjfvjnfvjvfk")
                .sellerGstTin("UR")
                .fmPhone("6000000001")
                .shippingMethod("Surface")
                .build();

        Optional.ofNullable(data.get("pin")).ifPresent(payload::setPin);

        return payload;
    }

    public static ItemDesc cmuPushItemDescp() {
        ItemDesc payload = ItemDesc.builder()
                .supplier("Kangaroo (India) Pvt Ltd")
                .sellerInv("test3")
                .sellerName("test3")
                .sellerAdd("test3")
                .sellerCst("456796")
                .sellerTin("789765572")
                .sellerInvDate("2016-11-09T18:00:00 5:30")
                .consigneeTin("423441")
                .commodityValue("7341")
                .taxValue("100")
                .salesTaxFormAckNo("234987")
                .categoryOfGoods("cover")
                .sellerGstTin("UR")
                .build();

        return payload;
    }

    public static SelfCollectApiRequestPayload selfCollectReqGen(String waybill, String scanLocation) {
        List<String> requiredField = new ArrayList<>();
        List<String> waybills = new ArrayList<>();
        requiredField.add("dd.atc");
        waybills.add(waybill);
        SelfCollectApiRequestPayload payload = SelfCollectApiRequestPayload.builder()
                .source("LM")
                .dwbn("abcd357121100000000")
                .processInsync(true)
                .requiredFields(requiredField)
                .data(SelfCollectData.builder()
                        .waybillSelfCollect(SelfCollectWbn.builder()
                                .addInfo(AddInfo.builder()
                                        .additionalRemark("self collect")
                                        .build())
                                .asr("")
                                .u("shreyvats")
                                .wbns(waybills)
                                .nslCode("SC-101")
                                .ss("Delivered")
                                .sr("Delivered to consignee")
                                .sl(scanLocation)
                                .sd(getDateTime(0))
                                .st("DL")
                                .build())
                        .build())
                .md("self_collect")
                .build();

        return payload;
    }

    public static LocationDissociateRequestPayload locationDissociateReqGen(String waybill, String scanTime) {
        return LocationDissociateRequestPayload.builder()
                .user("shivaksh")
                .sd(scanTime)
                .refId(waybill)
                .slid(origin_center.get("SortCode").toString())
                .locationId("SPACE-93847561")
                .build();
    }

    public static UpdatedBagmatrixRequestPayload updatedBagmatrixReqGen() {
        return UpdatedBagmatrixRequestPayload.builder()
                .lastUpdated(0)
                .scanLocation("IND742101AAC")
                .build();
    }


    public static PUpdatePayload packageUpdateReqGen(HashMap<String, String> data) {
        List<com.delhivery.Express.pojo.pUpdate.Datum> Payload = new ArrayList<>();
        Payload.add(pUpdatePayload(data));

        return PUpdatePayload.builder()
                .data(Payload)
                .build();
    }

    public static com.delhivery.Express.pojo.pUpdate.Datum pUpdatePayload(HashMap<String, String> data) {
        ActionDataPUpdate actionDataPUpdate = new ActionDataPUpdate();
        actionDataPUpdate.setLocationId(data.getOrDefault("locationId", ""));
        return com.delhivery.Express.pojo.pUpdate.Datum.builder()
                .source(data.getOrDefault("source", ""))
                .waybill(data.get("waybill"))
                .act(data.getOrDefault("act", "EDIT_DETAILS"))
                .actionDataPUpdate(actionDataPUpdate)
                .build();
    }


    public static EditPhoneRequestPayload editPhoneReqGen(String wbn) {
        List<DatumEditPhone> wbnData = new ArrayList<>();
        wbnData.add(wbnData(wbn));
        EditPhoneRequestPayload payload = EditPhoneRequestPayload.builder()
                .data(wbnData)
                .build();

        return payload;
    }

    public static DatumEditPhone wbnData(String wbn) {
        DatumEditPhone payload = DatumEditPhone.builder()
                .waybill(wbn)
                .act("EDIT_DETAILS")
                .actionData(ActionDataEditPhone.builder()
                        .phone("9813020202")
                        .build())
                .build();

        return payload;
    }

    public static PackageStatusUpdateRequestPayload updatePackageStatusReqGen() {
        List<String> reqFields = new ArrayList<>();
        reqFields.add("dd.atc");
        PackageStatusUpdateRequestPayload payload = PackageStatusUpdateRequestPayload.builder()
                .processInsync(true)
                .requiredFields(reqFields)
                .data(com.delhivery.Express.pojo.PackageStatusUpdate.Request.Data.builder()
                        .waybill(WbnPackageStatusUpdate.builder()
                                .ss("")
                                .nslCode("")
                                .st("")
                                .asr("")
                                .addInfo(com.delhivery.Express.pojo.PackageStatusUpdate.Request.AddInfo.builder()
                                        .build())
                                .u("test")
                                .sl(destination_center.get("Name").toString())
                                .sd(getDateTime(0))
                                .build())
                        .build())
                .build();
        return payload;
    }

    public static UpdateFinalWtRequestPayload updateFinalQcWtReqGen(String wbn) {
        UpdateFinalWtRequestPayload payload = UpdateFinalWtRequestPayload.builder()
                .wbn(wbn)
                .wt(11)
                .l(10)
                .h(20)
                .b(10)
                .intWtIwt("190")
                .div(5000)
                .wtRule("FINAL")
                .wtType("INTERNAL")
                .build();

        return payload;
    }

    public static InstaBaggingCreateRequestPayload instaBaggingCreateReqGen(HashMap<String, String> data) {
        InstaBaggingCreateRequestPayload payload = InstaBaggingCreateRequestPayload.builder()
                .bd(data.get("bd"))
                .bi(data.get("bi"))
                .bs(null)
                .bt(data.get("bt"))
                .dpc(true)
                .rm(false)
                .st(data.get("st"))
                .wbn(data.get("wbn"))
                .center_name(data.get("center_name"))
                .build();

        return payload;
    }

    public static InstaBaggingSealRequestPayload instaBaggingSealReqGen(HashMap<String, String> data) {
        InstaBaggingSealRequestPayload payload = InstaBaggingSealRequestPayload.builder()
                .bd(data.get("bd"))
                .bi(data.get("bi"))
                .bs(data.get("bs"))
                .bt(data.get("bt"))
                .wbn("")
                .bar(data.getOrDefault("bar","DV" + getUniqueInt(8)))
                .st(data.get("st"))
                .rm(false)
                .dpc("true")
                .center_name(data.get("center_name"))
                .build();
        return payload;
    }

    public static StatusRequest whsRequest(String name) {
        StatusRequest payload = StatusRequest.builder()
                .name(clientDetails.get("client_warehouse").toString())
                .phone("9899973145")
                .city("Delhi")
                .pin("110025")
                .address("PD block Pitampura")
                .country("India")
                .email("punitichauhan@gmail.com")
                .registeredName("PMTest1")
                .returnAddress("PMTAdd1")
                .returnPin("110018")
                .returnCity("Delhi")
                .returnState("Delhi")
                .returnCountry("India")
                .build();
        return payload;
    }

    public static EditRequest editwhsRequest(String name) {
        EditRequest payload = EditRequest.builder()
                .name(clientDetails.get("client_warehouse").toString())
                .phone("9899973145")
                .city("Delhi")
                .pin("110025")
                .address("PD block Pitampura")
                .country("India")
                .email("punitichauhan@gmail.com")
                .registeredName("PMTest1")
                .returnAddress("PMTAdd1")
                .returnPin("110018")
                .returnCity("Delhi")
                .returnState("Delhi")
                .returnCountry("India")
                .build();
        return payload;

    }

    public static CreateRequest createwhsRequest(String name) {
        CreateRequest payload = CreateRequest.builder()
                .name(getUniqueString())
                .phone("9899973145")
                .city("Delhi")
                .pin("110030")
                .address("PD block Pitampura")
                .country("India")
                .email("punitichauhan@gmail.com")
                .registeredName("PMTest1")
                .returnAddress("PMTAdd1")
                .returnPin("110018")
                .returnCity("Delhi")
                .returnState("Delhi")
                .returnCountry("India")
                .build();
        return payload;

    }

    // **agg wt update**//

    public static Agwtpayload agwtApiReqGen(HashMap<String, Object> dmap1) {
        return Agwtpayload.builder()
                .actionDate(System.currentTimeMillis())
                .waybill(dmap1.get("waybill").toString())
                .wbn(dmap1.get("waybill").toString())
                .mwn(dmap1.get("waybill").toString())
                .wtRule(dmap1.get("wtRule").toString())
                .wtType(dmap1.get("wtType").toString())
                .wt(dmap1.get("wt"))
                .maxWt(MaxWt.builder()
                        .dnm(378).build())
                .build();

    }

    public static QrcodeapiPayload QrcodeapiReqGen(HashMap<String, Object> dmap2) {
        return QrcodeapiPayload.builder()
                .waybill(dmap2.get("waybill").toString())
                .amount(dmap2.get("amount"))
                .gst(dmap2.get("gst"))
                .cgst(dmap2.get("cgst"))
                .sgst(dmap2.get("sgst"))
                .igst(dmap2.get("igst"))
                .cess(dmap2.get("cess"))
                .gstIncentive(dmap2.get("gst_incentive"))
                .gstpct(dmap2.get("gstpct"))
                .invoiceNo(dmap2.get("invoice_no"))
                .invoiceDate(dmap2.get("invoice_date"))
                .invoiceName(dmap2.get("invoice_name"))
                .gstIn(dmap2.get("gst_in"))
                .build();

    }

    public static FetchListRequest flApiReqGen(List<String> waybills) {
        return FetchListRequest.builder()
                .center("")
                .waybills(waybills)
                .build();

    }

    public static PhonelogRequest PhoneLogApiReqGen(HashMap<String, Object> dmap3) {
        return PhonelogRequest.builder()
                .waybill(dmap3.get("waybill")
                        .toString()).edit_page(dmap3.get("edit_page"))
                .consignee(dmap3.get("consignee"))
                .phoneNumber(dmap3.get("phoneNumber"))
                .build();

    }

    //create request payload for pojo of FetchB2cClaimsListingRequestParam with default value of each variable in class
    public static FetchB2cClaimsListingRequestParam fetchB2cClaimParamReqGen(HashMap<String, String> data) {
        FetchB2cClaimsListingRequestParam payload = FetchB2cClaimsListingRequestParam.builder()
                .limit(10)
                .offset(0)
                .fromDate("")
                .toDate("")
                .build();

        //add iterate function which iterates argument hashmap, iterate hashmap value and update payload value as found in loop
        for (Map.Entry<String, String> set : data.entrySet()) {
            String k = set.getKey();
            String v = set.getValue();
            if (k.equalsIgnoreCase("limit")) {
                payload.setLimit(Integer.parseInt(v));
            } else if (k.equalsIgnoreCase("offset")) {
                payload.setOffset(Integer.parseInt(v));
            } else if (k.equalsIgnoreCase("fromDate")) {
                payload.setFromDate(v);
            } else if (k.equalsIgnoreCase("toDate")) {
                payload.setToDate(v);
            }
        }

        return payload;
    }

    //create request payload for pojo of FetchB2cClaimsListingRequestParam with default value of each variable in class
    public static FetchB2bClaimsListingRequestParam fetchB2bClaimParamReqGen(HashMap<String, String> data) {
        FetchB2bClaimsListingRequestParam payload = FetchB2bClaimsListingRequestParam.builder()
                .limit(10)
                .offset(0)
                .fromDate("")
                .toDate("")
                .build();

        //add iterate function which iterates argument hashmap, iterate hashmap value and update payload value as found in loop
        for (Map.Entry<String, String> set : data.entrySet()) {
            String k = set.getKey();
            String v = set.getValue();
            if (k.equalsIgnoreCase("limit")) {
                payload.setLimit(Integer.parseInt(v));
            } else if (k.equalsIgnoreCase("offset")) {
                payload.setOffset(Integer.parseInt(v));
            } else if (k.equalsIgnoreCase("fromDate")) {
                payload.setFromDate(v);
            } else if (k.equalsIgnoreCase("toDate")) {
                payload.setToDate(v);
            }
        }

        return payload;
    }

    //create request payload for pojo of FetchUcpLossClaimsRequestParam with default value of each variable in class
    public static FetchUcpLossClaimsRequestParam fetchUcpLossClaimsParamReqGen(HashMap<String, String> data) {
        FetchUcpLossClaimsRequestParam payload = FetchUcpLossClaimsRequestParam.builder()
                .wbn("")
                .build();

        //add iterate function which iterates argument hashmap, iterate hashmap value and update payload value as found in loop
        for (Map.Entry<String, String> set : data.entrySet()) {
            String k = set.getKey();
            String v = set.getValue();
            if (k.equalsIgnoreCase("wbn")) {
                payload.setWbn(v);
            }
        }

        return payload;
    }

    public static CreateClaimsRequestPayload createClaimsReqGen(HashMap<String, String> data) {
        CreateClaimsRequestPayload payload = CreateClaimsRequestPayload.builder()
                .client_name("")
                .waybills(new ArrayList<CreateClaimWaybill>(Arrays.asList(CreateClaimWaybill.builder()
                        .wbn("")
                        .dispute_type("")
                        .claim_amount(0)
                        .client_remarks("")
                        .build())))
                .build();

        //add iterate function which iterates argument hashmap, iterate hashmap value and update payload value as found in loop
        for (Map.Entry<String, String> set : data.entrySet()) {
            String k = set.getKey();
            String v = set.getValue();
            if (k.equalsIgnoreCase("clientName")) {
                payload.setClient_name(v);
            } else if (k.equalsIgnoreCase("wbn")) {
                payload.getWaybills().get(0).setWbn(v);
            } else if (k.equalsIgnoreCase("disputeType")) {
                payload.getWaybills().get(0).setDispute_type(v);
            } else if (k.equalsIgnoreCase("claimAmount")) {
                payload.getWaybills().get(0).setClaim_amount(Integer.parseInt(v));
            } else if (k.equalsIgnoreCase("clientRemarks")) {
                payload.getWaybills().get(0).setClient_remarks(v);
            }
        }

        return payload;
    }

    //create request payload for pojo of FetchB2cClaimsListingRequestParam with default value of each variable in class
    public static FetchB2bClaimsDetailsRequestParam fetchB2bViewClaimParamReqGen(HashMap<String, String> data) {
        FetchB2bClaimsDetailsRequestParam payload = FetchB2bClaimsDetailsRequestParam.builder()
                .limit(10)
                .offset(0)
                .created_at("")
                .build();

        //add iterate function which iterates argument hashmap, iterate hashmap value and update payload value as found in loop
        for (Map.Entry<String, String> set : data.entrySet()) {
            String k = set.getKey();
            String v = set.getValue();
            if (k.equalsIgnoreCase("limit")) {
                payload.setLimit(Integer.parseInt(v));
            } else if (k.equalsIgnoreCase("offset")) {
                payload.setOffset(Integer.parseInt(v));
            } else if (k.equalsIgnoreCase("createdAt")) {
                payload.setCreated_at(v);
            }
        }

        return payload;
    }

    public static FetchClaimsReportRequestParam fetchClaimReportParamReqGen(HashMap<String, String> data) {
        FetchClaimsReportRequestParam payload = FetchClaimsReportRequestParam.builder()
                .from_date("")
                .to_date("")
                .build();

        //add iterate function which iterates argument hashmap, iterate hashmap value and update payload value as found in loop
        for (Map.Entry<String, String> set : data.entrySet()) {
            String k = set.getKey();
            String v = set.getValue();
            if (k.equalsIgnoreCase("fromDate")) {
                payload.setFrom_date(v);
            } else if (k.equalsIgnoreCase("toDate")) {
                payload.setTo_date(v);
            }
        }

        return payload;
    }

    public static UpdateClaimSettlementAmountRequestPayload updateClaimSettlementAmountReqGen(HashMap<String, String> data) {
        UpdateClaimSettlementAmountRequestPayload payload = UpdateClaimSettlementAmountRequestPayload.builder()
                .pdt("")
                .created_at("")
                .action_type("")
                .settlement_amount(0)
                .build();

        //add iterate function which iterates argument hashmap, iterate hashmap value and update payload value as found in loop
        for (Map.Entry<String, String> set : data.entrySet()) {
            String k = set.getKey();
            String v = set.getValue();
            if (k.equalsIgnoreCase("pdt")) {
                payload.setPdt(v);
            } else if (k.equalsIgnoreCase("createdAt")) {
                payload.setCreated_at(v);
            } else if (k.equalsIgnoreCase("actionType")) {
                payload.setAction_type(v);
            } else if (k.equalsIgnoreCase("settlementAmount")) {
                payload.setSettlement_amount(Integer.parseInt(v));
            }
        }

        return payload;
    }


    public static CenterUpdateRequest CenterUpdateApiReqGen(String wbn, String UpdatedCn) {
        List<UpdateDatum> updateDataLists = new ArrayList<>();
        updateDataLists.add(updatedDataList(wbn, UpdatedCn));
        CenterUpdateRequest payload = CenterUpdateRequest.builder()
                .updateData(updateDataLists)
                ._interface("MAPPER")
                .actionType("MAPPER_UPDATE")
                .build();

        return payload;

    }

    public static CenterUpdateRequestRT CenterUpdateRTApiReqGen(String wbn, String UpdatedRcn) {
        List<UpdateDatumRT> updateDataLists = new ArrayList<>();
        updateDataLists.add(updatedDataListRT(wbn, UpdatedRcn));
        CenterUpdateRequestRT payload = CenterUpdateRequestRT.builder()
                .updateData(updateDataLists)
                ._interface("RETURNS MAPPER")
                .actionType("MAPPER_UPDATE")
                .build();

        return payload;

    }

    public static UpdateDatum updatedDataList(String wbn, String UpdatedCn) {
        UpdateDatum payload = UpdateDatum.builder()
                .cn(UpdatedCn)
                .wbn(wbn)
                .build();
        return payload;
    }

    public static UpdateDatumRT updatedDataListRT(String wbn, String UpdatedRcn) {
        UpdateDatumRT payload = UpdateDatumRT.builder()
                .rcn(UpdatedRcn)
                .wbn(wbn)
                .build();
        return payload;
    }

    public static Applynslgeneric ApplyNslGeneric(List<String> wbns, String status_type, String nsl_code, String nsl_remark) {
        return Applynslgeneric.builder()
                .userName("gatee.khenwar")
                .statusType(status_type)
                .src("microsite")
                .wbn(Wbn.builder()
                        .waybill(com.delhivery.Express.pojo.applynslgeneric.request.Waybill.builder()
                                .nslCode(nsl_code)
                                .nslRemark(nsl_remark)
                                .build())
                        .build())
                .build();


    }

    public static applynsl Applynsl(List<String> wbns, String nsl_id) {
        return applynsl.builder()
                .nsl(nsl_id)
                .wbns(wbns)
                .build();

    }


    public static APIRestEWayBillRequestPayload apiRestEWayBillRequestPayload(Map<String, String> updateRSDCNEWBNOfWayBillMap) {
        List<APIRestDataPayload> dataList = new ArrayList<>();
        dataList.add(apiRestEWayBillDataRequest(updateRSDCNEWBNOfWayBillMap));
        return APIRestEWayBillRequestPayload.builder()
                .apiRestDataPayloadList(dataList).build();
    }

    public static APIRestDataPayload apiRestEWayBillDataRequest(Map<String, String> dataMap) {
        return APIRestDataPayload.builder()
                .rs(Integer.parseInt(dataMap.get("rs")))
                .eWbn(dataMap.get("eWayBillNumber"))
                .dcn(dataMap.get("documentNumber")).build();

    }

    public static UnsetReturnDispatchIdReqPayload unsetReturnDispatchIdReqGen(Map<String, Object> requestData, List<String> waybill) {
        return UnsetReturnDispatchIdReqPayload.builder()
                .centerCode(requestData.containsKey("center_code") ? requestData.get("center_code").toString() : origin_center.get("SortCode").toString())
                .refIds(waybill)
                .dispatchId(requestData.get("dispatch_id").toString())
                .username(requestData.containsKey("user_name") ? requestData.get("username").toString() : "akshat")
                .scanTime(requestData.containsKey("scan_time") ? requestData.get("scan_time").toString() : DateTimeUtility.getDateTimeODX(0))
                .build();
    }

    public static UpdateTransIdRequestPayload updateTransId() {
        return UpdateTransIdRequestPayload.builder()
                .cburl(Cburl.builder()
                        .url("https://odx-fm-bk-stage.delhivery.com/packages/bulk_lock/")
                        .method("PATCH")
                        .build())
                .wbnsDict(WbnsDict.builder()
                        .waybill(WbnTransIdUpdateBulk.builder()
                                .transactionId("134_TxnId")
                                .build())
                        .build())
                .action("UPDATE")
                .build();
    }

    public static UpdateTransId1RequestPayload updateTransId1(String upl) {
        return UpdateTransId1RequestPayload.builder()
                .data(UpdateTransIdDexData.builder()
                        .cburl(UpdateTransIdDexCburl.builder()
                                .url("https://odx-fm-bk-stage.delhivery.com/packages/bulk_lock/")
                                .method("PATCH")
                                .build())
                        .wbnsDict(UpdateTransIdDexWbnsDict.builder()
                                .waybill(UpdateTransIdDexWaybill.builder()
                                        .transactionId("1234567891212")
                                        .build())
                                .build())
                        .action("UPDATE")
                        .build())
                .upl(upl)
                .build();
    }

    public static UpdateBagStatusRequestPayload bagStatusUpdateHold(List<String> HoldBags, List<String> UnholdBags) {
        return UpdateBagStatusRequestPayload.builder()
                .hold(HoldBags)
                .unhold(UnholdBags)
                .build();
    }

    public static UpdateBagStatusRequestPayload bagStatusUpdateUnhold(List<String> HoldBags, List<String> UnholdBags) {
        return UpdateBagStatusRequestPayload.builder()
                .hold(HoldBags)
                .unhold(UnholdBags)
                .build();
    }

    public static ewbncreation createEwbn(String wbn) {
        return ewbncreation.builder()
                .src("HQ")
                .awb(wbn)
                .callbackUrl("itretyioudgfi/")
                .dataewbn(Dataewbn.builder()
                        .consolidate(false)
                        .modeOfTransport("ROAD")
                        //builder for tripitem from tripitembuilder class
                        .tripItems(new ArrayList<TripItem>(Arrays.asList(TripItem.builder()
                                .consignorAddr("Shop Num 19 Second Floor Silwar Sqauar Bhagwan Das Road C Scheme")
                                .consignorGst("URP")
                                .consigneePin("400059")
                                .consigneeName("ABSAR SK")
                                .consigneeGst("URP")
                                .billToState("MAHARASHTRA")
                                .consigneeAddr("sahebrampur jalangi ,sahebrampur jalangi murshidabad Domkol")
                                .consignorName("Shop.with.Style")
                                .qty(2)
                                .awb(wbn)
                                //function to get current date in yyyy-mm-dd-hh-mm-ss
                                .invoiceDt(Utilities.getDateTime(0))
                                //function for get unique integer
                                .invoiceNo("")
                                .invoiceVal(1.2)
                                .shipToState("MAHARASHTRA")
                                .unit("NOS")
                                .hsnCode("9105")
                                .ewaybill("")
                                .transactionType("SALES")
                                .consignorPin("400060")
                                .distance(10)
                                .build())))
                        .state("MH")
                        .vehicleNo("")
                        .transhipmentLocation("Mumbai")
                        .build())
                .build();

    }

    public static ReBagReqPayload reBagReqPayload(List<Map<String, Object>> reBagData) {
        return ReBagReqPayload.builder()
                .reBagDataReqPayloads(reBagDataReqPayloadList(reBagData))
                .build();
    }

    private static List<ReBagDataReqPayload> reBagDataReqPayloadList(List<Map<String, Object>> rebBagData) {
        return rebBagData.stream()
                .map(RequestBuilder::reBagDataReqPayload)
                .collect(Collectors.toList());
    }

    private static ReBagDataReqPayload reBagDataReqPayload(Map<String, Object> reBagData) {
        return ReBagDataReqPayload.builder()
                .action(reBagData.get("action").toString())
                .bagBarcode(reBagData.get("bag_barcode").toString())
                .sl(reBagData.get("sl").toString())
                .slid(reBagData.get("slid").toString())
                .build();
    }
}
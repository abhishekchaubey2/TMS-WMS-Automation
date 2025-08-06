package com.delhivery.Express.controllers.api;

import com.delhivery.Express.pojo.FetchPackageDetails.response.PackageDetail;
import com.delhivery.core.utils.ConfigLoader;
import com.delhivery.core.utils.Utilities;
import com.delhivery.core.utils.YamlReader;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DifferentStateShipments {
    ThreadLocal<PackageDetail> pkgDetails = new ThreadLocal<>();
    private String originCenter = "East_Delhi";
    private String destinationCenter = "Mumbai_MIDC";
    protected Map<String, Object> clientDetails = YamlReader
            .getYamlValues("Client_Details.client_" + ConfigLoader.getInstance().getRegressionClient());
    protected Map<String, Object> origin_center = YamlReader.getYamlValues("Centers." + originCenter);
    protected Map<String, Object> destination_center = YamlReader.getYamlValues("Centers." + destinationCenter);

    protected String ocid = YamlReader.getYamlValues("Centers." + originCenter).get("SortCode").toString();
    protected String cnid = YamlReader.getYamlValues("Centers." + destinationCenter).get("SortCode").toString();

    ApiController apiCtrl = new ApiController();

//    HashMap<String,String> data = new HashMap<String,String>();

    public String DifferentStateShipments(String state) {
        HashMap<String, String> data = new HashMap<String, String>();
        switch (state.toUpperCase()) {
            case "MANIFEST":
                return manifestShipment(data);
            //break;

            case "IN TRANSIT":
                return inTransitShipment(data);
            //break;

            case "PENDING":
                return pendingShipment(data);
            //break;

            case "DISPATCHED":
                return dispatchedShipment(data);
            //break;

            case "DELIVERED":
                return deliveredShipment(data);
            //break;

            case "RETURNED":
                return returnShipment(data);
            //break;

            case "RTO":
                return rtoShipment(data);
            //break;

            case "LOST":
                return lostShipment(data);
            //break;

            case "PICKUPPENDING":
                return pickupPendingShipment(data);
            //break;

            case "PICKEDUP":
                return pickedUpShipment(data);
            //break;

            case "DTO":
                return dtoShipment(data);
            //break;

            case "CANCELLED":
                return cancelledShipment(data);
            //break;

            default:
                return "null";

        }
    }


    public String DifferentStateShipments(String state, HashMap<String, String> manifestData) {
        HashMap<String, String> data = new HashMap<String, String>();
        data = manifestData;
        switch (state.toUpperCase()) {
            case "MANIFEST":
                return manifestShipment(data);
            //break;

            case "IN TRANSIT":
                return inTransitShipment(data);
            //break;

            case "PENDING":
                return pendingShipment(data);
            //break;

            case "DISPATCHED":
                return dispatchedShipment(data);
            //break;

            case "DELIVERED":
                return deliveredShipment(data);
            //break;

            case "RETURNED":
                return returnShipment(data);
            //break;

            case "RTO":
                return rtoShipment(data);
            //break;

            case "LOST":
                return lostShipment(data);
            //break;

            case "PICKUPPENDING":
                return pickupPendingShipment(data);
            //break;

            case "PICKEDUP":
                return pickedUpShipment(data);
            //break;

            case "DTO":
                return dtoShipment(data);
            //break;

            case "CANCELLED":
                return cancelledShipment(data);
            //break;

            case "REPL PICKUP":
                data.put("payment_mode", "REPL");
                if (!data.containsKey("product_type")) {
                    data.put("product_type", "B2C");
                }
                return replPickupShipment(data);

            case "REPL RETURNED":
                if (!data.containsKey("product_type")) {
                    data.put("product_type", "B2C");
                }
                data.put("payment_mode", "REPL");
                return replReturnShipment(data);
            default:
                return "null";
        }
    }

    private String manifestShipment(HashMap<String, String> data) {
        if (data.containsKey("pin")) {
            data.put("pin", data.get("pin"));
            if (data.containsKey("cn")) {
                destination_center = YamlReader.getYamlValues("Centers." + data.get("cn"));
            }
        } else if (data.containsKey("product_type")) {
            data.put("product_type", data.get("product_type"));
        } else if (data.containsKey("payment_mode")) {
            data.put("payment_mode", data.get("payment_mode"));
        } else {
            data.put("pin", "400059");
        }

        String waybill = apiCtrl.cmuManifestApi(data).get(0);
        return waybill;
    }

    private String inTransitShipment(HashMap<String, String> data) {
        String waybill = manifestShipment(data);
        apiCtrl.fmOMSApi(waybill, "FMPICK", data);
        if (data.containsKey("product_type") && (data.get("product_type").equalsIgnoreCase("B2B") || data.get("product_type").equalsIgnoreCase("Heavy"))) {
            Utilities.hardWait(60);
        }
        Utilities.hardWait(15);
        apiCtrl.giApi(waybill, data.containsKey("center")?data.get("center"):origin_center.get("Name").toString(), data);
        return waybill;
    }

    private String pendingShipment(HashMap<String, String> data) {
        if (!data.containsKey("payment_mode")) {
            data.put("payment_mode", "COD");
        }
        String waybill = manifestShipment(data);
        if (data.containsKey("product_type") && (data.get("product_type").equalsIgnoreCase("B2B") || data.get("product_type").equalsIgnoreCase("Heavy"))) {
            Utilities.hardWait(60);
        }

        pkgDetails.set(apiCtrl.fetchPackageInfo(waybill, data));
        if (pkgDetails.get().cn.equalsIgnoreCase("NSZ")) {
            Utilities.hardWait(60);
            pkgDetails.set(apiCtrl.fetchPackageInfo(waybill, data));
        }
        data.put("shipment_destination_center", pkgDetails.get().cn);
        apiCtrl.fmOMSApi(waybill, "FMPICK", pkgDetails.get().cnid, data);
        apiCtrl.giApi(waybill, pkgDetails.get().cn, data);
        return waybill;
    }

    private String dispatchedShipment(HashMap<String, String> data) {
        String waybill = pendingShipment(data);
        Utilities.hardWait(2);
        String dispatchId = apiCtrl.markShipmentDispatchApi(waybill, data);
        return waybill;
    }

    private String deliveredShipment(HashMap<String, String> data) {
        String waybill = dispatchedShipment(data);
        Utilities.hardWait(2);
        apiCtrl.lmUpdateHQShipmentApi(waybill, "Delivered", data);
        return waybill;
    }

    private String returnShipment(HashMap<String, String> data) {
        String waybill = inTransitShipment(data);
        Utilities.hardWait(10);
        List<String> waybills = new ArrayList<>();
        waybills.add(waybill);
        apiCtrl.ApplyNsl(waybills, "RT", "RT-101", data);
        return waybill;
    }

    private String rtoShipment(HashMap<String, String> data) {
        String waybill = returnShipment(data);
        Utilities.hardWait(2);
        List<String> waybills = new ArrayList<>();
        waybills.add(waybill);
        apiCtrl.ApplyNsl(waybills, "DL", "RT-110", data);
        return waybill;
    }

    private String lostShipment(HashMap<String, String> data) {
        String waybill = inTransitShipment(data);
        Utilities.hardWait(2);
        List<String> waybills = new ArrayList<>();
        waybills.add(waybill);
        apiCtrl.ApplyNsl(waybills, "LT", "LT-100", data);
        return waybill;
    }

    private String pickupPendingShipment(HashMap<String, String> data) {
        data.put("payment_mode", "Pickup");
        String waybill = apiCtrl.cmuManifestApi(data).get(0);
        List<String> waybills = new ArrayList<>();
        waybills.add(waybill);
        Utilities.hardWait(10);
        apiCtrl.ApplyNsl(waybills, "PP", "X-ASP", data);
        PackageDetail pkgDetails = apiCtrl.fetchPackageInfo(waybill, data);
        if (pkgDetails.cn.equalsIgnoreCase("NSZ")) {
            Utilities.hardWait(60);
            pkgDetails = apiCtrl.fetchPackageInfo(waybill, data);
        }
        data.put("shipment_destination_center", pkgDetails.cn);
        return waybill;
    }

    private String pickedUpShipment(HashMap<String, String> data) {
        String waybill = pickupPendingShipment(data);
        Utilities.hardWait(2);
        String dispatchId = apiCtrl.markRerveseShipmentDispatchApi(waybill, data);
        Utilities.hardWait(10);
        apiCtrl.lmUpdateHQShipmentApi(waybill, "PickedUp", data);
        Utilities.hardWait(2);
        apiCtrl.unsetShipmentDispatchIdApi(waybill, dispatchId, data);
        return waybill;
    }

    private String dtoShipment(HashMap<String, String> data) {
        String waybill = pickedUpShipment(data);
        Utilities.hardWait(2);
        List<String> waybills = new ArrayList<>();
        waybills.add(waybill);
        apiCtrl.ApplyNsl(waybills, "DL", "RT-111", data);
        return waybill;
    }

    private String cancelledShipment(HashMap<String, String> data) {
        String waybill = pickupPendingShipment(data);
        Utilities.hardWait(2);
        String dispatchId = apiCtrl.markRerveseShipmentDispatchApi(waybill, data);
        Utilities.hardWait(10);
        apiCtrl.lmUpdateHQShipmentApi(waybill, "Cancelled", data);
        Utilities.hardWait(2);
        apiCtrl.unsetShipmentDispatchIdApi(waybill, dispatchId, data);
        return waybill;
    }

    private String replPickupShipment(HashMap<String, String> data) {
        String waybill = dispatchedShipment(data);
        Utilities.hardWait(2);
        apiCtrl.lmUpdateHQShipmentApi(waybill, "REPL PU", data);
        PackageDetail pkgDetails = apiCtrl.fetchPackageInfo(waybill, data);
        Utilities.hardWait(2);
        apiCtrl.unsetShipmentDispatchIdApi(waybill, pkgDetails.dd.id.toString(), data);
        return waybill;
    }

    private String replReturnShipment(HashMap<String, String> data) {
        String waybill = dispatchedShipment(data);
        Utilities.hardWait(2);
        apiCtrl.lmUpdateHQShipmentApi(waybill, "REPL RT", data);
        PackageDetail pkgDetails = apiCtrl.fetchPackageInfo(waybill, data);
        Utilities.hardWait(2);
        apiCtrl.unsetShipmentDispatchIdApi(waybill, pkgDetails.dd.id.toString(), data);
        return waybill;
    }
}
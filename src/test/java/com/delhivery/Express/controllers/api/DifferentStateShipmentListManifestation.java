package com.delhivery.Express.controllers.api;

import com.delhivery.Express.pojo.FetchPackageDetails.response.PackageDetail;
import com.delhivery.core.utils.ConfigLoader;
import com.delhivery.core.utils.Utilities;
import com.delhivery.core.utils.YamlReader;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class DifferentStateShipmentListManifestation {
    private final ThreadLocal<PackageDetail> packageDetail = new ThreadLocal<>();
    private final ThreadLocal<ArrayList<String>> waybill = new ThreadLocal<>();
    private final ThreadLocal<String> dispatchId = new ThreadLocal<>();

    private final String originCenter = "East_Delhi";
    private final String destinationCenter = "Mumbai_MIDC";
    protected Map<String, Object> clientDetails = YamlReader
            .getYamlValues("Client_Details.client_" + ConfigLoader.getInstance().getRegressionClient());
    protected Map<String, Object> origin_center = YamlReader.getYamlValues("Centers." + originCenter);
    protected Map<String, Object> destination_center = YamlReader.getYamlValues("Centers." + destinationCenter);

    protected String ocid = YamlReader.getYamlValues("Centers." + originCenter).get("SortCode").toString();
    protected String cnid = YamlReader.getYamlValues("Centers." + destinationCenter).get("SortCode").toString();

    ApiController apiCtrl = new ApiController();

    public ArrayList<String> getDifferentStateShipmentAsList(String state) {
        HashMap<String, String> data = new HashMap<>();
        switch (state.toUpperCase()) {
            case "MANIFEST":
                return manifestShipment(data);

            case "IN TRANSIT":
                return inTransitShipment(data);

            case "PENDING":
                return pendingShipment(data);

            case "DISPATCHED":
                return dispatchedShipment(data);

            case "DELIVERED":
                return deliveredShipment(data);

            case "RETURNED":
                return returnShipment(data);

            case "RTO":
                return rtoShipment(data);

            case "LOST":
                return lostShipment(data);

            case "PICKUPPENDING":
                return pickupPendingShipment(data);

            case "PICKEDUP":
                return pickedUpShipment(data);

            case "DTO":
                return dtoShipment(data);

            case "CANCELLED":
                return cancelledShipment(data);

            default:
                throw new RuntimeException("Shipment State : " + state + " is not whitelisted !!!");
        }
    }


    public ArrayList<String> getDifferentStateShipmentAsList(String state, HashMap<String, String> data) {
        switch (state.toUpperCase()) {
            case "MANIFEST":
                return manifestShipment(data);

            case "IN TRANSIT":
                return inTransitShipment(data);

            case "PENDING":
                return pendingShipment(data);

            case "DISPATCHED":
                return dispatchedShipment(data);

            case "DELIVERED":
                return deliveredShipment(data);

            case "RETURNED":
                return returnShipment(data);

            case "RTO":
                return rtoShipment(data);

            case "LOST":
                return lostShipment(data);

            case "PICKUPPENDING":
                return pickupPendingShipment(data);

            case "PICKEDUP":
                return pickedUpShipment(data);

            case "DTO":
                return dtoShipment(data);

            case "CANCELLED":
                return cancelledShipment(data);

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
                throw new RuntimeException("Shipment State : " + state + " is not whitelisted !!!");
        }
    }

    private ArrayList<String> manifestShipment(HashMap<String, String> data) {
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
        Utilities.hardWait(30);
        return apiCtrl.cmuManifestApi(data);
    }

    private ArrayList<String> inTransitShipment(HashMap<String, String> data) {
        ArrayList<String> waybill = manifestShipment(data);
        apiCtrl.fmOMSApi(waybill, "FMPICK", data);
        if (data.containsKey("product_type") && (data.get("product_type").equalsIgnoreCase("B2B") || data.get("product_type").equalsIgnoreCase("Heavy"))) {
            Utilities.hardWait(60);
        }
        PackageDetail pkgDetails = apiCtrl.fetchPackageInfo(waybill.get(0), data);

        if (pkgDetails.cn.equalsIgnoreCase("NSZ")) {
            Utilities.hardWait(60);
        }
        Utilities.hardWait(20);
        apiCtrl.giApi(waybill, origin_center.get("Name").toString(), data);
        return waybill;
    }

    private ArrayList<String> pendingShipment(HashMap<String, String> data) {
        if (!data.containsKey("payment_mode")) {
            data.put("payment_mode", "COD");
        }

        ArrayList<String> waybill = manifestShipment(data);
        if (data.containsKey("product_type") && (data.get("product_type").equalsIgnoreCase("B2B") || data.get("product_type").equalsIgnoreCase("Heavy"))) {
            Utilities.hardWait(60);
        }

        for (String wbn : waybill) {
            PackageDetail pkgDetails = apiCtrl.fetchPackageInfo(wbn, data);
            if (pkgDetails.cn.equalsIgnoreCase("NSZ")) {
                Utilities.hardWait(60);
            }
            data.put("shipment_destination_center", pkgDetails.cn);
            apiCtrl.fmOMSApi(wbn, "FMPICK", pkgDetails.cnid, data);
            apiCtrl.giApi(wbn, pkgDetails.cn, data);
        }

        return waybill;
    }

    private ArrayList<String> dispatchedShipment(HashMap<String, String> data) {
        ArrayList<String> waybill = pendingShipment(data);
        Utilities.hardWait(2);
        apiCtrl.markShipmentDispatchApi(waybill, data);
        return waybill;
    }

    private ArrayList<String> deliveredShipment(HashMap<String, String> data) {
        ArrayList<String> waybill = dispatchedShipment(data);
        Utilities.hardWait(2);
        apiCtrl.lmUpdateHQShipmentApi(waybill, "Delivered", data);
        return waybill;
    }

    private ArrayList<String> returnShipment(HashMap<String, String> data) {
        ArrayList<String> waybill = inTransitShipment(data);
        Utilities.hardWait(10);
        apiCtrl.ApplyNsl(waybill, "RT", "RT-101", data);
        return waybill;
    }

    private ArrayList<String> rtoShipment(HashMap<String, String> data) {
        ArrayList<String> waybill = returnShipment(data);
        Utilities.hardWait(2);
        apiCtrl.ApplyNsl(waybill, "DL", "RT-110", data);
        return waybill;
    }

    private ArrayList<String> lostShipment(HashMap<String, String> data) {
        ArrayList<String> waybill = inTransitShipment(data);
        Utilities.hardWait(2);
        apiCtrl.ApplyNsl(waybill, "LT", "LT-100", data);
        return waybill;
    }

    private ArrayList<String> pickupPendingShipment(HashMap<String, String> data) {
        data.put("payment_mode", "Pickup");
        ArrayList<String> waybill = apiCtrl.cmuManifestApi(data);
        Utilities.hardWait(10);
        apiCtrl.ApplyNsl(waybill, "PP", "X-ASP", data);

        PackageDetail pkgDetails = apiCtrl.fetchPackageInfo(waybill.get(0), data);
        if (pkgDetails.cn.equalsIgnoreCase("NSZ")) {
            Utilities.hardWait(60);
        }

        return waybill;
    }

    private ArrayList<String> pickedUpShipment(HashMap<String, String> data) {
        waybill.set(pickupPendingShipment(data));
        Utilities.hardWait(2);
        dispatchId.set(apiCtrl.markRerveseShipmentDispatchApi(waybill.get(), data));
        Utilities.hardWait(10);
        apiCtrl.lmUpdateHQShipmentApi(waybill.get(), "PickedUp", data);
        Utilities.hardWait(2);

        if (data.containsKey("rt_odx")) {
            HashMap<String, Object> reqData = new HashMap<>();
            reqData.put("dispatch_id", dispatchId.get());
            Utilities.logInfo("Dispatch id : " + dispatchId.get() + " for  waybill : " + waybill.get());
            System.out.println("Dispatch id : " + dispatchId.get() + " for  waybill : " + waybill.get());
            apiCtrl.unsetReturnDispatchId(reqData, waybill.get());

            return waybill.get();
        }

        apiCtrl.unsetShipmentDispatchIdApi(waybill.get(), dispatchId.get(), data);
        return waybill.get();
    }

    private ArrayList<String> dtoShipment(HashMap<String, String> data) {
        ArrayList<String> waybill = pickedUpShipment(data);
        Utilities.hardWait(2);
        apiCtrl.ApplyNsl(waybill, "DL", "RT-111", data);
        return waybill;
    }

    private ArrayList<String> cancelledShipment(HashMap<String, String> data) {
        ArrayList<String> waybill = pickupPendingShipment(data);
        Utilities.hardWait(2);
        String dispatchId = apiCtrl.markRerveseShipmentDispatchApi(waybill, data);
        Utilities.hardWait(10);
        apiCtrl.lmUpdateHQShipmentApi(waybill, "Cancelled", data);
        Utilities.hardWait(2);

        if (data.containsKey("dont_unset_dd_id")) {
            return waybill;
        }

        apiCtrl.unsetShipmentDispatchIdApi(waybill, dispatchId, data);
        return waybill;
    }

    private ArrayList<String> replPickupShipment(HashMap<String, String> data) {
        waybill.set(dispatchedShipment(data));
        Utilities.hardWait(2);
        apiCtrl.lmUpdateHQShipmentApi(waybill.get(), "REPL PU", data);

        packageDetail.set(apiCtrl.fetchPackageInfo(waybill.get().get(0), data));
        dispatchId.set(packageDetail.get().dd.id.toString());
        Utilities.hardWait(2);

        if (data.containsKey("rt_odx")) {
            HashMap<String, Object> reqData = new HashMap<>();
            reqData.put("dispatch_id", dispatchId.get());
            Utilities.logInfo("Dispatch id : " + dispatchId.get() + " for  waybill : " + waybill.get());
            System.out.println("Dispatch id : " + dispatchId.get() + " for  waybill : " + waybill.get());
            apiCtrl.unsetReturnDispatchId(reqData, waybill.get());
            return waybill.get();
        }

        apiCtrl.unsetShipmentDispatchIdApi(waybill.get(), dispatchId.get(), data);
        return waybill.get();
    }

    private ArrayList<String> replReturnShipment(HashMap<String, String> data) {
        ArrayList<String> waybill = dispatchedShipment(data);
        Utilities.hardWait(2);
        apiCtrl.lmUpdateHQShipmentApi(waybill, "REPL RT", data);
        PackageDetail pkgDetails = apiCtrl.fetchPackageInfo(waybill.get(0), data);
        Utilities.hardWait(2);

        if (data.containsKey("rt_odx")) {
            HashMap<String, Object> reqData = new HashMap<>();
            reqData.put("dispatch_id", pkgDetails.dd.id);
            System.out.println("Dispatch id : " + pkgDetails.dd.id + " for  waybill : " + waybill);
            Utilities.logInfo("Dispatch id : " + pkgDetails.dd.id + " for  waybill : " + waybill);
            apiCtrl.unsetReturnDispatchId(reqData, waybill);
            return waybill;
        }

        apiCtrl.unsetShipmentDispatchIdApi(waybill, pkgDetails.dd.id.toString(), data);
        return waybill;
    }
}

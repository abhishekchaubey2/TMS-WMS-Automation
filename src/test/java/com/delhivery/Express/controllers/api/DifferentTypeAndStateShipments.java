package com.delhivery.Express.controllers.api;

import com.delhivery.core.utils.ConfigLoader;
import com.delhivery.core.utils.Utilities;
import com.delhivery.core.utils.YamlReader;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class DifferentTypeAndStateShipments {
    public static String getClientName(String variable_name) {
        Map<String, Object> clientDetails1 = YamlReader
                .getYamlValues("Client_Details.client_" + ConfigLoader.getInstance().getClient(variable_name));
        return clientDetails1.get("name").toString();
    }

    protected static Map<String, Object> clientDetails = YamlReader
            .getYamlValues("Client_Details.client_" + ConfigLoader.getInstance().getRegressionClient());
    protected static Map<String, Object> origin_center = YamlReader.getYamlValues("Centers.East_Delhi");
    protected static Map<String, Object> destination_center = YamlReader.getYamlValues("Centers.Mumbai_MIDC");

    static ApiController apiCtrl = new ApiController();

    public ArrayList<String> getDifferentTypeAndStateShipments(String type, String state, HashMap<String, String> Payload) {
        ArrayList<String> waybills;
        HashMap<String, String> data = new HashMap<>();
        data.putAll(Payload);

        switch (type.toUpperCase()) {
            case "B2C":
            case "B2C WITH CWH":
                data.put("product_type", "B2C");
                data.put("package_count", "1");
                return manifestShipment(type,state, data);

            case "B2C MPS":
            case "B2C MPS WITH CWH":
                data.put("product_type", "B2C");
                data.put("mps_amount", "14");
                data.put("total_amount", "1234");
                data.put("package_count", "2");
                return manifestShipment(type,state, data);

            case "B2B":
            case "B2B WITH CWH":
                data.put("product_type", "B2B");
                data.put("package_count", "1");
                return manifestShipment(type,state, data);

            case "B2B MPS":
            case "B2B MPS WITH CWH":
                data.put("product_type", "B2B");
                data.put("mps_amount", "14");
                data.put("total_amount", "1234");
                data.put("package_count", "2");
                return manifestShipment(type,state, data);

            case "HEAVY":
            case "HEAVY WITH CWH":
                data.put("product_type", "Heavy");
                data.put("package_count", "1");
                return manifestShipment(type,state, data);

            case "HEAVY MPS":
            case "HEAVY MPS WITH CWH":
                data.put("product_type", "Heavy");
                data.put("mps_amount", "14");
                data.put("total_amount", "1234");
                data.put("package_count", "2");
                return manifestShipment(type,state, data);

            case "B2B MPS WITH INTERNAL CHILD":
                if (data.containsKey("master_id") && data.containsKey("waybill")) {
                    data.put("master_id", data.get("master_id"));
                    data.put("waybill", data.get("waybill"));
                } else {
                    waybills = apiCtrl.FetchWaybills("HQAPIREGRESSION", 2, data);
                    if (data.containsKey("client")) {
                        waybills = apiCtrl.FetchWaybills(getClientName(data.get("client")), 2, data);
                    }
                    data.put("master_id", waybills.get(0));
                    data.put("waybill", waybills.get(1));
                }
                data.put("product_type", "B2B");
                return manifestShipment(type,state, data);

            case "MPS WITH MCOUNT 1":
                if (data.containsKey("master_id")) {
                    data.put("master_id", data.get("master_id"));
                } else {
                    waybills = apiCtrl.FetchWaybills("HQAPIREGRESSION", 1, data);
                    if (data.containsKey("client")) {
                        waybills = apiCtrl.FetchWaybills(getClientName(data.get("client")), 1, data);
                    }
                    data.put("master_id", waybills.get(0));
                }
                return manifestShipment(type,state, data);

            case "B2C MPS SHIPMENT WITH CHILD PAYLOAD":
                data.put("product_type", "B2C");
                return manifestShipment(type,state, data);

            case "B2B MPS SHIPMENT WITH CHILD PAYLOAD":
                data.put("product_type", "B2B");
                return manifestShipment(type,state, data);

            case "HEAVY MPS SHIPMENT WITH CHILD PAYLOAD":
                data.put("product_type", "Heavy");
                return manifestShipment(type,state, data);

            case "B2C MPS WITH MCOUNT 1":
                if (data.containsKey("master_id")) {
                    data.put("master_id", data.get("master_id"));
                } else {
                    waybills = apiCtrl.FetchWaybills("HQAPIREGRESSION", 1, data);
                    if (data.containsKey("client")) {
                        waybills = apiCtrl.FetchWaybills(getClientName(data.get("client")), 1, data);
                    }
                    data.put("master_id", waybills.get(0));
                }
                data.put("product_type", "B2C");
                return manifestShipment(type,state, data);


            //NO DATA, PARTIALLY MANIFESTED and NO DATA WITHOUT MANIFESTATION are taken care by default case
            default:
                return manifestShipment(type,state, data);

        }
    }


    private ArrayList<String> manifestShipment(String type,String state, HashMap<String, String> data) {
        ArrayList<String> waybills = new ArrayList<>();

        if (type.equalsIgnoreCase("B2B MPS WITH INTERNAL CHILD") || type.equalsIgnoreCase("MPS WITH MCOUNT 1")
                || type.equalsIgnoreCase("B2C MPS WITH MCOUNT 1")) {
            waybills = apiCtrl.cmuManifestApiB2BWithInternalChild(data);
        } else if (type.contains("MPS SHIPMENT WITH CHILD PAYLOAD")) {
            waybills = apiCtrl.cmuManifestMPSWithChildPayload(data);
        } else if (type.equalsIgnoreCase("NO DATA") && data.containsKey("client") && !data.get("client").equalsIgnoreCase("regression_client")) {

            String token = apiCtrl.fetchUserJwtTokenApi(data);
            String upl = apiCtrl.createNoDataUplApi(data, token);
            Utilities.hardWait(30);
            waybills = (ArrayList<String>) apiCtrl.getNoDataUplShipment(upl, token);
            try {
                data.put("NAME", data.get("client"));
                apiCtrl.cmuV2ManifestNoDataShipment(waybills, data, token);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        } else if (type.equalsIgnoreCase("NO DATA WITHOUT MANIFESTATION") && data.containsKey("client") && !data.get("client").equalsIgnoreCase("regression_client")) {
            String token = apiCtrl.fetchUserJwtTokenApi(data);
            String upl = apiCtrl.createNoDataUplApi(data, token);
            Utilities.hardWait(40);
            waybills = (ArrayList<String>) apiCtrl.getNoDataUplShipment(upl, token);
        } else if (type.equalsIgnoreCase("NO DATA")) {
            String token = apiCtrl.fetchUserJwtTokenApi(null);
            String upl = apiCtrl.createNoDataUplApi(token);
            Utilities.hardWait(40);
            waybills = (ArrayList<String>) apiCtrl.getNoDataUplShipment(upl, token);
            try {
                apiCtrl.cmuV2ManifestNoDataShipment(waybills, data, token);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        } else if (type.equalsIgnoreCase("NO DATA WITHOUT MANIFESTATION")) {
            String token = apiCtrl.fetchUserJwtTokenApi(null);
            String upl = apiCtrl.createNoDataUplApi(token);
            Utilities.hardWait(40);
            waybills = (ArrayList<String>) apiCtrl.getNoDataUplShipment(upl, token);
        } else if (data.containsKey("enviorment")) {
            waybills = apiCtrl.cmuManifestApiDiffEnv(data.get("enviorment"), data);
        } else if (type.equalsIgnoreCase("PARTIALLY MANIFESTED") && data.containsKey("client") && !data.get("client").equalsIgnoreCase("regression_client")) {
            String token = apiCtrl.fetchUserJwtTokenApi(data);
            String upl = apiCtrl.createPartiallyManifestedUplApi(data, token);
            Utilities.hardWait(40);
            waybills = (ArrayList<String>) apiCtrl.getNoDataUplShipment(upl, token);
        } else if (type.equalsIgnoreCase("PARTIALLY MANIFESTED")) {
            String token = apiCtrl.fetchUserJwtTokenApi(null);
            String upl = apiCtrl.createPartiallyManifestedUplApi(token);
            Utilities.hardWait(40);
            waybills = (ArrayList<String>) apiCtrl.getNoDataUplShipment(upl, token);
            try {
                apiCtrl.cmuV2ManifestNoDataShipment(waybills, data, token);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else if (type.contains("WITH CWH")) {
            try {
                waybills = apiCtrl.cmuManifestWithCwhApi(data);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else {
            waybills = apiCtrl.cmuManifestApi(data);
        }

        flowPackageToRequestedState(state,waybills,data);
        return waybills;
    }

    private void flowPackageToRequestedState(String state, ArrayList<String> waybills, HashMap<String,String>data){
        switch(state){
            case "MANIFESTED":
                break;

            case "IN TRANSIT":
                 flowPackageToInTransitState(waybills,data);
                 break;

            default:
                throw new RuntimeException("Undefined state "+state);
        }
    }

    private void flowPackageToInTransitState(ArrayList<String>waybills, HashMap<String,String> data){
        apiCtrl.fmOMSApi(waybills, "FMPICK", data);
        if (data.containsKey("product_type") && (data.get("product_type").equalsIgnoreCase("B2B") || data.get("product_type").equalsIgnoreCase("Heavy"))) {
            Utilities.hardWait(60);
        }
        Utilities.hardWait(15);
        apiCtrl.giApi(waybills, data.containsKey("center")?data.get("center"):origin_center.get("Name").toString(), data);
    }
}
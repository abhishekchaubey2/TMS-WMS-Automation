package com.delhivery.TMS_WMS.RequestBuilder;

import com.delhivery.TMS_WMS.pojo.wmsorder.request.*;
import com.delhivery.core.utils.ConfigLoader;
import com.delhivery.core.utils.Utilities;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * WMS Request Builder - Follows Express RequestBuilder pattern
 * Uses builder pattern with HashMap override for flexibility
 */
public class WmsRequestBuilder {
    
    private static final ConfigLoader config = ConfigLoader.getInstance();
    
    /**
     * Build WMS Order Creation Request with defaults from qa.properties
     * HashMap data can override any default value
     * @param baseOrderNumber Base order number (last 4 digits will be auto-generated)
     * @param data HashMap with optional overrides
     * @return CreateOrderRequest
     */
    public static CreateOrderRequest buildCreateOrderRequest(String baseOrderNumber, HashMap<String, String> data) {
        // Generate unique order number
        String uniqueOrderNumber = generateUniqueOrderNumber(baseOrderNumber);
        
        // Build Order Line with defaults
        OrderLine orderLine = new OrderLine();
        orderLine.setNumber("OL1");
        orderLine.setProductSku(getValueOrDefault(data, "product_sku", config.getProperty("wms.order.default.productSku")));
        orderLine.setQuantity(Integer.parseInt(getValueOrDefault(data, "quantity", config.getProperty("wms.order.default.quantity"))));
        orderLine.setClientId(getValueOrDefault(data, "client_id", config.getProperty("wms.order.default.clientId")));
        orderLine.setBucket(getValueOrDefault(data, "bucket", config.getProperty("wms.order.default.bucket")));
        
        List<OrderLine> orderLines = new ArrayList<>();
        orderLines.add(orderLine);
        
        // Build Invoice (same number as order)
        Invoice invoice = new Invoice();
        invoice.setInvoiceNumber(uniqueOrderNumber);
        invoice.setPaymentMode(getValueOrDefault(data, "payment_mode", config.getProperty("wms.order.default.paymentMode")));
        invoice.setNetAmount(getValueOrDefault(data, "net_amount", config.getProperty("wms.order.default.netAmount")));
        
        // Build Shipment (same number as order)
        Shipment shipment = new Shipment();
        shipment.setPriority(getValueOrDefault(data, "priority", config.getProperty("wms.order.default.priority")));
        shipment.setEwaybillExpiryDate(0L);
        shipment.setShippedBy(getValueOrDefault(data, "shipped_by", config.getProperty("wms.order.default.shippedBy")));
        shipment.setNumber(uniqueOrderNumber);
        shipment.setWorkflow(getValueOrDefault(data, "workflow", config.getProperty("wms.order.default.workflow")));
        shipment.setFc(getValueOrDefault(data, "fc", config.getProperty("wms.order.default.fc")));
        shipment.setEwaybillExpiryDate2(0L);
        shipment.setInvoice(invoice);
        shipment.setOrderLines(orderLines);
        
        List<Shipment> shipments = new ArrayList<>();
        shipments.add(shipment);
        
        // Build Consignee
        Consignee consignee = new Consignee();
        consignee.setName(getValueOrDefault(data, "consignee_name", config.getProperty("wms.order.default.consigneeName")));
        consignee.setAddressLine1(getValueOrDefault(data, "address_line1", config.getProperty("wms.order.default.addressLine1")));
        consignee.setPinCode(Integer.parseInt(getValueOrDefault(data, "pin_code", config.getProperty("wms.order.default.pinCode"))));
        consignee.setCity(getValueOrDefault(data, "city", config.getProperty("wms.order.default.city")));
        consignee.setState(getValueOrDefault(data, "state", config.getProperty("wms.order.default.state")));
        consignee.setCountry(getValueOrDefault(data, "country", config.getProperty("wms.order.default.country")));
        consignee.setPrimaryPhoneNumber(getValueOrDefault(data, "primary_phone", config.getProperty("wms.order.default.primaryPhone")));
        consignee.setSecondaryPhoneNumber(getValueOrDefault(data, "secondary_phone", config.getProperty("wms.order.default.secondaryPhone")));
        
        // Build Order
        Order order = new Order();
        order.setOrderNumber(uniqueOrderNumber);
        order.setOrderDate(getValueOrDefault(data, "order_date", config.getProperty("wms.order.default.orderDate")));
        order.setOrderType(getValueOrDefault(data, "order_type", config.getProperty("wms.order.default.orderType")));
        order.setChannel(getValueOrDefault(data, "channel", config.getProperty("wms.order.default.channel")));
        order.setShipments(shipments);
        order.setConsignee(consignee);
        
        List<Order> orders = new ArrayList<>();
        orders.add(order);
        
        // Build Request
        OrderData orderData = new OrderData(orders);
        CreateOrderRequest request = new CreateOrderRequest("CREATE", orderData);
        
        return request;
    }
    
    /**
     * Generate unique order number by replacing last 4 digits
     * @param baseOrderNumber Base order number
     * @return Unique order number
     */
    private static String generateUniqueOrderNumber(String baseOrderNumber) {
        if (baseOrderNumber == null || baseOrderNumber.length() < 4) {
            return baseOrderNumber + Utilities.getUniqueInt(4);
        }
        
        // Replace last 4 digits with unique number
        String prefix = baseOrderNumber.substring(0, baseOrderNumber.length() - 4);
        String newSuffix = Utilities.getUniqueInt(4);
        return prefix + newSuffix;
    }
    
    /**
     * Get value from HashMap or return default
     * @param data HashMap with data
     * @param key Key to look for
     * @param defaultValue Default value if key not found
     * @return Value from HashMap or default
     */
    private static String getValueOrDefault(HashMap<String, String> data, String key, String defaultValue) {
        if (data != null && data.containsKey(key)) {
            return data.get(key);
        }
        return defaultValue;
    }
}

package com.delhivery.TMS_WMS.RequestBuilder;

import com.delhivery.TMS_WMS.pojo.ftlorder.request.*;
import com.delhivery.TMS_WMS.pojo.wmsorder.request.Consignee;
import com.delhivery.TMS_WMS.pojo.wmsorder.request.OrderLine;
import com.delhivery.core.utils.ConfigLoader;
import com.delhivery.core.utils.Utilities;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * FTL Request Builder - Follows Express RequestBuilder pattern
 * Uses builder pattern with HashMap override for flexibility
 * Ensures order_number, shipment number, and invoice_number are unique and synchronized
 */
public class FTLRequestBuilder {
    
    private static final ConfigLoader config = ConfigLoader.getInstance();
    
    /**
     * Build FTL Order Creation Request with defaults from qa.properties
     * HashMap data can override any default value
     * 
     * @param baseOrderNumber Base order number (last 4 digits will be auto-generated)
     * @param data HashMap with optional overrides
     * @return CreateFTLOrderRequest
     */
    public static CreateFTLOrderRequest buildCreateFTLOrderRequest(String baseOrderNumber, HashMap<String, String> data) {
        // Generate unique order number - this will be used for order_number, shipment number, and invoice_number
        String uniqueOrderNumber = generateUniqueOrderNumber(baseOrderNumber);
        
        System.out.println("=== FTL Request Builder: Generating Unique Numbers ===");
        System.out.println("Base Order Number: " + baseOrderNumber);
        System.out.println("Generated Unique Order Number: " + uniqueOrderNumber);
        System.out.println("This number will be used for: order_number, shipment number, and invoice_number");
        
        // Build Order Line with defaults
        OrderLine orderLine = new OrderLine();
        orderLine.setNumber("OL1");
        orderLine.setProductSku(getValueOrDefault(data, "product_sku", config.getProperty("ftl.order.default.productSku", "7019250")));
        orderLine.setQuantity(Integer.parseInt(getValueOrDefault(data, "quantity", config.getProperty("ftl.order.default.quantity", "2"))));
        orderLine.setClientId(getValueOrDefault(data, "client_id", config.getProperty("ftl.order.default.clientId", "74f67ef854154e0d97ad5be1e124bbc7")));
        orderLine.setBucket(getValueOrDefault(data, "bucket", config.getProperty("ftl.order.default.bucket", "PRIME")));
        
        List<OrderLine> orderLines = new ArrayList<>();
        orderLines.add(orderLine);
        
        // Build FTL Invoice (same number as order for synchronization)
        FTLInvoice invoice = new FTLInvoice();
        invoice.setInvoiceNumber(uniqueOrderNumber); // Same as order_number
        invoice.setPaymentMode(getValueOrDefault(data, "payment_mode", config.getProperty("ftl.order.default.paymentMode", "PREPAID")));
        invoice.setNetAmount(getValueOrDefault(data, "net_amount", config.getProperty("ftl.order.default.netAmount", "1234.00")));
        // Optional fields - set to null as per curl
        invoice.setInvoiceType(null);
        invoice.setEwaybill(null);
        invoice.setInvoiceValue(null);
        invoice.setInvoiceDate(null);
        invoice.setInvoicePdf(null);
        invoice.setQrCode(null);
        invoice.setTotalAmt(null);
        invoice.setCurrency(null);
        invoice.setCodAmount(null);
        
        // Build FTL Shipment (same number as order for synchronization)
        FTLShipment shipment = new FTLShipment();
        shipment.setEwaybillExpiryDate(0L);
        shipment.setShippedBy(getValueOrDefault(data, "shipped_by", config.getProperty("ftl.order.default.shippedBy", "2025-11-25 07:45:39")));
        shipment.setNumber(uniqueOrderNumber); // Same as order_number
        shipment.setWorkflow(getValueOrDefault(data, "workflow", config.getProperty("ftl.order.default.workflow", "31")));
        shipment.setChildCourier("FTL"); // FTL-specific field
        shipment.setFc(getValueOrDefault(data, "fc", config.getProperty("ftl.order.default.fc", "ROUTECHFC")));
        shipment.setEwaybillExpiryDate2(0L);
        shipment.setInvoice(invoice);
        shipment.setOrderLines(orderLines);
        
        List<FTLShipment> shipments = new ArrayList<>();
        shipments.add(shipment);
        
        // Build Consignee
        Consignee consignee = new Consignee();
        consignee.setName(getValueOrDefault(data, "consignee_name", config.getProperty("ftl.order.default.consigneeName", "GKS")));
        consignee.setAddressLine1(getValueOrDefault(data, "address_line1", config.getProperty("ftl.order.default.addressLine1", "Plot 5 Sector 44")));
        consignee.setPinCode(Integer.parseInt(getValueOrDefault(data, "pin_code", config.getProperty("ftl.order.default.pinCode", "122002"))));
        consignee.setCity(getValueOrDefault(data, "city", config.getProperty("ftl.order.default.city", "Gurgaon")));
        consignee.setState(getValueOrDefault(data, "state", config.getProperty("ftl.order.default.state", "Haryana")));
        consignee.setCountry(getValueOrDefault(data, "country", config.getProperty("ftl.order.default.country", "India")));
        consignee.setPrimaryPhoneNumber(getValueOrDefault(data, "primary_phone", config.getProperty("ftl.order.default.primaryPhone", "9000000001")));
        consignee.setSecondaryPhoneNumber(getValueOrDefault(data, "secondary_phone", config.getProperty("ftl.order.default.secondaryPhone", "9000000002")));
        
        // Build FTL Order
        FTLOrder order = new FTLOrder();
        order.setOrderNumber(uniqueOrderNumber); // Same unique number
        order.setOrderDate(getValueOrDefault(data, "order_date", config.getProperty("ftl.order.default.orderDate", "2025-11-25 07:45:39")));
        order.setOrderType(getValueOrDefault(data, "order_type", config.getProperty("ftl.order.default.orderType", "FWD")));
        order.setChannel(getValueOrDefault(data, "channel", config.getProperty("ftl.order.default.channel", "AUTOCHANNEL")));
        order.setShipments(shipments);
        order.setConsignee(consignee);
        
        List<FTLOrder> orders = new ArrayList<>();
        orders.add(order);
        
        // Build Request
        FTLOrderData orderData = new FTLOrderData(orders);
        CreateFTLOrderRequest request = new CreateFTLOrderRequest("CREATE", orderData);
        
        System.out.println("=== FTL Request Builder: Request Created ===");
        System.out.println("Order Number: " + uniqueOrderNumber);
        System.out.println("Shipment Number: " + uniqueOrderNumber);
        System.out.println("Invoice Number: " + uniqueOrderNumber);
        System.out.println("All three numbers are synchronized: " + uniqueOrderNumber);
        
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


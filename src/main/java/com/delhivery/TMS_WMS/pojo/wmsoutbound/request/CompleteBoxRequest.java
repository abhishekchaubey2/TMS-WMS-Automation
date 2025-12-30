package com.delhivery.TMS_WMS.pojo.wmsoutbound.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class CompleteBoxRequest {
    @JsonProperty("fulfillment_center_id")
    public String fulfillmentCenterId;
    @JsonProperty("shipment_id")
    public String shipmentId;
    @JsonProperty("containers")
    public List<Container> containers;
    
    @Getter
    @Builder
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Container {
        @JsonProperty("container_id")
        public String containerId;
        @JsonProperty("product")
        public Product product;
        @JsonProperty("products")
        public List<Product> products;
    }
    
    @Getter
    @Builder
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Product {
        @JsonProperty("product_id")
        public String productId;
        @JsonProperty("quantity")
        public Integer quantity;
        @JsonProperty("siob")
        public Boolean siob;
        @JsonProperty("sid_list")
        public Object sidList;
        @JsonProperty("client_uuid")
        public String clientUuuid;
    }
}


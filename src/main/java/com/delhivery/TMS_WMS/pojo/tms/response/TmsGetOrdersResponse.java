package com.delhivery.TMS_WMS.pojo.tms.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class TmsGetOrdersResponse {
    
    @JsonProperty("data")
    private List<TmsOrderData> data;
    
    public List<TmsOrderData> getData() {
        return data;
    }
    
    public void setData(List<TmsOrderData> data) {
        this.data = data;
    }
}

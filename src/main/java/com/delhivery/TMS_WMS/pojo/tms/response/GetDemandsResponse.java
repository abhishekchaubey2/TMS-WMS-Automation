package com.delhivery.TMS_WMS.pojo.tms.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class GetDemandsResponse {
    
    @JsonProperty("data")
    private DemandsData data;
    
    @JsonProperty("success")
    private Boolean success;
    
    public DemandsData getData() {
        return data;
    }
    
    public void setData(DemandsData data) {
        this.data = data;
    }
    
    public Boolean getSuccess() {
        return success;
    }
    
    public void setSuccess(Boolean success) {
        this.success = success;
    }
    
    /**
     * Get the list of demands from the nested data structure
     */
    public List<DemandListItem> getDemandsList() {
        return data != null ? data.getDemands() : null;
    }
    
    /**
     * Inner class for the data structure
     */
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class DemandsData {
        @JsonProperty("demands")
        private List<DemandListItem> demands;
        
        @JsonProperty("meta")
        private DemandsMeta meta;
        
        public List<DemandListItem> getDemands() {
            return demands;
        }
        
        public void setDemands(List<DemandListItem> demands) {
            this.demands = demands;
        }
        
        public DemandsMeta getMeta() {
            return meta;
        }
        
        public void setMeta(DemandsMeta meta) {
            this.meta = meta;
        }
    }
    
    /**
     * Inner class for meta information
     */
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class DemandsMeta {
        @JsonProperty("totalElements")
        private Integer totalElements;
        
        @JsonProperty("page")
        private Integer page;
        
        @JsonProperty("size")
        private Integer size;
        
        public Integer getTotalElements() {
            return totalElements;
        }
        
        public void setTotalElements(Integer totalElements) {
            this.totalElements = totalElements;
        }
        
        public Integer getPage() {
            return page;
        }
        
        public void setPage(Integer page) {
            this.page = page;
        }
        
        public Integer getSize() {
            return size;
        }
        
        public void setSize(Integer size) {
            this.size = size;
        }
    }
}


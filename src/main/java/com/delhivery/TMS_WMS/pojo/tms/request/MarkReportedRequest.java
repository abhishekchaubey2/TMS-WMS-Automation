package com.delhivery.TMS_WMS.pojo.tms.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Request POJO for TMS Mark Reported API
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class MarkReportedRequest {

    @JsonProperty("dataIds")
    private DataIds dataIds;

    @JsonProperty("data")
    private MarkReportedData data;

    public DataIds getDataIds() {
        return dataIds;
    }

    public void setDataIds(DataIds dataIds) {
        this.dataIds = dataIds;
    }

    public MarkReportedData getData() {
        return data;
    }

    public void setData(MarkReportedData data) {
        this.data = data;
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class DataIds {
        @JsonProperty("indent")
        private String indent;

        public String getIndent() {
            return indent;
        }

        public void setIndent(String indent) {
            this.indent = indent;
        }
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class MarkReportedData {
        @JsonProperty("vehicleNumber")
        private String vehicleNumber;

        @JsonProperty("driverName")
        private String driverName;

        @JsonProperty("countryCode")
        private String countryCode;

        @JsonProperty("driverNumber")
        private String driverNumber;

        @JsonProperty("reportingCenter")
        private String reportingCenter;

        @JsonProperty("reportedOn")
        private Long reportedOn;

        public String getVehicleNumber() {
            return vehicleNumber;
        }

        public void setVehicleNumber(String vehicleNumber) {
            this.vehicleNumber = vehicleNumber;
        }

        public String getDriverName() {
            return driverName;
        }

        public void setDriverName(String driverName) {
            this.driverName = driverName;
        }

        public String getCountryCode() {
            return countryCode;
        }

        public void setCountryCode(String countryCode) {
            this.countryCode = countryCode;
        }

        public String getDriverNumber() {
            return driverNumber;
        }

        public void setDriverNumber(String driverNumber) {
            this.driverNumber = driverNumber;
        }

        public String getReportingCenter() {
            return reportingCenter;
        }

        public void setReportingCenter(String reportingCenter) {
            this.reportingCenter = reportingCenter;
        }

        public Long getReportedOn() {
            return reportedOn;
        }

        public void setReportedOn(Long reportedOn) {
            this.reportedOn = reportedOn;
        }
    }
}


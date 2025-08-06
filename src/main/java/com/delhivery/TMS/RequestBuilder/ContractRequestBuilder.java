package com.delhivery.TMS.RequestBuilder;

import com.delhivery.TMS.pojo.contracts.request.ContractRequestPayload;
import com.delhivery.TMS.pojo.contracts.request.LaneDetail;
import com.delhivery.TMS.pojo.contracts.request.RateDetails;
import com.delhivery.TMS.pojo.contracts.request.TransitDetails;

import java.util.ArrayList;
import java.util.List;

/**
 * Request Builder for TMS Contracts API
 */
public class ContractRequestBuilder {
    
    /**
     * Build a sample contract request payload
     */
    public static ContractRequestPayload buildSampleContractRequest() {
        return ContractRequestPayload.builder()
                .vendorId("SIN9393")
                .contractName("Singh_transport_contract1")
                .startDate(1753122600000L)
                .endDate(1785436200000L)
                .serviceType("FTL")
                .contractType("PER_TRIP")
                .requestType("LONG_TERM")
                .laneDetails(buildSampleLaneDetails())
                .remarks("NA")
                .vendorName("singh transporter pvt ltd")
                .build();
    }
    
    /**
     * Build lane details for the contract
     */
    private static List<LaneDetail> buildSampleLaneDetails() {
        List<LaneDetail> laneDetails = new ArrayList<>();
        
        LaneDetail laneDetail = LaneDetail.builder()
                .origin("JNP123")
                .destination("ISPAT_GGN")
                .vehicleName("Closed 32FT MXL")
                .rateDetails(buildRateDetails())
                .transitDetails(buildTransitDetails())
                .build();
        
        laneDetails.add(laneDetail);
        return laneDetails;
    }
    
    /**
     * Build rate details for the lane
     */
    private static RateDetails buildRateDetails() {
        return RateDetails.builder()
                .rate(700.0)
                .rateType("Flat")
                .build();
    }
    
    /**
     * Build transit details for the lane
     */
    private static TransitDetails buildTransitDetails() {
        return TransitDetails.builder()
                .tat(12)
                .tatDisplayUnit("hours")
                .build();
    }
    
    /**
     * Build custom contract request with provided parameters
     */
    public static ContractRequestPayload buildCustomContractRequest(
            String vendorId,
            String contractName,
            Long startDate,
            Long endDate,
            String serviceType,
            String contractType,
            String requestType,
            String origin,
            String destination,
            String vehicleName,
            Double rate,
            String rateType,
            Integer tat,
            String tatDisplayUnit,
            String remarks,
            String vendorName) {
        
        List<LaneDetail> laneDetails = new ArrayList<>();
        
        LaneDetail laneDetail = LaneDetail.builder()
                .origin(origin)
                .destination(destination)
                .vehicleName(vehicleName)
                .rateDetails(RateDetails.builder()
                        .rate(rate)
                        .rateType(rateType)
                        .build())
                .transitDetails(TransitDetails.builder()
                        .tat(tat)
                        .tatDisplayUnit(tatDisplayUnit)
                        .build())
                .build();
        
        laneDetails.add(laneDetail);
        
        return ContractRequestPayload.builder()
                .vendorId(vendorId)
                .contractName(contractName)
                .startDate(startDate)
                .endDate(endDate)
                .serviceType(serviceType)
                .contractType(contractType)
                .requestType(requestType)
                .laneDetails(laneDetails)
                .remarks(remarks)
                .vendorName(vendorName)
                .build();
    }
} 
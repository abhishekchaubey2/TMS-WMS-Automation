package com.delhivery.TMS.RequestBuilder;

import com.delhivery.TMS.pojo.contracts.request.ContractRequestPayloadV2;
import com.delhivery.TMS.pojo.contracts.request.LaneDetail;
import com.delhivery.TMS.pojo.contracts.request.RateDetails;
import com.delhivery.TMS.pojo.contracts.request.TransitDetails;

import java.util.ArrayList;
import java.util.List;

/**
 * Request Builder for TMS Contracts API V2
 */
public class ContractRequestBuilderV2 {
    
    /**
     * Build a sample contract request payload based on the curl command
     */
    public static ContractRequestPayloadV2 buildSampleContractRequest() {
        return ContractRequestPayloadV2.builder()
                .vendorId("SIN9393")
                .contractName("singh_transport_contract2")
                .startDate(1753122600000L)
                .endDate(1785436200000L)
                .serviceType("LTL")
                .contractType("PER_TRIP")
                .requestType("LONG_TERM")
                .laneDetails(buildSampleLaneDetails())
                .vendorName("singh transporter pvt ltd")
                .volumetricCoefficient(389)
                .minChargableWt(38)
                .minCharge(38)
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
    public static ContractRequestPayloadV2 buildCustomContractRequest(
            String vendorId,
            String contractName,
            Long startDate,
            Long endDate,
            String serviceType,
            String contractType,
            String requestType,
            String origin,
            String destination,
            Double rate,
            String rateType,
            Integer tat,
            String tatDisplayUnit,
            String vendorName,
            Integer volumetricCoefficient,
            Integer minChargableWt,
            Integer minCharge) {
        
        try {
            System.out.println("=== ContractRequestBuilderV2.buildCustomContractRequest Debug ===");
            System.out.println("Building lane details...");
            
            List<LaneDetail> laneDetails = new ArrayList<>();
            
            System.out.println("Creating LaneDetail...");
            LaneDetail laneDetail = LaneDetail.builder()
                    .origin(origin)
                    .destination(destination)
                    .rateDetails(RateDetails.builder()
                            .rate(rate)
                            .rateType(rateType)
                            .build())
                    .transitDetails(TransitDetails.builder()
                            .tat(tat)
                            .tatDisplayUnit(tatDisplayUnit)
                            .build())
                    .build();
            
            System.out.println("Adding lane detail to list...");
            laneDetails.add(laneDetail);
            
            System.out.println("Building ContractRequestPayloadV2...");
            
            return ContractRequestPayloadV2.builder()
                    .vendorId(vendorId)
                    .contractName(contractName)
                    .startDate(startDate)
                    .endDate(endDate)
                    .serviceType(serviceType)
                    .contractType(contractType)
                    .requestType(requestType)
                    .laneDetails(laneDetails)
                    .vendorName(vendorName)
                    .volumetricCoefficient(volumetricCoefficient)
                    .minChargableWt(minChargableWt)
                    .minCharge(minCharge)
                    .build();
        } catch (Exception e) {
            System.err.println("=== Error in ContractRequestBuilderV2.buildCustomContractRequest ===");
            System.err.println("Error: " + e.getMessage());
            e.printStackTrace();
            throw e;
        }
    }
} 
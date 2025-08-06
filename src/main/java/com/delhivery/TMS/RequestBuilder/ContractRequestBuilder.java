package com.delhivery.TMS.RequestBuilder;

import com.delhivery.TMS.pojo.contracts.request.ContractRequestPayload;
import com.delhivery.TMS.pojo.contracts.request.LaneDetail;
import com.delhivery.TMS.pojo.contracts.request.RateDetails;
import com.delhivery.TMS.pojo.contracts.request.TransitDetails;

import java.util.ArrayList;
import java.util.List;

/**
 * TMS Contract Request Builder
 * Builds ContractRequestPayload objects with dynamic is_submit handling
 */
public class ContractRequestBuilder {
    
    /**
     * Build a sample contract request with all fields
     * @param isSubmit Whether to submit the contract (affects field nullification)
     * @return ContractRequestPayload
     */
    public static ContractRequestPayload buildSampleContractRequest(boolean isSubmit) {
        List<LaneDetail> laneDetails = new ArrayList<>();
        
        LaneDetail laneDetail = LaneDetail.builder()
                .origin("JNP123")
                .destination("ISPAT_GGN")
                .vehicleName("Closed 32FT MXL")
                .rateDetails(RateDetails.builder()
                        .rate(700.0)
                        .rateType("Flat")
                        .build())
                .transitDetails(TransitDetails.builder()
                        .tat(12)
                        .tatDisplayUnit("hours")
                        .build())
                .build();
        
        laneDetails.add(laneDetail);
        
        ContractRequestPayload requestPayload = ContractRequestPayload.builder()
                .vendorId("SIN9393")
                .contractName("Singh_transport_contract1")
                .startDate(1753122600000L)
                .endDate(1785436200000L)
                .serviceType("FTL")
                .contractType("PER_TRIP")
                .requestType("LONG_TERM")
                .laneDetails(laneDetails)
                .remarks("NA")
                .vendorName("singh transporter pvt ltd")
                .volumetricCoefficient(389)
                .minChargableWt(38)
                .minCharge(38)
                .build();
        
        // Apply conditional nullification based on is_submit
        requestPayload.applyConditionalNullification(isSubmit);
        
        return requestPayload;
    }
    
    /**
     * Build a custom contract request with provided parameters
     * @param vendorId Vendor ID
     * @param contractName Contract name
     * @param startDate Start date
     * @param endDate End date
     * @param serviceType Service type
     * @param contractType Contract type
     * @param requestType Request type
     * @param origin Origin
     * @param destination Destination
     * @param vehicleName Vehicle name
     * @param rate Rate
     * @param rateType Rate type
     * @param tat TAT
     * @param tatDisplayUnit TAT display unit
     * @param remarks Remarks
     * @param vendorName Vendor name
     * @param volumetricCoefficient Volumetric coefficient
     * @param minChargableWt Minimum chargable weight
     * @param minCharge Minimum charge
     * @param isSubmit Whether to submit the contract (affects field nullification)
     * @return ContractRequestPayload
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
            String vendorName,
            Integer volumetricCoefficient,
            Integer minChargableWt,
            Integer minCharge,
            boolean isSubmit) {
        
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
        
        ContractRequestPayload requestPayload = ContractRequestPayload.builder()
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
                .volumetricCoefficient(volumetricCoefficient)
                .minChargableWt(minChargableWt)
                .minCharge(minCharge)
                .build();
        
        // Apply conditional nullification based on is_submit
        requestPayload.applyConditionalNullification(isSubmit);
        
        return requestPayload;
    }
} 
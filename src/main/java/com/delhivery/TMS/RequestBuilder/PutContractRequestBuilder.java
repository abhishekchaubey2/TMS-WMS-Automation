package com.delhivery.TMS.RequestBuilder;

import com.delhivery.TMS.pojo.put_api_contracts.request.*;
import java.util.ArrayList;
import java.util.List;

/**
 * TMS PUT Contract Request Builder
 * Builds PutContractRequestPayload objects for contract updates
 */
public class PutContractRequestBuilder {
    
    /**
     * Build a sample PUT contract request with all fields
     * @return PutContractRequestPayload
     */
    public static PutContractRequestPayload buildSamplePutContractRequest() {
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
        
        // Create slab for detention charges
        List<DetentionLoadingCharges.DetentionLoadingChargesSlab> detentionLoadingSlabs = new ArrayList<>();
        detentionLoadingSlabs.add(DetentionLoadingCharges.DetentionLoadingChargesSlab.builder()
                .truckType("Closed 32FT MXL")
                .unit("days")
                .range("0-4")
                .amount("200")
                .build());
        
        // Create slab for detention unloading charges
        List<DetentionUnloadingCharges.DetentionUnloadingChargesSlab> detentionUnloadingSlabs = new ArrayList<>();
        detentionUnloadingSlabs.add(DetentionUnloadingCharges.DetentionUnloadingChargesSlab.builder()
                .truckType("Closed 32FT MXL")
                .unit("days")
                .range("0-4")
                .amount("200")
                .build());
        
        // Create slab for delay charges
        List<DelayCharges.DelayChargesSlab> delaySlabs = new ArrayList<>();
        delaySlabs.add(DelayCharges.DelayChargesSlab.builder()
                .truckType("Closed 32FT MXL")
                .unit("days")
                .range("0-3")
                .amount("79")
                .build());
        
        PutContractRequestPayload requestPayload = PutContractRequestPayload.builder()
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
                .loadingCharges(LoadingCharges.builder()
                        .chargesType("FLAT_AMOUNT_PER_TRIP")
                        .chargesAmount(123)
                        .build())
                .unloadingCharges(UnloadingCharges.builder()
                        .chargesType("FLAT_AMOUNT_PER_TRIP")
                        .chargesAmount(123)
                        .build())
                .detentionLoadingCharges(DetentionLoadingCharges.builder()
                        .slabs(detentionLoadingSlabs)
                        .build())
                .detentionUnloadingCharges(DetentionUnloadingCharges.builder()
                        .slabs(detentionUnloadingSlabs)
                        .build())
                .multiPointCharges(MultiPointCharges.builder()
                        .chargesType("PER_KM")
                        .chargesAmount(123)
                        .build())
                .delayCharges(DelayCharges.builder()
                        .slabs(delaySlabs)
                        .build())
                .maxDamageCharges(MaxDamageCharges.builder()
                        .chargesAmount(123)
                        .build())
                .minimumLRCharges(MinimumLRCharges.builder()
                        .chargesAmount(123)
                        .chargesType("FLAT_AMOUNT_PER_TRIP")
                        .build())
                .build();
        
        return requestPayload;
    }
    
    /**
     * Build a custom PUT contract request with provided parameters
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
     * @return PutContractRequestPayload
     */
    public static PutContractRequestPayload buildCustomPutContractRequest(
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
        
        // Create slab for detention charges
        List<DetentionLoadingCharges.DetentionLoadingChargesSlab> detentionLoadingSlabs = new ArrayList<>();
        detentionLoadingSlabs.add(DetentionLoadingCharges.DetentionLoadingChargesSlab.builder()
                .truckType("Closed 32FT MXL")
                .unit("days")
                .range("0-4")
                .amount("200")
                .build());
        
        // Create slab for detention unloading charges
        List<DetentionUnloadingCharges.DetentionUnloadingChargesSlab> detentionUnloadingSlabs = new ArrayList<>();
        detentionUnloadingSlabs.add(DetentionUnloadingCharges.DetentionUnloadingChargesSlab.builder()
                .truckType("Closed 32FT MXL")
                .unit("days")
                .range("0-4")
                .amount("200")
                .build());
        
        // Create slab for delay charges
        List<DelayCharges.DelayChargesSlab> delaySlabs = new ArrayList<>();
        delaySlabs.add(DelayCharges.DelayChargesSlab.builder()
                .truckType("Closed 32FT MXL")
                .unit("days")
                .range("0-3")
                .amount("79")
                .build());
        
        PutContractRequestPayload requestPayload = PutContractRequestPayload.builder()
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
                .loadingCharges(LoadingCharges.builder()
                        .chargesType("FLAT_AMOUNT_PER_TRIP")
                        .chargesAmount(123)
                        .build())
                .unloadingCharges(UnloadingCharges.builder()
                        .chargesType("FLAT_AMOUNT_PER_TRIP")
                        .chargesAmount(123)
                        .build())
                .detentionLoadingCharges(DetentionLoadingCharges.builder()
                        .slabs(detentionLoadingSlabs)
                        .build())
                .detentionUnloadingCharges(DetentionUnloadingCharges.builder()
                        .slabs(detentionUnloadingSlabs)
                        .build())
                .multiPointCharges(MultiPointCharges.builder()
                        .chargesType("PER_KM")
                        .chargesAmount(123)
                        .build())
                .delayCharges(DelayCharges.builder()
                        .slabs(delaySlabs)
                        .build())
                .maxDamageCharges(MaxDamageCharges.builder()
                        .chargesAmount(123)
                        .build())
                .minimumLRCharges(MinimumLRCharges.builder()
                        .chargesAmount(123)
                        .chargesType("FLAT_AMOUNT_PER_TRIP")
                        .build())
                .build();
        
        return requestPayload;
    }
} 
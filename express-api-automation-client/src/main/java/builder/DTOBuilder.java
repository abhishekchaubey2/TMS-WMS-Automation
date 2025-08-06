package builder;

import dto.WaybillDTO;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DTOBuilder {
    public static List<WaybillDTO> buildWaybillDTOList(Map<String, List<String>> masterToChildMap, Map<String, String> masterToPaymentModeMap) {
        List<WaybillDTO> waybillDTOList = new ArrayList<>();
        masterToChildMap.forEach((master, children) -> waybillDTOList
                .add(buildWaybillDTO(master, children, masterToPaymentModeMap.get(master))));
        return waybillDTOList;
    }

    private static WaybillDTO buildWaybillDTO(String master, List<String> children, String paymentMode) {
        return WaybillDTO.builder()
                .masterWaybillNumber(master)
                .waybillNumber(children)
                .paymentMode(paymentMode)
                .build();
    }
}

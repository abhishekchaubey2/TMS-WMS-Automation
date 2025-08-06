package dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;
import java.util.Map;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class WaybillDTO {
    private List<String> waybillNumber;
    private String masterWaybillNumber;
    private String paymentMode;
    private String packageType;
    private List<WaybillVolumeDTO> waybillVolumeDTOList;
}

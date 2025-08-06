package dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class WaybillVolumeDTO {
    private String waybillNumber;
    private String internalWeight;
    private String dwhWeight;
    private String length;
    private String height;
    private String breadth;
}

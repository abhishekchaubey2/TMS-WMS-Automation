
package com.delhivery.Express.pojo.CMS.FetchB2bClaimsDetailsApi.response;

import java.util.List;
import lombok.*;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "count",
    "data"
})
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Waybills {

    @JsonProperty("count")
    public int count;
    @JsonProperty("data")
    public List<Datum> data;

}

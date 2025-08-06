
package com.delhivery.Express.pojo.CMS.FetchClaimsReportApi.requestParamFilter;

import lombok.*;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "from_date",
    "to_date"
})
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FetchClaimsReportRequestParam {

    @JsonProperty("from_date")
    public String from_date;
    @JsonProperty("to_date")
    public String to_date;

}

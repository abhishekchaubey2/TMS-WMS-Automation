
package com.delhivery.Express.pojo.CMS.FetchB2bClaimsListingApi.requestParamFilter;

import lombok.*;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "limit",
    "offset",
    "from_date",
    "to_date"
})
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FetchB2bClaimsListingRequestParam {

    @JsonProperty("limit")
    public int limit;
    @JsonProperty("offset")
    public int offset;
    @JsonProperty("from_date")
    public String fromDate;
    @JsonProperty("to_date")
    public String toDate;

}

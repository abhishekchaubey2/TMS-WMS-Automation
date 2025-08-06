
package com.delhivery.Express.pojo.MarkDispatchApi.request;

import java.util.List;
import lombok.*;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "wbns",
    "user",
    "sl",
    "source",
    "dwbn",
    "dn",
    "vn",
    "md",
    "ph"
})
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MarkDispatchRequestPayload {

    @JsonProperty("wbns")
    public List<String> wbns = null;
    @JsonProperty("user")
    public String user;
    @JsonProperty("sl")
    public String sl;
    @JsonProperty("source")
    public String source;
    @JsonProperty("dwbn")
    public String dwbn;
    @JsonProperty("dn")
    public String dn;
    @JsonProperty("vn")
    public String vn;
    @JsonProperty("md")
    public String md;
    @JsonProperty("ph")
    public String ph;

}

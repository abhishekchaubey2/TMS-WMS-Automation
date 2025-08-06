
package com.delhivery.Express.pojo.BagRemoveTripApi.request;

import java.util.HashMap;
import java.util.Map;
import lombok.*;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "ref_id",
    "trid",
    "slid",
    "vid",
    "ntcid",
    "mawb",
    "action"
})
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BagRemoveTripRequestPayload {

    @JsonProperty("ref_id")
    public String refId;
    @JsonProperty("trid")
    public String trid;
    @JsonProperty("slid")
    public String slid;
    @JsonProperty("vid")
    public String vid;
    @JsonProperty("ntcid")
    public String ntcid;
    @JsonProperty("mawb")
    public String mawb;
    @JsonProperty("action")
    public String action;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}

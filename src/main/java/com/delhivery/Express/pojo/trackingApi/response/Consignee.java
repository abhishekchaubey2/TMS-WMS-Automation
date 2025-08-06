
package com.delhivery.Express.pojo.trackingApi.response;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.delhivery.Express.pojo.hqTrackingError.response.HQTrackError;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.*;
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "Address1",
    "Address2",
    "Address3",
    "City",
    "Country",
    "Name",
    "PinCode",
    "State",
    "Telephone1",
    "Telephone2"
})
public class Consignee {

    @JsonProperty("Address1")
    public List<Object> address1;
    @JsonProperty("Address2")
    public List<Object> address2;
    @JsonProperty("Address3")
    public String address3;
    @JsonProperty("City")
    public String city;
    @JsonProperty("Country")
    public String country;
    @JsonProperty("Name")
    public String name;
    @JsonProperty("PinCode")
    public long pinCode;
    @JsonProperty("State")
    public String state;
    @JsonProperty("Telephone1")
    public String telephone1;
    @JsonProperty("Telephone2")
    public String telephone2;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}

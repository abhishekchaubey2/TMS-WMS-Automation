
package com.delhivery.Express.pojo.hqTracking.response;

import java.util.List;

import com.delhivery.Express.pojo.GIApi.response.Datum;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "City",
    "Name",
    "Country",
    "Address2",
    "Address3",
    "PinCode",
    "State",
    "Telephone2",
    "Telephone1",
    "Address1"
})
public class Consignee {

    @JsonProperty("City")
    public String city;
    @JsonProperty("Name")
    public String name;
    @JsonProperty("Country")
    public String country;
    @JsonProperty("Address2")
    public List<Object> address2;
    @JsonProperty("Address3")
    public String address3;
    @JsonProperty("PinCode")
    public long pinCode;
    @JsonProperty("State")
    public String state;
    @JsonProperty("Telephone2")
    public String telephone2;
    @JsonProperty("Telephone1")
    public String telephone1;
    @JsonProperty("Address1")
    public List<Object> address1;

}

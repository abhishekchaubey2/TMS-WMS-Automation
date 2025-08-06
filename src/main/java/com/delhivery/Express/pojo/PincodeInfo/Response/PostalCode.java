
package com.delhivery.Express.pojo.PincodeInfo.Response;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "city",
    "cod",
    "inc",
    "district",
    "pin",
    "max_amount",
    "pre_paid",
    "cash",
    "max_weight",
    "pickup",
    "repl",
    "covid_zone",
    "country_code",
    "is_oda",
    "remarks",
    "sort_code",
    "state_code",
    "center"
})
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PostalCode {

    @JsonProperty("city")
    public String city;
    @JsonProperty("cod")
    public String cod;
    @JsonProperty("inc")
    public String inc;
    @JsonProperty("district")
    public String district;
    @JsonProperty("pin")
    public long pin;
    @JsonProperty("max_amount")
    public float maxAmount;
    @JsonProperty("pre_paid")
    public String prePaid;
    @JsonProperty("cash")
    public String cash;
    @JsonProperty("max_weight")
    public float maxWeight;
    @JsonProperty("pickup")
    public String pickup;
    @JsonProperty("repl")
    public String repl;
    @JsonProperty("covid_zone")
    public String covidZone;
    @JsonProperty("country_code")
    public String countryCode;
    @JsonProperty("is_oda")
    public String isOda;
    @JsonProperty("remarks")
    public String remarks;
    @JsonProperty("sort_code")
    public String sortCode;
    @JsonProperty("state_code")
    public String stateCode;
    @JsonProperty("center")
    public List<Center> center;

}

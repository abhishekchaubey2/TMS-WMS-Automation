package com.delhivery.Express.pojo.ClientUpdateNew.Request;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter

public class Services {
    @JsonProperty("COD")
    public Object cod;
    @JsonProperty("Pickup")
    public Object pickup;
    @JsonProperty("Pre-paid")
    public Object prePaid;
    @JsonProperty("REPL")
    public Object repl;
    @JsonProperty("Cash")
    public Object Cash;
    @JsonProperty("No Data")
    public Object NoData;


}

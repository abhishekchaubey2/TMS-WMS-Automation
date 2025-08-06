package com.delhivery.Express.pojo.FetchPackageDetailsSecond.response;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;

@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "ocid",
    "cnid",
    "bs"
})
@JsonIgnoreProperties(ignoreUnknown=true)
public class Bag {

    @JsonProperty("ocid")
    public Object ocid;
    @JsonProperty("cnid")
    public Object cnid;
    @JsonProperty("bs")
    public String bs;

}

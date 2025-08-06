package com.delhivery.Express.pojo.ewbnCollection;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

public class dataewbn {
    @JsonProperty("dcn")
    public Object dcn;
    @JsonProperty("src")
    public Object src;
    @JsonProperty("rs")
    public Object rs;
    @JsonProperty("is_active")
    public Object isActive;
    @JsonProperty("expiry")
    public Object expiry;
    @JsonProperty("wbn")
    public Object wbn;
    @JsonProperty("is_valid")
    public Object isValid;
    @JsonProperty("ewbn")
    public Object ewbn;
    @JsonProperty("date")
    public Date date;
    @JsonProperty("ud")
    public Object ud;
    @JsonProperty("_id")
    public Id id;
    @JsonProperty("u")
    public Object u;
}

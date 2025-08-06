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

public class MiscellaneousData {
    @JsonProperty("parent_account_name")
    public Object parentAccountName;
    @JsonProperty("parent_account_id")
    public Object parentAccountId;
    @JsonProperty("credit_limit_trips")
    public Object creditLimitTrips;
    @JsonProperty("credit_period")
    public Object creditPeriod;
    @JsonProperty("credit_note_limit")
    public Object creditNoteLimit;
    @JsonProperty("is_client_active")
    public Object isClientActive;
    @JsonProperty("pricing_exp")
    public Object pricingExp;
    @JsonProperty("mot_divisor")
    public MotDivisor motDivisor;

}

package com.delhivery.Express.pojo.Meesho1.Request;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter

public class ExtraParametersMeesho {
    @JsonProperty("return_reason")
    public Object returnReason;
}

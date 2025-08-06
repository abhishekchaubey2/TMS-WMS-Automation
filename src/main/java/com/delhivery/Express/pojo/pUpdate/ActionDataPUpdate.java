package com.delhivery.Express.pojo.pUpdate;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.*;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "location_id"
})
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ActionDataPUpdate {

    @JsonProperty("location_id")
    public String locationId;

}

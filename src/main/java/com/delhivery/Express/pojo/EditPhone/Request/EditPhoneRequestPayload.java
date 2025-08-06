
package com.delhivery.Express.pojo.EditPhone.Request;

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
    "data"
})
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EditPhoneRequestPayload {

    @JsonProperty("data")
    public List<DatumEditPhone> data;

}

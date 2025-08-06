package com.delhivery.Express.pojo.PackageCount.Response;

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
    "count"
})
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PackageCountResponsePayload {

    @JsonProperty("count")
    public long count;

}

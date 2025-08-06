package com.delhivery.Express.pojo.ClientDetailsFetch.Response;

import java.util.List;

import com.delhivery.Express.pojo.BagV3Api.response.Datum;
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
    "regex",
    "name",
    "description"
})

public class AcceptedId {

    @JsonProperty("regex")
    public String regex;
    @JsonProperty("name")
    public String name;
    @JsonProperty("description")
    public String description;

}
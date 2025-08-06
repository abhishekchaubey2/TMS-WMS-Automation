package com.delhivery.Express.pojo.PincodeInfo.Response;

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
    "code",
    "e",
    "cn",
    "s",
    "u",
    "sort_code",
    "ud"
})
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Center {

    @JsonProperty("code")
    public String code;
    @JsonProperty("e")
    public String e;
    @JsonProperty("cn")
    public String cn;
    @JsonProperty("s")
    public String s;
    @JsonProperty("u")
    public String u;
    @JsonProperty("sort_code")
    public String sortCode;
    @JsonProperty("ud")
    public String ud;

}

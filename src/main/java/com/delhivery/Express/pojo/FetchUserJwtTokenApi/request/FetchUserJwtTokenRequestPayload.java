
package com.delhivery.Express.pojo.FetchUserJwtTokenApi.request;

import lombok.*;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "username",
    "password"
})
@Builder
@Getter
@Setter
public class FetchUserJwtTokenRequestPayload {

    @JsonProperty("username")
    public String username;
    @JsonProperty("password")
    public String password;

}

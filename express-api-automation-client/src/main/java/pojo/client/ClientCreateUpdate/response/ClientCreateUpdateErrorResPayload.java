package pojo.client.ClientCreateUpdate.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ClientCreateUpdateErrorResPayload {
    @JsonProperty("non_field_errors")
    private String[] nonFieldErrors;
}

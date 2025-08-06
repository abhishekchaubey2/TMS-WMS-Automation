
package com.delhivery.Express.pojo.MarkDispatchApi.responseMPS;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.*;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "success",
    "failure",
    "remark",
    "id",
    "already_dispatched_wbns",
    "status"
})
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Meta {

    @JsonProperty("success")
    public List<String> success = null;
    @JsonProperty("failure")
    public List<Object> failure = null;
    @JsonProperty("remark")
    public String remark;
    @JsonProperty("id")
    public String id;
    @JsonProperty("already_dispatched_wbns")
    public List<Object> already_dispatched_wbns = null;
    @JsonProperty("status")
    public boolean status;

}

package com.delhivery.Express.pojo.QC.Response;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
"item_1"
})

public class Wbn {

@JsonProperty("item_1")
public List<Item1> item1;

}
package com.delhivery.Express.pojo.Meesho1.Request;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;


import java.util.List;
@Builder
@Getter
@Setter

public class Qc {
    @JsonProperty("item")
    public List<ItemMeesho> item;
    @JsonProperty("num_item")
    public Object numItem;
}

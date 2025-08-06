
package com.delhivery.Express.pojo.ClientFetchInternal.Response;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
@JsonIgnoreProperties(ignoreUnknown=true)
@JsonPropertyOrder({
    "billing_method",
    "product_type",
    "name",
    "end_date",
    "updated_at",
    "start_date",
    "alias",
    "client_type",
    "is_prepaid",
    "wallet_provider",
    "wallet_id",
    "uuid_key",
    "freight_collection",
    "id",
    "frs_code",
    "waybill_prefix"
})
public class Message {
	@JsonProperty("billing_method")
	public String billingMethod;
	@JsonProperty("product_type")
	public String productType;
	@JsonProperty("name")
	public String name;
	@JsonProperty("end_date")
	public String endDate;
	@JsonProperty("updated_at")
	public String updatedAt;
	@JsonProperty("start_date")
	public String startDate;
	@JsonProperty("alias")
	public String alias;
	@JsonProperty("client_type")
	public String clientType;
	@JsonProperty("is_prepaid")
	public Boolean isPrepaid;
	@JsonProperty("wallet_provider")
	public Object walletProvider;
	@JsonProperty("wallet_id")
	public String walletId;
	@JsonProperty("uuid_key")
	public String uuidKey;
	@JsonProperty("freight_collection")
	public Boolean freightCollection;
	@JsonProperty("id")
	public Integer id;
	@JsonProperty("frs_code")
	public String frsCode;
	@JsonProperty("waybill_prefix")
	public Integer waybillPrefix;
}

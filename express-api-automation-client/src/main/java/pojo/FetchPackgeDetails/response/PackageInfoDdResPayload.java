package pojo.FetchPackgeDetails.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class PackageInfoDdResPayload {
    @JsonProperty("dn")
    private String dn;

    @JsonProperty("dto")
    private boolean dto;

    @JsonProperty("fdd")
    private String fdd;

    @JsonProperty("rto")
    private boolean rto;

    @JsonProperty("ist")
    private boolean ist;

    @JsonProperty("t")
    private Object t;

    @JsonProperty("srt")
    private boolean srt;

    @JsonProperty("id")
    private String id;

    @JsonProperty("dct")
    private long dct;

    @JsonProperty("ph")
    private String ph;

    @JsonProperty("atc")
    private long atc;
}

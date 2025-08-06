package common.template;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.LinkedHashMap;
import java.util.Map;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ExpressHttpReqObject {
    private Object requestBody;
    private String token;
    private String url;
    private String pathParam;
    private Map<String,String> headers;
    private Boolean isHeaderDecorationRequired;
    private LinkedHashMap<String,String> queryParam;
}

package it.eg.cookbook.config;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;

@Data
public class CustomInterceptor implements RequestInterceptor {

    private String token;

    @Override
    public void apply(RequestTemplate requestTemplate) {
        if (!StringUtils.isEmpty(token)) {
            requestTemplate.header("Authorization", "Bearer " + token);
        }
    }
}

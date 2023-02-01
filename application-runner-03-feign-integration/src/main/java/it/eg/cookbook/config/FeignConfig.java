package it.eg.cookbook.config;

import feign.codec.ErrorDecoder;
import it.eg.cookbook.error.BatchException;
import it.eg.cookbook.error.ResponseCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StreamUtils;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;

@Configuration
@Slf4j
public class FeignConfig {

    @Bean
    public CustomInterceptor requestInterceptor() {
        return new CustomInterceptor();
    }


    @Bean
    public ErrorDecoder errorDecoder() {
        return (s, response) -> {
            String message;
            try (InputStream bodyIs = response.body().asInputStream()) {
                message = StreamUtils.copyToString(bodyIs, Charset.defaultCharset());
            } catch (IOException e) {
                return new Exception(e.getMessage());
            }
            switch (response.status()) {
                case 400:
                case 403:
                case 404:
                    return new BatchException(message, ResponseCode.BUSINESS_ERROR);
                case 500:
                    return new BatchException(message, ResponseCode.SYSTEM_ERROR);
                default:
                    return new ErrorDecoder.Default().decode(s, response);
            }
        };
    }

}
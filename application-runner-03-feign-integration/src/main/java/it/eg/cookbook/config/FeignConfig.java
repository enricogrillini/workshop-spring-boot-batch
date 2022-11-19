package it.eg.cookbook.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
public class FeignConfig {

//    @Bean
//    public RequestInterceptor requestInterceptor() {
//        return requestTemplate -> {
//            log.info("RequestInterceptor {}", requestTemplate.headers());
//            requestTemplate.header(AccessLogFilter.CORRELATION_ID_NAME, "feign-" + MDC.get(AccessLogFilter.CORRELATION_ID_NAME));
//        };
//    }

//    @Bean
//    public ErrorDecoder errorDecoder() {
//        return new ErrorDecoder() {
//            @Override
//            public Exception decode(String s, Response response) {
//                try {
//                    String message = StreamUtils.copyToString(response.body().asInputStream(), Charset.defaultCharset());
//                    return new BusinessException(message, ResponseCode.SYSTEM_ERROR);
//                } catch (IOException e) {
//                    return new BusinessException(e, ResponseCode.SYSTEM_ERROR);
//                }
//            }
//        };
//    }

}
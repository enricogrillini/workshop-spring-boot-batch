package it.eg.cookbook.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
public class FeignConfig {

    @Bean
    public CustomInterceptor requestInterceptor() {
        return new CustomInterceptor();
    }

}
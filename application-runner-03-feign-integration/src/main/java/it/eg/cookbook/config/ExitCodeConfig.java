package it.eg.cookbook.config;

import it.eg.cookbook.error.BatchException;
import org.springframework.boot.ExitCodeEvent;
import org.springframework.boot.ExitCodeExceptionMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;

@Configuration
public class ExitCodeConfig {

    @Bean
    public ExitCodeExceptionMapper exceptionBasedExitCode() {
        return exception -> {
            if (exception.getCause() instanceof BatchException) {
                return 1;
            } else {
                return 99;
            }
        };
    }

}

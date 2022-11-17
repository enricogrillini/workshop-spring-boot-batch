package it.eg.cookbook.config;

import org.springframework.boot.ExitCodeExceptionMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ExitCodeConfig {
    @Bean
    public ExitCodeExceptionMapper exceptionBasedExitCode() {
        return exception -> 99;
    }

//    @Bean
//    public ExitCodeExceptionMapper exceptionBasedExitCode() {
//        return exception -> {
//            if (exception.getCause() instanceof NumberFormatException) {
//                return 30;
//            }
//
//            if (exception.getCause() instanceof IllegalArgumentException) {
//                return 20;
//            }
//            return 99;
//        };
//    }

}

package it.eg.cookbook;

import it.eg.cookbook.service.BatchService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.ExitCodeExceptionMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@Slf4j
public class Application implements ApplicationRunner {

    @Autowired
    BatchService batchService;

    @Bean
    public ExitCodeExceptionMapper exceptionBasedExitCode() {
        return exception -> {
            if (exception.getCause() instanceof NumberFormatException) {
                return 30;
            }

            if (exception.getCause() instanceof IllegalArgumentException) {
                return 20;
            }
            return 99;
        };
    }

    public static void main(String[] args) {
        log.info("STARTING : Spring boot application starting");
        SpringApplication.run(Application.class, args);
        log.info("STOPPED  : Spring boot application stopped");
    }

    @Override
    public void run(ApplicationArguments args) {
        batchService.printParameters();
        throw new RuntimeException("sfiga");
    }
}
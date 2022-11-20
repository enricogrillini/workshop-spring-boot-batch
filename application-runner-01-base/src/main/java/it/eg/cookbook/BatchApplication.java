package it.eg.cookbook;

import it.eg.cookbook.service.ExceptionService;
import it.eg.cookbook.service.PrintService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BatchApplication implements ApplicationRunner {

    @Autowired
    PrintService printService;

    @Autowired
    ExceptionService exceptionService;

    public static void main(String[] args) {
        SpringApplication.run(BatchApplication.class, args);
    }

    @Override
    public void run(ApplicationArguments args) {
        printService.run();
        exceptionService.run();
    }

}
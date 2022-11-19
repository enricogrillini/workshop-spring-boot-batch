package it.eg.cookbook;

import it.eg.cookbook.service.BatchService;
import it.eg.cookbook.service.InfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class BatchApplication implements ApplicationRunner {

    @Autowired
    BatchService batchService;

    @Autowired
    InfoService infoService;

    public static void main(String[] args) {
        SpringApplication.run(BatchApplication.class, args);
    }

    @Override
    public void run(ApplicationArguments args) {
        if (args.containsOption("help")) {
            infoService.printHelp();
            return;
        }

        if (args.containsOption("version")) {
            infoService.printVersion();
            return;
        }

        batchService.run();
    }

}
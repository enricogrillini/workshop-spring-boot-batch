package it.eg.cookbook;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class BatchApplication implements CommandLineRunner {

    @Override
    public void run(String... args) {
    }

}

package it.eg.cookbook.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class PrintService {

    @Autowired
    ApplicationArguments args;

    public void printParameters() {
        log.info("  EXECUTING : Run method of Application Runner");

        // Parametri non opzionali
        log.info("    Non Optional Args");
        args.getNonOptionArgs().forEach(nonOption -> log.info("      - {} ", nonOption));

        // Parametri opzionali
        log.info("    Optional");
        args.getOptionNames().forEach(option -> log.info("      - {}: {}", option, args.getOptionValues(option).get(0)));
    }
}

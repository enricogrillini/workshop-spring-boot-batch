package it.eg.cookbook.service;

import it.eg.cookbook.error.BatchException;
import it.eg.cookbook.error.ResponseCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class BatchService {

    @Autowired
    ApplicationArguments args;

    // Verifica i parametroi passati al batch
    private void checkParameters() {
        if (args.getOptionNames().isEmpty()) {
            throw new BatchException("Specificare almento un parametro", ResponseCode.PARAMETER_ERROR);
        }
    }

    public void run() {
        checkParameters();

        log.info("  EXECUTING : Run method of Application Runner");

        // Parametri non opzionali
        log.info("    Non Optional Args");
        args.getNonOptionArgs().forEach(nonOption -> log.info("      - {} ", nonOption));

        // Parametri opzionali
        log.info("    Optional");
        args.getOptionNames().forEach(option -> log.info("      - {}: {}", option, args.getOptionValues(option)));
    }

}

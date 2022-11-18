package it.eg.cookbook.service;

import it.eg.cookbook.error.BatchException;
import it.eg.cookbook.error.ResponseCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;

@Service
@Slf4j
public class HelpService {

    @Autowired
    ApplicationArguments args;

    @Value("classpath:help.txt")
    Resource resource;

    private String help() {
        try (Reader reader = new InputStreamReader(resource.getInputStream(), StandardCharsets.UTF_8)) {
            return FileCopyUtils.copyToString(reader);
        } catch (IOException e) {
            throw new BatchException("Impossibile trovare l'help", ResponseCode.SYSTEM_ERROR);
        }
    }

    // Verifica i parametroi passati al batch
    public void checkParameters() {
        try {
            if (args.getOptionNames().isEmpty()) {
                throw new BatchException("Specificare almeno un parametro", ResponseCode.PARAMETER_ERROR);
            }
        } catch (BatchException ex) {
            printHelp();
            throw ex;
        }
    }

    // Stampa l'help
    public void printHelp() {
        log.info(help());
    }

}
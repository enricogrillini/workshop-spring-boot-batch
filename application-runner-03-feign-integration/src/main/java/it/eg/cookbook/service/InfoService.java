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
public class InfoService {

    @Value("${info.app.name}")
    String appName;

    @Value("${info.app.version}")
    String appVersion;

    @Value("${info.app.description}")
    String appDescription;

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

    // Stampa l'help
    public void printHelp() {
        log.info(help());
    }

    // Stampa la versione del batch
    public void printVersion() {
        log.info(String.format("%n%n%s - %s%n%s%n", appName, appVersion, appDescription));
    }

}

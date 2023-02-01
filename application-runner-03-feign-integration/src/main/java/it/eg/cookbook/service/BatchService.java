package it.eg.cookbook.service;

import it.eg.cookbook.client.DocumentClient;
import it.eg.cookbook.client.SecurityClient;
import it.eg.cookbook.config.CustomInterceptor;
import it.eg.cookbook.error.BatchException;
import it.eg.cookbook.error.ResponseCode;
import it.eg.cookbook.gen.model.Document;
import it.eg.cookbook.gen.model.Token;
import it.eg.cookbook.gen.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class BatchService {

    @Autowired
    SecurityClient securityClient;

    @Autowired
    DocumentClient documentClient;

    @Autowired
    CustomInterceptor customInterceptor;

    @Autowired
    ApplicationArguments args;

    // Verifica i parametroi passati al batch
    private void checkParameters() {
        if (!args.containsOption("endpoint") || args.getOptionValues("endpoint").isEmpty()) {
            throw new BatchException("Specificare l'endpoint", ResponseCode.PARAMETER_ERROR);
        }
    }

    public void run() {
        checkParameters();

        // Login
        User user = new User()
                .issuer("www.idm.com")
                .subject("writer-batch")
                .audience("progetto-cookbook")
                .ttlMillis(3600000L);

        ResponseEntity<Token> tokenEntity = securityClient.postGenerateToken(user);

        // Registro il token per poterlo propagare alle call successive
        Token token = tokenEntity.getBody();
        if (token != null) {
            customInterceptor.setToken(token.getJwtToken());
        }

        // Aggiorno i documenti
        ResponseEntity<List<Document>> listEntity = documentClient.getDocuments();
        if (listEntity.getStatusCode() == HttpStatus.OK) {
            for (Document document : listEntity.getBody()) {
                log.info("Documento: {}", document.getName());
                document.setName("New name - " + document.getName());

                documentClient.putDocument(document);
            }
        }
    }
}

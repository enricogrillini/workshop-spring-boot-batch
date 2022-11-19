package it.eg.cookbook.service;

import it.eg.cookbook.client.DocumentClient;
import it.eg.cookbook.client.SecurityClient;
import it.eg.cookbook.error.BatchException;
import it.eg.cookbook.error.ResponseCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import it.eg.cookbook.gen.model.Document;

import java.util.List;

@Service
@Slf4j
public class BatchService {

    @Autowired
    SecurityClient securityClient;

    @Autowired
    DocumentClient documentClient;

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

//        User user = new User()
//                .issuer("www.idm.com")
//                .subject("writer.1")
//                .audience("progetto-cookbook")
//                .ttlMillis(3600000L);
//
//        ResponseEntity<Token> tokenEntity = securityClient.postGenerateToken(user);

        ResponseEntity<List<Document>> listEntity = documentClient.getDocuments();

        System.out.println(listEntity.getBody());


    }
}

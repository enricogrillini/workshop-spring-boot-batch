package it.eg.cookbook;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import it.eg.cookbook.base.AbstractTest;
import it.eg.cookbook.client.DocumentClient;
import it.eg.cookbook.client.SecurityClient;
import it.eg.cookbook.gen.model.Document;
import it.eg.cookbook.gen.model.ResponseMessage;
import it.eg.cookbook.gen.model.Token;
import it.eg.cookbook.service.BatchService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

@SpringBootTest(classes = BatchApplication.class, args = {"--endpoint=http://localhost:8082"})
class BatchTest extends AbstractTest {

    @SpyBean
    BatchService batchService;

    @MockBean
    SecurityClient securityClient;

    @MockBean
    DocumentClient documentClient;

    @Test
    void batchTest() throws JsonProcessingException {

        // Token
        Token token = readMockFile(Token.class, "-POST-generate-token");
        Mockito
                .doReturn(new ResponseEntity<>(token, HttpStatus.OK))
                .when(securityClient)
                .postGenerateToken(Mockito.any());

        // Lista documenti
        List<Document> documentList = objectMapper.readValue(readMockFile("-GET-document"), new TypeReference<List<Document>>() {
        });
        Mockito
                .doReturn(new ResponseEntity<>(documentList, HttpStatus.OK))
                .when(documentClient)
                .getDocuments();

        // Aggiornamento documenti
        ResponseMessage responseMessage = readMockFile(ResponseMessage.class, "-PUT-document");
        Mockito
                .doReturn(new ResponseEntity<>(responseMessage, HttpStatus.OK))
                .when(documentClient)
                .putDocument(Mockito.any());


        batchService.run();

        Mockito.verify(batchService, Mockito.times(1)).run();
        Mockito.verify(securityClient, Mockito.times(1)).postGenerateToken(Mockito.any());
        Mockito.verify(documentClient, Mockito.times(1)).getDocuments();
        Mockito.verify(documentClient, Mockito.times(3)).putDocument(Mockito.any());
    }

    @Test
    void batchTest_KO() throws JsonProcessingException {
        // Token
        Token token = readMockFile(Token.class, "-POST-generate-token");
        Mockito
                .doReturn(new ResponseEntity<>(token, HttpStatus.OK))
                .when(securityClient)
                .postGenerateToken(Mockito.any());

        // Lista documenti
        List<Document> documentList = objectMapper.readValue(readMockFile("-GET-document"), new TypeReference<List<Document>>() {
        });
        Mockito
                .doReturn(new ResponseEntity<>(documentList, HttpStatus.BAD_REQUEST))
                .when(documentClient)
                .getDocuments();

        batchService.run();

        Mockito.verify(batchService, Mockito.times(1)).run();
        Mockito.verify(securityClient, Mockito.times(1)).postGenerateToken(Mockito.any());
        Mockito.verify(documentClient, Mockito.times(1)).getDocuments();
        Mockito.verify(documentClient, Mockito.times(0)).putDocument(Mockito.any());
    }

}

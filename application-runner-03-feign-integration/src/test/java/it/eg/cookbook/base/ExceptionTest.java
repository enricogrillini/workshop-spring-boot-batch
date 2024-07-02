package it.eg.cookbook.base;

import it.eg.cookbook.BatchApplication;
import it.eg.cookbook.client.DocumentClient;
import it.eg.cookbook.client.SecurityClient;
import it.eg.cookbook.error.BatchException;
import it.eg.cookbook.error.ResponseCode;
import it.eg.cookbook.service.BatchService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;

@SpringBootTest(classes = BatchApplication.class, args = {})
class ExceptionTest extends AbstractTest {

    @SpyBean
    BatchService batchService;

    @Test
    void batchTest() {
        BatchException batchException = Assertions.assertThrows(BatchException.class, () -> batchService.run());
        Assertions.assertEquals(ResponseCode.PARAMETER_ERROR, batchException.getCode());

        Mockito.verify(batchService, Mockito.times(1)).run();
    }

}

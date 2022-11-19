package it.eg.cookbook.base;

import it.eg.cookbook.BatchApplication;
import it.eg.cookbook.error.BatchException;
import it.eg.cookbook.error.ResponseCode;
import it.eg.cookbook.service.BatchService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;

@SpringBootTest(classes = BatchApplication.class, args = {})
class ExceptionTest {

    @SpyBean
    BatchService batchService;

    @Test
    void batchTestKo() {
        BatchException batchException = Assertions.assertThrows(BatchException.class, () -> batchService.run());
        Assertions.assertEquals(ResponseCode.PARAMETER_ERROR, batchException.getCode());

        Mockito.verify(batchService, Mockito.times(1)).run();
    }

}

package it.eg.cookbook;

import it.eg.cookbook.error.BatchException;
import it.eg.cookbook.error.ResponseCode;
import it.eg.cookbook.service.RunService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.core.io.Resource;

@SpringBootTest(classes = BatchApplication.class, args = {})
class ExceptionTest {

    @SpyBean
    RunService runService;

    @Test
    void printServiceTest() {
        BatchException batchException = Assertions.assertThrows(BatchException.class, () -> runService.run());
        Assertions.assertEquals(ResponseCode.PARAMETER_ERROR, batchException.getCode());

        Mockito.verify(runService, Mockito.times(1)).run();
    }

}

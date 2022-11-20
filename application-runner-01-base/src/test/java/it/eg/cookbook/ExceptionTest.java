package it.eg.cookbook;

import it.eg.cookbook.service.ExceptionService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;

@SpringBootTest(classes = BatchApplication.class, args = {"--exception"})
class ExceptionTest {

    @SpyBean
    ExceptionService exceptionService;

    @Test
    void printServiceTest() {
        Assertions.assertThrows(RuntimeException.class, () -> exceptionService.run());
        Mockito.verify(exceptionService, Mockito.times(1)).run();
    }

}

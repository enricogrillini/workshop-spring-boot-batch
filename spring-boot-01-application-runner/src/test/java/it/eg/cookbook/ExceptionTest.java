package it.eg.cookbook;

import it.eg.cookbook.service.ExceptionService;
import it.eg.cookbook.service.PrintService;
import it.eg.cookbook.service.RunService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;

@SpringBootTest(args = {"--exception"})
class ExceptionTest {

    @Autowired
    RunService runService;

    @SpyBean
    ExceptionService exceptionService;

    @Test
    void printServiceTest() {
        Assertions.assertThrows(RuntimeException.class, () -> runService.run());

        Mockito.verify(exceptionService, Mockito.times(1)).throwException();
    }

}

package it.eg.cookbook;

import it.eg.cookbook.service.RunService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;

@SpringBootTest(classes = BatchApplication.class, args = {"--exception"})
class ExceptionTest {

    @SpyBean
    RunService runService;

    @Test
    void printServiceTest() {
        Assertions.assertThrows(RuntimeException.class, () -> runService.run());
        Mockito.verify(runService, Mockito.times(1)).run();
    }

}

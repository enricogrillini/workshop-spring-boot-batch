package it.eg.cookbook;

import it.eg.cookbook.service.BatchService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;

@SpringBootTest(classes = BatchApplication.class, args = {"--print", "--opt=opt-1", "required-1"})
class PrintTest {

    @SpyBean
    BatchService batchService;

    @Test
    void printServiceTest() {
        batchService.run();

        Mockito.verify(batchService, Mockito.times(1)).run();
    }

}

package it.eg.cookbook.base;

import it.eg.cookbook.BatchApplication;
import it.eg.cookbook.service.BatchService;
import it.eg.cookbook.service.InfoService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;

@SpringBootTest(classes = BatchApplication.class, args = {"--help"})
class InfoHelpTest {

    @Autowired
    BatchService batchService;

    @SpyBean
    InfoService infoService;

    @Test
    void printServiceTest() {
        batchService.run();

        Mockito.verify(infoService, Mockito.times(1)).printHelp();
    }

}

package it.eg.cookbook.base;

import it.eg.cookbook.BatchApplication;
import it.eg.cookbook.service.InfoService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;

@SpringBootTest(classes = BatchApplication.class, args = {"--help"})
class InfoHelpTest {

    @SpyBean
    InfoService infoService;

    @Test
    void infoService_Help() {
        infoService.run();

        Mockito.verify(infoService, Mockito.times(1)).printHelp();
    }

}

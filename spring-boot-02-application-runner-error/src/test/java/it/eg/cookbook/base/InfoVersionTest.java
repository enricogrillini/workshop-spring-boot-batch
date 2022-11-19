package it.eg.cookbook.base;

import it.eg.cookbook.BatchApplication;
import it.eg.cookbook.service.InfoService;
import it.eg.cookbook.service.RunService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;

@SpringBootTest(classes = BatchApplication.class, args = {"--version"})
class InfoVersionTest {

    @Autowired
    RunService runService;

    @SpyBean
    InfoService infoService;

    @Test
    void printServiceTest() {
        runService.run();

        Mockito.verify(infoService, Mockito.times(1)).printVersion();
    }

}

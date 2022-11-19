package it.eg.cookbook;

import it.eg.cookbook.service.HelpService;
import it.eg.cookbook.service.PrintService;
import it.eg.cookbook.service.RunService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;

@SpringBootTest(classes = BatchApplication.class, args = {"--help"})
class HelpTest {

    @Autowired
    RunService runService;

    @SpyBean
    HelpService printService;

    @Test
    void printServiceTest() {
        runService.run();

        Mockito.verify(printService, Mockito.times(1)).printHelp();
    }

}

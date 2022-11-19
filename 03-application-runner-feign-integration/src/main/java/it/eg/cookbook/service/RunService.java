package it.eg.cookbook.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class RunService {

    @Autowired
    ApplicationArguments applicationArguments;

    @Autowired
    InfoService infoService;

    @Autowired
    PrintService batchService;

    public void run() {
        infoService.checkParameters();

        if (applicationArguments.containsOption("help")) {
            infoService.printHelp();
            return;
        }

        if (applicationArguments.containsOption("version")) {
            infoService.printVersion();
            return;
        }

        if (applicationArguments.containsOption("print")) {
            batchService.printParameters();
        }
    }
}

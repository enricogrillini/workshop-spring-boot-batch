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
    HelpService helpService;

    @Autowired
    PrintService batchService;

    public void run() {
        if (applicationArguments.containsOption("help")) {
            helpService.printHelp();
        }

        if (applicationArguments.containsOption("print")) {
            batchService.printParameters();
        }
    }
}

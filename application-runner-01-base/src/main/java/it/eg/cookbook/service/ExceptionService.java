package it.eg.cookbook.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ExceptionService {

    @Autowired
    ApplicationArguments applicationArguments;

    @Autowired
    PrintService batchService;

    public void run() {
        if (applicationArguments.containsOption("exception")) {
            throw new RuntimeException("Test Exception");
        }
    }
}

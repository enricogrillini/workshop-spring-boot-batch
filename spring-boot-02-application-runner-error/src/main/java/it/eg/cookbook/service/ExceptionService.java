package it.eg.cookbook.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.stereotype.Service;

@Service
public class ExceptionService {

    @Autowired
    ApplicationArguments args;

    public void throwException() {
        throw new RuntimeException("Test Exception");
    }
}
